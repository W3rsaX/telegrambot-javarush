package com.github.javarushcommunity.tbjr.bot;

import static com.github.javarushcommunity.tbjr.command.CommandName.NO;

import com.github.javarushcommunity.tbjr.command.CommandContainer;
import com.github.javarushcommunity.tbjr.javarushclient.JavaRushGroupClient;
import com.github.javarushcommunity.tbjr.service.GroupSubService;
import com.github.javarushcommunity.tbjr.service.SendBotMessageServiceImpl;
import com.github.javarushcommunity.tbjr.service.StatisticsService;
import com.github.javarushcommunity.tbjr.service.TelegramUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class UpdateConsumer implements LongPollingSingleThreadUpdateConsumer {

  public static String COMMAND_PREFIX = "/";

  private final TelegramClient telegramClient;

  private final CommandContainer commandContainer;

  @Autowired
  public UpdateConsumer(TelegramClient telegramClient, TelegramUserService telegramUserService,
      JavaRushGroupClient groupClient, GroupSubService groupSubService,
      @Value("#{'${bot.admins}'.split(',')}") List<String> admins, StatisticsService statisticsService) {
    this.telegramClient = telegramClient;
    this.commandContainer = new CommandContainer(
        new SendBotMessageServiceImpl(this.telegramClient), telegramUserService, groupClient,
        groupSubService, admins, statisticsService);
  }

  @Override
  public void consume(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      String message = update.getMessage().getText().trim();
      String username = update.getMessage().getFrom().getUserName();
      if (message.startsWith(COMMAND_PREFIX)) {
        String commandIdentifier = message.split(" ")[0].toLowerCase();

        commandContainer.findCommand(commandIdentifier, username).execute(update);
      } else {
        commandContainer.findCommand(NO.getCommandName(), username).execute(update);
      }
    }
  }
}

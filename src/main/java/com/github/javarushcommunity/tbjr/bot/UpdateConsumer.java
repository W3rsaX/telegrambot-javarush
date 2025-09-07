package com.github.javarushcommunity.tbjr.bot;

import static com.github.javarushcommunity.tbjr.command.CommandName.NO;

import com.github.javarushcommunity.tbjr.command.CommandContainer;
import com.github.javarushcommunity.tbjr.javarushclient.JavaRushGroupClient;
import com.github.javarushcommunity.tbjr.service.GroupSubService;
import com.github.javarushcommunity.tbjr.service.SendBotMessageServiceImpl;
import com.github.javarushcommunity.tbjr.service.TelegramUserService;
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
  public UpdateConsumer(TelegramClient telegramClient, TelegramUserService telegramUserService, JavaRushGroupClient groupClient, GroupSubService groupSubService) {
    this.telegramClient = telegramClient;
    this.commandContainer = new CommandContainer(
        new SendBotMessageServiceImpl(this.telegramClient), telegramUserService, groupClient, groupSubService);
  }

  @Override
  public void consume(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      String message = update.getMessage().getText().trim();
      if (message.startsWith(COMMAND_PREFIX)) {
        String commandIdentifier = message.split(" ")[0].toLowerCase();

        commandContainer.retrieveCommand(commandIdentifier).execute(update);
      } else {
        commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
      }
    }
  }
}

package com.github.javarushcommunity.tbjr.bot;

import static com.github.javarushcommunity.tbjr.command.CommandName.NO;

import com.github.javarushcommunity.tbjr.command.CommandContainer;
import com.github.javarushcommunity.tbjr.service.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class UpdateConsumer implements LongPollingSingleThreadUpdateConsumer {

  public static String COMMAND_PREFIX = "/";

  @Value("${bot.token}")
  private String botToken;

  private final TelegramClient telegramClient;

  private final CommandContainer commandContainer;

  public UpdateConsumer(@Value("${bot.token}") String botToken) {
    this.telegramClient = new OkHttpTelegramClient(botToken);
    this.commandContainer = new CommandContainer(
        new SendBotMessageServiceImpl(this.telegramClient));
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

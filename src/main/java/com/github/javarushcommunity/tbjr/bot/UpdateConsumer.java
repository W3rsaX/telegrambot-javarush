package com.github.javarushcommunity.tbjr.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class UpdateConsumer implements LongPollingSingleThreadUpdateConsumer {

  @Value("${bot.token}")
  private String botToken;

  private final TelegramClient telegramClient;

  public UpdateConsumer(@Value("${bot.token}") String botToken) {
    this.telegramClient = new OkHttpTelegramClient(botToken);
  }

  @Override
  public void consume(Update update) {
    if(update.hasMessage() && update.getMessage().hasText()) {
      String message = update.getMessage().getText().trim();
      String chatId = update.getMessage().getChatId().toString();
      SendMessage sm = SendMessage.builder()
          .text(message)
          .chatId(chatId)
          .build();

      try {
        telegramClient.execute(sm);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }
}

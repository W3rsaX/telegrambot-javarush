package com.github.javarushcommunity.tbjr.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;

@Component
public class TelegramBotJavaRush implements SpringLongPollingBot {

  private final UpdateConsumer updateConsumer;

  @Value("${bot.token}")
  private String botToken;

  public TelegramBotJavaRush(UpdateConsumer updateConsumer) {
    this.updateConsumer = updateConsumer;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }

  @Override
  public LongPollingUpdateConsumer getUpdatesConsumer() {
    return updateConsumer;
  }
}

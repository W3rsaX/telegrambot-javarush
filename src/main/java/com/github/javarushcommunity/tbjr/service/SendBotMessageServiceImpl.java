package com.github.javarushcommunity.tbjr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class SendBotMessageServiceImpl implements SendBotMessageService {

  @Value("${bot.token}")
  private String botToken;

  private final TelegramClient telegramClient;

  @Autowired
  public SendBotMessageServiceImpl(TelegramClient telegramClient) {
    this.telegramClient = telegramClient;
  }

  @Override
  public void sendMessage(String chatId, String message) {
    SendMessage sendMessage = SendMessage.builder()
        .chatId(chatId)
        .text(message)
        .parseMode(ParseMode.HTML)
        .build();

    try {
      telegramClient.execute(sendMessage);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }
}

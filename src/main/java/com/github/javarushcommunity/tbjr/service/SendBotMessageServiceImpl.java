package com.github.javarushcommunity.tbjr.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

  private final TelegramClient telegramClient;

  @Autowired
  public SendBotMessageServiceImpl(TelegramClient telegramClient) {
    this.telegramClient = telegramClient;
  }

  @Override
  public void sendMessage(Long chatId, String message) {
    if (isBlank(message)) return;

    SendMessage sendMessage = SendMessage.builder()
        .chatId(chatId.toString())
        .text(message)
        .parseMode(ParseMode.HTML)
        .build();

    try {
      telegramClient.execute(sendMessage);
    } catch (TelegramApiException e) {
      //todo add logging to the project.
      e.printStackTrace();
    }
  }

  @Override
  public void sendMessage(Long chatId, List<String> messages) {
    if (isEmpty(messages)) return;

    messages.forEach(m -> sendMessage(chatId, m));
  }
}

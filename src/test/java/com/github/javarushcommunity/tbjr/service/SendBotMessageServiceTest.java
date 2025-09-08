package com.github.javarushcommunity.tbjr.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@DisplayName("Unit-level testing for SendBotMessageService")
public class SendBotMessageServiceTest {
  private SendBotMessageService sendBotMessageService;
  private TelegramClient telegramClient;

  @BeforeEach
  public void init() {
    telegramClient = Mockito.mock(TelegramClient.class);
    sendBotMessageService = new SendBotMessageServiceImpl(telegramClient);
  }

  @Test
  public void shouldProperlySendMessage() throws TelegramApiException {
    Long chatId = 123456L;
    String message = "test_message";

    SendMessage sendMessage = SendMessage.builder()
        .chatId(chatId)
        .text(message)
        .parseMode(ParseMode.HTML)
        .build();

    sendBotMessageService.sendMessage(chatId, message);

    Mockito.verify(telegramClient).execute(sendMessage);
  }
}

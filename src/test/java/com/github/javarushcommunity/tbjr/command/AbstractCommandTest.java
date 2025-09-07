package com.github.javarushcommunity.tbjr.command;

import com.github.javarushcommunity.tbjr.service.SendBotMessageService;
import com.github.javarushcommunity.tbjr.service.SendBotMessageServiceImpl;
import com.github.javarushcommunity.tbjr.service.TelegramUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

abstract class AbstractCommandTest {

  protected TelegramClient telegramClient = Mockito.mock(TelegramClient.class);

  protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(
      telegramClient);

  protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);

  abstract String getCommandName();

  abstract String getCommandMessage();

  abstract Command getCommand();

  @Test
  public void shouldProperlyExecuteCommand() throws TelegramApiException {
    Long chatId = 1234567824356L;

    Update update = new Update();
    Message message = Mockito.mock(Message.class);
    Mockito.when(message.getChatId()).thenReturn(chatId);
    Mockito.when(message.getText()).thenReturn(getCommandName());
    update.setMessage(message);

    SendMessage sendMessage = SendMessage.builder()
        .chatId(chatId.toString())
        .text(getCommandMessage())
        .parseMode(ParseMode.HTML)
        .build();

    getCommand().execute(update);

    Mockito.verify(telegramClient).execute(sendMessage);
  }

  public static Update prepareUpdate(Long chatId, String commandName) {
    Update update = new Update();
    Message message = Mockito.mock(Message.class);
    Mockito.when(message.getChatId()).thenReturn(chatId);
    Mockito.when(message.getText()).thenReturn(commandName);
    update.setMessage(message);
    return update;
  }
}

package com.github.javarushcommunity.tbjr.command;

import com.github.javarushcommunity.tbjr.repository.entity.TelegramUser;
import com.github.javarushcommunity.tbjr.service.SendBotMessageService;
import com.github.javarushcommunity.tbjr.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

  private final SendBotMessageService sendBotMessageService;
  private final TelegramUserService telegramUserService;

  public final static String START_MESSAGE =
      "Привет. Я Telegram Bot Javarush. Я помогу тебе быть в курсе последних " +
          "статей тех авторов, которые тебе интересны.";

  public StartCommand(SendBotMessageService sendBotMessageService,
      TelegramUserService telegramUserService) {
    this.sendBotMessageService = sendBotMessageService;
    this.telegramUserService = telegramUserService;
  }

  @Override
  public void execute(Update update) {
    String chatId = update.getMessage().getChatId().toString();

    telegramUserService.findByChatId(chatId).ifPresentOrElse(
        user -> {
          user.setActive(true);
          telegramUserService.save(user);
        },
        () -> {
          TelegramUser telegramUser = new TelegramUser();
          telegramUser.setChatId(chatId);
          telegramUser.setActive(true);
          telegramUserService.save(telegramUser);
        });

    sendBotMessageService.sendMessage(chatId, START_MESSAGE);
  }
}

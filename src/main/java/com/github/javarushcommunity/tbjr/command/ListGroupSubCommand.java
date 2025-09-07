package com.github.javarushcommunity.tbjr.command;

import static com.github.javarushcommunity.tbjr.command.CommandUtils.getChatId;

import com.github.javarushcommunity.tbjr.repository.entity.TelegramUser;
import com.github.javarushcommunity.tbjr.service.SendBotMessageService;
import com.github.javarushcommunity.tbjr.service.TelegramUserService;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ListGroupSubCommand implements Command {

  private final SendBotMessageService sendBotMessageService;
  private final TelegramUserService telegramUserService;

  public ListGroupSubCommand(SendBotMessageService sendBotMessageService,
      TelegramUserService telegramUserService) {
    this.sendBotMessageService = sendBotMessageService;
    this.telegramUserService = telegramUserService;
  }

  @Override
  public void execute(Update update) {
    //TODO add exception handling
    TelegramUser telegramUser = telegramUserService.findByChatId(getChatId(update))
        .orElseThrow();

    String message;
    if (CollectionUtils.isEmpty(telegramUser.getGroupSubs())) {
      message = "Пока нет подписок на группы. Чтобы добавить подписку напиши /addGroupSub";
    } else {
      String collectedGroups = telegramUser.getGroupSubs().stream()
          .map(it -> "Группа: " + it.getTitle() + " , ID = " + it.getId() + " \n")
          .collect(Collectors.joining());
      message = String.format("Я нашел все подписки на группы: \n\n%s", collectedGroups);
    }

    sendBotMessageService.sendMessage(telegramUser.getChatId(), message);
  }
}

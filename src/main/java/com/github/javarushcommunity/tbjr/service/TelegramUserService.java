package com.github.javarushcommunity.tbjr.service;

import com.github.javarushcommunity.tbjr.repository.entity.TelegramUser;
import java.util.List;
import java.util.Optional;

public interface TelegramUserService {

  void save(TelegramUser telegramUser);

  Optional<TelegramUser> findByChatId(Long chatId);

  List<TelegramUser> findAllInactiveUsers();

  List<TelegramUser> findAllActiveUsers();
}
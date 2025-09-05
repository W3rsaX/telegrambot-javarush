package com.github.javarushcommunity.tbjr.repository;

import com.github.javarushcommunity.tbjr.repository.entity.TelegramUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, String> {
  List<TelegramUser> findAllByActiveTrue();
}

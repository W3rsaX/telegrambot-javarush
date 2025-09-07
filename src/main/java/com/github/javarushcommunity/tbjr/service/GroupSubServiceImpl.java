package com.github.javarushcommunity.tbjr.service;

import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.tbjr.repository.GroupSubRepository;
import com.github.javarushcommunity.tbjr.repository.entity.GroupSub;
import com.github.javarushcommunity.tbjr.repository.entity.TelegramUser;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GroupSubServiceImpl implements GroupSubService {

  private final GroupSubRepository groupSubRepository;
  private final TelegramUserService telegramUserService;

  public GroupSubServiceImpl(GroupSubRepository groupSubRepository,
      TelegramUserService telegramUserService) {
    this.groupSubRepository = groupSubRepository;
    this.telegramUserService = telegramUserService;
  }

  @Override
  public GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo) {
    TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow();
    //TODO add exception handling
    GroupSub groupSub;
    Optional<GroupSub> groupSubFromDB = groupSubRepository.findById(groupDiscussionInfo.getId());
    if (groupSubFromDB.isPresent()) {
      groupSub = groupSubFromDB.get();
      Optional<TelegramUser> first = groupSub.getUsers().stream()
          .filter(it -> it.getChatId().equalsIgnoreCase(chatId))
          .findFirst();
      if (first.isEmpty()) {
        groupSub.addUser(telegramUser);
      }
    } else {
      groupSub = new GroupSub();
      groupSub.addUser(telegramUser);
      groupSub.setId(groupDiscussionInfo.getId());
      groupSub.setTitle(groupDiscussionInfo.getTitle());
    }
    return groupSubRepository.save(groupSub);
  }

  @Override
  public GroupSub save(GroupSub groupSub) {
    return groupSubRepository.save(groupSub);
  }

  @Override
  public Optional<GroupSub> findById(Integer id) {
    return groupSubRepository.findById(id);
  }

  @Override
  public List<GroupSub> findAll() {
    return groupSubRepository.findAll();
  }
}

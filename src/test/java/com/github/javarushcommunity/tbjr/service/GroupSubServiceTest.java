package com.github.javarushcommunity.tbjr.service;

import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.tbjr.repository.GroupSubRepository;
import com.github.javarushcommunity.tbjr.repository.entity.GroupSub;
import com.github.javarushcommunity.tbjr.repository.entity.TelegramUser;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for GroupSubService")
public class GroupSubServiceTest {
  private GroupSubService groupSubService;
  private GroupSubRepository groupSubRepository;
  private TelegramUser newUser;

  private final static String CHAT_ID = "1";

  @BeforeEach
  public void init() {
    TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
    groupSubRepository = Mockito.mock(GroupSubRepository.class);
    groupSubService = new GroupSubServiceImpl(groupSubRepository, telegramUserService);

    newUser = new TelegramUser();
    newUser.setActive(true);
    newUser.setChatId(CHAT_ID);

    Mockito.when(telegramUserService.findByChatId(CHAT_ID)).thenReturn(Optional.of(newUser));
  }

  @Test
  public void shouldProperlySaveGroup() {

    GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
    groupDiscussionInfo.setId(1);
    groupDiscussionInfo.setTitle("g1");

    GroupSub expectedGroupSub = new GroupSub();
    expectedGroupSub.setId(groupDiscussionInfo.getId());
    expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
    expectedGroupSub.addUser(newUser);

    groupSubService.save(CHAT_ID, groupDiscussionInfo);

    Mockito.verify(groupSubRepository).save(expectedGroupSub);
  }

  @Test
  public void shouldProperlyAddUserToExistingGroup() {
    TelegramUser oldTelegramUser = new TelegramUser();
    oldTelegramUser.setChatId("2");
    oldTelegramUser.setActive(true);

    GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
    groupDiscussionInfo.setId(1);
    groupDiscussionInfo.setTitle("g1");

    GroupSub groupFromDB = new GroupSub();
    groupFromDB.setId(groupDiscussionInfo.getId());
    groupFromDB.setTitle(groupDiscussionInfo.getTitle());
    groupFromDB.addUser(oldTelegramUser);

    Mockito.when(groupSubRepository.findById(groupDiscussionInfo.getId())).thenReturn(Optional.of(groupFromDB));

    GroupSub expectedGroupSub = new GroupSub();
    expectedGroupSub.setId(groupDiscussionInfo.getId());
    expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
    expectedGroupSub.addUser(oldTelegramUser);
    expectedGroupSub.addUser(newUser);

    groupSubService.save(CHAT_ID, groupDiscussionInfo);

    Mockito.verify(groupSubRepository).findById(groupDiscussionInfo.getId());
    Mockito.verify(groupSubRepository).save(expectedGroupSub);
  }
}

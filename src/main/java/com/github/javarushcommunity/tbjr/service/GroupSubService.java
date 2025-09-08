package com.github.javarushcommunity.tbjr.service;

import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.tbjr.repository.entity.GroupSub;
import java.util.List;
import java.util.Optional;

public interface GroupSubService {

  GroupSub save(Long chatId, GroupDiscussionInfo groupDiscussionInfo);

  Optional<GroupSub> findById(Integer integer);

  GroupSub  save(GroupSub groupSub);

  List<GroupSub> findAll();
}

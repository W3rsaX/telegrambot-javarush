package com.github.javarushcommunity.tbjr.service;

import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.tbjr.repository.entity.GroupSub;

public interface GroupSubService {

  GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
}

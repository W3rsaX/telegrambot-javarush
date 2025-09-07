package com.github.javarushcommunity.tbjr.javarushclient;

import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupInfo;
import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupRequestArgs;
import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupsCountRequestArgs;

import java.util.List;

public interface JavaRushGroupClient {

  List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);

  List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);

  Integer getGroupCount(GroupsCountRequestArgs countRequestArgs);

  GroupDiscussionInfo getGroupById(Integer id);

  Integer findLastPostId(Integer groupSub);
}

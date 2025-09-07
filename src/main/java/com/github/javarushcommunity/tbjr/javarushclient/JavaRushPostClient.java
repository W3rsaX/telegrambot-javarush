package com.github.javarushcommunity.tbjr.javarushclient;

import com.github.javarushcommunity.tbjr.javarushclient.dto.PostInfo;

import java.util.List;

public interface JavaRushPostClient {

  List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId);
}

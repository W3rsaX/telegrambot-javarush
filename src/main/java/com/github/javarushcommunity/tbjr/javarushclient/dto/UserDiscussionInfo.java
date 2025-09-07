package com.github.javarushcommunity.tbjr.javarushclient.dto;

import lombok.Data;

@Data
public class UserDiscussionInfo {

  private Boolean isBookmarked;
  private Long lastTime;
  private Integer newCommentsCount;
}

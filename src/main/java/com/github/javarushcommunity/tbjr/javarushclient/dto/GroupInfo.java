package com.github.javarushcommunity.tbjr.javarushclient.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GroupInfo {

  private Integer id;
  private String key;
  private String avatarUrl;
  private Long createTime;
  private String description;
  private Language language;
  private Integer levelToEditor;
  private MeGroupInfo meGroupInfo;
  private String pictureUrl;
  private String title;
  private GroupInfoType type;
  private Integer usersCount;
  private GroupVisibilityStatus visibilityStatus;
}

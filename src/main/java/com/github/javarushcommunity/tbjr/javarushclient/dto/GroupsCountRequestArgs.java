package com.github.javarushcommunity.tbjr.javarushclient.dto;

import static java.util.Objects.nonNull;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GroupsCountRequestArgs  {

  private final String query;
  private final GroupInfoType type;
  private final GroupFilter filter;

  public Map populateQueries() {
    Map queries = new HashMap<>();
    if (nonNull(query)) {
      queries.put("query", query);
    }
    if (nonNull(type)) {
      queries.put("type", type);
    }
    if (nonNull(filter)) {
      queries.put("filter", filter);
    }
    return queries;
  }
}

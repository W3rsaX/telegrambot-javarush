package com.github.javarushcommunity.tbjr.dto;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class StatisticDTO {
  private final int activeUserCount;
  private final int inactiveUserCount;
  private final List<GroupStatDTO> groupStatDTOs;
  private final double averageGroupCountByUser;
}

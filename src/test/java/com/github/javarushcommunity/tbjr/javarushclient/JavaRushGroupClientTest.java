package com.github.javarushcommunity.tbjr.javarushclient;

import static com.github.javarushcommunity.tbjr.javarushclient.dto.GroupInfoType.TECH;

import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupInfo;
import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupRequestArgs;
import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupsCountRequestArgs;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Integration-level testing for JavaRushGroupClientImplTest")
public class JavaRushGroupClientTest {

  public static final String JAVARUSH_API_PATH = "https://javarush.ru/api/1.0/rest";

  private final JavaRushGroupClient groupClient = new JavaRushGroupClientImpl(JAVARUSH_API_PATH);

  @Test
  public void shouldProperlyGetGroupsWithEmptyArgs() {
    GroupRequestArgs args = GroupRequestArgs.builder().build();

    List<GroupInfo> groupList = groupClient.getGroupList(args);

    Assertions.assertNotNull(groupList);
    Assertions.assertFalse(groupList.isEmpty());
  }

  @Test
  public void shouldProperlyGetWithOffSetAndLimit() {
    GroupRequestArgs args = GroupRequestArgs.builder()
        .offset(1)
        .limit(3)
        .build();

    List<GroupInfo> groupList = groupClient.getGroupList(args);

    Assertions.assertNotNull(groupList);
    Assertions.assertEquals(3, groupList.size());
  }

  @Test
  public void shouldProperlyGetGroupsDiscWithEmptyArgs() {
    GroupRequestArgs args = GroupRequestArgs.builder().build();

    List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(args);

    Assertions.assertNotNull(groupList);
    Assertions.assertFalse(groupList.isEmpty());
  }

  @Test
  public void shouldProperlyGetGroupDiscWithOffSetAndLimit() {
    GroupRequestArgs args = GroupRequestArgs.builder()
        .offset(1)
        .limit(3)
        .build();

    List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(args);

    Assertions.assertNotNull(groupList);
    Assertions.assertEquals(3, groupList.size());
  }

  @Test
  public void shouldProperlyGetGroupCount() {
    GroupsCountRequestArgs args = GroupsCountRequestArgs.builder().build();

    Integer groupCount = groupClient.getGroupCount(args);

    Assertions.assertEquals(20, groupCount);
  }

  @Test
  public void shouldProperlyGetGroupTECHCount() {
    GroupsCountRequestArgs args = GroupsCountRequestArgs.builder()
        .type(TECH)
        .build();

    Integer groupCount = groupClient.getGroupCount(args);

    Assertions.assertEquals(7, groupCount);
  }

  @Test
  public void shouldProperlyGetGroupById() {
    Integer androidGroupId = 16;

    GroupDiscussionInfo groupById = groupClient.getGroupById(androidGroupId);

    Assertions.assertNotNull(groupById);
    Assertions.assertEquals(16, groupById.getId());
    Assertions.assertEquals(TECH, groupById.getType());
    Assertions.assertEquals("android", groupById.getKey());
  }
}

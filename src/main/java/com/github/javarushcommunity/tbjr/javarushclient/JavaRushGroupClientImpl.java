package com.github.javarushcommunity.tbjr.javarushclient;

import static org.springframework.util.CollectionUtils.isEmpty;

import com.github.javarushcommunity.tbjr.javarushclient.dto.*;
import java.util.List;
import java.util.Optional;
import kong.unirest.core.GenericType;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JavaRushGroupClientImpl implements JavaRushGroupClient {

  private final String javarushApiGroupPath;
  private final String getJavarushApiPostPath;

  public JavaRushGroupClientImpl(@Value("${javarush.api.path}") String ApiPath) {
    this.javarushApiGroupPath = ApiPath + "/groups";
    this.getJavarushApiPostPath = ApiPath + "/posts";
  }

  @Override
  public List<GroupInfo> getGroupList(GroupRequestArgs requestArgs) {
    return Unirest.get(javarushApiGroupPath)
        .queryString(requestArgs.populateQueries())
        .asObject(new GenericType<List<GroupInfo>>() {
        })
        .getBody();
  }

  @Override
  public List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs) {
    return Unirest.get(javarushApiGroupPath)
        .queryString(requestArgs.populateQueries())
        .asObject(new GenericType<List<GroupDiscussionInfo>>() {
        })
        .getBody();
  }

  @Override
  public Integer getGroupCount(GroupsCountRequestArgs countRequestArgs) {
    return Integer.valueOf(
        Unirest.get(String.format("%s/count", javarushApiGroupPath))
            .queryString(countRequestArgs.populateQueries())
            .asString()
            .getBody()
    );
  }

  @Override
  public GroupDiscussionInfo getGroupById(Integer id) {
    return Unirest.get(String.format("%s/group%s", javarushApiGroupPath, id.toString()))
        .asObject(GroupDiscussionInfo.class)
        .getBody();
  }

  @Override
  public Integer findLastPostId(Integer groupSubId) {
    List<PostInfo> posts = Unirest.get(getJavarushApiPostPath)
        .queryString("order", "NEW")
        .queryString("groupKid", groupSubId.toString())
        .queryString("limit", "1")
        .asObject(new GenericType<List<PostInfo>>() {
        })
        .getBody();
    return isEmpty(posts) ? 0 : Optional.ofNullable(posts.get(0)).map(PostInfo::getId).orElse(0);
  }
}

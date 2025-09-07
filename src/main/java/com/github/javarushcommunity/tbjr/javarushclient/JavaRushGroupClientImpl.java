package com.github.javarushcommunity.tbjr.javarushclient;

import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupInfo;
import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupRequestArgs;
import com.github.javarushcommunity.tbjr.javarushclient.dto.GroupsCountRequestArgs;
import java.util.List;
import kong.unirest.core.GenericType;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JavaRushGroupClientImpl implements JavaRushGroupClient {

  private final String javarushApiGroupPath;

  public JavaRushGroupClientImpl(@Value("${javarush.api.path}") String ApiPath) {
    this.javarushApiGroupPath = ApiPath + "/groups";
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
}

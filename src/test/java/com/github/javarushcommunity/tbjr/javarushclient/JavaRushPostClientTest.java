package com.github.javarushcommunity.tbjr.javarushclient;

import com.github.javarushcommunity.tbjr.javarushclient.dto.PostInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.javarushcommunity.tbjr.javarushclient.JavaRushGroupClientTest.JAVARUSH_API_PATH;

@DisplayName("Integration-level testing for JavaRushPostClient")
public class JavaRushPostClientTest {

  private final JavaRushPostClient postClient = new JavaRushPostClientImpl(JAVARUSH_API_PATH);

  @Test
  public void shouldProperlyGetNew15Posts() {
    List<PostInfo> newPosts = postClient.findNewPosts(30, 2935);

    Assertions.assertEquals(15, newPosts.size());
  }
}

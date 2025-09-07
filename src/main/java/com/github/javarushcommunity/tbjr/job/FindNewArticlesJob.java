package com.github.javarushcommunity.tbjr.job;

import com.github.javarushcommunity.tbjr.service.FindNewPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class FindNewArticlesJob {

  private final FindNewPostService findNewArticleService;

  @Autowired
  public FindNewArticlesJob(FindNewPostService findNewArticleService) {
    this.findNewArticleService = findNewArticleService;
  }

  @Scheduled(fixedRateString = "${bot.recountNewArticleFixedRate}")
  public void findNewArticles() {
    LocalDateTime start = LocalDateTime.now();

    log.info("Find new article job started.");

    findNewArticleService.findNewPosts();

    LocalDateTime end = LocalDateTime.now();

    log.info("Find new articles job finished. Took seconds: {}",
        end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
  }
}

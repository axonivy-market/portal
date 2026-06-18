package com.axonivy.portal.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.dto.News;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestNewsService {

  NewsService service = NewsService.getInstance();

  @AfterEach
  void tearDown() {
    service.findAll().forEach(n -> service.deleteNewsById(n.getId()));
  }
  
  @Test
  void insert() {
    assertThat(service.findAll())
      .isEmpty();

    var testNews = new News("icon", "junit.rocks", 
      """
      Focused CI tests are the key 🔑️ to success:
      - Fast feedback loop
      - Easy to maintain
      - Enables TDD
      - Encourages good design
      - Provides confidence in code changes
      - Reduces bugs in production
      - Improves developer productivity
      - Increases code quality
      - Facilitates collaboration among team members
      - ... and much more!
      """, java.util.Locale.ENGLISH);
    service.saveOrUpdate(List.of(testNews));

    assertThat(service.findAll())
      .extracting(News::getName)
      .containsExactly("junit.rocks");
  }

}

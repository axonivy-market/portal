package com.axonivy.portal.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.dto.News;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestNewsService {

  NewsService service = NewsService.getInstance();

  @AfterEach
  void tearDown() {
    service.findAll().forEach(n -> service.deleteNewsById(n.getId()));
  }
  
  @Test
  void insert(){
    assertThat(service.findAll())
      .isEmpty();

    var testNews = new News("icon", "junit.rocks", 
      """
      Focused CI test are the key 🔑️ to success:
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
      """, null);
    service.saveOrUpdate(List.of(testNews));

    assertThat(service.findAll())
      .extracting(News::getName)
      .containsExactly("junit.rocks");
  }

  @Test
  void updateNews() {
    var news = new News("newicon", "Joe", "Hunter", null);
    service.saveOrUpdate(List.of(news));
  
    var updatedNews = service.findAll().getFirst();
    updatedNews.setIcon(null);
    updatedNews.setDescription("Jill");
    service.saveOrUpdate(List.of(updatedNews));

    var foundNews = service.findAll().getFirst();;
    assertThat(foundNews.getIcon()).isEqualTo(News.DEFAULT_NEWS_ICON);
    assertThat(foundNews.getDescription()).isEqualTo("Jill");
  }

  @Test
  void findNewsById_withUnknownId() {
    assertThat(service.findNewsById("no-id")).isNull();
  }
  
  @Test
  void deleteNewsById_withUnknownId() {
    service.deleteNewsById("no-id");
    assertThat(service.findAll()).isEmpty();
  }

  @Test
  void findNewsById_withExistingId() {
    var testNews = new News("icon", "Donald Trump", "Melania Trump", null);
    service.saveOrUpdate(List.of(testNews));

    var foundNews = service.findAll().getFirst();
    assertThat(foundNews).isNotNull();
    assertThat(foundNews.getName()).isEqualTo("Donald Trump");
  }

  @Test
  void findAll_shouldReturnNewsList_sortedByCreatedDate_descending() {
    List<News> newsList = new ArrayList<>();
    var pastNews = new News("icon", "junit.rocks",
        """
            Past news description
            """, null);
    Instant pastInstant = Instant.parse("2026-05-16T00:00:00Z");
    pastNews.setCreatedDate(Date.from(pastInstant));
    newsList.add(pastNews);
 
    Instant currentInstant = Instant.parse("2026-06-16T00:00:00Z");
    var currentNews = new News("icon", "junit.rocks",
        """
        News description
            """, null);
    currentNews.setCreatedDate(Date.from(currentInstant));
    newsList.add(currentNews);
    service.saveOrUpdate(newsList);
 
    assertThat(service.findAll())
        .first()
        .extracting(News::getCreatedDate)
        .isEqualTo(Date.from(currentInstant));
  }

  @Test
  void insert_withContentObject_multilanguageContent() {
    var enNews = new News("icon", "Hello", "English description", Locale.ENGLISH);
    var deNews = new News(null, "Hallo", "Deutsche Beschreibung", Locale.GERMAN);
    deNews.setId(enNews.getId());
    service.saveOrUpdate(List.of(enNews, deNews));

    var newsId = service.findAll().getFirst().getId();
    var contentObject = ContentManagement.cms(IApplication.current())
        .root().child().get(NewsService.PORTAL_NEWS_CMS_ROOT).get()
        .child().get(newsId).get();

    var newsEN = new News(contentObject, Locale.ENGLISH);
    var newsDE = new News(contentObject, Locale.GERMAN);

    assertThat(newsEN.getName()).isEqualTo("Hello");
    assertThat(newsEN.getDescription()).isEqualTo("English description");
    assertThat(newsDE.getName()).isEqualTo("Hallo");
    assertThat(newsDE.getDescription()).isEqualTo("Deutsche Beschreibung");
  }
}

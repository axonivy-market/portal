package com.axonivy.portal.service;

import static com.axonivy.portal.enums.NewsColumn.CREATED_DATE;
import static com.axonivy.portal.enums.NewsColumn.DESCRIPTION;
import static com.axonivy.portal.enums.NewsColumn.ICON;
import static com.axonivy.portal.enums.NewsColumn.NAME;
import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.axonivy.portal.dto.News;
import com.axonivy.portal.enums.NewsColumn;
import com.axonivy.portal.util.NewsUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.cm.ContentManagementSystem;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.ContentObjectChildAccessor;
import ch.ivyteam.ivy.cm.ContentObjectValue;
import ch.ivyteam.ivy.cm.exec.ContentManagement;

public class NewsService {

  public static final String NEWS_FEED = "NewsFeed";
  public static final String PORTAL_NEWS_CMS_ROOT = "Portal" + NEWS_FEED;

  private static NewsService instance;

  public static NewsService getInstance() {
    if (instance == null) {
      instance = new NewsService();
    }
    return instance;
  }

  public List<News> findAll() {
    return findAllWithoutSort().stream().filter(Objects::nonNull).sorted(compareCreatedDate())
        .collect(Collectors.toList());
  }

  public List<News> findAllWithoutSort() {
    List<ContentObject> contentObjects = getPortalNewsRoot().children();
    return CollectionUtils.emptyIfNull(contentObjects).stream().map(newsObject -> new News(newsObject))
        .collect(Collectors.toList());
  }

  public News findNewsById(String newsId) {
    Optional<ContentObject> foundNews = getPortalNewsRoot().child().get(newsId);
    return foundNews.isPresent() ? new News(foundNews.get()) : null;
  }

  public List<News> findNewsBySupportedLocale(String newsId, List<Locale> locales) {
    var newsList = new ArrayList<News>();
    Optional<ContentObject> foundNews = getPortalNewsRoot().child().get(newsId);
    if (foundNews.isPresent()) {
      CollectionUtils.emptyIfNull(locales).forEach(locale -> {
        var news = new News(foundNews.get(), locale);
        newsList.add(news);
      });
    }
    return newsList;
  }

  public void deleteNewsById(String newsId) {
    if (getPortalNewsRoot().child().exists(newsId)) {
      getPortalNewsRoot().child().get(newsId).get().delete();
    }
  }

  public void saveOrUpdate(List<News> newsList) {
    if (CollectionUtils.isEmpty(newsList)) {
      return;
    }
    ContentObject savedNewsFeed = getOrCreateNewsContentObject(newsList.get(0));
    for (var news : newsList) {
      if (StringUtils.isBlank(news.getName())) {
        continue;
      }
      if (news.getCreatedDate() == null) {
        news.setCreatedDate(new Date());
      }
      var locale = news.getLocale();
      ContentObjectChildAccessor newsByLocale = savedNewsFeed.child();
      writeByKey(newsByLocale, ICON, news.getIcon(), locale);
      writeByKey(newsByLocale, NAME, news.getName(), locale);
      writeByKey(newsByLocale, DESCRIPTION, news.getDescription(), locale);
      writeByKey(newsByLocale, CREATED_DATE, NewsUtils.formatDate(news.getCreatedDate()), locale);
    }
  }

  private Comparator<? super News> compareCreatedDate() {
    return (n1, n2) -> {
      if (isNull(n1) || isNull(n2)) {
        return -1;
      }
      if (isNull(n1.getCreatedDate())) {
        return 1;
      }
      if (isNull(n2.getCreatedDate())) {
        return -1;
      }
      return n2.getCreatedDate().compareTo(n1.getCreatedDate());
    };
  }

  private ContentObject getOrCreateNewsContentObject(News news) {
    String newsId = isNull(news) ? "" : news.getId();
    var foundNews = getPortalNewsRoot().child().get(newsId);
    if (foundNews.isEmpty()) {
      return getPortalNewsRoot().child().string(generateNewsId());
    }
    return foundNews.get();
  }

  private void writeByKey(ContentObjectChildAccessor cmsNode, NewsColumn column, Object value, Locale locale) {
    ContentObjectValue cmsValue = cmsNode.string(column.getKey()).value().get(locale);
    if (value == null || StringUtils.isEmpty(value.toString())) {
      cmsValue.delete();
    } else {
      cmsValue.write().string(StringUtils.strip(value.toString()));
    }
  }

  private String generateNewsId() {
    return NEWS_FEED.concat((new News()).getId());
  }

  private ContentObject getPortalNewsRoot() {
    ContentManagementSystem cms = ContentManagement.cms(IApplication.current());
    Optional<ContentObject> newsRoot = cms.root().child().get(PORTAL_NEWS_CMS_ROOT);
    if (newsRoot.isEmpty()) {
      return cms.root().child().string(PORTAL_NEWS_CMS_ROOT);
    }
    return newsRoot.get();
  }
}

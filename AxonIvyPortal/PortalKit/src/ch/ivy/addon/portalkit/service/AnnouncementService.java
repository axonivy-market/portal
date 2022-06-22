package ch.ivy.addon.portalkit.service;


import static ch.ivy.addon.portalkit.constant.IvyCacheIdentifier.PORTAL_ANNOUNCEMENT_CACHE_GROUP_NAME;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.configuration.Announcement;
import ch.ivy.addon.portalkit.configuration.LocalizationContent;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

public class AnnouncementService {
  private static final String ANNOUNCEMENT = PortalVariable.ANNOUNCEMENT.key;
  private static final String ANNOUNCEMENT_CACHE_ENTRY = "ANNOUNCEMENT";
  private static AnnouncementService instance;

  private AnnouncementService() {}

  public static final AnnouncementService getInstance() {
    if (instance == null) {
      instance = new AnnouncementService();
    }
    return instance;
  }

  public boolean isDefaultApplicationLanguage(String language) {
    return IvyExecutor.executeAsSystem(
        () -> IApplication.current().getDefaultEMailLanguage().getLanguage().equalsIgnoreCase(language));
  }

  public String getAnnouncementMessage() {
    List<LocalizationContent> contents = getAnnouncement().getContents();
    if (contents == null) {
      return EMPTY;
    }

    String language;
    Locale locale = Ivy.session().getSessionUser().getLanguage();
    if (locale != null) {
      language = locale.toLanguageTag();
    } else {
      language = getDefaultEmailLanguage();
    }

    Optional<LocalizationContent> displayNameOptional =
        contents.stream().filter(content -> language.equalsIgnoreCase(content.getLanguage())).findFirst();
    if (displayNameOptional.isEmpty() || StringUtils.isBlank(displayNameOptional.get().getValue())) {
      displayNameOptional = contents.stream()
          .filter(content -> getDefaultEmailLanguage().equalsIgnoreCase(content.getLanguage())).findFirst();
    }
    if (displayNameOptional.isEmpty()) {
      return EMPTY;
    }
    return displayNameOptional.get().getValue();
  }

  public void deactivateAnnouncement() {
    Announcement announcement = getPublicConfig();
    announcement.setEnabled(false);
    save(announcement);
  }

  public Announcement save(Announcement entity) {
    Ivy.var().set(getConfigKey(), BusinessEntityConverter.entityToJsonValue(entity));
    invalidateCache();
    return entity;
  }

  public boolean isAnnouncementActivated() {
    Announcement announcement = getAnnouncement();
    return announcement.isEnabled();
  }

  public Announcement getAnnouncementWithAllSupportedLanguage() {
    Announcement announcement = getAnnouncement();
    Map<String, LocalizationContent> languageToAnnouncements;
    List<LocalizationContent> contents = announcement.getContents();
    if (contents != null) {
      languageToAnnouncements =
          contents.stream().collect(Collectors.toMap(a -> a.getLanguage().toLowerCase(), content -> content));
    } else {
      languageToAnnouncements = new HashMap<>();
    }

    IvyLanguageResultDTO ivyLanguage = LanguageService.newInstance().findUserLanguages();

    List<String> supportedLanguages = ivyLanguage.getIvyLanguage().getSupportedLanguages();

    List<LocalizationContent> contentsWithAllSupportedLanguages = supportedLanguages.stream().map(language -> {
      if (languageToAnnouncements.containsKey(language.toLowerCase())) {
        return languageToAnnouncements.get(language);
      } else {
        return new LocalizationContent(language, null);
      }
    }).sorted((content1, content2) -> content1.getLanguage().compareToIgnoreCase(content2.getLanguage()))
        .collect(Collectors.toList());
    announcement.setContents(contentsWithAllSupportedLanguages);
    return announcement;
  }


  private String getDefaultEmailLanguage() {
    return IApplication.current().getDefaultEMailLanguage().toLanguageTag();
  }

  private Announcement getAnnouncement() {
    Announcement announcement = getAnnouncementFromCache();
    if (announcement == null) {
      announcement = getPublicConfig();
      cacheAnnouncement(announcement);
    }
    return announcement;
  }

  private Announcement getPublicConfig() {
    String value = Ivy.var().get(getConfigKey());
    Announcement announcement = BusinessEntityConverter.jsonValueToEntity(value, Announcement.class);
    return announcement;
  }

  private void cacheAnnouncement(Announcement announcement) {
    IvyCacheService.newInstance().setApplicationCache(PORTAL_ANNOUNCEMENT_CACHE_GROUP_NAME, ANNOUNCEMENT_CACHE_ENTRY,
        announcement);
  }

  private Announcement getAnnouncementFromCache() {
    return (Announcement) IvyCacheService.newInstance().getApplicationCache(PORTAL_ANNOUNCEMENT_CACHE_GROUP_NAME,
        ANNOUNCEMENT_CACHE_ENTRY);
  }

  private void invalidateCache() {
    IvyCacheService.newInstance()
        .invalidateApplicationCacheForAllAvailableApplications(PORTAL_ANNOUNCEMENT_CACHE_GROUP_NAME);
  }

  private String getConfigKey() {
    return ANNOUNCEMENT;
  }
}

package ch.ivy.addon.portalkit.service;


import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.Announcement;
import ch.ivy.addon.portalkit.bo.AnnouncementStatus;
import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

public class AnnouncementService extends BusinessDataService<Announcement> {
  private static final String ANNOUNCEMENT_ACTIVATED = "ANNOUNCEMENT_ACTIVATED";
  private static AnnouncementService instance;

  private AnnouncementService() {}

  public static final AnnouncementService getInstance() {
    if (instance == null) {
      instance = new AnnouncementService();
    }
    return instance;
  }

  public List<Announcement> findAllOrderedByLanguage() {
    List<Announcement> result = repo().search(getType()).orderBy().textField("language").ascending().execute().getAll();
    return result;
  }

  public void saveAll(List<Announcement> announcements) {
    for (Announcement announcement : announcements) {
      save(announcement);
    }
  }

  public List<Announcement> getAnnouncements() {
    List<Announcement> announcements = findAllOrderedByLanguage();
    Map<String, List<Announcement>> languageToAnnouncements =
        announcements.stream().collect(Collectors.groupingBy(Announcement::getLanguage));
    Stream<String> supportedLanguageStream =
        ServerService.getInstance().getApplicationsRelatedToPortal().stream().map(IApplication::getName)
            .flatMap(appName -> LanguageService.newInstance().getSupportedLanguages(appName).getIvyLanguages().stream())
            .flatMap(language -> language.getSupportedLanguages().stream()).distinct().map(String::toUpperCase);
    return IvyExecutor.executeAsSystem(() -> supportedLanguageStream.map(language -> {
      if (languageToAnnouncements.containsKey(language)) {
        return languageToAnnouncements.get(language).get(0);
      } else {
        return new Announcement(language, null);
      }
    }).collect(Collectors.toList()));
  }

  public boolean isDefaultApplicationLanguage(String language) {
    List<IApplication> apps = ServerService.getInstance().getApplicationsRelatedToPortal();
    return IvyExecutor.executeAsSystem(
        () -> apps.stream().anyMatch(app -> app.getDefaultEMailLanguage().getLanguage().equalsIgnoreCase(language)));
  }

  public String getAnnouncement() {
    String language;
    Locale locale = Ivy.session().getSessionUser().getEMailLanguage();
    if (locale != null) {
      language = locale.getLanguage();
    } else {
      language = getDefaultEmailLanguage();
    }
    Announcement announcement =
        repo().search(getType()).textField("language").isEqualToIgnoringCase(language).execute().getFirst();
    if (announcement == null || StringUtils.isBlank(announcement.getValue())) {
      announcement = repo().search(getType()).textField("language").isEqualToIgnoringCase(getDefaultEmailLanguage())
          .execute().getFirst();
    }
    if (announcement == null) {
      return "";
    }
    return announcement.getValue();
  }

  private String getDefaultEmailLanguage() {
    return Ivy.wf().getApplication().getDefaultEMailLanguage().getLanguage();
  }

  public void activateAnnouncement() {
    AnnouncementStatusService.getInstance().updateFirstProperty(Boolean.toString(true));
    makeSureAnnouncementStatusUpToDate("true");
  }
  
  public void deactivateAnnouncement() {
    AnnouncementStatusService.getInstance().updateFirstProperty(Boolean.toString(false));
    makeSureAnnouncementStatusUpToDate("false");
  }

  private void makeSureAnnouncementStatusUpToDate(String expectedValue) {
    for (int i = 0; i < 100; i++) {
      AnnouncementStatus status = AnnouncementStatusService.getInstance().findFirst();
      if (status == null || !status.getEnabled().equalsIgnoreCase(expectedValue)) {
        try {
          Thread.sleep(20);
          Ivy.log().warn("loop {0}", i);
        } catch (InterruptedException e) {
          Ivy.log().error("Error when enabling announcement", e);
        }
      } else {
        return;
      }
    }
    Ivy.log().error("Announcement status is not up to date");
  }

  public boolean isAnnouncementActivated() {
    Boolean announcementActivated =
        (Boolean) IvyCacheService.newInstance().getAnnouncementSettingsFromCache(ANNOUNCEMENT_ACTIVATED);
    if (announcementActivated == null) {
      AnnouncementStatus property = AnnouncementStatusService.getInstance().findFirst();
      announcementActivated = false;
      if (property != null) {
        announcementActivated = Boolean.parseBoolean(property.getEnabled());
      }
      IvyCacheService.newInstance().cacheAnnouncementSettings(ANNOUNCEMENT_ACTIVATED, announcementActivated);
      Ivy.log().warn("cache {0}",announcementActivated);
    }
    Ivy.log().warn("get cache {0}",announcementActivated);
    return announcementActivated;
  }

  public void invalidateCache() {
    IvyCacheService.newInstance()
        .invalidateCacheGroupOfAllPortalApps(IvyCacheIdentifier.PORTAL_ANNOUNCEMENT_CACHE_GROUP_NAME);
  }

  @Override
  public Class<Announcement> getType() {
    return Announcement.class;
  }
}

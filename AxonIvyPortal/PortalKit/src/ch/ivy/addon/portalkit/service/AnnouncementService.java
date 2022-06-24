package ch.ivy.addon.portalkit.service;


import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.Announcement;
import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

public class AnnouncementService extends BusinessDataService<Announcement> {
  private static final String FIELD_LANGUAGE = "language";
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
    return repo().search(getType()).orderBy().textField(FIELD_LANGUAGE).ascending().execute().getAll();
  }

  public void saveAll(List<Announcement> announcements) {
    List<Announcement> currentAnnouncementsInSystem = findAllOrderedByLanguage();
    cleanUpBeforeSave(currentAnnouncementsInSystem.stream().filter(announcement ->!announcements.contains(announcement)).collect(Collectors.toList()));
    for (Announcement announcement : announcements) {
      Announcement announcementUpdate = new Announcement(announcement.getLanguage(), announcement.getValue());
      save(announcementUpdate);
    }
  }

  private void cleanUpBeforeSave(List<Announcement> announcements) {
    announcements.forEach(announcement -> delete(announcement.getId()));
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
      language = locale.toLanguageTag();
    } else {
      language = getDefaultEmailLanguage();
    }
    Announcement announcement =
        repo().search(getType()).textField(FIELD_LANGUAGE).isEqualToIgnoringCase(language).execute().getFirst();
    if (announcement == null || StringUtils.isBlank(announcement.getValue())) {
      announcement = repo().search(getType()).textField(FIELD_LANGUAGE).isEqualToIgnoringCase(getDefaultEmailLanguage())
          .execute().getFirst();
    }
    if (announcement == null) {
      return "";
    }
    return announcement.getValue();
  }

  private String getDefaultEmailLanguage() {
    return IApplication.current().getDefaultEMailLanguage().toLanguageTag();
  }

  public void activateAnnouncement() throws InterruptedException {
    updateAnnouncementStatusByExpectedValue(true);
  }

  public void deactivateAnnouncement() throws InterruptedException {
    updateAnnouncementStatusByExpectedValue(false);
  }

  private void updateAnnouncementStatusByExpectedValue(boolean expectedValue) throws InterruptedException {
    AnnouncementStatusService.getInstance().updateAnnouncementStatus(expectedValue);

    // Check AnnouncementStatus is updated on ES
    boolean isStatusUpToDate = false;
    for (int i = 0; i < 100; i++) {
      if (expectedValue) {
        if (AnnouncementStatusService.getInstance().getAnnouncementStatus()) {
          isStatusUpToDate = true;
          break;
        }
      } else {
        AnnouncementStatusService.getInstance().removeAnnouncementStatus();
        if (!AnnouncementStatusService.getInstance().getAnnouncementStatus()) {
          isStatusUpToDate = true;
          break;
        }
      }
      Thread.sleep(20);
    }

    if (!isStatusUpToDate) {
      Ivy.log().error("Announcement status is not up to date");
    }
    invalidateCache();
    updateStatusToApplicationCache(expectedValue);
  }

  public boolean isAnnouncementActivated() {
    Boolean announcementActivated = (Boolean) IvyCacheService.newInstance().getApplicationCache(IvyCacheIdentifier.PORTAL_ANNOUNCEMENT_CACHE_GROUP_NAME, ANNOUNCEMENT_ACTIVATED);
    if (announcementActivated == null) {
      announcementActivated = AnnouncementStatusService.getInstance().getAnnouncementStatus();
      updateStatusToApplicationCache(announcementActivated);
    }
    return announcementActivated;
  }

  private void updateStatusToApplicationCache(Boolean status) {
    IvyCacheService.newInstance().setApplicationCache(IvyCacheIdentifier.PORTAL_ANNOUNCEMENT_CACHE_GROUP_NAME, ANNOUNCEMENT_ACTIVATED, status);
  }

  public void invalidateCache() {
    IvyCacheService.newInstance().invalidateCacheGroupOfAllPortalApps(IvyCacheIdentifier.PORTAL_ANNOUNCEMENT_CACHE_GROUP_NAME);
  }

  @Override
  public Class<Announcement> getType() {
    return Announcement.class;
  }
}

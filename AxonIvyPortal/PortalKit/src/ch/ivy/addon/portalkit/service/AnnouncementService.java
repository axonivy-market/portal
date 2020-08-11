package ch.ivy.addon.portalkit.service;


import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.Announcement;
import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
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
    cleanUpBeforeSave(announcements);
    for (Announcement announcement : announcements) {
      Announcement announcementUpdate = new Announcement(announcement.getLanguage(), announcement.getValue());
      save(announcementUpdate);
    }
  }

  private void cleanUpBeforeSave(List<Announcement> announcements) {
    announcements.forEach(announcement -> delete(announcement.getId()));
  }

  public List<Announcement> getAnnouncements(){
    List<Announcement> announcements = findAllOrderedByLanguage();
    Map<String, List<Announcement>> languageToAnnouncements =
        announcements.stream().collect(Collectors.groupingBy(Announcement::getLanguage));
    
    
    IvyLanguageResultDTO ivyLanguage = LanguageService.newInstance().findUserLanguages();
    List<String> supportedLanguages =  ivyLanguage.getIvyLanguage().getSupportedLanguages(); 
    
    return IvyExecutor.executeAsSystem(() -> supportedLanguages.stream().map(language -> {
      if (languageToAnnouncements.containsKey(language)) {
        return languageToAnnouncements.get(language).get(0);
      } else {
        return new Announcement(language, null);
      }
    }).collect(Collectors.toList()));
  }

  public boolean isDefaultApplicationLanguage(String language) {
    return IvyExecutor.executeAsSystem(
        () -> Ivy.wf().getApplication().getDefaultEMailLanguage().getLanguage().equalsIgnoreCase(language));
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
    return Ivy.wf().getApplication().getDefaultEMailLanguage().getLanguage();
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
    IvyCacheService.newInstance().cacheAnnouncementSettings(ANNOUNCEMENT_ACTIVATED, expectedValue);
  }

  public boolean isAnnouncementActivated() {
    Boolean announcementActivated =
        (Boolean) IvyCacheService.newInstance().getAnnouncementSettingsFromCache(ANNOUNCEMENT_ACTIVATED);
    if (announcementActivated == null) {
      announcementActivated = AnnouncementStatusService.getInstance().getAnnouncementStatus();
      IvyCacheService.newInstance().cacheAnnouncementSettings(ANNOUNCEMENT_ACTIVATED, announcementActivated);
    }
    return announcementActivated;
  }

  public void invalidateCache() {
    IvyCacheService.newInstance()
        .invalidateApplicationCacheByGroupName(IvyCacheIdentifier.PORTAL_ANNOUNCEMENT_CACHE_GROUP_NAME);
  }

  @Override
  public Class<Announcement> getType() {
    return Announcement.class;
  }
}

package ch.ivy.addon.portalkit.service;


import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.bo.Announcement;
import ch.ivy.addon.portalkit.bo.PortalProperty;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;

public class AnnouncementService extends BusinessDataService<Announcement> {
  private static final String ANNOUNCEMENT_ACTIVATED = "ANNOUNCEMENT_ACTIVATED";
  private static AnnouncementService INSTANCE;

  private AnnouncementService() {}

  public static final AnnouncementService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new AnnouncementService();
    }
    return INSTANCE;
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
    Stream<String> supportedLanguageStream = getApplicationsRelatedToPortal().stream().map(IApplication::getName)
        .flatMap(appName -> LanguageService.newInstance().getSupportedLanguages(appName).getIvyLanguages().stream())
        .flatMap(language -> language.getSupportedLanguages().stream());
    return supportedLanguageStream.distinct().map(String::toUpperCase).map(language -> {
      if (languageToAnnouncements.containsKey(language)) {
        return languageToAnnouncements.get(language).get(0);
      } else {
        return new Announcement(language, null);
      }
    }).collect(Collectors.toList());
  }

  public boolean isDefaultApplicationLanguage(String language) {
    List<IApplication> apps = getApplicationsRelatedToPortal();
    return apps.stream().anyMatch(app -> app.getDefaultEMailLanguage().getLanguage().equalsIgnoreCase(language));
  }

  private List<IApplication> getApplicationsRelatedToPortal() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    List<String> configuredApps =
        service.findAllIvyApplications().stream().map(Application::getName).collect(Collectors.toList());
    List<IApplication> apps;
    if (CollectionUtils.isEmpty(configuredApps)) {
      apps = IvyExecutor.executeAsSystem(
          () -> ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false));
    } else {
      apps = IvyExecutor.executeAsSystem(() -> ServiceUtilities.findApps(configuredApps));
    }
    return apps;
  }

  public String getAnnouncement() {
    Locale locale = Ivy.session().getSessionUser().getEMailLanguage();
    if (locale == null) {
      locale = Ivy.wf().getApplication().getDefaultEMailLanguage();
    }
    Announcement announcement =
        repo().search(getType()).textField("language").isEqualToIgnoringCase(locale.getLanguage()).execute().getFirst();
    if (announcement == null) {
      return null;
    }
    return announcement.getValue();
  }

  public void activateAnnouncement() {
    PortalPropertyService.getInstance().updateFirstPropertyByKey(ANNOUNCEMENT_ACTIVATED, Boolean.toString(true));
  }

  public void deactivateAnnouncement() {
    PortalPropertyService.getInstance().updateFirstPropertyByKey(ANNOUNCEMENT_ACTIVATED, Boolean.toString(false));
  }

  public boolean isAnnouncementActivated() {
    PortalProperty property = PortalPropertyService.getInstance().findFirstByKey(ANNOUNCEMENT_ACTIVATED);
    if (property == null) {
      return false;
    }
    return Boolean.parseBoolean(property.getValue());
  }

  @Override
  public Class<Announcement> getType() {
    return Announcement.class;
  }
}

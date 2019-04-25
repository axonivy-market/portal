package ch.ivy.addon.portalkit.service;


import ch.ivy.addon.portalkit.bo.AnnouncementStatus;
import ch.ivyteam.ivy.environment.Ivy;

public class AnnouncementStatusService extends BusinessDataService<AnnouncementStatus> {
  private static AnnouncementStatusService instance;

  private AnnouncementStatusService() {}

  public static final AnnouncementStatusService getInstance() {
    if (instance == null) {
      instance = new AnnouncementStatusService();
    }
    return instance;
  }

  public void updateFirstProperty(String value) {
    AnnouncementStatus property = findFirst();
    if (property == null) {
      property = new AnnouncementStatus(value);
    } else {
      property.setEnabled(value);
    }
    save(property);
  }

  public AnnouncementStatus findFirst() {
    AnnouncementStatus first = repo().search(getType()).limit(1).execute().getFirst();
    Ivy.log().warn("findFirst {0}", first);
    if (first != null) {
      Ivy.log().warn("findFirst {0}", first.getId());
    }
    return first;
  }

  @Override
  public Class<AnnouncementStatus> getType() {
    return AnnouncementStatus.class;
  }
}

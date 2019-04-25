package ch.ivy.addon.portalkit.service;


import ch.ivy.addon.portalkit.bo.AnnouncementStatus;

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
    return repo().search(getType()).limit(1).execute().getFirst();
  }

  @Override
  public Class<AnnouncementStatus> getType() {
    return AnnouncementStatus.class;
  }
}

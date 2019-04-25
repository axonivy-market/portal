package ch.ivy.addon.portalkit.service;


import ch.ivy.addon.portalkit.bo.AnnouncementStatus;

public class AnnouncementStatusService extends BusinessDataService<AnnouncementStatus> {
  private static AnnouncementStatusService INSTANCE;

  private AnnouncementStatusService() {}

  public static final AnnouncementStatusService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new AnnouncementStatusService();
    }
    return INSTANCE;
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
    return repo().search(getType()).execute().getFirst();
  }

  @Override
  public Class<AnnouncementStatus> getType() {
    return AnnouncementStatus.class;
  }
}

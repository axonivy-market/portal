package ch.ivy.addon.portalkit.service;


import java.util.List;

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

  public boolean getAnnouncementStatus() {
    boolean announcementActivated = false;
    AnnouncementStatus property = findFirst();
    if (property != null) {
      announcementActivated = Boolean.parseBoolean(property.getEnabled());
    }
    return announcementActivated;
  }

  public void updateAnnouncementStatus(boolean status) {
    removeAnnouncementStatus();
    if (status) {
      AnnouncementStatus property = new AnnouncementStatus(String.valueOf(status));
      save(property);
    }
  }

  public long getCountAnnouncementStatus() {
    return repo().search(getType()).limit(1).execute().totalCount();
  }

  public void removeAnnouncementStatus() {
    List<AnnouncementStatus> results = repo().search(getType()).limit((int) getCountAnnouncementStatus()).execute().getAll();
    results.forEach(announcementStatus -> delete(announcementStatus.getId()));
  }

  public AnnouncementStatus findFirst() {
    return repo().search(getType()).limit(1).execute().getFirst();
  }

  @Override
  public Class<AnnouncementStatus> getType() {
    return AnnouncementStatus.class;
  }
}

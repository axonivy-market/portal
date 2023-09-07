package com.axonivy.portal.service;

import java.util.List;

import com.axonivy.portal.datamodel.NotificationLazyModel;
import com.axonivy.portal.dto.NotificationDto;

import ch.ivyteam.ivy.notification.web.WebNotifications;

public class NotificationService {
  private static NotificationService instance;

  public static NotificationService getInstance() {
    if (instance == null) {
      instance = new NotificationService();
    }
    return instance;
  }

  public List<NotificationDto> getNotification() {
    NotificationLazyModel model = new NotificationLazyModel();
    return model.load(0, 20, null, null);
  }

  public long countUnread() {
    return WebNotifications.current().countUnread();
  }
}

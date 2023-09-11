package com.axonivy.portal.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.datamodel.NotificationLazyModel;
import com.axonivy.portal.dto.NotificationDto;

import ch.ivyteam.ivy.notification.web.WebNotifications;

@ViewScoped
@ManagedBean
public class NotificationBean {

  private final WebNotifications webNotifications;
  private final NotificationLazyModel dataModel;

  private long countAll;
  private long countUnread;

  public NotificationBean() {
    this.webNotifications = WebNotifications.current();
    this.countAll = webNotifications.countAll();
    this.countUnread = webNotifications.countUnread();
    this.dataModel = new NotificationLazyModel(webNotifications);
  }

  public NotificationLazyModel getDataModel() {
    return dataModel;
  }

  public void hideAll() {
    webNotifications.hideAll();
    countAll = 0;
    countUnread = 0;
  }

  public void readAll() {
    webNotifications.markAllAsRead();
    countUnread = 0;
  }

  public void markAsRead(NotificationDto dto) {
    if (!dto.isRead()) {
      webNotifications.markAsRead(dto.getNotification());
      countUnread--;
    }
  }

  public boolean hasNotifications() {
    return countAll != 0;
  }

  public boolean hasUnreadNotifications() {
    return countUnread != 0;
  }

  public String getUnreadNotifications() {
    return countUnread > 99 ? "99+" : String.valueOf(countUnread);
  }
}

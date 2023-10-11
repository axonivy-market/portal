package com.axonivy.portal.datamodel;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.axonivy.portal.dto.NotificationDto;

import ch.ivyteam.ivy.notification.web.WebNotification;
import ch.ivyteam.ivy.notification.web.WebNotifications;

public class NotificationLazyModel extends LazyDataModel<NotificationDto> {

  private static final long serialVersionUID = 4026276222222837138L;

  private final WebNotifications webNotifications;

  public NotificationLazyModel() {
    this.webNotifications = WebNotifications.current();
  }

  public NotificationLazyModel(WebNotifications webNotifications) {
    this.webNotifications = webNotifications;
  }

  private boolean isOnlyUnread = false;

  public boolean isOnlyUnread() {
    return isOnlyUnread;
  }

  public void setOnlyUnread(boolean isOnlyUnread) {
    this.isOnlyUnread = isOnlyUnread;
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return (int) webNotifications.countAll();
  }

  AtomicBoolean isMarkedToday = new AtomicBoolean();
  AtomicBoolean isMarkedOlder = new AtomicBoolean();
  @Override
  public List<NotificationDto> load(int first, int pageSize, Map<String, SortMeta> sortBy,
      Map<String, FilterMeta> filterBy) {
    if (first == 0) {
      resetGroupNotifications();
    }
    Date today = new Date();

    List<WebNotification> notifications;
    if (isOnlyUnread) {
      notifications = webNotifications.unread(first, pageSize);
    } else {
      notifications = webNotifications.all(first, pageSize);
    }
    List<NotificationDto> results = notifications.stream().map(noti -> {
      NotificationDto dto = new NotificationDto(noti);
      if (DateUtils.isSameDay(dto.getCreatedAt(), today)) {
        if (!isMarkedToday.get() && first == 0) {
          dto.setMarkedToday(true);
          isMarkedToday.getAndSet(true);
        }
      } else if (!isMarkedOlder.get()) {
          dto.setMarkedOlder(true);
          isMarkedOlder.getAndSet(true);
      }

      return dto;
    }).collect(Collectors.toList());

    int rowCount = 0;
    if (results.size() >= pageSize) {
      rowCount = first + pageSize + 1;
    } else {
      rowCount = first + results.size();
    }
    setRowCount(rowCount);
    PrimeFaces.current().executeScript("PF('notifications-scroller').cfg.totalSize = " + rowCount);
    return results;
  }

  public void onSelectedFilter() {
    resetGroupNotifications();
  }

  private void resetGroupNotifications() {
    isMarkedToday.getAndSet(false);
    isMarkedOlder.getAndSet(false);
  }

  public void markAsRead(WebNotification dto) {
    webNotifications.markAsRead(dto);
  }

}

package com.axonivy.portal.datamodel;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
  private boolean onlyUnread;
  private String widgetId;

  public NotificationLazyModel() {
    this.webNotifications = WebNotifications.current();
  }

  public NotificationLazyModel(WebNotifications webNotifications) {
    this.webNotifications = webNotifications;
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return (int) webNotifications.countAll();
  }

  private AtomicBoolean isMarkedToday = new AtomicBoolean();
  private AtomicBoolean isMarkedOlder = new AtomicBoolean();
  @Override
  public List<NotificationDto> load(int first, int pageSize, Map<String, SortMeta> sortBy,
      Map<String, FilterMeta> filterBy) {
    if (first == 0) {
      resetGroupNotifications();
    }
    Date today = new Date();
    List<WebNotification> notifications;
    if (this.onlyUnread) {
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
    String scroller = StringUtils.isBlank(widgetId) ? String.format("PF('notifications-scroller').cfg.totalSize =%s", rowCount) : String.format("PF('notifications-scroller-%s').cfg.totalSize= %s", widgetId, rowCount);
    PrimeFaces.current().executeScript(scroller);

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
    if (!dto.isRead()) {
      webNotifications.markAsRead(dto);
    }
  }
  
  public NotificationDto findById(String notificationId) {
    WebNotification notification =  webNotifications.find(notificationId);
    return new NotificationDto(notification);
  }

  public boolean isOnlyUnread() {
    return onlyUnread;
  }

  public void setOnlyUnread(boolean onlyUnread) {
    this.onlyUnread = onlyUnread;
  }

  public String getWidgetId() {
    return widgetId;
  }

  public void setWidgetId(String widgetId) {
    this.widgetId = widgetId;
  }

}

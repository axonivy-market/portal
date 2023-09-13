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

import ch.ivyteam.ivy.notification.web.WebNotifications;

public class NotificationLazyModel extends LazyDataModel<NotificationDto> {

  private static final long serialVersionUID = 4026276222222837138L;

  private final WebNotifications webNotifications;
  private AtomicBoolean isMarkedToday = new AtomicBoolean();
  private AtomicBoolean isMarkedOlder = new AtomicBoolean();

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

  @Override
  public List<NotificationDto> load(int first, int pageSize, Map<String, SortMeta> sortBy,
      Map<String, FilterMeta> filterBy) {

    Date today = new Date();
    List<NotificationDto> results = webNotifications.read(first, pageSize).stream().map(noti -> {
      NotificationDto dto = new NotificationDto(noti);
      if (DateUtils.isSameDay(dto.getCreatedAt(), today)) {
        if (!isMarkedToday.get()) {
          dto.setMarkedToday(true);
          isMarkedToday.getAndSet(true);
        }
      } else {
        if (!isMarkedOlder.get()) {
          dto.setMarkedOlder(true);
          isMarkedOlder.getAndSet(true);
        }
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
    // Just for fire event load
  }


}

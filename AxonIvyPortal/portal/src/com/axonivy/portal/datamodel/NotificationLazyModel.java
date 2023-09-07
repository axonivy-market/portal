package com.axonivy.portal.datamodel;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.axonivy.portal.dto.NotificationDto;

import ch.ivyteam.ivy.notification.web.WebNotifications;

public class NotificationLazyModel extends LazyDataModel<NotificationDto> {

  private static final long serialVersionUID = 4026276222222837138L;

  private final WebNotifications webNotifications;

  List<NotificationDto> notifications;

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

  @Override
  public List<NotificationDto> load(int first, int pageSize, Map<String, SortMeta> sortBy,
      Map<String, FilterMeta> filterBy) {

    List<NotificationDto> results = webNotifications.read(first, pageSize).stream().map(noti -> {
      NotificationDto dto = new NotificationDto(noti);
      dto.setId(noti.id());
      dto.setMessage(noti.message());
      dto.setRead(noti.isRead());
      dto.setCreatedAt(Date.from(noti.createdAt()));
      dto.setTimeAgo(getTimeAgo(dto.getCreatedAt()) + " ago");
      return dto;
    }).collect(Collectors.toList());

    return results;
  }

  private String getTimeAgo(Date date) {
    int seconds = (int) (new Date().getTime() - date.getTime()) / 1000;

    int interval = seconds / 31536000;

    if (interval >= 1) {
      return interval + " years";
    }
    interval = seconds / 2592000;
    if (interval >= 1) {
      return interval + " months";
    }
    interval = seconds / 86400;
    if (interval >= 1) {
      return interval + " days";
    }
    interval = seconds / 3600;
    if (interval >= 1) {
      return interval + " hours";
    }
    interval = seconds / 60;
    if (interval >= 1) {
      return interval + " minutes";
    }
    return seconds + " seconds";
  }

}

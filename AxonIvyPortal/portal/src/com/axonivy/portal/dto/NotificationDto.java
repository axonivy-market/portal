package com.axonivy.portal.dto;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.notification.web.WebNotification;

public class NotificationDto {
  private String id;
  private Date createdAt;
  private String message;
  private boolean isRead;
  private String timeSince;
  private boolean isMarkedToday;
  private boolean isMarkedOlder;

  public boolean isMarkedToday() {
    return isMarkedToday;
  }

  public void setMarkedToday(boolean isMarkedToday) {
    this.isMarkedToday = isMarkedToday;
  }

  public boolean isMarkedOlder() {
    return isMarkedOlder;
  }

  public void setMarkedOlder(boolean isMarkedOlder) {
    this.isMarkedOlder = isMarkedOlder;
  }

  public String getTimeSince() {
    return timeSince;
  }

  public void setTimeSince(String timeSince) {
    this.timeSince = timeSince;
  }

  private final WebNotification notification;

  public NotificationDto(WebNotification noti) {
    this.notification = noti;
    this.setId(noti.id());
    this.setMessage(noti.message());
    this.setRead(noti.isRead());
    this.setCreatedAt(Date.from(noti.createdAt()));
    this.setTimeSince(getTimeSince(this.getCreatedAt()));
  }

  private String getTimeSince(Date date) {
    long milliseconds = new Date().getTime() - date.getTime();

    long interval = milliseconds / (365 * DateUtils.MILLIS_PER_DAY);

    if (interval >= 1) {
      return String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/notifications/xYearsAgo"), interval);
    }
    interval = milliseconds / (30 * DateUtils.MILLIS_PER_DAY);
    if (interval >= 1) {
      return String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/notifications/xMonthsAgo"), interval);
    }
    interval = milliseconds / DateUtils.MILLIS_PER_DAY;
    if (interval >= 1) {
      return String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/notifications/xDaysAgo"), interval);
    }
    interval = milliseconds / DateUtils.MILLIS_PER_HOUR;
    if (interval >= 1) {
      return String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/notifications/xHoursAgo"), interval);
    }
    interval = milliseconds / DateUtils.MILLIS_PER_MINUTE;
    if (interval >= 1) {
      return String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/notifications/xMinutesAgo"), interval);
    }
    return String.format(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/notifications/xSecondsAgo"), milliseconds / 1000);
  }

  public WebNotification getNotification() {
    return notification;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setRead(boolean isRead) {
    this.isRead = isRead;
  }

  public String getMessage() {
    return this.message;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public boolean isRead() {
    return this.isRead;
  }

  public String getStyle() {
    if (this.isRead) {
      return "";
    }
    return "p-text-bold";
  }

}
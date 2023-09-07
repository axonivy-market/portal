package com.axonivy.portal.dto;

import java.util.Date;

import ch.ivyteam.ivy.notification.web.WebNotification;

public class NotificationDto {
  private String id;
  private Date createdAt;
  private String message;
  private boolean isRead;
  private String timeAgo;

  public String getTimeAgo() {
    return timeAgo;
  }

  public void setTimeAgo(String timeAgo) {
    this.timeAgo = timeAgo;
  }

  private final WebNotification notification;

  public NotificationDto(WebNotification notification) {
    this.notification = notification;
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
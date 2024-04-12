package com.axonivy.portal.dto;

import java.util.Date;

public class PortalSessionInfo {
  private String tabId;
  private String title;
  private Date activeTime;
  private long millisecondsToTimeout;

  public PortalSessionInfo(String tabId, String title) {
    this.tabId = tabId;
    this.title = title;
    this.activeTime = new Date();
  }

  public String getTabId() {
    return tabId;
  }

  public void setTabId(String tabId) {
    this.tabId = tabId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getActiveTime() {
    return activeTime;
  }

  public void setActiveTime(Date activeTime) {
    this.activeTime = activeTime;
  }

  public long getMillisecondsToTimeout() {
    return millisecondsToTimeout;
  }

  public void setMillisecondsToTimeout(long millisecondsToTimeout) {
    this.millisecondsToTimeout = millisecondsToTimeout;
  }
}

package com.axonivy.portal.dto;

import java.util.Date;

public class PortalSessionInfo {
  private String tabId;
  private Date activeTime;
  private long millisecondsToTimeout;

  public PortalSessionInfo(String tabId) {
    this.tabId = tabId;
    this.activeTime = new Date();
  }

  public String getTabId() {
    return tabId;
  }

  public void setTabId(String tabId) {
    this.tabId = tabId;
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

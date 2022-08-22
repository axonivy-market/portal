package ch.ivy.addon.portalkit.ivydata.bo;

import java.util.Set;

import ch.ivyteam.util.date.Weekday;

public class IvyEmailSetting {

  private boolean emailSendOnNewWorkTasks;
  private Set<Weekday> emailSendDailyTaskSummary;
  private boolean customMailEnabled;
  private String appName;
  private String appDisplayName;
  private boolean enableDailySummary;
  
  public boolean isEmailSendOnNewWorkTasks() {
    return emailSendOnNewWorkTasks;
  }

  public void setEmailSendOnNewWorkTasks(boolean emailSendOnNewWorkTasks) {
    this.emailSendOnNewWorkTasks = emailSendOnNewWorkTasks;
  }

  public Set<Weekday> getEmailSendDailyTaskSummary() {
    return emailSendDailyTaskSummary;
  }

  public void setEmailSendDailyTaskSummary(Set<Weekday> emailSendDailyTaskSummary) {
    this.emailSendDailyTaskSummary = emailSendDailyTaskSummary;
  }

  public boolean isCustomMailEnabled() {
    return customMailEnabled;
  }

  public void setCustomMailEnabled(boolean customMailEnabled) {
    this.customMailEnabled = customMailEnabled;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getAppDisplayName() {
    return appDisplayName;
  }

  public void setAppDisplayName(String appDisplayName) {
    this.appDisplayName = appDisplayName;
  }

  public boolean isEnableDailySummary() {
    return enableDailySummary;
  }

  public void setEnableDailySummary(boolean enableDailySummary) {
    this.enableDailySummary = enableDailySummary;
  }

}

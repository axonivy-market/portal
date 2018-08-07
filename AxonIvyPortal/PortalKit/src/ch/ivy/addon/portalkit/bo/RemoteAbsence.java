package ch.ivy.addon.portalkit.bo;

import java.util.Date;

public class RemoteAbsence {

  private String appName;
  private Date startDateInclusive;
  private Date stopDateInclusive;
  private String description;
  private String userName;
  private String userFullName;

  public RemoteAbsence() {}

  public RemoteAbsence(String appName, Date startDateInclusive, Date stopDateInclusive, String description) {
    this.appName = appName;
    this.startDateInclusive = startDateInclusive;
    this.stopDateInclusive = stopDateInclusive;
    this.description = description;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public Date getStartDateInclusive() {
    return startDateInclusive;
  }

  public void setStartDateInclusive(Date startDateInclusive) {
    this.startDateInclusive = startDateInclusive;
  }

  public Date getStopDateInclusive() {
    return stopDateInclusive;
  }

  public void setStopDateInclusive(Date stopDateInclusive) {
    this.stopDateInclusive = stopDateInclusive;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserFullName() {
    return userFullName;
  }

  public void setUserFullName(String userFullName) {
    this.userFullName = userFullName;
  }

}

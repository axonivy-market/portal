package ch.ivy.addon.portalkit.persistence.domain;



public class User extends BusinessEntity {
  private String userName;
  private String fullUserName;
  private String applicationName;
  private long serverId;
  
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFullUserName() {
    return fullUserName;
  }

  public void setFullUserName(String fullUserName) {
    this.fullUserName = fullUserName;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public long getServerId() {
    return serverId;
  }

  public void setServerId(long serverId) {
    this.serverId = serverId;
  }
  
  @Override
  public String toString() {
    return "User {userName=" + userName + ", fullUserName=" + fullUserName + ", serverId=" + serverId
        + ", applicationName=" + applicationName + ", id=" + getId() + "}";
  }
}

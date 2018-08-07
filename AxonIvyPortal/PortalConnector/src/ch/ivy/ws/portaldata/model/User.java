package ch.ivy.ws.portaldata.model;

public class User {
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
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
    result = prime * result + (int) (serverId ^ (serverId >>> 32));
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (applicationName == null) {
      if (other.applicationName != null)
        return false;
    } else if (!applicationName.equals(other.applicationName))
      return false;
    if (serverId != other.serverId)
      return false;
    if (userName == null) {
      if (other.userName != null)
        return false;
    } else if (!userName.equals(other.userName))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User {userName=" + userName + ", fullUserName=" + fullUserName + ", serverId=" + serverId
        + ", applicationName=" + applicationName + "}";
  }
}

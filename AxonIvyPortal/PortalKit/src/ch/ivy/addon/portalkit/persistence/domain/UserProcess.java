package ch.ivy.addon.portalkit.persistence.domain;


public class UserProcess extends BusinessEntity {
  private String userName;
  private String processName;
  private String icon;
  private String link;
  private boolean defaultProcess;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getProcessName() {
    return processName;
  }

  public void setProcessName(String processName) {
    this.processName = processName;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public boolean isDefaultProcess() {
    return defaultProcess;
  }

  public void setDefaultProcess(boolean defaultProcess) {
    this.defaultProcess = defaultProcess;
  }

  @Override
  public String toString() {
    return "UserProcess {userName=" + userName + ", processName=" + processName + ", icon=" + icon + ", link=" + link
        + ", isDefaultProcess=" + defaultProcess + ", id=" + getId() + "}";
  }
}

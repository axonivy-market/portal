package ch.ivy.addon.portalkit.persistence.domain;

import java.util.Objects;


public class UserProcess extends BusinessEntity {
  private String userName;
  private String processName;
  private String icon;
  private String link;
  private boolean defaultProcess;

  public UserProcess() {

  }

  public UserProcess(String processName, String userName, String link) {
    this.processName = processName;
    this.userName = userName;
    this.link = link;
  }

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

  @Override
  public int hashCode() {
    return Objects.hash(defaultProcess, icon, link, processName, userName);
  }
}

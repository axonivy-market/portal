package ch.ivy.addon.portalkit.persistence.domain;

import org.boon.json.annotations.JsonIgnore;


public class Application extends BusinessEntity {

  private String displayName;
  private Boolean isOnline;
  private Boolean isVisible;
  private Boolean isSupportEmailSettings;
  private Boolean isSupportAbsenceSettings;
  private String menuIcon;
  private Integer menuOrdinal;
  private String name;
  private String link;
  private Long serverId;

  @JsonIgnore
  private Server server;

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Boolean getIsOnline() {
    return isOnline;
  }

  public void setIsOnline(Boolean isOnline) {
    this.isOnline = isOnline;
  }

  public Boolean getIsVisible() {
    return isVisible;
  }

  public void setIsVisible(Boolean isVisible) {
    this.isVisible = isVisible;
  }

  public Boolean getIsSupportEmailSettings() {
    return isSupportEmailSettings;
  }

  public void setIsSupportEmailSettings(Boolean isSupportEmailSettings) {
    this.isSupportEmailSettings = isSupportEmailSettings;
  }

  public Boolean getIsSupportAbsenceSettings() {
    return isSupportAbsenceSettings;
  }

  public void setIsSupportAbsenceSettings(Boolean isSupportAbsenceSettings) {
    this.isSupportAbsenceSettings = isSupportAbsenceSettings;
  }

  public String getMenuIcon() {
    return menuIcon;
  }

  public void setMenuIcon(String menuIcon) {
    this.menuIcon = menuIcon;
  }

  public Integer getMenuOrdinal() {
    return menuOrdinal;
  }

  public void setMenuOrdinal(Integer menuOrdinal) {
    this.menuOrdinal = menuOrdinal;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Long getServerId() {
    return serverId;
  }

  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }

  public Server getServer() {
    return this.server;
  }

  public void setServer(Server server) {
    this.serverId = server.getId();
    this.server = server;
  }

  @Override
  public String toString() {
    return "Application {displayName=" + displayName + ", isOnline=" + isOnline + ", isVisible=" + isVisible
        + ", isSupportEmailSettings=" + isSupportEmailSettings + ", isSupportAbsenceSettings="
        + isSupportAbsenceSettings + ", menuIcon=" + menuIcon + ", menuOrdinal=" + menuOrdinal + ", name=" + name
        + ", link=" + link + ", serverId=" + serverId + ", id=" + getId() + "}";
  }
}

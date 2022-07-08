package com.axonivy.portal.component.persistence.domain;

public class Application extends BusinessEntity {

  private String displayName;
  private String menuIcon;
  private Integer menuOrdinal;
  private String name;
  private String link;
  private Long serverId;
  private boolean embedInFrame = true;

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
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

  public boolean isEmbedInFrame() {
    return embedInFrame;
  }

  public void setEmbedInFrame(boolean embedInFrame) {
    this.embedInFrame = embedInFrame;
  }

  @Override
  public String toString() {
    return "Application {displayName=" + displayName
        +  ", menuIcon=" + menuIcon + ", menuOrdinal=" + menuOrdinal + ", name=" + name
        + ", link=" + link + ", serverId=" + serverId + ", id=" + getId() + ", embedInFrame = " + embedInFrame + "}";
  }
}

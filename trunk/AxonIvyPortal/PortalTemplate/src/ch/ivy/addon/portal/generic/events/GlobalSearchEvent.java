package ch.ivy.addon.portal.generic.events;

import ch.ivyteam.ivy.event.SystemEvent;
import ch.ivyteam.ivy.event.SystemEventCategory;

public class GlobalSearchEvent {
  private String keyword;
  private String username;
  private Long serverId;
  private String applicationName;
  
  public GlobalSearchEvent(String keyword, String username, Long serverId, String applicationName){
    this.keyword = keyword;
    this.username = username;
    this.serverId = serverId;
    this.applicationName = applicationName;
  }
  
  public SystemEvent<GlobalSearchEvent> toSystemEvent() {
    return new SystemEvent<>(SystemEventCategory.THIRD_PARTY, GlobalSearchEvent.class.getName(), this);
  }

  public static boolean hasCorrectSignature(SystemEvent<?> systemEvent) {
    return GlobalSearchEvent.class.getName().equals(systemEvent.getName())
        && GlobalSearchEvent.class.isAssignableFrom(systemEvent.getParameter().getClass());
  }
  
  public String getKeyword() {
    return keyword;
  }
  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getServerId() {
    return serverId;
  }

  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }
}

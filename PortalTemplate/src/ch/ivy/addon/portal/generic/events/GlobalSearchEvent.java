package ch.ivy.addon.portal.generic.events;

import ch.ivyteam.ivy.event.SystemEvent;
import ch.ivyteam.ivy.event.SystemEventCategory;

public class GlobalSearchEvent {
  private String keyword;
  private String username;
  
  public GlobalSearchEvent(String keyword, String username){
    this.keyword = keyword;
    this.username = username;
  }
  
  public SystemEvent<GlobalSearchEvent> toSystemEvent() {
    return new SystemEvent<>(SystemEventCategory.THIRD_PARTY, GlobalSearchEvent.class.getName(), this);
  }

  public static boolean hasCorrectSignature(SystemEvent<?> systemEvent) {
    return GlobalSearchEvent.class.getName().equals(systemEvent.getName());
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
}

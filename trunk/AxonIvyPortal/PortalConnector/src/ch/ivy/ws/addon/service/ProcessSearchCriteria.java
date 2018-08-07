package ch.ivy.ws.addon.service;

import java.util.List;
import java.util.Objects;

public class ProcessSearchCriteria {

  private String involvedUsername;
  private List<String> involvedApplications;
  private Long serverId;
  private String keyword;

  public boolean isEmpty() {
    return !hasInvolvedUsername()
        && !hasInvolvedApplications()
        && !hasKeyword()
        && !hasServerId();
  }
  
  public String getInvolvedUsername() {
    return involvedUsername;
  }

  public void setInvolvedUsername(String involvedUsername) {
    this.involvedUsername = involvedUsername;
  }

  public List<String> getInvolvedApplications() {
    return involvedApplications;
  }

  public void setInvolvedApplications(List<String> involvedApplications) {
    this.involvedApplications = involvedApplications;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public boolean hasInvolvedApplications() {
    return involvedApplications != null && !involvedApplications.isEmpty();
  }

  public boolean hasKeyword() {
    return !(Objects.toString(keyword, "").isEmpty());
  }

  public boolean hasInvolvedUsername() {
    return !(Objects.toString(involvedUsername, "").isEmpty());
  }
  
  public Long getServerId() {
    return serverId;
  }

  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }
  
  public boolean hasServerId() {
    return serverId != null && serverId != 0;
  }
}

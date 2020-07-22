package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.List;

public class ProcessSearchCriteria {

  private String filteredKeyword;
  private Long userId;
  private List<String> apps;

  public String getFilteredKeyword() {
    return filteredKeyword;
  }

  public void setFilteredKeyword(String filteredKeyword) {
    this.filteredKeyword = filteredKeyword;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public List<String> getApps() {
    return apps;
  }

  public void setApps(List<String> apps) {
    this.apps = apps;
  }
}

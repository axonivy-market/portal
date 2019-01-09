package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.List;

public class ProcessSearchCriteria {

  private String filteredKeyword;
  private String username;
  private List<String> apps;

  public String getFilteredKeyword() {
    return filteredKeyword;
  }

  public void setFilteredKeyword(String filteredKeyword) {
    this.filteredKeyword = filteredKeyword;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getApps() {
    return apps;
  }

  public void setApps(List<String> apps) {
    this.apps = apps;
  }

}

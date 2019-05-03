package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.List;

public class CaseCustomFieldSearchCriteria {
  private String keyword;
  private List<String> apps;
  private String fieldName;
  
  public String getKeyword() {
    return keyword;
  }
  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
  public List<String> getApps() {
    return apps;
  }
  public void setApps(List<String> apps) {
    this.apps = apps;
  }
  public String getFieldName() {
    return fieldName;
  }
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }
}

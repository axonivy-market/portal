package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.enums.CustomVarCharField;

public class CaseCustomVarCharSearchCriteria {
  private CustomVarCharField customVarCharField;
  private String keyword;
  private int limit;
  private List<String> apps;

  public CustomVarCharField getCustomVarCharField() {
    return customVarCharField;
  }

  public void setCustomVarCharField(CustomVarCharField customVarCharField) {
    this.customVarCharField = customVarCharField;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public List<String> getApps() {
    return apps;
  }

  public void setApps(List<String> apps) {
    this.apps = apps;
  }

  public boolean hasApplications() {
    return CollectionUtils.isNotEmpty(apps);
  }

}

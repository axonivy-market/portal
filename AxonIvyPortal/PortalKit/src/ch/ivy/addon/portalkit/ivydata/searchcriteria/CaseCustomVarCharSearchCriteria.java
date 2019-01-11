package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import ch.ivy.addon.portalkit.enums.CustomVarCharField;

public class CaseCustomVarCharSearchCriteria {
  private CustomVarCharField customVarCharField;
  private String keyword;
  private int limit;
  private String applicationIds;

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

  public String getApplicationIds() {
    return applicationIds;
  }

  public void setApplicationIds(String applicationIds) {
    this.applicationIds = applicationIds;
  }

  public boolean hasApplications() {
    return applicationIds != null && !applicationIds.isEmpty();
  }

}

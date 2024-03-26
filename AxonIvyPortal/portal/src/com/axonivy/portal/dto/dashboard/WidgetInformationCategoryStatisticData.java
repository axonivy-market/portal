package com.axonivy.portal.dto.dashboard;

import java.io.Serializable;

public class WidgetInformationCategoryStatisticData implements Serializable {

  private static final long serialVersionUID = 8763058243562205725L;

  private String categoryDisplayName;

  private String categoryTechnicalPath;

  private long summary;
  
  private String categoryDisplayPath;

  public String getCategoryDisplayName() {
    return categoryDisplayName;
  }

  public void setCategoryDisplayName(String categoryDisplayName) {
    this.categoryDisplayName = categoryDisplayName;
  }

  public String getCategoryTechnicalPath() {
    return categoryTechnicalPath;
  }

  public void setCategoryTechnicalPath(String categoryTechnicalPath) {
    this.categoryTechnicalPath = categoryTechnicalPath;
  }

  public long getSummary() {
    return summary;
  }

  public void setSummary(long summary) {
    this.summary = summary;
  }

  public String getCategoryDisplayPath() {
    return categoryDisplayPath;
  }

  public void setCategoryDisplayPath(String categoryDisplayPath) {
    this.categoryDisplayPath = categoryDisplayPath;
  }
}

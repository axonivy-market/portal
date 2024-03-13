package com.axonivy.portal.dto.dashboard;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.cm.exec.LocalizedTextResolverFactory;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.Category;
import ch.ivyteam.ivy.workflow.category.CategoryPath;

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
    if (StringUtils.isNotEmpty(categoryTechnicalPath)) {
      Category category = Category.createFor(new CategoryPath(categoryTechnicalPath),
          new LocalizedTextResolverFactory().createFor("Categories", Ivy.request().getProcessModelVersion()));
      this.categoryDisplayName = category.getName();
      this.categoryDisplayPath = category.getPath();
    }
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

  @Override
  public String toString() {
    return "WidgetInformationCategoryStatisticData [categoryDisplayName=" + categoryDisplayName
        + ", categoryTechnicalPath=" + categoryTechnicalPath + ", summary=" + summary + ", categoryDisplayPath="
        + categoryDisplayPath + "]";
  }
}

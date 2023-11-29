package com.axonivy.portal.bo;

import java.util.List;

import com.axonivy.portal.util.DisplayNameUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ch.ivy.addon.portalkit.dto.DisplayName;

public abstract class ColumnChartConfig {
  private List<DisplayName> xTitles;
  @JsonProperty(access = Access.READ_ONLY)
  private String xTitle;
  private List<DisplayName> yTitles;
  @JsonProperty(access = Access.READ_ONLY)
  private String yTitle;

  public List<DisplayName> getxTitles() {
    return xTitles;
  }

  public void setxTitles(List<DisplayName> xTitles) {
    this.xTitles = xTitles;
    xTitle = DisplayNameUtils.findDisplayNameOfUserLanguage(xTitles);
  }

  public List<DisplayName> getyTitles() {
    return yTitles;
  }

  public void setyTitles(List<DisplayName> yTitles) {
    this.yTitles = yTitles;
    yTitle = DisplayNameUtils.findDisplayNameOfUserLanguage(yTitles);
  }

  public String getxTitle() {
    return xTitle;
  }

  public String getyTitle() {
    return yTitle;
  }
}

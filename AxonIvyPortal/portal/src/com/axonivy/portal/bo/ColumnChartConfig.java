package com.axonivy.portal.bo;

import java.util.List;

import com.axonivy.portal.util.DisplayNameUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;

public abstract class ColumnChartConfig {
  private List<DisplayName> xTitles;
  @JsonProperty(access = Access.READ_ONLY)
  @JsonIgnore
  private String xTitle;
  private List<DisplayName> yTitles;
  @JsonProperty(access = Access.READ_ONLY)
  @JsonIgnore
  private String yTitle;
  @JsonInclude(value = Include.NON_NULL)
  private String yValue;
  private List<String> backgroundColors;

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
    if (xTitle == null) {
      return LanguageUtils.getLocalizedName(xTitles, xTitle);
    }
    return xTitle;
  }

  public void setxTitle(String xTitle) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(xTitles, xTitle);
    this.xTitles = nameResult.names();
    this.xTitle = nameResult.name();
  }

  public String getyTitle() {
    if (yTitle == null) {
      return LanguageUtils.getLocalizedName(yTitles, yTitle);
    }
    return yTitle;
  }

  public void setyTitle(String yTitle) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(yTitles, yTitle);
    this.yTitles = nameResult.names();
    this.yTitle = nameResult.name();
  }

  public String getyValue() {
    return yValue;
  }

  public List<String> getBackgroundColors() {
    return backgroundColors;
  }

  public void setBackgroundColors(List<String> backgroundColors) {
    this.backgroundColors = backgroundColors;
  }

}

package com.axonivy.portal.bo;

import java.util.List;
import java.util.Map.Entry;

import com.axonivy.portal.util.DisplayNameUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivyteam.ivy.environment.Ivy;

public class ClientStatistic extends AbstractConfiguration {
  private String aggregates;
  private String filter;
  private List<String> permissions;
  private ChartTarget chartTarget;
  private String chartType;
  private long refreshInterval; // in seconds

  private BarChartConfig barChartConfig;
  private LineChartConfig lineChartConfig;
  private NumberChartConfig numberChartConfig;
  private List<DisplayName> names;
  @JsonProperty(access = Access.READ_ONLY)
  private String name;
  private List<DisplayName> descriptions;
  @JsonProperty(access = Access.READ_ONLY)
  private String description;
  @JsonProperty(access = Access.READ_ONLY)
  private List<Entry<String, String>> additionalConfig;
  private String icon;
  private String locale = Ivy.session().getFormattingLocale().toString();
  private Boolean hideLabel = false;
  private String manipulateValueBy;
  
  public String getIcon() {
    return icon;
  }

  public NumberChartConfig getNumberChartConfig() {
    return numberChartConfig;
  }

  public void setNumberChartConfig(NumberChartConfig numberChartConfig) {
    this.numberChartConfig = numberChartConfig;
  }

  public String getChartType() {
    return chartType;
  }

  public void setChartType(String chartType) {
    this.chartType = chartType;
  }

  public long getRefreshInterval() {
    return refreshInterval;
  }

  public void setRefreshInterval(long refreshInterval) {
    this.refreshInterval = refreshInterval;
  }

  public BarChartConfig getBarChartConfig() {
    return barChartConfig;
  }

  public void setBarChartConfig(BarChartConfig barChartConfig) {
    this.barChartConfig = barChartConfig;
  }

  public LineChartConfig getLineChartConfig() {
    return lineChartConfig;
  }

  public void setLineChartConfig(LineChartConfig lineChartConfig) {
    this.lineChartConfig = lineChartConfig;
  }

public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  public String getAggregates() {
    return aggregates;
  }

  public void setAggregates(String aggregates) {
    this.aggregates = aggregates;
  }

  public ChartTarget getChartTarget() {
    return chartTarget;
  }

  public void setChartTarget(ChartTarget chartTarget) {
    this.chartTarget = chartTarget;
  }

  public List<DisplayName> getNames() {
    return names;
  }

  public void setNames(List<DisplayName> names) {
    this.names = names;
    name = DisplayNameUtils.findDisplayNameOfUserLanguage(names);
  }

  public String getName() {
    return name;
  }

  public List<DisplayName> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<DisplayName> descriptions) {
    this.descriptions = descriptions;
    description = DisplayNameUtils.findDisplayNameOfUserLanguage(descriptions);
  }

  public String getDescription() {
    return description;
  }

  public List<Entry<String, String>> getAdditionalConfig() {
    return additionalConfig;
  }

  public void setAdditionalConfig(List<Entry<String, String>> additionalConfig) {
    this.additionalConfig = additionalConfig;
  }
  
  public String getLocale() {
    return this.locale;
  }
  
  public Boolean getHideLabel() {
    return this.hideLabel;
  }

  public String getManipulateValueBy() {
    return manipulateValueBy;
  }

  public void setManipulateValueBy(String manipulateValueBy) {
    this.manipulateValueBy = manipulateValueBy;
  }
}

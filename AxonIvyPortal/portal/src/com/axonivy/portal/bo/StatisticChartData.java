package com.axonivy.portal.bo;

import java.util.List;

import com.axonivy.portal.util.DisplayNameUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.dto.DisplayName;

public class StatisticChartData extends AbstractConfiguration {
  private String aggregates;
  private String filter;
  private List<String> permissions;
  private ChartTarget chartTarget;
  private String chartType;
  private long refreshInterval; // in seconds

  private BarChartConfig barChartConfig;
  private NumberChartConfig numberChartConfig;
  private List<DisplayName> names;
  @JsonProperty(access = Access.READ_ONLY)
  private String name;
  private List<DisplayName> descriptions;
  @JsonProperty(access = Access.READ_ONLY)
  private String description;

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


}

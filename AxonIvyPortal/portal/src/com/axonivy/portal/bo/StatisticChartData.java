package com.axonivy.portal.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;

public class StatisticChartData extends AbstractConfiguration{
  private String aggregates;
  private String filter;
  private List<String> permissions;
  private ChartTarget chartTarget;
  @JsonIgnore
  private String name;
  private String chartType;
  private String label;
  private BarChartConfig barChartConfig;
  private NumberChartConfig numberChartConfig;
  
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

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

}

package com.axonivy.portal.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;

public class StatisticData extends AbstractConfiguration{
  private String aggregates;
  private String filter;
  private List<String> permissions;
  private Boolean isCaseFilter;
  @JsonIgnore
  private String name;
  private String chartType;
  private String label;
  private BarChartConfig barChartConfig;

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

  public Boolean getIsCaseFilter() {
    return isCaseFilter;
  }

  public void setIsCaseFilter(Boolean isCaseFilter) {
    this.isCaseFilter = isCaseFilter;
    }

}

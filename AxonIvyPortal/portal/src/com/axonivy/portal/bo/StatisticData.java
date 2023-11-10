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

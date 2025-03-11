package com.axonivy.portal.dto.dashboard.filter;

import java.util.List;

import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;

public class BaseFilter {
  private String field;

  private String from;

  private String to;

  private List<String> values;

  private String value;

  private FilterOperator operator;

  @JsonProperty("type")
  private DashboardColumnType filterType;

  @JsonIgnore
  private FilterFormat filterFormat;

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public List<String> getValues() {
    return values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public FilterOperator getOperator() {
    return operator;
  }

  public void setOperator(FilterOperator operator) {
    this.operator = operator;
  }

  public DashboardColumnType getFilterType() {
    return filterType;
  }

  public void setFilterType(DashboardColumnType filterType) {
    this.filterType = filterType;
  }

  @JsonIgnore
  public FilterFormat getFilterFormat() {
    return filterFormat;
  }

  @JsonIgnore
  public void setFilterFormat(FilterFormat filterFormat) {
    this.filterFormat = filterFormat;
  }
}

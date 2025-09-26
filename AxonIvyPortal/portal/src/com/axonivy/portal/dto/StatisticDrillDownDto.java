package com.axonivy.portal.dto;

public class StatisticDrillDownDto {

  private String chartId;
  private String chartType;
  private String filterKey;
  private String filterValue;
  private String label;
  private Object value;
  private int count;
  private String chartTarget;
  private Object aggregation;
  private int pageNumber = 0;
  private int pageSize = 10;
  private String sortField;
  private String sortOrder = "ASC";

  public String getChartId() {
    return chartId;
  }

  public void setChartId(String chartId) {
    this.chartId = chartId;
  }

  public String getChartType() {
    return chartType;
  }

  public void setChartType(String chartType) {
    this.chartType = chartType;
  }

  public String getFilterKey() {
    return filterKey;
  }

  public void setFilterKey(String filterKey) {
    this.filterKey = filterKey;
  }

  public String getFilterValue() {
    return filterValue;
  }

  public void setFilterValue(String filterValue) {
    this.filterValue = filterValue;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getChartTarget() {
    return chartTarget;
  }

  public void setChartTarget(String chartTarget) {
    this.chartTarget = chartTarget;
  }

  public Object getAggregation() {
    return aggregation;
  }

  public void setAggregation(Object aggregation) {
    this.aggregation = aggregation;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public String getSortField() {
    return sortField;
  }

  public void setSortField(String sortField) {
    this.sortField = sortField;
  }

  public String getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(String sortOrder) {
    this.sortOrder = sortOrder;
  }
}

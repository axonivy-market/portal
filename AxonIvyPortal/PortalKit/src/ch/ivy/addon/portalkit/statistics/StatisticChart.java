package ch.ivy.addon.portalkit.statistics;


import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.donut.DonutChartModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.StatisticChartType;

public class StatisticChart {
  private String id;
  private List<DisplayName> names;
  private long userId;
  private String defaultChart;
  private StatisticChartType type;
  @JsonIgnore
  private String jsonQuery;
  private StatisticFilter filter;
  private long position;
  @JsonIgnore
  private DonutChartModel donutChartModel;
  @JsonIgnore
  private BarChartModel barChartModel;

  @JsonIgnore
  private String name;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<DisplayName> getNames() {
    return names;
  }

  public void setNames(List<DisplayName> names) {
    this.names = names;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public StatisticChartType getType() {
    return type;
  }

  public void setType(StatisticChartType type) {
    this.type = type;
  }

  public long getPosition() {
    return position;
  }

  public void setPosition(long position) {
    this.position = position;
  }

  public DonutChartModel getDonutChartModel() {
    return donutChartModel;
  }

  public void setDonutChartModel(DonutChartModel donutChartModel) {
    this.donutChartModel = donutChartModel;
  }

  public BarChartModel getBarChartModel() {
    return barChartModel;
  }

  public void setBarChartModel(BarChartModel barChartModel) {
    this.barChartModel = barChartModel;
  }

  public StatisticFilter getFilter() {
    return filter;
  }

  public void setFilter(StatisticFilter filter) {
    this.filter = filter;
  }

  public String getDefaultChart() {
    return defaultChart;
  }

  public void setDefaultChart(String defaultChart) {
    this.defaultChart = defaultChart;
  }
  
  @Override
  public boolean equals(Object o) {
    StatisticChart chart = (StatisticChart) o;
    EqualsBuilder builder = new EqualsBuilder();
    builder.append(getId(), chart.getId());
    return builder.isEquals();
  }
  
  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    builder.append(getId());
    return builder.hashCode();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

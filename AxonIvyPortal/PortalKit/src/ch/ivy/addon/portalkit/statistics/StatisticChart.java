package ch.ivy.addon.portalkit.statistics;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.PieChartModel;

import ch.ivy.addon.portalkit.enums.StatisticChartType;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StatisticChart {
  private String id;
  private String name;
  private long userId;
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
  private PieChartModel pieChartModel;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public PieChartModel getPieChartModel() {
    return pieChartModel;
  }

  public void setPieChartModel(PieChartModel pieChartModel) {
    this.pieChartModel = pieChartModel;
  }

  public StatisticFilter getFilter() {
    return filter;
  }

  public void setFilter(StatisticFilter filter) {
    this.filter = filter;
  }
  
}

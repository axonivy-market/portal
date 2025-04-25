package com.axonivy.portal.bo;

import java.io.Serializable;

import com.axonivy.portal.enums.statistic.AggregationInterval;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatisticAggregation implements Serializable {

  private static final long serialVersionUID = 1L;

  private String field;
  private DashboardColumnType type;
  private AggregationInterval interval;


  public AggregationInterval getInterval() {
    return interval;
  }

  public void setInterval(AggregationInterval interval) {
    this.interval = interval;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public DashboardColumnType getType() {
    return type;
  }

  public void setType(DashboardColumnType type) {
    this.type = type;
  }


}

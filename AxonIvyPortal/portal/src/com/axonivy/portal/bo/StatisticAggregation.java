package com.axonivy.portal.bo;

import java.io.Serializable;

import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.StatisticInterval;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatisticAggregation implements Serializable {

  private static final long serialVersionUID = 1L;

  private AggregationField aggregationField;
  private CustomFieldType customFieldType;
  private String customFieldValue;
  private StatisticInterval interval;

  public AggregationField getAggregate() {
    return aggregationField;
  }

  public void setAggregate(AggregationField aggregate) {
    this.aggregationField = aggregate;
  }

  public CustomFieldType getCustomFieldType() {
    return customFieldType;
  }

  public void setCustomFieldType(CustomFieldType customFieldType) {
    this.customFieldType = customFieldType;
  }

  public String getCustomFieldValue() {
    return customFieldValue;
  }

  public void setCustomFieldValue(String customFieldValue) {
    this.customFieldValue = customFieldValue;
  }

  public StatisticInterval getInterval() {
    return interval;
  }

  public void setInterval(StatisticInterval interval) {
    this.interval = interval;
  }

}

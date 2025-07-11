package com.axonivy.portal.bo;

import java.io.Serializable;

import com.axonivy.portal.enums.statistic.OperatorFieldStatistic;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.CUSTOM)
public class ThresholdStatisticChart implements Serializable {
  private static final long serialVersionUID = -2098346832426240167L;

  private OperatorFieldStatistic operator;
  private Integer value;
  private String backgroundColor;
  private String categoryValue;

  public OperatorFieldStatistic getOperator() {
    return operator;
  }

  public void setOperator(OperatorFieldStatistic operator) {
    this.operator = operator;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public String getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public String getCategoryValue() {
    return categoryValue;
  }

  public void setCategoryValue(String categoryValue) {
    this.categoryValue = categoryValue;
  }
}


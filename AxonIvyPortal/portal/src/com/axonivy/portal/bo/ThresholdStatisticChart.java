package com.axonivy.portal.bo;

import java.io.Serializable;

import com.axonivy.portal.enums.statistic.OperatorField;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.CUSTOM)
public class ThresholdStatisticChart implements Serializable {
  private static final long serialVersionUID = -2098346832426240167L;

  private OperatorField operator;
  private String value;
  private String backgroundColor;
  private String categoryValue;

  public OperatorField getOperator() {
    return operator;
  }

  public void setOperator(OperatorField operator) {
    this.operator = operator;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
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


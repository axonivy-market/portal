package com.axonivy.portal.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class NumberChartConfig {
  private String suffixSymbol;
  private Boolean hideLabel;
  
  public String getSuffixSymbol() {
    return suffixSymbol;
  }

  public void setSuffixSymbol(String suffixSymbol) {
    this.suffixSymbol = suffixSymbol;
  }
  
  public Boolean getHideLabel() {
    return this.hideLabel;
  }

  public void setHideLabel(Boolean hideLabel) {
    this.hideLabel = hideLabel;
  }
}

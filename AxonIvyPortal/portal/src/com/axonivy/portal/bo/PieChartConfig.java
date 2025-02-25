package com.axonivy.portal.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class PieChartConfig {
  private List<String> backgroundColors;

  public List<String> getBackgroundColors() {
    return backgroundColors;
  }

  public void setBackgroundColors(List<String> backgroundColors) {
    this.backgroundColors = backgroundColors;
  }

}

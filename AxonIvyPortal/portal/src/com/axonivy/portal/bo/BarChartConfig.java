package com.axonivy.portal.bo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BarChartConfig extends ColumnChartConfig {

  private boolean swapAxes;

  public boolean isSwapAxes() {
    return swapAxes;
  }

  public void setSwapAxes(boolean swapAxes) {
    this.swapAxes = swapAxes;
  }
}

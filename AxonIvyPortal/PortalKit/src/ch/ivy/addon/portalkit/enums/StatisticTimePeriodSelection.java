package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum StatisticTimePeriodSelection {
  CUSTOM("custom"),
  LAST_WEEK("lastWeek"),
  LAST_MONTH("lastMonth"),
  LAST_6_MONTH("last6Month");
  
private final String label;
  
  StatisticTimePeriodSelection(String label) {
    this.label = label;
  }
  
  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/timePeriod/" + label);
  }
}

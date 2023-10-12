package com.axonivy.portal.enums.dashboard.filter;

import ch.ivyteam.ivy.environment.Ivy;

public enum FilterPeriodType {
  YEAR,
  MONTH,
  WEEK,
  DAY;

  public String getLabel() {
    return Ivy.cms().co(String.format("/Labels/Enums/FilterPeriodType/%s", this.name()));
  }

  public String getPluralLabel() {
    return Ivy.cms().co(String.format("/Labels/Enums/FilterPeriodType/Plural/%s", this.name()));
  }
}

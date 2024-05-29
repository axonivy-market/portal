package com.axonivy.portal.enums.dashboard.filter;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

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

  public static final Set<FilterPeriodType> PERIOD_TYPES_FOR_CURRENT_OPERATOR = Collections.unmodifiableSet(EnumSet.of(YEAR, MONTH, WEEK));
}

package com.axonivy.portal.enums.dashboard.filter;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import ch.ivyteam.ivy.environment.Ivy;

public enum FilterOperator {
  TODAY,
  YESTERDAY,
  BEFORE,
  BETWEEN,
  CURRENT,
  LAST,
  NEXT,

  EMPTY,
  NOT_EMPTY,
  CONTAINS,
  NOT_CONTAINS,
  IS,
  IS_NOT,
  IN,
  NOT_IN,
  START_WITH,
  NOT_START_WITH,
  END_WITH,
  NOT_END_WITH;

  public String getLabel() {
    return Ivy.cms().co(String.format("/Labels/Enums/FilterOperator/%s", this.name()));
  }

  public static final Set<FilterOperator> DATE_OPERATORS = Collections.unmodifiableSet(EnumSet.of(TODAY, YESTERDAY, BEFORE, BETWEEN, CURRENT, LAST, NEXT));
  public static final Set<FilterOperator> TEXT_OPERATORS = Collections.unmodifiableSet(EnumSet.of(EMPTY, NOT_EMPTY, CONTAINS, NOT_CONTAINS, IS, IS_NOT, IN, NOT_IN, START_WITH, NOT_START_WITH, END_WITH, NOT_END_WITH));
}

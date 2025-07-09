package com.axonivy.portal.enums.statistic;

import java.util.Collections;

import java.util.EnumSet;
import java.util.Set;

import com.axonivy.portal.enums.HasCmsName;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OperatorFieldStatistic implements HasCmsName {
  GREATER("greater"),
  LESS("less"),
  GREATEROREQUAL("greaterOrEqual"),
  LESSOREQUAL("lessOrEqual"),
  EQUAL("equal");

  public static final Set<OperatorFieldStatistic> OPERATORS = Collections.unmodifiableSet(
      EnumSet.of(GREATER, LESS, GREATEROREQUAL, LESSOREQUAL, EQUAL));
  
  private String name;

  private OperatorFieldStatistic(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }
}

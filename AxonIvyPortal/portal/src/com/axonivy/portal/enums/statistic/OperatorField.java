package com.axonivy.portal.enums.statistic;

import java.util.Collections;

import java.util.EnumSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OperatorField {
  Greater("greater"),
  Less("less"),
  GreaterOrEqual("greaterOrEqual"),
  LessOrEqual("lessOrEqual"),
  Equal("equal");

  public static final Set<OperatorField> OPERATORS = Collections.unmodifiableSet(
      EnumSet.of(Greater, Less, GreaterOrEqual, LessOrEqual, Equal));
  
  private String name;

  private OperatorField(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }
}

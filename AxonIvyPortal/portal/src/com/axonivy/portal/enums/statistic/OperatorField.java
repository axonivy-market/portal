package com.axonivy.portal.enums.statistic;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.axonivy.portal.enums.HasCmsName;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OperatorField implements HasCmsName{
  Greater("greater"),
  Less("less"),
  GreaterOrEqual("greaterOrEqual");

  public static final Set<OperatorField> OPERATORS = Collections.unmodifiableSet(
      EnumSet.of(Greater, Less, GreaterOrEqual));
  
  private String name;

  private OperatorField(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }
}

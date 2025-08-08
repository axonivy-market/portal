package com.axonivy.portal.enums.statistic;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.axonivy.portal.enums.HasCmsName;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ConditionBasedColoringScope implements HasCmsName {
  ALL("all"),SPECIFIC("specific");
  public static final Set<ConditionBasedColoringScope> SCOPES = Collections.unmodifiableSet(
      EnumSet.of(ALL, SPECIFIC));
  
  private String name;

  private ConditionBasedColoringScope(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }
}

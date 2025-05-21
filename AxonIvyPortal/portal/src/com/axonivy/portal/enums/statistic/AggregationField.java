package com.axonivy.portal.enums.statistic;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.axonivy.portal.enums.HasCmsName;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AggregationField implements HasCmsName {
  STATE("state"),
  PRIORITY("priority"),
  CATEGORY("category"),
  IS_EXPIRED("isExpired"),
  WORKER_NAME("worker.name"),
  RESPONSIBLES_NAME("responsibles.name"),
  ACTIVATOR_NAME("activator.name"),
  CREATOR_NAME("creator.name"),
  BUSINESS_RUNTIME("businessRuntime"),
  START_TIMESTAMP("startTimestamp"),
  END_TIMESTAMP("endTimestamp"),
  EXPIRY_TIMESTAMP("expiryTimestamp"),
  CUSTOM_FIELD("customFields.*.*");

  private String name;

  private AggregationField(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }

  public static final Set<AggregationField> TASK_AGGREGATES = Collections
      .unmodifiableSet(EnumSet.of(STATE, PRIORITY, CATEGORY, WORKER_NAME, RESPONSIBLES_NAME, START_TIMESTAMP,
          END_TIMESTAMP, EXPIRY_TIMESTAMP, CUSTOM_FIELD));

  public static final Set<AggregationField> CASE_AGGREGATES = Collections.unmodifiableSet(
      EnumSet.of(STATE, CATEGORY, CREATOR_NAME, START_TIMESTAMP, END_TIMESTAMP, CUSTOM_FIELD));

  public static final Set<AggregationField> TASK_NUMBER_AGGREGATES = Collections
      .unmodifiableSet(EnumSet.of(STATE, PRIORITY, CATEGORY, BUSINESS_RUNTIME));

  public static final Set<AggregationField> CASE_NUMBER_AGGREGATES = Collections
      .unmodifiableSet(EnumSet.of(STATE, CATEGORY, BUSINESS_RUNTIME));
}

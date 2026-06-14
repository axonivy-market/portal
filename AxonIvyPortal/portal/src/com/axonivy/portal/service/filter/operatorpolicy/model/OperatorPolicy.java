package com.axonivy.portal.service.filter.operatorpolicy.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

public class OperatorPolicy implements Serializable {

  private static final long serialVersionUID = 1L;

  private final Map<String, Set<FilterOperator>> allowedOperatorsByField;

  private OperatorPolicy(Map<String, Set<FilterOperator>> allowedOperatorsByField) {
    this.allowedOperatorsByField = allowedOperatorsByField;
  }

  public static OperatorPolicy empty() {
    return new OperatorPolicy(Collections.emptyMap());
  }

  public static OperatorPolicy of(Map<String, ? extends Collection<FilterOperator>> allowedOperatorsByField) {
    Map<String, Set<FilterOperator>> immutableMap = allowedOperatorsByField.entrySet().stream()
        .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, entry -> Set.copyOf(entry.getValue())));
    return new OperatorPolicy(immutableMap);
  }

  public boolean hasPolicyForField(String field) {
    return allowedOperatorsByField.containsKey(field);
  }

  public Set<FilterOperator> getAllowedOperators(String field) {
    return allowedOperatorsByField.getOrDefault(field, Collections.emptySet());
  }

  public Map<String, Set<FilterOperator>> getAllowedOperatorsByField() {
    return allowedOperatorsByField;
  }
}

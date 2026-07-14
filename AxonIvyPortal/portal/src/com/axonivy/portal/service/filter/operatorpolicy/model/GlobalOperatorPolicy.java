package com.axonivy.portal.service.filter.operatorpolicy.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

public class GlobalOperatorPolicy implements Serializable {

  private static final long serialVersionUID = 1L;

  private final Set<FilterOperator> disabledOperators;

  private GlobalOperatorPolicy(Set<FilterOperator> disabledOperators) {
    this.disabledOperators = disabledOperators;
  }

  public static GlobalOperatorPolicy empty() {
    return new GlobalOperatorPolicy(Collections.emptySet());
  }

  public static GlobalOperatorPolicy of(Collection<FilterOperator> disabledOperators) {
    return new GlobalOperatorPolicy(Set.copyOf(disabledOperators));
  }

  public boolean isDisabled(FilterOperator operator) {
    return disabledOperators.contains(operator);
  }

  public Set<FilterOperator> getDisabledOperators() {
    return disabledOperators;
  }
}

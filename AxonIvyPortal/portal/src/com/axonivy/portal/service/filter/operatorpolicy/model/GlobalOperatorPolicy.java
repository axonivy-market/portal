package com.axonivy.portal.service.filter.operatorpolicy.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

public class GlobalOperatorPolicy implements Serializable {

  private static final long serialVersionUID = 1L;

  private final Set<FilterOperator> disabledOps;

  private GlobalOperatorPolicy(Set<FilterOperator> disabledOps) {
    this.disabledOps = disabledOps;
  }

  public static GlobalOperatorPolicy empty() {
    return new GlobalOperatorPolicy(Collections.emptySet());
  }

  public static GlobalOperatorPolicy of(Collection<FilterOperator> disabledOps) {
    return new GlobalOperatorPolicy(Set.copyOf(disabledOps));
  }

  public boolean isDisabled(FilterOperator op) {
    return disabledOps.contains(op);
  }

  public Set<FilterOperator> getDisabledOperators() {
    return disabledOps;
  }
}

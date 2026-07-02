package com.axonivy.portal.service.filter.operatorpolicy.provider;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.model.GlobalOperatorPolicy;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;

public class GlobalOperatorPolicyProvider implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final String GLOBAL_OPERATOR_POLICY_KEY = GlobalVariable.FILTER_OPERATOR_POLICY.getKey();

  public GlobalOperatorPolicy getPolicy() {
    return parse(Ivy.var().get(GLOBAL_OPERATOR_POLICY_KEY));
  }

  GlobalOperatorPolicy parse(String rawPolicy) {
    if (StringUtils.isBlank(rawPolicy)) {
      return GlobalOperatorPolicy.of(Set.of(FilterOperator.values()));
    }

    Set<FilterOperator> enabledOps = parseOperators(rawPolicy.trim().split(","));
    List<FilterOperator> disabledOps = Set.of(FilterOperator.values()).stream()
        .filter(op -> !enabledOps.contains(op)).collect(Collectors.toList());

    return disabledOps.isEmpty() ? GlobalOperatorPolicy.empty() : GlobalOperatorPolicy.of(disabledOps);
  }

  private Set<FilterOperator> parseOperators(String[] ops) {
    return Arrays.stream(ops).filter(StringUtils::isNotBlank)
        .map(FilterOperator::fromString).filter(Optional::isPresent).map(Optional::get)
        .collect(Collectors.toSet());
  }
}

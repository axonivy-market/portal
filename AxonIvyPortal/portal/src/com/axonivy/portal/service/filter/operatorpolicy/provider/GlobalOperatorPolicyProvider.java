package com.axonivy.portal.service.filter.operatorpolicy.provider;

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

public class GlobalOperatorPolicyProvider {

  private static final String GLOBAL_OPERATOR_POLICY_KEY = GlobalVariable.FILTER_OPERATOR_POLICY.getKey();

  public GlobalOperatorPolicy getPolicy() {
    return parse(Ivy.var().get(GLOBAL_OPERATOR_POLICY_KEY));
  }

  GlobalOperatorPolicy parse(String rawPolicy) {
    if (StringUtils.isBlank(rawPolicy)) {
      return GlobalOperatorPolicy.of(List.of(FilterOperator.values()));
    }

    Set<FilterOperator> enabledOperators = parseOperators(rawPolicy.trim().split(","));
    List<FilterOperator> disabledOperators = Set.of(FilterOperator.values()).stream()
        .filter(operator -> !enabledOperators.contains(operator)).collect(Collectors.toList());

    return disabledOperators.isEmpty() ? GlobalOperatorPolicy.empty() : GlobalOperatorPolicy.of(disabledOperators);
  }

  private Set<FilterOperator> parseOperators(String[] operators) {
    return Arrays.stream(operators).filter(StringUtils::isNotBlank)
        .map(FilterOperator::fromString).filter(Optional::isPresent).map(Optional::get)
        .collect(Collectors.toSet());
  }
}

package com.axonivy.portal.service.filter.operatorpolicy.service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.model.GlobalOperatorPolicy;
import com.axonivy.portal.service.filter.operatorpolicy.provider.GlobalOperatorPolicyProvider;

public class GlobalOperatorPolicyService implements Serializable {

  private static final long serialVersionUID = 1L;

  private final GlobalOperatorPolicyProvider globalPolicyProvider;

  public GlobalOperatorPolicyService() {
    this(new GlobalOperatorPolicyProvider());
  }

  GlobalOperatorPolicyService(GlobalOperatorPolicyProvider globalPolicyProvider) {
    this.globalPolicyProvider = globalPolicyProvider;
  }

  public GlobalOperatorPolicy readGlobalPolicy() {
    return globalPolicyProvider.getPolicy();
  }

  public boolean isOperatorGloballyEnabled(FilterOperator operator) {
    return operator != null && !globalPolicyProvider.getPolicy().isDisabled(operator);
  }

  public boolean isFilterAllowedByGlobalPolicy(DashboardFilter filter) {
    return filter != null && isOperatorGloballyEnabled(filter.getOperator());
  }

  public List<FilterOperator> keepGloballyEnabledOperators(List<FilterOperator> operators) {
    return operators.stream().filter(this::isOperatorGloballyEnabled).collect(Collectors.toList());
  }
}

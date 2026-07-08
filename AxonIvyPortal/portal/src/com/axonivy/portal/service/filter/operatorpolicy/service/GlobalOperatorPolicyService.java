package com.axonivy.portal.service.filter.operatorpolicy.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.model.GlobalOperatorPolicy;
import com.axonivy.portal.service.filter.operatorpolicy.provider.GlobalOperatorPolicyProvider;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;

public class GlobalOperatorPolicyService implements Serializable {

  private static final long serialVersionUID = 1L;

  protected final GlobalOperatorPolicy globalPolicy;
  protected final BaselineOperatorService baseOpService;

  public GlobalOperatorPolicyService() {
    this.globalPolicy = new GlobalOperatorPolicyProvider().getPolicy();
    this.baseOpService = new BaselineOperatorService();
  }

  public BaselineOperatorService getBaselineOperatorService() {
    return this.baseOpService;
  }

  public boolean isOperatorGloballyEnabled(FilterOperator op) {
    return !globalPolicy.isDisabled(op);
  }

  public boolean isFilterAllowedByGlobalPolicy(DashboardFilter filter) {
    return !globalPolicy.isDisabled(filter.getOperator());
  }

  public List<FilterOperator> keepGloballyEnabledOperators(List<FilterOperator> ops) {
    return ops.stream()
        .filter(op -> !globalPolicy.isDisabled(op))
        .collect(Collectors.toList());
  }

  public List<ColumnModel> getColumnsWithGloballyEnabledOperators(List<ColumnModel> columns) {
    return columns.stream()
        .filter(col -> !keepGloballyEnabledOperators(baseOpService.resolveForColumn(col)).isEmpty())
        .collect(Collectors.toList());
  }

  public List<FilterOperator> resolveEffectiveOperators(DashboardFilter filter) {
    return keepGloballyEnabledOperators(baseOpService.resolveForFilter(filter));
  }

  public Optional<FilterOperator> findFirstEnabledOp(DashboardFilter filter, FilterOperator defaultOp) {
    List<FilterOperator> effective = resolveEffectiveOperators(filter);
    return effective.contains(defaultOp) ? Optional.of(defaultOp) : effective.stream().findFirst();
  }
}

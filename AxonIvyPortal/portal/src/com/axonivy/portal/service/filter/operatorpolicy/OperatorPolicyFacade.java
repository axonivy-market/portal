package com.axonivy.portal.service.filter.operatorpolicy;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.model.GlobalOperatorPolicy;
import com.axonivy.portal.service.filter.operatorpolicy.model.OperatorPolicy;
import com.axonivy.portal.service.filter.operatorpolicy.service.BaselineOperatorService;
import com.axonivy.portal.service.filter.operatorpolicy.service.GlobalOperatorPolicyService;
import com.axonivy.portal.service.filter.operatorpolicy.service.OperatorPolicyGovernanceService;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;

public class OperatorPolicyFacade implements Serializable {

  private static final long serialVersionUID = 1L;

  private final GlobalOperatorPolicyService globalOperatorPolicyService;
  private final OperatorPolicyGovernanceService operatorPolicyGovernanceService;
  private final BaselineOperatorService baselineOperatorService;

  public OperatorPolicyFacade() {
    this(new GlobalOperatorPolicyService(), new OperatorPolicyGovernanceService(), new BaselineOperatorService());
  }

  OperatorPolicyFacade(GlobalOperatorPolicyService globalOperatorPolicyService,
      OperatorPolicyGovernanceService operatorPolicyGovernanceService,
      BaselineOperatorService baselineOperatorService) {
    this.globalOperatorPolicyService = globalOperatorPolicyService;
    this.operatorPolicyGovernanceService = operatorPolicyGovernanceService;
    this.baselineOperatorService = baselineOperatorService;
  }

  public List<FilterOperator> getAvailableOperators(ColumnModel column) {
    return baselineOperatorService.resolveForColumn(column);
  }

  public List<FilterOperator> keepGloballyEnabledOperators(List<FilterOperator> operators) {
    return globalOperatorPolicyService.keepGloballyEnabledOperators(operators);
  }

  public List<ColumnModel> getColumnsWithGloballyEnabledOperators(List<ColumnModel> columns) {
    return columns.stream()
        .filter(col -> !keepGloballyEnabledOperators(getAvailableOperators(col)).isEmpty())
        .collect(Collectors.toList());
  }

  public OperatorPolicy buildWidgetOperatorPolicy(List<ColumnModel> columns) {
    return operatorPolicyGovernanceService.buildWidgetOperatorPolicy(columns);
  }

  public List<DashboardFilter> keepFiltersAllowedByPolicy(List<DashboardFilter> filters, OperatorPolicy widgetPolicy) {
    return operatorPolicyGovernanceService.keepFiltersAllowedByPolicy(filters, widgetPolicy);
  }

  public GlobalOperatorPolicy readGlobalPolicy() {
    return globalOperatorPolicyService.readGlobalPolicy();
  }

  public List<FilterOperator> resolveEffectiveOperatorsByPolicy(DashboardFilter filter, GlobalOperatorPolicy globalPolicy, OperatorPolicy widgetPolicy) {
    return operatorPolicyGovernanceService.resolveEffectiveOperatorsByPolicy(filter, globalPolicy, widgetPolicy);
  }
}

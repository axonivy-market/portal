package com.axonivy.portal.service.filter.operatorpolicy;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.provider.GlobalOperatorPolicyProvider;
import com.axonivy.portal.util.filter.field.FilterField;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;

public class GlobalOperatorPolicyService {

  private static GlobalOperatorPolicyService instance;

  public static GlobalOperatorPolicyService getInstance() {
    if (instance == null) {
      instance = new GlobalOperatorPolicyService();
    }
    return instance;
  }

  private final GlobalOperatorPolicyProvider globalPolicyProvider;
  private final BaselineOperatorService baselineOperatorService;

  public GlobalOperatorPolicyService() {
    this.globalPolicyProvider = new GlobalOperatorPolicyProvider();
    this.baselineOperatorService = new BaselineOperatorService();
  }

  public boolean isOperatorGloballyEnabled(FilterOperator operator) {
    return operator != null && !globalPolicyProvider.getPolicy().isDisabled(operator);
  }

  public boolean isFilterAllowedByGlobalPolicy(DashboardFilter filter) {
    return filter != null && isOperatorGloballyEnabled(filter.getOperator());
  }

  public FilterOperator getFirstEnabledOperator(String field, FilterOperator defaultOp) {
    var policy = globalPolicyProvider.getPolicy();
    if (!policy.isDisabled(defaultOp)) {
      return defaultOp;
    }
    List<FilterOperator> operators = baselineOperatorService.resolveByField(field);
    return operators.stream()
        .filter(op -> op != null && !policy.isDisabled(op))
        .findFirst()
        .orElse(null);
  }

  public List<FilterOperator> keepGloballyEnabledOperators(List<FilterOperator> operators) {
    if (CollectionUtils.isEmpty(operators)) {
      return List.of();
    }
    var policy = globalPolicyProvider.getPolicy();
    return operators.stream()
        .filter(op -> op != null && !policy.isDisabled(op))
        .collect(Collectors.toList());
  }

  public List<DashboardFilter> keepGloballyEnabledFilters(List<DashboardFilter> filters) {
    if (CollectionUtils.isEmpty(filters)) {
      return List.of();
    }
    var policy = globalPolicyProvider.getPolicy();
    return filters.stream()
        .filter(f -> f != null && f.getOperator() != null && !policy.isDisabled(f.getOperator()))
        .collect(Collectors.toList());
  }

  public boolean hasAnyGloballyEnabledOperator(FilterField field) {
    return hasAnyGloballyEnabledOperator(baselineOperatorService.resolveByField(field.getName()));
  }

  public boolean hasAnyGloballyEnabledOperator(ColumnModel column) {
    return hasAnyGloballyEnabledOperator(baselineOperatorService.resolveForColumn(column));
  }

  private boolean hasAnyGloballyEnabledOperator(List<FilterOperator> operators) {
    if (CollectionUtils.isEmpty(operators)) {
        return false;
    }
    var policy = globalPolicyProvider.getPolicy();
    return operators.stream().anyMatch(operator -> operator != null && !policy.isDisabled(operator));
  }
}

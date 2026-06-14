package com.axonivy.portal.service.filter.operatorpolicy.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.model.GlobalOperatorPolicy;
import com.axonivy.portal.service.filter.operatorpolicy.model.OperatorPolicy;
import com.axonivy.portal.service.filter.operatorpolicy.provider.WidgetOperatorPolicyProvider;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;

public class OperatorPolicyGovernanceService implements Serializable {

  private static final long serialVersionUID = 1L;

  private final GlobalOperatorPolicyService globalPolicyService;
  private final WidgetOperatorPolicyProvider widgetPolicyProvider;
  private final BaselineOperatorService baselineOperatorService;

  public OperatorPolicyGovernanceService() {
    this(new GlobalOperatorPolicyService(), new WidgetOperatorPolicyProvider(), new BaselineOperatorService());
  }

  OperatorPolicyGovernanceService(GlobalOperatorPolicyService globalPolicyService,
      WidgetOperatorPolicyProvider widgetPolicyProvider, BaselineOperatorService baselineOperatorService) {
    this.globalPolicyService = globalPolicyService;
    this.widgetPolicyProvider = widgetPolicyProvider;
    this.baselineOperatorService = baselineOperatorService;
  }

  public OperatorPolicy buildWidgetOperatorPolicy(List<ColumnModel> columns) {
    return widgetPolicyProvider.getPolicy(columns);
  }

  private boolean isFilterAllowedByPolicy(DashboardFilter filter, OperatorPolicy widgetPolicy) {
    if (!globalPolicyService.isFilterAllowedByGlobalPolicy(filter)) {
      return false;
    }

    if (!widgetPolicy.hasPolicyForField(filter.getField())) {
      return true;
    }

    return widgetPolicy.getAllowedOperators(filter.getField()).contains(filter.getOperator());
  }

  public List<DashboardFilter> keepFiltersAllowedByPolicy(List<DashboardFilter> filters, OperatorPolicy widgetPolicy) {
    if (CollectionUtils.isEmpty(filters)) {
      return new ArrayList<>();
    }

    return filters.stream().filter(Objects::nonNull)
        .filter(filter -> isFilterAllowedByPolicy(filter, widgetPolicy))
        .collect(Collectors.toList());
  }

  public List<FilterOperator> resolveEffectiveOperatorsByPolicy(DashboardFilter filter, GlobalOperatorPolicy globalPolicy, OperatorPolicy widgetPolicy) {
    List<FilterOperator> defaultOperators = baselineOperatorService.resolveForFilter(filter);
    if (CollectionUtils.isEmpty(defaultOperators)) {
      return Collections.emptyList();
    }

    List<FilterOperator> globalEffective = defaultOperators.stream()
        .filter(operator -> !globalPolicy.isDisabled(operator)).collect(Collectors.toList());

    if (!widgetPolicy.hasPolicyForField(filter.getField())) {
      return globalEffective;
    }

    Set<FilterOperator> widgetAllowed = widgetPolicy.getAllowedOperators(filter.getField());
    return globalEffective.stream().filter(widgetAllowed::contains).collect(Collectors.toList());
  }

}


package com.axonivy.portal.service.filter.operatorpolicy.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.model.OperatorPolicy;
import com.axonivy.portal.service.filter.operatorpolicy.provider.WidgetOperatorPolicyProvider;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;

public class OperatorPolicyService extends GlobalOperatorPolicyService {

  private static final long serialVersionUID = 1L;

  private final OperatorPolicy widgetPolicy;

  public OperatorPolicyService(List<ColumnModel> columnModels) {
    super();
    this.widgetPolicy = new WidgetOperatorPolicyProvider().getPolicy(columnModels);
  }

  public OperatorPolicy readWidgetPolicy() {
    return this.widgetPolicy;
  }

  private boolean hasNoWidgetPolicyForField(String field) {
    return !widgetPolicy.hasPolicyForField(field);
  }

  private boolean isFilterAllowedByPolicy(DashboardFilter filter) {
    if (!this.isFilterAllowedByGlobalPolicy(filter)) {
      return false;
    }

    if (this.hasNoWidgetPolicyForField(filter.getField())) {
      return true;
    }

    return widgetPolicy.getAllowedOperators(filter.getField()).contains(filter.getOperator());
  }

  public List<DashboardFilter> keepFiltersAllowedByPolicy(List<DashboardFilter> filters) {
    return filters.stream()
        .filter(Objects::nonNull)
        .filter(this::isFilterAllowedByPolicy)
        .collect(Collectors.toList());
  }

  @Override
  public List<FilterOperator> resolveEffectiveOperators(DashboardFilter filter) {
    List<FilterOperator> globalEffective = super.resolveEffectiveOperators(filter);

    if (this.hasNoWidgetPolicyForField(filter.getField())) {
      return globalEffective;
    }

    Set<FilterOperator> widgetAllowed = widgetPolicy.getAllowedOperators(filter.getField());
    return globalEffective.stream()
        .filter(widgetAllowed::contains)
        .collect(Collectors.toList());
  }
}

package com.axonivy.portal.service.complexfilter.operatorpolicy;

import java.util.List;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;

final class OperatorPolicyFixtures {

  private OperatorPolicyFixtures() {
  }

  static DashboardFilter filterForField(String field) {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(field);
    return filter;
  }

  static DashboardFilter filter(String field, FilterOperator operator) {
    DashboardFilter filter = filterForField(field);
    filter.setOperator(operator);
    return filter;
  }

  static DashboardFilter filterWithOperator(FilterOperator operator) {
    DashboardFilter filter = new DashboardFilter();
    filter.setOperator(operator);
    return filter;
  }

  static ColumnModel filterableColumn(String field) {
    ColumnModel column = new ColumnModel();
    column.setField(field);
    column.setEnableFilter(true);
    return column;
  }

  static ColumnModel filterableColumn(String field, List<FilterOperator> allowedOperators) {
    ColumnModel column = filterableColumn(field);
    column.setAllowedOperators(allowedOperators);
    return column;
  }
}
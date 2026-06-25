package com.axonivy.portal.service.filter.operatorpolicy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class BaselineOperatorService {

  private static final Map<String, Set<FilterOperator>> FIELD_OPERATORS_MAP;

  static {
    Map<String, Set<FilterOperator>> map = new HashMap<>();
    map.put(DashboardStandardTaskColumn.STATE.getField(), FilterOperator.STATE_OPERATORS);
    map.put(DashboardStandardTaskColumn.PRIORITY.getField(), FilterOperator.PRIORITY_OPERATORS);
    map.put(DashboardStandardTaskColumn.RESPONSIBLE.getField(), FilterOperator.RESPONSIBLE_OPERATORS);
    map.put(DashboardStandardTaskColumn.WORKER.getField(), FilterOperator.WORKER_OPERATORS);
    map.put(DashboardStandardTaskColumn.CATEGORY.getField(), FilterOperator.CATEGORY_OPERATORS);
    map.put(DashboardStandardTaskColumn.APPLICATION.getField(), FilterOperator.APPLICATION_OPERATORS);
    map.put(DashboardStandardTaskColumn.ID.getField(), FilterOperator.ID_OPERATORS);
    map.put(DashboardStandardTaskColumn.BUSINESS_CASE_ID.getField(), FilterOperator.ID_OPERATORS);
    map.put(DashboardStandardTaskColumn.TECHNICAL_CASE_ID.getField(), FilterOperator.ID_OPERATORS);
    map.put(DashboardStandardTaskColumn.CREATED.getField(), FilterOperator.CREATED_DATE_OPERATORS);
    map.put(DashboardStandardTaskColumn.COMPLETED.getField(), FilterOperator.DATE_OPERATORS);
    map.put(DashboardStandardTaskColumn.NAME.getField(), FilterOperator.TEXT_OPERATORS);
    map.put(DashboardStandardTaskColumn.DESCRIPTION.getField(), FilterOperator.TEXT_OPERATORS);
    map.put(DashboardStandardTaskColumn.EXPIRY.getField(), FilterOperator.DATE_OPERATORS);
    map.put(DashboardStandardCaseColumn.CREATOR.getField(), FilterOperator.CREATOR_OPERATORS);
    FIELD_OPERATORS_MAP = Collections.unmodifiableMap(map);
  }

  public List<FilterOperator> resolveForColumn(ColumnModel column) {
    if (column == null || !column.canFilter() || StringUtils.isBlank(column.getField())) {
      return List.of();
    }

    if (column.getHasCmsValues()) {
      return List.of(FilterOperator.CONTAINS);
    }

    List<FilterOperator> byField = resolveByField(column.getField());
    if (!byField.isEmpty()) {
      return byField;
    }

    return resolveByFormat(column.getFormat());
  }

  public List<FilterOperator> resolveByField(String field) {
    Set<FilterOperator> operators = FIELD_OPERATORS_MAP.get(field);
    return operators == null ? List.of() : new ArrayList<>(operators);
  }

  private List<FilterOperator> resolveByFormat(DashboardColumnFormat format) {
    if (format == null) {
      return List.of();
    }

    return switch (format) {
      case NUMBER -> new ArrayList<>(FilterOperator.NUMBER_OPERATORS);
      case TIMESTAMP -> new ArrayList<>(FilterOperator.DATE_OPERATORS);
      case STRING, TEXT, CUSTOM, CUSTOM_CASE, CUSTOM_BUSINESS_CASE ->
        new ArrayList<>(FilterOperator.TEXT_OPERATORS);
    };
  }
}

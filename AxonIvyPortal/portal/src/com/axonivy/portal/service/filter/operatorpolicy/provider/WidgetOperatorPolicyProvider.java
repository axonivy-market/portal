package com.axonivy.portal.service.filter.operatorpolicy.provider;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.model.OperatorPolicy;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;

public class WidgetOperatorPolicyProvider implements Serializable {

  private static final long serialVersionUID = 1L;

  public OperatorPolicy getPolicy(List<ColumnModel> columns) {
    if (CollectionUtils.isEmpty(columns)) {
      return OperatorPolicy.empty();
    }

    Map<String, List<FilterOperator>> policyByField = new LinkedHashMap<>();
    columns.stream().filter(column -> column.getAllowedOperators() != null)
        .forEach(column -> policyByField.put(column.getField(), column.getAllowedOperators()));

    return policyByField.isEmpty() ? OperatorPolicy.empty() : OperatorPolicy.of(policyByField);
  }
}

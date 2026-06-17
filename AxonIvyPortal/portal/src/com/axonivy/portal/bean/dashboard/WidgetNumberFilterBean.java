package com.axonivy.portal.bean.dashboard;

import static com.axonivy.portal.enums.dashboard.filter.FilterOperator.EQUAL;
import static com.axonivy.portal.enums.dashboard.filter.FilterOperator.NOT_EQUAL;
import static com.axonivy.portal.enums.dashboard.filter.FilterOperator.LESS;
import static com.axonivy.portal.enums.dashboard.filter.FilterOperator.LESS_OR_EQUAL;
import static com.axonivy.portal.enums.dashboard.filter.FilterOperator.GREATER;
import static com.axonivy.portal.enums.dashboard.filter.FilterOperator.GREATER_OR_EQUAL;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.annotation.PostConstruct;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.GlobalOperatorPolicyService;

@ManagedBean
@ViewScoped
public class WidgetNumberFilterBean implements Serializable {

  private static final long serialVersionUID = 6088715427600445713L;

  private List<FilterOperator> resolvedOperators;
  private static List<FilterOperator> SINGLE_FILTER_OPERATORS = Arrays.asList(EQUAL, NOT_EQUAL, LESS, LESS_OR_EQUAL,
      GREATER, GREATER_OR_EQUAL);

  @PostConstruct
  public void initOperators() {
    resolvedOperators = GlobalOperatorPolicyService.getInstance().keepGloballyEnabledOperators(FilterOperator.NUMBER_OPERATORS.stream().toList());
  }

  public List<FilterOperator> getOperators() {
    return resolvedOperators;
  }

  public boolean renderSingleNumberFilter(DashboardFilter filter) {
    return SINGLE_FILTER_OPERATORS.contains(Optional.ofNullable(filter).map(DashboardFilter::getOperator).orElse(null));
  }

  public void onChangeOperator(DashboardFilter filter) {
    filter.setFrom(null);
    filter.setTo(null);
    filter.setValue(null);
  }
}

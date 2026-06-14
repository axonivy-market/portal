package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.OperatorPolicyFacade;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomString;
import com.axonivy.portal.util.filter.field.task.custom.caze.TaskFilterCaseFieldCustomString;

import ch.ivy.addon.portalkit.util.PortalCustomFieldUtils;

@ManagedBean
@ViewScoped
public class WidgetTextFilterBean implements Serializable {

  private static final long serialVersionUID = 3218284115015773931L;

  private final OperatorPolicyFacade operatorPolicyFacade = new OperatorPolicyFacade();
  private List<FilterOperator> cachedStatisticOperators;
  private List<FilterOperator> cachedTextOperators;

  @PostConstruct
  public void initOperators() {
    cachedStatisticOperators = operatorPolicyFacade.keepGloballyEnabledOperators(
        FilterOperator.STATISTIC_TEXT_OPERATORS.stream().toList());
    cachedTextOperators = operatorPolicyFacade.keepGloballyEnabledOperators(
        FilterOperator.TEXT_OPERATORS.stream().toList());
  }

  public void onChangeOperator(DashboardFilter filter) {
  }

  public boolean isShowTextListPanel(DashboardFilter filter) {
    if (!filter.isTextField()) {
      return false;
    }

    for (FilterOperator operator : getEnabledTextOperators()) {
      if (operator == FilterOperator.EMPTY || operator == FilterOperator.NOT_EMPTY) {
        continue;
      }
      if (operator == filter.getOperator()) {
        return true;
      }
    }
    
    for (FilterOperator operator : getStatisticOperators()) {
      if (operator == filter.getOperator()) {
        return true;
      }
    }

    return false;
  }
  
  public boolean isCustomFieldWithCms(DashboardFilter filter) {
    if (filter.getFilterField() instanceof CaseFilterFieldCustomString || filter.getFilterField() instanceof TaskFilterCaseFieldCustomString) {
      return PortalCustomFieldUtils.isSupportMultiLanguageCaseField(filter.getField());
    }
    return PortalCustomFieldUtils.isSupportMultiLanguageTaskField(filter.getField());
  }

  public List<FilterOperator> getStatisticOperators() {
    return cachedStatisticOperators;
  }

  private List<FilterOperator> getEnabledTextOperators() {
    return cachedTextOperators;
  }
}

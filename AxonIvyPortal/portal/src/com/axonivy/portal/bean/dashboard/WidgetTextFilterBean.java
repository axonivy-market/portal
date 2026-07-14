package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.annotation.PostConstruct;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.GlobalOperatorPolicyService;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomString;
import com.axonivy.portal.util.filter.field.task.custom.caze.TaskFilterCaseFieldCustomString;

import ch.ivy.addon.portalkit.util.PortalCustomFieldUtils;

@ManagedBean
@ViewScoped
public class WidgetTextFilterBean implements Serializable {

  private static final long serialVersionUID = 3218284115015773931L;

  private List<FilterOperator> resolvedOperators;
  private List<FilterOperator> resolvedOperatorsForCustomFieldWithCms;

  @PostConstruct
  public void initOperators() {
    resolvedOperators = GlobalOperatorPolicyService.getInstance().keepGloballyEnabledOperators(FilterOperator.TEXT_OPERATORS.stream().toList());
    resolvedOperatorsForCustomFieldWithCms = GlobalOperatorPolicyService.getInstance().keepGloballyEnabledOperators(List.of(FilterOperator.CONTAINS));
  }

  public List<FilterOperator> getOperators() {
    return resolvedOperators;
  }
  
  public List<FilterOperator> getOperatorsForCustomFieldWithCms() {
    return resolvedOperatorsForCustomFieldWithCms;
  }

  public void onChangeOperator(@SuppressWarnings("unused") DashboardFilter filter) {
  }

  public boolean isShowTextListPanel(DashboardFilter filter) {
    if (!filter.isTextField()) {
      return false;
    }

    for (FilterOperator operator : getOperators()) {
      if (operator == FilterOperator.EMPTY || operator == FilterOperator.NOT_EMPTY) {
        continue;
      }
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

}

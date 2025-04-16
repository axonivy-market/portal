package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomString;
import com.axonivy.portal.util.filter.field.task.custom.caze.TaskFilterCaseFieldCustomString;

import ch.ivy.addon.portalkit.util.PortalCustomFieldUtils;

@ManagedBean
@ViewScoped
public class WidgetTextFilterBean implements Serializable {

  private static final long serialVersionUID = 3218284115015773931L;

  private static List<FilterOperator> operators = FilterOperator.TEXT_OPERATORS.stream().toList();
  private static List<FilterOperator> operatorsForCustomFieldWithCms = Collections.unmodifiableSet(EnumSet.of(FilterOperator.CONTAINS)).stream().toList();
  private static List<FilterOperator> statisticOperators = FilterOperator.STATISTIC_TEXT_OPERATORS.stream().toList();

  public List<FilterOperator> getOperators() {
    return operators;
  }
  
  public List<FilterOperator> getOperatorsForCustomFieldWithCms() {
    return operatorsForCustomFieldWithCms;
  }

  public void onChangeOperator(@SuppressWarnings("unused") DashboardFilter filter) {
  }

  public boolean isShowTextListPanel(DashboardFilter filter) {
    if (!filter.isTextField()) {
      return false;
    }

    for (FilterOperator operator : operators) {
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

  public List<FilterOperator> getStatisticOperators() {
    return statisticOperators;
  }
}

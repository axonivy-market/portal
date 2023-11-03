package com.axonivy.portal.bean.dashboard.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterType;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.util.CaseUtils;

public abstract class AbstractCaseWidgetFilterBean implements Serializable {

  private static final long serialVersionUID = 4672539720688592048L;

  protected CaseDashboardWidget widget;
  protected List<DashboardStandardCaseColumn> filterTypes;

  public void preRender(CaseDashboardWidget widget) {
    this.widget = widget;
    this.widget.setInConfiguration(true);
    initFilterTypes();
  }

  public List<DashboardStandardCaseColumn> getFilterTypes() {
    return filterTypes;
  }

  public void onSelectFilter(DashboardFilter filter) {
    DashboardStandardCaseColumn columnEnum = DashboardStandardCaseColumn.findBy(filter.getField());
    if (columnEnum == null) {
      return;
    }

    switch (columnEnum) {
      case CREATED -> initDateFilter(filter);
      case FINISHED -> initDateFilter(filter);
      case NAME -> initTextFilter(filter);
      case DESCRIPTION -> initTextFilter(filter);
      case STATE -> initStateFilter(filter);
      default -> {}
    };
  }

  private void initDateFilter(DashboardFilter filter) {
    filter.setType(FilterType.DATE);
  }

  private void initTextFilter(DashboardFilter filter) {
    filter.setType(FilterType.TEXT);
    filter.setOperator(FilterOperator.CONTAINS);
    filter.setTexts(new ArrayList<>());
  }

  private void initStateFilter(DashboardFilter filter) {
    filter.setType(FilterType.STATE);
    filter.setOperator(FilterOperator.IN);
    filter.setTexts(new ArrayList<>());
    filter.setCaseStateOptions(getValidStates());
  }

  public static List<String> getValidStates() {
    List<String> validStates = CaseUtils.getValidStates().stream().map(businessState -> businessState.name()).toList();
    return validStates;
  }

  protected abstract void initFilterTypes();

  public abstract void removeFilter(CaseDashboardWidget widget, DashboardFilter filter);

  public abstract void addNewFilter(CaseDashboardWidget widget);
}

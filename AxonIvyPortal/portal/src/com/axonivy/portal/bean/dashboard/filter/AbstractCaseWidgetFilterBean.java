package com.axonivy.portal.bean.dashboard.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterType;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;

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
      case NAME -> initTextFilter(filter);
      default -> {}
    };
  }

  private void initDateFilter(DashboardFilter filter) {
    filter.setType(FilterType.DATE);
  }

  private void initTextFilter(DashboardFilter filter) {
    filter.setType(FilterType.TEXT);
    filter.setTexts(new ArrayList<>());
  }

  protected abstract void initFilterTypes();

  public abstract void removeFilter(CaseDashboardWidget widget, DashboardFilter filter);

  public abstract void addNewFilter(CaseDashboardWidget widget);
}

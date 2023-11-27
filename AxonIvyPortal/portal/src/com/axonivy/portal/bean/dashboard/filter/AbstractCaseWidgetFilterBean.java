package com.axonivy.portal.bean.dashboard.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterType;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;

public abstract class AbstractCaseWidgetFilterBean implements Serializable {

  private static final long serialVersionUID = 4672539720688592048L;

  protected CaseDashboardWidget widget;
  protected List<FilterField> filterTypes;

  public void preRender(CaseDashboardWidget widget) {
    this.widget = widget;
    this.widget.setInConfiguration(true);
    initFilterTypes();
  }

  public List<FilterField> getFilterTypes() {
    return filterTypes;
  }

  public void onSelectFilter(DashboardFilter filter) {
    String field = filter.getField();
    FilterField filterField = FilterFieldFactory.findBy(field);
    filterField.initFilter(filter);
    switch (field) {
      case "startTimestamp" -> initDateFilter(filter);
      case "endTimestamp" -> initDateFilter(filter);
      case "name" -> initTextFilter(filter);
      case "description" -> initTextFilter(filter);
      default -> {
      }
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

  protected abstract void initFilterTypes();

  public abstract void removeFilter(CaseDashboardWidget widget, DashboardFilter filter);

  public abstract void addNewFilter(CaseDashboardWidget widget);
}

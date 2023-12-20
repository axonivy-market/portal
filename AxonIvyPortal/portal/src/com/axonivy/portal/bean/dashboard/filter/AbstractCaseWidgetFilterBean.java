package com.axonivy.portal.bean.dashboard.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomNumber;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;

public abstract class AbstractCaseWidgetFilterBean implements Serializable {

  private static final long serialVersionUID = 4672539720688592048L;

  protected CaseDashboardWidget widget;
  protected List<FilterField> filterTypes;

  public void preRender(CaseDashboardWidget widget) {
    this.widget = widget;
    this.widget.setInConfiguration(true);
    initFilterTypes();
    initFilters();
  }

  private void initFilterTypes() {
    this.filterTypes = FilterFieldFactory.getStandardFilterableFields();

    // Add custom fields which are selected by user.
    this.widget.getFilterableColumns()
      .stream().filter(col -> col.getType() == DashboardColumnType.CUSTOM)
      .forEach(customColumn -> this.filterTypes.add(FilterFieldFactory.findBy(customColumn.getField())));
  }

  private void initFilters() {
    if (CollectionUtils.isEmpty(Optional.ofNullable(this.widget)
        .map(CaseDashboardWidget::getFilters).get())) {
      return;
    }

    for (DashboardFilter filter : this.widget.getFilters()) {
      FilterField filterField = FilterFieldFactory.findBy(Optional.ofNullable(filter).map(DashboardFilter::getField).orElse(""));
      if (filterField != null) {
        filterField.initFilter(filter);
      }
    }
  }

  public List<FilterField> getFilterTypes() {
    return filterTypes;
  }

  public void onSelectFilter(DashboardFilter filter) {
    String field = Optional.ofNullable( filter.getFilterField()).map(FilterField::getName).orElse(null);
    FilterField filterField = FilterFieldFactory.findBy(field);

    if (filterField == null) {
      return;
    }

    filterField.addNewFilter(filter);
    initCustomFieldNumberPattern(filter, field, filterField);
  }

  private void initCustomFieldNumberPattern(DashboardFilter filter, String field, FilterField filterField) {
    if (filterField instanceof CaseFilterFieldCustomNumber) {
      ColumnModel column = widget.getFilterableColumns().stream()
          .filter(col -> col.getField().contentEquals(field)).findFirst().orElse(new ColumnModel());
      filter.setNumberPattern(column.getPattern());
    }
  }

  public abstract void removeFilter(CaseDashboardWidget widget, DashboardFilter filter);

  public abstract void addNewFilter(CaseDashboardWidget widget);
}

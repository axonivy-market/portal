package com.axonivy.portal.bean.dashboard.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.TaskFilterFieldFactory;
import com.axonivy.portal.util.filter.field.task.custom.TaskFilterFieldCustomNumber;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;

public abstract class AbstractTaskWidgetFilterBean implements Serializable {

  private static final long serialVersionUID = -7603133281018922252L;

  protected TaskDashboardWidget widget;
  protected List<FilterField> filterFields;

  public void preRender(TaskDashboardWidget widget) {
    this.widget = widget;
    this.widget.setInConfiguration(true);
    initFilterFields();
    initFilters();
  }

  private void initFilterFields() {
    this.filterFields = new ArrayList<>();
    this.filterFields.add(TaskFilterFieldFactory.getDefaultFilterField());
    this.filterFields
        .addAll(TaskFilterFieldFactory.getStandardFilterableFields());

    // Add custom fields which are selected by user.
    this.widget.getFilterableColumns().stream().filter(column -> column.getType() != DashboardColumnType.STANDARD)
        .map(column -> TaskFilterFieldFactory.findBy(column.getField(), column.getType())).filter(Objects::nonNull)
        .forEach(this.filterFields::add);
  }

  private void initFilters() {
    if (CollectionUtils.isEmpty(Optional.ofNullable(this.widget).map(TaskDashboardWidget::getFilters).get())) {
      return;
    }

    // If the filter available in the filter list, initialize it
    for (DashboardFilter filter : this.widget.getFilters()) {
      if (isFilterAvaliable(filter)) {
        FilterField filterField = TaskFilterFieldFactory
            .findBy(Optional.ofNullable(filter).map(DashboardFilter::getField).orElse(""),
                Optional.ofNullable(filter).map(DashboardFilter::getFilterType).orElse(null));
        if (filterField != null) {
          filterField.initFilter(filter);
        }
      }
    }
  }

  /**
   * Check if the filter is existing in the filter list or not
   * 
   * @param filter
   */
  private boolean isFilterAvaliable(DashboardFilter filter) {
    return Optional.ofNullable(filter).map(DashboardFilter::getField).isPresent() && filterFields.stream()
        .filter(field -> filter.getField().contentEquals(filter.getField())).findFirst().isPresent();
  }

  public List<FilterField> getFilterFields() {
    return filterFields;
  }

  public void onSelectFilter(DashboardFilter filter) {
    String field = Optional.ofNullable(filter).map(DashboardFilter::getFilterField).map(FilterField::getName)
        .orElse(StringUtils.EMPTY);

    FilterField filterField = TaskFilterFieldFactory.findBy(field);

    if (filterField.getName()
        .contentEquals(BaseFilter.DEFAULT)) {
      filterField.addNewFilter(filter);
      return;
    }

    filter.getFilterField().addNewFilter(filter);
    initCustomFieldNumberPattern(filter, field, filter.getFilterField());
  }

  private void initCustomFieldNumberPattern(DashboardFilter filter, String field, FilterField filterField) {
    if (filterField instanceof TaskFilterFieldCustomNumber) {
      ColumnModel column = widget.getFilterableColumns().stream().filter(col -> col.getField().contentEquals(field))
          .findFirst().orElse(new ColumnModel());
      filter.setNumberPattern(column.getPattern());
    }
  }

  public abstract void removeFilter(TaskDashboardWidget widget, DashboardFilter filter);

  public abstract void addNewFilter(TaskDashboardWidget widget);

  public List<SecurityMemberDTO> completeCreators(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE).stream()
        .filter(SecurityMemberDTO::isUser).collect(Collectors.toList());
  }

  public List<SecurityMemberDTO> completeOwners(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
  }

  public abstract void resetTaskWidgetFilter(TaskDashboardWidget widget);

  public abstract void resetCaseWidgetFilter(CaseDashboardWidget widget);

  public void resetFilter(DashboardWidget widget) {
    if (DashboardWidgetType.TASK.getLabel().equals(widget.getType().getLabel())) {
      resetTaskWidgetFilter((TaskDashboardWidget) widget);
      return;
    }
    resetCaseWidgetFilter((CaseDashboardWidget) widget);
  }

  public void updateWidgetFilterBeforeApply(DashboardWidget widget) {
    if (widget instanceof TaskDashboardWidget) {
      TaskDashboardWidget taskWidget = (TaskDashboardWidget) widget;
      removeEmptyFilters(taskWidget.getFilters());
    } else {
      CaseDashboardWidget caseWidget = (CaseDashboardWidget) widget;
      removeEmptyFilters(caseWidget.getFilters());
    }
  }

  private void removeEmptyFilters(List<DashboardFilter> filters) {
    if (CollectionUtils.isNotEmpty(filters)) {
      filters.removeAll(filters.stream()
          .filter(filter -> StringUtils.isBlank(filter.getField()))
          .collect(Collectors.toList()));
    }
  }
}

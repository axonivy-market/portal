package com.axonivy.portal.bean.dashboard.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCreator;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomNumber;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;

public abstract class AbstractCaseWidgetFilterBean implements Serializable {

  private static final long serialVersionUID = 4672539720688592048L;

  protected CaseDashboardWidget widget;
  protected List<FilterField> filterFields;

  public void preRender(CaseDashboardWidget widget) {
    this.widget = widget;
    this.widget.setInConfiguration(true);
    initFilterFields();
    initFilters();
  }

  private void initFilterFields() {
    this.filterFields = new ArrayList<>();
    this.filterFields.add(FilterFieldFactory.getDefaultFilterField());
    this.filterFields.addAll(FilterFieldFactory.getStandardFilterableFields());

    // Remove Case Creator filter when the HideCaseCreator variable is enable.
    this.filterFields = filterFields.stream().filter(
        field -> !(field instanceof CaseFilterFieldCreator && GlobalSettingService.getInstance().isHideCaseCreator()))
        .collect(Collectors.toList());

    // Add custom fields which are selected by user.
    this.widget.getFilterableColumns().stream().filter(col -> col.getType() == DashboardColumnType.CUSTOM)
        .forEach(customColumn -> this.filterFields.add(FilterFieldFactory.findCustomFieldBy(customColumn.getField())));
  }

  private void initFilters() {
    if (CollectionUtils.isEmpty(Optional.ofNullable(this.widget).map(CaseDashboardWidget::getFilters).get())) {
      return;
    }

    // If the filter available in the filter list, initialize it
    for (DashboardFilter filter : this.widget.getFilters()) {
      if (isFilterAvaliable(filter)) {
        FilterField filterField =
            FilterFieldFactory.findBy(Optional.ofNullable(filter).map(DashboardFilter::getField).orElse(""));
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
    FilterField filterField = FilterFieldFactory.findBy(field);

    if (filterField.getName().contentEquals(BaseFilter.DEFAULT)) {
      filterField.addNewFilter(filter);
      return;
    }

    filterField.addNewFilter(filter);
    initCustomFieldNumberPattern(filter, field, filterField);
  }

  private void initCustomFieldNumberPattern(DashboardFilter filter, String field, FilterField filterField) {
    if (filterField instanceof CaseFilterFieldCustomNumber) {
      ColumnModel column = widget.getFilterableColumns().stream().filter(col -> col.getField().contentEquals(field))
          .findFirst().orElse(new ColumnModel());
      filter.setNumberPattern(column.getPattern());
    }
  }

  public abstract void removeFilter(CaseDashboardWidget widget, DashboardFilter filter);

  public abstract void addNewFilter(CaseDashboardWidget widget);

  public List<SecurityMemberDTO> completeCreators(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE).stream()
        .filter(SecurityMemberDTO::isUser).collect(Collectors.toList());
  }

  public List<SecurityMemberDTO> completeOwners(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
  }
}

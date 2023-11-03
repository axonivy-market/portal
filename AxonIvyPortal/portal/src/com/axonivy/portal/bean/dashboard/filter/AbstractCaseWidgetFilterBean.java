package com.axonivy.portal.bean.dashboard.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterType;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;

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
      case CREATOR -> initCreatorFilter(filter);
      case CATEGORY -> initCategoryFilter(filter);
      case APPLICATION -> initApplicationFilter(filter);
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

  private void initCreatorFilter(DashboardFilter filter) {
    filter.setType(FilterType.CREATOR);
    filter.setOperator(FilterOperator.CURRENT_USER);
    filter.setTexts(new ArrayList<>());
  }

  private void initCategoryFilter(DashboardFilter filter) {
    filter.setType(FilterType.CATEGORY);
    filter.setOperator(FilterOperator.IN);
    filter.setTexts(new ArrayList<>());
  }

  private void initApplicationFilter(DashboardFilter filter) {
    filter.setType(FilterType.APPLICATION);
    filter.setOperator(FilterOperator.IN);
    filter.setTexts(new ArrayList<>());
  }

  private void initStateFilter(DashboardFilter filter) {
    filter.setType(FilterType.STATE);
    filter.setOperator(FilterOperator.IN);
    filter.setTexts(new ArrayList<>());
  }

  protected abstract void initFilterTypes();

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

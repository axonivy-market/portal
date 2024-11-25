package com.axonivy.portal.util.filter.field.caze;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.caze.category.CategoryContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.category.CategoryInOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.category.CategoryNoCategoryOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CaseFilterFieldCategory extends FilterField {

  public CaseFilterFieldCategory() {
    super(DashboardStandardCaseColumn.CATEGORY.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardCaseColumn.CATEGORY.getLabel();
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setField(getName());

  }

  @Override
  public void addNewFilter(DashboardFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.IN);
    filter.setValues(new ArrayList<>());
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case IN -> CategoryInOperatorHandler.getInstance().buildInQuery(filter);
      case NOT_IN -> CategoryInOperatorHandler.getInstance().buildNotInQuery(filter);
      case CONTAINS -> CategoryContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> CategoryContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case NO_CATEGORY -> CategoryNoCategoryOperatorHandler.getInstance().buildQuery();
      default -> null;
    };
  }
  
  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return null;
  }

}

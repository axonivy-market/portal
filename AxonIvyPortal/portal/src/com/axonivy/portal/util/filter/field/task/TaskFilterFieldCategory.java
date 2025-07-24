package com.axonivy.portal.util.filter.field.task;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.task.category.CategoryContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.category.CategoryInOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.category.CategoryNoCategoryOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldCategory extends FilterField {

  public TaskFilterFieldCategory() {
    super(DashboardStandardTaskColumn.CATEGORY.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardTaskColumn.CATEGORY.getLabel();
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setField(getName());
    if (this.label == null) {
      setLabel(filter.getLabel());
    }
  }

  @Override
  public void addNewFilter(DashboardFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.IN);
    filter.setValues(new ArrayList<>());
  }

  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
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
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
}

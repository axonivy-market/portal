package com.axonivy.portal.util.filter.field.task;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.task.responsible.ReponsibleContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.responsible.ReponsibleCurrentUserOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.responsible.ResponsibleInOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldResponsible extends FilterField {

  public TaskFilterFieldResponsible() {
    super(DashboardStandardTaskColumn.RESPONSIBLE.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardTaskColumn.RESPONSIBLE.getLabel();
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
    filter.setOperator(FilterOperator.CURRENT_USER);
    filter.setValues(new ArrayList<>());
  }

  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case IN -> ResponsibleInOperatorHandler.getInstance().buildInQuery(filter);
      case NOT_IN -> ResponsibleInOperatorHandler.getInstance().buildNotInQuery(filter);
      case CURRENT_USER -> ReponsibleCurrentUserOperatorHandler.getInstance().buildQuery();
      case CONTAINS -> ReponsibleContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }

}
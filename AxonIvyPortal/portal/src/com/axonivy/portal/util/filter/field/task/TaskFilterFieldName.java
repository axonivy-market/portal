package com.axonivy.portal.util.filter.field.task;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.task.name.NameContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.name.NameEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.name.NameIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.name.NameIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.name.NameStartWithOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldName extends FilterField {

  public TaskFilterFieldName() {
    super(DashboardStandardTaskColumn.NAME.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardTaskColumn.NAME.getLabel();
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setFilterFormat(FilterFormat.TEXT);
    filter.setField(getName());
  }

  @Override
  public void addNewFilter(DashboardFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.CONTAINS);
    filter.setValues(new ArrayList<>());
  }

  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case CONTAINS -> NameContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> NameContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case IS -> NameIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> NameIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case START_WITH -> NameStartWithOperatorHandler.getInstance().buildStartWithQuery(filter);
      case NOT_START_WITH -> NameStartWithOperatorHandler.getInstance().buildNotStartWithQuery(filter);
      case END_WITH -> NameEndWithOperatorHandler.getInstance().buildEndWithQuery(filter);
      case NOT_END_WITH -> NameEndWithOperatorHandler.getInstance().buildNotEndWithQuery(filter);
      case EMPTY -> NameIsEmptyOperatorHandler.getInstance().buildIsEmptyQuery(filter);
      case NOT_EMPTY -> NameIsEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
      default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
}

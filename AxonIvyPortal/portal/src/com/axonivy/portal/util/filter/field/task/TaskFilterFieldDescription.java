package com.axonivy.portal.util.filter.field.task;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.task.description.DescriptionContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.description.DescriptionEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.description.DescriptionIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.description.DescriptionIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.description.DescriptionStartWithOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldDescription extends FilterField {

  public TaskFilterFieldDescription() {
    super(DashboardStandardTaskColumn.DESCRIPTION.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardTaskColumn.DESCRIPTION.getLabel();
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
      case CONTAINS -> DescriptionContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> DescriptionContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case IS -> DescriptionIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> DescriptionIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case START_WITH -> DescriptionStartWithOperatorHandler.getInstance().buildStartWithQuery(filter);
      case NOT_START_WITH -> DescriptionStartWithOperatorHandler.getInstance().buildNotStartWithQuery(filter);
      case END_WITH -> DescriptionEndWithOperatorHandler.getInstance().buildEndWithQuery(filter);
      case NOT_END_WITH -> DescriptionEndWithOperatorHandler.getInstance().buildNotEndWithQuery(filter);
      case EMPTY -> DescriptionIsEmptyOperatorHandler.getInstance().buildIsEmptyQuery(filter);
      case NOT_EMPTY -> DescriptionIsEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
      default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
}

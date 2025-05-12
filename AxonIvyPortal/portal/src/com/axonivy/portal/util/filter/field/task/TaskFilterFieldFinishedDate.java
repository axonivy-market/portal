package com.axonivy.portal.util.filter.field.task;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.task.finisheddate.FinishedDateAfterOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.finisheddate.FinishedDateBeforeOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.finisheddate.FinishedDateBetweenOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.finisheddate.FinishedDateCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.finisheddate.FinishedDateEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.finisheddate.FinishedDateIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.finisheddate.FinishedDateNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.finisheddate.FinishedDateTodayOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.finisheddate.FinishedDateYesterdayOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldFinishedDate extends FilterField {

  public TaskFilterFieldFinishedDate() {
    super(DashboardStandardTaskColumn.COMPLETED.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardTaskColumn.COMPLETED.getLabel();
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setFilterFormat(FilterFormat.DATE);
    filter.setField(getName());
  }

  @Override
  public void addNewFilter(DashboardFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.TODAY);
    filter.setFromDate(null);
    filter.setToDate(null);
  }

  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case BETWEEN -> FinishedDateBetweenOperatorHandler.getInstance().buildBetweenQuery(filter);
      case NOT_BETWEEN -> FinishedDateBetweenOperatorHandler.getInstance().buildNotBetweenQuery(filter);
      case BEFORE -> FinishedDateBeforeOperatorHandler.getInstance().buildQuery(filter);
      case AFTER -> FinishedDateAfterOperatorHandler.getInstance().buildQuery(filter);
      case TODAY -> FinishedDateTodayOperatorHandler.getInstance().buildQuery();
      case YESTERDAY -> FinishedDateYesterdayOperatorHandler.getInstance().buildQuery();
      case CURRENT -> FinishedDateCurrentPeriodOperatorHandler.getInstance().buildQuery(filter);
      case LAST -> FinishedDateNumberOfPeriodsOperatorHandler.getInstance().buildLastPeriodQuery(filter);
      case NEXT -> FinishedDateNumberOfPeriodsOperatorHandler.getInstance().buildNextPeriodQuery(filter);
      case IS -> FinishedDateIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> FinishedDateIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case EMPTY -> FinishedDateEmptyOperatorHandler.getInstance().buildEmptyQuery();
      case NOT_EMPTY -> FinishedDateEmptyOperatorHandler.getInstance().buildNotEmptyQuery();
      default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
}

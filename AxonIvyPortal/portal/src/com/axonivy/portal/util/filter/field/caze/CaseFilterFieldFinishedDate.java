package com.axonivy.portal.util.filter.field.caze;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateAfterOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateBeforeOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateBetweenOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateTodayOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateYesterdayOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeAfterOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeBeforeOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeBetweenOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeIsOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeTodayYesterdayOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CaseFilterFieldFinishedDate extends FilterField {

  public CaseFilterFieldFinishedDate() {
    super(DashboardStandardCaseColumn.FINISHED.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardCaseColumn.FINISHED.getLabel();
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
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
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
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return null;
  }
  
  @Override
  public String generateCaseFilter(DashboardFilter filter) {
    return switch (filter.getOperator()) {
    case BETWEEN -> DatetimeBetweenOperatorHandler.getInstance().buildBetweenFilter(filter);
    case AFTER -> DatetimeAfterOperatorHandler.getInstance().buildFilter(filter);
    case BEFORE -> DatetimeBeforeOperatorHandler.getInstance().buildFilter(filter);
    case CURRENT -> DatetimeCurrentPeriodOperatorHandler.getInstance().buildFilter(filter);
    case LAST -> DatetimeNumberOfPeriodsOperatorHandler.getInstance().buildLastPeriodFilter(filter);
    case NEXT -> DatetimeNumberOfPeriodsOperatorHandler.getInstance().buildNextPeriodFilter(filter);
    case TODAY -> DatetimeTodayYesterdayOperatorHandler.getInstance().buildTodayFilter(filter);
    case YESTERDAY -> DatetimeTodayYesterdayOperatorHandler.getInstance().buildYesterdayFilter(filter);
    case IS -> DatetimeIsOperatorHandler.getInstance().buildIsFilter(filter);
    default -> null;
    };
  }

}

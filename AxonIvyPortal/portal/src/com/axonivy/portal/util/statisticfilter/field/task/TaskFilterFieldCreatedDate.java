package com.axonivy.portal.util.statisticfilter.field.task;

import com.axonivy.portal.dto.statistic.StatisticFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.statisticfilter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeAfterOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeBeforeOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeBetweenOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeTodayYesterdayOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class TaskFilterFieldCreatedDate extends FilterField{

  public TaskFilterFieldCreatedDate() {
    super(DashboardStandardTaskColumn.CREATED.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardTaskColumn.CREATED.getLabel();
  }

  @Override
  public void initFilter(StatisticFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setFilterFormat(FilterFormat.DATE);
    filter.setField(getName());
  }

  @Override
  public void addNewFilter(StatisticFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.TODAY);
    filter.setFromDate(null);
    filter.setToDate(null);
  }

  @Override
  public String generateStringFilter(StatisticFilter filter) {
    return switch (filter.getOperator()) {
      case BETWEEN -> DatetimeBetweenOperatorHandler.getInstance().buildBetweenQuery(filter);
      case NOT_BETWEEN -> DatetimeBetweenOperatorHandler.getInstance().buildNotBetweenQuery(filter);
      case AFTER -> DatetimeAfterOperatorHandler.getInstance().buildQuery(filter);
      case BEFORE -> DatetimeBeforeOperatorHandler.getInstance().buildQuery(filter);
      case CURRENT -> DatetimeCurrentPeriodOperatorHandler.getInstance().buildQuery(filter);
      case LAST -> DatetimeNumberOfPeriodsOperatorHandler.getInstance().buildLastPeriodQuery(filter);
      case NEXT -> DatetimeNumberOfPeriodsOperatorHandler.getInstance().buildNextPeriodQuery(filter);
      case TODAY -> DatetimeTodayYesterdayOperatorHandler.getInstance().buildTodayQuery(filter);
      case YESTERDAY -> DatetimeTodayYesterdayOperatorHandler.getInstance().buildYesterdayQuery(filter);
      default -> null;
    };
  }

}

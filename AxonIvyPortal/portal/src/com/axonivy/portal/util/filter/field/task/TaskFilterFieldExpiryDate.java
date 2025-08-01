package com.axonivy.portal.util.filter.field.task;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.task.expirydate.ExpiryDateAfterOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.expirydate.ExpiryDateBeforeOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.expirydate.ExpiryDateBetweenOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.expirydate.ExpiryDateCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.expirydate.ExpiryDateEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.expirydate.ExpiryDateIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.expirydate.ExpiryDateNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.expirydate.ExpiryDateTodayOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.expirydate.ExpiryDateYesterdayOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeAfterOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeBeforeOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeBetweenOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeIsOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeTodayYesterdayOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldExpiryDate extends FilterField {

  public TaskFilterFieldExpiryDate() {
    super(DashboardStandardTaskColumn.EXPIRY.getField());
  }

  @Override
  public String getLabel() {
    if (StringUtils.isBlank(this.label)) {
      return DashboardStandardTaskColumn.EXPIRY.getLabel();
    }
    return this.label;
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setFilterFormat(FilterFormat.DATE);
    filter.setField(getName());
    if (this.label == null) {
      setLabel(filter.getLabel());
    }
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
      case BETWEEN -> ExpiryDateBetweenOperatorHandler.getInstance().buildBetweenQuery(filter);
      case NOT_BETWEEN -> ExpiryDateBetweenOperatorHandler.getInstance().buildNotBetweenQuery(filter);
      case BEFORE -> ExpiryDateBeforeOperatorHandler.getInstance().buildQuery(filter);
      case AFTER -> ExpiryDateAfterOperatorHandler.getInstance().buildQuery(filter);
      case TODAY -> ExpiryDateTodayOperatorHandler.getInstance().buildQuery();
      case YESTERDAY -> ExpiryDateYesterdayOperatorHandler.getInstance().buildQuery();
      case CURRENT -> ExpiryDateCurrentPeriodOperatorHandler.getInstance().buildQuery(filter);
      case LAST -> ExpiryDateNumberOfPeriodsOperatorHandler.getInstance().buildLastPeriodQuery(filter);
      case NEXT -> ExpiryDateNumberOfPeriodsOperatorHandler.getInstance().buildNextPeriodQuery(filter);
      case IS -> ExpiryDateIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> ExpiryDateIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case EMPTY -> ExpiryDateEmptyOperatorHandler.getInstance().buildEmptyQuery();
      case NOT_EMPTY -> ExpiryDateEmptyOperatorHandler.getInstance().buildNotEmptyQuery();
      default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
  
  @Override
  public String generateTaskFilter(DashboardFilter filter) {
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

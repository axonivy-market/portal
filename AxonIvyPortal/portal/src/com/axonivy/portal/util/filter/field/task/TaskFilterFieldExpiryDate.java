package com.axonivy.portal.util.filter.field.task;

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
    return DashboardStandardTaskColumn.EXPIRY.getLabel();
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
}

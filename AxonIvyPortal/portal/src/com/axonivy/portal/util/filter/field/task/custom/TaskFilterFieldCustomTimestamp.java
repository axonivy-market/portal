package com.axonivy.portal.util.filter.field.task.custom;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.CustomFilterField;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTimestampAfterOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTimestampBeforeOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTimestampBetweenOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTimestampCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTimestampEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTimestampIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTimestampNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTimestampTodayOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTimestampYesterdayOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeAfterOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeBeforeOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeBetweenOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeIsOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.datetime.DatetimeTodayYesterdayOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldCustomTimestamp extends CustomFilterField {

  private ICustomFieldMeta customField;

  public TaskFilterFieldCustomTimestamp() {
  }

  public TaskFilterFieldCustomTimestamp(ICustomFieldMeta customField) {
    super(customField.name(), FilterFormat.DATE);
    this.customField = customField;
  }

  @Override
  public String getLabel() {
    return customField.label();
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.CUSTOM);
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
    case BETWEEN -> CustomTimestampBetweenOperatorHandler.getInstance().buildBetweenQuery(filter);
    case NOT_BETWEEN -> CustomTimestampBetweenOperatorHandler.getInstance().buildNotBetweenQuery(filter);
    case BEFORE -> CustomTimestampBeforeOperatorHandler.getInstance().buildQuery(filter);
    case AFTER -> CustomTimestampAfterOperatorHandler.getInstance().buildQuery(filter);
    case TODAY -> CustomTimestampTodayOperatorHandler.getInstance().buildQuery(filter);
    case YESTERDAY -> CustomTimestampYesterdayOperatorHandler.getInstance().buildQuery(filter);
    case CURRENT -> CustomTimestampCurrentPeriodOperatorHandler.getInstance().buildQuery(filter);
    case LAST -> CustomTimestampNumberOfPeriodsOperatorHandler.getInstance().buildLastPeriodQuery(filter);
    case NEXT -> CustomTimestampNumberOfPeriodsOperatorHandler.getInstance().buildNextPeriodQuery(filter);
    case IS -> CustomTimestampIsOperatorHandler.getInstance().buildIsQuery(filter);
    case IS_NOT -> CustomTimestampIsOperatorHandler.getInstance().buildIsNotQuery(filter);
    case EMPTY -> CustomTimestampEmptyOperatorHandler.getInstance().buildEmptyQuery(filter);
    case NOT_EMPTY -> CustomTimestampEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
    default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
  
  @Override
  public String generateStringFilter(DashboardFilter filter) {
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
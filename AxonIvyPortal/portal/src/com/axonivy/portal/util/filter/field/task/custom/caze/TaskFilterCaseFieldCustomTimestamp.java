package com.axonivy.portal.util.filter.field.task.custom.caze;

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

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterCaseFieldCustomTimestamp extends CustomFilterField {

  private ICustomFieldMeta customField;

  public TaskFilterCaseFieldCustomTimestamp() {
  }

  public TaskFilterCaseFieldCustomTimestamp(ICustomFieldMeta customField) {
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
    filter.setFilterType(DashboardColumnType.CUSTOM_CASE);
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
    case BETWEEN -> CustomTimestampBetweenOperatorHandler.getInstance().buildBetweenQueryByCase(filter);
    case NOT_BETWEEN -> CustomTimestampBetweenOperatorHandler.getInstance().buildNotBetweenQueryByCase(filter);
    case BEFORE -> CustomTimestampBeforeOperatorHandler.getInstance().buildQueryByCase(filter);
    case AFTER -> CustomTimestampAfterOperatorHandler.getInstance().buildQueryByCase(filter);
    case TODAY -> CustomTimestampTodayOperatorHandler.getInstance().buildQueryByCase(filter);
    case YESTERDAY -> CustomTimestampYesterdayOperatorHandler.getInstance().buildQueryByCase(filter);
    case CURRENT -> CustomTimestampCurrentPeriodOperatorHandler.getInstance().buildQueryByCase(filter);
    case LAST -> CustomTimestampNumberOfPeriodsOperatorHandler.getInstance().buildLastPeriodQueryByCase(filter);
    case NEXT -> CustomTimestampNumberOfPeriodsOperatorHandler.getInstance().buildNextPeriodQueryByCase(filter);
    case IS -> CustomTimestampIsOperatorHandler.getInstance().buildIsQueryByCase(filter);
    case IS_NOT -> CustomTimestampIsOperatorHandler.getInstance().buildIsNotQueryByCase(filter);
    case EMPTY -> CustomTimestampEmptyOperatorHandler.getInstance().buildEmptyQueryByCase(filter);
    case NOT_EMPTY -> CustomTimestampEmptyOperatorHandler.getInstance().buildNotEmptyQueryByCase(filter);
    default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }

}
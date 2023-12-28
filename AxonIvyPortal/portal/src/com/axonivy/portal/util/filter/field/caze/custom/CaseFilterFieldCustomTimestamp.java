package com.axonivy.portal.util.filter.field.caze.custom;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.CustomFilterField;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampAfterOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampBeforeOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampBetweenOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampTodayOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampYesterdayOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseFilterFieldCustomTimestamp extends CustomFilterField {

  private ICustomFieldMeta customField;

  public CaseFilterFieldCustomTimestamp() {}

  public CaseFilterFieldCustomTimestamp(ICustomFieldMeta customField) {
    super(customField.name(), FilterFormat.DATE);
    this.customField = customField;
  }

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
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
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
}

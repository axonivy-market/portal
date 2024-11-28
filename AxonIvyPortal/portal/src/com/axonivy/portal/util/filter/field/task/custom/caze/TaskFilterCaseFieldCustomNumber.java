package com.axonivy.portal.util.filter.field.task.custom.caze;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.CustomFilterField;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomNumberBetweenOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomNumberEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomNumberEqualOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomNumberGreaterOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomNumberLessOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterCaseFieldCustomNumber extends CustomFilterField {

  private ICustomFieldMeta customField;

  public TaskFilterCaseFieldCustomNumber() {
  }

  public TaskFilterCaseFieldCustomNumber(ICustomFieldMeta customField) {
    super(customField.name(), FilterFormat.NUMBER);
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
    filter.setFilterFormat(FilterFormat.NUMBER);
    filter.setField(getName());
  }

  @Override
  public void addNewFilter(DashboardFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.NOT_EMPTY);
    filter.setFrom(null);
    filter.setTo(null);
    filter.setValue(null);
  }

  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
    case EQUAL -> CustomNumberEqualOperatorHandler.getInstance().buildEqualQueryByCase(filter);
    case NOT_EQUAL -> CustomNumberEqualOperatorHandler.getInstance().buildNotEqualQueryByCase(filter);
    case LESS -> CustomNumberLessOperatorHandler.getInstance().buildLessQueryByCase(filter);
    case LESS_OR_EQUAL -> CustomNumberLessOperatorHandler.getInstance().buildLessOrEqualQueryByCase(filter);
    case GREATER -> CustomNumberGreaterOperatorHandler.getInstance().buildGreaterQueryByCase(filter);
    case GREATER_OR_EQUAL -> CustomNumberGreaterOperatorHandler.getInstance().buildGreaterOrEqualQueryByCase(filter);
    case BETWEEN -> CustomNumberBetweenOperatorHandler.getInstance().buildEqualQueryByCase(filter);
    case NOT_BETWEEN -> CustomNumberBetweenOperatorHandler.getInstance().buildNotEqualQueryByCase(filter);
    case EMPTY -> CustomNumberEmptyOperatorHandler.getInstance().buildEmptyQueryByCase(filter);
    case NOT_EMPTY -> CustomNumberEmptyOperatorHandler.getInstance().buildNotEmptyQueryByCase(filter);
    default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
}
package com.axonivy.portal.util.filter.field.task.custom.caze;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.CustomFilterField;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomStringContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomStringEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomStringIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomStringIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomStringStartWithOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterCaseFieldCustomString extends CustomFilterField {

  private ICustomFieldMeta customField;

  public TaskFilterCaseFieldCustomString() {
  }

  public TaskFilterCaseFieldCustomString(ICustomFieldMeta customField) {
    super(customField.name(), FilterFormat.STRING);
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
    filter.setFilterFormat(FilterFormat.STRING);
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
    case CONTAINS -> CustomStringContainsOperatorHandler.getInstance().buildContainsQueryByCase(filter);
    case NOT_CONTAINS -> CustomStringContainsOperatorHandler.getInstance().buildNotContainsQueryByCase(filter);
    case IS -> CustomStringIsOperatorHandler.getInstance().buildIsQueryByCase(filter);
    case IS_NOT -> CustomStringIsOperatorHandler.getInstance().buildIsNotQueryByCase(filter);
    case START_WITH -> CustomStringStartWithOperatorHandler.getInstance().buildStartWithQueryByCase(filter);
    case NOT_START_WITH -> CustomStringStartWithOperatorHandler.getInstance().buildNotStartWithQueryByCase(filter);
    case END_WITH -> CustomStringEndWithOperatorHandler.getInstance().buildEndWithQueryByCase(filter);
    case NOT_END_WITH -> CustomStringEndWithOperatorHandler.getInstance().buildNotEndWithQueryByCase(filter);
    case EMPTY -> CustomStringIsEmptyOperatorHandler.getInstance().buildIsEmptyQueryByCase(filter);
    case NOT_EMPTY -> CustomStringIsEmptyOperatorHandler.getInstance().buildNotEmptyQueryByCase(filter);
    default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }

}
package com.axonivy.portal.util.filter.field.task.custom.caze;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.CustomFilterField;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTextContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTextEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTextIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTextIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.task.customfield.CustomTextStartWithOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterCaseFieldCustomText extends CustomFilterField {

  private ICustomFieldMeta customField;

  public TaskFilterCaseFieldCustomText() {
  }

  public TaskFilterCaseFieldCustomText(ICustomFieldMeta customField) {
    super(customField.name(), FilterFormat.TEXT);
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
    filter.setFilterFormat(FilterFormat.TEXT);
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
    case CONTAINS -> CustomTextContainsOperatorHandler.getInstance().buildContainsQueryByCase(filter);
    case NOT_CONTAINS -> CustomTextContainsOperatorHandler.getInstance().buildNotContainsQueryByCase(filter);
    case IS -> CustomTextIsOperatorHandler.getInstance().buildIsQueryByCase(filter);
    case IS_NOT -> CustomTextIsOperatorHandler.getInstance().buildIsNotQueryByCase(filter);
    case START_WITH -> CustomTextStartWithOperatorHandler.getInstance().buildStartWithQueryByCase(filter);
    case NOT_START_WITH -> CustomTextStartWithOperatorHandler.getInstance().buildNotStartWithQueryByCase(filter);
    case END_WITH -> CustomTextEndWithOperatorHandler.getInstance().buildEndWithQueryByCase(filter);
    case NOT_END_WITH -> CustomTextEndWithOperatorHandler.getInstance().buildNotEndWithQueryByCase(filter);
    case EMPTY -> CustomTextIsEmptyOperatorHandler.getInstance().buildIsEmptyQueryByCase(filter);
    case NOT_EMPTY -> CustomTextIsEmptyOperatorHandler.getInstance().buildNotEmptyQueryByCase(filter);
    default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }

}
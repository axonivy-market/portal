package com.axonivy.portal.util.filter.field.caze.custom;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.CustomFilterField;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringInOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringStartWithOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CaseFilterFieldCustomString extends CustomFilterField {

  private ICustomFieldMeta customField;

  public CaseFilterFieldCustomString() {}

  public CaseFilterFieldCustomString(ICustomFieldMeta customField) {
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
    filter.setFilterType(DashboardColumnType.CUSTOM);
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
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case CONTAINS -> CustomStringContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> CustomStringContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case IS -> CustomStringIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> CustomStringIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case START_WITH -> CustomStringStartWithOperatorHandler.getInstance().buildStartWithQuery(filter);
      case NOT_START_WITH -> CustomStringStartWithOperatorHandler.getInstance().buildNotStartWithQuery(filter);
      case END_WITH -> CustomStringEndWithOperatorHandler.getInstance().buildEndWithQuery(filter);
      case NOT_END_WITH -> CustomStringEndWithOperatorHandler.getInstance().buildNotEndWithQuery(filter);
      case EMPTY -> CustomStringIsEmptyOperatorHandler.getInstance().buildIsEmptyQuery(filter);
      case NOT_EMPTY -> CustomStringIsEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
      case IN -> CustomStringInOperatorHandler.getInstance().buildQuery(filter);
      default -> null;
    };
  }
  
  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return null;
  }

}

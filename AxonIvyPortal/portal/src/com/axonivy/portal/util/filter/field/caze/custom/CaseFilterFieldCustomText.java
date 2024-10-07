package com.axonivy.portal.util.filter.field.caze.custom;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.CustomFilterField;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextStartWithOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CaseFilterFieldCustomText extends CustomFilterField {

  private ICustomFieldMeta customField;

  public CaseFilterFieldCustomText() {}

  public CaseFilterFieldCustomText(ICustomFieldMeta customField) {
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
    filter.setFilterType(DashboardColumnType.CUSTOM);
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
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case CONTAINS -> CustomTextContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> CustomTextContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case IS -> CustomTextIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> CustomTextIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case START_WITH -> CustomTextStartWithOperatorHandler.getInstance().buildStartWithQuery(filter);
      case NOT_START_WITH -> CustomTextStartWithOperatorHandler.getInstance().buildNotStartWithQuery(filter);
      case END_WITH -> CustomTextEndWithOperatorHandler.getInstance().buildEndWithQuery(filter);
      case NOT_END_WITH -> CustomTextEndWithOperatorHandler.getInstance().buildNotEndWithQuery(filter);
      case EMPTY -> CustomTextIsEmptyOperatorHandler.getInstance().buildIsEmptyQuery(filter);
      case NOT_EMPTY -> CustomTextIsEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
      default -> null;
    };
  }
  
  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return null;
  }

}
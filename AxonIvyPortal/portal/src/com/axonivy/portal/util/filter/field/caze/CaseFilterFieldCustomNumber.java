package com.axonivy.portal.util.filter.field.caze;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterType;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringStartWithOperatorHandler;

import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseFilterFieldCustomNumber extends FilterField {

  private ICustomFieldMeta customField;

  public CaseFilterFieldCustomNumber() {}

  public CaseFilterFieldCustomNumber(ICustomFieldMeta customField) {
    super(customField.name());
    this.customField = customField;
  }

  public String getLabel() {
    return customField.label();
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setType(FilterType.NUMBER);
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
      default -> null;
    };
  }
}

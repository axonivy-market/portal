package com.axonivy.portal.util.filter.field.caze;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterType;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.caze.customfield.StringContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.StringEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.StringIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.StringIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.StringStartWithOperatorHandler;

import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseFilterFieldCustomString extends FilterField {

  public CaseFilterFieldCustomString() {}

  public CaseFilterFieldCustomString(ICustomFieldMeta customField) {
    super(customField.name());
  }

  public String getLabel() {
    return getName();
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setType(FilterType.TEXT);
    filter.setOperator(FilterOperator.CONTAINS);
    filter.setTexts(new ArrayList<>());
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case CONTAINS -> StringContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> StringContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case IS -> StringIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> StringIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case START_WITH -> StringStartWithOperatorHandler.getInstance().buildStartWithQuery(filter);
      case NOT_START_WITH -> StringStartWithOperatorHandler.getInstance().buildNotStartWithQuery(filter);
      case END_WITH -> StringEndWithOperatorHandler.getInstance().buildEndWithQuery(filter);
      case NOT_END_WITH -> StringEndWithOperatorHandler.getInstance().buildNotEndWithQuery(filter);
      case EMPTY -> StringIsEmptyOperatorHandler.getInstance().buildIsEmptyQuery(filter);
      case NOT_EMPTY -> StringIsEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
      default -> null;
    };
  }
}

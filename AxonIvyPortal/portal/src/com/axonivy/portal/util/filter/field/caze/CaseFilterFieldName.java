package com.axonivy.portal.util.filter.field.caze;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterType;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.caze.name.NameContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.name.NameEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.name.NameIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.name.NameIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.name.NameStartWithOperatorHandler;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseFilterFieldName extends FilterField {

  public CaseFilterFieldName() {}

  public CaseFilterFieldName(String name) {
    super(name);
  }

  public String getLabel() {
    return Ivy.cms().co(String.format("/Labels/Enums/DashboardStandardCaseColumn/%s", getName()));
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
      case CONTAINS -> NameContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> NameContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case IS -> NameIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> NameIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case START_WITH -> NameStartWithOperatorHandler.getInstance().buildStartWithQuery(filter);
      case NOT_START_WITH -> NameStartWithOperatorHandler.getInstance().buildNotStartWithQuery(filter);
      case END_WITH -> NameEndWithOperatorHandler.getInstance().buildEndWithQuery(filter);
      case NOT_END_WITH -> NameEndWithOperatorHandler.getInstance().buildNotEndWithQuery(filter);
      case EMPTY -> NameIsEmptyOperatorHandler.getInstance().buildIsEmptyQuery(filter);
      case NOT_EMPTY -> NameIsEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
      default -> null;
    };
  }
}

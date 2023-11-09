package com.axonivy.portal.util.filter.field.caze;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterType;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.caze.description.DescriptionContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.description.DescriptionEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.description.DescriptionIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.description.DescriptionIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.description.DescriptionStartWithOperatorHandler;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseFilterFieldDescription extends FilterField {

  public CaseFilterFieldDescription() {}

  public CaseFilterFieldDescription(String name) {
    super(name);
  }

  public String getLabel() {
    return Ivy.cms()
        .co(String.format("/Labels/Enums/DashboardStandardCaseColumn/%s", StringUtils.upperCase(getName())));
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
      case CONTAINS -> DescriptionContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> DescriptionContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case IS -> DescriptionIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> DescriptionIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case START_WITH -> DescriptionStartWithOperatorHandler.getInstance().buildStartWithQuery(filter);
      case NOT_START_WITH -> DescriptionStartWithOperatorHandler.getInstance().buildNotStartWithQuery(filter);
      case END_WITH -> DescriptionEndWithOperatorHandler.getInstance().buildEndWithQuery(filter);
      case NOT_END_WITH -> DescriptionEndWithOperatorHandler.getInstance().buildNotEndWithQuery(filter);
      case EMPTY -> DescriptionIsEmptyOperatorHandler.getInstance().buildIsEmptyQuery(filter);
      case NOT_EMPTY -> DescriptionIsEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
      default -> null;
    };
  }
}

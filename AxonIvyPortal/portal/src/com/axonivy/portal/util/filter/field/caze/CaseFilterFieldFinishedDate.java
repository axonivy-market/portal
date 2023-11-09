package com.axonivy.portal.util.filter.field.caze;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterType;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateAfterOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateBeforeOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateBetweenOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateTodayOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateYesterdayOperatorHandler;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseFilterFieldFinishedDate extends FilterField {

  public CaseFilterFieldFinishedDate() {}

  public CaseFilterFieldFinishedDate(String name) {
    super(name);
  }

  public String getLabel() {
    return Ivy.cms().co(String.format("/Labels/Enums/DashboardStandardCaseColumn/%s", getName()));
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setType(FilterType.DATE);
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case BETWEEN -> FinishedDateBetweenOperatorHandler.getInstance().buildBetweenQuery(filter);
      case NOT_BETWEEN -> FinishedDateBetweenOperatorHandler.getInstance().buildNotBetweenQuery(filter);
      case BEFORE -> FinishedDateBeforeOperatorHandler.getInstance().buildQuery(filter);
      case AFTER -> FinishedDateAfterOperatorHandler.getInstance().buildQuery(filter);
      case TODAY -> FinishedDateTodayOperatorHandler.getInstance().buildQuery();
      case YESTERDAY -> FinishedDateYesterdayOperatorHandler.getInstance().buildQuery();
      case CURRENT -> FinishedDateCurrentPeriodOperatorHandler.getInstance().buildQuery(filter);
      case LAST -> FinishedDateNumberOfPeriodsOperatorHandler.getInstance().buildLastPeriodQuery(filter);
      case NEXT -> FinishedDateNumberOfPeriodsOperatorHandler.getInstance().buildNextPeriodQuery(filter);
      case IS -> FinishedDateIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> FinishedDateIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case EMPTY -> FinishedDateEmptyOperatorHandler.getInstance().buildEmptyQuery();
      case NOT_EMPTY -> FinishedDateEmptyOperatorHandler.getInstance().buildNotEmptyQuery();
      default -> null;
    };
  }
}

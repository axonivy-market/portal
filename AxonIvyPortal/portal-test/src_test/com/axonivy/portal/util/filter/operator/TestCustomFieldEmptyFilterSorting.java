package com.axonivy.portal.util.filter.operator;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

@IvyTest
class TestCustomFieldEmptyFilterSorting {

  private static final String FIELD = "AccountNumber";

  private DashboardFilter filter(DashboardColumnType type) {
    DashboardFilter dashboardFilter = new DashboardFilter();
    dashboardFilter.setField(FIELD);
    dashboardFilter.setFilterType(type);
    return dashboardFilter;
  }

  private void runTask(TaskQuery filterQuery, boolean timestamp) {
    TaskQuery query = TaskQuery.create();
    query.where().and(filterQuery);
    if (timestamp) {
      query.orderBy().customField().timestampField(FIELD);
    } else {
      query.orderBy().customField().numberField(FIELD);
    }
    Sudo.get(() -> Ivy.wf().getTaskQueryExecutor().getResults(query, 0, 10));
  }

  private void runCase(CaseQuery filterQuery, boolean timestamp) {
    CaseQuery query = CaseQuery.create();
    query.where().and(filterQuery);
    if (timestamp) {
      query.orderBy().customField().timestampField(FIELD);
    } else {
      query.orderBy().customField().numberField(FIELD);
    }
    Sudo.get(() -> Ivy.wf().getCaseQueryExecutor().getResults(query, 0, 10));
  }

  @Test
  void taskNumberIsEmptyFilterAllowsSortingSameColumn() {
    var handler = com.axonivy.portal.util.filter.operator.task.customfield.CustomNumberEmptyOperatorHandler
        .getInstance();
    assertThatCode(() -> runTask(handler.buildEmptyQuery(filter(DashboardColumnType.CUSTOM)), false))
        .doesNotThrowAnyException();
  }

  @Test
  void taskTimestampIsEmptyFilterAllowsSortingSameColumn() {
    var handler = com.axonivy.portal.util.filter.operator.task.customfield.CustomTimestampEmptyOperatorHandler
        .getInstance();
    assertThatCode(() -> runTask(handler.buildEmptyQuery(filter(DashboardColumnType.CUSTOM)), true))
        .doesNotThrowAnyException();
  }

  @Test
  void caseNumberIsEmptyFilterAllowsSortingSameColumn() {
    var handler = com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberEmptyOperatorHandler
        .getInstance();
    assertThatCode(() -> runCase(handler.buildEmptyQuery(filter(DashboardColumnType.CUSTOM_CASE)), false))
        .doesNotThrowAnyException();
  }

  @Test
  void caseTimestampIsEmptyFilterAllowsSortingSameColumn() {
    var handler = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampEmptyOperatorHandler
        .getInstance();
    assertThatCode(() -> runCase(handler.buildEmptyQuery(filter(DashboardColumnType.CUSTOM_CASE)), true))
        .doesNotThrowAnyException();
  }

  @Test
  void taskIsEmptyByCaseFilterAllowsSortingSameColumn() {
    var handler = com.axonivy.portal.util.filter.operator.task.customfield.CustomNumberEmptyOperatorHandler
        .getInstance();
    assertThatCode(() -> runTask(handler.buildEmptyQueryByCase(filter(DashboardColumnType.CUSTOM_CASE)), false))
        .doesNotThrowAnyException();
  }

  @Test
  void isNotEmptyFiltersStillAllowSortingSameColumn() {
    var taskHandler = com.axonivy.portal.util.filter.operator.task.customfield.CustomNumberEmptyOperatorHandler
        .getInstance();
    var caseHandler = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTimestampEmptyOperatorHandler
        .getInstance();
    assertThatCode(() -> runTask(taskHandler.buildNotEmptyQuery(filter(DashboardColumnType.CUSTOM)), false))
        .doesNotThrowAnyException();
    assertThatCode(() -> runCase(caseHandler.buildNotEmptyQuery(filter(DashboardColumnType.CUSTOM_CASE)), true))
        .doesNotThrowAnyException();
  }
}

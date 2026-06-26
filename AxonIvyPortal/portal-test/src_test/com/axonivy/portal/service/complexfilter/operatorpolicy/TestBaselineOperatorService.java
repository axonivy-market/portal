package com.axonivy.portal.service.complexfilter.operatorpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.service.BaselineOperatorService;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestBaselineOperatorService {

  private BaselineOperatorService service;

  @BeforeEach
  void setUp() {
    service = new BaselineOperatorService();
  }

  @Test
  void resolveForColumn_returnsEmpty_whenColumnCannotFilter() {
    ColumnModel col = columnWithFilter(false);
    assertThat(service.resolveForColumn(col)).isEmpty();
  }

  @Test
  void resolveForColumn_returnsContains_whenColumnHasCmsValues() {
    ColumnModel col = columnWithFilter(true);
    col.setHasCmsValues(true);

    assertThat(service.resolveForColumn(col))
        .containsExactly(FilterOperator.CONTAINS);
  }

  @Test
  void resolveForColumn_resolvesByField_forKnownTaskField() {
    ColumnModel col = columnWithFilter(true);
    col.setField(DashboardStandardTaskColumn.NAME.getField());
    col.setHasCmsValues(false);

    assertThat(service.resolveForColumn(col))
        .containsExactlyInAnyOrderElementsOf(FilterOperator.TEXT_OPERATORS);
  }

  @Test
  void resolveForColumn_resolvesByField_forCreatorCaseField() {
    ColumnModel col = columnWithFilter(true);
    col.setField(DashboardStandardCaseColumn.CREATOR.getField());
    col.setHasCmsValues(false);

    assertThat(service.resolveForColumn(col))
        .containsExactlyInAnyOrderElementsOf(FilterOperator.CREATOR_OPERATORS);
  }

  @Test
  void resolveForFilter_returnsDateOperators_forExpiryDateField() {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(DashboardStandardTaskColumn.EXPIRY.getField());

    assertThat(service.resolveForFilter(filter))
        .containsExactlyInAnyOrderElementsOf(FilterOperator.DATE_OPERATORS);
  }

  @Test
  void resolveForFilter_resolvesByKnownField_priority() {
    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.PRIORITY.getField());

    assertThat(service.resolveForFilter(filter))
        .containsExactlyInAnyOrderElementsOf(FilterOperator.PRIORITY_OPERATORS);
  }

  @ParameterizedTest(name = "resolveByField_returnsIdOperators_for{0}Column")
  @EnumSource(value = DashboardStandardTaskColumn.class,
      names = {"ID", "BUSINESS_CASE_ID", "TECHNICAL_CASE_ID"})
  void resolveByField_returnsIdOperators_forIdColumns(DashboardStandardTaskColumn col) {
    assertThat(service.resolveByField(col.getField()))
        .containsExactlyInAnyOrderElementsOf(FilterOperator.ID_OPERATORS);
  }

  @Test
  void resolveByField_returnsEmpty_forUnknownField() {
    assertThat(service.resolveByField("fieldThatDoesNotExist")).isEmpty();
  }

  @Test
  void resolveByField_returnsStateOperators_forStateField() {
    assertThat(service.resolveByField(DashboardStandardTaskColumn.STATE.getField()))
        .containsExactlyInAnyOrderElementsOf(FilterOperator.STATE_OPERATORS);
  }

  private static ColumnModel columnWithFilter(boolean canFilter) {
    ColumnModel col = new ColumnModel();
    col.setEnableFilter(canFilter);
    return col;
  }
}
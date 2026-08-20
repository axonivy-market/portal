package com.axonivy.portal.util;

import static com.axonivy.portal.util.CaseQueryUtils.initCaseQuery;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

/**
 * A task references both its own case and its business case, so an unscoped case query makes the
 * engine join and union both. Scoping the query to the case the filtered column refers to leaves a
 * single join.
 */
@IvyTest
class TestCaseQueryUtils {

  /**
   * Guards the assertions below: they compare rendered queries, which only distinguishes the scopes if
   * the scope reaches the rendered query at all.
   */
  @Test
  void theThreeCaseScopes_renderDifferentQueries() {
    assertThat(CaseQuery.businessCases().toString())
        .isNotEqualTo(CaseQuery.subCases().toString())
        .isNotEqualTo(CaseQuery.create().toString());
    assertThat(CaseQuery.subCases().toString()).isNotEqualTo(CaseQuery.create().toString());
  }

  @Test
  void businessCaseColumn_queriesBusinessCasesOnly() {
    assertThat(initCaseQuery(DashboardColumnType.CUSTOM_BUSINESS_CASE).toString())
        .isEqualTo(CaseQuery.businessCases().toString());
  }

  @Test
  void subCaseColumn_queriesSubCasesOnly() {
    assertThat(initCaseQuery(DashboardColumnType.CUSTOM_CASE).toString())
        .isEqualTo(CaseQuery.subCases().toString());
  }

  /**
   * The case widget types its filters {@code CUSTOM} and picks the scope on its root query instead, so
   * it must keep the unscoped query.
   */
  @Test
  void taskCustomColumn_staysUnscoped() {
    assertThat(initCaseQuery(DashboardColumnType.CUSTOM).toString())
        .isEqualTo(CaseQuery.create().toString());
  }

  @Test
  void standardColumn_staysUnscoped() {
    assertThat(initCaseQuery(DashboardColumnType.STANDARD).toString())
        .isEqualTo(CaseQuery.create().toString());
  }

  @Test
  void untypedFilter_staysUnscoped() {
    assertThat(initCaseQuery(null).toString()).isEqualTo(CaseQuery.create().toString());
  }

  @Test
  void everyCallReturnsANewQuery() {
    CaseQuery first = initCaseQuery(DashboardColumnType.CUSTOM_BUSINESS_CASE);
    CaseQuery second = initCaseQuery(DashboardColumnType.CUSTOM_BUSINESS_CASE);

    assertThat(first).isNotSameAs(second);

    first.where().customField().stringField("HIDE").isNotNull();

    assertThat(second.toString()).isEqualTo(CaseQuery.businessCases().toString());
  }
}

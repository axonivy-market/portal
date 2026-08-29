package com.axonivy.portal.util.filter.field;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

@IvyTest
class TestTaskFilterFieldFactory {

  private static final String CASE_CUSTOM_FIELD = "HIDE";

  private static final String WIDGET_ID = "task-widget-1";

  @Test
  void theCaseCustomFieldUsedByTheseTestsExists() {
    assertThat(ICustomFieldMeta.cases()).anyMatch(meta -> CASE_CUSTOM_FIELD.equals(meta.name()));
  }

  @Test
  void subCaseColumn_initializesASubCaseFilter() {
    DashboardFilter filter = new DashboardFilter();

    TaskFilterFieldFactory.findBy(CASE_CUSTOM_FIELD, DashboardColumnType.CUSTOM_CASE).initFilter(filter);

    assertThat(filter.getFilterType()).isEqualTo(DashboardColumnType.CUSTOM_CASE);
    assertThat(filter.getField()).isEqualTo(CASE_CUSTOM_FIELD);
  }

  @Test
  void businessCaseColumn_initializesABusinessCaseFilter() {
    DashboardFilter filter = new DashboardFilter();

    TaskFilterFieldFactory.findBy(CASE_CUSTOM_FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE)
        .initFilter(filter);

    assertThat(filter.getFilterType()).isEqualTo(DashboardColumnType.CUSTOM_BUSINESS_CASE);
    assertThat(filter.getField()).isEqualTo(CASE_CUSTOM_FIELD);
  }

  @Test
  void businessCaseColumn_initializesABusinessCaseFilter_perWidget() {
    DashboardFilter filter = new DashboardFilter();

    TaskFilterFieldFactory.findBy(WIDGET_ID, CASE_CUSTOM_FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE)
        .initFilter(filter);

    assertThat(filter.getFilterType()).isEqualTo(DashboardColumnType.CUSTOM_BUSINESS_CASE);
  }

  @Test
  void addingANewBusinessCaseFilter_storesTheBusinessCaseType() {
    DashboardFilter filter = new DashboardFilter();

    TaskFilterFieldFactory.findBy(CASE_CUSTOM_FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE)
        .addNewFilter(filter);

    assertThat(filter.getFilterType()).isEqualTo(DashboardColumnType.CUSTOM_BUSINESS_CASE);
  }

  @Test
  void addingANewSubCaseFilter_storesTheSubCaseType() {
    DashboardFilter filter = new DashboardFilter();

    TaskFilterFieldFactory.findBy(CASE_CUSTOM_FIELD, DashboardColumnType.CUSTOM_CASE).addNewFilter(filter);

    assertThat(filter.getFilterType()).isEqualTo(DashboardColumnType.CUSTOM_CASE);
  }

  @Test
  void theTwoCaseScopes_resolveToDifferentFilterFields() {
    FilterField subCase = TaskFilterFieldFactory.findBy(CASE_CUSTOM_FIELD, DashboardColumnType.CUSTOM_CASE);
    FilterField businessCase =
        TaskFilterFieldFactory.findBy(CASE_CUSTOM_FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE);

    assertThat(subCase).isNotNull();
    assertThat(businessCase).isNotNull();
    assertThat(businessCase).isNotSameAs(subCase);
  }

  @Test
  void reopeningTheWidget_keepsTheBusinessCaseType() {
    DashboardFilter filter = new DashboardFilter();
    TaskFilterFieldFactory.findBy(CASE_CUSTOM_FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE)
        .addNewFilter(filter);

    TaskFilterFieldFactory.findBy(WIDGET_ID, filter.getField(), filter.getFilterType()).initFilter(filter);

    assertThat(filter.getFilterType()).isEqualTo(DashboardColumnType.CUSTOM_BUSINESS_CASE);
  }

  @Test
  void reopeningTheWidget_keepsTheSubCaseType() {
    DashboardFilter filter = new DashboardFilter();
    TaskFilterFieldFactory.findBy(CASE_CUSTOM_FIELD, DashboardColumnType.CUSTOM_CASE).addNewFilter(filter);

    TaskFilterFieldFactory.findBy(WIDGET_ID, filter.getField(), filter.getFilterType()).initFilter(filter);

    assertThat(filter.getFilterType()).isEqualTo(DashboardColumnType.CUSTOM_CASE);
  }
}

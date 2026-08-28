package com.axonivy.portal.util.filter.field;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

/**
 * A case custom field can be added to a task widget as a sub case column or as a business case
 * column. Until 14.0.0 both produced a filter typed {@code CUSTOM_CASE}, so the query could not tell
 * which case to look at. The factory now keeps one filter field per case scope.
 */
@IvyTest
class TestTaskFilterFieldFactory {

  /** Declared in {@code AxonIvyPortal/portal/config/custom-fields.yaml} under {@code CustomFields.Cases}. */
  private static final String CASE_CUSTOM_FIELD = "HIDE";

  private static final String WIDGET_ID = "task-widget-1";

  @Test
  void theCaseCustomFieldUsedByTheseTestsExists() {
    // Without it every assertion below would silently degrade to asserting on null.
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

  /** This is the path taken when a user picks the field in the complex filter dialog. */
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

  /**
   * The task widget re-initializes its filters every time it is opened for configuration. That must not
   * downgrade a business case filter back to a sub case one.
   */
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

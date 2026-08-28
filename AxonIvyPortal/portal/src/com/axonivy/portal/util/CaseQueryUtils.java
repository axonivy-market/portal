package com.axonivy.portal.util;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public final class CaseQueryUtils {

  private CaseQueryUtils() {
  }

  /**
   * Creates the case query which a task widget's case custom field filter is built on, scoped to the
   * kind of case the filtered column refers to.
   * <p>
   * A task references both its own case and its business case, so an unscoped
   * {@link CaseQuery#create()} makes the engine join and union both, which is expensive. Scoping the
   * query to a single kind of case leaves a single join.
   * <ul>
   * <li>{@link DashboardColumnType#CUSTOM_BUSINESS_CASE} - {@link CaseQuery#businessCases()}</li>
   * <li>{@link DashboardColumnType#CUSTOM_CASE} - {@link CaseQuery#subCases()}</li>
   * <li>anything else - {@link CaseQuery#create()}</li>
   * </ul>
   * The fallback keeps the case widget unchanged: its filters carry
   * {@link DashboardColumnType#CUSTOM} and its root query already picks the scope itself.
   *
   * @param type type of the column the filter was created from
   * @return a new case query scoped to the kind of case the column refers to
   */
  public static CaseQuery initCaseQuery(DashboardColumnType type) {
    if (DashboardColumnType.CUSTOM_BUSINESS_CASE == type) {
      return CaseQuery.businessCases();
    }
    if (DashboardColumnType.CUSTOM_CASE == type) {
      return CaseQuery.subCases();
    }
    return CaseQuery.create();
  }
}

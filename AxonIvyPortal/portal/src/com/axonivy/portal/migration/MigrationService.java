package com.axonivy.portal.migration;

public class MigrationService {

  private MigrationService() {}

  public static void migrateWhenStartEngine() {
    migrateTo112();
  }

  private static void migrateTo112() {
    com.axonivy.portal.migration.version112.DashboardMigrationService.migrateAllDashboardsAndRelatedData();
  }

}

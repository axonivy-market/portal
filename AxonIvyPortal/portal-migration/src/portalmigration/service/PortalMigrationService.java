package portalmigration.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import portalmigration.enums.PortalLibrary;
import portalmigration.version91.migrate.config.service.ApplicationPropertyMigrationService;
import portalmigration.version91.migrate.statistic.service.StatisticMigrationService;
import portalmigration.version93.service.BusinessDataMigrationService;
import portalmigration.version93.service.PortalProcessMigrationService;

public class PortalMigrationService {

  public static final String SLASH = "/";

  public static List<String> migrate() {
    List<String> result = null;
    List<IApplication> portalIApplications = getPortalApps();
    for (IApplication app : portalIApplications) {
      Ivy.log().info("***Start migrating configuration for app {0}", app.getName());
      result = startMigratingToTargetVersion(app);
    }
    return result;
  }

  /**
   * Write migration code here
   * @param app is ivy application that depend on portal
   */
  private static List<String> startMigratingToTargetVersion(IApplication app) {
    List<String> errors = new ArrayList<>();

    Ivy.log().info("***Start migrating to latest Portal");
    migratePortalBusinessData(app, errors);

    migratePortalProcesses(app, errors);

    migrate91to92();

    return errors;
  }

  private static void migratePortalBusinessData(IApplication app, List<String> errors) {
    BusinessDataMigrationService.startMigrationData(app, errors);
  }

  private static void migratePortalProcesses(IApplication app, List<String> errors) {
    errors.addAll(PortalProcessMigrationService.startMigration(app));
  }

  /** Migrate from 9.1 -> 9.2
   * This is legacy code, to keep track of the history.
   */
  protected static void migrate91to92() {
    if (Boolean.FALSE) {
      ApplicationPropertyMigrationService.migrate();
      new StatisticMigrationService().migrateStatisticCharts();
    }
  }

  protected static List<IApplication> getPortalApps() {
    List<IApplication> portalApps = new ArrayList<>();
    for (var app : IApplicationRepository.instance().all()) {
      List<ILibrary> libraries = app.getLibraries();
      for (ILibrary lib : libraries) {
        if (StringUtils.endsWith(lib.getId(), PortalLibrary.PORTAL_TEMPLATE.getProjectId())
            && app.getActivityState() == ActivityState.ACTIVE) {
          Ivy.log().info("***Found app {0} is depending on Portal", app.getName());
          portalApps.add(app);
        }
      }
    }
    return portalApps;
  }
}

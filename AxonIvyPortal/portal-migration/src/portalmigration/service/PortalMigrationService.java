package portalmigration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.server.ServerFactory;
import portalmigration.enums.PortalLibrary;
import portalmigration.version91.migrate.config.service.ApplicationPropertyMigrationService;
import portalmigration.version91.migrate.statistic.service.StatisticMigrationService;
import portalmigration.version93.service.PortalProcessMigrationService;

public class PortalMigrationService {

  public static final String SLASH = "/";
  private static boolean isLegacySystem;

  public static List<String> migrate(Map<String, Object> options) {
    List<String> result = null;
    List<IApplication> portalIApplications = getPortalApps();
    for (IApplication app : portalIApplications) {
      result = startMigratingToTargetVersion(app, options);
    }
    return result;
  }

  /**
   * Write migrtion code here
   * @param app is ivy apps that depend on portal
   */
  private static List<String> startMigratingToTargetVersion(IApplication app, Map<String, Object> options) {
    List<String> errors = new ArrayList<>();

    migratePortalProcesses(app, options, errors);

    // From 9.1 -> 9.2
    // This is legacy code, to keep track of the history.
    if (isLegacySystem) { // This condition is always FALSE
      migrate91to92();
    }
    return errors;
  }

  private static void migratePortalProcesses(IApplication app, Map<String, Object> options, List<String> errors) {
    errors.addAll(PortalProcessMigrationService.startMigration(app, options));
  }

  private static void migrate91to92() {
    ApplicationPropertyMigrationService.migrate();
    new StatisticMigrationService().migrateStatisticCharts();
  }

  protected static List<IApplication> getPortalApps() {
    List<IApplication> applications = applicationManager().getApplications();
    List<IApplication> portalApps = new ArrayList<>();
    for (IApplication app : applications) {
      List<ILibrary> libraries = app.getLibraries();
      for (ILibrary lib : libraries) {
        if (StringUtils.endsWith(lib.getId(), PortalLibrary.PORTAL_TEMPLATE.getProjectId())
            && app.getActivityState() == ActivityState.ACTIVE) {
          portalApps.add(app);
        }
      }
    }
    return portalApps;
  }

  private static IApplicationConfigurationManager applicationManager() {
    return ServerFactory.getServer().getApplicationConfigurationManager();
  }
}

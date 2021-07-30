package portal.migration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.vars.Variables;
import portal.migration.enums.PortalLibrary;
import portal.migration.enums.PortalVariable;
import portal.migration.version91.migrate.config.service.ApplicationPropertyMigrationService;
import portal.migration.version91.migrate.statistic.service.StatisticMigrationService;
import portal.migration.version93.service.PortalProcessMigrationService;

public class PortalMigrationService {

  public static int LATEST_VERSION = 93;
  public static final String SLASH = "/";
  private static boolean isLegacySystem;

  public static boolean mustMigrateData() {
    return !getObsoletePortalApps().isEmpty();
  }
  
  /**
   * Steps:
   * - Check current version
   * - Migrate data from current portal version to latest portal version
   * - Update PortalVariable.VERSION to latest
   * @return error when migrating
   */
  public static List<String> migrate() {
    List<String> result = null;
    Map<Long, Integer> portalAppVersionMap = getObsoletePortalApps();

    for (Long appId : portalAppVersionMap.keySet()) {
      IApplication migrateApp = applicationManager().findApplication(appId);
      int currentVersion = portalAppVersionMap.get(appId);
      while (currentVersion < LATEST_VERSION) {

        result = startMigratingToTargetVersion(currentVersion, migrateApp);

        currentVersion++;
      }

      if (CollectionUtils.isEmpty(result)) {
        updatePortalVersion();
      }
    }

    return result;
  }

  private static Map<Long, Integer> getObsoletePortalApps() {
    Map<Long, Integer> obsoleteAppVersionMap = new HashMap<>();
    List<IApplication> applications = getPortalApps();
    for (IApplication app : applications) {
      var portalVersion = Variables.of(app).variable(PortalVariable.VERSION.key);
      String version = "";
      if (portalVersion == null || StringUtils.isEmpty(portalVersion.value())) {
        // This is first version: 9.3
        version = "92";
      } else {
        version = portalVersion.value();
      }

      if (NumberUtils.isCreatable(version)) {
        int currentVersion = NumberUtils.toInt(version);
        if (currentVersion < LATEST_VERSION) {
          obsoleteAppVersionMap.put(app.getId(), currentVersion);
        }
      }
    }

    return obsoleteAppVersionMap;
  }

  /**
   * Write migrtion code here
   * @param currentVersion is version of portal need to migrated
   */
  private static List<String> startMigratingToTargetVersion(int currentVersion, IApplication app) {
    List<String> errors = new ArrayList<>();
    if (currentVersion == 93) {
      migratePortalProcesses(app, errors);
    }

    // From 9.1 -> 9.2
    // This is legacy code, to keep track of the history.
    if (isLegacySystem) { // This condition is always FALSE
      migrate91to92();
    }
    return errors;
  }

  private static void migratePortalProcesses(IApplication app, List<String> errors) {
    errors.addAll(PortalProcessMigrationService.startMigration(app));
  }

  private static void migrate91to92() {
    ApplicationPropertyMigrationService.migrate();
    new StatisticMigrationService().migrateStatisticCharts();
  }

  private static void updatePortalVersion() {
    
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

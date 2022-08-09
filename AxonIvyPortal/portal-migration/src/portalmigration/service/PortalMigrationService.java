package portalmigration.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import portalmigration.enums.PortalLibrary;
import portalmigration.version91.migrate.config.service.ApplicationPropertyMigrationService;
import portalmigration.version91.migrate.statistic.service.StatisticMigrationService;
import portalmigration.version93.service.BusinessDataMigrationService;
import portalmigration.version93.service.PortalProcessMigrationService;
import portalmigration.version94.service.PortalCaseFilterBusinessDataMigrationService;
import portalmigration.version94.service.PortalTaskAnalysisFilterBusinessDataMigrationService;
import portalmigration.version94.service.PortalTaskFilterBusinessDataMigrationService;

public class PortalMigrationService {

  public static final String SLASH = "/";
  public static final String PROCESS_MODEL_PORTAL_TEMPLATE = "PortalTemplate";
  public static final String PROCESS_MODEL_PORTAL = "portal";

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

    migrate91to92();
    migrate92to93(app, errors);
    migrate93to94(app);

    return errors;
  }

  /** Migrate from 9.2 -> 9.3
   * This is legacy code, to keep track of the history.
   */
  private static void migrate92to93(IApplication app, List<String> errors) {
    if (Boolean.FALSE) {
      migratePortalBusinessData(app, errors);
      migratePortalProcesses(app, errors);
    }
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

  private static void migrate93to94(IApplication app) {
    IProcessModel portalTemplateProcessModel = app.findProcessModel(PROCESS_MODEL_PORTAL_TEMPLATE);
    long portalTemplateProcessModelId = portalTemplateProcessModel != null ? portalTemplateProcessModel.getId() : -1L;
    IProcessModel portalProcessModel = app.findProcessModel(PROCESS_MODEL_PORTAL);
    long portalProcessModelId = portalProcessModel != null ? portalProcessModel.getId() : -1L;

    if (portalTemplateProcessModelId > -1L && portalProcessModelId > -1L) {
      PortalCaseFilterBusinessDataMigrationService.startMigrationData(portalTemplateProcessModelId, portalProcessModelId);
      PortalTaskFilterBusinessDataMigrationService.startMigrationData(portalTemplateProcessModelId, portalProcessModelId);
      PortalTaskAnalysisFilterBusinessDataMigrationService.startMigrationData(portalTemplateProcessModelId, portalProcessModelId);
    }
  }
}

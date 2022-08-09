package portalmigration.version94.service;

import java.util.List;

import ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisFilterData;
import ch.ivyteam.ivy.environment.Ivy;

public class PortalTaskAnalysisFilterBusinessDataMigrationService {

  public static void startMigrationData(long portalTemplateGroupId, long portalGroupId) {
    Ivy.log().info("***Start migrating PortalTaskAnalysisFilterBusinessData of PortalTemplateGroupId: " + portalTemplateGroupId
        + " to PortalGroupId: " + portalGroupId);
    migrateTaskAnalysisFilterBusinessData(portalTemplateGroupId, portalGroupId);
    Ivy.log().info("***End migrating PortalTaskAnalysisFilterBusinessData of PortalTemplateGroupId: " + portalTemplateGroupId
        + " to PortalGroupId: " + portalGroupId);
  }

  private static void migrateTaskAnalysisFilterBusinessData(long portalTemplateGroupId, long portalGroupId) {
    TaskAnalysisFilterService taskAnalysisFilterService = new TaskAnalysisFilterService();
    List<TaskAnalysisFilterData> list = taskAnalysisFilterService.getFiltersByGroupId(portalTemplateGroupId);
    for (TaskAnalysisFilterData taskAnalysisFilterData : list) {
      taskAnalysisFilterData.setFilterGroupId(portalGroupId);
      taskAnalysisFilterService.save(taskAnalysisFilterData);
    }
  }
}

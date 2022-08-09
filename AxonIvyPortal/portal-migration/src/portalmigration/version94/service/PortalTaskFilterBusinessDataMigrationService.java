package portalmigration.version94.service;

import java.util.List;

import ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData;
import ch.ivyteam.ivy.environment.Ivy;

public class PortalTaskFilterBusinessDataMigrationService {

  public static void startMigrationData(long portalTemplateGroupId, long portalGroupId) {
    Ivy.log().info("***Start migrating PortalTaskFilterBusinessData of PortalTemplateGroupId: " + portalTemplateGroupId
        + " to PortalGroupId: " + portalGroupId);
    migrateTaskFilterBusinessData(portalTemplateGroupId, portalGroupId);
    Ivy.log().info("***End migrating PortalTaskFilterBusinessData of PortalTemplateGroupId: " + portalTemplateGroupId
        + " to PortalGroupId: " + portalGroupId);
  }

  private static void migrateTaskFilterBusinessData(long portalTemplateGroupId, long portalGroupId) {
    TaskFilterService taskFilterService = new TaskFilterService();
    List<TaskFilterData> list = taskFilterService.getFiltersByGroupId(portalTemplateGroupId);
    for (TaskFilterData taskFilterData : list) {
      taskFilterData.setFilterGroupId(portalGroupId);
      taskFilterService.save(taskFilterData);
    }
  }
}

package portalmigration.version94.service;

import java.util.List;

import ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData;
import ch.ivyteam.ivy.environment.Ivy;

public class PortalCaseFilterBusinessDataMigrationService {
  public static void startMigrationData(long portalTemplateGroupId, long portalGroupId) {
    Ivy.log().info("***Start migrating PortalCaseFilterBusinessData of PortalTemplateGroupId: " + portalTemplateGroupId
        + " to PortalGroupId: " + portalGroupId);
    migrateCaseFilterBusinessData(portalTemplateGroupId, portalGroupId);
    Ivy.log().info("***End migrating PortalCaseFilterBusinessData of PortalTemplateGroupId: " + portalTemplateGroupId
        + " to PortalGroupId: " + portalGroupId);
  }

  private static void migrateCaseFilterBusinessData(long portalTemplateGroupId, long portalGroupId) {
    CaseFilterService caseFilterService = new CaseFilterService();
    List<CaseFilterData> list = caseFilterService.getFiltersByGroupId(portalTemplateGroupId);
    for (CaseFilterData caseFilterData : list) {
      caseFilterData.setFilterGroupId(portalGroupId);
      caseFilterService.save(caseFilterData);
    }
  }
}

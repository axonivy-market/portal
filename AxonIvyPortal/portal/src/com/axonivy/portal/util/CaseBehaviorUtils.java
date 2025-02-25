package com.axonivy.portal.util;

import static ch.ivy.addon.portalkit.enums.PortalPermission.SHOW_CASE_DETAILS;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;

import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInCaseList;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.workflow.ICase;

public class CaseBehaviorUtils {

  public static String getTargetLinkWhenClickOnCase(ICase caze) {
    if (canAccessBusinessDetails()) {
      return BusinessDetailsUtils.getAdditionalCaseDetailsPageUri(caze);
    } else {
      return PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageByUUID(caze.uuid());
    }

  }

  public static boolean canAccessBusinessDetails() {
    boolean isShowCaseDetails = PermissionUtils.hasPortalPermission(SHOW_CASE_DETAILS);
    boolean isAccessBusinessDetailsWhenClickingOnCaseInList = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.DEFAULT_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_CASE_LIST)
        .equals(BehaviourWhenClickingOnLineInCaseList.ACCESS_BUSINESS_DETAILS.name());
    return isShowCaseDetails && isAccessBusinessDetailsWhenClickingOnCaseInList;
  }

}
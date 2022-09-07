package ch.ivy.addon.portalkit.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.workflow.ICase;

public class ProcessViewerUtils {

  private static final String START_PROCESS_PORTAL_PROCESS_VIEWER_PAGE = "Start Processes/PortalStart/PortalProcessViewer.ivp";
  public static final String DEFAULT_LINK = "#";

  public static String getStartProcessViewerPageUri(ICase selectedCase) {
    if (Objects.isNull(selectedCase) || Objects.isNull(selectedCase.getProcessStart()) || isExpressCase(selectedCase)) {
      return DEFAULT_LINK;
    }
    return buildPortalProcessViewerUrl(selectedCase.getId(), selectedCase.getProcessStart().getLink().getRelative());
  }

  public static String getStartProcessViewerPageUri(String processStartLink) {
    if (StringUtils.isBlank(processStartLink)) {
      return DEFAULT_LINK;
    }
    return buildPortalProcessViewerUrl(null, processStartLink);
  }

  private static String buildPortalProcessViewerUrl(Long caseId, String processStartLink) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    params.put("processKey", processStartLink);
    return PortalNavigator.buildUrlByKeyword("PortalProcessViewer.ivp", START_PROCESS_PORTAL_PROCESS_VIEWER_PAGE, params);
  }

  private static boolean isExpressCase(ICase iCase) {
    return BooleanUtils.toBoolean(iCase.customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).getOrNull());
  }

  public static boolean isShowProcessViewer(ICase caze) {
    if (isExpressCase(caze) || !caze.isBusinessCase()) {
      return false;
    }
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_PROCESS_VIEWER);
  }
}

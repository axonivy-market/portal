package ch.ivy.addon.portalkit.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.ProcessViewerUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.workflow.ICase;

public class PortalProcessViewerUtils {

  private static final String START_PROCESS_PORTAL_PROCESS_VIEWER_PAGE = "Start Processes/PortalStart/PortalProcessViewer.ivp";
  public static final String DEFAULT_LINK = "#";

  public static String getStartProcessViewerPageUri(ICase selectedCase) {
    if (Objects.isNull(selectedCase) || Objects.isNull(selectedCase.getProcessStart()) || ProcessViewerUtils.isExpressCase(selectedCase)) {
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

  public static boolean isShowProcessViewer(ICase caze) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_PROCESS_VIEWER) && ProcessViewerUtils.isViewerAllowed(caze);
  }
}

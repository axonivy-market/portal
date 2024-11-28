package ch.ivy.addon.portalkit.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.ProcessViewerUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

public class PortalProcessViewerUtils {

  private static final String START_PROCESS_PORTAL_PROCESS_VIEWER_PAGE = "Start Processes/PortalStart/PortalProcessViewer.ivp";
  public static final String DEFAULT_LINK = "#";

  public static String getStartProcessViewerPageUri(ICase selectedCase) {
    if (Objects.isNull(selectedCase) || Objects.isNull(selectedCase.getProcessStart())) {
      return DEFAULT_LINK;
    }
    return buildPortalProcessViewerUrl(null, selectedCase.getId(), selectedCase.getProcessStart().getLink().getRelative());
  }

  public static String getStartProcessViewerPageUri(ITask selectedTask) {
    if (Objects.isNull(selectedTask) || Objects.isNull(selectedTask.getCase()) || Objects.isNull(selectedTask.getCase().getProcessStart())) {
      return DEFAULT_LINK;
    }
    return buildPortalProcessViewerUrl(selectedTask.getId(), null, null);
  }

  public static String getStartProcessViewerPageUri(String processStartLink) {
    if (StringUtils.isBlank(processStartLink)) {
      return DEFAULT_LINK;
    }
    return buildPortalProcessViewerUrl(null, null, processStartLink);
  }

  private static String buildPortalProcessViewerUrl(Long selectedTaskId, Long caseId, String processStartLink) {
    Map<String, String> params = new HashMap<>();
    Optional.ofNullable(selectedTaskId).ifPresent(v -> params.put("selectedTaskId", String.valueOf(v)));
    Optional.ofNullable(caseId).ifPresent(v -> params.put("caseId", String.valueOf(v)));
    Optional.ofNullable(processStartLink).ifPresent(v -> params.put("processKey", v));
    return PortalNavigator.buildUrlByKeyword("PortalProcessViewer.ivp", START_PROCESS_PORTAL_PROCESS_VIEWER_PAGE, params);
  }

  public static boolean isShowProcessViewer(ICase caze) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_PROCESS_VIEWER) && ProcessViewerUtils.isViewerAllowed(caze);
  }
}

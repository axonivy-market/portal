package ch.ivy.addon.portalkit.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessViewerUtils {

  private static final String START_PROCESS_PORTAL_PROCESS_VIEWER_PAGE = "Start Processes/PortalStart/PortalProcessViewer.ivp";
  private static final String TEXT_SEPARATOR = "/";
  private static List<IWebStartable> webStartables;

  public static boolean isValidCase(Long caseId) {
    return Objects.isNull(CaseUtils.findCase(caseId)) ? false : true;
  }

  public static String getProcessViewerIframeURL(ICase selectedCase) {
    if (selectedCase == null || isExpressCase(selectedCase)) {
      return "";
    }
    selectedCase.getProcessStart().getLink().getRelative();
    var selectedProcess = getWebStartables().stream()
          .filter(webStartable -> StringUtils.equals(webStartable.getLink().getRelative(), selectedCase.getProcessStart().getLink().getRelative()))
          .findFirst().orElse(null);
    return Objects.isNull(selectedProcess) ? "#" : selectedProcess.viewerLink().getRelative();
  }

  private static List<IWebStartable> getWebStartables() {
    if (CollectionUtils.isEmpty(webStartables)) {
      webStartables = ProcessService.newInstance().findProcesses().getProcesses();
    }
    return webStartables;
  }

  public static String getStartProcessViewerPageUri(ICase selectedCase) {
    if (isExpressCase(selectedCase)) {
      return "";
    }
    Map<String, String> params = new HashMap<>();
    params.put("processViewerCaseId", String.valueOf(selectedCase.getId()));
    return PortalNavigator.buildUrlByKeyword("PortalProcessViewer", START_PROCESS_PORTAL_PROCESS_VIEWER_PAGE, params);
  }

  public static String getDisplayProcessRequestPath(IProcessStart processStart) {
    var path = processStart.getRequestPath();
    var pathElements = Arrays.asList(processStart.getRequestPath().split(TEXT_SEPARATOR));
    var extractedPath = pathElements.stream()
        .filter(element -> element.endsWith(".ivp") || element.endsWith(".icm"))
        .findFirst().orElse(null);
    if (StringUtils.isNotEmpty(extractedPath)) {
      path = extractedPath;
    }
    return path;
  }

  public static String getDisplayProcessPMV(IProcessStart processStart) {
    return IvyExecutor.executeAsSystem(() -> {
      var pmv = processStart.getProcessModelVersion();
      var displayPMVName = new StringBuilder("");
      displayPMVName.append(pmv.getApplication().getName()).append(TEXT_SEPARATOR);
      displayPMVName.append(pmv.getProjectName()).append(TEXT_SEPARATOR);
      displayPMVName.append(pmv.getVersionName());
      return displayPMVName.toString();
    });
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

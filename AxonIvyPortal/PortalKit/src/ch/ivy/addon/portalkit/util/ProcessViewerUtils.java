package ch.ivy.addon.portalkit.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

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
  public static final String DEFAULT_LINK = "#";
  private static List<IWebStartable> webStartables;

  public static boolean isValidCase(Long caseId) {
    return Objects.isNull(CaseUtils.findCase(caseId)) ? false : true;
  }

  public static String getProcessViewerIframeURL(Long caseId, String processId) {
    var selectedProcess = findWebStartable(caseId, processId);
    return Objects.isNull(selectedProcess) ? DEFAULT_LINK : selectedProcess.viewerLink().getRelative();
  }

  public static IWebStartable findWebStartable(Long caseId, String processId) {
    String startProcessLink = "";
    var selectedCase = CaseUtils.findCase(caseId);
    if (selectedCase != null && !isExpressCase(selectedCase)) {
      startProcessLink = selectedCase.getProcessStart().getLink().getRelative();
    }
    if (caseId == null || selectedCase == null) {
      startProcessLink = processId;
    }
    return getWebStartables().stream()
        .filter(filterByRelativeLink(startProcessLink)).findFirst()
        .orElse(null);
  }

  private static Predicate<? super IWebStartable> filterByRelativeLink(String startProcessId) {
    return webStartable -> StringUtils.equals(startProcessId, webStartable.getLink().getRelative());
  }

  private static List<IWebStartable> getWebStartables() {
    if (CollectionUtils.isEmpty(webStartables)) {
      webStartables = ProcessService.newInstance().findProcesses().getProcesses();
    }
    return webStartables;
  }

  public static String getStartProcessViewerPageUri(ICase selectedCase) {
    if (isExpressCase(selectedCase)) {
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


  public static String getDisplayProcessRequestPath(Long caseId, String processId) {
    var webStartable = findWebStartable(caseId, processId);
    return Objects.isNull(webStartable) ? "" : webStartable.getDisplayName();
  }

  public static String getDisplayProcessPMV(IProcessStart processStart) {
    if (processStart == null) {
      return "";
    }

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

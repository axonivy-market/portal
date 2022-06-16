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
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.casemap.runtime.ICaseMapService;
import ch.ivyteam.ivy.casemap.runtime.model.ICaseMap;
import ch.ivyteam.ivy.casemap.runtime.start.CaseMapViewerUrl;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.model.value.WebLink;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IStartElement;
import ch.ivyteam.ivy.workflow.start.IProcessWebStartable;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import ch.ivyteam.ivy.workflow.start.ProcessViewerUrl;

@SuppressWarnings("restriction")
public class ProcessViewerUtils {

  private static final String START_PROCESS_PORTAL_PROCESS_VIEWER_PAGE = "Start Processes/PortalStart/PortalProcessViewer.ivp";
  private static final String TEXT_SEPARATOR = "/";
  public static final String DEFAULT_LINK = "#";
  private static final int PROJECT_NAME_INDEX = 3;
  private static List<IWebStartable> webStartables;
  private static List<IProcessWebStartable> webProcessStartables;

  public static boolean isValidCase(Long caseId) {
    return Objects.isNull(CaseUtils.findCase(caseId)) ? false : true;
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
    if (isCaseMap(selectedCase)) {
      ICaseMap caseMap =
          findCaseMapByCase(selectedCase);
      return getWebStartables().stream()
          .filter(filterById(caseMap.getUuid().toString())).findFirst()
          .orElse(null);
    }
    return getWebStartables().stream()
        .filter(filterByRelativeLink(startProcessLink)).findFirst()
        .orElse(null);
  }
  
  public static IProcessWebStartable findProcessWebStartable(String processId) {
    String startProcessLink = "";
    var selectedCase = CaseUtils.findCase(caseId);
    if (selectedCase != null && !isExpressCase(selectedCase)) {
      startProcessLink = selectedCase.getProcessStart().getLink().getRelative();
    }
    if (caseId == null || selectedCase == null) {
      startProcessLink = processId;
    }
    if (isCaseMap(selectedCase)) {
      ICaseMap caseMap =
          findCaseMapByCase(selectedCase);
      return getWebStartables().stream()
          .filter(filterById(caseMap.getUuid().toString())).findFirst()
          .orElse(null);
    }
    return getWebStartables().stream()
        .filter(filterByRelativeLink(startProcessLink)).findFirst()
        .orElse(null);
  }

  private static Predicate<? super IWebStartable> filterByRelativeLink(String startProcessId) {
    return webStartable -> StringUtils.equals(startProcessId, webStartable.getLink().getRelative());
  }
  
  private static Predicate<? super IWebStartable> filterById(String startProcessId) {
    return webStartable -> webStartable.getLink().getRelative().contains(startProcessId);
  }

  private static List<IWebStartable> getWebStartables() {
    if (CollectionUtils.isEmpty(webStartables)) {
      webStartables = ProcessService.newInstance().findProcesses().getProcesses();
    }
    return webStartables;
  }
  
  /*private static List<IProcessWebStartable> getProcessWebStartables() {
    if (CollectionUtils.isEmpty(webProcessStartables)) {
      webProcessStartables = ProcessService.newInstance().findProcesses();
    }
    return webProcessStartables;
  }*/

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

  public static boolean isCaseMap(ICase caze) {
    return !Objects.isNull(findCaseMapByCase(caze));
  }

  public static boolean isShowProcessViewer(ICase caze) {
    if (isExpressCase(caze) || !caze.isBusinessCase()) {
      return false;
    }
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_PROCESS_VIEWER);
  }
  
  public static WebLink getViewerWebLink(Long caseId, String processId) {
    var selectedCase = CaseUtils.findCase(caseId);
    if (selectedCase != null && !isExpressCase(selectedCase)) {
      if (isCaseMap(selectedCase)) {
        ICaseMap caseMap =
            findCaseMapByCase(selectedCase);
        return new CaseMapViewerUrl.Builder(caseMap).toWebLink();
      } else {
        String friendlyRequestPath = selectedCase.getProcessStart().getUserFriendlyRequestPath();
        IStartElement element = ProcessStartAPI.findStartElementByProcessStartFriendlyRequestPath(friendlyRequestPath);
        return new ProcessViewerUrl.Builder(element).toWebLink();
      }
    }
    var webStartable = findWebStartable(caseId, processId);
    //IStartElement element = ProcessStartAPI.findStartElementByProcessStartFriendlyRequestPath;
    if (webStartable instanceof IProcessWebStartable)
      return ProcessViewerUrl.Builder((IStartElement) webStartable);
    
    /*String projectName = getProjectNameFromProcessId(processId);
    
    IWebStartable selectedProcess = findWebStartable(caseId, processId);
    var selectedProcessId = selectedProcess.getId();
    var friendlyRequestPath = selectedProcessId.substring(selectedProcessId.indexOf(projectName) + projectName.length() + 1);
    IStartElement element = ProcessStartAPI.findStartElementByProcessStartFriendlyRequestPath(friendlyRequestPath);
    return new ProcessViewerUrl.Builder(element).toWebLink();*/

  }
  
  /**
   * Get Project Name From ProcessId
   * @param processId process id e.g "/designer/pro/InternalSupport/15C7B30FB93C827E/Appraisal.ivp"
   * @return project name or empty string ex. InternalSupport
   */
  private static String getProjectNameFromProcessId(String processId) {
    String[] projectNames = processId.split("/");
    String projectName = "";
    if(projectNames.length > PROJECT_NAME_INDEX) {
      projectName = projectNames[PROJECT_NAME_INDEX];
    }
    return projectName;
  }
  
  public static ICaseMap findCaseMapByCase(ICase caze) {
    if (Objects.isNull(caze)) {
      return null;
    }
    return Ivy.get(ICaseMapService.class).getCaseMapService(caze.getBusinessCase()).find().current();
  }
  
  public static IProcessWebStartable findProcessByProcessId(String id) {
    if (Objects.isNull(id)) {
      return null;
    }
    return Ivy.get(ICaseMapService.class).getCaseMapService(caze.getBusinessCase()).find().current();
  }
}

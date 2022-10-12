package com.axonivy.portal.components.util;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.constant.CustomFields;
import com.axonivy.portal.components.dto.ProcessViewerDTO;
import com.axonivy.portal.components.service.impl.ProcessService;

import ch.ivyteam.ivy.casemap.runtime.ICaseMapService;
import ch.ivyteam.ivy.casemap.runtime.model.ICaseMap;
import ch.ivyteam.ivy.casemap.viewer.api.CaseMapViewer;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.model.value.WebLink;
import ch.ivyteam.ivy.process.viewer.api.ProcessViewer;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;
import ch.ivyteam.ivy.workflow.start.ICaseMapWebStartable;
import ch.ivyteam.ivy.workflow.start.IProcessWebStartable;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessViewerUtils {

  public static ProcessViewerDTO initProcessViewer(Long caseId, String processLink) {
    boolean isViewerAllowed = false;
    IWebStartable webStartable = null;
    WebLink webLink = null;
    boolean isError = false;
    String errorMessage = null;
    if (caseId != 0 || StringUtils.isNotBlank(processLink)) {
      // init data using caseId
      ICase selectedCase = CaseUtils.findCase(caseId);
      isViewerAllowed = isViewerAllowed(selectedCase);
      if (isViewerAllowed) {
        ICaseMap caseMap = findCaseMapByCase(selectedCase);
        if (!Objects.isNull(caseMap)) {
          webStartable = findWebStartable(caseMap);
          webLink = CaseMapViewer.of(caseMap).url().toWebLink();
        } else {
          webStartable = findWebStartable(selectedCase.getProcessStart().getLink().getRelative());
          webLink = ProcessViewer.of(selectedCase).url().toWebLink();
        }
      }

      // try to init data using processLink
      if (webLink == null) {
        webStartable = findWebStartable(processLink);
        isViewerAllowed = isViewerAllowed(webStartable);
        if (isViewerAllowed) {
          if (webStartable instanceof ICaseMapWebStartable) {
            webLink = CaseMapViewer.of((ICaseMapWebStartable) webStartable).url().toWebLink();
          } else {
            webLink = ProcessViewer.of((IProcessWebStartable) webStartable).url().toWebLink();
          }
        }
      }
    }

    // check result
    if (webLink == null) {
      isError = true;
      errorMessage = webStartable != null && !isViewerAllowed
          ? Ivy.cms().co("/Dialogs/com/axonivy/portal/components/ProcessViewer/ProcessIsHidden")
          : Ivy.cms().co("/Dialogs/com/axonivy/portal/components/ProcessViewer/ProcessDataNotFound");
    }
    return new ProcessViewerDTO(webStartable, webLink, isError, errorMessage);
  }

  public static IWebStartable findWebStartable(ICaseMap caseMap) {
    if (caseMap != null) {
      return getWebStartables().stream().filter(filterById(caseMap.getUuid().toString())).findFirst().orElse(null);
    }
    return null;
  }

  public static IWebStartable findWebStartable(String processLink) {
    if (StringUtils.isNotBlank(processLink)) {
      return getWebStartables().stream().filter(filterByRelativeLink(processLink)).findFirst().orElse(null);
    }
    return null;
  }

  private static Predicate<? super IWebStartable> filterByRelativeLink(String startProcessId) {
    return webStartable -> StringUtils.equals(startProcessId, webStartable.getLink().getRelative());
  }

  private static Predicate<? super IWebStartable> filterById(String startProcessId) {
    return webStartable -> webStartable.getLink().getRelative().contains(startProcessId);
  }

  private static List<IWebStartable> getWebStartables() {
    return ProcessService.newInstance().findProcesses().getProcesses();
  }

  public static boolean isExpressCase(ICase iCase) {
    return iCase != null
        && BooleanUtils.toBoolean(iCase.customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).getOrNull());
  }

  public static ICaseMap findCaseMapByCase(ICase caze) {
    if (Objects.isNull(caze)) {
      return null;
    }
    return Ivy.get(ICaseMapService.class).getCaseMapService(caze.getBusinessCase()).find().current();
  }

  public static boolean hasCaseMap(IBusinessCase businessCase) {
    return findCaseMapByCase(businessCase) != null;
  }

  public static boolean isViewerAllowed(ICase caze) {
    if (caze == null || isExpressCase(caze) || !caze.isBusinessCase()) {
      return false;
    } else if (hasCaseMap(caze.getBusinessCase())) {
      return true;
    } else {
      return ProcessViewer.of(caze).isViewAllowed();
    }
  }

  public static boolean isViewerAllowed(IWebStartable webStartable) {
    if (webStartable == null) {
      return false;
    } else if (webStartable instanceof ICaseMapWebStartable) {
      return true;
    } else {
      return ProcessViewer.of((IProcessWebStartable) webStartable).isViewAllowed();
    }
  }
}

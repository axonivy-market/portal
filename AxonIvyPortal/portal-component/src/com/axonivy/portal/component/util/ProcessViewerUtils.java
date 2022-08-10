package com.axonivy.portal.component.util;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.component.constant.CustomFields;
import com.axonivy.portal.component.service.impl.ProcessService;

import ch.ivyteam.ivy.casemap.runtime.ICaseMapService;
import ch.ivyteam.ivy.casemap.runtime.model.ICaseMap;
import ch.ivyteam.ivy.casemap.runtime.start.CaseMapViewerUrl;
import ch.ivyteam.ivy.casemap.runtime.start.CaseMapViewerUrl.CaseMapViewerMode;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.model.value.WebLink;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.start.ICaseMapWebStartable;
import ch.ivyteam.ivy.workflow.start.IProcessWebStartable;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import ch.ivyteam.ivy.workflow.start.ProcessViewerUrl;
import ch.ivyteam.ivy.workflow.start.ProcessViewerUrl.ProcessViewerMode;

public class ProcessViewerUtils {

  public static IWebStartable findWebStartable(Long caseId, String processLink) {
    String startProcessLink = "";
    var selectedCase = caseId > 0 ? CaseUtils.findCase(caseId) : null;
    if (selectedCase != null && !isExpressCase(selectedCase)) {
      ICaseMap caseMap = findCaseMapByCase(selectedCase);
      if (!Objects.isNull(caseMap)) {
        return getWebStartables().stream()
            .filter(filterById(caseMap.getUuid().toString())).findFirst()
            .orElse(null);
      } else {
        startProcessLink = selectedCase.getProcessStart().getLink().getRelative();
      }
    } else {
      startProcessLink = processLink;
    }

    return findWebStartable(startProcessLink);
  }

  public static IWebStartable findWebStartable(String processLink) {
    if (StringUtils.isNotBlank(processLink)) {
      return getWebStartables().stream()
          .filter(filterByRelativeLink(processLink)).findFirst()
          .orElse(null);
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

  private static boolean isExpressCase(ICase iCase) {
    return BooleanUtils.toBoolean(iCase.customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).getOrNull());
  }

  public static WebLink getViewerWebLink(Long caseId, String processLink) {
    var selectedCase = caseId > 0 ? CaseUtils.findCase(caseId) : null;
    if (selectedCase != null && !isExpressCase(selectedCase)) {
      ICaseMap caseMap = findCaseMapByCase(selectedCase);
      if (!Objects.isNull(caseMap)) {
        return CaseMapViewerUrl.of(caseMap).mode(CaseMapViewerMode.VIEWER).toWebLink();
      } else {
        return ProcessViewerUrl.of(selectedCase).mode(ProcessViewerMode.VIEWER).toWebLink();
      }
    }

    var webStartable = findWebStartable(processLink);
    if(webStartable instanceof ICaseMapWebStartable) {
      return CaseMapViewerUrl.of((ICaseMapWebStartable) webStartable).mode(CaseMapViewerMode.VIEWER).toWebLink();
    } else {
      return ProcessViewerUrl.of((IProcessWebStartable) webStartable).mode(ProcessViewerMode.VIEWER).toWebLink();
    }
  }

  public static ICaseMap findCaseMapByCase(ICase caze) {
    if (Objects.isNull(caze)) {
      return null;
    }
    return Ivy.get(ICaseMapService.class).getCaseMapService(caze.getBusinessCase()).find().current();
  }
}

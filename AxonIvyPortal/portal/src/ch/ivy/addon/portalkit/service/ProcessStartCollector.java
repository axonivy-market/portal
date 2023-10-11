package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;
import com.axonivy.portal.components.util.ProcessStartUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;

public class ProcessStartCollector {
  private static final String EXPRESS_CREATE_FRIENDLY_REQUEST_PATH =
      "Start Processes/CreateWorkflow/AxonIvyExpressWF.ivp";
  private static final String EXPRESS_ADHOC_WF_FRIENDLY_REQUEST_PATH =
      "Start Processes/CreateWorkflow/AxonIvyExpressAdhocWF.ivp";
  private static final String EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH =
      "Start Processes/GenericPredefinedWorkflowStart/GenericPredefinedProcessStart.ivp";
  private static final String EXPRESS_WORKFLOW_EDIT_REQUEST_PATH =
      "Start Processes/GenericPredefinedWorkflowStart/GenericEditProcessStart.ivp";
  private static final String EXPRESS_BUSINESS_VIEW_REQUEST_PATH =
      "Start Processes/ExpressStart/startExpressBusinessView.ivp";

  private final List<IApplication> applicationsInSecurityContext;
  private static ProcessStartCollector instance;
  private static String expressWorkflowStartLink;

  public static ProcessStartCollector getInstance() {
    if (instance == null) {
      instance = new ProcessStartCollector();
    }
    return instance;
  }

  public ProcessStartCollector() {
    this.applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
  }

  /**
   * find friendly request path in specific pmv base on keyword
   * 
   * @param keyword
   * @param portalStartPmvId
   * @return user friendly request path
   */
  public String findFriendlyRequestPathContainsKeyword(String keyword, Object portalStartPmvId) {
    if (portalStartPmvId == null) {
      return findFriendlyRequestPathContainsKeywordInPMV(keyword, Ivy.wfTask().getProcessModelVersion());
    } else {
      for (IApplication app : applicationsInSecurityContext) {
        IProcessModelVersion findProcessModelVersion = app.findProcessModelVersion(portalStartPmvId);
        if (findProcessModelVersion != null) {
          return findFriendlyRequestPathContainsKeywordInPMV(keyword, findProcessModelVersion);
        }
      }
    }
    return StringUtils.EMPTY;
  }

  /**
   * find friendly request path in specific pmv base on keyword If pmv id not found, get pmv of current task
   * 
   * @param keyword
   * @param processModelVersion
   * @return user friendly request path
   */
//  public String findFriendlyRequestPathContainsKeyword(String keyword, IProcessModelVersion processModelVersion) {
//    if (processModelVersion == null) {
//      processModelVersion = Ivy.wfTask().getProcessModelVersion();
//    }
//    return findFriendlyRequestPathContainsKeywordInPMV(keyword, processModelVersion);
//  }

  private String findFriendlyRequestPathContainsKeywordInPMV(String keyword, IProcessModelVersion processModelVersion) {
    if (processModelVersion != null) {
      List<IProcessStart> processStarts =
          findProcessStartRequestPathContainsKeywordAndPmv(keyword, processModelVersion);
      if (CollectionUtils.isNotEmpty(processStarts)) {
        return processStarts.get(0).getUserFriendlyRequestPath();
      }
    }
    return StringUtils.EMPTY;
  }

  private List<IProcessStart> findProcessStartRequestPathContainsKeywordAndPmv(String keyword,
      IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv = IWorkflowProcessModelVersion.of(processModelVersion);
    return workflowPmv.getProcessStarts().stream()
        .filter(processStart -> processStart.getUserFriendlyRequestPath().contains(keyword))
        .collect(Collectors.toList());
  }

  public String findExpressAdhocWFLink() {
    return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(EXPRESS_ADHOC_WF_FRIENDLY_REQUEST_PATH);
  }

  public String findExpressWorkflowStartLink() {
    if (StringUtils.isEmpty(expressWorkflowStartLink)) {
      expressWorkflowStartLink =
          ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH);
    }
    return expressWorkflowStartLink;
  }

  public String findExpressBusinessViewStartLink() {
    return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(EXPRESS_BUSINESS_VIEW_REQUEST_PATH);
  }

  public IProcessStart findExpressCreationProcess() {
    return ProcessStartUtils.findProcessStartByUserFriendlyRequestPath(EXPRESS_CREATE_FRIENDLY_REQUEST_PATH);
  }

  public String findExpressWorkflowEditLink(String workflowId) {
    String url = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(EXPRESS_WORKFLOW_EDIT_REQUEST_PATH);
    if (StringUtils.isNotBlank(url)) {
      return String.format("%s?workflowID=%s", url, workflowId);
    }
    return StringUtils.EMPTY;
  }
}

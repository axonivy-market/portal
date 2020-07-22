package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;

public class ProcessStartCollector {
  private final IApplication application;
  private static final String EXPRESS_CREATE_FRIENDLY_REQUEST_PATH =
      "Start Processes/CreateWorkflow/AxonIvyExpressWF.ivp";
  private static final String EXPRESS_ADHOC_WF_FRIENDLY_REQUEST_PATH =  "Start Processes/CreateWorkflow/AxonIvyExpressAdhocWF.ivp";
  private static final String EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH =
      "Start Processes/GenericPredefinedWorkflowStart/GenericPredefinedProcessStart.ivp";
  private static final String EXPRESS_WORKFLOW_EDIT_REQUEST_PATH =
      "Start Processes/GenericPredefinedWorkflowStart/GenericEditProcessStart.ivp";
  private static final String EXPRESS_BUSINESS_VIEW_REQUEST_PATH = "Start Processes/ExpressStart/startExpressBusinessView.ivp";

  public ProcessStartCollector(IApplication application) {
    this.application = application;
  }

  public List<IProcessStart> findProcessStartRequestPathContainsKeyword(String keyword) {
    List<IProcessStart> processStarts = new ArrayList<>();
    if (isActive(application)) {
      if (Ivy.request().getApplication().equals(application)) {
        processStarts =
            findProcessStartRequestPathContainsKeywordAndPmv(keyword, Ivy.wfTask().getProcessModelVersion());
      }
      if (CollectionUtils.isNotEmpty(processStarts)) {
        return processStarts;
      }

      List<IProcessModel> processModels = application.getProcessModelsSortedByName();

      for (IProcessModel processModel : processModels) {
        Optional<List<IProcessStart>> processStartsOptional =
            Optional.of(processModel).filter(this::isActive).map(IProcessModel::getReleasedProcessModelVersion)
                .filter(this::isActive).map(p -> findProcessStartRequestPathContainsKeywordAndPmv(keyword, p))
                .filter(CollectionUtils::isNotEmpty);
        if (processStartsOptional.isPresent()) {
          return processStartsOptional.get();
        }
      }
    }
    return processStarts;
  }
  
  /**
   * find friendly request path in specific pmv base on keyword
   * If pmv id not found, get pmv of current task 
   * @param keyword
   * @param portalStartPmvId
   * @return user friendly request path
   */
  public String findFriendlyRequestPathContainsKeyword(String keyword, Object portalStartPmvId) {
    IProcessModelVersion findProcessModelVersion = portalStartPmvId == null ? Ivy.wfTask().getProcessModelVersion() : 
      application.findProcessModelVersion(portalStartPmvId);
    if (findProcessModelVersion != null){
      List<IProcessStart> processStarts = findProcessStartRequestPathContainsKeywordAndPmv(keyword, findProcessModelVersion);
      if (CollectionUtils.isNotEmpty(processStarts)){
        return processStarts.get(0).getUserFriendlyRequestPath();
      }
    }
    return StringUtils.EMPTY;
  }

  public IProcessStart findStartableProcessStartByUserFriendlyRequestPath(String requestPath) {
    if (isActive(application)) {
      List<IProcessModel> processModels = application.getProcessModelsSortedByName();
      for (IProcessModel processModel : processModels) {
        Optional<IProcessStart> processStartOptional =
            Optional.of(processModel).filter(this::isActive).map(IProcessModel::getReleasedProcessModelVersion)
                .filter(this::isActive).map(p -> getProcessStart(requestPath, p))
                .filter(processStart -> isStartableProcessStart(processStart.getFullUserFriendlyRequestPath()));
        if (processStartOptional.isPresent()) {
          return processStartOptional.get();
        }
      }
    }
    return null;
  }

  private IProcessStart getProcessStart(String requestPath, IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv =
        WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
    return workflowPmv.findStartElementByUserFriendlyRequestPath(requestPath);
  }
  
  private boolean isStartableProcessStart(String fullUserFriendlyRequestPath) {
	  return Ivy.session().getStartableProcessStarts()
			  .stream()
			  .map(IProcessStart::getFullUserFriendlyRequestPath)
			  .filter(startablePorcessRequestPath -> startablePorcessRequestPath.equals(fullUserFriendlyRequestPath))
			  .findFirst().isPresent();
  }

  private List<IProcessStart> findProcessStartRequestPathContainsKeywordAndPmv(String keyword,
      IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv =
        WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
    return workflowPmv.getProcessStarts().stream()
        .filter(processStart -> processStart.getUserFriendlyRequestPath().contains(keyword))
        .collect(Collectors.toList());
  }
  
  public String findExpressAdhocWFLink() {
    return ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(application, EXPRESS_ADHOC_WF_FRIENDLY_REQUEST_PATH);
  }

  public String findExpressWorkflowStartLink() {
    return ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(application, EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH);
  }

  public String findExpressBusinessViewStartLink() {
    return ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(application, EXPRESS_BUSINESS_VIEW_REQUEST_PATH);
  }

  public String findCreateExpressWorkflowStartLink() {
    return ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(application, EXPRESS_CREATE_FRIENDLY_REQUEST_PATH);
  }

  public IProcessStart findExpressCreationProcess() {
    return ProcessStartUtils.findProcessStartByUserFriendlyRequestPath(application, EXPRESS_CREATE_FRIENDLY_REQUEST_PATH);
  }

  public String findExpressWorkflowEditLink(String workflowId) {
    return IvyExecutor.executeAsSystem(() -> {
      String url = ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(application, EXPRESS_WORKFLOW_EDIT_REQUEST_PATH);
      if (StringUtils.isNoneBlank(url)) {
        return url + "?workflowID=" + workflowId;
      }
      return StringUtils.EMPTY;
    });
  }

  public String findStartableLinkByUserFriendlyRequestPath(String requestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      IProcessStart processStart = findStartableProcessStartByUserFriendlyRequestPath(requestPath);
    	  return processStart != null ? processStart.getLink().getRelative() : StringUtils.EMPTY; 
    });
  }

  private boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
  }

  private boolean isActive(IProcessModel processModel) {
    return processModel.getActivityState() == ActivityState.ACTIVE;
  }

  private boolean isActive(IApplication ivyApplication) {
    return ivyApplication.getActivityState() == ActivityState.ACTIVE;
  }
}

package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;

public class ProcessStartCollector {
  @SuppressWarnings("unused")
  @Deprecated(forRemoval = true, since = "9.4")
  private final IApplication application;
  private final List<IApplication> applicationsInSecurityContext;
  private static final String EXPRESS_CREATE_FRIENDLY_REQUEST_PATH =
      "Start Processes/CreateWorkflow/AxonIvyExpressWF.ivp";
  private static final String EXPRESS_ADHOC_WF_FRIENDLY_REQUEST_PATH =  "Start Processes/CreateWorkflow/AxonIvyExpressAdhocWF.ivp";
  private static final String EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH =
      "Start Processes/GenericPredefinedWorkflowStart/GenericPredefinedProcessStart.ivp";
  private static final String EXPRESS_WORKFLOW_EDIT_REQUEST_PATH =
      "Start Processes/GenericPredefinedWorkflowStart/GenericEditProcessStart.ivp";
  private static final String EXPRESS_BUSINESS_VIEW_REQUEST_PATH = "Start Processes/ExpressStart/startExpressBusinessView.ivp";

  public ProcessStartCollector() {
    this.application = null;
    this.applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
    
  }
  
  /**
   * @param application 
   * @deprecated Use ProcessStartCollector() instead
   */
  @Deprecated(forRemoval = true, since = "9.4")
  public ProcessStartCollector(IApplication application) {
    this.application = application;
    this.applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
  }
  
  /**
   * find friendly request path in specific pmv base on keyword
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
        if(findProcessModelVersion != null) {
          return findFriendlyRequestPathContainsKeywordInPMV(keyword, findProcessModelVersion);
        }
      }
    }
    return StringUtils.EMPTY;
  }

  /**
   * find friendly request path in specific pmv base on keyword
   * If pmv id not found, get pmv of current task
   * @param keyword
   * @param processModelVersion
   * @return user friendly request path
   */
  public String findFriendlyRequestPathContainsKeyword(String keyword, IProcessModelVersion processModelVersion) {
    if (processModelVersion == null) {
      processModelVersion = Ivy.wfTask().getProcessModelVersion();
    }
    return findFriendlyRequestPathContainsKeywordInPMV(keyword, processModelVersion);
  }

  private String findFriendlyRequestPathContainsKeywordInPMV(String keyword, IProcessModelVersion processModelVersion) {
    if (processModelVersion != null){
      List<IProcessStart> processStarts = findProcessStartRequestPathContainsKeywordAndPmv(keyword, processModelVersion);
      if (CollectionUtils.isNotEmpty(processStarts)){
        return processStarts.get(0).getUserFriendlyRequestPath();
      }
    }
    return StringUtils.EMPTY;
  }

  private IProcessStart findStartableProcessStartByUserFriendlyRequestPath(String requestPath) {
    List<IProcessModel> processModels = applicationsInSecurityContext.stream()
                                        .map(IApplication::getProcessModelsSortedByName)
                                        .flatMap(List::stream)
                                        .collect(Collectors.toList());
    for (IProcessModel processModel : processModels) {
      Optional<IProcessStart> processStartOptional = Optional.of(processModel)
        .filter(this::isActive)
        .map(IProcessModel::getReleasedProcessModelVersion)
        .filter(this::isActive)
        .map(p -> getProcessStart(requestPath, p))
        .filter(processStart -> isStartableProcessStart(processStart.getFullUserFriendlyRequestPath()));
      if (processStartOptional.isPresent()) {
        return processStartOptional.get();
      }
    }
    return null;
  }

  private IProcessStart getProcessStart(String requestPath, IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv = IWorkflowProcessModelVersion.of(processModelVersion);
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
    IWorkflowProcessModelVersion workflowPmv = IWorkflowProcessModelVersion.of(processModelVersion);
    return workflowPmv
        .getProcessStarts()
        .stream()
        .filter(processStart -> processStart.getUserFriendlyRequestPath().contains(keyword))
        .collect(Collectors.toList());
  }
  
  public String findExpressAdhocWFLink() {
    return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(EXPRESS_ADHOC_WF_FRIENDLY_REQUEST_PATH); 
  }

  public String findExpressWorkflowStartLink() {
    return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH);
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
      return  String.format("%s?workflowID=%s", url, workflowId);
    }
    return StringUtils.EMPTY;
  }

  /**
   * Find start link from friendly request path
   * @deprecated Use {@link ProcessStartAPI#findStartableLinkByUserFriendlyRequestPath(String)} instead
   * @param requestPath 
   * @return start link or empty string
   */
  @Deprecated
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
}

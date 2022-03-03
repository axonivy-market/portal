package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;
import ch.ivyteam.util.Pair;

public class ProcessStartCollector {
  private final IApplication application;
  private static final String ACM_FRIENDLY_REQUEST_PATH = "BusinessProcesses/AdHocWF/start.ivp";
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

  public IProcessStart findProcessStartByUserFriendlyRequestPath(String requestPath) {
    IProcessStart processStart = null;
    if (isActive(application)) {
      if (Ivy.request().getApplication().equals(application)) {
        processStart =
            findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, Ivy.wfTask().getProcessModelVersion());
      }
      if (processStart != null) {
        return processStart;
      }

      List<IProcessModel> processModels = application.getProcessModelsSortedByName();

      for (IProcessModel processModel : processModels) {
        Optional<IProcessStart> processStartOptional =
            Optional.of(processModel).filter(this::isActive).map(IProcessModel::getReleasedProcessModelVersion)
                .filter(this::isActive).map(p -> findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, p))
                .filter(Objects::nonNull);
        if (processStartOptional.isPresent()) {
          return processStartOptional.get();
        }
      }
    }
    return processStart;
  }

  public IProcessStart findStartableProcessStartByUserFriendlyRequestPath(String requestPath) {
    if (isActive(application)) {
      List<IProcessModel> processModels = application.getProcessModelsSortedByName();
      for (IProcessModel processModel : processModels) {
        Optional<IProcessStart> processStartOptional =
            Optional.of(processModel).filter(this::isActive).map(IProcessModel::getReleasedProcessModelVersion)
                .filter(this::isActive).map(p -> findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, p))
                .filter(processStart -> Ivy.session().getStartableProcessStarts().contains(processStart));
        if (processStartOptional.isPresent()) {
          return processStartOptional.get();
        }
      }
    }
    return null;
  }

  private IProcessStart findProcessStartByUserFriendlyRequestPathAndPmv(String requestPath,
      IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv =
        WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
    return workflowPmv.findProcessStartByUserFriendlyRequestPath(requestPath);
  }

  private List<IProcessStart> findProcessStartRequestPathContainsKeywordAndPmv(String keyword,
      IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv =
        WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
    return workflowPmv.getProcessStarts().stream()
        .filter(processStart -> processStart.getUserFriendlyRequestPath().contains(keyword))
        .collect(Collectors.toList());
  }

  public String findACMLink() {
    return ProcessStartAPI.findLinkByFriendlyRequestPath(application, ACM_FRIENDLY_REQUEST_PATH);
  }
  
  public String findExpressAdhocWFLink() {
    return ProcessStartAPI.findLinkByFriendlyRequestPath(application, EXPRESS_ADHOC_WF_FRIENDLY_REQUEST_PATH);
  }

  public String findExpressWorkflowStartLink() {
    return ProcessStartAPI.findLinkByFriendlyRequestPath(application, EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH);
  }

  public String findExpressBusinessViewStartLink() {
    return ProcessStartAPI.findLinkByFriendlyRequestPath(application, EXPRESS_BUSINESS_VIEW_REQUEST_PATH);
  }

  public String findCreateExpressWorkflowStartLink() {
    return ProcessStartAPI.findLinkByFriendlyRequestPath(application, EXPRESS_CREATE_FRIENDLY_REQUEST_PATH);
  }

  public IProcessStart findExpressCreationProcess() {
    return IvyExecutor.executeAsSystem(() -> {
      ProcessStartCollector collector = new ProcessStartCollector(application);
      return collector.findProcessStartByUserFriendlyRequestPath(EXPRESS_CREATE_FRIENDLY_REQUEST_PATH);
    });
  }

  public String findExpressWorkflowEditLink(String workflowId) {
    return IvyExecutor.executeAsSystem(() -> {
      ProcessStartCollector collector = new ProcessStartCollector(application);
      IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath(EXPRESS_WORKFLOW_EDIT_REQUEST_PATH);
      if (process != null) {
        Pair<String, String> workflowIdParam = new Pair<>("workflowID", workflowId);
        return RequestUriFactory.createProcessStartUri(process, workflowIdParam).toASCIIString();
      }
      return StringUtils.EMPTY;
    });
  }
  
  /**
   * @deprecated Use {@link ProcessStartAPI#findLinkByFriendlyRequestPath(IApplication, String)} instead
   * @param friendlyRequestPath
   * @return link
   */
  @Deprecated(since="8.0.13", forRemoval = true)
  public String findLinkByFriendlyRequestPath(String friendlyRequestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      ProcessStartCollector collector = new ProcessStartCollector(application);
      IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath(friendlyRequestPath);
      if (process != null) {
        return RequestUriFactory.createProcessStartUri(process).toASCIIString();
      }
      return StringUtils.EMPTY;
    });
  }

  /**
   * @deprecated Use {@link ProcessStartAPI#findStartableLinkByUserFriendlyRequestPath(IApplication, String)} instead
   * @param requestPath
   * @return link which login user can start
   */
  @Deprecated(since="8.0.13", forRemoval = true)
  public String findStartableLinkByUserFriendlyRequestPath(String requestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      IProcessStart processStart = findStartableProcessStartByUserFriendlyRequestPath(requestPath);
      if (processStart != null) {
        return RequestUriFactory.createProcessStartUri(processStart).toASCIIString();
      }
      return StringUtils.EMPTY;
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

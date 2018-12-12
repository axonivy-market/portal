package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;
import ch.ivyteam.util.Pair;

public class ProcessStartCollector {
  private final IApplication application;
  private final String ACM_FRIENDLY_REQUEST_PATH = "BusinessProcesses/AdHocWF/start.ivp";
  private final String EXPRESS_CREATE_FRIENDLY_REQUEST_PATH = "Start Processes/CreateWorkflow/AxonIvyExpressWF.ivp";
  private final String EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH =
      "Start Processes/GenericPredefinedWorkflowStart/GenericPredefinedProcessStart.ivp";
  private final String EXPRESS_WORKFLOW_EDIT_REQUEST_PATH =
      "Start Processes/GenericPredefinedWorkflowStart/GenericEditProcessStart.ivp";

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

        if (isActive(processModel)) {
          IProcessModelVersion processModelVersion = processModel.getReleasedProcessModelVersion();

          if (isActive(processModelVersion)) {
            processStarts = findProcessStartRequestPathContainsKeywordAndPmv(keyword, processModelVersion);
            if (CollectionUtils.isNotEmpty(processStarts)) {
              break;
            }
          }
        }
      }
    }
    return processStarts;
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

        if (isActive(processModel)) {
          IProcessModelVersion processModelVersion = processModel.getReleasedProcessModelVersion();

          if (isActive(processModelVersion)) {
            processStart = findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, processModelVersion);
            if (processStart != null) {
              return processStart;
            }
          }
        }
      }
    }
    return processStart;
  }
  
  public IProcessStart findStartableProcessStartByUserFriendlyRequestPath(String requestPath) throws Exception {
    if (isActive(application)) {
      List<IProcessModel> processModels = application.getProcessModelsSortedByName();

      for (IProcessModel processModel : processModels) {

        if (isActive(processModel)) {
          IProcessModelVersion processModelVersion = processModel.getReleasedProcessModelVersion();

          if (isActive(processModelVersion)) {
            IWorkflowProcessModelVersion workflowPmv =
                WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
            List<IProcessStart> sessionStartableProcessStarts = Ivy.session().getStartableProcessStarts();
            IProcessStart processStart = workflowPmv.findProcessStartByUserFriendlyRequestPath(requestPath);
            if (processStart != null && sessionStartableProcessStarts.contains(processStart)) {
              return processStart;
            }
          }
        }
      }
    }
    return null;
  }
  
  private IProcessStart findProcessStartByUserFriendlyRequestPathAndPmv(String requestPath,
      IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv =
        WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
    IProcessStart processStart = workflowPmv.findProcessStartByUserFriendlyRequestPath(requestPath);
    return processStart;
  }

  private List<IProcessStart> findProcessStartRequestPathContainsKeywordAndPmv(String keyword,
      IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv =
        WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
    return workflowPmv.getProcessStarts().stream()
        .filter(processStart -> processStart.getUserFriendlyRequestPath().contains(keyword))
        .collect(Collectors.toList());
  }

  public String findACMLink() throws Exception {
    return findLinkByFriendlyRequestPath(ACM_FRIENDLY_REQUEST_PATH);
  }

  public String findExpressWorkflowStartLink() throws Exception {
    return findLinkByFriendlyRequestPath(EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH);
  }

  public String findCreateExpressWorkflowStartLink() throws Exception {
    return findLinkByFriendlyRequestPath(EXPRESS_CREATE_FRIENDLY_REQUEST_PATH);
  }

  public String findLinkByFriendlyRequestPath(String friendlyRequestPath) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
      @Override
      public String call() throws Exception {
        ProcessStartCollector collector = new ProcessStartCollector(application);
        IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath(friendlyRequestPath);
        if (process != null) {
          return RequestUriFactory.createProcessStartUri(
              ServerFactory.getServer().getApplicationConfigurationManager(), process).toString();
        }
        return StringUtils.EMPTY;
      }
    });
  }


  public IProcessStart findCreateExpressWorlflowProcess() throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<IProcessStart>() {
      @Override
      public IProcessStart call() throws Exception {
        ProcessStartCollector collector = new ProcessStartCollector(application);
        return collector.findProcessStartByUserFriendlyRequestPath(EXPRESS_CREATE_FRIENDLY_REQUEST_PATH);
      }
    });
  }

  public String findExpressWorkflowEditLink(String workflowId) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
      @Override
      public String call() throws Exception {
        ProcessStartCollector collector = new ProcessStartCollector(application);
        IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath(EXPRESS_WORKFLOW_EDIT_REQUEST_PATH);
        if (process != null) {
          Pair<String, String> workflowIdParam = new Pair<String, String>("workflowID", workflowId);
          return RequestUriFactory.createProcessStartUri(
              ServerFactory.getServer().getApplicationConfigurationManager(), process, workflowIdParam).toString();
        }
        return StringUtils.EMPTY;
      }
    });
  }

  public String findStartableLinkByUserFriendlyRequestPath(String requestPath) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {

      @Override
      public String call() throws Exception {
        IProcessStart processStart = findStartableProcessStartByUserFriendlyRequestPath(requestPath);
        if(processStart != null) {
          return RequestUriFactory.createProcessStartUri(
              ServerFactory.getServer().getApplicationConfigurationManager(), processStart).toString();
        }
        return StringUtils.EMPTY;
      }
    });

  }

  private boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
  }

  private boolean isActive(IProcessModel processModel) {
    return processModel.getActivityState() == ActivityState.ACTIVE;
  }

  private boolean isActive(IApplication application) {
    return application.getActivityState() == ActivityState.ACTIVE;
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
}

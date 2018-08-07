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
  private final String CREATE_EXPRESS_LINK = "Start Processes/CreateWorkflow/AxonIvyExpressWF.ivp";

  public ProcessStartCollector(IApplication application) {
    this.application = application;
  }
  
  public List<IProcessStart> findProcessStartRequestPathContainsKeyword(String keyword) {
    List<IProcessStart> processStarts = new ArrayList<>();
    if (isActive(this.application)) {
      if (Ivy.request().getApplication().equals(application)) {
        processStarts =
            findProcessStartRequestPathContainsKeywordAndPmv(keyword, Ivy.wfTask().getProcessModelVersion());
      }
      if (CollectionUtils.isNotEmpty(processStarts)) {
        return processStarts;
      }

      List<IProcessModel> processModels = this.application.getProcessModelsSortedByName();

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
    if (isActive(this.application)) {
      if (Ivy.request().getApplication().equals(application)) {
        processStart = findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, Ivy.wfTask().getProcessModelVersion());
      }
      if (processStart != null) {
        return processStart;
      }
      
      List<IProcessModel> processModels = this.application.getProcessModelsSortedByName();

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

  private IProcessStart findProcessStartByUserFriendlyRequestPathAndPmv(String requestPath,
      IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv =
        WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
    IProcessStart processStart = workflowPmv.findProcessStartByUserFriendlyRequestPath(requestPath);
    return processStart;
  }
  
  private List<IProcessStart> findProcessStartRequestPathContainsKeywordAndPmv(String keyword, IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv =
        WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
    return workflowPmv.getProcessStarts().stream()
        .filter(processStart -> processStart.getUserFriendlyRequestPath().contains(keyword))
        .collect(Collectors.toList());
  }

  public String findACMLink() throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
      @Override
      public String call() throws Exception {
        ProcessStartCollector collector = new ProcessStartCollector(application);
        IProcessStart process =
            collector.findProcessStartByUserFriendlyRequestPath("BusinessProcesses/AdHocWF/start.ivp");
        if (process != null) {
          return RequestUriFactory.createProcessStartUri(
              ServerFactory.getServer().getApplicationConfigurationManager(), process).toString();
        }
        return StringUtils.EMPTY;
      }
    });
  }
  
  public String findExpressWorkflowStartLink() throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
      @Override
      public String call() throws Exception {
        ProcessStartCollector collector = new ProcessStartCollector(application);
        IProcessStart process =
            collector.findProcessStartByUserFriendlyRequestPath("Start Processes/GenericPredefinedWorkflowStart/GenericPredefinedProcessStart.ivp");
        if (process != null) {
          return RequestUriFactory.createProcessStartUri(
              ServerFactory.getServer().getApplicationConfigurationManager(), process).toString();
        }
        return StringUtils.EMPTY;
      }
    });
  }
  

  public String findCreateExpressWorkflowStartLink() throws Exception {
	    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
	      @Override
	      public String call() throws Exception {
	        ProcessStartCollector collector = new ProcessStartCollector(application);
	        IProcessStart process =
	            collector.findProcessStartByUserFriendlyRequestPath(CREATE_EXPRESS_LINK);
	        if (process != null) {
	          return RequestUriFactory.createProcessStartUri(
	              ServerFactory.getServer().getApplicationConfigurationManager(), process).toString();
	        }
	        return StringUtils.EMPTY;
	      }
	    });
	  }

  public IProcessStart findCreateExpressWorlflowProcess() throws Exception{
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<IProcessStart>() {
      @Override
      public IProcessStart call() throws Exception {
        ProcessStartCollector collector = new ProcessStartCollector(application);
        return collector.findProcessStartByUserFriendlyRequestPath(CREATE_EXPRESS_LINK);
      }
    });
  }

  public String findExpressWorkflowEditLink(String workflowId) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
      @Override
      public String call() throws Exception {
        ProcessStartCollector collector = new ProcessStartCollector(application);
        IProcessStart process =
            collector.findProcessStartByUserFriendlyRequestPath("Start Processes/GenericPredefinedWorkflowStart/GenericEditProcessStart.ivp");
        if (process != null) {
          Pair<String, String> workflowIdParam = new Pair<String, String>("workflowID", workflowId);
          return RequestUriFactory.createProcessStartUri(
              ServerFactory.getServer().getApplicationConfigurationManager(), process, workflowIdParam).toString();
        }
        return StringUtils.EMPTY;
      }
    });
  }

  private boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && ActivityState.ACTIVE.equals(processModelVersion.getActivityState());
  }

  private boolean isActive(IProcessModel processModel) {
    return ActivityState.ACTIVE.equals(processModel.getActivityState());
  }

  private boolean isActive(IApplication application) {
    return ActivityState.ACTIVE.equals(application.getActivityState());
  }
}

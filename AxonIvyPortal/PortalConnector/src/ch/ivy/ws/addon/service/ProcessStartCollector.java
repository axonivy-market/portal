package ch.ivy.ws.addon.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.ws.addon.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;

public class ProcessStartCollector {

  private final IApplication application;

  public ProcessStartCollector(IApplication application) {
    this.application = application;
  }

  public IProcessStart findProcessStartByUserFriendlyRequestPath(String requestPath) {
    if (application != null && !isActive(application)) {
      List<IProcessModel> processModels = application.getProcessModelsSortedByName();
      Optional<IProcessModelVersion> processModelVersion =
          processModels.stream().filter(this::isActive).map(IProcessModel::getReleasedProcessModelVersion)
              .filter(this::isActive).findFirst();
      if (processModelVersion.isPresent()) {
        IWorkflowProcessModelVersion workflowPmv = getWorkflowProcessModelVersion(processModelVersion.get());
        IProcessStart processStart = workflowPmv.findProcessStartByUserFriendlyRequestPath(requestPath);
        if (processStart != null) {
          return processStart;
        }
      }
    }
    return null;
  }

  private IWorkflowProcessModelVersion getWorkflowProcessModelVersion(IProcessModelVersion processModelVersion) {
    return WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
  }

  public String findACMLink() {
    return IvyExecutor.executeAsSystem(new Callable<String>() {
      @Override
      public String call() {
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

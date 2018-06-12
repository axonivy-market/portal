package ch.ivy.ws.addon.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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
    if (isActive(this.application)) {
      List<IProcessModel> processModels = this.application.getProcessModelsSortedByName();

      List<IProcessModelVersion> processModelVersions =
          processModels.stream().filter(this::isActive).map(IProcessModel::getReleasedProcessModelVersion)
              .filter(this::isActive).collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(processModelVersions)) {
        Optional<IProcessStart> processStart = processModelVersions.stream()
            .map(p -> findProcessStart(p, requestPath)).filter(Objects::nonNull).findFirst();
        if (processStart.isPresent()) {
          return processStart.get();
        }
      }
    }
    return null;
  }

  private IProcessStart findProcessStart(IProcessModelVersion processModelVersion, String requestPath) {
    IWorkflowProcessModelVersion workflowPmv = WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
    return workflowPmv.findProcessStartByUserFriendlyRequestPath(requestPath);
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

  private boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && ActivityState.ACTIVE == processModelVersion.getActivityState();
  }

  private boolean isActive(IProcessModel processModel) {
    return processModel != null && ActivityState.ACTIVE == processModel.getActivityState();
  }

  private boolean isActive(IApplication application) {
    return application != null && ActivityState.ACTIVE == application.getActivityState();
  }
}

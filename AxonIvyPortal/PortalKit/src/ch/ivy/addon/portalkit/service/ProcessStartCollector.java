package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
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

      for (IProcessModel processModel : processModels) {

        if (isActive(processModel)) {
          IProcessModelVersion processModelVersion = processModel.getReleasedProcessModelVersion();

          if (isActive(processModelVersion)) {
            IWorkflowProcessModelVersion workflowPmv =
                WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
            IProcessStart processStart = workflowPmv.findProcessStartByUserFriendlyRequestPath(requestPath);
            if (processStart != null) {
              return processStart;
            }
          }
        }
      }
    }
    return null;
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

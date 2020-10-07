package ch.ivy.addon.portalkit.util;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;

public class ProcessStartUtils {

  private ProcessStartUtils() {}

  public static boolean isExpressProcessAdded(ExpressProcess expressProcess, List<UserProcess> userProcesses) {
    return userProcesses.stream()
    .filter(process -> StringUtils.isNoneBlank(process.getWorkflowId()) && process.getWorkflowId().equals(expressProcess.getId()))
    .findFirst()
    .isPresent();
  }

  public static IProcessStart findProcessStartByUserFriendlyRequestPath(String requestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      IApplication application = Ivy.wf().getApplication();
      IProcessStart processStart = null;
      processStart = findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, Ivy.request().getProcessModelVersion());
      if (processStart != null) {
        return processStart;
      }

      List<IProcessModel> processModels = application.getProcessModelsSortedByName();

      for (IProcessModel processModel : processModels) {
        Optional<IProcessStart> processStartOptional =
            Optional.of(processModel).filter(pm -> isActive(pm)).map(IProcessModel::getReleasedProcessModelVersion)
                .filter(pmv -> isActive(pmv)).map(p -> findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, p))
                .filter(Objects::nonNull);
        if (processStartOptional.isPresent()) {
          return processStartOptional.get();
        }
      }
      return processStart;
    });
  }

  public static String findRelativeUrlByProcessStartFriendlyRequestPath(String friendlyRequestPath) {
    IProcessStart processStart = findProcessStartByUserFriendlyRequestPath(friendlyRequestPath);
    return processStart != null ? processStart.getLink().getRelative() : StringUtils.EMPTY;
  }

  private static IWorkflowProcessModelVersion getWorkflowProcessModelVersion(IProcessModelVersion processModelVersion) {
    if (processModelVersion == null) {
      return null;
    }
    return processModelVersion.getAdapter(IWorkflowProcessModelVersion.class);
  }

  private static IProcessStart findProcessStartByUserFriendlyRequestPathAndPmv(String requestPath,
      IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv = getWorkflowProcessModelVersion(processModelVersion);
    return workflowPmv.findStartElementByUserFriendlyRequestPath(requestPath);
  }
  
  private static boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
  }

  private static boolean isActive(IProcessModel processModel) {
    return processModel.getActivityState() == ActivityState.ACTIVE;
  }
}

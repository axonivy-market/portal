package ch.ivy.addon.portalkit.util;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivyteam.ivy.scripting.objects.List;

public class ProcessStartsUtil {

  private ProcessStartsUtil() {}

  public static boolean isExpressProcessAdded(ExpressProcess expressProcess, List<UserProcess> userProcesses) {
    return userProcesses.stream()
    .filter(process -> StringUtils.isNoneBlank(process.getWorkflowId()) && process.getWorkflowId().equals(expressProcess.getId()))
    .findFirst()
    .isPresent();
  }
}

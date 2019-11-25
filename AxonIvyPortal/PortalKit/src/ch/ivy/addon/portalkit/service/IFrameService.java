package ch.ivy.addon.portalkit.service;

import java.util.Optional;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class IFrameService {

  /**
   * @param taskId
   * @return
   */
  public static boolean embedInFrame(Long taskId) {
    ITask task = Ivy.wf().findTask(taskId);
    if (task == null) {
      return true;
    }
    boolean isExpress = Boolean
        .parseBoolean(task.getCase().customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).getOrDefault("false"));
    Boolean isIFrameCustomField = IvyAdapterService.getEmbedInIFrameCustomField(task);
    RegisteredApplicationService service = new RegisteredApplicationService();
    return isExpress ? false
        : isIFrameCustomField != null ? isIFrameCustomField
            : Optional.ofNullable(service.findByName(task.getApplication().getName())).orElse(new Application())
                .isEmbedInFrame();
  }
}

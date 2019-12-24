package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
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
    if (isExpress) {
      return false;
    }
    
    Boolean isIFrameCustomField = IvyAdapterService.getEmbedInIFrameCustomField(task);
    if (isIFrameCustomField != null) {
      return isIFrameCustomField;
    }
    
    RegisteredApplicationService service = new RegisteredApplicationService();
    Application app = service.findByName(task.getApplication().getName());
    if (app != null) {
      return app.isEmbedInFrame();
    }
    
    return new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.EMBED_IN_FRAME.toString());
  }
}

package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class IFrameService {

  /**
   * @param taskId
   * @return whether embed task in IFrame
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
    
    Boolean isIFrameCustomField = getEmbedInIFrameCustomField(task);
    if (isIFrameCustomField != null) {
      return isIFrameCustomField;
    }
    
    return new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.EMBED_IN_FRAME);
  }

  /**
   * @param task
   * @return whether task embed in IFrame
   */
  private static Boolean getEmbedInIFrameCustomField(ITask task) {
    String embedInIFrame = task.customFields().stringField(CustomFields.EMBED_IN_FRAME).getOrNull();
    if (embedInIFrame == null) {
      embedInIFrame = task.getCase().customFields().stringField(CustomFields.EMBED_IN_FRAME).getOrNull();
    }
    return embedInIFrame != null ? Boolean.valueOf(embedInIFrame) : null;
  }
}

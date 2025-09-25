package ch.ivy.addon.portalkit.service;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

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
    
    Boolean isIFrameCustomField = getEmbedInIFrameCustomField(task);
    if (isIFrameCustomField != null) {
      return isIFrameCustomField;
    }
    
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.EMBED_IN_FRAME);
  }
  
  public static boolean embedInFrame(String taskUUID) {
    ITask task = Ivy.wf().findTask(taskUUID);
    if (task == null) {
      return true;
    }
    
    Boolean isIFrameCustomField = getEmbedInIFrameCustomField(task);
    if (isIFrameCustomField != null) {
      return isIFrameCustomField;
    }
    
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.EMBED_IN_FRAME);
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

  /**
   * @param process
   * @return whether request start has embed in IFrame
   */
  public static String embedInFrame(DashboardProcess process) {
    String embedInFrame = null;
    Object nestedProcess = process.getProcess();
    if (nestedProcess instanceof IWebStartable) {
      embedInFrame = ((IWebStartable) nestedProcess).customFields().value(CustomFields.EMBED_IN_FRAME);
      return embedInFrame == null ? StringUtils.EMPTY : embedInFrame;
    }
    IWebStartable startableProcess = Ivy.wf().findStartable(process.getId()).orElse(null);
    if (startableProcess == null) {
      return StringUtils.EMPTY;
    }
    embedInFrame = startableProcess.customFields().value(CustomFields.EMBED_IN_FRAME);
    return embedInFrame == null ? StringUtils.EMPTY : embedInFrame;
  }
}

package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class IFrameService {

  public static boolean embedInFrame(Long taskId) {
    GlobalSettingService service = new GlobalSettingService();
    ITask task = Ivy.wf().findTask(taskId);
    if (task != null) {
      boolean isExpress = Boolean.parseBoolean(task.getCase().customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).getOrDefault("false"));
      Boolean isIFrameCustomField = IvyAdapterService.getTaskEmbedInIFrameCustomField(task);
      Ivy.log().error("aa {0}", isIFrameCustomField);
      // There are two levels: custom field in task, if not set, check Portal global setting
      return isExpress ? false : isIFrameCustomField != null ? isIFrameCustomField : Boolean.parseBoolean(service.findGlobalSettingValue(GlobalVariable.EMBEDED_IN_IFRAME.toString()));
    }
    return false;
  }
}

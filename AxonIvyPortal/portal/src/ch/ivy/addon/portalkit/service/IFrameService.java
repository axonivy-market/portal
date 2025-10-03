package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;
import com.axonivy.portal.components.service.impl.ProcessService;

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
    String embedInFrame = "";
    if (isFirstTask(task, task.getId())) {
      embedInFrame = getEmbedInFrameInProcessRequestTab(task);
    }
    if (StringUtils.isBlank(embedInFrame)) {
      embedInFrame = task.customFields().stringField(CustomFields.EMBED_IN_FRAME).getOrNull();
      if (embedInFrame == null) {
        embedInFrame = task.getCase().customFields().stringField(CustomFields.EMBED_IN_FRAME).getOrNull();
      }
    }
    return embedInFrame != null ? Boolean.valueOf(embedInFrame) : null;
  }
  
  /**
   * @param task
   * @return whether embedInFrame exists in request tab of process start
   */
  private static String getEmbedInFrameInProcessRequestTab(ITask task) {
    String friendlyRequestPath = task.getCase().getProcessStart().getUserFriendlyRequestPath();
    String relativeUrl = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);

    if (relativeUrl.isEmpty()) {
      return null;
    }
    IWebStartable webstartable = findWebStartable(relativeUrl);
    if (webstartable == null) {
      return null;
    }
    return webstartable.customFields().value(CustomFields.EMBED_IN_FRAME);
  }
  
  private static IWebStartable findWebStartable(String processLink) {
    if (StringUtils.isNotBlank(processLink)) {
      return getWebStartables().stream().filter(filterByRelativeLink(processLink)).findFirst().orElse(null);
    }
    
    return null;
  }
  
  private static Predicate<? super IWebStartable> filterByRelativeLink(String startProcessId) {
    return webStartable -> Strings.CS.equals(startProcessId, webStartable.getLink().getRelative());
  }
  
  private static List<IWebStartable> getWebStartables() {
    return Optional.ofNullable(ProcessService.getInstance().findProcesses()).orElse(new ArrayList<>());
  }
  
  private static Boolean isFirstTask(ITask task, Long taskId) {
    Long firstTaskId = task.getCase().getFirstTask().getId();
    return Objects.equals(taskId, firstTaskId);
  }
}

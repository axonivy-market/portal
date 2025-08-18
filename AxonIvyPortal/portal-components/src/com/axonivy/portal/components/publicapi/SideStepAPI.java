package com.axonivy.portal.components.publicapi;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Public API for Side Step process functionality
 */
public final class SideStepAPI {
  private static final String DEFAULT_PREFIX_TASK_NAME_CMS = "/Dialogs/com/axonivy/portal/components/Common/SideStepPrefix";

  private SideStepAPI() {
  }

  /**
   * If side step task is switch type, remove hide attribute to show it in task widget
   * @param originalTaskUuid uuid of the task which starts this side step
   * @param isParallelSideStep whether side step run parallel with original task
   */
  public static void finishSideStep(String originalTaskUuid, boolean isParallelSideStep) {
    if (!isParallelSideStep) {
      ITask originalTask = Ivy.wf().findTask(originalTaskUuid);
      if (originalTask != null) {
        TaskAPI.removeHidePropertyToDisplayInPortal(originalTask);
      }
    }
  }

  /**
   * Create side step task name bases on original task uuid
   * @param originalTaskUuid uuid of the task which starts this side step
   * @param prefixTaskNameCmsUri cms uri of prefix task name, if not provided or not found, default prefix will be used
   * @return side step task name
   */
  public static String createSideStepTaskName(String originalTaskUuid, String prefixTaskNameCmsUri) {
    ITask originalTask = Ivy.wf().findTask(originalTaskUuid);
    if (originalTask != null) {
      String prefixFromCms = StringUtils.isNotBlank(prefixTaskNameCmsUri) ? Ivy.cms().co(prefixTaskNameCmsUri) : "";
      return (StringUtils.isNotBlank(prefixFromCms) ? prefixFromCms : Ivy.cms().co(DEFAULT_PREFIX_TASK_NAME_CMS)) + originalTask.getName();
    }
    return null;
  }

}

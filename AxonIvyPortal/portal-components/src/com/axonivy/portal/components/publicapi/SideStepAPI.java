package com.axonivy.portal.components.publicapi;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.constant.CustomFields;
import com.axonivy.portal.components.enums.SideStepType;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Public API for Side Step process functionality
 */
public final class SideStepAPI {
  private static final String DEFAULT_PREFIX_TASK_NAME_CMS = "/Dialogs/com/axonivy/portal/components/Common/SideStepPrefix";

  private SideStepAPI() {
  }

  public static <T> void handleSaveProcessData(T data, String originalTaskUuid, SideStepType type) {
    Ivy.repo().save(data);
    ITask originalTask = Ivy.wf().findTask(originalTaskUuid);
    if (type == SideStepType.SWITCH && originalTask != null) {
      TaskAPI.removeHidePropertyToDisplayInPortal(originalTask);
      ICase currentCase = Ivy.wfCase();
      originalTask.customFields().stringField(CustomFields.SIDE_STEP_CASE).set(currentCase.uuid());
    }
    // update original task name
    if (originalTask != null) {
      originalTask.setName(StringUtils.replace(originalTask.getName(), Ivy.cms().co(DEFAULT_PREFIX_TASK_NAME_CMS), ""));
    }
  }

  public static String createSideStepTaskName(String originalTaskUuid, String prefixTaskNameCmsUri) {
    ITask originalTask = Ivy.wf().findTask(originalTaskUuid);
    if (originalTask != null) {
      String prefixFromCms = Ivy.cms().co(prefixTaskNameCmsUri);
      return (StringUtils.isNoneBlank(prefixFromCms) ? prefixFromCms : Ivy.cms().co(DEFAULT_PREFIX_TASK_NAME_CMS)) + originalTask.getName();
    }
    return null;
  }

}

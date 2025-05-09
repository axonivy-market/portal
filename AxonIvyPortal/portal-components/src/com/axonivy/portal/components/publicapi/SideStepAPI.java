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
  private static final String PREFIX_TASK_NAME = "Side step task for: ";

  private SideStepAPI() {
  }

  public static <T> void handleSaveProcessData(T data, String originalTaskId, SideStepType type) {
    Ivy.repo().save(data);
    ITask originalTask = Ivy.wf().findTask(originalTaskId);
    if (type == SideStepType.SWITCH && originalTask != null) {
      TaskAPI.removeHidePropertyToDisplayInPortal(originalTask);
      ICase currentCase = Ivy.wfCase();
      originalTask.customFields().stringField(CustomFields.SIDE_STEP_CASE).set(currentCase.uuid());
    }
    // update original task name
    originalTask.setName(StringUtils.replace(originalTask.getName(), PREFIX_TASK_NAME, ""));
  }

  public static String createSideStepTaskName(String originalTaskId) {
    ITask originalTask = Ivy.wf().findTask(originalTaskId);
    if (originalTask != null) {
      return PREFIX_TASK_NAME + originalTask.getName();
    }
    return null;
  }

}
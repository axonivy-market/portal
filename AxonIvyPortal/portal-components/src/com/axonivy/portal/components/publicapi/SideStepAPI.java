package com.axonivy.portal.components.publicapi;

import java.util.List;

import com.axonivy.portal.components.constant.CustomFields;
import com.axonivy.portal.components.dto.SideStepDTO;
import com.axonivy.portal.components.enums.SideStepType;
import com.axonivy.portal.components.service.exception.PortalException;
import com.axonivy.portal.components.util.TaskUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

/**
 * Public API for Side Step process functionality
 */
public final class SideStepAPI {
    private SideStepAPI() {}

    /**
     * Creates and sets path to task custom string field {@code sideStep} for displaying side step process.
     * <p>Use {@code com.axonivy.portal.components.dto.SideStepDTO.builder()} to
     * build {@code SideStepDTO}</p>
     *
     * Example:
     * <pre><code>
     * SideStepAPI.create(SideStepDTO.builder()
     *   .path("Start Processes/SideStep/queryProcess.ivp")
     *   .task(currentTask)
     *   .build());
     * </code></pre>
     *
     * @param sideStepDTO SideStepDTO containing configuration
     */
    public static void create(SideStepDTO sideStepDTO) {
        String path = sideStepDTO.getSignal();
        ITask task = Ivy.wf().findTask(sideStepDTO.getTaskId());
        
        // Find the process by path
        IWebStartable webStartable = findWebStartable(path);
        String customField = webStartable.getId().toString();
//        
//        if (sideStepDTO.isEmbedInFrame()) {
//            customField += "?embedInFrame=true";
//        }

        // Store the side step configuration
        task.customFields().stringField(CustomFields.SIDE_STEPS).set(customField);
        
        // Park the current task
        TaskUtils.parkTask(task);
        
        
        // Start the side step process
        //ICase sideStepCase = webStartable.start();
        //sideStepCase.customFields().stringField(CustomFields.ORIGINAL_TASK).set(String.valueOf(task.getId()));
    }

    public static <T> void handleSaveProcessData(T data, String originalTaskId, SideStepType type) {
      Ivy.repo().save(data);
      ITask currentTask = Ivy.wf().findTask(originalTaskId);
      if (type == SideStepType.SWITCH && currentTask != null) {
        TaskAPI.removeHidePropertyToDisplayInPortal(currentTask);
        ICase currentCase = Ivy.wfCase();
        currentTask.customFields().stringField(CustomFields.SIDE_STEP_CASE).set(currentCase.uuid());
      }
    }
    
    /**
     * Creates and sets path to task custom string field {@code sideStep} using current task.
     *
     * Example:
     * <pre><code>
     * SideStepAPI.create("Start Processes/SideStep/queryProcess.ivp");
     * </code></pre>
     *
     * @param path Path to the side step process
     */
    public static void create(String path) {
        ITask currentTask = Ivy.wfTask();
        //create(SideStepDTO.builder().path(path).taskId(currentTask.uuid()).build());
    }

    /**
     * Completes a side step process and resumes the original task
     *
     * @param sideStepDTO SideStepDTO containing configuration
     * @param result Result data to be passed back to original task
     */
    public static void complete(SideStepDTO sideStepDTO, String result) {
//        ICase sideStepCase = sideStepDTO.getCase();
//        String originalTaskId = sideStepCase.customFields().stringField(CustomFields.ORIGINAL_TASK).getOrNull();
//        
//        if (originalTaskId != null) {
//            ITask originalTask = Ivy.wf().findTask(Long.parseLong(originalTaskId));
//            if (originalTask != null) {
//                originalTask.customFields().stringField(CustomFields.SIDE_STEP_RESULT).set(result);
//                //originalTask.resume();
//            }
//        }
    }

    private static IWebStartable findWebStartable(String path) {
      List<IWebStartable> iWebStartables = IWorkflowSession.current().getAllStartables().toList();

      IWebStartable targetStartable =
          iWebStartables.stream().filter(startable -> startable.getId().endsWith(path)).findAny().orElseThrow(
              () -> new PortalException(String.format("Cannot find IWebStartable by ID [%s].", path)));
      return targetStartable;
    }
} 
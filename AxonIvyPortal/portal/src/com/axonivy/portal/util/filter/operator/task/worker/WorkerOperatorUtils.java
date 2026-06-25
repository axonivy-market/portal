package com.axonivy.portal.util.filter.operator.task.worker;

import java.util.List;

import ch.ivyteam.ivy.workflow.TaskState;

public class WorkerOperatorUtils {
      final static List<TaskState> EXCLUDE_STATES = List.of(TaskState.SUSPENDED, TaskState.FAILED);
}

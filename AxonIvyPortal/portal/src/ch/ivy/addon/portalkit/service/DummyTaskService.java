package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.constant.DummyTask;
import ch.ivyteam.ivy.workflow.ITask;

public class DummyTaskService {

  public static List<ITask> dummyTasks() {
    ITask task = ITask.current();
    task.names().current(DummyTask.TASK_NAME);
    task.descriptions().current(DummyTask.TASK_DESCRIPTION);
    return Arrays.asList(task);
  }
  
}

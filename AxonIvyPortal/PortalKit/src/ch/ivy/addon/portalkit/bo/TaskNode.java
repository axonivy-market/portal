package ch.ivy.addon.portalkit.bo;

import java.util.ArrayList;
import java.util.List;

public class TaskNode extends MainMenuNode {

  private List<RemoteTask> tasks;

  public TaskNode(RemoteTask task) {
    tasks = new ArrayList<>();
    tasks.add(task);
  }

  public TaskNode() {}

  public List<RemoteTask> getTasks() {
    return tasks;
  }

  public void setTasks(List<RemoteTask> tasks) {
    this.tasks = tasks;
  }
}

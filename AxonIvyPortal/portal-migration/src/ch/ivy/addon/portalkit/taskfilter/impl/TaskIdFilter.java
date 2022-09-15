package ch.ivy.addon.portalkit.taskfilter.impl;

import java.util.Objects;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskIdFilter extends TaskFilter {

  private Long taskId;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ID");
  }

  @Override
  public String value() {
    if (taskId == null || taskId.equals(0l)) {
      taskId = null;
      return getAllLabel();
    }
    return String.valueOf(taskId);
  }

  @Override
  public TaskQuery buildQuery() {
    if (Objects.isNull(taskId)) {
      return null;
    }

    String containingKeyword = String.format("%%%d%%", taskId);
    return TaskQuery.create().where().taskId().isLike(containingKeyword);
  }

  @Override
  public void resetValues() {
    taskId = null;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }
}

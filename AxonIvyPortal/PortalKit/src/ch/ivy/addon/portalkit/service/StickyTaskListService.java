package ch.ivy.addon.portalkit.service;

import java.util.Objects;

import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.workflow.ITask;

public class StickyTaskListService {
  private static StickyTaskListService service = new StickyTaskListService();

  private StickyTaskListService() {}

  public static StickyTaskListService service() {
    return service;
  }

  public String getTaskEndInfoSessionAttributeKey(Long taskId) {
    return SessionAttribute.TASK_END_INFO + taskId.toString();
  }

  public ITask getPreviousTaskWithTaskEndInfo(ITask task) {
    if (Objects.isNull(task) || isTaskWithTaskEndInfo(getTaskEndInfoSessionAttributeKey(task.getId()))) {
      return task;
    }
    for (ITask previousTask : task.getStartSwitchEvent().getEndedTasks()) {
      ITask t = getPreviousTaskWithTaskEndInfo(previousTask);
      if (t != null) {
        return t;
      }
    }
    return null;
  }

  private boolean isTaskWithTaskEndInfo(String taskEndInfoSessionAttributeKey) {
    return SecurityServiceUtils.getSessionAttribute(taskEndInfoSessionAttributeKey) != null;
  }
}

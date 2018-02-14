package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.persistence.domain.Server;

public class StickyTaskListService {
  private static StickyTaskListService service = new StickyTaskListService();

  private StickyTaskListService() {}

  public static StickyTaskListService service() {
    return service;
  }

  public String getTaskEndInfoSessionAttributeKey(String taskId) {
    Server server = new ServerWorkingOnDetector().getServerWorkingOn();
    return SessionAttribute.TASK_END_INFO + server.getId().toString() + taskId;
  }
}

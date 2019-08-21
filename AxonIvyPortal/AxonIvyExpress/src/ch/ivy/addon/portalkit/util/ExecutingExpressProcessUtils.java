package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public final class ExecutingExpressProcessUtils {
  public static final String TASK_GROUP_ID_KEY = "expressGroupId";

  private ExecutingExpressProcessUtils() {}

  @SuppressWarnings("unchecked")
  public static <T> List<T> getAttributesOfTasks(String groupId, String attribute) {
    return IvyExecutor.executeAsSystem(() -> {
      TaskQuery query =
          TaskQuery.create().where().caseId().isEqual(Ivy.wfCase().getId()).and().customField().textField(TASK_GROUP_ID_KEY)
              .isEqual(groupId).orderBy().endTimestamp();
      List<ITask> tasks = Ivy.wf().getTaskQueryExecutor().getResults(query);
      List<T> result = new ArrayList<>();
      for (ITask task : tasks) {
        try {
          result.add((T) task.getEndProcessData().get(attribute));
        } catch (PersistencyException | NoSuchFieldException e) {
          throw new PortalException(e);
        }
      }
      return result;
    });
  }
}

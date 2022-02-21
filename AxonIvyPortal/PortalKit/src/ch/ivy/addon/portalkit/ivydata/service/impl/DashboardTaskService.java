package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.List;

import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class DashboardTaskService extends TaskService {

  private static DashboardTaskService instance;

  public DashboardTaskService() {}

  public static DashboardTaskService getInstance() {
    if (instance == null) {
      instance = new DashboardTaskService();
    }
    return instance;
  }

  public List<ITask> findByTaskQuery(TaskQuery query, int startIndex, int count) {
    var finalQuery = extendQueryWithUserHasPermissionToSee(query, PermissionUtils.checkReadAllTasksPermission());
    return Ivy.wf().getTaskQueryExecutor().getResults(finalQuery, startIndex, count);
  }
}

package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.util.List;

import ch.ivy.addon.portalkit.util.PermissionUtils;
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
    var subQuery = TaskQuery.create();
    if (!PermissionUtils.checkReadAllTasksPermission()) {
      subQuery.where().and(queryInvolvedTasks());
    }
    if (isHiddenTasksCasesExcluded()) {
      subQuery.where().and(queryExcludeHiddenTasks());
    }
    var finalQuery = query.where().and(subQuery);
    return executeTaskQuery(finalQuery, startIndex, count);
  }
}

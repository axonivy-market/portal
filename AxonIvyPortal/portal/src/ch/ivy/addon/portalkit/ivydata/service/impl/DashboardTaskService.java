package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardTaskSearchCriteria;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
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
    return Sudo.get(() -> {
      var subQuery = TaskQuery.create();
      boolean hasSubQueryChanged = false;
      if (!PermissionUtils.checkReadAllTasksPermission()) {
        subQuery.where().and(queryInvolvedTasks());
        hasSubQueryChanged = true;
      }
      if (isHiddenTasksCasesExcluded()) {
        subQuery.where().and(queryExcludeHiddenTasks());
        hasSubQueryChanged = true;
      }
      if (!PermissionUtils.hasSystemTaskReadAllPermission()) {
        subQuery.where().and(queryExcludeSystemTasks());
        hasSubQueryChanged = true;
      }
      var finalQuery = hasSubQueryChanged ? query.where().and(subQuery) : query;
      return executeTaskQuery(finalQuery, startIndex, count);
    });
  }

  public Long countByTaskQuery(TaskQuery query) {
    return Sudo.get(() ->{
      TaskQuery subQuery = TaskQuery.create();
      boolean hasSubQueryChanged = false;
      if(!PermissionUtils.checkReadAllTasksPermission()) {
        subQuery.where().and(queryInvolvedTasks());
        hasSubQueryChanged = true;
      }
      if (isHiddenTasksCasesExcluded()) {
        subQuery.where().and(queryExcludeHiddenTasks());
        hasSubQueryChanged = true;
      }
      if (!PermissionUtils.hasSystemTaskReadAllPermission()) {
        subQuery.where().and(queryExcludeSystemTasks());
        hasSubQueryChanged = true;
      }
      var finalQuery = hasSubQueryChanged ? query.where().and(subQuery) : query;
      
      return countTasks(finalQuery);
    });
  }

  public List<ITask> findDashboardTaskByCriteria(DashboardTaskSearchCriteria criteria, int startIndex, int count) {
    return Sudo.get(() -> {
      TaskQuery query = criteria.buildQuery();
      var subQuery = TaskQuery.create();
      boolean hasSubQueryChanged = false;

      if (criteria.isFilterTasksByCurrentCaseOwner() && GlobalSettingService.getInstance().isCaseOwnerEnabled()) {
        CaseQuery subCaseQuery = CaseQuery.create().where().currentUserIsOwner();
        query.where().cases(subCaseQuery);
        hasSubQueryChanged = true;
      } else if (!PermissionUtils.checkReadAllTasksPermission()) {
        subQuery.where().and(queryInvolvedTasks());
        hasSubQueryChanged = true;
      }

      if (isHiddenTasksCasesExcluded()) {
        subQuery.where().and(queryExcludeHiddenTasks());
        hasSubQueryChanged = true;
      }
      var finalQuery = hasSubQueryChanged ? query.where().and(subQuery) : query;
      return executeTaskQuery(finalQuery, startIndex, count);
    });
  }

  public Long countDashboardTaskByCriteria(DashboardTaskSearchCriteria criteria) {
    return Sudo.get(() -> {
      TaskQuery query = criteria.buildQuery();
      TaskQuery subQuery = TaskQuery.create();
      boolean hasSubQueryChanged = false;
      if (criteria.isFilterTasksByCurrentCaseOwner() && GlobalSettingService.getInstance().isCaseOwnerEnabled()) {
        CaseQuery subCaseQuery = CaseQuery.create().where().currentUserIsOwner();
        query.where().cases(subCaseQuery);
        hasSubQueryChanged = true;
      } else if (!PermissionUtils.checkReadAllTasksPermission()) {
        subQuery.where().and(queryInvolvedTasks());
        hasSubQueryChanged = true;
      }
      if (isHiddenTasksCasesExcluded()) {
        subQuery.where().and(queryExcludeHiddenTasks());
        hasSubQueryChanged = true;
      }
      var finalQuery = hasSubQueryChanged ? query.where().and(subQuery) : query;

      return countTasks(finalQuery);
    });
  }
}

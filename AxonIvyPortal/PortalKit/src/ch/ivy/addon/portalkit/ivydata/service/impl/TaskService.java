package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.List;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.ITaskService;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskService implements ITaskService {

  private TaskService() {
  }
  
  public static TaskService newInstance() {
    return new TaskService();
  }
  
  @Override
  public IvyTaskResultDTO findTasksByCriteria(TaskSearchCriteria criteria, int startIndex, int count) throws Exception { // NOSONAR
    return getServer().getSecurityManager().executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      try {
        TaskQuery finalQuery = criteria.getFinalTaskQuery();
        if (criteria.hasApps()) {
          if (criteria.hasInvolvedUsername() && !criteria.isAdminQuery()) {
            finalQuery.where().and(queryForUsers(criteria.getInvolvedUsername(), criteria.getApps()));
          } else {
            finalQuery.where().and(queryForApplications(criteria.getApps()));
          }
        }
//        finalQuery.where().and(queryExcludeHiddenTasks());
        result.setTasks(executeTaskQuery(finalQuery, startIndex, count));
      } catch (Exception ex) {
        Ivy.log().error("Error in getting tasks", ex);
//        result.setErrors(Arrays.asList(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_LANGUAGE.toString())));
      }
      return result;
    });
  }
  
  @Override
  public IvyTaskResultDTO countTasksByCriteria(TaskSearchCriteria criteria) throws Exception { // NOSONAR
    return getServer().getSecurityManager().executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      try {
        TaskQuery finalQuery = criteria.getFinalTaskQuery();
        if (criteria.hasInvolvedUsername() && !criteria.isAdminQuery()) {
          finalQuery.where().and(queryForUsers(criteria.getInvolvedUsername(), criteria.getApps()));
        } else {
          finalQuery.where().and(queryForApplications(criteria.getApps()));
        }
//        finalQuery.where().and(queryExcludeHiddenTasks());
        result.setTotalTasks(countTasks(finalQuery));
      } catch (Exception ex) {
        Ivy.log().error("Error in counting Tasks", ex);
//        result.setErrors(Arrays.asList(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_LANGUAGE.toString())));
      }
      return result;
    });
  }
  
  private List<ITask> executeTaskQuery(TaskQuery query, Integer startIndex, Integer count) {
    return Ivy.wf().getGlobalContext().getTaskQueryExecutor().getResults(query, startIndex, count);
  }

  private long countTasks(TaskQuery query) {
    return Ivy.wf().getGlobalContext().getTaskQueryExecutor().getCount(query);
  }
  
  private static TaskQuery queryForUsers(String involvedUsername, List<String> apps) {
    TaskQuery taskQuery = TaskQuery.create();
    apps.forEach(app -> taskQuery.where().or().userIsInvolved(involvedUsername, app));
    return taskQuery;
  }
  
  private TaskQuery queryForApplications(List<String> apps) {
    TaskQuery taskQuery = TaskQuery.create();
    apps.forEach(app -> {
      IApplication application = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(app);
      if (application != null && application.getActivityState() == ActivityState.ACTIVE) {
        taskQuery.where().or().applicationId().isEqual(application.getId());
      }
    });
    return taskQuery;
  }
  
  private TaskQuery queryExcludeHiddenTasks() {
    return TaskQuery.create().where().additionalProperty(AdditionalProperty.HIDE.toString()).isNull();
  }

  @Override
  public IvyTaskResultDTO findCategoriesByCriteria(TaskCategorySearchCriteria criteria) throws Exception { // NOSONAR
    return getServer().getSecurityManager().executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      try {
        TaskQuery finalQuery = criteria.getFinalQuery();
        
        if (criteria.hasApps()) {
          if (criteria.hasInvolvedUsername()) {
            finalQuery.where().and(queryForUsers(criteria.getInvolvedUsername(), criteria.getApps()));
          } else {
            finalQuery.where().and(queryForApplications(criteria.getApps()));
          }
        }
//        finalQuery.where().and(queryExcludeHiddenTasks());
        finalQuery.where().and().category().isNotNull();
        result.setCategoryTree(CategoryTree.createFor(finalQuery));
      } catch (Exception ex) {
        Ivy.log().error("Error in getting task category", ex);
//        result.setErrors(Arrays.asList(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_LANGUAGE.toString())));
      }
      return result;
    });
  }
}

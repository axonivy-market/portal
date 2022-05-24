package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.PriorityStatistic;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.ITaskService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Record;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.ITaskQueryExecutor;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskService implements ITaskService {

  private TaskService() {
  }
  
  public static TaskService newInstance() {
    return new TaskService();
  }
  
  @Override
  public IvyTaskResultDTO findTasksByCriteria(TaskSearchCriteria criteria, int startIndex, int count) { 
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      try {
        TaskQuery finalQuery = extendQueryWithInvolvedUser(criteria);
        result.setTasks(executeTaskQuery(finalQuery, startIndex, count));
      } catch (Exception ex) {
        Ivy.log().error("Error in getting tasks", ex);
        result.setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_LOAD_TASK.toString())));
      }
      return result;
    });
  }
  
  @Override
  public IvyTaskResultDTO countTasksByCriteria(TaskSearchCriteria criteria) { 
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      try {
        TaskQuery finalQuery = extendQueryWithInvolvedUser(criteria);
        result.setTotalTasks(countTasks(finalQuery));
      } catch (Exception ex) {
        Ivy.log().error("Error in counting Tasks", ex);
        result.setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_COUNT_TASK.toString())));
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
    apps.forEach(app -> {
      taskQuery.where().or().userIsInvolved(involvedUsername, app);
    });
    return taskQuery;
  }
  
  private static TaskQuery queryForUserCanWorkOn(String involvedUsername, List<String> apps) {
    TaskQuery taskQuery = TaskQuery.create();
    apps.forEach(app -> {
      IUser user = ServiceUtilities.findUser(involvedUsername, app);
      taskQuery.where().or().canWorkOn(user);
    });
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
    return TaskQuery.create().where().customField().stringField(AdditionalProperty.HIDE.toString()).isNull();
  }

  @Override
  public IvyTaskResultDTO findCategoriesByCriteria(TaskCategorySearchCriteria criteria) { 
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      try {
        TaskQuery finalQuery = criteria.createQuery(Ivy.wf().getTaskQueryExecutor());
        
        if (criteria.hasApps()) {
          if (criteria.hasInvolvedUsername()) {
            finalQuery.where().and(queryForUsers(criteria.getInvolvedUsername(), criteria.getApps()));
          } else {
            finalQuery.where().and(queryForApplications(criteria.getApps()));
          }
        }
        if (isHiddenTasksCasesExcluded(criteria.getApps())) {
          finalQuery.where().and(queryExcludeHiddenTasks());
        }
        finalQuery.where().and().category().isNotNull();
        result.setCategoryTree(CategoryTree.createFor(finalQuery));
      } catch (Exception ex) {
        Ivy.log().error("Error in getting task category", ex);
        result.setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_LOAD_TASK_CATEGORY.toString())));
      }
      return result;
    });
  }
  
  @Override
  public IvyTaskResultDTO analyzePriorityStatistic(TaskSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      try {
        TaskQuery finalQuery = extendQueryWithUserCanWorkOn(criteria);
        finalQuery.aggregate().countRows().groupBy().priority();

        Recordset recordSet = taskQueryExecutor().getRecordset(finalQuery);
        PriorityStatistic priorityStatistic = createPriorityStatistic(recordSet);
        result.setPriorityStatistic(priorityStatistic);
      } catch (Exception ex) {
        Ivy.log().error("Error in getting task priority statistic", ex);
        result.setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_LOAD_TASK_PRIORITY_STATISTIC.toString())));
      }
      return result;
    });
  }
  
  private PriorityStatistic createPriorityStatistic(Recordset recordSet) {
    PriorityStatistic priorityStatistic = new PriorityStatistic();
    if (recordSet != null) {
      recordSet.getRecords().forEach(record -> {
        int priority = Integer.parseInt(record.getField("PRIORITY").toString());
        long numberOfTasks = Long.parseLong(record.getField("COUNT").toString());
        if (priority == WorkflowPriority.EXCEPTION.intValue()) {
          priorityStatistic.setException(numberOfTasks);
        } else if (priority == WorkflowPriority.HIGH.intValue()) {
          priorityStatistic.setHigh(numberOfTasks);
        } else if (priority == WorkflowPriority.NORMAL.intValue()) {
          priorityStatistic.setNormal(numberOfTasks);
        } else {
          priorityStatistic.setLow(numberOfTasks);
        }
      });
    }
    return priorityStatistic;
  }
  
  @Override
  public IvyTaskResultDTO analyzeExpiryStatistic(TaskSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      try {
        TaskQuery finalQuery = extendQueryWithUserCanWorkOn(criteria);
        finalQuery.aggregate().countRows().groupBy().expiryTimestamp().orderBy().expiryTimestamp();

        Recordset recordSet = taskQueryExecutor().getRecordset(finalQuery);
        ExpiryStatistic expiryStatistic = createExpiryTimeStampToCountMap(recordSet);
        result.setExpiryStatistic(expiryStatistic);
      } catch (Exception ex) {
        Ivy.log().error("Error in getting task expiry statistic", ex);
        result.setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_LOAD_TASK_EXPIRY_STATISTIC.toString())));
      }
      return result;
    });
  }
  
  private ExpiryStatistic createExpiryTimeStampToCountMap(Recordset recordSet) {
    ExpiryStatistic expiryStatistic = new ExpiryStatistic();
    Map<Date, Long> numberOfTasksByExpiryTime = new HashMap<>();
    if (recordSet != null) {
      for (Record record : recordSet.getRecords()) {
        if (record.getField("EXPIRYTIMESTAMP") != null) {
          try { 
            Date date = DateUtils.parseDate(record.getField("EXPIRYTIMESTAMP").toString(), "yyyy-MM-dd HH:mm:ss.SSS");
            numberOfTasksByExpiryTime.put(date, Long.valueOf(record.getField("COUNT").toString()));
          } catch (Exception e) {
            Ivy.log().error(e);
          }
        }
      }
    }
    expiryStatistic.setNumberOfTasksByExpiryTime(numberOfTasksByExpiryTime);
    return expiryStatistic;
  }

  private TaskQuery extendQueryWithInvolvedUser(TaskSearchCriteria criteria) {
    TaskQuery finalQuery = criteria.getFinalTaskQuery();
    TaskQuery clonedQuery = TaskQuery.fromJson(finalQuery.asJson()); // clone to keep the final query in TaskSearchCriteria
    if (criteria.hasApps()) {
      if (criteria.hasInvolvedUsername() && !criteria.isAdminQuery()) {
        clonedQuery.where().and(queryForUsers(criteria.getInvolvedUsername(), criteria.getApps()));
      } else {
        clonedQuery.where().and(queryForApplications(criteria.getApps()));
      }
    }
    if (isHiddenTasksCasesExcluded(criteria.getApps())) {
      clonedQuery.where().and(queryExcludeHiddenTasks());
    }
    return clonedQuery;
  }
  
  private TaskQuery extendQueryWithUserCanWorkOn(TaskSearchCriteria criteria) {
    TaskQuery finalQuery = criteria.getFinalTaskQuery();
    if (criteria.hasApps()) {
      if (criteria.hasInvolvedUsername() && !criteria.isAdminQuery()) {
        finalQuery.where().and(queryForUserCanWorkOn(criteria.getInvolvedUsername(), criteria.getApps()));
      } else {
        finalQuery.where().and(queryForApplications(criteria.getApps()));
      }
    }
    if (isHiddenTasksCasesExcluded(criteria.getApps())) {
      finalQuery.where().and(queryExcludeHiddenTasks());
    }
    return finalQuery;
  }
  
  private ITaskQueryExecutor taskQueryExecutor() {
    return Ivy.wf().getGlobalContext().getTaskQueryExecutor();
  }

}

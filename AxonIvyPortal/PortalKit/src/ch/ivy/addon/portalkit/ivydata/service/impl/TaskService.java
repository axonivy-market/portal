package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.PriorityStatistic;
import ch.ivy.addon.portalkit.bo.TaskCategoryStatistic;
import ch.ivy.addon.portalkit.bo.TaskStateStatistic;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.ITaskService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Record;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.ITaskQueryExecutor;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.FilterLink;

public class TaskService implements ITaskService {

  protected TaskService() {}

  public static TaskService newInstance() {
    return new TaskService();
  }
  
  @Override
  public IvyTaskResultDTO findTasksByCriteria(TaskSearchCriteria criteria, int startIndex, int count) { 
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithUserHasPermissionToSee(criteria);
      result.setTasks(executeTaskQuery(finalQuery, startIndex, count));
      return result;
    });
  }
  
  @Override
  public IvyTaskResultDTO countTasksByCriteria(TaskSearchCriteria criteria) { 
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithUserHasPermissionToSee(criteria);
      result.setTotalTasks(countTasks(finalQuery));
      return result;
    });
  }
  
  protected List<ITask> executeTaskQuery(TaskQuery query, Integer startIndex, Integer count) {
    return taskQueryExecutor().getResults(query, startIndex, count);
  }

  private long countTasks(TaskQuery query) {
    return taskQueryExecutor().getCount(query);
  }
  
  protected TaskQuery queryExcludeHiddenTasks() {
    return TaskQuery.create().where().customField().stringField(AdditionalProperty.HIDE.toString()).isNull();
  }

  @Override
  public IvyTaskResultDTO findCategoriesByCriteria(TaskCategorySearchCriteria criteria) { 
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = criteria.createQuery();
      
      if (isHiddenTasksCasesExcluded()) {
        finalQuery.where().and(queryExcludeHiddenTasks());
      }
      finalQuery.where().and().category().isNotNull();
      result.setCategoryTree(CategoryTree.createFor(finalQuery));
      return result;
    });
  }
  
  @Override
  public IvyTaskResultDTO analyzePriorityStatistic(TaskSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithUserCanWorkOn(criteria);
      finalQuery.aggregate().countRows().groupBy().priority();

      Recordset recordSet = taskQueryExecutor().getRecordset(finalQuery);
      PriorityStatistic priorityStatistic = createPriorityStatistic(recordSet);
      result.setPriorityStatistic(priorityStatistic);
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
      TaskQuery finalQuery = extendQueryWithUserCanWorkOn(criteria);
      finalQuery.aggregate().countRows().groupBy().expiryTimestamp().orderBy().expiryTimestamp();

      Recordset recordSet = taskQueryExecutor().getRecordset(finalQuery);
      ExpiryStatistic expiryStatistic = createExpiryTimeStampToCountMap(recordSet);
      result.setExpiryStatistic(expiryStatistic);
      return result;
    });
  }
  
  @Override
  public IvyTaskResultDTO analyzeTaskStateStatistic(TaskSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithInvolvedUser(criteria);
      finalQuery.aggregate().countRows().groupBy().state();

      Recordset recordSet = taskQueryExecutor().getRecordset(finalQuery);
      TaskStateStatistic taskStateStatistic = createTaskStateStatistic(recordSet);
      result.setTaskStateStatistic(taskStateStatistic);
      return result;
    });
  }
  
  private TaskStateStatistic createTaskStateStatistic(Recordset recordSet) {
    TaskStateStatistic taskStateStatistic = new TaskStateStatistic();
    taskStateStatistic.setNumberOfTasksByState(new HashMap<>());
    if (recordSet != null) {
      recordSet.getRecords().forEach(record -> {
        int state = Integer.parseInt(record.getField("STATE").toString());
        long numberOfTasks = Long.parseLong(record.getField("COUNT").toString());
        taskStateStatistic.getNumberOfTasksByState().put(state, numberOfTasks);
      });
    }
    return taskStateStatistic;
  }

  @Override
  public IvyTaskResultDTO analyzeTaskCategoryStatistic(TaskSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithInvolvedUser(criteria);
      finalQuery.aggregate().countRows().groupBy().category().orderBy().category();

      Recordset recordSet = taskQueryExecutor().getRecordset(finalQuery);
      TaskCategoryStatistic taskStateStatistic = createTaskCategoryStatistic(recordSet);
      result.setTaskCategoryStatistic(taskStateStatistic);
      return result;
    });
  }

  private TaskCategoryStatistic createTaskCategoryStatistic(Recordset recordSet) {
    TaskCategoryStatistic taskCategoryStatistic = new TaskCategoryStatistic();
    taskCategoryStatistic.setNumberOfTasksByCategory(new HashMap<>());
    if (recordSet != null) {
      recordSet.getRecords().forEach(record -> {
        long numberOfTasks = Long.parseLong(record.getField("COUNT").toString());
        taskCategoryStatistic.getNumberOfTasksByCategory().put(record.getField("CATEGORY").toString(), numberOfTasks);
      });
    }
    return taskCategoryStatistic;
  }

  private ExpiryStatistic createExpiryTimeStampToCountMap(Recordset recordSet) throws ParseException {
    ExpiryStatistic expiryStatistic = new ExpiryStatistic();
    Map<Date, Long> numberOfTasksByExpiryTime = new HashMap<>();
    if (recordSet != null) {
      for (Record record : recordSet.getRecords()) {
        if (record.getField("EXPIRYTIMESTAMP") != null) {
          // must use same format as IVY DB, can not change it to Ivy.cms().co("/patterns/dateTimePattern")
          String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
          Date date = DateUtils.parseDate(record.getField("EXPIRYTIMESTAMP").toString(), pattern);
          numberOfTasksByExpiryTime.put(date, Long.valueOf(record.getField("COUNT").toString()));
        }
      }
    }
    expiryStatistic.setNumberOfTasksByExpiryTime(numberOfTasksByExpiryTime);
    return expiryStatistic;
  }

  private TaskQuery extendQueryWithUserHasPermissionToSee(TaskSearchCriteria criteria) {
    TaskQuery clonedQuery = TaskQuery.fromJson(criteria.getFinalTaskQuery().asJson()); // clone to keep the final query in TaskSearchCriteria
    if (!criteria.isAdminQuery()) {
      clonedQuery.where().and(queryInvolvedTasks());
    }
    if (isHiddenTasksCasesExcluded()) {
      clonedQuery.where().and(queryExcludeHiddenTasks());
    }
    return clonedQuery;
  }

  protected TaskQuery queryInvolvedTasks() {
    return TaskQuery.create().where().or().currentUserIsInvolved();
  }

  private TaskQuery extendQueryWithInvolvedUser(TaskSearchCriteria criteria) {
    TaskQuery finalQuery = criteria.getFinalTaskQuery();
    TaskQuery clonedQuery = TaskQuery.fromJson(finalQuery.asJson()); // clone to keep the final query in TaskSearchCriteria
    if (!criteria.isAdminQuery()) {
      FilterLink currentUserIsInvolved = TaskQuery.create().where().or().currentUserIsInvolved();
      clonedQuery.where().and(currentUserIsInvolved);
    } 
    if (isHiddenTasksCasesExcluded()) {
      clonedQuery.where().and(queryExcludeHiddenTasks());
    }
    return clonedQuery;
  }
  
  private TaskQuery extendQueryWithUserCanWorkOn(TaskSearchCriteria criteria) {
    TaskQuery finalQuery = criteria.getFinalTaskQuery();
    if (!criteria.isAdminQuery()) {
      finalQuery.where().and().currentUserCanWorkOn();
    } 
    if (isHiddenTasksCasesExcluded()) {
      finalQuery.where().and(queryExcludeHiddenTasks());
    }
    return finalQuery;
  }
  
  private ITaskQueryExecutor taskQueryExecutor() {
    return Ivy.wf().getTaskQueryExecutor();
  }

}

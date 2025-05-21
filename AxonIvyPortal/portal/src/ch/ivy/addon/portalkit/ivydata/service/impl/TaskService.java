package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;
import static ch.ivyteam.ivy.workflow.TaskState.CREATED;
import static ch.ivyteam.ivy.workflow.TaskState.DELAYED;
import static ch.ivyteam.ivy.workflow.TaskState.DESTROYED;
import static ch.ivyteam.ivy.workflow.TaskState.DONE;
import static ch.ivyteam.ivy.workflow.TaskState.FAILED;
import static ch.ivyteam.ivy.workflow.TaskState.JOIN_FAILED;
import static ch.ivyteam.ivy.workflow.TaskState.PARKED;
import static ch.ivyteam.ivy.workflow.TaskState.READY_FOR_JOIN;
import static ch.ivyteam.ivy.workflow.TaskState.RESUMED;
import static ch.ivyteam.ivy.workflow.TaskState.SUSPENDED;
import static ch.ivyteam.ivy.workflow.TaskState.WAITING_FOR_INTERMEDIATE_EVENT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import com.axonivy.portal.bo.ItemByCategoryStatistic;

import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.PriorityStatistic;
import ch.ivy.addon.portalkit.bo.TaskStateStatistic;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.query.IPagedResult;
import ch.ivyteam.ivy.scripting.objects.Record;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.ITaskQueryExecutor;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.FilterLink;

public class TaskService {

  protected TaskService() {}

  public static TaskService newInstance() {
    return new TaskService();
  }
  
  public IvyTaskResultDTO findTasksByCriteria(TaskSearchCriteria criteria, int startIndex, int count) { 
    return Sudo.get(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithUserHasPermissionToSee(criteria);
      result.setTasks(executeTaskQuery(finalQuery, startIndex, count));
      return result;
    });
  }
  
  public IvyTaskResultDTO findGlobalSearchTasksByCriteria(TaskSearchCriteria criteria, int startIndex, int count) {
    return Sudo.get(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithUserHasPermissionToSee(criteria);
      IPagedResult<ITask> iTask = Ivy.wf().getTaskQueryExecutor()
          .getResultsPaged(finalQuery);
      result.setTasks(iTask.window(startIndex, count));
      result.setTotalTasks(iTask.count());
      return result;
    });
  }

  public IvyTaskResultDTO countTasksByCriteria(TaskSearchCriteria criteria) { 
    return Sudo.get(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithUserHasPermissionToSee(criteria);
      result.setTotalTasks(countTasks(finalQuery));
      return result;
    });
  }
  
  protected List<ITask> executeTaskQuery(TaskQuery query, Integer startIndex, Integer count) {
    return Ivy.wf().getTaskQueryExecutor().getResults(query, startIndex, count);
  }

  protected long countTasks(TaskQuery query) {
    return Ivy.wf().getTaskQueryExecutor().getCount(query);
  }
  
  protected TaskQuery queryExcludeSystemTasks() {
    return TaskQuery.create().where().workerId().isNotEqual(ISecurityContext.current().users().system().getSecurityMemberId()).or().workerId().isNull();
  }

  protected TaskQuery queryExcludeHiddenTasks() {
    return TaskQuery.create().where().customField().stringField(AdditionalProperty.HIDE.toString()).isNull();
  }

  public IvyTaskResultDTO findCategoriesByCriteria(TaskCategorySearchCriteria criteria) { 
    return Sudo.get(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = criteria.createQuery();
      
      if (isHiddenTasksCasesExcluded()) {
        finalQuery.where().and(queryExcludeHiddenTasks());
      }
      if (!PermissionUtils.hasSystemTaskReadAllPermission()) {
        finalQuery.where().and(queryExcludeSystemTasks());
      }
      finalQuery.where().and().category().isNotNull();
      result.setCategoryTree(CategoryTree.createFor(finalQuery));
      return result;
    });
  }
  
  public IvyTaskResultDTO analyzePriorityStatistic(TaskSearchCriteria criteria) {
    return Sudo.get(() -> {
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
        long numberOfTasks = ((Number)(record.getField("COUNT"))).longValue();
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
  
  public IvyTaskResultDTO analyzeExpiryStatistic(TaskSearchCriteria criteria) {
    return Sudo.get(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithUserCanWorkOn(criteria);
      finalQuery.aggregate().countRows().groupBy().expiryTimestamp().orderBy().expiryTimestamp();

      Recordset recordSet = taskQueryExecutor().getRecordset(finalQuery);
      ExpiryStatistic expiryStatistic = null;
      try {
        expiryStatistic = createExpiryTimeStampToCountMap(recordSet);
      } catch (ParseException e) {
        Ivy.log().error(e);
      }
      result.setExpiryStatistic(expiryStatistic);
      return result;
    });
  }
  
  public IvyTaskResultDTO analyzeTaskBusinessStateStatistic(TaskSearchCriteria criteria) {
    return Sudo.get(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithInvolvedUser(criteria);
      finalQuery.aggregate().countRows().groupBy().businessState();
      Recordset recordSet = taskQueryExecutor().getRecordset(finalQuery);
      TaskStateStatistic taskStateStatistic = createTaskBusinessStateStatistic(recordSet);
      result.setTaskStateStatistic(taskStateStatistic);
      return result;
    });
  }

  private TaskStateStatistic createTaskBusinessStateStatistic(Recordset recordSet) {
    TaskStateStatistic taskStateStatistic = new TaskStateStatistic();
    taskStateStatistic.setNumberOfTasksByState(new HashMap<>());
    if (recordSet != null) {
      recordSet.getRecords().forEach(record -> {
        int state = Integer.parseInt(record.getField("BUSINESSSTATE").toString());
        long numberOfTasks = ((Number) (record.getField("COUNT"))).longValue();
        taskStateStatistic.getNumberOfTasksByState().put(state, numberOfTasks);
      });
    }
    return taskStateStatistic;
  }

  public IvyTaskResultDTO analyzeTaskCategoryStatistic(TaskSearchCriteria criteria) {
    return Sudo.get(() -> {
      IvyTaskResultDTO result = new IvyTaskResultDTO();
      TaskQuery finalQuery = extendQueryWithInvolvedUser(criteria);
      result.setCategoryTree(CategoryTree.createFor(finalQuery));
      List<ItemByCategoryStatistic> statistics = CategoryUtils.createItemCategoryStatistic(result.getCategoryTree());
      result.setItemByCategoryStatistic(statistics);
      return result;
    });
  }

//  private TaskCategoryStatistic createTaskCategoryStatistic(Recordset recordSet) {
//    TaskCategoryStatistic taskCategoryStatistic = new TaskCategoryStatistic();
//    taskCategoryStatistic.setNumberOfTasksByCategory(new HashMap<>());
//    if (recordSet != null) {
//      recordSet.getRecords().forEach(record -> {
//        long numberOfTasks = ((Number)(record.getField("COUNT"))).longValue();
//        taskCategoryStatistic.getNumberOfTasksByCategory().put(record.getField("CATEGORY").toString(), numberOfTasks);
//      });
//    }
//    return taskCategoryStatistic;
//  }

  private ExpiryStatistic createExpiryTimeStampToCountMap(Recordset recordSet) throws ParseException {
      ExpiryStatistic expiryStatistic = new ExpiryStatistic();
      Map<Date, Long> numberOfTasksByExpiryTime = new HashMap<>();
      if (recordSet != null) {
        for (Record record : recordSet.getRecords()) {
          if (record.getField("EXPIRYTIMESTAMP") != null) {
            // must use same format as IVY DB, can not change it to Ivy.cms().co("/patterns/dateTimePattern")
            String pattern = "yyyy-MM-dd HH:mm:ss.SSS";

            Date date = new Date();
            try {
              date = DateUtils.parseDate(record.getField("EXPIRYTIMESTAMP").toString(), pattern);
            } catch(ParseException e) {
              // Try to parse by MySQL specific date format
              // Ticket: IVYPORTAL-14349
              SimpleDateFormat mySqlDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
              date = mySqlDateFormat.parse(record.getField("EXPIRYTIMESTAMP").toString());
            }
            long numberOfTasks = ((Number)(record.getField("COUNT"))).longValue();
            numberOfTasksByExpiryTime.put(date, numberOfTasks);
          }
        }
      }
      expiryStatistic.setNumberOfTasksByExpiryTime(numberOfTasksByExpiryTime);
      return expiryStatistic;
    }

  private TaskQuery extendQueryWithUserHasPermissionToSee(
        TaskSearchCriteria criteria) {
    TaskQuery cloneQuery = criteria.createQuery();

    if (!criteria.isAdminQuery()) {
      cloneQuery.where().and(queryInvolvedTasks());
    }
    if (isHiddenTasksCasesExcluded()) {
      cloneQuery.where().and(queryExcludeHiddenTasks());
    }
    if (!PermissionUtils.hasSystemTaskReadAllPermission()) {
      cloneQuery.where().and(queryExcludeSystemTasks());
    }
    return cloneQuery;
  }

  protected TaskQuery queryInvolvedTasks() {
    FilterLink currentUserIsInvolved = TaskQuery.create().where().or().currentUserOrHisRolesAreInvolved();
    return currentUserIsInvolved;
  }

  private TaskQuery extendQueryWithInvolvedUser(TaskSearchCriteria criteria) {
    TaskQuery clonedQuery = criteria.createQuery();

    if (!criteria.isAdminQuery()) {
      clonedQuery.where().and(queryInvolvedTasks());
    } 
    if (isHiddenTasksCasesExcluded()) {
      clonedQuery.where().and(queryExcludeHiddenTasks());
    }
    if (!PermissionUtils.hasSystemTaskReadAllPermission()) {
      clonedQuery.where().and(queryExcludeSystemTasks());
    }
    return clonedQuery;
  }
  
  private TaskQuery extendQueryWithUserCanWorkOn(TaskSearchCriteria criteria) {
    TaskQuery finalQuery = criteria.createQuery();
    if (!criteria.isAdminQuery()) {
      finalQuery.where().and().currentUserCanWorkOn();
    } 
    if (isHiddenTasksCasesExcluded()) {
      finalQuery.where().and(queryExcludeHiddenTasks());
    }
    if (!PermissionUtils.hasSystemTaskReadAllPermission()) {
      finalQuery.where().and(queryExcludeSystemTasks());
    }
    return finalQuery;
  }
  
  private ITaskQueryExecutor taskQueryExecutor() {
    return Ivy.wf().getTaskQueryExecutor();
  }

  public ITask findTaskById(long taskId) {
    return Sudo.get(() -> {
      TaskQuery taskQuery = TaskQuery.create().where().taskId().isEqual(taskId);
      if (PermissionUtils.checkReadAllTasksPermission()) {
        EnumSet<TaskState> ADVANCE_STATES = EnumSet.of(CREATED, SUSPENDED, RESUMED, PARKED, READY_FOR_JOIN, DONE,
            DELAYED, DESTROYED, JOIN_FAILED, FAILED, WAITING_FOR_INTERMEDIATE_EVENT);
        taskQuery.where().and(queryForStates(ADVANCE_STATES));
      } else {
        EnumSet<TaskState> STANDARD_STATES = EnumSet.of(CREATED, SUSPENDED, RESUMED, PARKED, READY_FOR_JOIN, DONE);
        taskQuery.where().and(queryForStates(STANDARD_STATES)).and(queryInvolvedTasks());
      }
      if (isHiddenTasksCasesExcluded()) {
        taskQuery.where().and(queryExcludeHiddenTasks());
      }
      if (!PermissionUtils.hasSystemTaskReadAllPermission()) {
        taskQuery.where().and(queryExcludeSystemTasks());
      }
      return taskQueryExecutor().getFirstResult(taskQuery);
    });
  }
  
  public ITask findTaskByUUID(String uuid) {
    return Sudo.get(() -> {
      TaskQuery taskQuery = TaskQuery.create().where().uuid().isEqual(uuid);
      if (PermissionUtils.checkReadAllTasksPermission()) {
        EnumSet<TaskState> ADVANCE_STATES = EnumSet.of(CREATED, SUSPENDED, RESUMED, PARKED, READY_FOR_JOIN, DONE,
            DELAYED, DESTROYED, JOIN_FAILED, FAILED, WAITING_FOR_INTERMEDIATE_EVENT);
        taskQuery.where().and(queryForStates(ADVANCE_STATES));
      } else {
        EnumSet<TaskState> STANDARD_STATES = EnumSet.of(CREATED, SUSPENDED, RESUMED, PARKED, READY_FOR_JOIN, DONE);
        taskQuery.where().and(queryForStates(STANDARD_STATES)).and(queryInvolvedTasks());
      }
      if (isHiddenTasksCasesExcluded()) {
        taskQuery.where().and(queryExcludeHiddenTasks());
      }
      if (!PermissionUtils.hasSystemTaskReadAllPermission()) {
        taskQuery.where().and(queryExcludeSystemTasks());
      }
      return taskQueryExecutor().getFirstResult(taskQuery);
    });
  }

  public boolean isTaskAccessible(String uuid) {
    return findTaskByUUID(uuid) != null;
  }

  private TaskQuery queryForStates(EnumSet<TaskState> states) {
    TaskQuery taskQuery = TaskQuery.create();
    for (TaskState state : states) {
      taskQuery.where().or().state().isEqual(state);
    }
    return taskQuery;
  }
}

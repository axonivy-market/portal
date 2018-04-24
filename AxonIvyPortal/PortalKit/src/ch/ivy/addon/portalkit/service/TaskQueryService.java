package ch.ivy.addon.portalkit.service;

import java.util.Date;
import java.util.List;

import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.support.TaskQueryCriteria;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskQueryService {

  private static TaskQueryService service = new TaskQueryService();

  private TaskQueryService() {}

  public static TaskQueryService service() {
    return service;
  }

  public TaskQuery createQuery(TaskQueryCriteria criteria) {
    TaskQuery finalQuery = TaskQuery.create();
    criteria.setNewQueryCreated(criteria.isNewQueryCreated() || criteria.getTaskQuery() == null || criteria.hasTaskId()
        || criteria.hasCaseId());

    if (!criteria.isNewQueryCreated()) {
      finalQuery = TaskQuery.fromJson(criteria.getTaskQuery().asJson());
    }

    if (criteria.hasIncludedStates() && !criteria.isQueryForUnassignedTask()) {
      finalQuery.where().and(queryForStates(criteria.getIncludedStates()));
    }

    if (criteria.hasTaskId()) {
      finalQuery.where().and(queryForTaskId(criteria.getTaskId()));
      return finalQuery;
    }

    if (criteria.hasCaseId()) {
      if (criteria.isQueryByBusinessCaseId()) {
        finalQuery.where().and(
            TaskQuery.create().where().businessCaseId().isEqual(criteria.getCaseId()).or().caseId()
                .isEqual(criteria.getCaseId()));
      } else {
        finalQuery.where().and().caseId().isEqual(criteria.getCaseId());
      }
    }

    if (criteria.hasKeyword()) {
      finalQuery.where().and(queryForKeyword(criteria.getKeyword()));
    }

    if (criteria.isQueryForUnassignedTask()) {
      finalQuery.where().and().activatorUserId().isNull().and().activatorRoleId().isNull();
    } else if (criteria.getTaskAssigneeType() == TaskAssigneeType.ROLE) {
      finalQuery.where().and().activatorRoleId().isNotNull();
    } else if (criteria.getTaskAssigneeType() == TaskAssigneeType.USER) {
      TaskQuery personalTaskQuery = TaskQuery.create().where().activatorUserId().isNotNull();
      if (criteria.getIncludedStates().contains(TaskState.PARKED)) {
        TaskQuery reservedTaskQuery =
            TaskQuery.create().where().activatorRoleId().isNotNull().and().state().isEqual(TaskState.PARKED);
        personalTaskQuery.where().or(reservedTaskQuery);
      }
      finalQuery.where().and(personalTaskQuery);
    }

    if (criteria.hasCategory()) {
      finalQuery.where().and(queryForCategory(criteria.getCategory()));
    }

    TaskSortingQueryAppender appender = new TaskSortingQueryAppender(finalQuery);
    finalQuery = appender.appendSorting(criteria).toQuery();
    return finalQuery;
  }

  public TaskQuery findNewTasks(TaskQuery currentQuerry, Date timeStamp, Boolean ignoreInvolvedUser) {
    TaskQuery returnQuery = currentQuerry;
    TaskQuery subQuery = TaskQuery.create();
    if (ignoreInvolvedUser) {
      subQuery.where().startTimestamp().isGreaterThan(timeStamp);
    } else {
      // task when delegate or reset, delegate time will write to customTimestampField5
      subQuery.where().startTimestamp().isGreaterThan(timeStamp).or().customTimestampField5()
          .isGreaterOrEqualThan(timeStamp);
    }
    currentQuerry.where().and(subQuery);

    return returnQuery;
  }

  private TaskQuery queryForKeyword(String keyword) {
    String containingKeyword = String.format("%%%s%%", keyword);
    TaskQuery filterByKeywordQuery =
        TaskQuery.create().where().or().name().isLikeIgnoreCase(containingKeyword).or().description()
            .isLikeIgnoreCase(containingKeyword);

    try {
      long idKeyword = Long.parseLong(keyword);
      filterByKeywordQuery.where().or().taskId().isEqual(idKeyword);
    } catch (NumberFormatException e) {
    }
    return filterByKeywordQuery;
  }

  private TaskQuery queryForCategory(String keyword) {
    String startingWithCategory = String.format("%s%%", keyword);
    TaskQuery filterByKeywordQuery = TaskQuery.create().where().category().isLike(startingWithCategory);

    return filterByKeywordQuery;
  }

  private TaskQuery queryForStates(List<TaskState> states) {
    TaskQuery stateFieldQuery = TaskQuery.create();

    IFilterQuery filterQuery = stateFieldQuery.where();
    for (TaskState state : states) {
      filterQuery.or().state().isEqual(state);
    }
    return stateFieldQuery;
  }

  private TaskQuery queryForTaskId(Long taskId) {
    TaskQuery query = TaskQuery.create();
    query.where().taskId().isEqual(taskId);
    return query;
  }

  private class TaskSortingQueryAppender {

    private TaskQuery query;

    public TaskSortingQueryAppender(TaskQuery query) {
      this.query = query;
    }

    public TaskQuery toQuery() {
      return query;
    }

    public TaskSortingQueryAppender appendSorting(TaskQueryCriteria criteria) {
      appendSortByPriorityIfSet(criteria);
      appendSortByNameIfSet(criteria);
      appendSortByActivatorIfSet(criteria);
      appendSortByIdIfSet(criteria);
      appendSortByCreationDateIfSet(criteria);
      appendSortByExpiryDateIfSet(criteria);
      appendSortByStateIfSet(criteria);
      return this;
    }

    private void appendSortByPriorityIfSet(TaskQueryCriteria criteria) {
      if (TaskSortField.PRIORITY.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().priority().descending();
        } else {
          query.orderBy().priority();
        }
      }
    }

    private void appendSortByNameIfSet(TaskQueryCriteria criteria) {
      if (TaskSortField.NAME.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().name().descending();
        } else {
          query.orderBy().name();
        }
      }
    }

    private void appendSortByActivatorIfSet(TaskQueryCriteria criteria) {
      if (TaskSortField.ACTIVATOR.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().activatorDisplayName().descending();
        } else {
          query.orderBy().activatorDisplayName();
        }
      }
    }

    private void appendSortByIdIfSet(TaskQueryCriteria criteria) {
      if (TaskSortField.ID.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().taskId().descending();
        } else {
          query.orderBy().taskId();
        }
      }
    }

    private void appendSortByCreationDateIfSet(TaskQueryCriteria criteria) {
      if (TaskSortField.CREATION_TIME.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().startTimestamp().descending();
        } else {
          query.orderBy().startTimestamp();
        }
      }
    }

    private void appendSortByExpiryDateIfSet(TaskQueryCriteria criteria) {
      if (TaskSortField.EXPIRY_TIME.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().expiryTimestamp().descending();
        } else {
          query.orderBy().expiryTimestamp();
        }
      }
    }

    private void appendSortByStateIfSet(TaskQueryCriteria criteria) {
      if (TaskSortField.STATE.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().state().descending();
        } else {
          query.orderBy().state();
        }
      }
    }
  }
}

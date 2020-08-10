package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class DashboardTaskSearchCriteria {

  private String name;
  private String description;
  private List<WorkflowPriority> priorities;
  private List<TaskState> states;
  private List<String> responsibles;
  private Date createdFrom;
  private Date createdTo;
  private Date expiryFrom;
  private Date expiryTo;
  private String category;
  
  public TaskQuery buildQuery() {
    TaskQuery query = TaskQuery.create();
    queryName(query);
    queryDescription(query);
    queryPriorities(query);
    queryStates(query);
    queryResponsibles(query);
    queryCategory(query);
    return query;
  }
  
  private void queryName(TaskQuery query) {
    if (StringUtils.isNotBlank(name)) {
      query.where().name().isLikeIgnoreCase(String.format("%s%%", name));
    }
  }
  
  private void queryDescription(TaskQuery query) {
    if (StringUtils.isNotBlank(description)) {
      query.where().description().isLikeIgnoreCase(String.format("%s%%", description));
    }
  }
  
  private void queryPriorities(TaskQuery query) {
    if (CollectionUtils.isNotEmpty(priorities)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (WorkflowPriority priority : priorities) {
        filterQuery.or().priority().isEqual(priority);
      }
      
      query.where().and(subQuery);
    }
  }

  private void queryStates(TaskQuery query) {
    if (CollectionUtils.isNotEmpty(states)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (TaskState state : states) {
        filterQuery.or().state().isEqual(state);
      }
      
      query.where().and(subQuery);
    }
  }
  
  private void queryResponsibles(TaskQuery query) {
    if (CollectionUtils.isNotEmpty(responsibles)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String responsible : responsibles) {
        filterQuery.or().activatorId().isEqual(responsible);
      }
      
      query.where().and(subQuery);
    }
  }
  
  private void queryCategory(TaskQuery query) {
    if (StringUtils.isNotBlank(category)) {
      query.where().category().isLike(String.format("%s%%", category));
    }
  }
}

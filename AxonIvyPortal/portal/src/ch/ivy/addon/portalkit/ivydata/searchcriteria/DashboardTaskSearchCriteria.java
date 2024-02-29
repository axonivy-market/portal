package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.OrderByColumnQuery;

public class DashboardTaskSearchCriteria {

  private boolean canWorkOn;
  private List<TaskColumnModel> columns;
  private List<DashboardFilter> filters;
  private List<DashboardFilter> userFilters;
  private String sortField;
  private boolean sortDescending;
  private boolean isInConfiguration;

  public TaskQuery buildQuery() {
    TaskQuery query = buildQueryWithoutOrderByClause();
    TaskSortingQueryAppender appender = new TaskSortingQueryAppender(query);
    query = appender.appendSorting(this).toQuery();
    return query;
  }

  public TaskQuery buildQueryWithoutOrderByClause() {
    TaskQuery query = TaskQuery.create();
    queryFilters(query);
    queryCanWorkOn(query);
    return query;
  }

  private void queryCanWorkOn(TaskQuery query) {
    if (canWorkOn) {
      query.where().currentUserCanWorkOn();
    }
  }

  private void queryComplexFilter(TaskQuery query, List<DashboardFilter> filterList) {
    if (CollectionUtils.isEmpty(filterList)) {
      return;
    }

    for (DashboardFilter filter : filterList) {
      if (Optional.ofNullable(filter).map(DashboardFilter::getOperator).isEmpty()) {
        continue;
      }

      FilterField filterField = TaskFilterFieldFactory.findBy(filter.getField(), filter.getFilterType());
      if (filterField != null) {
        TaskQuery filterQuery = filterField.generateFilterTaskQuery(filter);
        if (filterQuery != null) {
          query.where().and(filterQuery);
        }
      }
    }
  }

  private void queryFilters(TaskQuery query) {
    List<DashboardFilter> allFilters = new ArrayList<>(CollectionUtils.union(filters, userFilters));
    if (CollectionUtils.isNotEmpty(allFilters)) {
      queryComplexFilter(query, allFilters);
    }
  }
  
  public String getSortField() {
    return sortField;
  }

  public void setSortField(String sortField) {
    this.sortField = sortField;
  }

  public boolean isSortDescending() {
    return sortDescending;
  }

  public void setSortDescending(boolean sortDescending) {
    this.sortDescending = sortDescending;
  }
  
  public boolean isInConfiguration() {
    return isInConfiguration;
  }
  
  public void setInConfiguration(boolean isInConfiguration) {
    this.isInConfiguration = isInConfiguration;
  }

  private class TaskSortingQueryAppender {

    private TaskQuery query;
    private OrderByColumnQuery order;
    private boolean sortStandardColumn;

    public TaskSortingQueryAppender(TaskQuery query) {
      this.query = query;
    }

    public TaskQuery toQuery() {
      return query;
    }

    public TaskSortingQueryAppender appendSorting(DashboardTaskSearchCriteria criteria) {
      appendSortByNameIfSet(criteria);
      appendSortByResponsibleIfSet(criteria);
      appendSortByIdIfSet(criteria);
      appendSortByExpiryDateIfSet(criteria);
      appendSortByStateIfSet(criteria);
      appendSortByPriorityIfSet(criteria);
      if (criteria.isSortDescending() && order != null) {
        order.descending();
      }
      return this;
    }
    
    private void appendSortByNameIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.NAME.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().name();
        sortStandardColumn = true;
      }
    }
    
    private void appendSortByResponsibleIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.RESPONSIBLE.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().activatorDisplayName();
        sortStandardColumn = true;
      }
    }
    
    private void appendSortByIdIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.ID.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().taskId();
        sortStandardColumn = true;
      }
    }
    
    private void appendSortByStateIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.STATE.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().state();
        sortStandardColumn = true;
      }
    }

    private void appendSortByExpiryDateIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.EXPIRY.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().expiryTimestamp();
        sortStandardColumn = true;
      }
    }

    private void appendSortByPriorityIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.PRIORITY.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().priority();
        sortStandardColumn = true;
      }
    }

  }

  public boolean getCanWorkOn() {
    return canWorkOn;
  }

  public void setCanWorkOn(boolean canWorkOn) {
    this.canWorkOn = canWorkOn;
  }

  public List<TaskColumnModel> getColumns() {
    return columns;
  }
  
  public void setColumns(List<TaskColumnModel> columns) {
    this.columns = columns;
  }
  
  public List<DashboardFilter> getFilters() {
    return filters;
  }

  public void setFilters(List<DashboardFilter> filters) {
    this.filters = filters;
  }

  public List<DashboardFilter> getUserFilters() {
    return userFilters;
  }

  public void setUserFilters(List<DashboardFilter> userFilters) {
    this.userFilters = userFilters;
  }

}

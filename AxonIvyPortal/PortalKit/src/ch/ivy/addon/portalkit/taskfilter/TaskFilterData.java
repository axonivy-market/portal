package ch.ivy.addon.portalkit.taskfilter;

import java.util.List;

import ch.ivy.addon.portalkit.enums.FilterType;

public class TaskFilterData {
  private List<TaskFilter> taskFilters;
  private String filterName;
  private Long userId;
  private FilterType type;

  public TaskFilterData() {}

  public TaskFilterData(List<TaskFilter> taskFilters) {
    this.taskFilters = taskFilters;
  }

  public List<TaskFilter> getTaskFilters() {
    return taskFilters;
  }

  public void setTaskFilters(List<TaskFilter> taskFilters) {
    this.taskFilters = taskFilters;
  }

  public String getFilterName() {
    return filterName;
  }

  public void setFilterName(String filterName) {
    this.filterName = filterName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public FilterType getType() {
    return type;
  }

  public void setType(FilterType type) {
    this.type = type;
  }

}

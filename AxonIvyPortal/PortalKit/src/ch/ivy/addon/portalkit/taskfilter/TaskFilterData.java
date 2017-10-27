package ch.ivy.addon.portalkit.taskfilter;

import java.util.List;

import ch.ivy.addon.portalkit.enums.FilterType;

public class TaskFilterData {
  private List<TaskFilter> taskFilters;
  private String filterName;
  private String applicationName;
  private String userName;
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

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public FilterType getType() {
    return type;
  }

  public void setType(FilterType type) {
    this.type = type;
  }

}

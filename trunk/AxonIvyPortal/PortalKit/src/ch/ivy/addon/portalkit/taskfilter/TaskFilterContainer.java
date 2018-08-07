package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskFilterContainer {

  protected List<TaskFilter> filters = new ArrayList<>();
  protected TaskStateFilter stateFilter = new TaskStateFilter();

  public TaskFilterContainer() {
    filters.add(stateFilter);
  }
  
  public List<TaskFilter> getFilters() {
    return filters;
  }

  public void setFilters(List<TaskFilter> filters) {
    this.filters = filters;
  }
  
  public TaskStateFilter getStateFilter() {
    return stateFilter;
  }

  public void setStateFilter(TaskStateFilter stateFilter) {
    this.stateFilter = stateFilter;
  }

}

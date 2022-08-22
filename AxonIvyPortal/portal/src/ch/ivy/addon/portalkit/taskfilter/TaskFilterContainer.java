package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.taskfilter.impl.DefaultTaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskStateFilter;

/**
 * Extend this class for custom task filter container.
 * This container contains all your custom filter. Refer to {@link DefaultTaskFilterContainer} for sample.
 *
 */
public abstract class TaskFilterContainer {

  protected List<TaskFilter> filters = new ArrayList<>();
  protected TaskStateFilter stateFilter = new TaskStateFilter();

  /**
   * Initialize list of filters here.
   * Default there is task state filter.
   */
  public TaskFilterContainer() {
    filters.add(stateFilter);
  }
  
  /**
   * Getter for all filters
   * @return all filters
   */
  public List<TaskFilter> getFilters() {
    return filters;
  }

  /**
   * Setter for all filters
   * @param filters all filters
   */
  public void setFilters(List<TaskFilter> filters) {
    this.filters = filters;
  }
  
  /**
   * Getter for default task state filter
   * @return task state filter
   */
  public TaskStateFilter getStateFilter() {
    return stateFilter;
  }

  /**
   * Setter for default task state filter
   * @param stateFilter task state filter
   */
  public void setStateFilter(TaskStateFilter stateFilter) {
    this.stateFilter = stateFilter;
  }

}

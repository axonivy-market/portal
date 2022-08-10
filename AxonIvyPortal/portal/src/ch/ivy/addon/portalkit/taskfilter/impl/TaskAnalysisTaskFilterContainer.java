package ch.ivy.addon.portalkit.taskfilter.impl;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;

public class TaskAnalysisTaskFilterContainer extends DefaultTaskFilterContainer {

  private TaskWorkerFilter workerFilter = new TaskWorkerFilter();

  public TaskAnalysisTaskFilterContainer() {
    super();
    filters.add(workerFilter);
    Collections.sort(filters, new TaskFilterComparator());
  }
  
  public TaskAnalysisTaskFilterContainer(boolean filterForUnavailableActivator) {
    super(filterForUnavailableActivator);
    filters.add(workerFilter);
    Collections.sort(filters, new TaskFilterComparator());
  }

  public TaskWorkerFilter getWorkerFilter() {
    return workerFilter;
  }

  public void setWorkerFilter(TaskWorkerFilter workerFilter) {
    this.workerFilter = workerFilter;
  }

}

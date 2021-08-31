package ch.ivy.addon.portalkit.taskfilter.impl;

import java.util.Collections;

import portalmigration.comparator.TaskFilterComparator;

public class TaskAnalysisTaskFilterContainer extends DefaultTaskFilterContainer {

  private TaskNameFilter nameFilter = new TaskNameFilter();
  private TaskWorkerFilter workerFilter = new TaskWorkerFilter();

  public TaskAnalysisTaskFilterContainer() {
    super();
    filters.add(nameFilter);
    filters.add(workerFilter);
    Collections.sort(filters, new TaskFilterComparator());
  }

  public TaskWorkerFilter getWorkerFilter() {
    return workerFilter;
  }

  public void setWorkerFilter(TaskWorkerFilter workerFilter) {
    this.workerFilter = workerFilter;
  }

  public TaskNameFilter getNameFilter() {
    return nameFilter;
  }

  public void setNameFilter(TaskNameFilter nameFilter) {
    this.nameFilter = nameFilter;
  }
}

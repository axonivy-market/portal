package ch.ivy.addon.portalkit.taskfilter;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;

/**
 * @deprecated use TaskAnalysisTaskFilterContainer in package ch.ivy.addon.portalkit.casefilter.impl
 *
 */
@Deprecated(since="8.0.18")
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

package ch.ivy.addon.portalkit.taskfilter;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;

public class TaskAnalysisTaskFilterContainer extends DefaultTaskFilterContainer {

  private TaskNameFilter nameFilter = new TaskNameFilter();
  private TaskWorkerFilter workerFilter = new TaskWorkerFilter();
  private TaskCategoryFilter categoryFilter = new TaskCategoryFilter();

  public TaskAnalysisTaskFilterContainer() {
    super();
    filters.add(nameFilter);
    filters.add(workerFilter);
    filters.add(categoryFilter);
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

  public TaskCategoryFilter getCategoryFilter() {
    return categoryFilter;
  }

  public void setCategoryFilter(TaskCategoryFilter categoryFilter) {
    this.categoryFilter = categoryFilter;
  }
}

package ch.ivy.addon.portalkit.taskfilter;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;

public class TaskAnalysisTaskFilterContainer extends TaskFilterContainer {

  private TaskPriorityFilter priorityFilter = new TaskPriorityFilter();
  private TaskDescriptionFilter descriptionFilter = new TaskDescriptionFilter();
  private TaskCreationDateFilter creationDateFilter = new TaskCreationDateFilter();
  private TaskExpiredDateFilter expiredDateFilter = new TaskExpiredDateFilter();
  private TaskResponsibleFilter responsibleFilter = new TaskResponsibleFilter();
  private TaskNameFilter nameFilter = new TaskNameFilter();
  private TaskWorkerFilter workerFilter = new TaskWorkerFilter();

  public TaskAnalysisTaskFilterContainer() {
    super();
    filters.add(priorityFilter);
    filters.add(descriptionFilter);
    filters.add(creationDateFilter);
    filters.add(expiredDateFilter);
    filters.add(responsibleFilter);
    filters.add(nameFilter);
    filters.add(workerFilter);
    Collections.sort(filters, new TaskFilterComparator());
  }
  
  public TaskPriorityFilter getPriorityFilter() {
    return priorityFilter;
  }

  public void setPriorityFilter(TaskPriorityFilter priorityFilter) {
    this.priorityFilter = priorityFilter;
  }

  public TaskDescriptionFilter getDescriptionFilter() {
    return descriptionFilter;
  }

  public void setDescriptionFilter(TaskDescriptionFilter descriptionFilter) {
    this.descriptionFilter = descriptionFilter;
  }

  public TaskCreationDateFilter getCreationDateFilter() {
    return creationDateFilter;
  }

  public void setCreationDateFilter(TaskCreationDateFilter creationDateFilter) {
    this.creationDateFilter = creationDateFilter;
  }

  public TaskExpiredDateFilter getExpiredDateFilter() {
    return expiredDateFilter;
  }

  public void setExpiredDateFilter(TaskExpiredDateFilter expiredDateFilter) {
    this.expiredDateFilter = expiredDateFilter;
  }

  public TaskResponsibleFilter getResponsibleFilter() {
    return responsibleFilter;
  }

  public void setResponsibleFilter(TaskResponsibleFilter responsibleFilter) {
    this.responsibleFilter = responsibleFilter;
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

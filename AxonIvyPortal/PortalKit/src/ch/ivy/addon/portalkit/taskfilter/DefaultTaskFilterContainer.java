package ch.ivy.addon.portalkit.taskfilter;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;

public class DefaultTaskFilterContainer extends TaskFilterContainer {

  private TaskPriorityFilter priorityFilter = new TaskPriorityFilter();
  private TaskDescriptionFilter descriptionFilter = new TaskDescriptionFilter();
  private TaskCreationDateFilter creationDateFilter = new TaskCreationDateFilter();
  private TaskExpiredDateFilter expiredDateFilter = new TaskExpiredDateFilter();
  private TaskResponsibleFilter responsibleFilter = new TaskResponsibleFilter();
  private TaskCategoryFilter categoryFilter = new TaskCategoryFilter();
  private TaskCompletedDateFilter completedDateFilter = new TaskCompletedDateFilter();


  public DefaultTaskFilterContainer() {
    super();
    filters.add(priorityFilter);
    filters.add(descriptionFilter);
    filters.add(creationDateFilter);
    filters.add(expiredDateFilter);
    filters.add(responsibleFilter);
    filters.add(completedDateFilter);
    filters.add(categoryFilter);
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
  
  public TaskCompletedDateFilter getCompletedDateFilter() {
	return completedDateFilter;
  }

  public void setCompletedDateFilter(TaskCompletedDateFilter CompletedDateFilter) {
    this.completedDateFilter = CompletedDateFilter;
  }


  public TaskResponsibleFilter getResponsibleFilter() {
    return responsibleFilter;
  }

  public void setResponsibleFilter(TaskResponsibleFilter responsibleFilter) {
    this.responsibleFilter = responsibleFilter;
  }

  public TaskCategoryFilter getCategoryFilter() {
    return categoryFilter;
  }

  public void setCategoryFilter(TaskCategoryFilter categoryFilter) {
    this.categoryFilter = categoryFilter;
  }
}

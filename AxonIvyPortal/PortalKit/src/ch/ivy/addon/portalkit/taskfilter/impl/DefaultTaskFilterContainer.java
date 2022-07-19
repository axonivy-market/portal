package ch.ivy.addon.portalkit.taskfilter.impl;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;

public class DefaultTaskFilterContainer extends TaskFilterContainer {

  private TaskIdFilter idFilter = new TaskIdFilter();
  private TaskNameFilter nameFilter = new TaskNameFilter();
  private TaskPriorityFilter priorityFilter = new TaskPriorityFilter();
  private TaskDescriptionFilter descriptionFilter = new TaskDescriptionFilter();
  private TaskCreationDateFilter creationDateFilter = new TaskCreationDateFilter();
  private TaskExpiredDateFilter expiredDateFilter = new TaskExpiredDateFilter();
  private TaskResponsibleFilter responsibleFilter = new TaskResponsibleFilter();
  private TaskCategoryFilter categoryFilter = new TaskCategoryFilter();
  private TaskCompletedDateFilter completedDateFilter = new TaskCompletedDateFilter();
  private TaskForAvailableActivatorFilter availableActivatorFilter = new TaskForAvailableActivatorFilter();
  private TaskApplicationFilter applicationFilter = new TaskApplicationFilter();

  public DefaultTaskFilterContainer() {
    super();
    filters.add(idFilter);
    filters.add(nameFilter);
    filters.add(priorityFilter);
    filters.add(descriptionFilter);
    filters.add(creationDateFilter);
    filters.add(expiredDateFilter);
    filters.add(responsibleFilter);
    filters.add(completedDateFilter);
    filters.add(categoryFilter);
    filters.add(applicationFilter);
    Collections.sort(filters, new TaskFilterComparator());
  }
  
  public DefaultTaskFilterContainer(boolean filterForUnavailableActivator) {
    super();
    filters.add(idFilter);
    filters.add(nameFilter);
    filters.add(priorityFilter);
    filters.add(descriptionFilter);
    filters.add(creationDateFilter);
    filters.add(expiredDateFilter);
    filters.add(responsibleFilter);
    filters.add(completedDateFilter);
    filters.add(categoryFilter);
    filters.add(applicationFilter);
    if (filterForUnavailableActivator) {
      filters.add(availableActivatorFilter);
    }
    Collections.sort(filters, new TaskFilterComparator());
  }

  public TaskIdFilter getIdFilter() {
    return idFilter;
  }

  public void setIdFilter(TaskIdFilter idFilter) {
    this.idFilter = idFilter;
  }

  public TaskNameFilter getNameFilter() {
    return nameFilter;
  }

  public void setNameFilter(TaskNameFilter nameFilter) {
    this.nameFilter = nameFilter;
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
  
  public TaskForAvailableActivatorFilter getAvailableActivatorFilter() {
    return availableActivatorFilter;
  }

  public void setAvailableActivatorFilter(TaskForAvailableActivatorFilter availableActivatorFilter) {
    this.availableActivatorFilter = availableActivatorFilter;
  }

  public TaskApplicationFilter getApplicationFilter() {
    return applicationFilter;
  }

  public void setApplicationFilter(TaskApplicationFilter applicationFilter) {
    this.applicationFilter = applicationFilter;
  }
}

package com.axonivy.portal.developerexamples.component.customize;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskCategoryFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskCompletedDateFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskCreationDateFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskDescriptionFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskExpiredDateFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskForAvailableActivatorFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskPriorityFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskResponsibleFilter;

/**
 * @author tntanh
 *
 */
public class CustomizedTaskFilterContainer extends TaskFilterContainer {

  private TaskPriorityFilter priorityFilter = new TaskPriorityFilter();
  private TaskDescriptionFilter descriptionFilter = new TaskDescriptionFilter();
  private TaskCreationDateFilter creationDateFilter = new TaskCreationDateFilter();
  private TaskExpiredDateFilter expiredDateFilter = new TaskExpiredDateFilter();
  private TaskResponsibleFilter responsibleFilter = new TaskResponsibleFilter();
  private CustomerNameFilter customerNameFilter = new CustomerNameFilter();
  private ShipmentDateFilter shipmentDateFilter = new ShipmentDateFilter();
  private TaskCategoryFilter categoryFilter = new TaskCategoryFilter();
  private TaskCompletedDateFilter completedDateFilter = new TaskCompletedDateFilter();
  private TaskForAvailableActivatorFilter availableActivatorFilter = new TaskForAvailableActivatorFilter();
  
  public CustomizedTaskFilterContainer() {
    filters.add(priorityFilter);
    filters.add(completedDateFilter);
    filters.add(categoryFilter);
    filters.add(descriptionFilter);
    filters.add(creationDateFilter);
    filters.add(expiredDateFilter);
    filters.add(responsibleFilter);
    filters.add(customerNameFilter);
    filters.add(shipmentDateFilter);
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

  public CustomerNameFilter getCustomerNameFilter() {
    return customerNameFilter;
  }

  public void setCustomerNameFilter(CustomerNameFilter customerNameFilter) {
    this.customerNameFilter = customerNameFilter;
  }

  public ShipmentDateFilter getShipmentDateFilter() {
    return shipmentDateFilter;
  }

  public void setShipmentDateFilter(ShipmentDateFilter shipmentDateFilter) {
    this.shipmentDateFilter = shipmentDateFilter;
  }

  public TaskCategoryFilter getCategoryFilter() {
    return categoryFilter;
  }

  public void setCategoryFilter(TaskCategoryFilter categoryFilter) {
    this.categoryFilter = categoryFilter;
  }

  public TaskCompletedDateFilter getCompletedDateFilter() {
    return completedDateFilter;
  }

  public void setCompletedDateFilter(TaskCompletedDateFilter completedDateFilter) {
    this.completedDateFilter = completedDateFilter;
  }

  public TaskForAvailableActivatorFilter getAvailableActivatorFilter() {
    return availableActivatorFilter;
  }

  public void setAvailableActivatorFilter(TaskForAvailableActivatorFilter availableActivatorFilter) {
    this.availableActivatorFilter = availableActivatorFilter;
  }

}

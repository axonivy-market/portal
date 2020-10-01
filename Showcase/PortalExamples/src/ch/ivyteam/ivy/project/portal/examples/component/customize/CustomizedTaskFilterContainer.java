package ch.ivyteam.ivy.project.portal.examples.component.customize;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;
import ch.ivy.addon.portalkit.taskfilter.TaskCategoryFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskCreationDateFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskDescriptionFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskExpiredDateFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.TaskPriorityFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskResponsibleFilter;

/**
 * @author tntanh
 *
 */
public class CustomizedTaskFilterContainer extends TaskFilterContainer {

  private TaskDescriptionFilter descriptionFilter = new TaskDescriptionFilter();
  private TaskCreationDateFilter creationDateFilter = new TaskCreationDateFilter();
  private TaskExpiredDateFilter expiredDateFilter = new TaskExpiredDateFilter();
  private TaskResponsibleFilter responsibleFilter = new TaskResponsibleFilter();
  private CustomerNameFilter customerNameFilter = new CustomerNameFilter();
  private ShipmentDateFilter shipmentDateFilter = new ShipmentDateFilter();
  private TaskPriorityFilter priorityFilter = new TaskPriorityFilter();
  private TaskCategoryFilter categoryFilter = new TaskCategoryFilter();

  public CustomizedTaskFilterContainer() {
    filters.remove(stateFilter);
    filters.add(descriptionFilter);
    filters.add(creationDateFilter);
    filters.add(expiredDateFilter);
    filters.add(responsibleFilter);
    filters.add(customerNameFilter);
    filters.add(shipmentDateFilter);
    filters.add(priorityFilter);
    filters.add(categoryFilter);
    Collections.sort(filters, new TaskFilterComparator());
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

  public TaskPriorityFilter getPriorityFilter() {
    return priorityFilter;
  }

  public void setPriorityFilter(TaskPriorityFilter priorityFilter) {
    this.priorityFilter = priorityFilter;
  }

  public TaskCategoryFilter getCategoryFilter() {
    return categoryFilter;
  }

  public void setCategoryFilter(TaskCategoryFilter categoryFilter) {
    this.categoryFilter = categoryFilter;
  }

}

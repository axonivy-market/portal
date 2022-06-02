package ch.ivyteam.ivy.project.portal.examples.component.customize;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskCategoryFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskCreationDateFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskDescriptionFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskIdFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskNameFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskPriorityFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskResponsibleFilter;

/**
 * @author tntanh
 *
 */
public class CustomizedTaskFilterContainer extends TaskFilterContainer {

  private TaskIdFilter idFilter = new TaskIdFilter();
  private TaskNameFilter nameFilter = new TaskNameFilter();
  private TaskDescriptionFilter descriptionFilter = new TaskDescriptionFilter();
  private TaskCreationDateFilter creationDateFilter = new TaskCreationDateFilter();
  private TaskResponsibleFilter responsibleFilter = new TaskResponsibleFilter();
  private CustomerNameFilter customerNameFilter = new CustomerNameFilter();
  private ShipmentDateFilter shipmentDateFilter = new ShipmentDateFilter();
  private TaskPriorityFilter priorityFilter = new TaskPriorityFilter();
  private TaskCategoryFilter categoryFilter = new TaskCategoryFilter();

  public CustomizedTaskFilterContainer() {
    filters.remove(stateFilter);
    filters.add(descriptionFilter);
    filters.add(creationDateFilter);
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

}

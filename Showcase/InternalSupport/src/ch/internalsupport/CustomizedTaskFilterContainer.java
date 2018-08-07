package ch.internalsupport;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;
import ch.ivy.addon.portalkit.taskfilter.TaskCreationDateFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskDescriptionFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskExpiredDateFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.TaskResponsibleFilter;

public class CustomizedTaskFilterContainer extends TaskFilterContainer {

  private TaskDescriptionFilter descriptionFilter = new TaskDescriptionFilter();
  private TaskCreationDateFilter creationDateFilter = new TaskCreationDateFilter();
  private TaskExpiredDateFilter expiredDateFilter = new TaskExpiredDateFilter();
  private TaskResponsibleFilter responsibleFilter = new TaskResponsibleFilter();
  private CustomerNameFilter customerNameFilter = new CustomerNameFilter();
  private CustomerTypeFilter customerTypeFilter = new CustomerTypeFilter();

  public CustomizedTaskFilterContainer() {
    filters.remove(stateFilter);
    filters.add(descriptionFilter);
    filters.add(creationDateFilter);
    filters.add(expiredDateFilter);
    filters.add(responsibleFilter);
    filters.add(customerNameFilter);
    filters.add(customerTypeFilter);
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

  public CustomerTypeFilter getCustomerTypeFilter() {
    return customerTypeFilter;
  }

  public void setCustomerTypeFilter(CustomerTypeFilter customerTypeFilter) {
    this.customerTypeFilter = customerTypeFilter;
  }
}

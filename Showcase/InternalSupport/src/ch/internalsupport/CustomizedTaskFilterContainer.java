package ch.internalsupport;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.TaskFilterComparator;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskCreationDateFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskDescriptionFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskExpiredDateFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskIdFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskNameFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskResponsibleFilter;

public class CustomizedTaskFilterContainer extends TaskFilterContainer {

  private TaskIdFilter idFilter = new TaskIdFilter();
  private TaskNameFilter nameFilter = new TaskNameFilter();
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

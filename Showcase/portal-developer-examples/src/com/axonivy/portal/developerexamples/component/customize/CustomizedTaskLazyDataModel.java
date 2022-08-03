package com.axonivy.portal.developerexamples.component.customize;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomizedTaskLazyDataModel extends TaskLazyDataModel {

  public static final String CUSTOM_SHIPMENT_DATE = "ShipmentDate";
  public static final String CUSTOM_CUSTOMER_NAME = "CustomerName";
  private static final long serialVersionUID = 7996851327481047161L;

  public CustomizedTaskLazyDataModel() {
    super();
  }
  
  public CustomizedTaskLazyDataModel(String taskWidgetComponentId) {
    super(taskWidgetComponentId);
  }

  //===================Basic customization, extend columns====================
  
  // Extend sort fields, include 1 text field and 1 date time field
  @Override
  public void extendSort(TaskQuery taskQuery) {
    if (CUSTOM_CUSTOMER_NAME.equalsIgnoreCase(criteria.getSortField())) {
      if (criteria.isSortDescending()) {
        taskQuery.orderBy().customField().stringField(CUSTOM_CUSTOMER_NAME).descending();
      } else {
        taskQuery.orderBy().customField().stringField(CUSTOM_CUSTOMER_NAME);
      }
    } else if (CUSTOM_SHIPMENT_DATE.equalsIgnoreCase(criteria.getSortField())) {
      if (criteria.isSortDescending()) {
        taskQuery.orderBy().customField().timestampField(CUSTOM_SHIPMENT_DATE).descending();
      } else {
        taskQuery.orderBy().customField().timestampField(CUSTOM_SHIPMENT_DATE);
      }
    }
  }
  
  @Override
  public List<String> getDefaultColumns() {
    return Arrays.asList(TaskSortField.PRIORITY.name(), TaskSortField.NAME.name(), TaskSortField.ACTIVATOR.name(), TaskSortField.ID.name(), 
        TaskSortField.CREATION_TIME.name(), TaskSortField.EXPIRY_TIME.name(), CUSTOM_CUSTOMER_NAME, CUSTOM_SHIPMENT_DATE);
  }
  
  @Override
  public String getColumnLabel(String column) {
    return Ivy.cms().co("/DefaultColumns/" + column);
  }
  
//  ===================Extend filters====================
  
  @Override
  public void initFilterContainer() {
    filterContainer = new CustomizedTaskFilterContainer();
//    filterContainer = new CustomizedTaskFilterContainer(criteria.isAdminQuery());
  }
  
  /**
   * Customize sort field labels on mobile task list
   */
  @Override
  public String getSortFieldLabel(String fieldName) {
    return Ivy.cms().co("/sortFields/" + fieldName);
  }

}

package com.axonivy.portal.developerexamples.component.customize;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_TIMESTAMP_FIELD1;
import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_VARCHAR_FIELD5;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomizedTaskLazyDataModel extends TaskLazyDataModel {

  public static final String CUSTOM_TIMESTAMP_FIELD12 = "customTimestampField1";
  public static final String CUSTOM_VAR_CHAR_FIELD5 = "customVarCharField5";
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
    if (CUSTOM_VAR_CHAR_FIELD5.equalsIgnoreCase(criteria.getSortField())) {
      if (criteria.isSortDescending()) {
        taskQuery.orderBy().customField().stringField(CUSTOM_VARCHAR_FIELD5).descending();
      } else {
        taskQuery.orderBy().customField().stringField(CUSTOM_VARCHAR_FIELD5);
      }
    } else if (CUSTOM_TIMESTAMP_FIELD12.equalsIgnoreCase(criteria.getSortField())) {
      if (criteria.isSortDescending()) {
        taskQuery.orderBy().customField().timestampField(CUSTOM_TIMESTAMP_FIELD1).descending();
      } else {
        taskQuery.orderBy().customField().timestampField(CUSTOM_TIMESTAMP_FIELD1);
      }
    }
  }
  
  @Override
  protected List<String> getDefaultColumns() {
    return Arrays.asList(TaskSortField.PRIORITY.name(), TaskSortField.NAME.name(), TaskSortField.ACTIVATOR.name(), TaskSortField.ID.name(), 
        TaskSortField.CREATION_TIME.name(), TaskSortField.EXPIRY_TIME.name(), CUSTOM_VAR_CHAR_FIELD5, CUSTOM_TIMESTAMP_FIELD12);
  }
  
  @Override
  public String getColumnLabel(String column) {
    return Ivy.cms().co("/DefaultColumns/" + column);
  }
  
//  ===================Extend filters====================
  
  @Override
  protected void initFilterContainer() {
    filterContainer = new CustomizedTaskFilterContainer();
  }
  
  /**
   * Customize sort field labels on mobile task list
   */
  @Override
  public String getSortFieldLabel(String fieldName) {
    return Ivy.cms().co("/sortFields/" + fieldName);
  }

}

package ch.ivyteam.ivy.project.portal.examples.component.customize;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_TIMESTAMP_FIELD1;
import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_VARCHAR_FIELD5;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomizedTaskLazyDataModel extends TaskLazyDataModel {

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
    if ("customVarCharField5".equalsIgnoreCase(criteria.getSortField())) {
      if (criteria.isSortDescending()) {
        taskQuery.orderBy().customField().stringField(CUSTOM_VARCHAR_FIELD5).descending();
      } else {
        taskQuery.orderBy().customField().stringField(CUSTOM_VARCHAR_FIELD5);
      }
    } else if ("customTimestampField1".equalsIgnoreCase(criteria.getSortField())) {
      if (criteria.isSortDescending()) {
        taskQuery.orderBy().customField().stringField(CUSTOM_TIMESTAMP_FIELD1).descending();
      } else {
        taskQuery.orderBy().customField().stringField(CUSTOM_TIMESTAMP_FIELD1);
      }
    }
  }
  
  @Override
  public List<String> getDefaultColumns() {
    return Arrays.asList("PRIORITY", "NAME", "ACTIVATOR", "ID", "CREATION_TIME", "customVarCharField5", "customTimestampField1");
  }

  @Override
  public String getColumnLabel(String column) {
    return Ivy.cms().co("/DefaultColumns/" + column);
  }
  
//  ===================Extend filters====================
  
  @Override
  public void initFilterContainer() {
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

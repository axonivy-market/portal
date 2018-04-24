package ch.ivyteam.ivy.project.portal.examples.component.customize;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomizedTaskLazyDataModel extends TaskLazyDataModel {

  private static final long serialVersionUID = 7996851327481047161L;

  public CustomizedTaskLazyDataModel() {
    super();
  }

  //===================Basic customization, extend columns====================
  
  // Extend sort fields, include 1 text field and 1 date time field
  @Override
  public void extendSort(TaskQuery taskQuery) {
    if ("customVarcharField5".equalsIgnoreCase(queryCriteria.getSortField())) {
      if (queryCriteria.isSortDescending()) {
        taskQuery.orderBy().customVarCharField5().descending();
      } else {
        taskQuery.orderBy().customVarCharField5();
      }
    } else if ("customTimestampField1".equalsIgnoreCase(queryCriteria.getSortField())) {
      if (queryCriteria.isSortDescending()) {
        taskQuery.orderBy().customTimestampField1().descending();
      } else {
        taskQuery.orderBy().customTimestampField1();
      }
    }
  }

  @Override
  public void extendSortTasksInNotDisplayedTaskMap() {
    if ("customVarcharField5".equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorString(RemoteTask::getCustomVarCharField5);
    } else if ("customTimestampField1".equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getCustomTimestampField1);
    }
  }
  
  @Override
  protected List<String> getDefaultColumns() {
    return Arrays.asList("PRIORITY", "NAME", "ACTIVATOR", "ID", "CREATION_TIME", "EXPIRY_TIME", "customVarcharField5", "customTimestampField1");
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

}

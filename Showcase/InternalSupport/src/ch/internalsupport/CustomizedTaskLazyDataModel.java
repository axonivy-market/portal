package ch.internalsupport;

import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomizedTaskLazyDataModel extends TaskLazyDataModel {

  private static final long serialVersionUID = 7996851327481047161L;

  public CustomizedTaskLazyDataModel() {
    super();
  }

  @Override
  protected void initFilterContainer() {
    filterContainer = new CustomizedTaskFilterContainer();
  }

  @Override
  public void extendSort(TaskQuery taskQuery) {
    if ("customVarcharField5".equalsIgnoreCase(queryCriteria.getSortField())) {
      if (queryCriteria.isSortDescending()) {
        taskQuery.orderBy().customVarCharField5().descending();
      } else {
        taskQuery.orderBy().customVarCharField5();
      }
    } else if ("customVarcharField1".equalsIgnoreCase(queryCriteria.getSortField())) {
      if (queryCriteria.isSortDescending()) {
        taskQuery.orderBy().customVarCharField1().descending();
      } else {
        taskQuery.orderBy().customVarCharField1();
      }
    }
  }

  @Override
  public void extendSortTasksInNotDisplayedTaskMap() {
    if ("customVarcharField5".equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorString(RemoteTask::getCustomVarCharField5);
    } else if ("customVarcharField1".equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorString(RemoteTask::getCustomVarCharField1);
    }
  }

}

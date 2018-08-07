package ch.internalsupport;

import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;

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
  public void extendSort() {
    if ("customVarcharField5".equalsIgnoreCase(queryCriteria.getSortField())) {
      if (queryCriteria.isSortDescending()) {
        queryCriteria.getTaskQuery().orderBy().customVarCharField5().descending();
      } else {
        queryCriteria.getTaskQuery().orderBy().customVarCharField5();
      }
    } else if ("customVarcharField1".equalsIgnoreCase(queryCriteria.getSortField())) {
      if (queryCriteria.isSortDescending()) {
        queryCriteria.getTaskQuery().orderBy().customVarCharField1().descending();
      } else {
        queryCriteria.getTaskQuery().orderBy().customVarCharField1();
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

package ch.ivy.addon.portalkit.service;


import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData;

public class TaskFilterService extends AbstractFilterService<TaskFilterData> {
  @Override
  public Class<TaskFilterData> getType() {
    return TaskFilterData.class;
  }

  public void applyFilter(TaskLazyDataModel dataModel, TaskFilterData savedFilterData) throws ReflectiveOperationException {
    List<TaskFilter> filters = dataModel.getFilterContainer().getFilters();
    dataModel.setSelectedFilters(new ArrayList<>());
    for (int i = 0; i < filters.size(); i++) {
      TaskFilter taskFilter = dataModel.getFilterContainer().getFilters().get(i);
      for (int j = 0; j < savedFilterData.getFilters().size(); j++) {
        TaskFilter savedTaskFilter = savedFilterData.getFilters().get(j);
        if (taskFilter.getClass().equals(savedTaskFilter.getClass())) {
          copyFilterValues(taskFilter, savedTaskFilter);
          dataModel.getSelectedFilters().add(taskFilter);
          break;
        }
      }
    }
  }
}

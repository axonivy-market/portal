package ch.ivy.addon.portalkit.service;


import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.datamodel.internal.TaskAnalysisLazyDataModel;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisFilterData;

public class TaskAnalysisFilterService extends AbstractFilterService<TaskAnalysisFilterData> {
  @Override
  public Class<TaskAnalysisFilterData> getType() {
    return TaskAnalysisFilterData.class;
  }

  public void applyFilter(TaskAnalysisLazyDataModel dataModel, TaskAnalysisFilterData savedFilterData)
      throws ReflectiveOperationException {
    applyTaskFilter(dataModel, savedFilterData);
    applyCaseFilter(dataModel, savedFilterData);
  }

  private void applyTaskFilter(TaskAnalysisLazyDataModel dataModel, TaskAnalysisFilterData savedFilterData)
      throws ReflectiveOperationException {
    List<TaskFilter> filters = dataModel.getTaskFilterContainer().getFilters();
    dataModel.setSelectedTaskFilters(new ArrayList<>());
    for (int i = 0; i < filters.size(); i++) {
      TaskFilter taskFilter = dataModel.getTaskFilterContainer().getFilters().get(i);
      for (int j = 0; j < savedFilterData.getTaskFilters().size(); j++) {
        TaskFilter savedTaskFilter = savedFilterData.getTaskFilters().get(j);
        if (taskFilter.getClass().equals(savedTaskFilter.getClass())) {
          copyFilterValues(taskFilter, savedTaskFilter);
          dataModel.getSelectedTaskFilters().add(taskFilter);
          break;
        }
      }
    }
  }

  public void applyCaseFilter(TaskAnalysisLazyDataModel dataModel, TaskAnalysisFilterData savedFilterData)
      throws ReflectiveOperationException {
    List<CaseFilter> filters = dataModel.getCaseFilterContainer().getFilters();
    dataModel.setSelectedCaseFilters(new ArrayList<>());
    for (int i = 0; i < filters.size(); i++) {
      CaseFilter filter = dataModel.getCaseFilterContainer().getFilters().get(i);
      for (int j = 0; j < savedFilterData.getCaseFilters().size(); j++) {
        CaseFilter savedFilter = savedFilterData.getCaseFilters().get(j);
        if (filter.getClass().equals(savedFilter.getClass())) {
          copyFilterValues(filter, savedFilter);
          dataModel.getSelectedCaseFilters().add(filter);
          break;
        }
      }
    }
  }
}

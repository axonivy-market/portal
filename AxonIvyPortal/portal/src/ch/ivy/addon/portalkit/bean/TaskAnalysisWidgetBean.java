package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.Visibility;

import ch.ivy.addon.portalkit.datamodel.internal.TaskAnalysisLazyDataModel;
import ch.ivy.addon.portalkit.enums.TaskAndCaseAnalysisColumn;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.TaskAnalysisFilterService;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisFilterData;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class TaskAnalysisWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private TaskAnalysisLazyDataModel dataModel;
  private Map<String, Boolean> columns;
  private boolean isHideCaseCreator;
  private boolean isCaseOwnerEnabled;

  public TaskAnalysisWidgetBean() {
  }

  @PostConstruct
  public void init() {
    isCaseOwnerEnabled = GlobalSettingService.getInstance().isCaseOwnerEnabled();
    isHideCaseCreator = GlobalSettingService.getInstance().isHideCaseCreator();
    columns = new HashMap<>();
    for(TaskAndCaseAnalysisColumn column : TaskAndCaseAnalysisColumn.values()) {
      if (column.name().equals(TaskAndCaseAnalysisColumn.CASE_CREATOR.name())
          && isHideCaseCreator) {
        columns.put(column.name(), false);
      }
      columns.put(column.name(), column.isDefaultColumn());
    }

  }

  public TaskAnalysisLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(TaskAnalysisLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public boolean isDeleteFilterEnabledFor(TaskAnalysisFilterData filterData) {
    TaskAnalysisFilterService filterService = new TaskAnalysisFilterService();
    return filterService.isDeleteFilterEnabledFor(filterData);
  }
  
  public void onToggleColumns(ToggleEvent e) {
    TaskAndCaseAnalysisColumn toggledColumn = TaskAndCaseAnalysisColumn.values()[(Integer) e.getData()];
    if (isHideCaseCreator() && TaskAndCaseAnalysisColumn.CASE_CREATOR.equals(toggledColumn)) {
      toggledColumn = TaskAndCaseAnalysisColumn.CASE_STATE;
    }

    // If the selected column has index of "Case Owner" column
    // but the case owner feature is disabled
    // shift the selected column to "Application"
    if (!isCaseOwnerEnabled() && TaskAndCaseAnalysisColumn.CASE_OWNER.equals(toggledColumn)) {
      toggledColumn = TaskAndCaseAnalysisColumn.APPLICATION;
    }
    columns.put(toggledColumn.name(), e.getVisibility() == Visibility.VISIBLE);
  }

  public Map<String, Boolean> getColumns() {
    return columns;
  }

  public void setColumns(Map<String, Boolean> columns) {
    this.columns = columns;
  }
  
  public int getMaxTaskNumberInExcel() {
    return Exporter.MAX_ROW_NUMBER_IN_EXCEL;
  }
  

  public boolean isHideCaseCreator() {
    return isHideCaseCreator;
  }

  public void setHideCaseCreator(boolean isHideCaseCreator) {
    this.isHideCaseCreator = isHideCaseCreator;
  }

  public boolean isCaseOwnerEnabled() {
    return isCaseOwnerEnabled;
  }

  public void setCaseOwnerEnabled(boolean isCaseOwnerEnabled) {
    this.isCaseOwnerEnabled = isCaseOwnerEnabled;
  }

  public SortMeta getSortByTaskName() {
    return SortFieldUtil.buildSortMeta("TASK_NAME", false);
  }
}

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

@ManagedBean
@ViewScoped
public class TaskAnalysisWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private TaskAnalysisLazyDataModel dataModel;
  private Map<String, Boolean> columns;

  public TaskAnalysisWidgetBean() {
  }

  @PostConstruct
  public void init() {
    columns = new HashMap<>();
    for(TaskAndCaseAnalysisColumn column : TaskAndCaseAnalysisColumn.values()) {
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
  
  public boolean isCaseOwnerEnabled() {
    return new GlobalSettingService().isCaseOwnerEnabled();
  }

  public SortMeta getSortByTaskName() {
    return SortFieldUtil.buildSortMeta("TASK_NAME", false);
  }
}

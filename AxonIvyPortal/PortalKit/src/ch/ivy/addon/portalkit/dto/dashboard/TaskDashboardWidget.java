package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class TaskDashboardWidget extends DashboardWidget {
  private static final long serialVersionUID = 3048837559125720787L;

  @JsonIgnore
  private DashboardTaskLazyDataModel dataModel;
  
  public TaskDashboardWidget() {
    dataModel = new DashboardTaskLazyDataModel();
    setColumns(new ArrayList<>());
  }
  
  public void onToggleColumns(ToggleEvent e) {
    int pos = (int) e.getData();
    boolean isVisible = e.getVisibility() == Visibility.VISIBLE;
    ColumnModel columnModel = getColumns().get(pos);
    columnModel.setVisible(isVisible);
  }
  
  @JsonIgnore
  public List<ColumnModel> getFilterableColumns() {
    return getColumns().stream()
        .filter(c -> !StringUtils.equalsIgnoreCase(c.getField(), DashboardStandardTaskColumn.START.toString()) 
            && !StringUtils.equalsIgnoreCase(c.getField(), DashboardStandardTaskColumn.ID.toString())) 
        .collect(Collectors.toList());
  }
  
  public boolean getCanWorkOn() {
    return this.dataModel.getCanWorkOn();
  }
  
  public void setCanWorkOn(boolean canWorkOn) {
    this.dataModel.setCanWorkOn(canWorkOn);
  }

  public List<String> getCategories() {
    return this.dataModel.getCategories();
  }

  public void setCategories(List<String> categories) {
    this.dataModel.setCategories(categories);
  }
  
  @JsonIgnore
  public String getDisplayCategories() {
    return Optional.ofNullable(getCategories()).orElse(new ArrayList<>()).stream().collect(Collectors.joining(", "));
  }
  
  public String getSortField() {
    return this.dataModel.getCriteria().getSortField();
  }
  
  public void setSortField(String sortField) {
    this.dataModel.getCriteria().setSortField(sortField);
  }
  
  public boolean isSortDescending() {
    return this.dataModel.getCriteria().isSortDescending();
  }
  
  public void setSortDescending(boolean sortDescending) {
    this.dataModel.getCriteria().setSortDescending(sortDescending);
  }

  public DashboardTaskLazyDataModel getDataModel() {
    return dataModel;
  }
  
  public void setDataModel(DashboardTaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }
  
  public List<ColumnModel> getColumns() {
    return this.dataModel.getCriteria().getColumns();
  }
  
  public void setColumns(List<ColumnModel> columns) {
    this.dataModel.getCriteria().setColumns(columns);
  }
  
  @JsonIgnore
  public int getTaskCount() {
    return getDataModel().getRowCount();
  }
}
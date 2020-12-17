package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;

public class TaskDashboardWidget extends DashboardWidget {
  private static final long serialVersionUID = 3048837559125720787L;

  @JsonIgnore
  private DashboardTaskLazyDataModel dataModel;
  @JsonIgnore
  private CheckboxTreeNode categoryTree;
  @JsonIgnore
  private CheckboxTreeNode[] categoryNodes;
  
  public TaskDashboardWidget() {
    dataModel = new DashboardTaskLazyDataModel();
    setColumns(new ArrayList<>());
  }
  
  public CheckboxTreeNode[] getCategoryNodes() {
    return categoryNodes;
  }

  public void setCategoryNodes(CheckboxTreeNode[] categoryNodes) {
    this.categoryNodes = categoryNodes;
    setUserFilterCategories(CategoryUtils.getCategoryPaths(categoryNodes));
  }
  
  public CheckboxTreeNode getCategoryTree() {
    return categoryTree;
  }
  
  public void setCategoryTree(CheckboxTreeNode categoryTree) {
    this.categoryTree = categoryTree;
  }
  
  public void buildCategoryTree() {
    this.categoryTree = TaskTreeUtils.buildTaskCategoryCheckboxTreeRoot();
    CategoryUtils.disableSelectionExcept(this.categoryTree, getCategories());
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
  
  @JsonIgnore
  public List<String> getUserFilterCategories() {
    return this.dataModel.getUserFilterCategories();
  }

  public void setUserFilterCategories(List<String> categories) {
    this.dataModel.setUserFilterCategories(categories);
  }
  
  @JsonIgnore
  public String getUserFilterDisplayCategories() {
    return Optional.ofNullable(getUserFilterCategories()).orElse(new ArrayList<>()).stream().collect(Collectors.joining(", "));
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
  
  @JsonIgnore
  public boolean isInConfiguration() {
    return this.dataModel.getCriteria().isInConfiguration();
  }
  
  public void setInConfiguration(boolean isInConfiguration) {
    this.dataModel.getCriteria().setInConfiguration(isInConfiguration);
  }
}
package portalmigration.version112.dto.taskcolumn;

import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivyteam.ivy.workflow.ITask;
import portalmigration.version112.bo.CategoryNode;
import portalmigration.version112.enums.DashboardColumnFormat;
import portalmigration.version112.enums.DashboardStandardTaskColumn;

public class CategoryColumnModel extends TaskColumnModel {

  private static final long serialVersionUID = 2890439587578791422L;

  @JsonIgnore
  private CheckboxTreeNode<CategoryNode> categoryTree;
  @JsonIgnore
  private CheckboxTreeNode<CategoryNode>[] selectionCategoryNodes;
  @JsonIgnore
  private CheckboxTreeNode<CategoryNode> userCategoryTree;
  @JsonIgnore
  private CheckboxTreeNode<CategoryNode>[] userSelectionCategoryNodes;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.CATEGORY.getField();
    this.style = defaultIfEmpty(this.style, getDefaultStyle());
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.format = getDefaultFormat();
    this.sortable = getDefaultSortable();
    this.sorted = false;
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CATEGORY";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }

  @Override
  public Boolean getDefaultSortable() {
    return false;
  }

  @Override
  public String getDefaultStyle() {
    return NORMAL_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__category u-text-align-center";
  }

  @Override
  public Object display(ITask task) {
    if (task == null) {
      return "";
    }
    return task.getCategory().getName();
  }

  @JsonIgnore
  public CheckboxTreeNode<CategoryNode>[] getCategoryNodes() {
    return selectionCategoryNodes;
  }

  @JsonIgnore
  public void setCategoryNodes(CheckboxTreeNode<CategoryNode>[] categoryNodes) {
    this.selectionCategoryNodes = categoryNodes;
  }

  @JsonIgnore
  public CheckboxTreeNode<CategoryNode> getCategoryTree() {
    return categoryTree;
  }

  @JsonIgnore
  public void setCategoryTree(CheckboxTreeNode<CategoryNode> categoryTree) {
    this.categoryTree = categoryTree;
  }

  public CheckboxTreeNode<CategoryNode>[] getSelectionCategoryNodes() {
    return selectionCategoryNodes;
  }

  public void setSelectionCategoryNodes(CheckboxTreeNode<CategoryNode>[] selectionCategoryNodes) {
    this.selectionCategoryNodes = selectionCategoryNodes;
  }

  public CheckboxTreeNode<CategoryNode> getUserCategoryTree() {
    return userCategoryTree;
  }

  public void setUserCategoryTree(CheckboxTreeNode<CategoryNode> userCategoryTree) {
    this.userCategoryTree = userCategoryTree;
  }

  public CheckboxTreeNode<CategoryNode>[] getUserSelectionCategoryNodes() {
    return userSelectionCategoryNodes;
  }

  public void setUserSelectionCategoryNodes(CheckboxTreeNode<CategoryNode>[] userSelectionCategoryNodes) {
    this.userSelectionCategoryNodes = userSelectionCategoryNodes;
  }

}

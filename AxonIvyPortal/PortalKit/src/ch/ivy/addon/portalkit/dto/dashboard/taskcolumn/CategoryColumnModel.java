package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;
import ch.ivyteam.ivy.workflow.ITask;

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
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CATEGORY");
    this.field = DashboardStandardTaskColumn.CATEGORY.getField();
    this.style = defaultIfEmpty(this.style, NORMAL_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__category u-text-align-center");
    this.fieldStyleClass = defaultIfEmpty(this.fieldStyleClass, StringUtils.EMPTY);
    this.format = DashboardColumnFormat.CUSTOM;
    this.sortable = false;
    this.sorted = false;
  }

  @Override
  public Object display(ITask task) {
    if (task == null) {
      return "";
    }
    return task.getCategory().getName();
  }

  @JsonIgnore
  public String getDisplayCategories() {
    return generateFriendlyCategoryPath(filterList, selectionCategoryNodes);
  }

  @JsonIgnore
  public String getUserFriendlyCategories() {
    return generateFriendlyCategoryPath(userFilterList, userSelectionCategoryNodes);
  }

  @JsonIgnore
  public String generateFriendlyCategoryPath(List<String> filterList, CheckboxTreeNode<CategoryNode>[] treeNodes) {
    var paths = Optional.ofNullable(filterList).orElse(new ArrayList<>());
    if (CollectionUtils.isEmpty(filterList) && treeNodes != null && treeNodes.length > 0) {
      paths = Optional.ofNullable(CategoryUtils.getCategoryPaths(treeNodes)).orElse(new ArrayList<>());
    }
    return paths.stream().collect(Collectors.joining(", "));
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
  public void updateCategoriesPath() {
    setUserFilterList(CategoryUtils.getCategoryPaths(userSelectionCategoryNodes));
    setFilterList(CategoryUtils.getCategoryPaths(selectionCategoryNodes));
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

  @JsonIgnore
  public void loadCategories(boolean isConfigurationMode) {
    var availableCategories = TaskTreeUtils.buildTaskCategoryCheckboxTreeRoot();
    if (isConfigurationMode) {
      this.categoryTree = availableCategories;
      if (CollectionUtils.isNotEmpty(filterList)) {
        CategoryUtils.recoverSelectedCategories(this.categoryTree, filterList);
      }
    } else {
      this.userCategoryTree = availableCategories;
      CategoryUtils.disableSelectionExcept(this.userCategoryTree, filterList);
      if (CollectionUtils.isNotEmpty(userFilterList)) {
        CategoryUtils.recoverSelectedCategories(this.userCategoryTree, userFilterList);
      }
    }
  }

}

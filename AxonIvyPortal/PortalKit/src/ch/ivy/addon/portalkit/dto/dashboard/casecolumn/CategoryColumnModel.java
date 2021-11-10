package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.CategoryUtils;

public class CategoryColumnModel extends CaseColumnModel {

  private static final long serialVersionUID = 2890439587578791422L;

  @JsonIgnore
  private CheckboxTreeNode categoryTree;
  @JsonIgnore
  private CheckboxTreeNode[] selectionCategoryNodes;
  @JsonIgnore
  private CheckboxTreeNode userCategoryTree;
  @JsonIgnore
  private CheckboxTreeNode[] userSelectionCategoryNodes;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CATEGORY");
    this.field = DashboardStandardTaskColumn.CATEGORY.getField();
    this.style = defaultIfEmpty(this.style, NORMAL_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__category u-text-align-center");
    this.fieldStyleClass = defaultIfEmpty(this.fieldStyleClass, StringUtils.EMPTY);
    this.format = DashboardColumnFormat.CUSTOM;
  }

  @JsonIgnore
  public String getDisplayCategories() {
    var paths = Optional.ofNullable(filterList).orElse(new ArrayList<>());
    if (CollectionUtils.isEmpty(filterList) && selectionCategoryNodes != null && selectionCategoryNodes.length > 0) {
      paths = Optional.ofNullable(CategoryUtils.getCategoryPaths(selectionCategoryNodes)).orElse(new ArrayList<>());
    }
    return paths.stream().collect(Collectors.joining(", "));
  }

  @JsonIgnore
  public String getUserFriendlyCategories() {
    var paths = Optional.ofNullable(userFilterList).orElse(new ArrayList<>());
    if (CollectionUtils.isEmpty(userFilterList) && userSelectionCategoryNodes != null
        && userSelectionCategoryNodes.length > 0) {
      paths = Optional.ofNullable(CategoryUtils.getCategoryPaths(userSelectionCategoryNodes)).orElse(new ArrayList<>());
    }
    return paths.stream().collect(Collectors.joining(", "));
  }

  @JsonIgnore
  public CheckboxTreeNode[] getCategoryNodes() {
    return selectionCategoryNodes;
  }

  @JsonIgnore
  public void setCategoryNodes(CheckboxTreeNode[] categoryNodes) {
    this.selectionCategoryNodes = categoryNodes;
  }

  @JsonIgnore
  public void updateCategoriesPath() {
    setUserFilterList(CategoryUtils.getCategoryPaths(userSelectionCategoryNodes));
    setFilterList(CategoryUtils.getCategoryPaths(selectionCategoryNodes));
  }

  @JsonIgnore
  public CheckboxTreeNode getCategoryTree() {
    return categoryTree;
  }

  @JsonIgnore
  public void setCategoryTree(CheckboxTreeNode categoryTree) {
    this.categoryTree = categoryTree;
  }

  public CheckboxTreeNode[] getSelectionCategoryNodes() {
    return selectionCategoryNodes;
  }

  public void setSelectionCategoryNodes(CheckboxTreeNode[] selectionCategoryNodes) {
    this.selectionCategoryNodes = selectionCategoryNodes;
  }

  public CheckboxTreeNode getUserCategoryTree() {
    return userCategoryTree;
  }

  public void setUserCategoryTree(CheckboxTreeNode userCategoryTree) {
    this.userCategoryTree = userCategoryTree;
  }

  public CheckboxTreeNode[] getUserSelectionCategoryNodes() {
    return userSelectionCategoryNodes;
  }

  public void setUserSelectionCategoryNodes(CheckboxTreeNode[] userSelectionCategoryNodes) {
    this.userSelectionCategoryNodes = userSelectionCategoryNodes;
  }

  @JsonIgnore
  public void loadCategories(boolean isConfigurationMode) {
    var availableCategories = CaseTreeUtils.buildCaseCategoryCheckboxTreeRoot();
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

package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.ProcessTreeUtils;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryColumnModel extends ProcessColumnModel implements Serializable {

  private static final long serialVersionUID = -5311971648747993138L;
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
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/common/categories");
    this.field = DashboardStandardProcessColumn.CATEGORY.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-process__category");
    this.format = DashboardColumnFormat.CUSTOM;
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
  public String generateFriendlyCategoryPath(List<String> filterList, CheckboxTreeNode[] treeNodes) {
    var paths = Optional.ofNullable(filterList).orElse(new ArrayList<>());
    if (CollectionUtils.isEmpty(filterList) && treeNodes != null && treeNodes.length > 0) {
      paths = Optional.ofNullable(CategoryUtils.getCategoryPaths(treeNodes)).orElse(new ArrayList<>());
    }
    return paths.stream().collect(Collectors.joining(", "));
  }

  @JsonIgnore
  public void updateCategoriesPath() {
    setUserFilterList(CategoryUtils.getCategoryPaths(userSelectionCategoryNodes));
    setFilterList(CategoryUtils.getCategoryPaths(selectionCategoryNodes));
  }

  @JsonIgnore
  public void loadCategories(boolean isConfigurationMode) {
    var availableCategories = ProcessTreeUtils.buildProcessCategoryCheckboxTreeRoot(DashboardWidgetUtils.getAllPortalProcesses());
    if (isConfigurationMode) {
      this.categoryTree = availableCategories;
      if (CollectionUtils.isNotEmpty(filterList)) {
        CategoryUtils.recoverSelectedCategories(this.categoryTree, filterList);
      }
    } else {
      this.userCategoryTree = availableCategories;
      CategoryUtils.disableSelectionWithoutSelectingExcept(userCategoryTree, filterList);
      if (CollectionUtils.isNotEmpty(userFilterList)) {
        CategoryUtils.recoverSelectedCategories(this.userCategoryTree, userFilterList);
      }
    }
  }

  public CheckboxTreeNode getCategoryTree() {
    return categoryTree;
  }

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
}

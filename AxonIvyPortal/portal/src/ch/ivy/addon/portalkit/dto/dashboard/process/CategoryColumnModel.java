package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.bean.CompactDashboardProcessBean;
import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.ProcessTreeUtils;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryColumnModel extends ProcessColumnModel implements Serializable {

  private static final long serialVersionUID = -5311971648747993138L;
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
    this.field = DashboardStandardProcessColumn.CATEGORY.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-process__category");
    this.format = DashboardColumnFormat.CUSTOM;
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/categories";
  }

  @JsonIgnore
  public String getDisplayCategories() {
    return generateFriendlyCategoryPath(filterList, selectionCategoryNodes, false);
  }

  @JsonIgnore
  public String getUserFriendlyCategories() {
    return generateFriendlyCategoryPath(userFilterList, userSelectionCategoryNodes, true);
  }

  @JsonIgnore
  public String generateFriendlyCategoryPath(List<String> filterList, CheckboxTreeNode<CategoryNode>[] treeNodes, boolean isUserFilter) {
    if (CollectionUtils.isEmpty(filterList)) {
      return "";
    }
    if (treeNodes != null && treeNodes.length > 0) {
      return CategoryUtils.getCategoryValues(treeNodes);
    } else {
      if (isUserFilter && userCategoryTree != null) {
        return CategoryUtils.getSelectedNodesAsString(userCategoryTree);
      } else if (categoryTree != null) {
        return CategoryUtils.getSelectedNodesAsString(categoryTree);
      }
    }
    return CollectionUtils.emptyIfNull(filterList).stream().collect(Collectors.joining(CategoryUtils.CATEGORY_SEPARATOR));
  }

  @JsonIgnore
  public void updateCategoriesPath(boolean isConfigurationMode) {
    if (isConfigurationMode) {
      setFilterList(CategoryUtils.getCategoryPaths(selectionCategoryNodes));
    } else {
      setUserFilterList(CategoryUtils.getCategoryPaths(userSelectionCategoryNodes));
    }
    updatePortalCompactProcesses();
  }

  private void updatePortalCompactProcesses() {
    CompactDashboardProcessBean dashboardProcessBean = ManagedBeans.get("compactDashboardProcessBean");
    if (dashboardProcessBean != null) {
      List<DashboardProcess> processes = filterByCategory(dashboardProcessBean);
      dashboardProcessBean.setPortalCompactProcesses(processes);
      if (!Objects.isNull(dashboardProcessBean.getWidget())) {
        dashboardProcessBean.getWidget().setProcesses(null);
      }
    }
  }

  private List<DashboardProcess> filterByCategory(CompactDashboardProcessBean dashboardProcessBean) {
    return dashboardProcessBean.getAllPortalProcesses().stream()
        .filter(process -> DashboardWidgetUtils.isProcessMatchedCategory(process, filterList)).collect(Collectors.toList());
  }

  @JsonIgnore
  public void loadCategories(boolean isConfigurationMode) {
    var availableCategories = ProcessTreeUtils.buildProcessCategoryCheckboxTreeRoot(DashboardWidgetUtils.getAllPortalProcesses());
    if (isConfigurationMode) {
      this.categoryTree = availableCategories;
      if (CollectionUtils.isNotEmpty(filterList)) {
        setSelectionCategoryNodes(CategoryUtils.recoverSelectedCategories(this.categoryTree, filterList));
      }
    } else {
      this.userCategoryTree = availableCategories;
      CategoryUtils.disableSelectionWithoutSelectingExcept(userCategoryTree, filterList);
      if (CollectionUtils.isNotEmpty(userFilterList)) {
        CategoryUtils.recoverSelectedCategories(this.userCategoryTree, userFilterList);
      }
    }
  }

  public CheckboxTreeNode<CategoryNode> getCategoryTree() {
    return categoryTree;
  }

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
  
  public void initCategoryTree() {
    this.userCategoryTree = ProcessTreeUtils.buildRoot();
    this.categoryTree = ProcessTreeUtils.buildRoot();
  }
}

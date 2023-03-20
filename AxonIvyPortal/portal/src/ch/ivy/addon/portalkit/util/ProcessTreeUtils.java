package ch.ivy.addon.portalkit.util;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.Category;

public class ProcessTreeUtils {

  private ProcessTreeUtils() {}

  public static CheckboxTreeNode<CategoryNode> buildProcessCategoryCheckboxTreeRoot(List<DashboardProcess> processes) {
    CheckboxTreeNode<CategoryNode> root = buildRoot();
    CheckboxTreeNode<CategoryNode> allCategoriesNode = buildAllCategoriesNode(root);
    buildNoCategoryNode(allCategoriesNode);
    buildChildrenNodes(allCategoriesNode, processes);
    return root;
  }

  private static void buildChildrenNodes(CheckboxTreeNode<CategoryNode> allCategoriesNode,
      List<DashboardProcess> processes) {
    CollectionUtils.emptyIfNull(processes).stream()
        .filter(process -> nonNull(process.getCategory()))
        .filter(process -> StringUtils.isNotBlank(process.getCategory().getName()))
        .forEach(process -> {
          createCategoryTreeNode(allCategoriesNode, null, process.getCategory());
        });
  }

  private static void createCategoryTreeNode(CheckboxTreeNode<CategoryNode> parent, CheckboxTreeNode<CategoryNode> categoryNode, Category category) {
    if (isNull(parent) || isNull(category)) {
      return;
    }
    CheckboxTreeNode<CategoryNode> rootTreeNode = parent;
    Category parentCategory = category.getParent();
    if (nonNull(parentCategory)) {
      var parentNode = findTreeNodeByCmsUri(parent, parentCategory.getCmsUri());
      if (isNull(parentNode)) {
        parentNode = new CheckboxTreeNode<>(new CategoryNode(parentCategory));
        parentNode.setExpanded(true);
        createCategoryTreeNode(parent, (CheckboxTreeNode<CategoryNode>) parentNode, parentCategory.getParent());
      }
      rootTreeNode = (CheckboxTreeNode<CategoryNode>) parentNode;
      parent.getChildren().add(rootTreeNode);
    }
    updateExistedParentDataToRootTree(category, categoryNode, rootTreeNode);
  }

  private static void updateExistedParentDataToRootTree(Category category, TreeNode<CategoryNode> categoryNode,
      TreeNode<CategoryNode> parentNode) {
    TreeNode<CategoryNode> foundRole = findTreeNodeByCmsUri(parentNode, category.getCmsUri());
    if (isNull(foundRole)) {
      foundRole = buildNewSubTreeNode((CheckboxTreeNode<CategoryNode>) parentNode, category);
    }
    if (nonNull(categoryNode)) {
      TreeNode<CategoryNode> foundSelectedChild = findTreeNodeByCmsUri(foundRole, categoryNode.getData().getCategory());
      if (isNull(foundSelectedChild)) {
        foundRole.getChildren().add(categoryNode);
      }
    }
  }

  private static TreeNode<CategoryNode> findTreeNodeByCmsUri(TreeNode<CategoryNode> treeNode, String cmsUri) {
    return treeNode.getChildren().stream().filter(node -> node.getData().getCategory().equals(cmsUri)).findAny()
        .orElse(null);
  }

  private static CheckboxTreeNode<CategoryNode> buildNewSubTreeNode(CheckboxTreeNode<CategoryNode> parent,
      Category category) {
    var checkboxTreeNode = new CheckboxTreeNode<>(new CategoryNode(category), parent);
    checkboxTreeNode.setSelected(true);
    checkboxTreeNode.setExpanded(true);
    return checkboxTreeNode;
  }

  public static CheckboxTreeNode<CategoryNode> buildRoot() {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(StringUtils.EMPTY);
    nodeData.setCategory(StringUtils.EMPTY);
    CheckboxTreeNode<CategoryNode> root = new CheckboxTreeNode<>(StringUtils.EMPTY, nodeData, null);
    root.setExpanded(true);
    root.setSelected(true);
    return root;
  }

  private static CheckboxTreeNode<CategoryNode> buildAllCategoriesNode(CheckboxTreeNode<CategoryNode> parent) {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/allCategories"));
    nodeData.setCategory(StringUtils.EMPTY);
    CheckboxTreeNode<CategoryNode> allCategoriesNode = new CheckboxTreeNode<>(nodeData, parent);
    allCategoriesNode.setExpanded(true);
    allCategoriesNode.setSelected(true);
    return allCategoriesNode;
  }

  private static void buildNoCategoryNode(CheckboxTreeNode<CategoryNode> parent) {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(Ivy.cms().co(CategoryUtils.NO_CATEGORY_CMS));
    nodeData.setCategory(CategoryUtils.NO_CATEGORY);
    CheckboxTreeNode<CategoryNode> checkboxTreeNode = new CheckboxTreeNode<>(nodeData, parent);
    checkboxTreeNode.setSelected(true);
  }
}

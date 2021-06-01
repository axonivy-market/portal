package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivyteam.ivy.environment.Ivy;

public class ProcessTreeUtils {

  public static final String DELIMITER = "/";
  public static final String EXPRESS_WORKFLOW_CMS = "/Categories/ExpressWorkflow/name";
  public static final String EXPRESS_WORKFLOW = "Express Workflow";

  private ProcessTreeUtils() {}

  public static CheckboxTreeNode buildProcessCategoryCheckboxTreeRoot(List<DashboardProcess> processes) {
    CheckboxTreeNode root = buildRoot();
    CheckboxTreeNode allCategoriesNode = buildAllCategoriesNode(root);
    buildNoCategoryNode(allCategoriesNode);
    List<String> allProcessCategoryPaths = getAllProcessCategoryPaths(processes);
    Collections.sort(allProcessCategoryPaths);
    buildChildrenNodes(allCategoriesNode, allProcessCategoryPaths);
    return root;
  }

  public static CheckboxTreeNode buildRoot() {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(StringUtils.EMPTY);
    nodeData.setCategory(StringUtils.EMPTY);
    CheckboxTreeNode root = new CheckboxTreeNode(StringUtils.EMPTY, nodeData, null);
    root.setExpanded(true);
    root.setSelected(true);
    return root;
  }

  private static CheckboxTreeNode buildAllCategoriesNode(CheckboxTreeNode parent) {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/allCategories"));
    nodeData.setCategory(StringUtils.EMPTY);
    CheckboxTreeNode allCategoriesNode = new CheckboxTreeNode(nodeData, parent);
    allCategoriesNode.setExpanded(true);
    allCategoriesNode.setSelected(true);
    return allCategoriesNode;
  }

  private static void buildNoCategoryNode(CheckboxTreeNode parent) {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(Ivy.cms().co(CategoryUtils.NO_CATEGORY_CMS));
    nodeData.setCategory(CategoryUtils.NO_CATEGORY);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode(nodeData, parent);
    checkboxTreeNode.setSelected(true);
  }
  
  private static List<String> getAllProcessCategoryPaths(List<DashboardProcess> processes) {
    Set<String> categoryPaths = new HashSet<String>();
    for (DashboardProcess process : processes) {
      String categoryPath = process.getCategory();
      if (StringUtils.isNotBlank(categoryPath)) {
        categoryPaths.add(categoryPath);
      }
    }
    return new ArrayList<String>(categoryPaths);
  }

  private static void buildChildrenNodes(CheckboxTreeNode parent, List<String> allProcessCategoryPaths) {
    for (String path : allProcessCategoryPaths) {
      String[] pathArr = path.split(DELIMITER);
      buildCategoryTree(parent, pathArr);
    }
  }

  private static void buildCategoryTree(CheckboxTreeNode parent, String[] pathArr) {
    int currentLevel = 0;
    Optional<TreeNode>  parentTreeNode = parent.getChildren().stream()
        .filter(node -> ((CategoryNode) node.getData()).getValue().equals(pathArr[currentLevel])).findFirst();
    if (pathArr.length == 1) {
      if (parentTreeNode.isEmpty()) {
        buildSingleCategoryNode(parent, pathArr[0], pathArr[0]);
      }
    } else {
      buildSubCategoryTrees(parent, pathArr, currentLevel, parentTreeNode);
    }
  }

  private static void buildSubCategoryTrees(CheckboxTreeNode parent, String[] pathArr, int currentLevel, Optional<TreeNode> parentTreeNode) {
    if (currentLevel < pathArr.length) {
      if (parentTreeNode.isEmpty()) {
        String parentCategoryPath = currentLevel == 0 ? "" : ((CategoryNode) parent.getData()).getValue(); 
        String fullCategoryPath = StringUtils.isBlank(parentCategoryPath) ? pathArr[currentLevel] : parentCategoryPath + DELIMITER + pathArr[currentLevel];
        CheckboxTreeNode currentTreeNode = buildSingleCategoryNode(parent, pathArr[currentLevel], fullCategoryPath);
        buildSubCategoryTrees(currentTreeNode, pathArr, currentLevel + 1, parentTreeNode);
      } else {
        Optional<TreeNode> childTreeNode = parentTreeNode.get().getChildren().stream()
            .filter(node -> ((CategoryNode) node.getData()).getValue().equals(pathArr[currentLevel + 1])).findFirst();
        buildSubCategoryTrees((CheckboxTreeNode)parentTreeNode.get(), pathArr, currentLevel + 1, childTreeNode);
      }
    }
  }

  private static CheckboxTreeNode buildSingleCategoryNode(CheckboxTreeNode parent, String category,
      String rawCategoryPath) {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(category);
    nodeData.setCategory(rawCategoryPath);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode(nodeData, parent);
    checkboxTreeNode.setSelected(true);
    checkboxTreeNode.setExpanded(true);
    return checkboxTreeNode;
  }
}

package ch.ivy.addon.portalkit.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskTreeUtils {

  public static final String DELIMITER = "/";

  private TaskTreeUtils() {}

  public static CheckboxTreeNode<CategoryNode> buildTaskCategoryCheckboxTreeRoot() {
    CheckboxTreeNode<CategoryNode> root = buildRoot();
    TaskQuery taskQuery = IvyExecutor.executeAsSystem(() -> {
      return SubProcessCall.withPath(PortalConstants.BUILD_TASK_QUERY_CALLABLE).withStartSignature("buildTaskQuery()")
          .call().get("taskQuery", TaskQuery.class);
    });
    CategoryTree allTaskCategoryTree = findAllTaskCategoryTree(taskQuery);
    convertToCheckboxTreeNode((CheckboxTreeNode<CategoryNode>) root.getChildren().get(0), allTaskCategoryTree);
    sortNode(root);
    return root;
  }

  private static CategoryTree findAllTaskCategoryTree(TaskQuery taskQuery) {
    Map<String, Object> params = new HashMap<>();
    TaskCategorySearchCriteria criteria = new TaskCategorySearchCriteria();
    criteria.setCustomTaskQuery(taskQuery);
    params.put("taskCategorySearchCriteria", criteria);
    Map<String, Object> response = IvyAdapterService.startSubProcess(
        "findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria)", params,
        Arrays.asList(PortalLibrary.PORTAL.getValue()));
    return (CategoryTree) response.get("categoryTree");
  }

  private static void convertToCheckboxTreeNode(CheckboxTreeNode<CategoryNode> root, CategoryTree categoryTree) {
    for (CategoryTree category : categoryTree.getChildren()) {
      String name = category.getCategory().getName();
      String categoryRawPath = category.getRawPath();
      CheckboxTreeNode<CategoryNode> childNode = buildTaskCategoryCheckBoxTreeNode(root, name, categoryRawPath);
      root.getChildren().add(childNode);
      if (CollectionUtils.isNotEmpty(category.getChildren())) {
        convertToCheckboxTreeNode(childNode, category);
      }
    }
  }

  public static void sortNode(TreeNode<CategoryNode> node) {
    Comparator<TreeNode<CategoryNode>> comparator = (firstNode, secondNode) -> {
      CategoryNode firstNodeData = firstNode.getData();
      CategoryNode secondNodeData = secondNode.getData();
      return firstNodeData.getValue().compareToIgnoreCase(secondNodeData.getValue());
    };
    Collections.sort(node.getChildren(), comparator);
  }

  private static CheckboxTreeNode<CategoryNode> buildTaskCategoryCheckBoxTreeNode(CheckboxTreeNode<CategoryNode> parentNode, String newNodeName, String category) {
    List<TreeNode<CategoryNode>> childNodes = parentNode.getChildren();
    for (TreeNode<CategoryNode> childNode : childNodes) {
      CategoryNode childNodeData = childNode.getData();
      if (category.equalsIgnoreCase(childNodeData.getValue())) {
        return (CheckboxTreeNode<CategoryNode>) childNode;
      }
    }

    CategoryNode nodeData = buildTaskNodeFrom(newNodeName, category);
    CheckboxTreeNode<CategoryNode> checkboxTreeNode = new CheckboxTreeNode<CategoryNode>(nodeData, parentNode);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(true);
    return checkboxTreeNode;
  }

  private static CategoryNode buildTaskNodeFrom(String name, String category) {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(name);
    nodeData.setCategory(category);
    return nodeData;
  }

  private static CheckboxTreeNode<CategoryNode> buildRoot() {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(StringUtils.EMPTY);
    nodeData.setCategory(StringUtils.EMPTY);
    CheckboxTreeNode<CategoryNode> checkboxTreeNode = new CheckboxTreeNode<>(StringUtils.EMPTY, nodeData, null);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(true);
    buildAllCategoriesNode(checkboxTreeNode);
    return checkboxTreeNode;
  }

  private static void buildAllCategoriesNode(CheckboxTreeNode<CategoryNode> root) {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/allCategories"));
    nodeData.setCategory(StringUtils.EMPTY);
    CheckboxTreeNode<CategoryNode> checkboxTreeNode = new CheckboxTreeNode<>(nodeData, root);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(true);
    buildNoCategoryNode(checkboxTreeNode);
  }

  private static void buildNoCategoryNode(CheckboxTreeNode<CategoryNode> parent) {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(Ivy.cms().co(CategoryUtils.NO_CATEGORY_CMS));
    nodeData.setCategory(CategoryUtils.NO_CATEGORY);
    CheckboxTreeNode<CategoryNode> checkboxTreeNode = new CheckboxTreeNode<CategoryNode>(nodeData, parent);
    checkboxTreeNode.setSelected(true);
  }
}

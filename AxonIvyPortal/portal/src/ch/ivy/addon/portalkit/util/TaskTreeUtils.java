package ch.ivy.addon.portalkit.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import com.axonivy.portal.components.service.IvyAdapterService;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskTreeUtils extends TreeUtils{

  public static final String DELIMITER = "/";

  private TaskTreeUtils() {}

  public static CheckboxTreeNode<CategoryNode> buildTaskCategoryCheckboxTreeRoot() {
    CheckboxTreeNode<CategoryNode> root = buildRoot();
    TaskQuery taskQuery = Sudo.get(() -> {
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
    Map<String, Object> response = IvyAdapterService.startSubProcessInProjectAndAllRequired(
        "findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria)", params);
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
}

package ch.ivy.addon.portalkit.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.util.TreeUtils;

import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskTreeUtils {

  public static final String DELIMITER = "/";

  private TaskTreeUtils() {}
  
  public static CheckboxTreeNode buildTaskCategoryCheckboxTreeRoot() {

    CheckboxTreeNode root = buildRoot();
    RegisteredApplicationService service = new RegisteredApplicationService();
    List<String> involvedApplications =
        service.findActiveIvyAppsBasedOnConfiguration(Ivy.session().getSessionUserName());
    TaskQuery taskQuery = IvyExecutor.executeAsSystem(() -> {
      return SubProcessCall.withPath(PortalConstants.BUILD_TASK_QUERY_CALLABLE).withStartSignature("buildTaskQuery()")
          .call().get("taskQuery", TaskQuery.class);
    });
    CategoryTree allTaskCategoryTree = findAllTaskCategoryTree(involvedApplications, taskQuery);
    convertToCheckboxTreeNode(root, allTaskCategoryTree);
    sortNode(root);
    return root;
  }
  
  private static CategoryTree findAllTaskCategoryTree(List<String> involvedApplications, TaskQuery taskQuery) {
    Map<String, Object> params = new HashMap<>();
    TaskCategorySearchCriteria criteria = new TaskCategorySearchCriteria();
    criteria.setCustomTaskQuery(taskQuery);
    criteria.setApps(involvedApplications);
    params.put("taskCategorySearchCriteria", criteria);
    Map<String, Object> response = IvyAdapterService.startSubProcess(
        "findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria)", params,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (CategoryTree) response.get("categoryTree");
  }
  
  private static void convertToCheckboxTreeNode(CheckboxTreeNode root, CategoryTree categoryTree) {
    String nodeType = "default";
    for (CategoryTree category : categoryTree.getChildren()) {
      String name = category.getCategory().getName();
      String categoryRawPath = category.getRawPath();
      CheckboxTreeNode childNode = buildTaskCategoryCheckBoxTreeNode(root, name, nodeType, categoryRawPath);
      root.getChildren().add(childNode);
      if (CollectionUtils.isNotEmpty(category.getChildren())) {
        convertToCheckboxTreeNode(childNode, category);
      }
    }
  }
  
  public static void sortNode(TreeNode node) {
    Comparator<TreeNode> comparator = (firstNode, secondNode) -> {
      TaskNode firstNodeData = (TaskNode) firstNode.getData();
      TaskNode secondNodeData = (TaskNode) secondNode.getData();
      return firstNodeData.getValue().compareToIgnoreCase(secondNodeData.getValue());
    };
    TreeUtils.sortNode(node, comparator);
  }
  
  private static CheckboxTreeNode buildTaskCategoryCheckBoxTreeNode(CheckboxTreeNode parentNode, String newNodeName, String nodeType, String category) {
    List<TreeNode> childNodes = parentNode.getChildren();
    for (TreeNode childNode : childNodes) {
      TaskNode childNodeData = (TaskNode) childNode.getData();
      if (category.equalsIgnoreCase(childNodeData.getValue())) {
        return (CheckboxTreeNode) childNode;
      }
    }

    TaskNode nodeData = buildTaskNodeFrom(newNodeName, category);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode(nodeType, nodeData, parentNode);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(false);
    return checkboxTreeNode;
  }

  private static TaskNode buildTaskNodeFrom(String name, String category) {
    TaskNode nodeData = new TaskNode();
    nodeData.setValue(name);
    nodeData.setCategory(category);
    nodeData.setRootNodeAllTask(false);
    nodeData.setFirstCategoryNode(false);
    return nodeData;
  }
  
  private static CheckboxTreeNode buildRoot() {
    TaskNode nodeData = new TaskNode();
    nodeData.setValue(StringUtils.EMPTY);
    nodeData.setCategory(StringUtils.EMPTY);
    nodeData.setRootNodeAllTask(true);
    nodeData.setFirstCategoryNode(true);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode(StringUtils.EMPTY, nodeData, null);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(false);
    return checkboxTreeNode;
  }

}

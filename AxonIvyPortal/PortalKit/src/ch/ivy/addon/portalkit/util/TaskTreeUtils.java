package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.util.TreeUtils;

import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.ws.addon.CategoryData;
import ch.ivyteam.ivy.process.call.SubProcessCall;


/**
 * Provide the utilities related to Task Tree
 * 
 * @author BOLT
 */
public class TaskTreeUtils {

  public static final String DELIMITER = "/";
  
  private static CheckboxTreeNode root;

  private TaskTreeUtils() {}
  
  /**
   * Convert to Tree from the structure on field CustomVarCharField5 of task;
   * 
   * @param categories list category of user
   * @param firstCategory 
   * @param isRootAllTask 
   * @param menuState 
   * @return TreeNode : The Tree after convert
   */
  public static TreeNode convertTaskListToTree(List<CategoryData> categories, String firstCategory,
      boolean isRootAllTask, String menuState) {
    TreeNode taskRootNode = new DefaultTreeNode();
    TreeNode navigatorNode = taskRootNode;
    for (CategoryData category : categories) {
      String categoryPath = category.getPath();
      String[] nodeNames = categoryPath.split(DELIMITER);

      String categoryRawPath = category.getRawPath();
      String[] nodePaths = category.getRawPath().split(DELIMITER);

      for (int i = 0; i < nodeNames.length; i++) {
        String nodeName = nodeNames[i];
        String categoryName = categoryPath.substring(0, categoryPath.indexOf(nodeName) + nodeName.length());

        String nodePath = nodePaths[i];
        String rawPath = categoryRawPath.substring(0, categoryRawPath.indexOf(nodePath) + nodePath.length());

        String nodeType = firstCategory + DELIMITER + categoryName.replaceAll(" ", "_");
        navigatorNode =
            buildTaskCategoryNode(navigatorNode, nodeName, nodeType, categoryName, rawPath, isRootAllTask, menuState);
      }
      navigatorNode = taskRootNode;
    }
    sortNode(taskRootNode);
    return taskRootNode;
  }

  private static void sortNode(TreeNode node) {
    Comparator<TreeNode> comparator = (firstNode, secondNode) -> {
      TaskNode firstNodeData = (TaskNode) firstNode.getData();
      TaskNode secondNodeData = (TaskNode) secondNode.getData();
      return firstNodeData.getValue().compareToIgnoreCase(secondNodeData.getValue());
    };
    TreeUtils.sortNode(node, comparator);
  }

  /**
   * Build task node for current task. If node name exist, return node. Else add new node
   * 
   * @param navigatorNode : Current tree node
   * @param newNodeName : node name
   * @return TreeNode : Tree node after add node
   */
  private static TreeNode buildTaskCategoryNode(TreeNode navigatorNode, String newNodeName, String nodeType,
      String category, String rawPath, boolean isRootAllTask, String menuState) {
    List<TreeNode> childNodes = navigatorNode.getChildren();
    for (TreeNode childNode : childNodes) {
      TaskNode childNodeData = (TaskNode) childNode.getData();
      String childNodeName = childNodeData.getValue();
      if (newNodeName.equalsIgnoreCase(childNodeName)) {
        return childNode;
      }
    }

    TaskNode newNodeData = new TaskNode();
    newNodeData.setValue(newNodeName);
    newNodeData.setMenuKind(MenuKind.TASK);
    newNodeData.setCategory(category);
    newNodeData.setCategoryRawPath(rawPath);
    newNodeData.setRootNodeAllTask(isRootAllTask);
    newNodeData.setFirstCategoryNode(false);
    TreeNode newNode = new DefaultTreeNode(nodeType, newNodeData, navigatorNode);
    if (menuState.contains(nodeType)
        && !getLastCategoryFromCategoryPath(menuState).contains(getLastCategoryFromCategoryPath(nodeType))) {
      newNode.setExpanded(true);
    } else {
      newNode.setExpanded(false);
    }
    return newNode;
  }
  
  public static CheckboxTreeNode buildTaskCategoryCheckboxTreeRoot() {
    if (root != null){
      return root;
    }
    List<String> involvedApplications = null;
    String appName = SecurityServiceUtils.getApplicationNameFromSession();
    if (StringUtils.isNotEmpty(appName)) {
      involvedApplications = new ArrayList<>();
      involvedApplications.add(appName);
    }
    String jsonQuery = SubProcessCall.withPath("Functional Processes/BuildTaskJsonQuery").withStartSignature("buildTaskJsonQuery()").call().get("jsonQuery", String.class);
    List<CategoryData> allTaskCategories = findAllTaskCategories(involvedApplications, jsonQuery);
    root = buildTaskCategoryCheckboxTreeNode(allTaskCategories);
    return root;
  }
  
  private static List<CategoryData> findAllTaskCategories(List<String> involvedApplications, String jsonQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put("jsonQuery", jsonQuery);
    params.put("apps", involvedApplications);
    params.put("serverId", ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession());
    Map<String, Object> response =
        IvyAdapterService.startSubProcess("findCategories(String, String, List<String>, Long)", params, Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    @SuppressWarnings("unchecked")
    List<CategoryData> allTaskCategories = (List<CategoryData>) response.get("categories");
    return allTaskCategories;
  }
  
  private static CheckboxTreeNode buildTaskCategoryCheckboxTreeNode(List<CategoryData> categories) {
    CheckboxTreeNode taskRootNode = new CheckboxTreeNode(buildTaskNodeFrom(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
    CheckboxTreeNode navigatorNode = taskRootNode;
    String nodeType = "default";
    for (CategoryData category : categories) {
      String categoryPath = category.getPath();
      String[] categoryNames = categoryPath.split(DELIMITER);

      String categoryRawPath = category.getRawPath();
      String[] nodeRawPaths = category.getRawPath().split(DELIMITER);

      for (int i = 0; i < categoryNames.length; i++) {
        String subCategoryName = categoryNames[i];
        String subCategoryPath = categoryPath.substring(0, categoryPath.indexOf(subCategoryName) + subCategoryName.length());

        String subCategoryRawName = nodeRawPaths[i];
        String subCategoryRawPath = categoryRawPath.substring(0, categoryRawPath.indexOf(subCategoryRawName) + subCategoryRawName.length());

        navigatorNode = buildTaskCategoryTreeNode(navigatorNode, nodeType, subCategoryName, subCategoryPath, subCategoryRawPath);
      }
      navigatorNode = taskRootNode;
    }
    sortNode(taskRootNode);
    return taskRootNode;
  }

  private static CheckboxTreeNode buildTaskCategoryTreeNode(CheckboxTreeNode navigatorNode, String nodeType, String subCategoryName, String subCategoryPath, String subCategoryRawPath) {
    List<TreeNode> childNodes = navigatorNode.getChildren();
    for (TreeNode childNode : childNodes) {
      TaskNode childNodeData = (TaskNode) childNode.getData();
      if (subCategoryPath.equalsIgnoreCase(childNodeData.getValue())) {
        return (CheckboxTreeNode) childNode;
      }
    }

    TaskNode nodeData = buildTaskNodeFrom(subCategoryName, subCategoryPath, subCategoryRawPath);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode(nodeType, nodeData, navigatorNode);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(false);
    return checkboxTreeNode;
  }

  private static TaskNode buildTaskNodeFrom(String subCategoryName, String subCategoryPath, String subCategoryRawPath) {
    TaskNode nodeData = new TaskNode();
    nodeData.setValue(subCategoryPath);
    nodeData.setMenuKind(MenuKind.TASK);
    nodeData.setCategory(subCategoryName);
    nodeData.setCategoryRawPath(subCategoryRawPath);
    nodeData.setRootNodeAllTask(false);
    nodeData.setFirstCategoryNode(false);
    return nodeData;
  }

  public static String getLastCategoryFromCategoryPath(String categoryPath) {
    if (!StringUtils.isBlank(categoryPath)) {
      String[] categories = categoryPath.split("/");
      return categories[categories.length - 1];
    }
    return "";
  }
}

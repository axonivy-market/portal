package ch.ivy.addon.portalkit.util;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.util.TreeUtils;

import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.ws.addon.CategoryData;


/**
 * Provide the utilities related to Task Tree
 * 
 * @author BOLT
 */
public class TaskTreeUtils {

  private static final String DELIMITER = "/";

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
    Comparator<TreeNode> comparator = new Comparator<TreeNode>() {
      @Override
      public int compare(TreeNode firstNode, TreeNode secondNode) {
        TaskNode firstNodeData = (TaskNode) firstNode.getData();
        TaskNode secondNodeData = (TaskNode) secondNode.getData();
        return firstNodeData.getValue().compareToIgnoreCase(secondNodeData.getValue());
      }
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
    newNode.setExpanded(false);
    if (menuState.contains(nodeType) && (menuState.indexOf(nodeType) + nodeType.length() <= menuState.length())
        && !getLastCategoryFromCategoryPath(menuState).contains(getLastCategoryFromCategoryPath(nodeType))) {
      if((menuState.indexOf(nodeType) + nodeType.length() == menuState.length()) || (menuState.charAt(menuState.indexOf(nodeType) + nodeType.length()) == '/')) {
        newNode.setExpanded(true);
      }
    }
    return newNode;
  }

  public static String getLastCategoryFromCategoryPath(String categoryPath) {
    if (!StringUtils.isBlank(categoryPath)) {
      String[] categories = categoryPath.split("/");
      return categories[categories.length - 1];
    }
    return "";
  }
}

package ch.ivy.addon.portalkit.util;

import java.util.Comparator;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.util.TreeUtils;

import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.enums.MenuKind;


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
   * @return TreeNode : The Tree after convert
   */
  public static TreeNode convertTaskListToTree(List<String> categories, String firstCategory, boolean isRootAllTask) {
    TreeNode taskRootNode = new DefaultTreeNode();
    TreeNode navigatorNode = taskRootNode;
    for (String structure : categories) {
      String[] nodeNames = structure.split(DELIMITER);
      for (String nodeName : nodeNames) {
        String category = structure.substring(0, structure.indexOf(nodeName) + nodeName.length());
        String nodeType = firstCategory + DELIMITER + category.replaceAll(" ", "_");
        navigatorNode = buildTaskCategoryNode(navigatorNode, nodeName, nodeType, category, isRootAllTask);
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
      String category, boolean isRootAllTask) {
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
    newNodeData.setRootNodeAllTask(isRootAllTask);
    newNodeData.setFirstCategoryNode(false);
    TreeNode newNode = new DefaultTreeNode(nodeType, newNodeData, navigatorNode);
    newNode.setExpanded(true);
    return newNode;
  }
}

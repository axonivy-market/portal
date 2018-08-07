package ch.ivy.addon.portalkit.util;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.Comparator;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.util.TreeUtils;

import ch.ivy.addon.portalkit.bo.RemoteTask;
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
   * @param tasks list task of user
   * @return TreeNode : The Tree after convert
   */
  public static TreeNode convertTaskListToTree(List<RemoteTask> remoteTasks, String firstCategory) {
    TreeNode taskRootNode = new DefaultTreeNode();
    TreeNode navigatorNode = taskRootNode;
    for (RemoteTask remoteTask : remoteTasks) {
      String structure = remoteTask.getCustomVarCharField5();
      if (isNotEmpty(structure)) {
        String[] nodeNames = structure.split(DELIMITER);
        for (String nodeName : nodeNames) {
          String categoryStructure = firstCategory + DELIMITER + structure.substring(0, structure.indexOf(nodeName) + nodeName.length()).replaceAll(" ", "_");
          navigatorNode = buildTaskCategoryNode(navigatorNode, nodeName, remoteTask, categoryStructure);
        }
        navigatorNode = taskRootNode;
      }
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
   * @param task : current task
   * @return TreeNode : Tree node after add node
   */
  private static TreeNode buildTaskCategoryNode(TreeNode navigatorNode, String newNodeName, RemoteTask task, String categoryStructure) {
    List<TreeNode> childNodes = navigatorNode.getChildren();
    for (TreeNode childNode : childNodes) {
      TaskNode childNodeData = (TaskNode) childNode.getData();
      String childNodeName = childNodeData.getValue();
      if (newNodeName.equalsIgnoreCase(childNodeName)) {
        childNodeData.getTasks().add(task);
        return childNode;
      }
    }

    TaskNode newNodeData = new TaskNode(task);
    newNodeData.setValue(newNodeName);
    newNodeData.setMenuKind(MenuKind.TASK);
    TreeNode newNode = new DefaultTreeNode(categoryStructure, newNodeData, navigatorNode);
    newNode.setExpanded(true);
    return newNode;
  }
}

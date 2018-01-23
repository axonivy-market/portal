package ch.ivy.addon.portalkit.util;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.util.TreeUtils;

import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.ws.addon.CategoryData;

/**
 * Utilities for case tree.
 */
public class CaseTreeUtils {

  private final static String DELIMITER = "/";

  /**
   * Convert field CustomVarCharField5 of cases to TreeNode
   * 
   * @param cases : list of case
   * @return {@link TreeNode}
   */
  public static TreeNode convertToTreeNode(List<CategoryData> categories, String firstCategory, boolean isRootAllCase,
      String menuState) {
    TreeNode root = new DefaultTreeNode();
    TreeNode navigatorNode = root;
    for (CategoryData category : categories) {
      String categoryPath = category.getPath();
      String[] nodeNames = categoryPath.split(DELIMITER);

      String categoryRawPath = category.getRawPath();
      String[] nodePaths = category.getRawPath().split(DELIMITER);

      for (int i=0; i<nodeNames.length; i++) {
        String nodeName = nodeNames[i];
        String categoryName = categoryPath.substring(0, categoryPath.indexOf(nodeName) + nodeName.length());

        String nodePath = nodePaths[i];
        String rawPath = categoryRawPath.substring(0, categoryRawPath.indexOf(nodePath) + nodePath.length());

        String nodeType = firstCategory + DELIMITER + categoryName.replaceAll(" ", "_");
        navigatorNode = buildCaseCategoryNode(navigatorNode, nodeName, nodeType, categoryName, rawPath, isRootAllCase, menuState);
      }
      navigatorNode = root;
    }
    sortNode(root);
    return root;
  }
  
  private static void sortNode(TreeNode node) {
    Comparator<TreeNode> comparator = new Comparator<TreeNode>() {
      @Override
      public int compare(TreeNode firstNode, TreeNode secondNode) {
        CaseNode firstNodeData = (CaseNode) firstNode.getData();
        CaseNode secondNodeData = (CaseNode) secondNode.getData();
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
  private static TreeNode buildCaseCategoryNode(TreeNode navigatorNode, String newNodeName, String nodeType,
      String category, String rawPath, boolean isRootAllCase, String menuState) {
    List<TreeNode> childNodes = navigatorNode.getChildren();
    for (TreeNode childNode : childNodes) {
      CaseNode childNodeData = (CaseNode) childNode.getData();
      String childNodeName = childNodeData.getValue();
      if (newNodeName.equalsIgnoreCase(childNodeName)) {
        return childNode;
      }
    }

    CaseNode newNodeData = new CaseNode();
    newNodeData.setValue(newNodeName);
    newNodeData.setMenuKind(MenuKind.CASE);
    newNodeData.setCategory(category);
    newNodeData.setCategoryRawPath(rawPath);
    newNodeData.setRootNodeAllCase(isRootAllCase);
    newNodeData.setFirstCategoryNode(false);
    TreeNode newNode = new DefaultTreeNode(nodeType, newNodeData, navigatorNode);
    newNode.setExpanded(true);
    if (menuState.contains(nodeType)
        && !getLastCategoryFromCategoryPath(menuState).contains(getLastCategoryFromCategoryPath(nodeType))) {
      newNode.setExpanded(true);
    } else {
      newNode.setExpanded(false);
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

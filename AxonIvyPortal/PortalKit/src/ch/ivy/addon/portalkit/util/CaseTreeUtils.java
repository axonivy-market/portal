package ch.ivy.addon.portalkit.util;

import static java.util.stream.Collectors.joining;

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

import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.ws.addon.CategoryData;
import ch.ivyteam.ivy.process.call.SubProcessCall;

/**
 * Utilities for case tree.
 */
public class CaseTreeUtils {

  public static final String DELIMITER = "/";
  
  private static CheckboxTreeNode root;

  private CaseTreeUtils() {}

  /**
   * Convert categories of cases to TreeNode
   * 
   * @param categories
   * @param firstCategory
   * @param isRootAllCase
   * @param menuState
   * 
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

      for (int i = 0; i < nodeNames.length; i++) {
        String nodeName = nodeNames[i];
        String categoryName = categoryPath.substring(0, categoryPath.indexOf(nodeName) + nodeName.length());

        String nodePath = nodePaths[i];
        String rawPath = categoryRawPath.substring(0, categoryRawPath.indexOf(nodePath) + nodePath.length());

        String nodeType = firstCategory + DELIMITER + categoryName.replaceAll(" ", "_");
        navigatorNode =
            buildCaseCategoryNode(navigatorNode, nodeName, nodeType, categoryName, rawPath, isRootAllCase, menuState);
      }
      navigatorNode = root;
    }
    sortNode(root);
    return root;
  }

  private static void sortNode(TreeNode node) {
    Comparator<TreeNode> comparator = (firstNode, secondNode) -> {
      CaseNode firstNodeData = (CaseNode) firstNode.getData();
      CaseNode secondNodeData = (CaseNode) secondNode.getData();
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
  
  public static CheckboxTreeNode buildCaseCategoryCheckboxTreeRoot() {
    if (root != null){
      return root;
    }
    List<String> involvedApplications = null;
    String appName = SecurityServiceUtils.getApplicationNameFromSession();
    if (StringUtils.isNotEmpty(appName)) {
      involvedApplications = new ArrayList<>();
      involvedApplications.add(appName);
    }
    String jsonQuery = SubProcessCall.withPath("Functional Processes/BuildCaseJsonQuery").withStartSignature("buildCaseJsonQuery()").call().get("jsonQuery", String.class);
    List<CategoryData> allCaseCategories = findAllCaseCategories(involvedApplications, jsonQuery);
    root = buildCaseCategoryCheckboxTreeNode(allCaseCategories);
    return root;
  }

  private static List<CategoryData> findAllCaseCategories(List<String> involvedApplications, String jsonQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put("jsonQuery", jsonQuery);
    params.put("apps", involvedApplications != null ? involvedApplications.stream().collect(joining("=~=")) : null);
    params.put("serverId", ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession());
    Map<String, Object> response =
        IvyAdapterService.startSubProcess("findCaseCategoriesByCriteria(String, String, Long, String)", params, Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    @SuppressWarnings("unchecked")
    List<CategoryData> allCaseCategories = (List<CategoryData>) response.get("caseCategories");
    return allCaseCategories;
  }
  
  private static CheckboxTreeNode buildCaseCategoryCheckboxTreeNode(List<CategoryData> categories) {
    CheckboxTreeNode caseRootNode = new CheckboxTreeNode(buildCaseNodeFrom(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
    CheckboxTreeNode navigatorNode = caseRootNode;
    String nodeType = "default";
    for (CategoryData category : categories) {
      String categoryPath = category.getPath();
      String[] nodeNames = categoryPath.split(DELIMITER);

      String categoryRawPath = category.getRawPath();
      String[] nodeRawPaths = category.getRawPath().split(DELIMITER);

      for (int i = 0; i < nodeNames.length; i++) {
        String subCategoryName = nodeNames[i];
        String subCategoryPath = categoryPath.substring(0, categoryPath.indexOf(subCategoryName) + subCategoryName.length());

        String subCategoryRawName = nodeRawPaths[i];
        String subCategoryRawPath = categoryRawPath.substring(0, categoryRawPath.indexOf(subCategoryRawName) + subCategoryRawName.length());

        navigatorNode = buildCaseCategoryTreeNode(navigatorNode, nodeType, subCategoryName, subCategoryPath, subCategoryRawPath);
      }
      navigatorNode = caseRootNode;
    }
    sortNode(caseRootNode);
    return caseRootNode;
  }
  
  private static CheckboxTreeNode buildCaseCategoryTreeNode(CheckboxTreeNode navigatorNode, String nodeType, String subCategoryName, String subCategoryPath, String subCategoryRawPath) {
    List<TreeNode> childNodes = navigatorNode.getChildren();
    for (TreeNode childNode : childNodes) {
      CaseNode childNodeData = (CaseNode) childNode.getData();
      if (subCategoryPath.equalsIgnoreCase(childNodeData.getValue())) {
        return (CheckboxTreeNode) childNode;
      }
    }

    CaseNode nodeData = buildCaseNodeFrom(subCategoryName, subCategoryPath, subCategoryRawPath);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode(nodeType, nodeData, navigatorNode);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(false);
    return checkboxTreeNode;
  }

  private static CaseNode buildCaseNodeFrom(String subCategoryName, String subCategoryPath, String subCategoryRawPath) {
    CaseNode nodeData = new CaseNode();
    nodeData.setValue(subCategoryPath);
    nodeData.setMenuKind(MenuKind.CASE);
    nodeData.setCategory(subCategoryName);
    nodeData.setCategoryRawPath(subCategoryRawPath);
    nodeData.setRootNodeAllCase(false);
    nodeData.setFirstCategoryNode(false);
    return nodeData;
  }

  public static String getLastCategoryFromCategoryPath(String categoryPath) {
    if (!StringUtils.isBlank(categoryPath)) {
      String[] categories = categoryPath.split(DELIMITER);
      return categories[categories.length - 1];
    }
    return StringUtils.EMPTY;
  }
}

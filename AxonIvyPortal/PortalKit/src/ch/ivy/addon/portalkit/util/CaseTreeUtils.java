package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.util.TreeUtils;

import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

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
   * @param root
   * @param categoryTree
   * @param isRootAllCase
   * @param menuState
   */
  public static void convertToTreeNode(TreeNode root, CategoryTree categoryTree, boolean isRootAllCase, String menuState) {
    for (CategoryTree category : categoryTree.getChildren()) {
      String name = category.getCategory().getName();
      String categoryRawPath = category.getRawPath();
      String nodeType = root.getType() + DELIMITER + category.getCategory().getName(Locale.ENGLISH).replaceAll(" ", "_");
      TreeNode childNode = buildCaseCategoryNode(root, name, nodeType, categoryRawPath, isRootAllCase, menuState);
      root.getChildren().add(childNode);
      if (CollectionUtils.isNotEmpty(category.getChildren())) {
        convertToTreeNode(childNode, category, isRootAllCase, menuState);
      }
    }
  }

  private static TreeNode buildCaseCategoryNode(TreeNode root, String newNodeName, String nodeType, String rawPath, boolean isRootAllCase, String menuState) {
    CaseNode newNodeData = new CaseNode();
    newNodeData.setValue(newNodeName);
    newNodeData.setMenuKind(MenuKind.CASE);
    newNodeData.setCategoryRawPath(rawPath);
    newNodeData.setRootNodeAllCase(isRootAllCase);
    
    TreeNode newNode = new DefaultTreeNode(nodeType, newNodeData, root);
    if (menuState.contains(nodeType) && isSelectedCategory(menuState, nodeType)) {
      newNode.getParent().setExpanded(true);
    }
    return newNode;
  }

  private static boolean isSelectedCategory(String menuState, String nodeType) {
    return (menuState.indexOf(nodeType) + nodeType.length() == menuState.length())
        ||(menuState.charAt(menuState.indexOf(nodeType) + nodeType.length()) == '/');
  }

  public static CheckboxTreeNode buildCaseCategoryCheckboxTreeRoot() {
    if (root != null) {
      return root;
    }
    List<String> involvedApplications = null;
    String appName = SecurityServiceUtils.getApplicationNameFromSession();
    if (StringUtils.isNotEmpty(appName)) {
      involvedApplications = new ArrayList<>();
      involvedApplications.add(appName);
    }
    CaseQuery caseQuery = SubProcessCall.withPath("Functional Processes/BuildCaseQuery")
        .withStartSignature("buildCaseQuery()").call().get("caseQuery", CaseQuery.class);
    CategoryTree allCaseCategories = findAllCaseCategoryTree(involvedApplications, caseQuery);
    convertToCheckboxTreeNode(root, allCaseCategories);
    sortNode(root);
    return root;
  }

  private static CategoryTree findAllCaseCategoryTree(List<String> involvedApplications, CaseQuery caseQuery) {
    Map<String, Object> params = new HashMap<>();
    CaseCategorySearchCriteria criteria = new CaseCategorySearchCriteria();
    criteria.setCustomCaseQuery(caseQuery);
    criteria.setApps(involvedApplications);
    params.put("caseCategorySearchCriteria", criteria);
    Map<String, Object> response =
        IvyAdapterService.startSubProcess("findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria)", 
            params, Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    CategoryTree allCaseCategoryTree = (CategoryTree) response.get("categoryTree");
    return allCaseCategoryTree;
  }

  private static void convertToCheckboxTreeNode(CheckboxTreeNode root, CategoryTree categoryTree) {
    for (CategoryTree category : categoryTree.getChildren()) {
      String name = category.getCategory().getName();
      String categoryRawPath = category.getRawPath();
      String nodeType = root.getType() + DELIMITER + category.getCategory().getName(Locale.ENGLISH).replaceAll(" ", "_");
      CheckboxTreeNode childNode = buildCaseCategoryCheckBoxTreeNode(root, name, nodeType, categoryRawPath);
      root.getChildren().add(childNode);
      if (CollectionUtils.isNotEmpty(category.getChildren())) {
        convertToCheckboxTreeNode(childNode, category);
      }
    }
  }
  
  private static void sortNode(TreeNode node) {
    Comparator<TreeNode> comparator = (firstNode, secondNode) -> {
      CaseNode firstNodeData = (CaseNode) firstNode.getData();
      CaseNode secondNodeData = (CaseNode) secondNode.getData();
      return firstNodeData.getValue().compareToIgnoreCase(secondNodeData.getValue());
    };
    TreeUtils.sortNode(node, comparator);
  }
  
  private static CheckboxTreeNode buildCaseCategoryCheckBoxTreeNode(CheckboxTreeNode root, String newNodeName, String nodeType, String rawPath) {
    List<TreeNode> childNodes = root.getChildren();
    for (TreeNode childNode : childNodes) {
      CaseNode childNodeData = (CaseNode) childNode.getData();
      if (rawPath.equalsIgnoreCase(childNodeData.getValue())) {
        return (CheckboxTreeNode) childNode;
      }
    }

    CaseNode nodeData = buildCaseNodeFrom(newNodeName, rawPath);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode(nodeType, nodeData, root);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(false);
    return checkboxTreeNode;
  }
  
  private static CaseNode buildCaseNodeFrom(String name, String rawPath) {
    CaseNode nodeData = new CaseNode();
    nodeData.setValue(name);
    nodeData.setMenuKind(MenuKind.CASE);
    nodeData.setCategoryRawPath(rawPath);
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

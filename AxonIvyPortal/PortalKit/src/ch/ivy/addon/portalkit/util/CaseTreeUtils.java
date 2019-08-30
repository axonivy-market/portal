package ch.ivy.addon.portalkit.util;

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
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

/**
 * Utilities for case tree.
 */
public class CaseTreeUtils {

  public static final String DELIMITER = "/";

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

  private static TreeNode buildCaseCategoryNode(TreeNode parentNode, String newNodeName, String nodeType, String category, boolean isRootAllCase, String menuState) {
    CaseNode newNodeData = new CaseNode();
    newNodeData.setValue(newNodeName);
    newNodeData.setMenuKind(MenuKind.CASE);
    newNodeData.setCategory(category);
    newNodeData.setRootNodeAllCase(isRootAllCase);
    
    TreeNode newNode = new DefaultTreeNode(nodeType, newNodeData, parentNode);
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
    
    CheckboxTreeNode root = buildRoot();
    RegisteredApplicationService service = new RegisteredApplicationService();
    List<String> involvedApplications = service.findActiveIvyAppsBasedOnConfiguration(Ivy.session().getSessionUserName());
    CaseQuery caseQuery = SubProcessCall.withPath(PortalConstants.BUILD_CASE_QUERY_CALLABLE)
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
    return (CategoryTree) response.get("categoryTree");
  }

  private static void convertToCheckboxTreeNode(CheckboxTreeNode root, CategoryTree categoryTree) {
    String nodeType = "default";
    for (CategoryTree category : categoryTree.getChildren()) {
      String name = category.getCategory().getName();
      String categoryRawPath = category.getRawPath();
      CheckboxTreeNode childNode = buildCaseCategoryCheckBoxTreeNode(root, name, nodeType, categoryRawPath);
      root.getChildren().add(childNode);
      if (CollectionUtils.isNotEmpty(category.getChildren())) {
        convertToCheckboxTreeNode(childNode, category);
      }
    }
  }
  
  public static void sortNode(TreeNode node) {
    Comparator<TreeNode> comparator = (firstNode, secondNode) -> {
      CaseNode firstNodeData = (CaseNode) firstNode.getData();
      CaseNode secondNodeData = (CaseNode) secondNode.getData();
      return firstNodeData.getValue().compareToIgnoreCase(secondNodeData.getValue());
    };
    TreeUtils.sortNode(node, comparator);
  }
  
  private static CheckboxTreeNode buildCaseCategoryCheckBoxTreeNode(CheckboxTreeNode parentNode, String newNodeName, String nodeType, String category) {
    List<TreeNode> childNodes = parentNode.getChildren();
    for (TreeNode childNode : childNodes) {
      CaseNode childNodeData = (CaseNode) childNode.getData();
      if (category.equalsIgnoreCase(childNodeData.getValue())) {
        return (CheckboxTreeNode) childNode;
      }
    }

    CaseNode nodeData = buildCaseNodeFrom(newNodeName, category);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode(nodeType, nodeData, parentNode);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(false);
    return checkboxTreeNode;
  }
  
  private static CaseNode buildCaseNodeFrom(String name, String category) {
    CaseNode nodeData = new CaseNode();
    nodeData.setValue(name);
    nodeData.setMenuKind(MenuKind.CASE);
    nodeData.setCategory(category);
    nodeData.setRootNodeAllCase(false);
    nodeData.setFirstCategoryNode(false);
    return nodeData;
  }
  
  private static CheckboxTreeNode buildRoot() {
    CaseNode nodeData = new CaseNode();
    nodeData.setValue(StringUtils.EMPTY);
    nodeData.setMenuKind(MenuKind.CASE);
    nodeData.setCategory(StringUtils.EMPTY);
    nodeData.setRootNodeAllCase(true);
    nodeData.setFirstCategoryNode(true);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode(StringUtils.EMPTY, nodeData, null);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(false);
    return checkboxTreeNode;
  }

  public static String getLastCategoryFromCategoryPath(String categoryPath) {
    if (!StringUtils.isBlank(categoryPath)) {
      String[] categories = categoryPath.split(DELIMITER);
      return categories[categories.length - 1];
    }
    return StringUtils.EMPTY;
  }
}

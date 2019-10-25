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

import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
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

  public static CheckboxTreeNode buildCaseCategoryCheckboxTreeRoot() {

    CheckboxTreeNode root = buildRoot();
    RegisteredApplicationService service = new RegisteredApplicationService();
    List<String> involvedApplications =
        service.findActiveIvyAppsBasedOnConfiguration(Ivy.session().getSessionUserName());
    CaseQuery caseQuery = IvyExecutor.executeAsSystem(() -> {
      return SubProcessCall.withPath(PortalConstants.BUILD_CASE_QUERY_CALLABLE).withStartSignature("buildCaseQuery()")
          .call().get("caseQuery", CaseQuery.class);
    });
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
  
  public static CaseNode buildCaseNodeFrom(String name, String category) {
    CaseNode nodeData = new CaseNode();
    nodeData.setValue(name);
    nodeData.setCategory(category);
    nodeData.setRootNodeAllCase(false);
    nodeData.setFirstCategoryNode(false);
    return nodeData;
  }
  
  public static CheckboxTreeNode buildRoot() {
    CaseNode nodeData = new CaseNode();
    nodeData.setValue(StringUtils.EMPTY);
    nodeData.setCategory(StringUtils.EMPTY);
    nodeData.setRootNodeAllCase(true);
    nodeData.setFirstCategoryNode(true);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode(StringUtils.EMPTY, nodeData, null);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(false);
    return checkboxTreeNode;
  }

}

package ch.ivy.addon.portalkit.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.NodeUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class StatisticCaseCategoryFilter {

  @JsonIgnore
  private CheckboxTreeNode[] categories = new CheckboxTreeNode[] {};
  @JsonIgnore
  private CheckboxTreeNode root;

  /**
   * Only using in saving filters, don't use anymore because getter/setter were changed
   */
  private List<String> categoryPaths = new ArrayList<>();

  /**
   * StatisticCaseCategoryFilter's constructor will generate Case's Category as an TreeRoot
   * And add two options such as SelectAll, No Category
   * 
   */
  public StatisticCaseCategoryFilter() {
    super();
    CheckboxTreeNode caseCategoryTree = CaseTreeUtils.buildCaseCategoryCheckboxTreeRoot();
    if (caseCategoryTree != null && caseCategoryTree.getChildCount() > 0) {
      root = buildRoot();
      CheckboxTreeNode checkboxTreeNode = buildCaseCategoryCheckBoxTreeNode(root,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/filter/selectAll"),
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/filter/selectAll"));
      checkboxTreeNode.getChildren().addAll(caseCategoryTree.getChildren());

      CheckboxTreeNode noCategory = buildCaseCategoryCheckBoxTreeNode(root,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/filter/noCategory"),
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/filter/noCategory"));
      checkboxTreeNode.getChildren().add(noCategory);
      root.setChildren(new ArrayList<>());
      root.getChildren().add(checkboxTreeNode);
    } else {
      root = new CheckboxTreeNode();
    }
  }

  private static CheckboxTreeNode buildRoot() {
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

  private static CheckboxTreeNode buildCaseCategoryCheckBoxTreeNode(CheckboxTreeNode parentNode, String newNodeName,
      String category) {
    CaseNode nodeData = buildCaseNodeFrom(newNodeName, category);
    CheckboxTreeNode checkboxTreeNode = new CheckboxTreeNode("default", nodeData, parentNode);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(false);
    return checkboxTreeNode;
  }

  private static CaseNode buildCaseNodeFrom(String name, String category) {
    CaseNode nodeData = new CaseNode();
    nodeData.setValue(name);
    nodeData.setCategory(category);
    nodeData.setRootNodeAllCase(false);
    nodeData.setFirstCategoryNode(false);
    return nodeData;
  }

  public CaseQuery buildQuery() {
    if (categories == null || categories.length == 0) {
      return null;
    }
    CaseQuery caseQuery = CaseQuery.create();
    IFilterQuery filterQuery = caseQuery.where();
    for (CheckboxTreeNode node : categories) {
      if (node.getParent() != null && !Arrays.asList(categories).contains(node.getParent())) {
        String category = ((CaseNode) node.getData()).getCategory();
        if (node.isLeaf()) {
          filterQuery.or().category().isEqualIgnoreCase(category);
        } else {
          filterQuery.or().category().isEqualIgnoreCase(category);
          filterQuery.or().category().isLikeIgnoreCase(String.format("%s%%", category + CaseTreeUtils.DELIMITER));
        }
      }
    }
    return caseQuery;
  }

  public CheckboxTreeNode[] getCategories() {
    return categories;
  }

  public void setCategories(CheckboxTreeNode[] categories) {
    if (ArrayUtils.isEmpty(categories)) {
      this.categories = new CheckboxTreeNode[] {};
    } else {
      this.categories = categories;
    }
  }

  public CheckboxTreeNode getRoot() {
    return root;
  }

  public void setRoot(CheckboxTreeNode root) {
    this.root = root;
  }

  /**
   * This method is used for updating Category Tree 
   * and Category Paths when having session filter
   */
  public void updateRootAndCategoryPaths() {
    root = CaseTreeUtils.buildCaseCategoryCheckboxTreeRoot();
    setCategoryPaths(this.categoryPaths);
  }

  /**
   * get Case's category path by data from Tree node component
   * @return list of paths for case's category
   */
  public List<String> getCategoryPaths() {
    this.categoryPaths = NodeUtils.getCategoryPaths(categories, CaseNode.class);
    return this.categoryPaths;
  }

  /**
   * set Case's category path to categoryPaths is provided and update categories by the change on Tree
   * @param categoryPaths
   */
  public void setCategoryPaths(List<String> categoryPaths) {
    this.categoryPaths = categoryPaths;
    List<CheckboxTreeNode> selectedCategories = new ArrayList<>();
    checkCategoryTreeNode(root, selectedCategories, categoryPaths);
    categories = selectedCategories.toArray(new CheckboxTreeNode[selectedCategories.size()]);
  }

  private void checkCategoryTreeNode(CheckboxTreeNode node, List<CheckboxTreeNode> selectedCategories,
      List<String> paths) {
    if (node == null) {
      return;
    }
    CaseNode nodeData = (CaseNode) node.getData();
    for (String path : paths) {
      if (path.equals(nodeData.getCategory())) {
        node.setSelected(true);
        selectedCategories.add(node);
      } else {
        if (!selectedCategories.contains(node)) {
          node.setSelected(false);
        }
      }
    }
    node.getChildren().forEach(child -> checkCategoryTreeNode((CheckboxTreeNode) child, selectedCategories, paths));
  }
}

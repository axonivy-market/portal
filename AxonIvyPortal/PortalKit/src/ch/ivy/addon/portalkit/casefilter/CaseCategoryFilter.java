package ch.ivy.addon.portalkit.casefilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.NodeUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseCategoryFilter extends CaseFilter {

  @JsonIgnore
  private CheckboxTreeNode[] categories = new CheckboxTreeNode[] {};
  @JsonIgnore
  private CheckboxTreeNode root;

  // Only using in saving filters, don't use anymore because getter/setter were changed
  private List<String> categoryPaths = new ArrayList<>();

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseCategory");
  }

  @Override
  public String value() {
    return NodeUtils.getNodeValue(categories, CaseNode.class);
  }

  @Override
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

  @Override
  public void resetValues() {
    categories = new CheckboxTreeNode[] {};
    if(root != null) {
      root.setSelected(false);
    }
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
    if(root == null) {
      root = CaseTreeUtils.buildCaseCategoryCheckboxTreeRoot();
    }
    return root;
  }

  public void setRoot(CheckboxTreeNode root) {
    this.root = root;
  }
  
  //This method is used for updating Category Tree and Category Paths when having session filter 
  public void updateRootAndCategoryPaths() {
    root = CaseTreeUtils.buildCaseCategoryCheckboxTreeRoot();
    setCategoryPaths(this.categoryPaths);
  }

  public List<String> getCategoryPaths() {
    this.categoryPaths = NodeUtils.getCategoryPaths(categories, CaseNode.class);
    return this.categoryPaths;
  }

  public void setCategoryPaths(List<String> categoryPaths) {
    this.categoryPaths = categoryPaths;
    List<CheckboxTreeNode> selectedCategories = new ArrayList<>();
    checkCategoryTreeNode(root, selectedCategories, categoryPaths);
    categories = selectedCategories.toArray(new CheckboxTreeNode[selectedCategories.size()]);
  }

  private void checkCategoryTreeNode(CheckboxTreeNode node, List<CheckboxTreeNode> selectedCategories, List<String> paths) {
    if (node == null) {
      return;
    }
    CaseNode nodeData = (CaseNode) node.getData();
    for(String path : paths) {
      if (path.equals(nodeData.getCategory())) {
        node.setSelected(true);
        selectedCategories.add(node);
      } else {
        if(!selectedCategories.contains(node)) {
          node.setSelected(false);
        }
      }
    }
    node.getChildren().forEach(child -> checkCategoryTreeNode((CheckboxTreeNode) child, selectedCategories, paths));
  }
}

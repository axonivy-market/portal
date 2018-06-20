package ch.ivy.addon.portalkit.casefilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CaseCategoryFilter extends CaseFilter {

  @JsonIgnore
  private CheckboxTreeNode[] categories = new CheckboxTreeNode[] {};
  @JsonIgnore
  private CheckboxTreeNode root;

  // Only using in saving filters, don't use anymore because getter/setter were changed
  private List<String> categoryPaths = new ArrayList<>();

  public CaseCategoryFilter() {
    super();
    root = CaseTreeUtils.buildCaseCategoryCheckboxTreeRoot();
  }

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseCategory");
  }

  @Override
  public String value() {
    if (categories == null || categories.length == 0) {
      return ALL;
    }
    List<String> values = new ArrayList<>();
    for (CheckboxTreeNode node : categories) {
      if (node.getParent() != null && !Arrays.asList(categories).contains(node.getParent())) {
        CaseNode nodeData = (CaseNode) node.getData();
        values.add(nodeData.getCategory());
      }
    }
    return StringUtils.join(values, ", ");
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
        CaseNode nodeData = (CaseNode) node.getData();
        if (node.isLeaf()) {
          filterQuery.or().category().isEqualIgnoreCase(nodeData.getValue());
        } else {
          filterQuery.or().category().isEqualIgnoreCase(nodeData.getValue());
          filterQuery.or().category().isLikeIgnoreCase(String.format("%s%%", nodeData.getValue() + CaseTreeUtils.DELIMITER));
        }
      }
    }
    return caseQuery;
  }

  @Override
  public void resetValues() {
    categories = new CheckboxTreeNode[] {};
    unselectCheckboxTreeNode(root);
  }
  
  private void unselectCheckboxTreeNode(CheckboxTreeNode node){
    node.setSelected(false);
    node.getChildren().forEach(child -> unselectCheckboxTreeNode((CheckboxTreeNode)child));
  }

  public CheckboxTreeNode[] getCategories() {
    return categories;
  }

  public void setCategories(CheckboxTreeNode[] categories) {
    this.categories = categories;
  }

  public CheckboxTreeNode getRoot() {
    return root;
  }

  public void setRoot(CheckboxTreeNode root) {
    this.root = root;
  }

  public List<String> getCategoryPaths() {
    categoryPaths = new ArrayList<>();
    for (CheckboxTreeNode node : categories) {
      CaseNode nodeData = (CaseNode) node.getData();
      categoryPaths.add(nodeData.getValue());
    }
    return categoryPaths;
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
    if (paths.contains(nodeData.getValue())) {
      node.setSelected(true);
      selectedCategories.add(node);
    } else {
      node.setSelected(false);
    }
    node.getChildren().forEach(child -> checkCategoryTreeNode((CheckboxTreeNode) child, selectedCategories, paths));
  }
}

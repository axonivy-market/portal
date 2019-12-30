package ch.ivy.addon.portalkit.casefilter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseCategoryFilter extends CaseFilter {

  @JsonIgnore
  private CheckboxTreeNode[] categories = new CheckboxTreeNode[] {};
  @JsonIgnore
  private CheckboxTreeNode root;

  private List<String> categoryPaths = new ArrayList<>();

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseCategory");
  }

  @Override
  public String value() {
    return CategoryUtils.getNodeValue(categoryPaths);
  }

  @Override
  public CaseQuery buildQuery() {
    if (CollectionUtils.isEmpty(categoryPaths)) {
      return null;
    }
    CaseQuery caseQuery = CaseQuery.create();
    IFilterQuery filterQuery = caseQuery.where();
    for (String category : categoryPaths) {
      if (StringUtils.equals(category, CategoryUtils.NO_CATEGORY)) {
        filterQuery.or().category().isEqual(StringUtils.EMPTY);
      } else {
        filterQuery.or().category().isEqualIgnoreCase(category);
      }
    }
    return caseQuery;
  }

  @Override
  public void resetValues() {
    categories = new CheckboxTreeNode[] {};
    categoryPaths = new ArrayList<>();
    root = null;
  }

  public CheckboxTreeNode[] getCategories() {
    return categories;
  }

  public void setCategories(CheckboxTreeNode[] categories) {
    this.categories = categories;
    this.categoryPaths = CategoryUtils.getCategoryPaths(categories);
  }

  public CheckboxTreeNode getRoot() {
    initializeRoot();
    return root;
  }

  public void setRoot(CheckboxTreeNode root) {
    this.root = root;
  }
  
  public void initializeRoot() {
    if (root == null) {
      root = CaseTreeUtils.buildCaseCategoryCheckboxTreeRoot();
      categories = CategoryUtils.recoverSelectedCategories(root, categoryPaths);
    }
  }

  public List<String> getCategoryPaths() {
    return this.categoryPaths;
  }

  public void setCategoryPaths(List<String> categoryPaths) {
    this.categoryPaths = categoryPaths;
    categories = CategoryUtils.recoverSelectedCategories(root, categoryPaths);
  }
}

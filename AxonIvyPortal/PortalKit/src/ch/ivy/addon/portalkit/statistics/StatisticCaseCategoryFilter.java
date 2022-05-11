package ch.ivy.addon.portalkit.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class StatisticCaseCategoryFilter implements Serializable {

  private static final long serialVersionUID = -1200590667880254731L;

  @JsonIgnore
  private CheckboxTreeNode<CategoryNode>[] categories = new CheckboxTreeNode[] {};
  @JsonIgnore
  private CheckboxTreeNode<CategoryNode> root;

  private List<String> categoryPaths = new ArrayList<>();

  public CaseQuery buildQuery() {
    if (CollectionUtils.isEmpty(categoryPaths)) {
      return null;
    }
    CaseQuery caseQuery = CaseUtils.createBusinessCaseQuery();
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

  public CheckboxTreeNode<CategoryNode>[] getCategories() {
    return categories;
  }

  public void setCategories(CheckboxTreeNode<CategoryNode>[] categories) {
    this.categories = categories;
    this.categoryPaths = CategoryUtils.getCategoryPaths(categories);
  }

  public CheckboxTreeNode<CategoryNode> getRoot() {
    if (root == null) {
      root = CaseTreeUtils.buildCaseCategoryCheckboxTreeRoot();
    }
    return root;
  }

  public void setRoot(CheckboxTreeNode<CategoryNode> root) {
    this.root = root;
  }

  /**
   * get Case's category path by data from Tree node component
   * @return list of paths for case's category
   */
  public List<String> getCategoryPaths() {
    return this.categoryPaths;
  }

  /**
   * set Case's category path to categoryPaths is provided and update categories by the change on Tree
   * @param categoryPaths
   */
  public void setCategoryPaths(List<String> categoryPaths) {
    this.categoryPaths = categoryPaths;
    categories = CategoryUtils.recoverSelectedCategories(root, categoryPaths);
  }
}

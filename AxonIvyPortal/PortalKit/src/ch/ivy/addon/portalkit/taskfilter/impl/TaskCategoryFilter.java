package ch.ivy.addon.portalkit.taskfilter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskCategoryFilter extends TaskFilter {

  @SuppressWarnings("unchecked")
  @JsonIgnore
  private CheckboxTreeNode<CategoryNode>[] categories = new CheckboxTreeNode[] {};
  @JsonIgnore
  private CheckboxTreeNode<CategoryNode> root;

  private List<String> categoryPaths = new ArrayList<>();

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CATEGORY");
  }

  @Override
  public String value() {
    if (CollectionUtils.isNotEmpty(categoryPaths) && (categories == null || categories.length == 0)) {
      initializeRoot();
    }
    return CategoryUtils.getCategoryValues(categories);
  }

  @Override
  public TaskQuery buildQuery() {
    if (CollectionUtils.isEmpty(categoryPaths)) {
      return null;
    }
    TaskQuery taskQuery = TaskQuery.create();
    IFilterQuery filterQuery = taskQuery.where();
    for (String category : categoryPaths) {
      if (StringUtils.equals(category, CategoryUtils.NO_CATEGORY)) {
        filterQuery.or().category().isEqual(StringUtils.EMPTY);
      } else {
        filterQuery.or().category().isEqualIgnoreCase(category);
      }
    }
    return taskQuery;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void resetValues() {
    categories = new CheckboxTreeNode[] {};
    categoryPaths = new ArrayList<>();
    root = null;
  }

  public CheckboxTreeNode<CategoryNode>[] getCategories() {
    return categories;
  }

  public void setCategories(CheckboxTreeNode<CategoryNode>[] categories) {
    this.categories = categories;
    this.categoryPaths = CategoryUtils.getCategoryPaths(categories);
  }

  public CheckboxTreeNode<CategoryNode> getRoot() {
    initializeRoot();
    return root;
  }

  public void setRoot(CheckboxTreeNode<CategoryNode> root) {
    this.root = root;
  }

  private void initializeRoot() {
    String allCategoriesText = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/allCategories");
    // If language is changed, category tree needs to be rebuilt
    String allCategoriesTextInTree = Optional.ofNullable(root)
        .map(CheckboxTreeNode::getChildren)
        .filter(CollectionUtils::isNotEmpty)
        .map(list -> list.get(0))
        .map(TreeNode::getData)
        .map(data -> data.getValue())
        .orElse(StringUtils.EMPTY);
    if (root == null || !StringUtils.equals(allCategoriesTextInTree, allCategoriesText)) {
      root = TaskTreeUtils.buildTaskCategoryCheckboxTreeRoot();
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

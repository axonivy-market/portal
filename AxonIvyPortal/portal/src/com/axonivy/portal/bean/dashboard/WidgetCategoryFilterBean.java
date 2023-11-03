package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.CategoryUtils;

@ManagedBean
@ViewScoped
public class WidgetCategoryFilterBean implements Serializable {

  private static final long serialVersionUID = 5886200209828694569L;

  private static List<FilterOperator> operators = FilterOperator.CATEGORY_OPERATORS.stream().toList();

  private CheckboxTreeNode<CategoryNode> categoryTree;
  private CheckboxTreeNode<CategoryNode>[] selectedCategoryNodes;
  private String selectedCategoriesString;

  public void init(DashboardFilter filter) {
    loadCategories(filter);

    selectedCategoriesString = "";
    if (CollectionUtils.isNotEmpty(filter.getTexts())) {
      selectedCategoriesString = filter.getTexts().stream().collect(Collectors.joining(", "));
    }
  }

  public List<FilterOperator> getOperators() {
    return operators;
  }

  public void onChangeOperator(DashboardFilter filter) {
    if (filter.getOperator() == FilterOperator.NO_CATEGORY) {
      filter.setTexts(new ArrayList<>());
    }
  }

  public void loadCategories(DashboardFilter filter) {
    var availableCategories = CaseTreeUtils.buildCaseCategoryCheckboxTreeRootWithoutAllCategoriesNode();
    this.categoryTree = availableCategories;
    List<String> filterList = filter.getTexts();
    if (CollectionUtils.isNotEmpty(filterList)) {
      CategoryUtils.recoverSelectedCategories(this.getCategoryTree(), filterList);
    }
  }

  public String getSelectedCategoriesString() {
    return selectedCategoriesString;
  }

  public CheckboxTreeNode<CategoryNode>[] getSelectedCategoryNodes() {
    return selectedCategoryNodes;
  }

  public void setSelectedCategoryNodes(CheckboxTreeNode<CategoryNode>[] selectedCategoryNodes) {
    this.selectedCategoryNodes = selectedCategoryNodes;
  }

  public void updateCategoriesPath(DashboardFilter filter) {
    filter.setTexts(CategoryUtils.getCategoryPaths(selectedCategoryNodes));
  }

  public CheckboxTreeNode<CategoryNode> getCategoryTree() {
    return categoryTree;
  }
}

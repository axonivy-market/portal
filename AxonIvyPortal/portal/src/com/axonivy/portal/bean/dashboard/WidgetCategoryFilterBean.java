package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;

@ManagedBean
@ViewScoped
public class WidgetCategoryFilterBean implements Serializable {

  private static final long serialVersionUID = 5886200209828694569L;

  private static List<FilterOperator> operators = FilterOperator.CATEGORY_OPERATORS.stream().toList();
  private static List<FilterOperator> statisticOperators = FilterOperator.STATISTIC_TEXT_OPERATORS.stream().toList();
  

  private CheckboxTreeNode<CategoryNode> categoryTree;
  private CheckboxTreeNode<CategoryNode>[] selectedCategoryNodes;
  private String selectedCategoriesString;

  public void init(BaseFilter filter, String widgetType) {
    loadCategories(filter, widgetType);

    setSelectedCategoriesString("");
    if (CollectionUtils.isNotEmpty(filter.getValues())) {
      setSelectedCategoriesString(filter.getValues().stream().collect(Collectors.joining(", ")));
    }
  }

  public List<FilterOperator> getOperators() {
    return operators;
  }

  public List<FilterOperator> getStatisticOperators() {
    return statisticOperators;
  }

  public void onChangeOperator(BaseFilter filter) {
    filter.setValues(new ArrayList<>());
  }

  public void loadCategories(BaseFilter filter, String widgetType) {
    if ("task".equals(StringUtils.lowerCase(widgetType))) {
      this.categoryTree = TaskTreeUtils.buildTaskCategoryCheckboxTreeRootWithoutAllCategoriesNode();
    } else {
      this.categoryTree = CaseTreeUtils.buildCaseCategoryCheckboxTreeRootWithoutAllCategoriesNode();
    }

    List<String> filterList = filter.getValues();
    if (CollectionUtils.isNotEmpty(filterList)) {
      CategoryUtils.recoverSelectedCategories(this.getCategoryTree(), filterList);
    }
  }

  public String getSelectedCategoriesString() {
    return selectedCategoriesString;
  }

  public void setSelectedCategoriesString(String selectedCategoriesString) {
    this.selectedCategoriesString = selectedCategoriesString;
  }

  public CheckboxTreeNode<CategoryNode>[] getSelectedCategoryNodes() {
    return selectedCategoryNodes;
  }

  public void setSelectedCategoryNodes(CheckboxTreeNode<CategoryNode>[] selectedCategoryNodes) {
    this.selectedCategoryNodes = selectedCategoryNodes;
  }

  public void updateCategoriesPath(BaseFilter filter) {
    filter.setValues(CategoryUtils.getCategoryPaths(selectedCategoryNodes));
  }

  public CheckboxTreeNode<CategoryNode> getCategoryTree() {
    return categoryTree;
  }

  public boolean isShowCategorySelectionPanel(BaseFilter filter) {
    return !(filter.getOperator() == FilterOperator.NO_CATEGORY || filter.getOperator() == FilterOperator.CONTAINS
        || filter.getOperator() == FilterOperator.NOT_CONTAINS);
  }

  public boolean isShowCategoryContainsPanel(BaseFilter filter) {
    return filter.getOperator() == FilterOperator.CONTAINS || filter.getOperator() == FilterOperator.NOT_CONTAINS;
  }
}

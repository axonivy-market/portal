package ch.ivy.addon.portalkit.casefilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseCategoryFilter extends CaseFilter {
  private CheckboxTreeNode[] categories = new CheckboxTreeNode[]{};

  @Override
  public String label() {
    return "Case Category";
  }

  @Override
  public String value() {
    if (categories == null || categories.length == 0){
      return ALL;
    }
    List<String> values = new ArrayList<>();
    for (CheckboxTreeNode node : categories) {
      node.setExpanded(true);
      CaseNode nodeData = (CaseNode) node.getData();
      values.add(nodeData.getCategory());
    }
    return StringUtils.join(values, ",");
  }

  @Override
  public CaseQuery buildQuery() {
    if (categories == null || categories.length == 0){
      return null;
    }
    List<String> selectedCategories = new ArrayList<>();
    for (CheckboxTreeNode node : categories) {
      if (node.getParent() != null && !Arrays.asList(categories).contains(node.getParent())){
        CaseNode nodeData = (CaseNode) node.getData(); 
        selectedCategories.add(nodeData.getValue());
      }
    }
    
    CaseQuery caseQuery = CaseQuery.create();
    IFilterQuery filterQuery = caseQuery.where();
    selectedCategories.forEach(category -> filterQuery.or().category().isLikeIgnoreCase(String.format("%s%%", category)));
    return caseQuery;
  }

  @Override
  public void resetValues() {
    categories = new CheckboxTreeNode[]{};
  }

  public CheckboxTreeNode[] getCategories() {
    return categories;
  }

  public void setCategories(CheckboxTreeNode[] categories) {
    this.categories = categories;
  }
}

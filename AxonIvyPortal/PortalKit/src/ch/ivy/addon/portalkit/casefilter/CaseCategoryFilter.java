package ch.ivy.addon.portalkit.casefilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

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
      values.add(node.getData().toString());
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
        selectedCategories.add(buildCategoryValueFromTreeNode(node));
      }
    }
    return CaseQuery.create().where().category().isEqualIgnoreCase(categories[0].getData().toString());
  }

  private String buildCategoryValueFromTreeNode(CheckboxTreeNode node) {
    String categoryValue = node.getData().toString();
    CheckboxTreeNode tempNode = node;
    while (tempNode.getParent() != null && !"Root".equals(tempNode.getParent().getData().toString())) {
      tempNode = (CheckboxTreeNode) tempNode.getParent();
      categoryValue = tempNode.getData().toString() + "/" + categoryValue;
    }
    return categoryValue;
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

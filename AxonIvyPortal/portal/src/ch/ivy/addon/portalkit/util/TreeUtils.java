package ch.ivy.addon.portalkit.util;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivyteam.ivy.environment.Ivy;

public class TreeUtils {

  public static CheckboxTreeNode<CategoryNode> buildRoot() {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(StringUtils.EMPTY);
    nodeData.setCategory(StringUtils.EMPTY);
    CheckboxTreeNode<CategoryNode> checkboxTreeNode = new CheckboxTreeNode<>(StringUtils.EMPTY, nodeData, null);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(true);
    buildAllCategoriesNode(checkboxTreeNode);
    return checkboxTreeNode;
  }

  private static void buildAllCategoriesNode(CheckboxTreeNode<CategoryNode> root) {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/allCategories"));
    nodeData.setCategory(StringUtils.EMPTY);
    CheckboxTreeNode<CategoryNode> checkboxTreeNode = new CheckboxTreeNode<>(nodeData, root);
    checkboxTreeNode.setExpanded(true);
    checkboxTreeNode.setSelected(true);
    buildNoCategoryNode(checkboxTreeNode);
  }

  private static void buildNoCategoryNode(CheckboxTreeNode<CategoryNode> parent) {
    CategoryNode nodeData = new CategoryNode();
    nodeData.setValue(Ivy.cms().co(CategoryUtils.NO_CATEGORY_CMS));
    nodeData.setCategory(CategoryUtils.NO_CATEGORY);
    CheckboxTreeNode<CategoryNode> checkboxTreeNode = new CheckboxTreeNode<>(nodeData, parent);
    checkboxTreeNode.setSelected(true);
  }
}

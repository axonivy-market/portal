package com.axonivy.portal.developerexamples.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.axonivy.portal.developerexamples.dto.CategoryNode;

public class CategoryUtils {

  public static final String NO_CATEGORY = "[No Category]";
  public static final String NO_CATEGORY_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/noCategory";
  public static final String CATEGORY_SEPARATOR = ", ";
  public static final String CATEGORY_PATH_DELIMITER = "/";

  private CategoryUtils() {}

  public static List<String> getCategoryPaths(CheckboxTreeNode<CategoryNode>[] nodes) {
    List<String> nodePaths = new ArrayList<>();
    if (nodes != null) {
      for (CheckboxTreeNode<CategoryNode> node : nodes) {
        String category = node.getData().getCategory();
        if (StringUtils.equals(category, StringUtils.EMPTY)) {
          return new ArrayList<>(); // "All categories" is selected
        }
        nodePaths.add(category);
      }
    }
    return nodePaths;
  }

  @SuppressWarnings("unchecked")
  public static CheckboxTreeNode<CategoryNode>[] recoverSelectedCategories(CheckboxTreeNode<CategoryNode> node,
      List<String> paths) {
    List<CheckboxTreeNode<CategoryNode>> selectedCategories = new ArrayList<>();
    recoverSelectedCategories(node, selectedCategories, paths);
    return selectedCategories.toArray(new CheckboxTreeNode[selectedCategories.size()]);
  }

  private static void recoverSelectedCategories(CheckboxTreeNode<CategoryNode> node,
      List<CheckboxTreeNode<CategoryNode>> selectedCategories, List<String> paths) {
    if (node == null || paths == null) {
      return;
    }
    CategoryNode nodeData = node.getData();
    for (String path : paths) {
      if (path.equals(nodeData.getCategory())) {
        node.setSelected(true);
        selectedCategories.add(node);
      } else {
        if (!selectedCategories.contains(node)) {
          node.setSelected(false);
        }
      }
    }
    node.getChildren()
        .forEach(child -> recoverSelectedCategories((CheckboxTreeNode<CategoryNode>) child, selectedCategories, paths));
  }

}

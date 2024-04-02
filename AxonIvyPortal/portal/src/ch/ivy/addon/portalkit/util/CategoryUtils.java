package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivyteam.ivy.cm.exec.LocalizedTextResolverFactory;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.Category;
import ch.ivyteam.ivy.workflow.category.CategoryPath;

public class CategoryUtils {

  private static final String ALL = "All";
  public static final String NO_CATEGORY = "[No Category]";
  public static final String NO_CATEGORY_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/noCategory";
  public static final String CATEGORY_SEPARATOR = ", ";
  public static final String CATEGORY_PATH_DELIMITER = "/";

  private CategoryUtils() {}

  public static String getNodeValue(List<String> categoryPaths) {
    if (CollectionUtils.isEmpty(categoryPaths)) {
      return ALL;
    }

    List<String> values = new ArrayList<>();
    for (String category : categoryPaths) {
      if (StringUtils.equals(category, CategoryUtils.NO_CATEGORY)) {
        values.add(Ivy.cms().co(NO_CATEGORY_CMS));
      } else {
        values.add(category);
      }
    }
    return StringUtils.join(values, CATEGORY_SEPARATOR);
  }

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

  public static String getCategoryValues(CheckboxTreeNode<CategoryNode>[] nodes) {
    List<String> nodePaths = new ArrayList<>();
    if (nodes != null) {
      for (CheckboxTreeNode<CategoryNode> node : nodes) {
        var categoryNode = node.getData();
        if (StringUtils.equals(categoryNode.getCategory(), StringUtils.EMPTY)) {
          return ALL; // "All categories" is selected
        }
        nodePaths.add(categoryNode.getValue());
      }
    }
    return getNodeValue(nodePaths);
  }

  @SuppressWarnings("unchecked")
  public static CheckboxTreeNode<CategoryNode>[] recoverSelectedCategories(CheckboxTreeNode<CategoryNode> node, List<String> paths) {
    List<CheckboxTreeNode<CategoryNode>> selectedCategories = new ArrayList<>();
    recoverSelectedCategories(node, selectedCategories, paths);
    return selectedCategories.toArray(new CheckboxTreeNode[selectedCategories.size()]);
  }

  private static void recoverSelectedCategories(CheckboxTreeNode<CategoryNode> node, List<CheckboxTreeNode<CategoryNode>> selectedCategories, List<String> paths) {
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
    node.getChildren().forEach(child -> recoverSelectedCategories((CheckboxTreeNode<CategoryNode>) child, selectedCategories, paths));
  }

  public static void disableSelectionExcept(CheckboxTreeNode<CategoryNode> node, List<String> selectablePaths) {
    if (node == null || CollectionUtils.isEmpty(selectablePaths)) {
      return;
    }
    node.setSelectable(false);
    node.setSelected(false);
    CategoryNode nodeData = node.getData();
    for (String path : selectablePaths) {
      if (path.equals(nodeData.getCategory())) {
        node.setSelectable(true);
        node.setSelected(true);
      }
    }
    node.getChildren().forEach(child -> disableSelectionExcept((CheckboxTreeNode<CategoryNode>) child, selectablePaths));
  }

  public static void disableSelectionWithoutSelectingExcept(CheckboxTreeNode<CategoryNode> node, List<String> selectablePaths) {
    if (node == null || CollectionUtils.isEmpty(selectablePaths)) {
      return;
    }
    node.setSelectable(false);
    node.setSelected(false);
    CategoryNode nodeData = node.getData();
    for (String path : selectablePaths) {
      if (path.equals(nodeData.getCategory())) {
        node.setSelectable(true);
        node.setSelected(false);
      }
    }
    node.getChildren().forEach(child -> disableSelectionWithoutSelectingExcept((CheckboxTreeNode<CategoryNode>) child, selectablePaths));
  }

  public static String getSelectedNodesAsString(CheckboxTreeNode<CategoryNode> node) {
    List<String> selectedNodes = new ArrayList<>();
    if (node == null) {
      return "";
    }
    filterSelectedNode(node, selectedNodes);
    return StringUtils.join(selectedNodes, CATEGORY_SEPARATOR);
  }

  private static void filterSelectedNode(CheckboxTreeNode<CategoryNode> node, List<String> selectedNodeValues) {
    CategoryNode nodeData = node.getData();
    if (node.isSelected() && !selectedNodeValues.contains(nodeData.getValue())) {
      selectedNodeValues.add(nodeData.getValue());
    }
    node.getChildren().forEach(child -> filterSelectedNode((CheckboxTreeNode<CategoryNode>) child, selectedNodeValues));
  }

  public static Category buildExpressCategory(String processName) {
    String categoryName = processName;
    if (processName.contains(CATEGORY_PATH_DELIMITER)) {
      String[] processNameArray = processName.split(CATEGORY_PATH_DELIMITER);
      categoryName = processNameArray[processNameArray.length - 1];
    }

    CategoryPath rootExpress = new CategoryPath("ExpressWorkflow");
    CategoryPath process = rootExpress.append(categoryName.trim());
    return Category.createFor(process,
        new LocalizedTextResolverFactory().createFor("Categories", Ivy.request().getProcessModelVersion()));
  }
  
  public static Category buildCategoryFromTechnicalPath(String categoryTechnicalPath) {
    return Category.createFor(new CategoryPath(categoryTechnicalPath),
        new LocalizedTextResolverFactory().createFor("Categories", Ivy.request().getProcessModelVersion()));
  }
}

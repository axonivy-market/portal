package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.NodeUtils;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskCategoryFilter extends TaskFilter {

  @JsonIgnore
  private CheckboxTreeNode[] categories = new CheckboxTreeNode[] {};
  @JsonIgnore
  private CheckboxTreeNode root;

  private List<String> categoryPaths = new ArrayList<>();

  public TaskCategoryFilter() {
    super();
    root = TaskTreeUtils.buildTaskCategoryCheckboxTreeRoot();
  }

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/elapsedTimeChart/taskCategory");
  }

  @Override
  public String value() {
    return NodeUtils.getNodeValue(categories, TaskNode.class);
  }

  @Override
  public TaskQuery buildQuery() {
    if (categories == null || categories.length == 0) {
      return null;
    }
    TaskQuery taskQuery = TaskQuery.create();
    IFilterQuery filterQuery = taskQuery.where();
    for (CheckboxTreeNode node : categories) {
      if (node.getParent() != null && !Arrays.asList(categories).contains(node.getParent())) {
        TaskNode nodeData = (TaskNode) node.getData();
        if (node.isLeaf()) {
          filterQuery.or().category().isEqualIgnoreCase(nodeData.getValue());
        } else {
          filterQuery.or().category().isEqualIgnoreCase(nodeData.getValue());
          filterQuery.or().category().isLikeIgnoreCase(String.format("%s%%", nodeData.getValue() + CaseTreeUtils.DELIMITER));
        }
      }
    }
    return taskQuery;
  }

  @Override
  public void resetValues() {
    categories = new CheckboxTreeNode[] {};
    root.setSelected(false);
  }


  public CheckboxTreeNode[] getCategories() {
    return categories;
  }

  public void setCategories(CheckboxTreeNode[] categories) {
    this.categories = categories;
  }

  public CheckboxTreeNode getRoot() {
    return root;
  }

  public void setRoot(CheckboxTreeNode root) {
    this.root = root;
  }

  public List<String> getCategoryPaths() {
    this.categoryPaths = NodeUtils.getCategoryPaths(categories, TaskNode.class);
    return this.categoryPaths;
  }

  public void setCategoryPaths(List<String> categoryPaths) {
    this.categoryPaths = categoryPaths;
    List<CheckboxTreeNode> selectedCategories = new ArrayList<>();
    checkCategoryTreeNode(root, selectedCategories, categoryPaths);
    categories = selectedCategories.toArray(new CheckboxTreeNode[selectedCategories.size()]);
  }

  private void checkCategoryTreeNode(CheckboxTreeNode node, List<CheckboxTreeNode> selectedCategories, List<String> paths) {
    if (node == null) {
      return;
    }
    TaskNode nodeData = (TaskNode) node.getData();
    if (paths.contains(nodeData.getValue())) {
      node.setSelected(true);
      selectedCategories.add(node);
    } else {
      node.setSelected(false);
    }
    node.getChildren().forEach(child -> checkCategoryTreeNode((CheckboxTreeNode) child, selectedCategories, paths));
  }
}

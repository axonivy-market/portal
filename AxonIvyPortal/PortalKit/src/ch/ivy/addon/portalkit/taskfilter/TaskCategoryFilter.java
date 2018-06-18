package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskCategoryFilter extends TaskFilter {
  private CheckboxTreeNode[] categories = new CheckboxTreeNode[] {};

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/elapsedTimeChart/taskCategory");
  }

  @Override
  public String value() {
    if (categories == null || categories.length == 0) {
      return ALL;
    }
    List<String> values = new ArrayList<>();
    for (CheckboxTreeNode node : categories) {
      TaskNode nodeData = (TaskNode) node.getData();
      values.add(nodeData.getCategory());
    }
    return StringUtils.join(values, ",");
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
  }

  public CheckboxTreeNode[] getCategories() {
    return categories;
  }

  public void setCategories(CheckboxTreeNode[] categories) {
    this.categories = categories;
  }
}

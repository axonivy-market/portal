package ch.ivy.addon.portalkit.taskfilter.impl;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskCategoryFilter extends TaskFilter {
  private List<String> categoryPaths = new ArrayList<>();

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CATEGORY");
  }

  @Override
  public String value() {
    return null;
  }

  @Override
  public TaskQuery buildQuery() {
    return null;
  }

  @Override
  public void resetValues() {}

  public List<String> getCategoryPaths() {
    return this.categoryPaths;
  }

  public void setCategoryPaths(List<String> categoryPaths) {
    this.categoryPaths = categoryPaths;
  }
}

package ch.ivy.addon.portalkit.taskfilter.impl;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskWorkerFilter extends TaskFilter {

  private String selectedWorkerMemberName;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/filter/worker");
  }

  @Override
  public String value() {
    return ALL;
  }

  @Override
  public TaskQuery buildQuery() {
    return null;
  }

  @Override
  public void resetValues() {
    selectedWorkerMemberName = null;
  }

  public String getSelectedWorkerMemberName() {
    return selectedWorkerMemberName;
  }

  public void setSelectedWorkerMemberName(String selectedWorkerMemberName) {
    this.selectedWorkerMemberName = selectedWorkerMemberName;
  }
}

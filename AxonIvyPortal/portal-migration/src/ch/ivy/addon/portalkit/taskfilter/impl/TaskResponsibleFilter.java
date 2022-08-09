package ch.ivy.addon.portalkit.taskfilter.impl;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskResponsibleFilter extends TaskFilter {
  private String selectedResponsibleMemberName;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ACTIVATOR");
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
    selectedResponsibleMemberName = null;
  }

  @Override
  public boolean defaultFilter() {
    return true;
  }

  public String getSelectedResponsibleMemberName() {
    return selectedResponsibleMemberName;
  }

  public void setSelectedResponsibleMemberName(String selectedResponsibleMemberName) {
    this.selectedResponsibleMemberName = selectedResponsibleMemberName;
  }

}

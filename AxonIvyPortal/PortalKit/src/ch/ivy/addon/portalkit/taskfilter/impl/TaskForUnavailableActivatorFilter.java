package ch.ivy.addon.portalkit.taskfilter.impl;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskForUnavailableActivatorFilter extends TaskFilter {

  private boolean queryTaskForUnavailableActivator;
  
  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/TASK_FOR_UNAVAILABLE_ACTIVATOR");
  }

  @Override
  public String value() {
    return null;
  }

  @Override
  public TaskQuery buildQuery() {
    TaskQuery query = TaskQuery.create().where().activatorAvailable().isFalse();
    return query;
  }

  @Override
  public void resetValues() {
    setQueryTaskForUnavailableActivator(false);
  }

  public boolean isQueryTaskForUnavailableActivator() {
    return queryTaskForUnavailableActivator;
  }

  public void setQueryTaskForUnavailableActivator(boolean queryTaskForUnavailableActivator) {
    this.queryTaskForUnavailableActivator = queryTaskForUnavailableActivator;
  }
}

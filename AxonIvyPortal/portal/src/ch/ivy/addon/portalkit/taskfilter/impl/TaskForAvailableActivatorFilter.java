package ch.ivy.addon.portalkit.taskfilter.impl;

import org.apache.commons.lang.WordUtils;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskForAvailableActivatorFilter extends TaskFilter {

  private boolean queryTaskForUnavailableActivator;
  
  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/TASK_FOR_UNAVAILABLE_ACTIVATOR");
  }

  @Override
  public String value() {
    return WordUtils.capitalize(String.valueOf(queryTaskForUnavailableActivator));
  }

  @Override
  public TaskQuery buildQuery() {
    if (queryTaskForUnavailableActivator) {
      return TaskQuery.create().where().activatorAvailable().isFalse();
    }
    return null;
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

package ch.ivy.addon.portalkit.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum BehaviourWhenClickingOnLineInTaskList {
  ACCESS_TASK_DETAILS, RUN_TASK;

  public String getLabel() {
    String label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/BehaviourWhenClickingOnLineInTaskList/" + name());
    return StringUtils.isBlank(label) ? name() : label;
  }
}

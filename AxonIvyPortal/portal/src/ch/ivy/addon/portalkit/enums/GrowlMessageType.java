package ch.ivy.addon.portalkit.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum GrowlMessageType {
  TASK_LEFT,
  TASK_FINISHED,
  PROCESS_VIEWER;
  
  public String message() {
    String label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/growlMessage/" + name());
    return StringUtils.isBlank(label) ? name() : label;
  }
}

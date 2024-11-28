package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum TaskEmptyMessage {
  EMPTY_MESSAGE("si-task-list-edit");

  private String icon;

  private TaskEmptyMessage() {}

  private TaskEmptyMessage(String icon) {
    this.icon = icon;
  }

  public String getMessage() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/iconMessage/taskWidget/" + name());
  }

  public String getIcon() {
    return icon;
  }
}

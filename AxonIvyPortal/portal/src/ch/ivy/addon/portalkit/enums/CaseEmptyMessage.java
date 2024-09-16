package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum CaseEmptyMessage {
  EMPTY_MESSAGE("si-task-list-edit");

  private String icon;

  private CaseEmptyMessage() {}

  private CaseEmptyMessage(String icon) {
    this.icon = icon;
  }

  public String getMessage() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/iconMessage/caseWidget/" + name());
  }

  public String getIcon() {
    return icon;
  }
}

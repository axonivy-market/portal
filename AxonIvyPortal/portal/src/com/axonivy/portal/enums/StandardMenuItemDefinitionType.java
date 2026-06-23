package com.axonivy.portal.enums;

public enum StandardMenuItemDefinitionType {
  TASK("/ch.ivy.addon.portalkit.ui.jsf/common/tasks", "navigateToTaskList();", "si si-task-list-edit"),
  PROCESS("/ch.ivy.addon.portalkit.ui.jsf/common/processes", "navigateToProcessList();",
      "si si-hierarchy-6 si-rotate-270"),
  CASE("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases", "navigateToCaseList();", "si si-layout-bullets"),
  DASHBOARD("/ch.ivy.addon.portalkit.ui.jsf/common/dashboard", "navigateToDefaultDashboard();",
      "si si-layout-dashboard");

  private String cmsUri;
  private String onClick;
  private String icon;

  private StandardMenuItemDefinitionType(String cmsUri, String onClick, String icon) {
    this.cmsUri = cmsUri;
    this.onClick = onClick;
    this.icon = icon;
  }

  public String getCmsUri() {
    return cmsUri;
  }

  public String getOnClick() {
    return onClick;
  }

  public String getIcon() {
    return icon;
  }
}
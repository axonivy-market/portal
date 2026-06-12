package com.axonivy.portal.dto.menu;

import com.axonivy.portal.components.enums.MenuKind;

import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;

public class CustomMenuItemDefinition extends PortalMenuItemDefinition {

  private static final long serialVersionUID = 2389106209816728259L;

  private String processStartPath;
  private String application;
  private DashboardProcess processStart;

  @Override
  public MenuKind getType() {
    return MenuKind.CUSTOM;
  }

  public String getProcessStartPath() {
    return processStartPath;
  }

  public void setProcessStartPath(String processStartPath) {
    this.processStartPath = processStartPath;
  }

  public DashboardProcess getProcessStart() {
    return processStart;
  }

  public void setProcessStart(DashboardProcess processStart) {
    this.processStart = processStart;
  }

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }
}

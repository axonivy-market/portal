package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum ExpressMessageType {
  INFO("INFO", "/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/info"),
  WARNING("WARNING", "/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/warning"),
  ERROR("WARNING", "/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/error"),
  FAILED("FAILED","/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/failed");

  private final String value;
  private final String label;

  ExpressMessageType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  public String getValue() {
    return value;
  }

  public String getLabel() {
    return Ivy.cms().co(label);
  }
}

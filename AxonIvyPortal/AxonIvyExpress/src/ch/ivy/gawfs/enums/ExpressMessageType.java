package ch.ivy.gawfs.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum ExpressMessageType {
  INFO("INFO", "/Texts/ExpressMessages/status/info"),
  WARNING("WARNING", "/Texts/ExpressMessages/status/warning"),
  ERROR("WARNING", "/Texts/ExpressMessages/status/error");

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

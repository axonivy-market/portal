package ch.ivy.addon.portalkit.enums;

public enum CustomWidgetParam {

  ID("id"),
  CATEGORY("category");

  private final String value;

  CustomWidgetParam(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}

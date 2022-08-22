package ch.ivy.addon.portalkit.enums;

public enum CustomWidgetParam {

  ID("id"),
  CATEGORY("category"),
  USERNAME("username"),
  EMAIL("email");

  private final String value;

  CustomWidgetParam(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}

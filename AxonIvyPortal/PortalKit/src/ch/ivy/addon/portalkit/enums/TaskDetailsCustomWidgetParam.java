package ch.ivy.addon.portalkit.enums;

public enum TaskDetailsCustomWidgetParam {

  ID("id"),
  CATEGORY("category");

  private final String value;

  TaskDetailsCustomWidgetParam(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}

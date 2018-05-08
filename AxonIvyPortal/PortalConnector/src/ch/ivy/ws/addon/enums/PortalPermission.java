package ch.ivy.ws.addon.enums;

public enum PortalPermission {
  TASK_WRITE_ACTIVATOR_OWN_TASKS("TaskWriteActivatorOwnTasks");

  private String value;

  private PortalPermission(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}

package ch.ivy.addon.portalkit.enums;

public enum MenuKind {
  PROCESS, TASK, CASE, DASHBOARD, CUSTOM;
  
  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}

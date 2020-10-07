package ch.ivy.addon.portalkit.enums;

public enum MenuKind {
  PROCESS, TASK, CASE, STATISTICS, CUSTOM, EXTERNAL_LINK;
  
  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}

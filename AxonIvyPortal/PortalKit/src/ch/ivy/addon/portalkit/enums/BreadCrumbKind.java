package ch.ivy.addon.portalkit.enums;

public enum BreadCrumbKind {
PROCESS, TASK, TASK_DETAIL, CASE_DETAIL, CASE, DASHBOARD, EXPRESS;
  
  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}

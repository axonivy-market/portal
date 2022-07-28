package ch.ivy.addon.portalkit.enums;


public enum TaskAndCaseAnalysisColumn {
  CASE_NAME(false, true),
  CASE_DESCRIPTION(false, false),
  CASE_ID(false, false),
  CASE_CATEGORY(false, false),
  CASE_CREATOR(false, false),
  CASE_STATE(false, true),
  TASK_NAME(true, true),
  TASK_ID(true, false),
  TASK_CATEGORY(true, false),
  TASK_DESCRIPTION(true, false),
  TASK_ACTIVATOR(true, false),
  TASK_WORKER(true, false),
  TASK_PRIORITY(true, true),
  TASK_STATE(true, false),
  TASK_CREATION_TIME(true, true),
  TASK_EXPIRY_TIME(true, false),
  TASK_FINISHED_TIME(true, true),
  CASE_OWNER(false, false),
  APPLICATION(false, false);

  private boolean isTaskField;
  private boolean isDefaultColumn;


  private TaskAndCaseAnalysisColumn(boolean isTaskField, boolean isDefaultColumn) {
    this.isTaskField = isTaskField;
    this.isDefaultColumn = isDefaultColumn;
  }

  public boolean isTaskField() {
    return isTaskField;
  }

  public boolean isDefaultColumn() {
    return isDefaultColumn;
  }
}

package com.axonivy.portal.selenium.common;


public enum TaskState {
  OPEN("open-task-state"), DONE("done-task-state"), DESTROYED("destroyed-task-state"),
  IN_PROGRESS("in-prorgess-task-state"), RESERVED("parked-task-state"), DELAYED("delayed-task-state"),
  SUSPENDED("suspended-task-state");

  private String value;

  private TaskState(String value) {
    this.setValue(value);
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}

package com.axonivy.portal.bo.jsonversion;

public class TaskDetailsJsonVersion extends AbstractJsonVersion {

  public static final TaskDetailsJsonVersion LATEST_VERSION = new TaskDetailsJsonVersion(LATEST);
  public static final TaskDetailsJsonVersion OLDEST_VERSION = new TaskDetailsJsonVersion(OLDEST);

  public TaskDetailsJsonVersion(String value) {
    super(value);
  }
}

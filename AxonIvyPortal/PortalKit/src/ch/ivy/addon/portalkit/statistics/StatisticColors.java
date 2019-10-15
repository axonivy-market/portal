package ch.ivy.addon.portalkit.statistics;

import java.io.Serializable;

public class StatisticColors implements Serializable {

  public static final String DEFAULT_EXCEPTION_PRIORITY_COLOR = "rgb(255, 90, 90)";
  public static final String DEFAULT_HIGH_PRIORITY_COLOR = "rgb(255, 192, 192)";
  public static final String DEFAULT_NORMAL_PRIORITY_COLOR = "rgb(150, 220, 130)";
  public static final String DEFAULT_LOW_PRIORITY_COLOR = "rgb(212, 212, 212)";

  public static final String DEFAULT_CREATED_CASE_COLOR = "rgb(150,220,130)";
  public static final String DEFAULT_RUNNING_CASE_COLOR = "rgb(75,178,197)";
  public static final String DEFAULT_DONE_CASE_COLOR = "rgb(234,162,40)";
  public static final String DEFAULT_FAILED_CASE_COLOR = "rgb(197,180,127)";
  
  public static final String DEFAULT_ELAPSED_TIME_COLOR = "rgba(54, 162, 235, 0.2)";

  private String taskExceptionPriority;
  private String taskHighPriority;
  private String taskNormalPriority;
  private String taskLowPriority;

  private String createdCase;
  private String runningCase;
  private String doneCase;
  private String failedCase;
  
  private String elapsedTime;

  public String getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(String elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

  public String getTaskExceptionPriority() {
    return taskExceptionPriority;
  }

  public void setTaskExceptionPriority(String taskExceptionPriority) {
    this.taskExceptionPriority = taskExceptionPriority;
  }

  public String getTaskHighPriority() {
    return taskHighPriority;
  }

  public void setTaskHighPriority(String taskHighPriority) {
    this.taskHighPriority = taskHighPriority;
  }

  public String getTaskNormalPriority() {
    return taskNormalPriority;
  }

  public void setTaskNormalPriority(String taskNormalPriority) {
    this.taskNormalPriority = taskNormalPriority;
  }

  public String getTaskLowPriority() {
    return taskLowPriority;
  }

  public void setTaskLowPriority(String taskLowPriority) {
    this.taskLowPriority = taskLowPriority;
  }

  public String getCreatedCase() {
    return createdCase;
  }

  public void setCreatedCase(String createdCase) {
    this.createdCase = createdCase;
  }

  public String getRunningCase() {
    return runningCase;
  }

  public void setRunningCase(String runningCase) {
    this.runningCase = runningCase;
  }

  public String getDoneCase() {
    return doneCase;
  }

  public void setDoneCase(String doneCase) {
    this.doneCase = doneCase;
  }

  public String getFailedCase() {
    return failedCase;
  }

  public void setFailedCase(String failedCase) {
    this.failedCase = failedCase;
  }

}

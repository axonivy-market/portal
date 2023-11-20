package com.axonivy.portal.response;

import ch.ivyteam.ivy.workflow.ITask;

public class TaskData {
  private long id;
  private String name;
  private String description;
  private String priority;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public TaskData() {
  }

  public TaskData(ITask task) {
    this.id = task.getId();
    this.name = task.getName();
    this.description = task.getDescription();
    this.priority = task.getPriority().name();
  }

}

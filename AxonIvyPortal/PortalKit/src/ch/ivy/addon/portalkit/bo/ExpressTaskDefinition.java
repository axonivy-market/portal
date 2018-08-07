package ch.ivy.addon.portalkit.bo;

public class ExpressTaskDefinition {

  private String id;
  private String processID;
  private String taskActor;
  private String subject;
  private String prio;
  private String description;
  private int taskCount;
  private int untilDays;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProcessID() {
    return processID;
  }

  public void setProcessID(String processID) {
    this.processID = processID;
  }

  public String getTaskActor() {
    return taskActor;
  }

  public void setTaskActor(String taskActor) {
    this.taskActor = taskActor;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getPrio() {
    return prio;
  }

  public void setPrio(String prio) {
    this.prio = prio;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getTaskCount() {
    return taskCount;
  }

  public void setTaskCount(int taskCount) {
    this.taskCount = taskCount;
  }

  public int getUntilDays() {
    return untilDays;
  }

  public void setUntilDays(int untilDays) {
    this.untilDays = untilDays;
  }


}

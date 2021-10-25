package ch.ivy.addon.portalkit.bo;

import java.util.List;

public class ExpressTaskDefinition {

  private String id;
  private String processID;
  private String type;
  private List<String> responsibles;
  private String subject;
  private String prio;
  private String description;
  private int taskPosition;
  private int untilDays;
  private String responsibleDisplayName;
  private ExpressUserEmail email;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<String> getResponsibles() {
    return responsibles;
  }

  public void setResponsibles(List<String> responsibles) {
    this.responsibles = responsibles;
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

  public int getTaskPosition() {
    return taskPosition;
  }

  public void setTaskPosition(int taskPosition) {
    this.taskPosition = taskPosition;
  }

  public int getUntilDays() {
    return untilDays;
  }

  public void setUntilDays(int untilDays) {
    this.untilDays = untilDays;
  }

  public String getResponsibleDisplayName() {
    return responsibleDisplayName;
  }

  public void setResponsibleDisplayName(String responsibleDisplayName) {
    this.responsibleDisplayName = responsibleDisplayName;
  }

  public ExpressUserEmail getEmail() {
    return email;
  }

  public void setEmail(ExpressUserEmail email) {
    this.email = email;
  }

}

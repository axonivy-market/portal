package ch.ivy.addon.portalkit.bo;

import java.util.List;

import ch.ivyteam.ivy.security.ISecurityMember;

public class ExpressTaskDefinition {

  private String id;
  private String processID;
  private List<ISecurityMember> responsibles;
  private String subject;
  private String prio;
  private String description;
  private int taskPosition;
  private int untilDays;
  private String responsibleDisplayName;

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

  public List<ISecurityMember> getResponsibles() {
    return responsibles;
  }

  public void setResponsibles(List<ISecurityMember> responsibles) {
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


}

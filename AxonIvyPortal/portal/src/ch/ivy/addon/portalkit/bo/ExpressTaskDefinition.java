package ch.ivy.addon.portalkit.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpressTaskDefinition implements Serializable {

  private static final long serialVersionUID = 6708062804413815719L;
  private String type;
  private List<String> responsibles;
  private String subject;
  private String description;
  private int taskPosition;
  private int untilDays;
  private String responsibleDisplayName;
  private ExpressUserEmail email;
  private List<ExpressFormElement> formElements;

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

  public List<ExpressFormElement> getFormElements() {
    return formElements;
  }

  public void setFormElements(List<ExpressFormElement> formElements) {
    this.formElements = formElements;
  }

}

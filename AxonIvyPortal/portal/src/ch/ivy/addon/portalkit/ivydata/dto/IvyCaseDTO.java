package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.Objects;

public class IvyCaseDTO {
  private String caseId;
  private String name;
  private String state;
  private String creator;
  private String description;
  public IvyCaseDTO() {
  }
  public IvyCaseDTO(String caseId, String name, String state, String creator, String description) {
    this.caseId = caseId;
    this.name = name;
    this.state = state;
    this.creator = creator;
    this.description = description;
  }
  public String getCaseId() {
    return caseId;
  }
  public void setCaseId(String caseId) {
    this.caseId = caseId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getCreator() {
    return creator;
  }
  public void setCreator(String creator) {
    this.creator = creator;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IvyCaseDTO that = (IvyCaseDTO) o;
    return Objects.equals(caseId, that.caseId) &&
           Objects.equals(name, that.name) &&
           Objects.equals(state, that.state) &&
           Objects.equals(creator, that.creator) &&
           Objects.equals(description, that.description);
  }
  @Override
  public int hashCode() {
    return Objects.hash(caseId, name, state, creator, description);
  }
}

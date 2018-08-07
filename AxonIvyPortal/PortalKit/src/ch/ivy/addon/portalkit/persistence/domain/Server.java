package ch.ivy.addon.portalkit.persistence.domain;

import java.util.List;

import org.boon.json.annotations.JsonIgnore;

public class Server extends BusinessEntity {

  private Boolean isOnline;
  private String path;
  private String name;
  private Boolean isNTLMAuthentication;

  @JsonIgnore
  private List<Application> applications;

  public Boolean getIsOnline() {
    return isOnline;
  }

  public void setIsOnline(Boolean isOnline) {
    this.isOnline = isOnline;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getIsNTLMAuthentication() {
    return isNTLMAuthentication;
  }

  public void setIsNTLMAuthentication(Boolean isNTLMAuthentication) {
    this.isNTLMAuthentication = isNTLMAuthentication;
  }

  public List<Application> getApplications() {
    return applications;
  }

  public void setApplications(List<Application> applications){
    this.applications = applications;
  }

  @Override
  public String toString() {
    return "Server {isOnline=" + isOnline + ", path=" + path + ", name=" + name + ", isNTLMAuthentication=" + isNTLMAuthentication
        + ", id=" + getId() + "}";
  }


}

package ch.ivy.addon.portalkit.persistence.domain;

import java.util.List;

import org.boon.json.annotations.JsonIgnore;

import ch.ivy.addon.portalkit.enums.WSAuthenticationType;

public class Server extends BusinessEntity {

  private Boolean isOnline;
  private String path;
  private String name;
  private WSAuthenticationType wsAuthenticationType;
  private String username;
  private String host;
  private String domain;
  private String password;
  @JsonIgnore
  private List<Application> applications;

  public Server() {
    wsAuthenticationType = WSAuthenticationType.HTTP_BASIC;
  }

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

  public List<Application> getApplications() {
    return applications;
  }

  public void setApplications(List<Application> applications) {
    this.applications = applications;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public WSAuthenticationType getWsAuthenticationType() {
    return wsAuthenticationType;
  }

  public void setWsAuthenticationType(WSAuthenticationType wsAuthenticationType) {
    this.wsAuthenticationType = wsAuthenticationType;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  @Override
  public String toString() {
    return String.format("Server {isOnline=%s, path=%s, name=%s, id=%d}", isOnline, path, name, getId());
  }
}

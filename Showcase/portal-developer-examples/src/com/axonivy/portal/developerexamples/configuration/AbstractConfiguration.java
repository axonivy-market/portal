package com.axonivy.portal.developerexamples.configuration;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractConfiguration {
  private String id;
  private String version;

  @JsonIgnore
  private boolean isPublic;

  public AbstractConfiguration() {
    id = UUID.randomUUID().toString().replace("-", "");
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public boolean getIsPublic() {
    return isPublic;
  }

  public void setIsPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

}

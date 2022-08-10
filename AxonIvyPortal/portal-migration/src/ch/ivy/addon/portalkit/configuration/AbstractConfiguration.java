package ch.ivy.addon.portalkit.configuration;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractConfiguration {
  private String id;

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

  public boolean getIsPublic() {
    return isPublic;
  }

  public void setIsPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

}

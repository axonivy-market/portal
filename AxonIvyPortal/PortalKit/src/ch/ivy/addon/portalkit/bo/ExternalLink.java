package ch.ivy.addon.portalkit.bo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;
import ch.ivyteam.ivy.environment.Ivy;

public class ExternalLink extends BusinessEntity{

  private String name;
  private String link;
  private Long creatorId;
  private boolean isPublic;

  @Deprecated
  private String creator;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public boolean isPublic() {
    return isPublic;
  }

  public void setPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  public Long getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Long creatorId) {
    this.creatorId = creatorId;
  }

  @Deprecated
  public String getCreator() {
    return creator;
  }

  @Deprecated
  public void setCreator(String creator) {
    this.creator = creator;
  }

  @JsonIgnore
  public boolean isAbleToEdit() {
    return this.creatorId == Ivy.session().getSessionUser().getId();
  }
  
  @Override
  public String toString() {
    return "ExternalLink {creatorId=" + creatorId + ", name=" + name + ", link=" + link + ", isPublic=" + isPublic + "}";
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(name, link, creatorId, isPublic);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ExternalLink other = (ExternalLink) obj;
    if (id == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!id.equals(other.getId())) {
      return false;
    }
    return true;
  }

}

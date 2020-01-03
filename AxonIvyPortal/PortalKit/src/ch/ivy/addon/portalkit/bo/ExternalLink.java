package ch.ivy.addon.portalkit.bo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;
import ch.ivyteam.ivy.environment.Ivy;

public class ExternalLink extends BusinessEntity{

  private String name;
  private String link;
  private String creator;
  private boolean isPublic;

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

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  @JsonIgnore
  public boolean isAbleToEdit() {
    return this.creator.equalsIgnoreCase(Ivy.session().getSessionUserName());
  }
  
  @Override
  public String toString() {
    return "ExternalLink {creator=" + creator + ", name=" + name + ", link=" + link + ", isPublic=" + isPublic + "}";
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(name, link, creator, isPublic);
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

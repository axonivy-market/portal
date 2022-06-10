package ch.ivy.addon.portalkit.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivyteam.ivy.environment.Ivy;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExternalLink extends AbstractConfiguration {

  private String name;
  private String link;
  private Long creatorId;
  private String icon;
  private String description;

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

  public Long getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Long creatorId) {
    this.creatorId = creatorId;
  }

  @JsonIgnore
  public boolean isAbleToEdit() {
    return this.creatorId == null ? true : this.creatorId == Long.valueOf(Ivy.session().getSessionUser().getSecurityMemberId());
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return String.format("ExternalLink {creatorId=%s, name=%s, link=%s, isPublic=%s, icon=%s}", creatorId, name, link,
        getIsPublic(), icon);
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}

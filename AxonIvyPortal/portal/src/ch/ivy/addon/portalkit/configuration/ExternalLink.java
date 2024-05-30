package ch.ivy.addon.portalkit.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExternalLink extends AbstractConfiguration {
  private static final String ROLE_EVERYBODY = "Everybody";

  @Deprecated(forRemoval = true, since = "10.0.16")
  private Long creatorId;
  private String name;
  private String description;
  private String link;
  private String icon;
  private String imageLocation;
  private String imageType;
  private String imageContent;
  private List<String> permissions;
  @JsonIgnore
  private List<String> defaultPermissions = new ArrayList<>();
  private String securityMemberId;
  private List<DisplayName> names;
  private List<DisplayName> descriptions;
  
  public ExternalLink() {
  }
  
  public String getName() {
    return LanguageUtils.getLocalizedName(names, name);
  }

  public void setName(String name) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(names, name);
    this.names = nameResult.names();
    this.name = nameResult.name();
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  /**
   * @deprecated use {@link #getSecurityMemberId()}
   * @return creator id
   */
  @Deprecated(forRemoval = true, since = "10.0.16")
  public Long getCreatorId() {
    return creatorId;
  }

  /**
   * @deprecated use {@link #setSecurityMemberId(String)}
   * @param creatorId
   */
  @Deprecated(forRemoval = true, since = "10.0.16")
  public void setCreatorId(Long creatorId) {
    this.creatorId = creatorId;
  }

  @JsonIgnore
  public boolean isAbleToEdit() {
    return (this.creatorId == null || PermissionUtils.isSessionUserHasAdminRole()) ? true : this.creatorId == Ivy.session().getSessionUser().getId();
  }

  public String getDescription() {
    return LanguageUtils.getLocalizedName(descriptions, description);
  }

  public void setDescription(String description) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(descriptions, description);
    this.descriptions = nameResult.names();
    this.description = nameResult.name();
  }

  public String getImageLocation() {
    return imageLocation;
  }

  public void setImageLocation(String imageLocation) {
    this.imageLocation = imageLocation;
  }

  public String getImageContent() {
    return imageContent;
  }

  public void setImageContent(String imageContent) {
    this.imageContent = imageContent;
  }

  public String getImageType() {
    return imageType;
  }

  public void setImageType(String imageType) {
    this.imageType = imageType;
  }

  @Override
  public String toString() {
    return String.format("ExternalLink {creatorId=%s, name=%s, link=%s, isPublic=%s, rolePermission=[%s], icon=%s, securityMemberId=%s}", creatorId, name, link,
        getIsPublic(), String.join(", ", permissions), icon, securityMemberId);
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public List<String> getPermissions() {
    return this.getIsPublic() ? (CollectionUtils.isEmpty(permissions) ? getDefaultPermissions() : permissions) : new ArrayList<>();
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  private List<String> getDefaultPermissions() {
    defaultPermissions.add(ROLE_EVERYBODY);
    return defaultPermissions;
  }

  public String getSecurityMemberId() {
    return securityMemberId;
  }

  public void setSecurityMemberId(String securityMemberId) {
    this.securityMemberId = securityMemberId;
  }

  public List<DisplayName> getNames() {
    if (CollectionUtils.isEmpty(names)) {
      IvyLanguage ivyLanguage = LanguageService.newInstance().findUserLanguages().getIvyLanguage();
      names = initDisplayName(ivyLanguage);
    }
    return names;
  }
  
  private List<DisplayName> initDisplayName(IvyLanguage ivyLanguage){
    List<DisplayName> result = new ArrayList<>();
    for (String language : ivyLanguage.getSupportedLanguages()) {
      DisplayName newItem = new DisplayName();
      newItem.setLocale(Locale.forLanguageTag(language));
      newItem.setValue("");
      result.add(newItem);
    }
    return result;
  }

  public void setNames(List<DisplayName> names) {
    this.names = names;
  }

  public List<DisplayName> getDescriptions() {
    if (CollectionUtils.isEmpty(descriptions)) {
      IvyLanguage ivyLanguage = LanguageService.newInstance().findUserLanguages().getIvyLanguage();
      descriptions = initDisplayName(ivyLanguage);
    }  
    return descriptions;
  }

  public void setDescriptions(List<DisplayName> descriptions) {
    this.descriptions = descriptions;
  }
}

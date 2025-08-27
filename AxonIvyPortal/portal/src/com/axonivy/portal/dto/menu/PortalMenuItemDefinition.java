package com.axonivy.portal.dto.menu;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.enums.MenuKind;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ch.addon.portal.generic.menu.PortalMenuItem;
import ch.addon.portal.generic.menu.PortalMenuItem.PortalMenuBuilder;
import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = StandardMenuItemDefinition.class, name = "standard"),
    @Type(value = DashboardMenuItemDefinition.class, name = "main_dashboard"),
    @Type(value = ExternalLinkMenuItemDefinition.class, name = "external_link"),
    @Type(value = CustomMenuItemDefinition.class, name = "custom") })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class PortalMenuItemDefinition extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 3039118169604897617L;
  protected static final String DEFAULT_LINK = "#";

  private List<DisplayName> titles;
  private String icon;
  private String description;
  private List<String> permissions;
  private String url;
  private Integer index;
  private MenuSource source;

  @JsonIgnore
  private String displayTitle;

  @JsonIgnore
  private String onClick;

  @JsonIgnore
  protected String getCurrentLanguage() {
    return UserUtils.getUserLanguage();
  }

  @JsonIgnore
  private Long workingtaskId;

  public abstract MenuKind getType();

  @JsonIgnore
  private List<SecurityMemberDTO> permissionDTOs;

  private String displayedPermission;

  public List<DisplayName> getTitles() {
    return titles;
  }

  public void setTitles(List<DisplayName> titles) {
    this.titles = titles;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  @JsonIgnore
  public List<SecurityMemberDTO> getPermissionDTOs() {
    return permissionDTOs;
  }

  @JsonIgnore
  public void setPermissionDTOs(List<SecurityMemberDTO> permissionDTOs) {
    this.permissionDTOs = permissionDTOs;
  }

  public PortalMenuItem convertToPortalMenuItem() {
    if (!hasPermission()) {
      return null;
    }
    String displayTitle = titles.stream()
        .filter(t -> t.getLocale().toLanguageTag().contentEquals(LanguageService.getInstance().getUserLanguage()))
        .findFirst().orElseGet(() -> titles.get(0)).getValue();

    if (workingtaskId == null) {
      return new PortalMenuBuilder(displayTitle, getType(), false).icon(getIconClass()).url(url).build();
    }

    return new PortalMenuBuilder(displayTitle, getType(), true).icon(getIconClass()).url(url)
        .workingTaskId(workingtaskId).build();

  }

  @JsonIgnore
  public String getIconClass() {
    if (StringUtils.isBlank(this.icon)) {
      return PortalMenuItem.DEFAULT_DASHBOARD_ICON;
    }
    return (this.icon.startsWith("fa") ? "fa " : "si ") + this.icon;
  }

  @JsonIgnore
  protected boolean hasPermission() {
    if (permissions == null) {
      return false;
    }
    return !permissions.stream().noneMatch(p -> isSessionUserHasPermisson(p));
  }

  @JsonIgnore
  private boolean isSessionUserHasPermisson(String permission) {
    return Strings.CS.startsWith(permission, "#")
        ? Strings.CS.equals(Ivy.session().getSessionUser().getMemberName(), permission)
        : PermissionUtils.doesSessionUserHaveRole(permission);
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Long getWorkingtaskId() {
    return workingtaskId;
  }

  public void setWorkingtaskId(Long workingtaskId) {
    this.workingtaskId = workingtaskId;
  }

  protected void setOnClick(String onClick) {
    this.onClick = onClick;
  }

  public String getDisplayedPermission() {
    return displayedPermission;
  }

  public void setDisplayedPermission(String displayedPermission) {
    this.displayedPermission = displayedPermission;
  }

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public MenuSource getSource() {
    return source;
  }

  public void setSource(MenuSource source) {
    this.source = source;
  }

  public String getDisplayTitle() {
    return displayTitle;
  }

  public void setDisplayTitle(String displayTitle) {
    this.displayTitle = displayTitle;
  }

  public enum MenuSource {
    CALLABLE, DASHBOARD, CUSTOM_MENU_CONFIGURATION, THIRD_PARTY_APP_CONFIGURATION;
  }
}
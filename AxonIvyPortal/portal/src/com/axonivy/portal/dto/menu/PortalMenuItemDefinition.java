package com.axonivy.portal.dto.menu;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.Strings;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.util.MenuUtils;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;

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
  private String displayTitle;
  private String onClick;
  private Long workingtaskId;
  private List<SecurityMemberDTO> permissionDTOs;
  private boolean isIncludedIconFamily;
  private String displayedPermission;

  protected String getCurrentLanguage() {
    return UserUtils.getUserLanguage();
  }

  public abstract MenuKind getType();

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

  public List<SecurityMemberDTO> getPermissionDTOs() {
    return permissionDTOs;
  }

  public void setPermissionDTOs(List<SecurityMemberDTO> permissionDTOs) {
    this.permissionDTOs = permissionDTOs;
  }

  public String getIconClass() {
    return MenuUtils.buildIconClass(this.icon);
  }

  protected boolean hasPermission() {
    if (permissions == null) {
      return false;
    }
    return permissions.stream().anyMatch(this::isSessionUserHasPermisson);
  }

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

  public String getOnClick() {
    return onClick;
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

  public boolean isIncludedIconFamily() {
    return isIncludedIconFamily;
  }

  public void setIncludedIconFamily(boolean isIncludedIconFamily) {
    this.isIncludedIconFamily = isIncludedIconFamily;
  }

  @Override
  public int hashCode() {
    return getId() == null ? 0 : getId().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    PortalMenuItemDefinition other = (PortalMenuItemDefinition) obj;
    return getId() == null ? other.getId() == null : getId().equals(other.getId());
  }
}

package ch.ivy.addon.portalkit.configuration;

import java.util.List;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Application extends AbstractConfiguration {

  private String displayName;
  private String menuIcon;
  private Integer menuOrdinal;
  private String name;
  private String link;
  private List<String> permissions;
  private List<SecurityMemberDTO> permissionDTOs;
  private String displayedPermission;

  public Application() {
    setIsPublic(true);
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getMenuIcon() {
    return menuIcon;
  }

  public void setMenuIcon(String menuIcon) {
    this.menuIcon = menuIcon;
  }

  public Integer getMenuOrdinal() {
    return menuOrdinal;
  }

  public void setMenuOrdinal(Integer menuOrdinal) {
    this.menuOrdinal = menuOrdinal;
  }

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

  public void setPermissionDTOs(List<SecurityMemberDTO> permissionDTOs) {
    this.permissionDTOs = permissionDTOs;
  }

  @JsonIgnore
  public String getDisplayedPermission() {
    return displayedPermission;
  }

  public void setDisplayedPermission(String displayedPermission) {
    this.displayedPermission = displayedPermission;
  }
  @Override
  public String toString() {
    return String.format(
        "Application {displayName=%s, menuIcon=%s, menuOrdinal=%s, name=%s, link=%s, permissions=%s, id=%s}", 
        displayName, menuIcon, menuOrdinal, name, link, permissions, getId());
  }
}

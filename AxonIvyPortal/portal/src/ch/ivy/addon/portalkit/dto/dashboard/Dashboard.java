package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ch.ivy.addon.portalkit.DashboardDisplayType;
import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Dashboard extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 4580715578128184706L;
  private String templateId;
  @JsonProperty(access = Access.WRITE_ONLY)
  private String title;
  private List<DisplayName> titles;
  private String icon;
  private String description;
  private List<DashboardWidget> widgets;
  private List<String> permissions;
  @JsonIgnore
  private List<SecurityMemberDTO> permissionDTOs;
  @JsonIgnore
  private String displayedPermission;
  private DashboardDisplayType dashboardDisplayType;
  
  @JsonIgnore
  private Boolean isResponsive;

  public Dashboard() {
    // Set default values
    dashboardDisplayType = DashboardDisplayType.SUB_MENU;
    isResponsive = false;
  }

  public Dashboard(Dashboard dashboard) {
    setId(dashboard.getId());
    setIsPublic(dashboard.getIsPublic());
    setVersion(dashboard.getVersion());
    templateId = dashboard.getTemplateId();
    title = dashboard.title;
    titles = dashboard.titles;
    icon = dashboard.icon;
    description = dashboard.description;
    widgets = dashboard.widgets;
    permissions = dashboard.permissions;
    permissionDTOs = dashboard.permissionDTOs;
    displayedPermission = dashboard.displayedPermission;
    dashboardDisplayType = dashboard.dashboardDisplayType;
    isResponsive = dashboard.isResponsive;
  }
  
  public String getTitle() {
    return LanguageUtils.getLocalizedName(titles, title);
  }

  public void setTitle(String title) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(titles, title);
    this.titles = nameResult.names();
    this.title = nameResult.name();
  }

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

  @JsonIgnore
  public String getIconClass() {
    if (StringUtils.isBlank(this.icon)) {
      return StringUtils.EMPTY;
    }
    return (this.icon.startsWith("fa") ? "fa " : "si ") + this.icon;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<DashboardWidget> getWidgets() {
    return widgets;
  }

  public void setWidgets(List<DashboardWidget> widgets) {
    this.widgets = widgets;
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

  public String getDisplayedPermission() {
    return displayedPermission;
  }

  public void setDisplayedPermission(String displayedPermission) {
    this.displayedPermission = displayedPermission;
  }

  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  @JsonIgnore
  public Boolean getIsResponsive() {
    return isResponsive;
  }

  @JsonIgnore
  public void setIsResponsive(Boolean isResponsive) {
    this.isResponsive = isResponsive;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    return result;
  }
  
  public DashboardDisplayType getDashboardDisplayType() {
    return this.dashboardDisplayType;
  }
  
  public void setDashboardDisplayType(DashboardDisplayType dashboardDisplayType) {
    this.dashboardDisplayType = dashboardDisplayType;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Dashboard other = (Dashboard) obj;
    if (getId() == null) {
      if (other.getId() != null)
        return false;
    } else if (!getId().equals(other.getId()))
      return false;
    return true;
  }
}

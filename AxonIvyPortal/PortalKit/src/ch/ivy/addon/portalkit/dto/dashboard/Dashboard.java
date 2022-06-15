package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivyteam.ivy.environment.Ivy;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Dashboard extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 4580715578128184706L;
  private String templateId;
  private String title;
  private String description;
  private List<DashboardWidget> widgets;
  private List<String> permissions;
  @JsonIgnore
  private List<SecurityMemberDTO> permissionDTOs;
  @JsonIgnore
  private String displayedPermission;

  public Dashboard() {}

  public Dashboard(Dashboard dashboard) {
    setId(dashboard.getId());
    setTemplateId(dashboard.getTemplateId());
    setIsPublic(dashboard.getIsPublic());
    title = dashboard.title;
    description = dashboard.description;
    widgets = dashboard.widgets;
    permissions = dashboard.permissions;
    permissionDTOs = dashboard.permissionDTOs;
    displayedPermission = dashboard.displayedPermission;
  }

  public Dashboard(String id, String title, List<DashboardWidget> widgets) {
    this.setId(id);
    this.title = title;
    this.widgets = widgets;
  }
  
  public String getTitle() {
    if (StringUtils.startsWithIgnoreCase(title, DashboardConfigurationPrefix.CMS)) {
      return Ivy.cms().co(StringUtils.removeStart(title, DashboardConfigurationPrefix.CMS));
    }
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    return result;
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

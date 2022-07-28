package ch.ivy.addon.portal.generic.bean;

import static ch.ivy.addon.portalkit.constant.PortalConstants.MAX_USERS_IN_AUTOCOMPLETE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.component.util.RoleUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DashboardModificationBean extends DashboardBean implements Serializable {

  private static final long serialVersionUID = 1L;
  protected boolean isPublicDashboard;
  protected List<String> selectedDashboardPermissions;

  public void initConfigration(boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
    this.selectedDashboardPermissions = new ArrayList<>();
    collectDashboardsForManagement();
  }

  private void collectDashboardsForManagement() {
    this.dashboards = new ArrayList<>();
    String dashboardInUserProperty = readDashboardBySessionUser();
    if (isPublicDashboard) {
      this.dashboards = DashboardUtils.getPublicDashboards();
    } else if (StringUtils.isNoneEmpty(dashboardInUserProperty)) {
      List<Dashboard> myDashboards = getVisibleDashboards(dashboardInUserProperty);
      this.dashboards.addAll(myDashboards);
    }
  }

  public void openDashboardDetailDialog(Dashboard dashboard) {
    this.selectedDashboardPermissions = new ArrayList<>();
    this.selectedDashboard = dashboard;
    dashboard.setPermissionDTOs(new ArrayList<>());
    Map<String, SecurityMemberDTO> nameToSecurityMemberDTO = SecurityMemberUtils.findSecurityMembers("", 0, MAX_USERS_IN_AUTOCOMPLETE)
            .stream().filter(securityMember -> !securityMember.isUser())
            .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, v -> v));
    List<String> permissions = dashboard.getPermissions();
    if (CollectionUtils.isNotEmpty(permissions)) {
      var responsibles = permissions.stream().filter(Objects::nonNull).distinct()
          .filter(permission -> !permission.startsWith("#")).map(permission -> nameToSecurityMemberDTO.get(permission))
          .filter(Objects::nonNull).collect(Collectors.toSet());
      this.selectedDashboardPermissions = responsibles.stream().map(SecurityMemberDTO::getName).collect(Collectors.toList());
      dashboard.setPermissionDTOs(new ArrayList<>(responsibles));
    }
  }

  public List<SecurityMemberDTO> completePermissions(String query) {
    return RoleUtils.findRoles(null, selectedDashboardPermissions, query).stream()
        .map(SecurityMemberDTOMapper::mapFromRoleDTO).collect(Collectors.toList());
  }

  public void saveDashboardDetail() {
    List<SecurityMemberDTO> responsibles = this.selectedDashboard.getPermissionDTOs();
    List<String> permissions = new ArrayList<>();
    String displayedPermission = "";
    if (CollectionUtils.isNotEmpty(responsibles)) {
      Collection<SecurityMemberDTO> distinctPermissionDTOs = responsibles.stream()
          .collect(Collectors
              .toMap(SecurityMemberDTO::getMemberName, responsible -> responsible, (responsible1, responsible2) -> responsible1)).values();
      responsibles.clear();
      responsibles.addAll(distinctPermissionDTOs);
      displayedPermission = responsibles.stream().map(SecurityMemberDTO::getDisplayName).collect(Collectors.joining(", "));
      permissions = responsibles.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    }
    this.selectedDashboard.setDisplayedPermission(displayedPermission);
    this.selectedDashboard.setPermissions(permissions);
    if (!this.dashboards.contains(selectedDashboard)) {
      this.dashboards.add(selectedDashboard);
    }
    saveDashboards(new ArrayList<>(this.dashboards));
  }

  public void removeDashboard() {
    this.dashboards.remove(selectedDashboard);
    saveDashboards(new ArrayList<>(this.dashboards));
  }

  private void saveDashboards(List<Dashboard> dashboards) {
    String dashboardJson = BusinessEntityConverter.entityToJsonValue(dashboards);
    if (isPublicDashboard) {
      Ivy.var().set(PortalVariable.DASHBOARD.key, dashboardJson);
    } else {
      currentUser().setProperty(PortalVariable.DASHBOARD.key, dashboardJson);
    }
  }

  private List<Dashboard> getVisibleDashboards(String dashboardJson) {
    if (isPublicDashboard) {
      return DashboardUtils.jsonToDashboards(dashboardJson);
    } else {
      return DashboardUtils.getVisibleDashboards(dashboardJson);
    }
  }

  public void navigateToDashboardDetailsPage(String dashboardId) {
    PortalNavigator.navigateToDashboardDetailsPage(dashboardId, isPublicDashboard);
  }

  public void onSelectedDeleteDashboard(Dashboard dashboard) {
    this.selectedDashboard = dashboard;
  }

  public void onSelectPermissionForDashboard(SelectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    this.selectedDashboardPermissions.add(selectedItem.getName());
  }

  public void onUnSelectPermissionForDashboard(UnselectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    this.selectedDashboardPermissions.remove(selectedItem.getName());
  }

  public void createDashboard() {
    collectDashboardsForManagement();
    saveDashboardDetail();
    navigateToDashboardDetailsPage(this.selectedDashboard.getId());
  }

  public boolean isPublicDashboard() {
    return isPublicDashboard;
  }

  public void setPublicDashboard(boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
  }

  public String generateDashboardPermisisonForDisplay(Dashboard dashboard) {
    return String.join(", ", dashboard.getPermissions());
  }
}

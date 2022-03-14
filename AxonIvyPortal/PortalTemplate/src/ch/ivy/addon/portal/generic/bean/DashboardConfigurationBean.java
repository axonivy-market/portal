package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DashboardConfigurationBean extends DashboardBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private boolean isPublicDashboard;
  private String title;
  private Dashboard selectedEditingDashboard;
  private List<Dashboard> editingDashboards;

  @PostConstruct
  public void initConfigration() {
    isPublicDashboard = Attrs.currentContext().getAttribute("#{data.isPublicDashboard}", Boolean.class);
    updateTitle();
    collectDashboardsForManagement();
  }

  public String getBreadcrumb() {
    return
        isPublicDashboard ? BreadCrumbKind.EDIT_PUBLIC_DASHBOARD.name() : BreadCrumbKind.EDIT_PRIVATE_DASHBOARD.name();
  }

  private void updateTitle() {
    String cmsUri = isPublicDashboard ? "/ch.ivy.addon.portalkit.ui.jsf/dashboard/editPublicDashboard" : "/ch.ivy.addon.portalkit.ui.jsf/dashboard/editPrivateDashboard";
    title = Ivy.cms().co(cmsUri);
  }

  private void collectDashboardsForManagement() {
    setEditingDashboards(new ArrayList<>());
    String dashboardInUserProperty = readDashboardBySessionUser();
    try {
      if (isPublicDashboard) {
        setEditingDashboards(getVisiblePublicDashboards());
      } else if (StringUtils.isNoneEmpty(dashboardInUserProperty)) {
        List<Dashboard> myDashboards = getVisibleDashboards(dashboardInUserProperty);
        getEditingDashboards().addAll(myDashboards);
      }
    } catch (PortalException e) {
      // If errors like parsing JSON errors, ignore them
      Ivy.log().error(e);
    }
  }

  public void openDashboardDetail(Dashboard dashboard) {
    setSelectedEditingDashboard(dashboard);
    List<SecurityMemberDTO> securityMembers =
        SecurityMemberUtils.findSecurityMembers("", 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
    Map<String, SecurityMemberDTO> nameToSecurityMemberDTO =
        securityMembers.stream().filter(securityMember -> !securityMember.isUser())
            .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, v -> v));
    List<String> permissions = dashboard.getPermissions();
    if (CollectionUtils.isNotEmpty(permissions)) {
      List<SecurityMemberDTO> responsibles = permissions.stream().filter(Objects::nonNull).distinct()
          .filter(permission -> !permission.startsWith("#")).map(permission -> nameToSecurityMemberDTO.get(permission))
          .filter(Objects::nonNull).collect(Collectors.toList());
      dashboard.setPermissionDTOs(responsibles);
    } else {
      dashboard.setPermissionDTOs(new ArrayList<>());
    }
  }

  public List<SecurityMemberDTO> completePermissions(String query) {
    List<SecurityMemberDTO> securityMembers =
        SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
    List<SecurityMemberDTO> roles =
        securityMembers.stream().filter(securityMember -> !securityMember.isUser()).collect(Collectors.toList());
    return roles;
  }

  public void saveDashboardDetail() {
    List<SecurityMemberDTO> responsibles = selectedEditingDashboard.getPermissionDTOs();
    List<String> permissions;
    String displayedPermission;
    if (CollectionUtils.isNotEmpty(responsibles)) {
      Collection<SecurityMemberDTO> distinctPermissionDTOs =
          responsibles.stream().collect(Collectors.toMap(SecurityMemberDTO::getMemberName, responsible -> responsible,
              (responsible1, responsible2) -> responsible1)).values();
      responsibles.clear();
      responsibles.addAll(distinctPermissionDTOs);
      displayedPermission =
          responsibles.stream().map(SecurityMemberDTO::getDisplayName).collect(Collectors.joining(", "));
      permissions = responsibles.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    } else {
      displayedPermission = "";
      permissions = new ArrayList<>();
    }

    selectedEditingDashboard.setDisplayedPermission(displayedPermission);
    selectedEditingDashboard.setPermissions(permissions);
    saveDashboards(editingDashboards);
  }

  public void removeDashboard() {
    editingDashboards.remove(selectedEditingDashboard);
    saveDashboards(editingDashboards);
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Dashboard getSelectedEditingDashboard() {
    return selectedEditingDashboard;
  }

  public void setSelectedEditingDashboard(Dashboard selectedEditingDashboard) {
    this.selectedEditingDashboard = selectedEditingDashboard;
  }

  public List<Dashboard> getEditingDashboards() {
    return editingDashboards;
  }

  public void setEditingDashboards(List<Dashboard> editingDashboards) {
    this.editingDashboards = editingDashboards;
  }

  public void addTempPrivateDashboard() {
    Dashboard privateDashboard = new Dashboard();
    privateDashboard.setIsPublic(false);
    privateDashboard.setId(DashboardUtils.generateId());
    privateDashboard.setWidgets(new ArrayList<>());
    editingDashboards.add(privateDashboard);
    DashboardService.getInstance().save(privateDashboard);
  }
}

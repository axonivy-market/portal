package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.FacesMessageUtils;
import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.util.UploadDocumentUtils;
import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portal.generic.bean.DashboardModificationBean;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.NavigationWidgetUtils;
import ch.ivy.addon.portalkit.util.RoleUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;

@ViewScoped
@ManagedBean
public class DashboardImportBean extends DashboardModificationBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private boolean isLoaded = false;
  private UploadedFile importFile;
  private Boolean isError = false;
  private String fileSize;
  private IRole everybodyRole;
  private int index = 0;
  private int activeIndex;
  private Map<String, String> oldToNewIdMap = new HashMap<>();

  @Override
  @PostConstruct
  public void init() {
    everybodyRole = Ivy.security().roles().topLevel();
  }

  public void tabInit() {
    if (CollectionUtils.isNotEmpty(this.importedDashboards)) {
      this.selectedDashboard = importedDashboards.get(0);
      index = 0;
    }
  }

  public void onTabChange(Dashboard dashboard) {
    this.selectedDashboard = dashboard;
  }

  public void importDashboard(boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
    resetDialog();
  }

  public void loadImportedFile(FileUploadEvent event) {
    resetDialog();
    this.selectedDashboardPermissions = new ArrayList<>();
    importFile = event.getFile();
    String validateStr = UploadDocumentUtils.validateUploadedFile(importFile);
    if (StringUtils.isNotEmpty(validateStr)) {
      isError = true;
      displayedMessage(validateStr);
      return;
    }
    try {
      importedDashboards = DashboardUtils.convertDashboardsFromUploadFileToLatestVersion(importFile.getInputStream());
    } catch (Exception e) {
      isError = true;
      displayedMessage(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileCouldNotParse"));
      Ivy.log().error(e);
      return;
    }

    // Build oldId → newId map so navigation widget references can be remapped after
    // import
    oldToNewIdMap = new HashMap<>();

    importedDashboards.stream().forEach(dashboard -> {
      selectedDashboard = dashboard;
      String newId = DashboardUtils.generateId();
      // Use existing oldId if present (exported before this change), otherwise use
      // current id
      String originalId = StringUtils.isNotBlank(dashboard.getOldId()) ? dashboard.getOldId() : dashboard.getId();
      if (originalId != null) {
        oldToNewIdMap.put(originalId, newId);
        selectedDashboard.setOldId(originalId);
      }
      selectedDashboard.setIsPublic(isPublicDashboard);
      selectedDashboard.setId(newId);
      selectedDashboard.setPermissionDTOs(new ArrayList<>());
      findAndSetPermissions();
      if (!isPublicDashboard) {
        selectedDashboard.setDashboardDisplayType(DashboardDisplayType.SUB_MENU);
      }
    });

    Map<String, String> portalOldIdToCurrentIdMap = buildPortalOldIdToCurrentIdMap();
    importedDashboards
        .forEach(dashboard -> remapNavigationWidgetTargetIds(dashboard, oldToNewIdMap, portalOldIdToCurrentIdMap));

    fileSize = FileUtils.byteCountToDisplaySize(importFile.getSize());
    isLoaded = true;
  }

  private void findAndSetPermissions() {
    List<String> permissions = selectedDashboard.getPermissions();
    if (CollectionUtils.isNotEmpty(permissions)) {
      List<IRole> iRoles = new ArrayList<>();
      for (String permission : permissions) {
          IRole iRole = RoleUtils.findRole(permission);
          if (iRole == null) {
            Ivy.log().warn("Role [{0}] could not be found. Will be replaced by role {1}.", permission,
                everybodyRole.getName());
            iRole = everybodyRole;
          }
          iRoles.add(iRole);
      }
      var distinctRoles = iRoles.stream().distinct().collect(Collectors.toList());
      List<SecurityMemberDTO> securityMemberDTOs = SecurityMemberUtils.convertIRoleToSecurityMemberDTO(distinctRoles);
      selectedDashboardPermissions =
          securityMemberDTOs.stream().map(SecurityMemberDTO::getName).collect(Collectors.toList());
      selectedDashboard.setPermissionDTOs(securityMemberDTOs);
    }
  }

  @Override
  public void createDashboard() {
    if (CollectionUtils.isNotEmpty(importedDashboards)) {
      for (Dashboard dashboard : importedDashboards) {
        selectedDashboard = dashboard;
        if (CollectionUtils.isNotEmpty(this.selectedDashboard.getWidgets())) {
          for (DashboardWidget widget : this.selectedDashboard.getWidgets()) {
            if (widget instanceof WelcomeDashboardWidget) {
              WelcomeDashboardWidget welcomeWidget = (WelcomeDashboardWidget) widget;
              WelcomeWidgetUtils.writeWelcomeWidgetImage(welcomeWidget);
            } else if (widget instanceof NavigationDashboardWidget) {
              NavigationDashboardWidget navWid = (NavigationDashboardWidget) widget;
              NavigationWidgetUtils.writeNavigateWidgetImage(navWid);
            }
          }
        }
        createDashboards();
      }
      fixExistingNavigationWidgetReferences();
      if (importedDashboards.size() > 1) {
        if (this.isPublicDashboard) {
          navigateToPublicDashBoardListPage();
        } else {
          navigateToPrivateDashboardPage();
        }
      } else {
        navigateToDashboardDetailsPage(importedDashboards.get(0).getId());
      }
    }
  }

  private void remapNavigationWidgetTargetIds(Dashboard dashboard, Map<String, String> oldToNewIdMap,
      Map<String, String> portalOldIdToCurrentIdMap) {
    Optional.ofNullable(dashboard.getWidgets()).orElse(new ArrayList<>()).stream()
        .filter(w -> w instanceof NavigationDashboardWidget)
        .map(w -> (NavigationDashboardWidget) w)
        .forEach(navWidget -> {
          String targetId = navWidget.getTargetDashboardId();
          if (StringUtils.isNotBlank(targetId)) {
            if (oldToNewIdMap.containsKey(targetId)) {
              // Same-file import: remap to the new ID assigned in this import
              navWidget.setTargetDashboardId(oldToNewIdMap.get(targetId));
            } else if (portalOldIdToCurrentIdMap.containsKey(targetId)) {
              // Cross-file import: target was imported in a prior session; resolve via oldId
              navWidget.setTargetDashboardId(portalOldIdToCurrentIdMap.get(targetId));
            } else {
              Ivy.log().warn(
                  "Navigation widget ''{0}'' references dashboard ID ''{1}'' which is not in the import file and does not exist in this portal.",
                  navWidget.getName(), targetId);
              displayWarningMessage(
                  Ivy.cms().co(
                      "/Dialogs/com/axonivy/portal/dashboard/component/DashboardImportDetails/NavigationWidgetMissingTarget",
                      List.of(navWidget.getName())));
            }
          }
        });
  }

  /**
   * Builds a map from every known portal dashboard's old or current ID → its
   * current ID.
   * This allows navigation widget targets to be resolved even when the target
   * dashboard
   * was imported in a prior separate import operation.
   */
  private Map<String, String> buildPortalOldIdToCurrentIdMap() {
    Map<String, String> map = new HashMap<>();
    List<Dashboard> allDashboards = new ArrayList<>(DashboardUtils.getPublicDashboards());
    allDashboards.addAll(DashboardUtils.getPrivateDashboards());
    for (Dashboard d : allDashboards) {
      if (d.getId() != null) {
        map.put(d.getId(), d.getId());
      }
      if (StringUtils.isNotBlank(d.getOldId())) {
        map.put(d.getOldId(), d.getId());
      }
    }
    return map;
  }

  private void displayedMessage(String validateMessage) {
    FacesContext.getCurrentInstance().addMessage("import-dashboard-form:import-dashboard-dialog-message",
        FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR, validateMessage, null));
  }

  private void displayWarningMessage(String message) {
    FacesContext.getCurrentInstance().addMessage("import-dashboard-form:import-dashboard-dialog-message",
        FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_WARN, message, null));
  }

  private void resetDialog() {
    importedDashboards = new ArrayList<>();
    selectedDashboard = new Dashboard();
    this.selectedDashboard.setTitles(new ArrayList<>());
    this.selectedDashboardPermissions = new ArrayList<>();
    fileSize = null;
    importFile = null;
    isLoaded = isError = false;
    index = 0;
    activeIndex = 0;
    oldToNewIdMap = new HashMap<>();
  }

  public boolean isLoaded() {
    return isLoaded;
  }

  public void setLoaded(boolean isLoaded) {
    this.isLoaded = isLoaded;
  }

  public UploadedFile getImportFile() {
    return importFile;
  }

  public void setImportFile(UploadedFile importFile) {
    this.importFile = importFile;
  }

  public Boolean getIsError() {
    return isError;
  }

  public void setIsError(Boolean isError) {
    this.isError = isError;
  }

  public String getFileSize() {
    return fileSize;
  }

  public void setFileSize(String fileSize) {
    this.fileSize = fileSize;
  }

  public int getDashBoardIndex() {
    if (CollectionUtils.isNotEmpty(importedDashboards) && index >= importedDashboards.size()) {
      index = 0;
    }
    index++;
    return index;
  }

  public int getActiveIndex() {
    return activeIndex;
  }

  public void setActiveIndex(int activeIndex) {
    this.activeIndex = activeIndex;
  }

  /**
   * After all imported dashboards are saved, re-scan every portal dashboard's
   * navigation widgets and remap any targetDashboardId that matches an oldId
   * from this import. This fixes the case where the navigation widget dashboard
   * was imported before the target dashboard.
   */
  private void fixExistingNavigationWidgetReferences() {
    if (oldToNewIdMap.isEmpty()) {
      return;
    }
    List<Dashboard> publicDashboards = DashboardUtils.getPublicDashboards();
    if (fixNavigationWidgets(publicDashboards)) {
      boolean prev = isPublicDashboard;
      isPublicDashboard = true;
      saveDashboards(publicDashboards);
      isPublicDashboard = prev;
    }
    List<Dashboard> privateDashboards = DashboardUtils.getPrivateDashboards();
    if (fixNavigationWidgets(privateDashboards)) {
      boolean prev = isPublicDashboard;
      isPublicDashboard = false;
      saveDashboards(privateDashboards);
      isPublicDashboard = prev;
    }
  }

  private boolean fixNavigationWidgets(List<Dashboard> dashboards) {
    boolean anyFixed = false;
    for (Dashboard dashboard : dashboards) {
      if (CollectionUtils.isEmpty(dashboard.getWidgets())) {
        continue;
      }
      for (DashboardWidget widget : dashboard.getWidgets()) {
        if (!(widget instanceof NavigationDashboardWidget)) {
          continue;
        }
        NavigationDashboardWidget navWidget = (NavigationDashboardWidget) widget;
        String targetId = navWidget.getTargetDashboardId();
        if (StringUtils.isNotBlank(targetId) && oldToNewIdMap.containsKey(targetId)) {
          navWidget.setTargetDashboardId(oldToNewIdMap.get(targetId));
          anyFixed = true;
        }
      }
    }
    return anyFixed;
  }

}

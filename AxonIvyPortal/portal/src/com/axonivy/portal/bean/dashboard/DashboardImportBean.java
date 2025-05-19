package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import com.axonivy.portal.util.UploadDocumentUtils;
import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portal.generic.bean.DashboardModificationBean;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.util.DashboardUtils;
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

    importedDashboards.stream().forEach(dashboard -> {
      selectedDashboard = dashboard;
      selectedDashboard.setIsPublic(isPublicDashboard);
      selectedDashboard.setId(DashboardUtils.generateId());
      selectedDashboard.setPermissionDTOs(new ArrayList<>());
      findAndSetPermissions();
      if (!isPublicDashboard) {
        selectedDashboard.setDashboardDisplayType(DashboardDisplayType.SUB_MENU);
      }
    });


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
            }
          }
        }
        createDashboards();
      }
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

  private void displayedMessage(String validateMessage) {
    FacesContext.getCurrentInstance().addMessage("import-dashboard-form:import-dashboard-dialog-message",
        FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR, validateMessage, null));
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

}

package com.axonivy.portal.bean.dashboard;

import static ch.ivy.addon.portalkit.constant.PortalConstants.MAX_USERS_IN_AUTOCOMPLETE;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.axonivy.portal.util.UploadDocumentUtils;
import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portal.generic.bean.DashboardModificationBean;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;

@ViewScoped
@ManagedBean
public class DashboardImportBean extends DashboardModificationBean implements Serializable{
  private static final long serialVersionUID = 1L;
  private boolean isLoaded = false;
  private UploadedFile importFile; 
  private FacesMessage validateMessage;
  private Boolean isError = false;
  private String fileSize;
  
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
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, validateStr, null);
      displayedMessage();
      return;
    }
    try {
      selectedDashboard = BusinessEntityConverter.inputStreamToEntity(importFile.getInputStream(), Dashboard.class);
    } catch (IOException e) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileCouldNotParse"), null);
      displayedMessage();
      Ivy.log().error(e);
      return;
    }
    selectedDashboard.setIsPublic(isPublicDashboard);
    selectedDashboard.setId(DashboardUtils.generateId());
    selectedDashboard.setPermissionDTOs(new ArrayList<>());
    Map<String, SecurityMemberDTO> nameToSecurityMemberDTO = SecurityMemberUtils.findSecurityMembers("", 0, MAX_USERS_IN_AUTOCOMPLETE)
            .stream().filter(securityMember -> !securityMember.isUser())
            .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, v -> v));
    List<String> permissions = selectedDashboard.getPermissions();
    if (CollectionUtils.isNotEmpty(permissions)) {
      List<SecurityMemberDTO> securityMemberDTOs = new ArrayList<>();
      for(String permission : permissions) {
        if (permission != null && !permission.startsWith("#")) {
          var dto = nameToSecurityMemberDTO.get(permission);
          if (dto == null) {
            Ivy.log().warn("Role [{0}] could not be found. Will be replaced by role {1}.", permission, ISecurityConstants.TOP_LEVEL_ROLE_NAME);
            dto = nameToSecurityMemberDTO.get(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
          }
          securityMemberDTOs.add(dto);
        }
      }
      var responsibles = securityMemberDTOs.stream().distinct().collect(Collectors.toSet());
      selectedDashboardPermissions = responsibles.stream().map(SecurityMemberDTO::getName).collect(Collectors.toList());
      selectedDashboard.setPermissionDTOs(new ArrayList<>(responsibles));
    }
    fileSize = FileUtils.byteCountToDisplaySize(importFile.getSize());
    isLoaded = true;
  }
  
  @Override
  public void createDashboard() {
    if (CollectionUtils.isNotEmpty(this.selectedDashboard.getWidgets())) {
      for (DashboardWidget widget : this.selectedDashboard.getWidgets()) {
        if (widget instanceof WelcomeDashboardWidget) {
          WelcomeDashboardWidget welcomeWidget = (WelcomeDashboardWidget) widget;
          WelcomeWidgetUtils.writeWelcomeWidgetImage(welcomeWidget);
        }
      }
    }
    super.createDashboard();
  }
  
  private void displayedMessage() {
    FacesContext.getCurrentInstance().addMessage("import-dashboard-form:import-dashboard-dialog-message", validateMessage);
  }

  private void resetDialog() {
    selectedDashboard = new Dashboard();
    this.selectedDashboardPermissions = new ArrayList<>();
    fileSize = null;
    importFile = null;
    isLoaded = isError = false;
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

  public FacesMessage getValidateMessage() {
    return validateMessage;
  }
  
  public void setValidateMessage(FacesMessage validateMessage) {
    this.validateMessage = validateMessage;
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
}

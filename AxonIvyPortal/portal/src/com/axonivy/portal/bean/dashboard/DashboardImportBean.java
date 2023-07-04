package com.axonivy.portal.bean.dashboard;

import static ch.ivy.addon.portalkit.constant.PortalConstants.MAX_USERS_IN_AUTOCOMPLETE;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.WELCOME;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portal.generic.bean.DashboardModificationBean;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DashboardImportBean extends DashboardModificationBean implements Serializable{
  private static final long serialVersionUID = 1L;
  private boolean isLoaded = false;
  private UploadedFile importFile; 
  private FacesMessage validateMessage;
  private Boolean isError = false;
  private String fileSize;
  private static final String ROLE_EVERYBODY = "Everybody";
  
  public void importDashboard(boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
    resetDialog();
  }
  
  public void loadImportedFile(FileUploadEvent event) {
    this.selectedDashboardPermissions = new ArrayList<>();
    importFile = event.getFile();
    if (validateFile()) {
      displayedMessage();
      return;
    }
    try {
      selectedDashboard = BusinessEntityConverter.jsonValueToEntity(new String(importFile.getContent()), Dashboard.class);      
    } catch (PortalException e) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript"), null);
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
            dto = nameToSecurityMemberDTO.get(ROLE_EVERYBODY);
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
  
  
  /**
   * Create new image with new widgetId
   * 
   * @param widget
   */
  private void writeWelcomeWidgetImage(WelcomeDashboardWidget widget) {
    if (widget.getImageType() == null) {
      Ivy.log().warn("WidgetId {0} does not has imageType. Skip write to cms.", widget.getId());
      return;
    }
    if (StringUtils.isNotBlank(widget.getImageLocation())) {
      String widgetId = DashboardWidgetUtils.generateNewWidgetId(WELCOME);
      String fileExtension = WelcomeWidgetUtils.getFileTypeOfImage(widget.getImageType());
      String imageLocation = widgetId.concat(WelcomeWidgetUtils.DEFAULT_LOCALE_AND_DOT).concat(fileExtension);
      ContentObject newImageObject = WelcomeWidgetUtils.getImageContentObject(WelcomeWidgetUtils.getFileNameOfImage(imageLocation), fileExtension);
      if (StringUtils.isNotBlank(widget.getImageContent())) {
        // If has defined content, create new image
        if (newImageObject != null) {
          WelcomeWidgetUtils.readObjectValueOfDefaultLocale(newImageObject).write().bytes(Base64.getDecoder().decode(widget.getImageContent()));
        }
        widget.setImageLocation(imageLocation);
        widget.setImageContent(null);
      } else {
        // If has defined location, clone new image
        byte[] oldFileContent = WelcomeWidgetUtils.getImageAsByteData(widget.getImageLocation());
        WelcomeWidgetUtils.readObjectValueOfDefaultLocale(newImageObject).write().bytes(oldFileContent);
      }
    }
  }
  
  @Override
  public void createDashboard() {
    if (CollectionUtils.isNotEmpty(this.selectedDashboard.getWidgets())) {
      for (DashboardWidget widget : this.selectedDashboard.getWidgets()) {
        if (widget instanceof WelcomeDashboardWidget) {
          WelcomeDashboardWidget welcomeWidget = (WelcomeDashboardWidget) widget;
          writeWelcomeWidgetImage(welcomeWidget);
        }
      }
    }
    super.createDashboard();
  }
  
  private void displayedMessage() {
    FacesContext.getCurrentInstance().addMessage("import-dashboard-form:import-dashboard-dialog-message", validateMessage);
  }

  private boolean validateFile() {
    isError = false;
    
    if (importFile == null || importFile.getSize() == 0) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileEmptyMessage"), null);
    } else if (CaseDocumentService.enableVirusScannerForUploadedDocument() && CaseDocumentService.isDocumentTypeHasVirus(importFile)) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainVirus"), null);
    } else if (CaseDocumentService.enableScriptCheckingForUploadedDocument() && !CaseDocumentService.isDocumentSafe(importFile)) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript"), null);
    } else if (!FilenameUtils.isExtension(importFile.getFileName(), "json")) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/Dialogs/components/CaseDocument/invalidFileMessage"), null);
    }
    return isError;
  }
  
  private void resetDialog() {
    selectedDashboard = new Dashboard();
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

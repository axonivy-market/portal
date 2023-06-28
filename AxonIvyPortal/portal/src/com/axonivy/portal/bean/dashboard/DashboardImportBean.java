package com.axonivy.portal.bean.dashboard;

import static ch.ivy.addon.portalkit.constant.PortalConstants.MAX_USERS_IN_AUTOCOMPLETE;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import com.fasterxml.jackson.databind.JsonNode;

import ch.ivy.addon.portal.generic.bean.DashboardModificationBean;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.ExpressMessageType;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DashboardImportBean extends DashboardModificationBean implements Serializable{
  private static final long serialVersionUID = 1L;
  private boolean isLoaded = false;
  private String msgOutput;
  private String loadStatus;
  private UploadedFile importDashboardFile; 
  private FacesMessage validateMessage;
  private Boolean isError = false;
  private String fileSize;
  
  public void importDashboard(boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
    resetDialog();
  }
  
  public void loadImportedFile(FileUploadEvent event) {
    importDashboardFile = event.getFile();
    if (validateFile()) {
      displayedMessage();
      return;
    }
    try {
      this.selectedDashboard = BusinessEntityConverter.jsonValueToEntity(new String(importDashboardFile.getContent()), Dashboard.class);      
    } catch (PortalException e) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript"), null);
      displayedMessage();
      return;
    }
    this.selectedDashboard.setIsPublic(isPublicDashboard);
    this.selectedDashboard.setId(DashboardUtils.generateId());
    this.selectedDashboard.setPermissionDTOs(new ArrayList<>());
    Map<String, SecurityMemberDTO> nameToSecurityMemberDTO = SecurityMemberUtils.findSecurityMembers("", 0, MAX_USERS_IN_AUTOCOMPLETE)
            .stream().filter(securityMember -> !securityMember.isUser())
            .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, v -> v));
    List<String> permissions = selectedDashboard.getPermissions();
    if (CollectionUtils.isNotEmpty(permissions)) {
      var responsibles = permissions.stream().filter(Objects::nonNull).distinct()
          .filter(permission -> !permission.startsWith("#")).map(permission -> nameToSecurityMemberDTO.get(permission))
          .filter(Objects::nonNull).collect(Collectors.toSet());
      this.selectedDashboardPermissions = responsibles.stream().map(SecurityMemberDTO::getName).collect(Collectors.toList());
      this.selectedDashboard.setPermissionDTOs(new ArrayList<>(responsibles));
    }
    fileSize = FileUtils.byteCountToDisplaySize(this.importDashboardFile.getSize());
    isLoaded = true;
  }
  
  private void writeWelcomeWidgetImage(WelcomeDashboardWidget widget) {
    if (StringUtils.isNotBlank(widget.getImageContent()) && StringUtils.isNotBlank(widget.getImageLocation())) {
      // If has defined location, save to that location
      ContentObject widgetImage = WelcomeWidgetUtils.getImageContentObject(widget.getImageLocation(), widget.getImageType());
      WelcomeWidgetUtils.readObjectValueOfDefaultLocale(widgetImage).write().bytes(Base64.getDecoder().decode(widget.getImageContent()));
      widget.setImageContent(null);
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
    FacesContext.getCurrentInstance().addMessage("import-express-form:import-express-dialog-message", validateMessage);
    loadStatus = ExpressMessageType.FAILED.getLabel();
  }

  private boolean validateFile() {
    isError = false;
    msgOutput = StringUtils.EMPTY;
    
    if (importDashboardFile == null || importDashboardFile.getSize() == 0) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/fileEmptyMessage"), null);
    } else if (CaseDocumentService.enableVirusScannerForUploadedDocument() && CaseDocumentService.isDocumentTypeHasVirus(importDashboardFile)) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainVirus"), null);
    } else if (CaseDocumentService.enableScriptCheckingForUploadedDocument() && !CaseDocumentService.isDocumentSafe(importDashboardFile)) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript"), null);
    } else if (!FilenameUtils.isExtension(importDashboardFile.getFileName(), "json")) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/Dialogs/components/CaseDocument/invalidFileMessage"), null);
    }
    return isError;
  }
  
  private void resetDialog() {
    this.selectedDashboard = new Dashboard();
    fileSize = null;
    importDashboardFile = null;
    isLoaded = isError = false;
  }
  
  public boolean isLoaded() {
    return isLoaded;
  }
  
  public void setLoaded(boolean isLoaded) {
    this.isLoaded = isLoaded;
  }
  
  public String getMsgOutput() {
    return msgOutput;
  }

  public void setMsgOutput(String msgOutput) {
    this.msgOutput = msgOutput;
  }

  public String getLoadStatus() {
    return loadStatus;
  }

  public void setLoadStatus(String loadStatus) {
    this.loadStatus = loadStatus;
  }

  public UploadedFile getImportDashboardFile() {
    return importDashboardFile;
  }
  
  public void setImportDashboardFile(UploadedFile importDashboardFile) {
    this.importDashboardFile = importDashboardFile;
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

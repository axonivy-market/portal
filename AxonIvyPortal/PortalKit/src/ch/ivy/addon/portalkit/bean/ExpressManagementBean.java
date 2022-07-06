package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.enums.ExpressMessageType;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.util.ExpressManagementUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class ExpressManagementBean implements Serializable {

  private static final long serialVersionUID = -6072339110563610370L;

  private List<SecurityMemberDTO> activeMemberList;
  private List<ExpressProcess> expressProcesses;
  private List<ExpressProcess> selectedExpressProcesses;
  private StreamedContent exportExpressFile;
  private UploadedFile importExpressFile;
  private String importOutput;
  private String importStatus;
  private FacesMessage validateMessage;
  private Boolean isError;

  public void initWorkflowSummaryTable() {
    activeMemberList = findAllActiveUser();
    setExpressProcesses(ExpressManagementUtils.findExpressProcesses());
  }

  @SuppressWarnings("unchecked")
  private List<SecurityMemberDTO> findAllActiveUser() {
    if (activeMemberList == null) {
      return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
          .withStartName("findSecurityMembers")
          .withParam("query", "")
          .withParam("startIndex", 0)
          .withParam("count", PortalConstants.MAX_USERS_IN_AUTOCOMPLETE)
          .call()
          .get("members", List.class);
    }
    return activeMemberList;
  }

  /**
   * Get a display name by activator name
   *
   * @param activatorName
   * @return display name
   */
  public String getUserDisplayName(String activatorName) {
    if (StringUtils.isBlank(activatorName)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
    }

    String displayName = activatorName;
    if (CollectionUtils.isNotEmpty(activeMemberList)) {
      Optional<SecurityMemberDTO> activeUser = activeMemberList.stream().filter(user -> user.getMemberName().equalsIgnoreCase(activatorName))
          .findFirst();
      if (activeUser.isPresent()) {
        displayName = StringUtils.isBlank(activeUser.get().getDisplayName()) ? activeUser.get().getName() : activeUser.get().getDisplayName();
      } else {
        displayName = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
      }
    } else {
      IUser user = ServiceUtilities.findUser(activatorName, Ivy.request().getApplication());
      displayName = StringUtils.isBlank(user.getDisplayName()) ? user.getName() : user.getDisplayName();
    }
    return displayName;
  }

  public void importExpress(FileUploadEvent event) {
    importExpressFile = event.getFile();
    validate();
    if (isError) {
      displayedMessage();
    } else {
      importExpressProcesses();
    }
  }

  public StreamedContent exportExpress() {
    if (selectedExpressProcesses != null && !selectedExpressProcesses.isEmpty()) {
      exportExpressFile = ExpressManagementUtils.exportExpressProcess(selectedExpressProcesses);
    }
    return exportExpressFile;
  }

  private void validate() {
    isError = false;
    importOutput = StringUtils.EMPTY;

    if (importExpressFile == null || importExpressFile.getSize() == 0) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/fileEmptyMessage"), null);
    } else if (CaseDocumentService.enableVirusScannerForUploadedDocument() && CaseDocumentService.isDocumentTypeHasVirus(importExpressFile)) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainVirus"), null);
    } else if (CaseDocumentService.enableScriptCheckingForUploadedDocument() && !CaseDocumentService.isDocumentSafe(importExpressFile)) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript"), null);
    } else if (!FilenameUtils.isExtension(importExpressFile.getFileName(), "json")) {
      isError = true;
      validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/Dialogs/components/CaseDocument/invalidFileMessage"), null);
    }
  }

  private void displayedMessage() {
    FacesContext.getCurrentInstance().addMessage("import-express-form:import-express-dialog-message", validateMessage);
    importStatus = ExpressMessageType.FAILED.getLabel();
  }

  @SuppressWarnings("unchecked")
  private void importExpressProcesses() {
    Map<ExpressMessageType, Object> results = ExpressManagementUtils.importExpressProcesses(importExpressFile);
    try {
      importStatus = results.get(ExpressMessageType.IMPORT_STATUS).toString();
      importOutput = results.get(ExpressMessageType.IMPORT_RESULT).toString();
      if (!importStatus.equalsIgnoreCase(ExpressMessageType.FAILED.getLabel())) {
        expressProcesses.addAll((List<ExpressProcess>) results.get(ExpressMessageType.IMPORT_EXPRESS_PROCESSES));
      }
    } catch (Exception e) {
      importStatus = ExpressMessageType.FAILED.getLabel();
      importOutput = e.getMessage();
    }

    if (importStatus.equalsIgnoreCase(ExpressMessageType.FAILED.getLabel())) {
      isError = true;
    }
  }

  public List<ExpressProcess> getExpressProcesses() {
    return expressProcesses;
  }

  public void setExpressProcesses(List<ExpressProcess> expressProcesses) {
    this.expressProcesses = expressProcesses;
  }

  public String getImportOutput() {
    return importOutput;
  }

  public void setImportOutput(String importOutput) {
    this.importOutput = importOutput;
  }

  public List<ExpressProcess> getSelectedExpressProcesses() {
    return selectedExpressProcesses;
  }

  public void setSelectedExpressProcesses(List<ExpressProcess> selectedExpressProcesses) {
    this.selectedExpressProcesses = selectedExpressProcesses;
  }

  public StreamedContent getExportExpressFile() {
    return exportExpressFile;
  }

  public void setExportExpressFile(StreamedContent exportExpressFile) {
    this.exportExpressFile = exportExpressFile;
  }

  public String getImportStatus() {
    return importStatus;
  }

  public void setImportStatus(String importStatus) {
    this.importStatus = importStatus;
  }

  public UploadedFile getImportExpressFile() {
    return importExpressFile;
  }

  public void setImportExpressFile(UploadedFile importExpressFile) {
    this.importExpressFile = importExpressFile;
  }
}

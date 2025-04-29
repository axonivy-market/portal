package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.components.enums.BasicDocumentType;
import com.axonivy.portal.components.enums.DocumentType;
import com.axonivy.portal.components.util.FacesMessageUtils;
import com.axonivy.portal.util.UploadDocumentUtils;

import ch.ivy.addon.portalkit.enums.ApplicationType;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.masterdata.AwesomeIcon;
import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.util.GrowlMessageUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class MasterDataBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final String APPLICATION_NAME = GlobalVariable.APPLICATION_NAME.getKey();
  private static final String DEFAULT_APPLICATION_NAME =
      Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/defaultApplicationName");
  private static final String PORTAL_NAME = Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalName/PortalName");

  public AwesomeIcon[] getAwesomeIcons() {
    return AwesomeIcon.values();
  }

  public ApplicationType[] getApplicationTypes() {
    return ApplicationType.values();
  }

  public String getTaskSortFieldPriority() {
    return TaskSortField.PRIORITY.toString();
  }

  public String getTaskSortFieldName() {
    return TaskSortField.NAME.toString();
  }

  public String getTaskSortFieldActivator() {
    return TaskSortField.ACTIVATOR.toString();
  }

  public String getTaskSortFieldId() {
    return TaskSortField.ID.toString();
  }

  public String getTaskSortFieldCreationTime() {
    return TaskSortField.CREATION_TIME.toString();
  }

  public String getTaskSortFieldExpiryTime() {
    return TaskSortField.EXPIRY_TIME.toString();
  }
  
  public String getTaskSortFieldCompletedTime() {
	return TaskSortField.COMPLETED_ON.toString();
  }

  public String getTaskSortFieldState() {
    return TaskSortField.STATE.toString();
  }
  
  public String getTaskSortFieldCategory() {
    return TaskSortField.CATEGORY.toString();
  }

  public Long getFileUploadSizeLimit() {
    return UploadDocumentUtils.getDocumentUploadSizeLimit();
  }
  
  public String getFileUploadInvalidSizeMessage() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/errorFileUploadSize",
        Arrays.asList(FileUtils.byteCountToDisplaySize(getFileUploadSizeLimit())));
  }
  
  public DocumentType[] getDocumentTypes() {
    return BasicDocumentType.values();
  }

  public String getReadableFileSize(Long fileSize) {
    return FileUtils.byteCountToDisplaySize(fileSize);
  }

  public boolean isPortalInTeams() {
    Boolean isPortalInTeams = (Boolean) SecurityServiceUtils.getSessionAttribute(SessionAttribute.PORTAL_IN_TEAMS.toString());
    return BooleanUtils.isTrue(isPortalInTeams);
  }
  
  public String getTaskSortFieldApplication() {
    return TaskSortField.APPLICATION.toString();
  }
  
  public void showLinkCopiedMessage() {
    String message = Ivy.cms().co("/Dialogs/com/axonivy/portal/component/ShareLinkDialog/LinkCopied");
    FacesContext.getCurrentInstance().addMessage(GrowlMessageUtils.PORTAL_GLOBAL_GROWL_MESSAGE, FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_INFO, message, null));
    PrimeFaces.current().ajax().update(GrowlMessageUtils.PORTAL_GLOBAL_GROWL);
  }
  
  public String getPortalApplicationName() {
    if (getApplicationName().isBlank()) {
      return PORTAL_NAME;
    }
    return String.join(" - ", PORTAL_NAME, getApplicationName());
  }

  public String getApplicationName() {
    String applicationName = Ivy.var().get(APPLICATION_NAME);
    return applicationName.isBlank() ? StringUtils.EMPTY : applicationName;
  }

  public String getUserLanguage() {
    return LanguageService.getInstance().getUserLanguage();
  }
  
  public String getAllowedUploadFileType() {
    List<String> extensionList = CaseDocumentService.getAllowedUploadFileType();
    if (CollectionUtils.isEmpty(extensionList)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/allTypes");
    } else
      return String.join(", ", extensionList);
  }
}

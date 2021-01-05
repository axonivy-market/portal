package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.FileUtils;

import ch.ivy.addon.portalkit.enums.ApplicationType;
import ch.ivy.addon.portalkit.enums.BasicDocumentType;
import ch.ivy.addon.portalkit.enums.DocumentType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.masterdata.AwesomeIcon;
import ch.ivy.addon.portalkit.masterdata.MasterData;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class MasterDataBean implements Serializable {

  private static final long serialVersionUID = 1L;

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
    return MasterData.getFileUploadSizeLimit();
  }
  
  public String getFileUploadInvalidSizeMessage() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/errorFileUploadSize",
        Arrays.asList(FileUtils.byteCountToDisplaySize(getFileUploadSizeLimit())));
  }
  
  public DocumentType[] getDocumentTypes() {
    return BasicDocumentType.values();
  }
}

package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortMeta;

import com.axonivy.portal.components.ivydata.bo.IvyDocument;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;

@ManagedBean
public class CaseTaskDocumentBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String SHOW_TASK_DOCUMENT_FRIENDLY_REQUEST_PATH =
      "Start Processes/PortalStart/ShowTaskDocument.ivp";
  private static final String SHOW_CASE_DOCUMENT_FRIENDLY_REQUEST_PATH =
      "Start Processes/PortaStart/ShowCaseDocument.ivp";
  private static final String UUID = "uuid";

  public boolean isShowMoreDocument() {
    return PermissionUtils.hasPermission(IPermission.DOCUMENT_READ);
  }

  public boolean canWriteDocument(ICase caze) {
    if (caze == null) {
      return false;
    }
    var isHideUploadDocForDoneCase = GlobalSettingService.getInstance()
        .findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE);
    return !(caze.getBusinessState() == CaseBusinessState.DONE && isHideUploadDocForDoneCase) && hasPermissionWriteDocument(caze);
  }
  
  public boolean canWriteDocument(ITask task) {
    ICase currentCase = task.getCase().getBusinessCase();
    if (currentCase == null) {
      return false;
    }
    var isHideUploadDocForDoneCase = GlobalSettingService.getInstance()
        .findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE);
    return !(currentCase.getBusinessState() == CaseBusinessState.DONE && isHideUploadDocForDoneCase) && hasPermissionWriteDocument(task);
  }

  private boolean hasPermissionWriteDocument(Object iNoteable) {
    return hasPermission(iNoteable, IPermission.DOCUMENT_WRITE)
        || hasPermission(iNoteable, IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE);
  }

  private boolean hasPermission(Object iNoteable, IPermission permission) {
    if (iNoteable == null || permission == null) {
      return false;
    }
    return PermissionUtils.hasPermission(permission);
  }

  public String getTaskDocumentsLink(ITask task) {
    Map<String, String> params = new HashMap<>();
    params.put(UUID, task.uuid());
    return PortalNavigator.buildUrlByKeyword("ShowTaskDocument.ivp", SHOW_TASK_DOCUMENT_FRIENDLY_REQUEST_PATH,
        params);
  }

  public String getCaseDocumentsLink(ICase iCase) {
    Map<String, String> params = new HashMap<>();
    params.put(UUID, iCase.uuid());
    return PortalNavigator.buildUrlByKeyword("ShowCaseDocument.ivp", SHOW_CASE_DOCUMENT_FRIENDLY_REQUEST_PATH,
        params);
  }

  public SortMeta getDocumentSortByCreationTimestamp() {
    return SortFieldUtil.buildSortMeta("creation.timestamp", true);
  }
  
  public boolean canPreviewDocument(IvyDocument document) {
    boolean enablePreviewSetting = GlobalSettingService.getInstance().findBooleanGlobalSettingValue(GlobalVariable.ENABLE_DOCUMENT_PREVIEW);
    Ivy.log().error("enablePreviewSetting is {0}", enablePreviewSetting);
    if (document != null && StringUtils.startsWithIgnoreCase(document.getContentType(), "image/")) {
      return enablePreviewSetting;
    }
    boolean isSupportedPreviewType = document != null && StringUtils.endsWithAny(document.getPath().toLowerCase(), ".pdf", ".txt", ".log");
    Ivy.log().error("isSupportedPreviewType is {0}", isSupportedPreviewType);
    return enablePreviewSetting && isSupportedPreviewType;
  }
}

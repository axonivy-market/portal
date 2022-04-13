package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.SortMeta;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INoteable;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
public class CaseTaskDocumentBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String SHOW_TASK_DOCUMENT_FRIENDLY_REQUEST_PATH =
      "Start Processes/PortalStart/ShowTaskDocument.ivp";
  private static final String SHOW_CASE_DOCUMENT_FRIENDLY_REQUEST_PATH =
      "Start Processes/PortaStart/ShowCaseDocument.ivp";

  public boolean isShowMoreDocument() {
    return PermissionUtils.hasPermission(IPermission.DOCUMENT_READ);
  }

  public boolean canWriteDocument(INoteable iNoteable) {
    ICase currentCase = getCurrentBusinessCase(iNoteable);
    if (currentCase == null) {
      return false;
    }
    var isHideUploadDocForDoneCase = GlobalSettingService.getInstance()
        .findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE);
    return !(currentCase.getState() == CaseState.DONE && isHideUploadDocForDoneCase) && hasPermissionWriteDocument(iNoteable);
  }

  private ICase getCurrentBusinessCase(INoteable iNoteable) {
    if (iNoteable instanceof ICase) {
      return (ICase) iNoteable;
    }
    if (iNoteable instanceof ITask) {
      var task = (ITask) iNoteable;
      return task.getCase().getBusinessCase();
    }
    return null;
  }

  public boolean hasPermissionWriteDocument(INoteable iNoteable) {
    return hasPermission(iNoteable, IPermission.DOCUMENT_WRITE)
        || hasPermission(iNoteable, IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE);
  }

  private boolean hasPermission(INoteable iNoteable, IPermission permission) {
    if (iNoteable == null || permission == null) {
      return false;
    }
    return PermissionUtils.hasPermission(permission);
  }

  public String getTaskDocumentsLink(ITask task) {
    Map<String, String> params = new HashMap<>();
    params.put("selectedTaskId", String.valueOf(task.getId()));
    return PortalNavigator.buildUrlByKeyword("ShowTaskDocument.ivp", SHOW_TASK_DOCUMENT_FRIENDLY_REQUEST_PATH,
        params);
  }

  public String getCaseDocumentsLink(ICase iCase) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(iCase.getId()));
    return PortalNavigator.buildUrlByKeyword("ShowCaseDocument.ivp", SHOW_CASE_DOCUMENT_FRIENDLY_REQUEST_PATH,
        params);
  }

  public SortMeta getDocumentSortByCreationTimestamp() {
    return SortFieldUtil.buildSortMeta("creation.timestamp", true);
  }
}

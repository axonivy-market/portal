package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.inject.Named;

import org.primefaces.model.SortMeta;

import com.axonivy.portal.components.ivydata.bo.IvyDocument;
import com.axonivy.portal.components.util.DocumentUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import jakarta.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class CaseTaskDocumentBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String SHOW_TASK_DOCUMENT_FRIENDLY_REQUEST_PATH =
      "Start Processes/PortalStart/ShowTaskDocument.ivp";
  private static final String SHOW_CASE_DOCUMENT_FRIENDLY_REQUEST_PATH =
      "Start Processes/PortaStart/ShowCaseDocument.ivp";
  private static final String UUID = "uuid";

  private final Map<String, Boolean> accessibleCaseByUuid = new HashMap<>();

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
    if (task == null) {
      return false;
    }
    ICase currentCase = task.getCase().getBusinessCase();
    if (currentCase == null) {
      return false;
    }
    var isHideUploadDocForDoneCase = GlobalSettingService.getInstance()
        .findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE);
    return !(currentCase.getBusinessState() == CaseBusinessState.DONE && isHideUploadDocForDoneCase) && hasPermissionWriteDocument(currentCase);
  }

  /**
   * Documents are stored on the business case, so holding the permission globally is not enough:
   * the document must also belong to a case the current user is allowed to see. Otherwise the
   * upload/rename/delete controls are offered for any case reached by uuid.
   */
  private boolean hasPermissionWriteDocument(ICase businessCase) {
    if (businessCase == null) {
      return false;
    }
    return PermissionUtils.hasAtLeastOnePermission(IPermission.DOCUMENT_WRITE,
        IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE) && isCaseAccessible(businessCase);
  }

  private boolean isCaseAccessible(ICase businessCase) {
    return accessibleCaseByUuid.computeIfAbsent(businessCase.uuid(),
        uuid -> CaseService.newInstance().isCaseAccessible(uuid));
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
  
  public boolean isSupportedPreviewType(IvyDocument document) {
    return DocumentUtils.isSupportedPreviewType(document);
  }
}

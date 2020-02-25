package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.service.CaseFilterService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.NumberUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean
@ViewScoped
public class CaseWidgetBean implements Serializable {

  private static final String START_PROCESSES_SHOW_ADDITIONAL_CASE_DETAILS_PAGE = "Start Processes/CaseWidget/showAdditionalCaseDetails.ivp";  
  private static final long serialVersionUID = 1L;

  private Long expandedCaseId;
  private ICase selectedCase;
  private boolean isShowCaseDetails;
  private boolean isShowAllTasksOfCase;
  private boolean isShowFullCaseList;

  public CaseWidgetBean() {
    expandedCaseId = -1L;
    isShowCaseDetails = PermissionUtils.hasPortalPermission(PortalPermission.SHOW_CASE_DETAILS);
    isShowAllTasksOfCase = PermissionUtils.hasPortalPermission(PortalPermission.SHOW_ALL_TASKS_OF_CASE);
    isShowFullCaseList = PermissionUtils.checkAccessFullCaseListPermission();
  }

  public Long getExpandedCaseId() {
    return expandedCaseId;
  }

  public void setExpandedCaseId(Long expandedCaseId, boolean alreadyExpanded) {
    this.expandedCaseId = alreadyExpanded ? 0L : expandedCaseId;
  }

  public ICase getSelectedCase() {
    return selectedCase;
  }

  public void setSelectedCase(ICase selectedCase) {
    this.selectedCase = selectedCase;
  }

  public boolean isDeleteFilterEnabledFor(CaseFilterData filterData) {
    CaseFilterService filterService = new CaseFilterService();
    return filterService.isDeleteFilterEnabledFor(filterData);
  }

  public String getAdditionalCaseDetailsPageUri(ICase iCase) {
    String additionalCaseDetailsPageUri = iCase.customFields().textField(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString()).getOrNull();
    if (StringUtils.isEmpty(additionalCaseDetailsPageUri)) {
      additionalCaseDetailsPageUri = CaseUtils.getProcessStartUriWithCaseParameters(iCase, START_PROCESSES_SHOW_ADDITIONAL_CASE_DETAILS_PAGE);
    }
    return additionalCaseDetailsPageUri;
  }

  public boolean isNaN(Number number){
    return NumberUtils.isNaN(number);
  }

  public boolean isHiddenCase(ICase iCase) {
    return iCase == null ? false : iCase.customFields().stringField(AdditionalProperty.HIDE.toString()).isPresent();
  }
  
  public ICase findCase(long caseId) {
    return CaseUtils.findCase(caseId);
  }
  
  public void destroyCase(ICase iCase) {
    IvyExecutor.executeAsSystem(() -> {
      iCase.destroy();
      return Void.class;
    });
  }

  public String sanitizeHTML(String text) {
    return HtmlParser.sanitizeHTML(text);
  }

  public String formatCaseDescription(String text) {
    return HtmlParser.extractTextFromHtml(text);
  }

  public boolean isShowCaseDetails() {
    return isShowCaseDetails;
  }

  public void setShowCaseDetails(boolean isShowCaseDetails) {
    this.isShowCaseDetails = isShowCaseDetails;
  }

  public boolean isShowAllTasksOfCase() {
    return isShowAllTasksOfCase;
  }

  public void setShowAllTasksOfCase(boolean isShowAllTasksOfCase) {
    this.isShowAllTasksOfCase = isShowAllTasksOfCase;
  }

  public boolean isShowFullCaseList() {
    return isShowFullCaseList;
  }

  public void setShowFullCaseList(boolean isShowFullCaseList) {
    this.isShowFullCaseList = isShowFullCaseList;
  }
  
  public <T> List<T> getFirst20Items(List<T> items) {
    return items.stream().limit(20).collect(Collectors.toList());
  }
  
}

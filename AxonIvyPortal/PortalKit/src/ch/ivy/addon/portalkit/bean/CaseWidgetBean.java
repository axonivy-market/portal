package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.filter.AbstractFilter.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData;
import ch.ivy.addon.portalkit.casefilter.impl.CaseStateFilter;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.service.CaseFilterService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.NumberUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
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
  private boolean isAdminCaseStateIncluded;
  private CaseLazyDataModel dataModel;

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
    String additionalCaseDetailsPageUri = StringUtils.EMPTY;
    if (isExpressCase(iCase)) {
      ProcessStartCollector processStartCollector =  new ProcessStartCollector();
      additionalCaseDetailsPageUri = processStartCollector.findExpressBusinessViewStartLink() + "?caseId=" + iCase.getId();
    } else {
      additionalCaseDetailsPageUri = iCase.customFields().textField(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString()).getOrNull();
      if (StringUtils.isEmpty(additionalCaseDetailsPageUri)) {
        additionalCaseDetailsPageUri = CaseUtils.getProcessStartUriWithCaseParameters(iCase, START_PROCESSES_SHOW_ADDITIONAL_CASE_DETAILS_PAGE) + "&embedInFrame";
      } else {
        // support open in iFrame 
        additionalCaseDetailsPageUri = additionalCaseDetailsPageUri + "&embedInFrame";
      }
    }
    return additionalCaseDetailsPageUri;
  }

  public String getProcessOverviewPageUri(ICase iCase) {
    String processOverviewPageUri = StringUtils.EMPTY;

    processOverviewPageUri = PortalNavigator.buildProcessInfoUrl(iCase.getApplication().getName().concat("/").concat(iCase.getProcessStart().getFullUserFriendlyRequestPath()));
    return processOverviewPageUri;
  }

  private boolean isExpressCase(ICase iCase) {
    return BooleanUtils.toBoolean(iCase.customFields().stringField("isExpress").getOrNull());
  }

  public boolean showProcessOverviewLink(ICase iCase) {
    return new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_PROCESS_INFORMATION.name()) && !isExpressCase(iCase);
  }

  /**
   * If Case State filter is selecting DESTROYED
   * Then disable option save a filter for all user
   * @param caseFilters is selected filters
   */
  public void verifyCaseStateFilter(List<CaseFilter> caseFilters) {
    if (!PermissionUtils.checkReadAllCasesPermission()) {
      isAdminCaseStateIncluded = false;
      return;
    }
    for (CaseFilter filter : caseFilters) {
      if (filter instanceof CaseStateFilter) {
        CaseStateFilter caseStateFilter = (CaseStateFilter) filter;
        if (!caseStateFilter.value().equals(ALL)) {
          isAdminCaseStateIncluded = caseStateFilter.getSelectedFilteredStates()
              .contains(CaseState.DESTROYED);
        }
        break;
      }
    }
  }

  public boolean isAdminCaseStateIncluded() {
    return isAdminCaseStateIncluded;
  }

  public void setAdminCaseStateIncluded(boolean isAdminCaseStateIncluded) {
    this.isAdminCaseStateIncluded = isAdminCaseStateIncluded;
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
    CaseUtils.destroyCase(iCase);
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
  
  /**
   * navigate after destroy case
   * if destroy case from related technical case -> navigate back
   * otherwise return to case list
   * @return whether to navigate back
   */
  public boolean willNavigateBack() {
    if (Ivy.session().getAttribute(SessionAttribute.NAVIGATE_FROM_RELATED_CASE.toString()) != null) {
      Ivy.session().removeAttribute(SessionAttribute.NAVIGATE_FROM_RELATED_CASE.toString());
      return true;
    }
    return false;
  }

  public CaseLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(CaseLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  /**
   * Gets visible columns on Case list page.
   * 
   * @param dataModel
   * @return visible columns
   */
  public List<String> getColumns(CaseLazyDataModel dataModel) {
    List<String> visibilityColumns = new ArrayList<>();
    visibilityColumns.addAll(dataModel.getSelectedColumns());

    /*
     * In UI we have a column called "Name / Description", but PortalRequiredColumns contains only "Name" column, so
     * that we need to check and add "Description" to Excel file
     */
    List<String> requiredColumns = dataModel.getPortalRequiredColumns();
    if (requiredColumns != null && requiredColumns.contains(CaseSortField.NAME.name())) {
      visibilityColumns.add(CaseLazyDataModel.DESCRIPTION);
    }
    return visibilityColumns;
  }

  public int getMaxCaseNumberInExcel() {
    return Exporter.MAX_ROW_NUMBER_IN_EXCEL;
  }

  /**
   * Gets visible columns on Task list page.
   * 
   * @param dataModel
   * @return visible columns
   */
  public List<String> getRelatedTaskColumns(TaskLazyDataModel dataModel) {
    List<String> visibilityColumns = new ArrayList<>();
    visibilityColumns.addAll(dataModel.getSelectedColumns());

    /*
     * In UI we have a column called "Name / Description", but PortalRequiredColumns contains only "Name" column, so
     * that we need to check and add "Description" to Excel file
     */
    List<String> requiredColumns = dataModel.getPortalRequiredColumns();
    if (requiredColumns != null && requiredColumns.contains(TaskSortField.NAME.name())) {
      visibilityColumns.add(TaskLazyDataModel.DESCRIPTION);
    }
    return visibilityColumns;
  }
}

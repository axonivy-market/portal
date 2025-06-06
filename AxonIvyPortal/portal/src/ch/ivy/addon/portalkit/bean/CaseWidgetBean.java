package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.enums.SearchScopeCaseField;
import com.axonivy.portal.service.GlobalSearchService;

import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.NumberUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean
@ViewScoped
public class CaseWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ICase selectedCase;
  private boolean isShowAllTasksOfCase;
  private boolean isShowFullCaseList;
  private boolean isAdminCaseStateIncluded;
  private CaseLazyDataModel dataModel;

  public CaseWidgetBean() {
    isShowAllTasksOfCase = PermissionUtils.hasPortalPermission(PortalPermission.SHOW_ALL_TASKS_OF_CASE);
    isShowFullCaseList = PermissionUtils.checkAccessFullCaseListPermission();
  }

  public ICase getSelectedCase() {
    return selectedCase;
  }

  public void setSelectedCase(ICase selectedCase) {
    this.selectedCase = selectedCase;
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

  public String getGlobalSearchText(CaseLazyDataModel model) {
    String result = "";
    if (model.getCriteria().isGlobalSearch()) {
      String keyword = Optional.ofNullable(model.getCriteria().getKeyword()).orElse("");
      String searchScopeCaseFieldsString = "";

      if (CollectionUtils.isNotEmpty(model.getCriteria().getSearchScopeCaseFields())) {
        searchScopeCaseFieldsString
          = ", ".concat(String.join(", ", model.getCriteria().getSearchScopeCaseFields().stream().map(SearchScopeCaseField::getLabel).collect(Collectors.toList())));
      }

      result = Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/GlobalSearchText", Arrays.asList(keyword, searchScopeCaseFieldsString));
    }
    return result;
  }

  public boolean isShowGlobalSearchScope() {
    return GlobalSearchService.getInstance().isShowGlobalSearchByCases();
  }

  public boolean isCaseFound(ICase caze) {
    if (caze != null) {
      return CaseService.newInstance().isCaseAccessible(caze.uuid());
    }
    return false;
  }
  
  public String getDestroyCaseMessage() {
    String caseName = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/unknownCase");
    String caseId = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/unknownId");
    if (this.selectedCase != null) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/destroyCaseMessage", List.of(this.selectedCase.names().current(), this.selectedCase.getId()));
    }
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/destroyCaseMessage", List.of(caseName, caseId));
  }
}

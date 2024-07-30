package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SortMeta;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.components.publicapi.ProcessStartAPI;
import com.axonivy.portal.components.util.ProcessStartUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.datamodel.internal.RelatedCaseLazyDataModel;
import ch.ivy.addon.portalkit.dto.casedetails.CaseDetails;
import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInTaskList;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

@ManagedBean
@ViewScoped
public class CaseDetailsBean extends AbstractConfigurableContentBean<CaseDetails> implements Serializable {

  private static final long serialVersionUID = 1023540096176033250L;
  private static final String OPEN_CASES_LIST = "Start Processes/PortalStart/CaseListPage.ivp";
  private boolean isHideCaseDocument;
  private boolean isTaskStartedInDetails;
  private boolean showBackButton;
  private ICase selectedCase;
  private ITask selectedTask;
  private CaseActionBean caseActionBean;
  private boolean inFrame;
  private boolean isFirstTime;
  private boolean isRunningTaskWhenClickingOnTaskInList;
  private String caseDetailsDescription;
  private String caseDetailsUrl;
  private Boolean isShowShareButton;
  private boolean isHideCaseCreator;

  public void init() {
    super.initConfig();
    isHideCaseDocument = GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_CASE_DOCUMENT);
    caseActionBean = ManagedBeans.get("caseActionBean");
    isFirstTime = true;
    isShowShareButton = PermissionUtils.hasShareCaseDetailsPermission();
    isHideCaseCreator = GlobalSettingService.getInstance().isHideCaseCreator();
    isRunningTaskWhenClickingOnTaskInList = GlobalSettingService.getInstance()
        .findGlobalSettingValue(GlobalVariable.DEFAULT_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST)
        .equals(BehaviourWhenClickingOnLineInTaskList.RUN_TASK.name());
  }

  public void preRender(ICase selectedCase, boolean showBackButton) {
    this.init();
    this.selectedCase = selectedCase;
    this.showBackButton = showBackButton;
    this.isTaskStartedInDetails = BooleanUtils.toBooleanDefaultIfNull((Boolean) Ivy.session().getAttribute(SessionAttribute.IS_TASK_STARTED_IN_DETAILS.toString()), false);
    this.caseDetailsUrl = PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageByUUID(selectedCase.uuid());
    if (isFirstTime) {
      isFirstTime = false;
      try {
        loadWidgets();
      } catch (Exception e) {
        Ivy.log().error("Exception at method preRender of class CaseDetailsBean", e);
      }
    }
  }

  public boolean hasTechnicalCases(ICase iCase) {
    CaseQuery caseQuery = CaseQuery.subCases().where().businessCaseId().isEqual(iCase.getId());
    return Ivy.wf().getCaseQueryExecutor().getFirstResult(caseQuery) != null;
  }

  public void backToCasesList() {
    String friendlyRequestPath = ProcessStartUtils.findFriendlyRequestPathContainsKeyword("CaseListPage.ivp");
    if (StringUtils.isEmpty(friendlyRequestPath)) {
      friendlyRequestPath = OPEN_CASES_LIST;
    }
    String requestPath = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
      TaskUtils.updateTaskStartedAttribute(false);
      PortalNavigator.redirect(requestPath);
    }
  }
  
  public boolean showDestroyLink(ICase caseItem) {
    return caseActionBean.canDestroy(caseItem)
        && caseItem.getBusinessState() != CaseBusinessState.DONE
        && caseItem.getBusinessState() != CaseBusinessState.DESTROYED;
  }

  public void initCaseDescription(ICase caseDetails) {
    setCaseDetailsDescription(caseDetails.descriptions().current());
  }

  public void updateCaseDescription(ICase caseDetails) {
    if (caseDetails == null || caseDetailsDescription == null) {
      return;
    }
    caseDetails.descriptions().current(caseDetailsDescription);
  }

  public String getCaseDetailsDescription() {
    return caseDetailsDescription;
  }

  public void setCaseDetailsDescription(String caseDetailsDescription) {
    this.caseDetailsDescription = caseDetailsDescription;
  }

  /**
   * Gets visible columns on Case list page.
   * 
   * @param dataModel
   * @return visible columns
   */
  public List<String> getColumns(RelatedCaseLazyDataModel dataModel) {
    List<String> visibilityColumns = new ArrayList<>();
    visibilityColumns.addAll(dataModel.getSelectedColumns());

    /*
     * In UI we have a column called "Name / Description", but PortalRequiredColumns contains only "Name" column, so
     * that we need to check and add "Description" to Excel file
     */
    List<String> requiredColumns = dataModel.getPortalRequiredColumns();
    if (requiredColumns != null && requiredColumns.contains(CaseSortField.NAME.name())) {
      visibilityColumns.add(RelatedCaseLazyDataModel.DESCRIPTION);
    }
    return visibilityColumns;
  }
  
  public int getMaxCaseNumberInExcel() {
    return Exporter.MAX_ROW_NUMBER_IN_EXCEL;
  }
  
  public boolean isDisplayColumn(RelatedCaseLazyDataModel dataModel, String columnName) {
    return dataModel.getSelectedColumns().contains(columnName);
  }

  public void handleRowSelectEventOnRelatedTaskList(SelectEvent<Object> event) throws IOException {
    ITask task = (ITask) event.getObject();
    handleSelectedTaskInDialog(task, inFrame);
  }
  
  private void handleSelectedTaskInDialog(ITask task, boolean isInFrame) throws IOException {
    if(isInFrame) {
      navigateToSelectedTaskDetails(task);
    }else {
      handleSelectedTaskInCaseDetailsList(task);
    }
  }
  
  private void handleSelectedTaskInCaseDetailsList(ITask task) throws IOException {
    if (isRunningTaskWhenClickingOnTaskInList) {
      handleStartTask(task);
    } else {
      navigateToSelectedTaskDetails(task);
    }
  }
  
  private void handleStartTask(ITask task) throws IOException {
    selectedTask = task;
    TaskUtils.handleStartTask(task, PortalPage.CASE_DETAIL_FROM_TASK, PortalConstants.RESET_TASK_CONFIRMATION_DIALOG);
  }

  public void navigateToSelectedTaskDetails(ITask task) {
    if (inFrame) {
      PortalNavigator.navigateToPortalTaskDetailsInFrame(task.uuid());
    } else {
      PortalNavigator.navigateToPortalTaskDetails(task.uuid());
    }
  }

  public void navigateToSelectedCaseDetails(SelectEvent<Object> event) {
    String uuid = ((ICase) event.getObject()).uuid();
    navigateToCaseDetails(uuid);
  }

  public void navigateToCaseDetails(String uuid) {
    if (inFrame) {
      PortalNavigator.navigateToPortalCaseDetailsInFrame(uuid, false);
    } else {
      PortalNavigator.navigateToPortalCaseDetails(uuid);
    }
  }

  @Override
  protected boolean findConfigByPredefinedFilters(List<CaseDetails> configurations) {
    ICase caseInfo = Objects.isNull(this.selectedCase) ? getCaseInfoFromData() : this.selectedCase;
    if (Objects.isNull(caseInfo)) {
      return false;
    }

    for (CaseDetails config : configurations) {
      if (isFilterByCategories(caseInfo.getCategoryPath(), config.getFilters())
          || isFilterByStates(caseInfo.getState().name(), config.getFilters())) {
        this.configuration = config;
        return true;
      }
    }
    return false;
  }

  public ICase getCaseInfoFromData() {
    return Attrs.currentContext().getAttribute("#{data.caseInfo}", ICase.class);
  }

  public String getHistoryWidgetComponentId(String clientId) {
    int widgetPosition = getWidgetPositionByType("HistoryWidget");
    if (widgetPosition > -1) {
      return clientId + ":widgets:" + widgetPosition +":history-container";
    }
    return "";
  }

  @Override
  protected Class<CaseDetails> getConfigurationType() {
    return CaseDetails.class;
  }

  @Override
  public String getVariableKey() {
    return PortalVariable.CASE_DETAIL.key;
  }

  @Override
  protected String getDefaultConfigId() {
    return "default-case-detail";
  }

  public boolean isHideCaseDocument() {
    return isHideCaseDocument;
  }

  public void setHideCaseDocument(boolean isHideCaseDocument) {
    this.isHideCaseDocument = isHideCaseDocument;
  }

  public boolean isTaskStartedInDetails() {
    return isTaskStartedInDetails;
  }

  public void setTaskStartedInDetails(boolean isTaskStartedInDetails) {
    this.isTaskStartedInDetails = isTaskStartedInDetails;
  }

  public boolean isShowBackButton() {
    return showBackButton;
  }

  public void setShowBackButton(boolean showBackButton) {
    this.showBackButton = showBackButton;
  }

  public ICase getSelectedCase() {
    return selectedCase;
  }

  public void setSelectedCase(ICase selectedCase) {
    this.selectedCase = selectedCase;
  }

  public boolean isInFrame() {
    return inFrame;
  }

  public void setInFrame(boolean inFrame) {
    this.inFrame = inFrame;
  }
  
  public ITask getSelectedTask() {
    return selectedTask;
  }

  public void setSelectedTask(ITask selectedTask) {
    this.selectedTask = selectedTask;
  }

  public SortMeta getSortById() {
    return SortFieldUtil.buildSortMeta("ID", true);
  }

  public SortMeta getSortByTimestamp() {
    return SortFieldUtil.buildSortMeta("timestamp", true);
  }

  public SortMeta getSortByCreationTimestamp() {
    return SortFieldUtil.buildSortMeta("creation.timestamp", true);
  }

  public String getCaseDetailsUrl() {
    return caseDetailsUrl;
  }

  public void setCaseDetailsUrl(String caseDetailsUrl) {
    this.caseDetailsUrl = caseDetailsUrl;
  }

  public Boolean getIsShowShareButton() {
    return isShowShareButton;
  }

  public void setIsShowShareButton(Boolean isShowShareButton) {
    this.isShowShareButton = isShowShareButton;
  }

  public boolean isHideCaseCreator() {
    return isHideCaseCreator;
  }

  public void setHideCaseCreator(boolean isHideCaseCreator) {
    this.isHideCaseCreator = isHideCaseCreator;
  }

}

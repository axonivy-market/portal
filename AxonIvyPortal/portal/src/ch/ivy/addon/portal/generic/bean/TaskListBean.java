package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

import com.axonivy.portal.service.DeepLTranslationService;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInTaskList;
import ch.ivy.addon.portalkit.enums.CaseEmptyMessage;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.TaskEmptyMessage;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class TaskListBean implements Serializable {

  private static final long serialVersionUID = -2000024036297633880L;

  protected Dashboard selectedDashboard;
  private String selectedDashboardId;
  protected DashboardWidget widget;
  protected boolean isReadOnlyMode = true;
  private List<WidgetFilterModel> widgetFilters;
  private List<WidgetFilterModel> deleteFilters;
  private ITask selectedTask;
  private boolean isRunningTaskWhenClickingOnTaskInList;
  private CaseEmptyMessage noCasesMessage;
  private TaskEmptyMessage noTasksMessage;
  private List<DashboardTemplate> dashboardTemplates;
  protected String translatedText;
  protected String warningText;

  @PostConstruct
  public void init() {

    buildWidgetModels(selectedDashboard);
    isRunningTaskWhenClickingOnTaskInList = GlobalSettingService.getInstance()
        .findGlobalSettingValue(GlobalVariable.DEFAULT_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST)
        .equals(BehaviourWhenClickingOnLineInTaskList.RUN_TASK.name());
  }


  protected void buildWidgetModels(Dashboard dashboard) {
    if (dashboard == null || CollectionUtils.isEmpty(dashboard.getWidgets())) {
      return;
    }
    for (var widget : dashboard.getWidgets()) {
      DashboardWidgetUtils.buildWidgetColumns(widget);
      if (!(widget instanceof SingleProcessDashboardWidget)) {
        WidgetFilterService.getInstance().applyUserFilterFromSession(widget);
      }
      DashboardWidgetUtils.removeStyleNewWidget(widget);
    }
  }

  public void navigateToSelectedTaskDetails(SelectEvent<Object> event) {
    String uuid = ((ITask) event.getObject()).uuid();
    PortalNavigator.navigateToPortalTaskDetails(uuid);
  }


  public void handleRowSelectEventOnTaskWidget(SelectEvent<Object> event) throws IOException {
    ITask task = ((ITask) event.getObject());
    handleSelectedTask(task);
  }

  private void handleSelectedTask(ITask task) throws IOException {
    if (isRunningTaskWhenClickingOnTaskInList) {
      handleStartTask(task);
    } else {
      navigateToSelectedTaskDetails(task);
    }
  }


  public void handleStartTask(ITask task) throws IOException {
    selectedTask = task;
    TaskUtils.handleStartTask(task, PortalPage.HOME_PAGE, PortalConstants.RESET_TASK_CONFIRMATION_DIALOG);
  }

  public void navigateToSelectedTaskDetails(ITask task) {
    PortalNavigator.navigateToPortalTaskDetails(task.uuid());
  }


  public void resetAndOpenTask() throws IOException {
    TaskUtils.resetTask(selectedTask);
    FacesContext.getCurrentInstance().getExternalContext().redirect(selectedTask.getStartLinkEmbedded().getRelative());
  }


  protected IUser currentUser() {
    return Ivy.session().getSessionUser();
  }


  public int getMaxRowNumberInExcel() {
    return Exporter.MAX_ROW_NUMBER_IN_EXCEL;
  }


  protected List<String> getSupportedLanguages() {
    return LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
  }


  public boolean isShowTranslation(DisplayName title) {
    return DeepLTranslationService.getInstance().isShowTranslation(title.getLocale());
  }


  public boolean isFocus(DisplayName title) {
    return !isShowTranslation(title) && title.getLocale().getLanguage().equals(UserUtils.getUserLanguage());
  }


  public String getTranslatedText() {
    return translatedText;
  }


  public void applyTranslatedText(DisplayName displayName) {
    if (StringUtils.isNotBlank(translatedText)) {
      displayName.setValue(translatedText);
      translatedText = "";
    }
  }


  public boolean isRequiredField(DisplayName displayName) {
    String currentLanguage = UserUtils.getUserLanguage();
    String displayLanguage = displayName.getLocale().getLanguage();
    return currentLanguage.equals(displayLanguage);
  }


  public String getWarningText() {
    return warningText;
  }
}

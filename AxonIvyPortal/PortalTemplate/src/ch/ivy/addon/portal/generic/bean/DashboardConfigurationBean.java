package ch.ivy.addon.portal.generic.bean;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidgetParam;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetSample;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.Dates;

@ViewScoped
@ManagedBean
public class DashboardConfigurationBean extends DashboardBean implements Serializable {

  private static final long serialVersionUID = -7803140491152435429L;
  private static final String WIDGET_ID_PATTERN = "%s_%s";
  protected List<WidgetSample> samples;
  private String newWidgetHeader;
  private DashboardWidget deleteWidget;
  private List<String> categories;

  @PostConstruct
  public void initConfigration() {
    super.init();
    HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String userAgent = request.getHeader("user-agent");

    this.isReadOnlyMode = userAgent.matches(".*Android.*|.*webOS.*|.*iPhone.*|.*iPad.*|.*iPod.*|.*BlackBerry.*|.*IEMobile.*|.*Opera Mini.*");

    samples = List.of(taskSample(), caseSample(), statisticSample(), processSample(), customSample());
  }

  private WidgetSample taskSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/taskList"), DashboardWidgetType.TASK,
        "task-widget-sample.png");
  }

  private WidgetSample caseSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/caseList"), DashboardWidgetType.CASE,
        "case-widget-sample.png");
  }

  private WidgetSample statisticSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/statisticWidget"), DashboardWidgetType.STATISTIC,
        "statistic-widget-sample.png");
  }

  private WidgetSample processSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/processList"), DashboardWidgetType.PROCESS,
        "process-widget-sample.png");
  }

  private WidgetSample customSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/customWidget"), DashboardWidgetType.CUSTOM,
        "");
  }

  private void backupCategories() {
    this.categories = new ArrayList<>();
    if (this.widget instanceof TaskDashboardWidget) {
      TaskDashboardWidget taskWidget = (TaskDashboardWidget) this.widget;
      this.categories = taskWidget.getDataModel().getCategories();
      
    } else if (this.widget instanceof CaseDashboardWidget) {
      CaseDashboardWidget caseWidget = (CaseDashboardWidget) this.widget;
      this.categories = caseWidget.getDataModel().getCategories();
    }
  }

  public void restore() throws IOException {
    removeDashboardInUserProperty();

    List<Dashboard> defaultDashboards = this.defaultDashboards();
    this.selectedDashboard = defaultDashboards.get(defaultDashboards.indexOf(this.getSelectedDashboard()));
    this.dashboards.set(this.dashboards.indexOf(this.getSelectedDashboard()), this.getSelectedDashboard());
    buildSubWidgetModels(this.getSelectedDashboard().getWidgets());
  }

  public void create(WidgetSample sample) {
    this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader", Arrays.asList(EMPTY));
    switch (sample.getType()) {
      case CASE:
        this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/common/case")));
        this.widget = getDefaultCaseDashboardWidget();
        break;
      case TASK:
        this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/common/tasks")));
        this.widget = getDefaultTaskDashboardWidget();
        break;
      case PROCESS:
        this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/common/processes")));
        this.widget = getDefaultProcessDashboardWidget();
        break;
      case CUSTOM:
        this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/customWidget")));
        this.widget = getDefaultCustomDashboardWidget();
        break;
      case STATISTIC:
        this.widget = null;
        break;
      default:
        break;
    }
  }

  public void removeWidget() throws JsonProcessingException {
    if (this.deleteWidget != null) {
      this.getSelectedDashboard().getWidgets().remove(deleteWidget);
      saveOrUpdateDashboardToUserProperty(getSelectedDashboard());
    }
  }
  
  private CaseDashboardWidget getDefaultCaseDashboardWidget() {
    String widgetId = generateNewWidgetId(DashboardWidgetType.CASE);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCases");
    return CaseDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  public TaskDashboardWidget getDefaultTaskDashboardWidget() {
    String widgetId = generateNewWidgetId(DashboardWidgetType.TASK);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourTasks");
    return TaskDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  public ProcessDashboardWidget getDefaultProcessDashboardWidget() {
    String widgetId = generateNewWidgetId(DashboardWidgetType.PROCESS);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourProcesses");
    return ProcessDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  public CustomDashboardWidget getDefaultCustomDashboardWidget() {
    String widgetId = generateNewWidgetId(DashboardWidgetType.CUSTOM);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCustomWidget");
    CustomDashboardWidget a =  CustomDashboardWidget.buildDefaultWidget(widgetId, widgetName);
    return a;
  }

  public String generateNewWidgetId(DashboardWidgetType type) {
    final String widgetIdPrefix = String.format(WIDGET_ID_PATTERN, type.name(), EMPTY).toLowerCase();

    if (CollectionUtils.isNotEmpty(getSelectedDashboard().getWidgets())) {
      List<String> ids = getSelectedDashboard().getWidgets().stream()
              .filter(widget -> widget.getId().startsWith(widgetIdPrefix))
              .map(DashboardWidget::getId).collect(Collectors.toList());
      if (CollectionUtils.isNotEmpty(ids)) {
        Integer maxId = Collections.max(ids.stream()
                          .map(id -> Integer.parseInt(id.replace(widgetIdPrefix, EMPTY)))
                          .collect(Collectors.toList()));
        if (maxId != null && maxId >= 0) {
          String widgetId = Integer.toString(maxId + 1);
          return String.format(WIDGET_ID_PATTERN, type.name(), widgetId).toLowerCase();
        }
      }
    }

    return String.format(WIDGET_ID_PATTERN, type.name(), 0).toLowerCase();
  }

  public void saveWidget() throws JsonProcessingException, ParseException {
    if (this.widget.getType() == DashboardWidgetType.PROCESS) {
      ProcessDashboardWidget processWidget = (ProcessDashboardWidget) this.widget;
      if (processWidget.getDisplayMode() == ProcessWidgetMode.FULL_MODE) {
        processWidget.getLayout().setHeight(4);
        processWidget.getLayout().setWidth(2);
        processWidget.setName(processWidget.getProcess() != null ? processWidget.getProcess().getName() : "");
        processWidget.setDisplayProcesses(new ArrayList<>());
        processWidget.setProcesses(new ArrayList<>());
        processWidget.setProcessPath(processWidget.getProcess().getId());
      } else if (processWidget.getDisplayMode() == ProcessWidgetMode.COMBINED_MODE) {
        processWidget.getLayout().setHeight(6);
        processWidget.getLayout().setWidth(5);
        processWidget.setDisplayProcesses(new ArrayList<>());
        processWidget.setProcesses(new ArrayList<>());
        processWidget.setName(processWidget.getProcess() != null ? processWidget.getProcess().getName() : "");
        processWidget.setProcessPath(processWidget.getProcess().getId());
      } else {
        processWidget.getLayout().setHeight(6);
        processWidget.getLayout().setWidth(2);
        processWidget.setProcess(null);
        processWidget.getUserFilter().setCategories(processWidget.getCategories());
        boolean isAllProcessesSelected = CollectionUtils.isEmpty(processWidget.getCategories())
            && (CollectionUtils.isEmpty(processWidget.getProcesses())
                || getAllPortalProcesses().size() == processWidget.getProcesses().size()); 
        processWidget.setSelectedAllProcess(isAllProcessesSelected);
        updateProcessesOfWidget(processWidget);
      }
    } else if (this.widget.getType() == DashboardWidgetType.TASK) {
      updateTaskWidgetAfterSave();
      backupCategories();
    }  else if (this.widget.getType() == DashboardWidgetType.CASE) {
      updateCaseWidgetAfterSave();
      backupCategories();
    } else if (this.widget.getType() == DashboardWidgetType.CUSTOM) {
      CustomDashboardWidget customWidget =  (CustomDashboardWidget) widget;

      for (CustomDashboardWidgetParam param : customWidget.getData().getCustomParams()) {
        switch (param.getType()) {
          case BOOLEAN:
            param.setValue(param.getValueBoolean().toString());
            break;
          case DATE:
            param.setValue(Dates.format(param.getValueDate()));
            break;
          case USER:
            param.setValue(param.getValueUser().getName());
            break;
          default:
            break;
        }
      }
    }
    resetUserFilter();
    this.widget.buildPredefinedFilterData();
    if (CollectionUtils.isEmpty(this.getSelectedDashboard().getWidgets())) {
      this.getSelectedDashboard().setWidgets(new ArrayList<>());
    }
    List<DashboardWidget> widgets = this.getSelectedDashboard().getWidgets();
    if (widgets.contains(this.widget)) {
      widgets.set(widgets.indexOf(this.widget), this.widget);
    } else {
      widgets.add(widget);
    }
    saveSelectedWidget();
    this.widget = null;
  }

  private void updateCaseWidgetAfterSave() {
    CaseDashboardWidget caseWidget = (CaseDashboardWidget) this.widget;
    List<String> userFilters = caseWidget.getDataModel().getUserFilterCategories();
    if (CollectionUtils.isNotEmpty(userFilters)) {
      List<String> userFiltersToRemove = new ArrayList<>();
      for (String userFilter : userFilters) {
        if (!caseWidget.getDataModel().getCategories().contains(userFilter)) {
          userFiltersToRemove.add(userFilter);
        }
      }
      caseWidget.getDataModel().getUserFilterCategories().removeAll(userFiltersToRemove);
      if (CollectionUtils.isEmpty(caseWidget.getDataModel().getUserFilterCategories())) {
        caseWidget.setUserDefinedFiltersCount(null);
      }
    }
  }

  private void updateTaskWidgetAfterSave() {
    TaskDashboardWidget taskWidget = (TaskDashboardWidget) this.widget;
    List<String> userFilters = taskWidget.getDataModel().getUserFilterCategories();
    if (CollectionUtils.isNotEmpty(userFilters)) {
      List<String> userFiltersToRemove = new ArrayList<>();
      for (String userFilter : userFilters) {
        if (!taskWidget.getDataModel().getCategories().contains(userFilter)) {
          userFiltersToRemove.add(userFilter);
        }
      }
      taskWidget.getDataModel().getUserFilterCategories().removeAll(userFiltersToRemove);
      if (CollectionUtils.isEmpty(taskWidget.getDataModel().getUserFilterCategories())) {
        taskWidget.setUserDefinedFiltersCount(null);
      }
    }
  }

  private void updateProcessesOfWidget(ProcessDashboardWidget widget) {
    List<DashboardProcess> displayProcesses;
    if (widget.isSelectedAllProcess()) {
      displayProcesses = getAllPortalProcesses();
      widget.setProcesses(new ArrayList<>());
    } else if (CollectionUtils.isNotEmpty(widget.getProcesses())) {
      displayProcesses = widget.getProcesses();
      widget.setProcesses(displayProcesses);
    } else {
      List<DashboardProcess> allPortalProcesses = getAllPortalProcesses();
      displayProcesses = allPortalProcesses.stream()
          .filter(process -> isProcessMatchedCategory(process, widget.getCategories())).collect(Collectors.toList());
    }

    widget.setDisplayProcesses(displayProcesses);
    widget.setOriginalDisplayProcesses(displayProcesses);
  }

  private boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    return categories.indexOf(process.getCategory()) > -1
        || (StringUtils.isBlank(process.getCategory()) && hasNoCategory);
  }

  private List<DashboardProcess> getAllPortalProcesses() {
    DashboardProcessBean dashboardProcessBean = ManagedBeans.get("dashboardProcessBean");
    return dashboardProcessBean == null ? new ArrayList<>() : dashboardProcessBean.getAllPortalProcesses();
  }

  private void resetUserFilter() {
    if (this.widget.getType() == DashboardWidgetType.TASK) {
      ((TaskDashboardWidget) this.widget).setInConfiguration(false);
    }
    if (this.widget.getType() == DashboardWidgetType.CASE) {
      ((CaseDashboardWidget) this.widget).setInConfiguration(false);
    }
    this.widget.resetUserFilters();
  }

  public void setEditWidget(DashboardWidget widget) {
    this.setWidget(widget);
    this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/editWidgetHeader");
  }


  public void restoreWidgetData() {
    if (this.widget instanceof CaseDashboardWidget) {
      ((CaseDashboardWidget)this.widget).getDataModel().setCategories(this.categories);
    } else if (this.widget instanceof TaskDashboardWidget) {
      ((TaskDashboardWidget)this.widget).getDataModel().setCategories(this.categories);
    }
  }

  public List<WidgetSample> getSamples() {
    return samples;
  }

  public String getNewWidgetHeader() {
    return newWidgetHeader;
  }

  public DashboardWidget getDeleteWidget() {
    return deleteWidget;
  }

  public void setDeleteWidget(DashboardWidget deleteWidget) {
    this.deleteWidget = deleteWidget;
  }

  public void navigatetoDashboardPage() {
    PortalNavigator.navigateToNewDashboard();
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }
}

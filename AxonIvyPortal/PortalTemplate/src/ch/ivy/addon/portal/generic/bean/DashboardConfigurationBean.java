package ch.ivy.addon.portal.generic.bean;

import static ch.ivy.addon.portalkit.constant.DashboardConstants.WIDGET_ID_PATTERN;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.CASE;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.PROCESS;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.TASK;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.DashboardConstants;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidgetParam;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetSample;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.Dates;

@ViewScoped
@ManagedBean
public class DashboardConfigurationBean extends DashboardBean implements Serializable {

  private static final long serialVersionUID = -7803140491152435429L;
  protected List<WidgetSample> samples;
  private String newWidgetHeader;
  private boolean isEditWidget;
  private String newWidgetId;
  private DashboardWidget deleteWidget;
  private ProcessDashboardWidget originalProcessWidget;
  private List<String> categories;
  private Long portalGridsCurrentRow;
  
  @PostConstruct
  public void initConfigration() {
    super.init();
    HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String userAgent = request.getHeader("user-agent");

    this.isReadOnlyMode = userAgent.matches(".*Android.*|.*webOS.*|.*iPhone.*|.*iPad.*|.*iPod.*|.*BlackBerry.*|.*IEMobile.*|.*Opera Mini.*");

    samples = List.of(taskSample(), caseSample(), processSample(), customSample());
  }

  private WidgetSample taskSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/taskList"), TASK,
        "task-widget-sample.png", translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/taskListIntroduction"));
  }

  private WidgetSample caseSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/caseList"), CASE,
        "case-widget-sample.png", translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/caseListIntroduction"));
  }

  private WidgetSample processSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/processList"), PROCESS,
        "process-widget-sample.png", translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/processListIntroduction"));
  }

  private WidgetSample customSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/customWidget"), DashboardWidgetType.CUSTOM,
        "si si-cog-double-2", translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/customWidgetIntroduction"), true);
  }

  public void restore() {
    removeDashboardInUserProperty();
    List<Dashboard> defaultDashboards = defaultDashboards();
    this.selectedDashboard = defaultDashboards.get(defaultDashboards.indexOf(getSelectedDashboard()));
    this.dashboards.set(this.dashboards.indexOf(getSelectedDashboard()), getSelectedDashboard());
    buildWidgetModels(getSelectedDashboard());
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
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/statistic/timePeriod/custom")));
        this.widget = getDefaultCustomDashboardWidget();
        break;
      case STATISTIC:
        this.widget = null;
        break;
      default:
        break;
    }
  }

  public void removeWidget() {
    if (this.deleteWidget != null) {
      this.getSelectedDashboard().getWidgets().remove(deleteWidget);
      DashboardService.getInstance().saveDashboardForSessionUser(getSelectedDashboard());
    }
  }

  private CaseDashboardWidget getDefaultCaseDashboardWidget() {
    String widgetId = generateNewWidgetId(CASE);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCases");
    return (CaseDashboardWidget) DashboardWidgetUtils.buildDefaultWidget(widgetId, widgetName, CASE);
  }

  public TaskDashboardWidget getDefaultTaskDashboardWidget() {
    String widgetId = generateNewWidgetId(TASK);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourTasks");
    return (TaskDashboardWidget) DashboardWidgetUtils.buildDefaultWidget(widgetId, widgetName, TASK);
  }

  public ProcessDashboardWidget getDefaultProcessDashboardWidget() {
    String widgetId = generateNewWidgetId(PROCESS);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourProcesses");
    return (ProcessDashboardWidget) DashboardWidgetUtils.buildDefaultWidget(widgetId, widgetName, PROCESS);
  }

  public CustomDashboardWidget getDefaultCustomDashboardWidget() {
    String widgetId = generateNewWidgetId(DashboardWidgetType.CUSTOM);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCustomWidget");
    return CustomDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  public String generateNewWidgetId(DashboardWidgetType type) {
    String uuid = UUID.randomUUID().toString().replace("-", "");
    return String.format(WIDGET_ID_PATTERN, type.name(), uuid).toLowerCase();
  }

  public void saveWidget() {
    switch (widget.getType()) {
      case PROCESS:
        ProcessDashboardWidget processWidget = (ProcessDashboardWidget) this.widget;
        if (processWidget.getDisplayMode() == ProcessWidgetMode.FULL_MODE) {
          updateProcessWidget(processWidget, 4, 2);
        } else if (processWidget.getDisplayMode() == ProcessWidgetMode.COMBINED_MODE) {
          updateProcessWidget(processWidget, 6, 7);
        } else if (processWidget.getDisplayMode() == ProcessWidgetMode.COMPACT_MODE) {
          processWidget.getLayout().setHeight(8);
          processWidget.getLayout().setWidth(3);
          processWidget.setProcess(null);
          unifyCompactProcessCategory(processWidget);
          updateProcessesOfWidget(processWidget);
        } else if (processWidget.getDisplayMode() == ProcessWidgetMode.IMAGE_MODE) {
          updateProcessWidget(processWidget, 6, 2);
        }
        break;
      case CUSTOM:
        CustomDashboardWidget customWidget =  (CustomDashboardWidget) widget;
        loadCustomWidget(customWidget);
      default:
        break;
    }
    updateWidgetPosition(widget);
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
    newWidgetId = widget.getId();
    widget = null;
    isEditWidget = false;
    PrimeFaces.current().ajax().update("grid-stack");
  }

  public void unifyCompactProcessCategory(ProcessDashboardWidget processWidget) {
    processWidget.setSelectedAllProcess(false);
    processWidget.setCategories(null);
    processWidget.getFilterableColumns().stream()
        .filter(column -> DashboardStandardProcessColumn.CATEGORY.getField().equalsIgnoreCase(column.getField()))
        .findAny().ifPresent(categoryColumn -> {
          processWidget.setCategories(categoryColumn.getFilterList());
        });
    if (CollectionUtils.isEmpty(processWidget.getCategories()) && (CollectionUtils.isEmpty(processWidget.getProcesses())
        || DashboardWidgetUtils.getAllPortalProcesses().size() == processWidget.getProcesses().size())) {
      processWidget.setSelectedAllProcess(true);
    }
  }

  private void loadCustomWidget(CustomDashboardWidget customWidget) {
    if (customWidget.getData().getType() == DashboardCustomWidgetType.PROCESS) {
      for (CustomDashboardWidgetParam param : customWidget.getData().getParams()) {
        switch (param.getType()) {
          case BOOLEAN:
            param.setValue(param.getValueBoolean().toString());
            break;
          case DATE:
            param.setValue(Dates.format(param.getValueDate()));
            break;
          case USER:
            param.setValue(Optional.ofNullable(param.getValueUser()).map(UserDTO::getName).orElse(null));
            break;
          default:
            break;
        }
      }
    }
  }

  public void updatePortalGridsCurrentRow() {
    Map<String, String> requestParamMap = getRequestParameterMap();
    var currentRowNumber = Optional.ofNullable(requestParamMap.get("portalGridsCurrentRow")).orElse(StringUtils.EMPTY);
    if (currentRowNumber.isEmpty()) {
      portalGridsCurrentRow = 0l;
    }
    else {
      portalGridsCurrentRow = Long.valueOf(currentRowNumber);
    }
  }

  private void updateWidgetPosition(DashboardWidget widget) {
    if (isEditWidget) {
      return;
    }
    DashboardWidget lastWidget = null;
    for (var compareWidget : CollectionUtils.emptyIfNull(selectedDashboard.getWidgets())) {
      if (lastWidget == null) {
        lastWidget = compareWidget;
        continue;
      }
      if (lastWidget.getLayout().getAxisY() < compareWidget.getLayout().getAxisY()
          || (lastWidget.getLayout().getAxisY() == compareWidget.getLayout().getAxisY()
              && lastWidget.getLayout().getAxisX() < compareWidget.getLayout().getAxisX())) {
        lastWidget = compareWidget;
      }
    }
    if (lastWidget != null && widget != null) {
      var nextAxisX = lastWidget.getLayout().getAxisX() + lastWidget.getLayout().getWidth();
      var totalWidth = nextAxisX + widget.getLayout().getWidth();
      if (totalWidth <= 12) {
        widget.getLayout().setAxisX(nextAxisX);
        widget.getLayout().setAxisY(lastWidget.getLayout().getAxisY());
      }
      else {
        widget.getLayout().setAxisX(0);
        widget.getLayout().setAxisY(portalGridsCurrentRow.intValue());
      }
    }

    if (StringUtils.isEmpty(widget.getLayout().getStyleClass())) {
      widget.getLayout().setStyleClass(DashboardConstants.NEW_WIDGET_STYLE_CLASS);
    } else {
      widget.getLayout().setStyleClass(widget.getLayout().getStyleClass().concat(DashboardConstants.NEW_WIDGET_STYLE_CLASS));
    }
  }

  private void updateProcessWidget(ProcessDashboardWidget processWidget, int height, int width) {
    processWidget.getLayout().setHeight(height);
    processWidget.getLayout().setWidth(width);
    processWidget.setDisplayProcesses(new ArrayList<>());
    processWidget.setProcesses(new ArrayList<>());
    DashboardProcess process = processWidget.getProcess();
    processWidget.setName(Objects.isNull(process) ? EMPTY : process.getName());
    processWidget.setProcessPath(Objects.isNull(process) ? EMPTY : process.getId());
  }

  private void updateProcessesOfWidget(ProcessDashboardWidget widget) {
    List<DashboardProcess> displayProcesses;
    if (widget.isSelectedAllProcess()) {
      displayProcesses = DashboardWidgetUtils.getAllPortalProcesses();
      widget.setProcesses(new ArrayList<>());
    } else if (CollectionUtils.isNotEmpty(widget.getProcesses())) {
      displayProcesses = widget.getProcesses();
      widget.setProcesses(displayProcesses);
    } else {
      displayProcesses = DashboardWidgetUtils.getAllPortalProcesses().stream()
          .filter(process -> isProcessMatchedCategory(process, widget.getCategories()))
          .collect(Collectors.toList());
    }

    widget.setDisplayProcesses(displayProcesses);
    widget.setOriginalDisplayProcesses(displayProcesses);
  }

  private boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    return categories.indexOf(process.getCategory()) > -1
        || (StringUtils.isBlank(process.getCategory()) && hasNoCategory);
  }

  private void resetUserFilter() {
    if (TASK == widget.getType()) {
      ((TaskDashboardWidget) widget).setInConfiguration(false);
    }
    if (CASE == widget.getType()) {
      ((CaseDashboardWidget) widget).setInConfiguration(false);
    }
    if (PROCESS == widget.getType()) {
      ((ProcessDashboardWidget) widget).setInConfiguration(false);
    }
    this.widget.resetWidgetFilters();
  }

  public void prepareEditWidget(DashboardWidget widget) {
    if (widget instanceof ProcessDashboardWidget) {
      backupProcessWidget(widget);
    }
    setWidget(widget);
    newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/editWidgetHeader");
    isEditWidget = true;
  }

  private void backupProcessWidget(DashboardWidget widget) {
    originalProcessWidget = new ProcessDashboardWidget();
    originalProcessWidget.setName(widget.getName());
    originalProcessWidget.setDisplayMode(((ProcessDashboardWidget) widget).getDisplayMode());
    originalProcessWidget.setProcesses(((ProcessDashboardWidget) widget).getProcesses());
    originalProcessWidget.setProcess(((ProcessDashboardWidget) widget).getProcess());
    originalProcessWidget.setCategories(((ProcessDashboardWidget) widget).getCategories());
    originalProcessWidget.setProcessPath(((ProcessDashboardWidget) widget).getProcessPath());
  }

  public void restoreWidgetData() {
    if (widget instanceof ProcessDashboardWidget) {
      restoreProcessWidget();
    }
  }

  private void restoreProcessWidget() {
    if (originalProcessWidget != null) {
      ((ProcessDashboardWidget) widget).setDisplayMode(originalProcessWidget.getDisplayMode());
      ((ProcessDashboardWidget) widget).setProcess(originalProcessWidget.getProcess());
      ((ProcessDashboardWidget) widget).setProcessPath(originalProcessWidget.getProcessPath());
      ((ProcessDashboardWidget) widget).setProcesses(originalProcessWidget.getProcesses());
      ((ProcessDashboardWidget) widget).setCategories(originalProcessWidget.getCategories());
    }
  }

  public void reloadParamtersFromProcessForCustomWidget(DashboardWidget widget) {
    CustomDashboardWidget customWidget = (CustomDashboardWidget) widget;
    customWidget.loadParametersFromProcess();
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
    PortalNavigator.navigateToDashboard();
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }
  public String getNewWidgetId() {
    return newWidgetId;
  }

  public void setNewWidgetId(String newWidgetId) {
    this.newWidgetId = newWidgetId;
  }

  public boolean isEditWidget() {
    return isEditWidget;
  }
}


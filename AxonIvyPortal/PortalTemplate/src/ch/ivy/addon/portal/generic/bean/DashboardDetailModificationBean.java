package ch.ivy.addon.portal.generic.bean;

import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.CASE;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.CUSTOM;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.PROCESS;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.PROCESS_VIEWER;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.STATISTIC;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.TASK;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.component.dto.UserDTO;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bean.DashboardProcessBean;
import ch.ivy.addon.portalkit.constant.DashboardConstants;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CombinedProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidgetParam;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.FullProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ImageProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessViewerDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.StatisticDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetSample;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;

@ViewScoped
@ManagedBean
public class DashboardDetailModificationBean extends DashboardBean implements Serializable, PropertyChangeListener {

  private static final long serialVersionUID = -5272278165636659596L;
  private List<WidgetSample> samples;
  private String newWidgetHeader;
  private boolean isEditWidget;
  private String newWidgetId;
  private DashboardWidget deleteWidget;
  private Long portalGridsCurrentRow;
  private String selectedDashboardId;
  private boolean isPublicDashboard;
  private List<String> categories;
  private String restoreDashboardMessage;
  private Optional<DashboardTemplate> foundTemplate;

  @PostConstruct
  public void initConfigration() {
    selectedDashboardId = Attrs.currentContext().getAttribute("#{data.dashboardId}", String.class);
    isPublicDashboard = Attrs.currentContext().getAttribute("#{data.isPublicDashboard}", Boolean.class);
    super.init();
    isReadOnlyMode = false;
    ((DashboardProcessBean) ManagedBeans.get("dashboardProcessBean")).addPropertyChangeListener(this);
    foundTemplate = findSelectedTemplate(getSelectedDashboard().getTemplateId());
  }

  public void initSampleWidgets() {
    if (CollectionUtils.isEmpty(samples)) {
      samples = List.of(taskSample(), caseSample(), processSample(), statisticSample(), customSample(), processViewerSample());
    }
  }

  @Override
  protected List<Dashboard> collectDashboards() {
    List<Dashboard> collectedDashboards = new ArrayList<>();
    try {
      if (isPublicDashboard) {
        collectedDashboards = DashboardUtils.getPublicDashboards();
      } else {
        String dashboardInUserProperty = readDashboardBySessionUser();
        collectedDashboards = getVisibleDashboards(dashboardInUserProperty);
      }
    } catch (PortalException e) {
      // If errors like parsing JSON errors, ignore them
      Ivy.log().error(e);
    }
    return collectedDashboards.stream()
        .filter(dashboard -> dashboard.getId().equals(selectedDashboardId)).collect(Collectors.toList());
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

  private WidgetSample statisticSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/statisticChartWidget"), STATISTIC,
        "statistic-widget-sample.png", translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/statisticChartIntroduction"));
  }

  private WidgetSample customSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/customWidget"), CUSTOM,
        "si si-cog-double-2", translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/customWidgetIntroduction"), true);
  }

  private WidgetSample processViewerSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/ProcessViewer/ProcessViewerText"), PROCESS_VIEWER,
        "si si-hierarchy-6 si-rotate-270", translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/processViewerIntroduction"), true);
  }

  public void restore() {
    selectedDashboardId = getSelectedDashboard().getId();
    if (StringUtils.isBlank(getSelectedDashboard().getTemplateId())) {
      selectedDashboard.setWidgets(new ArrayList<>());
    } else {
      var foundTemplate = findSelectedTemplate(getSelectedDashboard().getTemplateId());
      foundTemplate.ifPresent(template -> {
        if (DashboardConstants.CREATE_FROM_SCRATCH.equals(template.getId())) {
          selectedDashboard.setWidgets(new ArrayList<>());
        } else {
          selectedDashboard.setWidgets(new ArrayList<>(template.getDashboard().getWidgets()));
        }
      });
      for(DashboardWidget widget : getSelectedDashboard().getWidgets()) {
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(widget.getType()));
      }
    }
    buildWidgetModels(getSelectedDashboard());
    save();
    PortalNavigator.navigateToDashboardDetailsPage(selectedDashboardId, isPublicDashboard);
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
        this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/statisticChartWidget")));
        this.widget = getDefaultStatisticDashboardWidget();
        break;
      case PROCESS_VIEWER:
        this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/ProcessViewer/ProcessViewerText")));
        this.widget = getDefaultProcessViewerDashboardWidget();
        break;
      default:
        break;
    }
  }

  public void removeWidget() {
    if (this.getDeleteWidget() != null) {
      this.getSelectedDashboard().getWidgets().remove(getDeleteWidget());
      DashboardService.getInstance().save(getSelectedDashboard());
    }
  }

  private CaseDashboardWidget getDefaultCaseDashboardWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(CASE);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCases");
    return (CaseDashboardWidget) DashboardWidgetUtils.buildDefaultWidget(widgetId, widgetName, CASE);
  }

  private TaskDashboardWidget getDefaultTaskDashboardWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(TASK);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourTasks");
    return (TaskDashboardWidget) DashboardWidgetUtils.buildDefaultWidget(widgetId, widgetName, TASK);
  }

  private ProcessDashboardWidget getDefaultProcessDashboardWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(PROCESS);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourProcesses");
    return (ProcessDashboardWidget) DashboardWidgetUtils.buildDefaultWidget(widgetId, widgetName, PROCESS);
  }

  private StatisticDashboardWidget getDefaultStatisticDashboardWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(STATISTIC);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourStatistics");
    return (StatisticDashboardWidget) DashboardWidgetUtils.buildDefaultWidget(widgetId, widgetName, STATISTIC);
  }

  private CustomDashboardWidget getDefaultCustomDashboardWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(DashboardWidgetType.CUSTOM);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCustomWidget");
    return CustomDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  private ProcessViewerDashboardWidget getDefaultProcessViewerDashboardWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(DashboardWidgetType.PROCESS_VIEWER);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourProcessViewer");
    return ProcessViewerDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  public void saveWidget() {
    if (CollectionUtils.isEmpty(this.getSelectedDashboard().getWidgets())) {
      this.getSelectedDashboard().setWidgets(new ArrayList<>());
    }
    List<DashboardWidget> widgets = this.getSelectedDashboard().getWidgets();
    switch (widget.getType()) {
      case PROCESS:
        ProcessDashboardWidget processWidget = (ProcessDashboardWidget) this.widget;
        processWidget.setPreview(false);
        if (processWidget.getDisplayMode() == ProcessWidgetMode.FULL_MODE) {
          updateProcessWidget((SingleProcessDashboardWidget) processWidget, 4, 2);
        } else if (processWidget.getDisplayMode() == ProcessWidgetMode.COMBINED_MODE) {
          updateProcessWidget((SingleProcessDashboardWidget) processWidget, 6, 5);
        } else if (processWidget.getDisplayMode() == ProcessWidgetMode.COMPACT_MODE) {
          processWidget.getLayout().setHeight(8);
          processWidget.getLayout().setWidth(3);
          CompactProcessDashboardWidget compactProcessWidget = (CompactProcessDashboardWidget) processWidget;
          unifyCompactProcessCategory(compactProcessWidget);
          updateProcessesOfWidget(compactProcessWidget);
          if (CollectionUtils.isEmpty((compactProcessWidget).getFilterableColumns())) {
            (compactProcessWidget).buildFilterableColumns(DashboardWidgetUtils.initProcessFilterableColumns());
          }
        } else if (processWidget.getDisplayMode() == ProcessWidgetMode.IMAGE_MODE) {
          updateProcessWidget((SingleProcessDashboardWidget) processWidget, 6, 2);
        }
        DashboardWidget dashboardWidget =
            widgets.stream().filter(w -> widget.getId().equals(w.getId()) && w.getType() == DashboardWidgetType.PROCESS)
                .findFirst().orElse(null);
        if (dashboardWidget != null) {
          widgets.set(widgets.indexOf(dashboardWidget), widget);
        } else {
          widgets.add(widget);
        }
        break;
      case CUSTOM:
        CustomDashboardWidget customWidget =  (CustomDashboardWidget) widget;
        loadCustomWidget(customWidget);
        break;
      case PROCESS_VIEWER:
        ProcessViewerDashboardWidget processViewerWidget = (ProcessViewerDashboardWidget) this.widget;
        updateProcessViewerWidget(processViewerWidget);
        break;
      case STATISTIC:
        updateStatisticWidgetData(widget);
        break;
      default:
        break;
    }
    updateWidgetPosition(widget);
    resetUserFilter();
    this.widget.buildPredefinedFilterData();
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

  private void updateStatisticWidgetData(DashboardWidget widget) {
    var statisticWidget = (StatisticDashboardWidget) widget;
    var displayName = StatisticService.getInstance().getDisplayNameInUserLanguageForChart(statisticWidget.getChart());
    if (displayName != null) {
      statisticWidget.setName(displayName.getValue());
    }
  }

  private void unifyCompactProcessCategory(CompactProcessDashboardWidget processWidget) {
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

  private void updateProcessViewerWidget(ProcessViewerDashboardWidget processViewerWidget) {
    List<IProcessStart> startableProcessStarts = Ivy.session().getStartableProcessStarts();
    String userFriendlyRequestPath = startableProcessStarts.stream()
        .filter(process -> process.getLink().getRelative()
            .contentEquals(processViewerWidget.getProcess().getStartLink()))
        .findFirst().get().getUserFriendlyRequestPath();
    processViewerWidget.setProcessStart(userFriendlyRequestPath);
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

  private void updateProcessWidget(SingleProcessDashboardWidget processWidget, int height, int width) {
    processWidget.getLayout().setHeight(height);
    processWidget.getLayout().setWidth(width);
    DashboardProcess process = processWidget.getProcess();
    processWidget.setName(Objects.isNull(process) ? EMPTY : process.getName());
    processWidget.setProcessPath(Objects.isNull(process) ? EMPTY : process.getId());
  }

  private void updateProcessesOfWidget(CompactProcessDashboardWidget widget) {
    List<DashboardProcess> displayProcesses;
    List<String> processPaths = new ArrayList<>();
    if (widget.isSelectedAllProcess()) {
      displayProcesses = DashboardWidgetUtils.getAllPortalProcesses();
    } else if (CollectionUtils.isNotEmpty(widget.getProcesses())) {
      displayProcesses = widget.getProcesses();
      processPaths = getProcessPaths(displayProcesses);
    } else {
      displayProcesses = DashboardWidgetUtils.getAllPortalProcesses().stream()
          .filter(process -> isProcessMatchedCategory(process, widget.getCategories()))
          .collect(Collectors.toList());
    }

    widget.setProcessPaths(processPaths);
    widget.setDisplayProcesses(displayProcesses);
    widget.setOriginalDisplayProcesses(displayProcesses);
  }

  private boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    return categories.indexOf(process.getCategory()) > -1
        || (StringUtils.isBlank(process.getCategory()) && hasNoCategory);
  }

  private List<String> getProcessPaths(List<DashboardProcess> processes) {
    List<String> processPaths = new ArrayList<>();
    for (DashboardProcess process: processes) {
      processPaths.add(process.getId());
    }

    return processPaths;
  }

  private void resetUserFilter() {
    if (TASK == widget.getType()) {
      ((TaskDashboardWidget) widget).setInConfiguration(false);
    }
    if (CASE == widget.getType()) {
      ((CaseDashboardWidget) widget).setInConfiguration(false);
    }
    if (PROCESS == widget.getType()
        && ((ProcessDashboardWidget) widget).getDisplayMode() == ProcessWidgetMode.COMPACT_MODE) {
      ((CompactProcessDashboardWidget) widget).setInConfiguration(false);
    }
    this.widget.resetWidgetFilters();
  }

  public void prepareEditWidget(DashboardWidget widget) {
    switch (widget.getType()) {
      case PROCESS:
        ProcessDashboardWidget processDashboardWidget = (ProcessDashboardWidget) widget;
        ProcessDashboardWidget clonedWidget;
        switch (processDashboardWidget.getDisplayMode()) {
          case COMPACT_MODE:
            clonedWidget = new CompactProcessDashboardWidget((CompactProcessDashboardWidget) processDashboardWidget);
            break;
          case COMBINED_MODE:
            clonedWidget = new CombinedProcessDashboardWidget((CombinedProcessDashboardWidget) processDashboardWidget);
            break;
          case FULL_MODE:
            clonedWidget = new FullProcessDashboardWidget((FullProcessDashboardWidget) processDashboardWidget);
            break;
          case IMAGE_MODE:
            clonedWidget = new ImageProcessDashboardWidget((ImageProcessDashboardWidget) processDashboardWidget);
            break;
          default:
            clonedWidget = new ProcessDashboardWidget(processDashboardWidget);
            break;
        }
        setWidget(clonedWidget);
        break;
      case STATISTIC:
        var statisticDashboardWidget = new StatisticDashboardWidget((StatisticDashboardWidget) widget);
        setWidget(statisticDashboardWidget);
        break;

      default:
        setWidget(widget);
        break;
    }
    newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/editWidgetHeader");
    isEditWidget = true;
  }

  public void reloadParamtersFromProcessForCustomWidget(DashboardWidget widget) {
    CustomDashboardWidget customWidget = (CustomDashboardWidget) widget;
    customWidget.loadParametersFromProcess();
  }

  private List<Dashboard> getVisibleDashboards(String dashboardJson) {
    if (isPublicDashboard) {
      return DashboardUtils.jsonToDashboards(dashboardJson);
    } else {
      return DashboardUtils.getVisibleDashboards(dashboardJson);
    }
  }

  public void navigatetoDashboardConfigurationPage() throws IOException {
    String dashboardConfigurationUrl = PortalNavigator.buildDashboardConfigurationUrl();
    FacesContext.getCurrentInstance().getExternalContext().redirect(dashboardConfigurationUrl);
  }

  @Override
  public boolean getIsEditMode() {
    return true;
  }

  public List<WidgetSample> getSamples() {
    return samples;
  }

  public String getNewWidgetHeader() {
    return newWidgetHeader;
  }

  public boolean isEditWidget() {
    return isEditWidget;
  }

  public DashboardWidget getDeleteWidget() {
    return deleteWidget;
  }

  public void setDeleteWidget(DashboardWidget deleteWidget) {
    this.deleteWidget = deleteWidget;
  }

  public String getNewWidgetId() {
    return newWidgetId;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public boolean isPublicDashboard() {
    return isPublicDashboard;
  }

  public String getRestoreDashboardMessage() {
    if (StringUtils.isBlank(restoreDashboardMessage)) {
      if (foundTemplate.isPresent()) {
        restoreDashboardMessage = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/RestoreDefaultDashboardMessage",
            Arrays.asList(foundTemplate.get().getTitle()));
      }
    }
    return restoreDashboardMessage;
  }

  private Optional<DashboardTemplate> findSelectedTemplate(String templateId) {
    if (DashboardConstants.CREATE_FROM_SCRATCH.equals(templateId)) {
      DashboardTemplate scratchDashboard = new DashboardTemplate();
      scratchDashboard.setId(DashboardConstants.CREATE_FROM_SCRATCH);
      scratchDashboard.setTitle(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/CreateFromScratch"));
      return Optional.of(scratchDashboard);
    }
    return CollectionUtils.emptyIfNull(getDashboardTemplates()).stream()
        .filter(template -> template.getId().equals(templateId)).findFirst();
  }

  public Optional<DashboardTemplate> getFoundTemplate() {
    return foundTemplate;
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    setWidget((DashboardWidget) event.getNewValue());
  }
}

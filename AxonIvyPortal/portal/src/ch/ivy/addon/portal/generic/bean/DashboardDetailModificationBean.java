package ch.ivy.addon.portal.generic.bean;

import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.CASE;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.CLIENT_STATISTIC;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.CUSTOM;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.NEWS;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.NOTIFICATION;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.PROCESS;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.PROCESS_VIEWER;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.TASK;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.WELCOME;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DEFAULT_CASE_LIST_DASHBOARD;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DEFAULT_TASK_LIST_DASHBOARD;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.util.Strings;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ColumnResizeEvent;

import com.axonivy.portal.bo.ClientStatistic;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.dto.News;
import com.axonivy.portal.dto.dashboard.NewsDashboardWidget;
import com.axonivy.portal.dto.dashboard.NotificationDashboardWidget;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.service.ClientStatisticService;
import com.axonivy.portal.service.DeepLTranslationService;
import com.axonivy.portal.util.DashboardCloneUtils;
import com.axonivy.portal.util.WelcomeWidgetUtils;
import com.google.common.base.Predicate;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bean.DashboardProcessBean;
import ch.ivy.addon.portalkit.constant.DashboardConstants;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ClientStatisticDashboardWidget;
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
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetSample;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.dto.widget.DashboardCustomWidgetData;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.ProcessSorting;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.CustomWidgetUtils;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.ContentObjectValue;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ViewScoped
@ManagedBean
public class DashboardDetailModificationBean extends DashboardBean implements Serializable, PropertyChangeListener {

  private static final long serialVersionUID = -5272278165636659596L;
  private static final String DEFAULT_COMPLEX_USER_FILTER_ID = "widget-configuration-form:new-widget-configuration-component:predefined-filter";
  private static final String DEFAULT_USER_FILTER_ID = "widget-configuration-form:new-widget-configuration-component:user-filter";
  private static final String DEFAULT_WIDGET_TITLE_ID = "widget-configuration-form:new-widget-configuration-component:widget-title-group";
  private static final String PROCESS_ICON_CUSTOM_FIELD = "cssIcon";
  private static final String DEFAULT_PROCESS_ICON = "si si-hierarchy-6 si-rotate-270";
  
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
  private List<DashboardProcess> customWidgets;
  private List<ClientStatistic> statisticWidgets;

  // Clone widget function
  private Dashboard cloneFromDashboard;
  private DashboardWidget cloneFromWidget;
  private List<Dashboard> cloneableDashboards;

  @PostConstruct
  public void initConfigration() {
    foundTemplate = Optional.empty();
    selectedDashboardId = Attrs.currentContext().getAttribute("#{data.dashboardId}", String.class);
    isPublicDashboard = Attrs.currentContext().getAttribute("#{data.isPublicDashboard}", Boolean.class);
    isReadOnlyMode = false;
    super.init();
    ((DashboardProcessBean) ManagedBeans.get("dashboardProcessBean")).addPropertyChangeListener(this);
    if (getSelectedDashboard() != null) {
      foundTemplate = findSelectedTemplate(getSelectedDashboard().getTemplateId());
    }
  }

  public void initSampleWidgets() {
    if (CollectionUtils.isEmpty(samples)) {
      samples = List.of(taskSample(), caseSample(), processSample(), externalPageSample(),
          processViewerSample(), welcomeWidgetSample(), newsSample(), notificationSample());
      samples = samples.stream().sorted(Comparator.comparing(WidgetSample::getName)).collect(Collectors.toList());
    }
    initCustomWidgets();
    initStatisticWidgets();
  }

  protected void initStatisticWidgets() {
    setStatisticWidgets(new ArrayList<>());
    getStatisticWidgets().addAll(ClientStatisticService.getInstance().findAllCharts());
  }

  private void initCustomWidgets() {
    setCustomWidgets(new ArrayList<>());
    getCustomWidgets().addAll(ProcessService.getInstance().findCustomDashboardProcesses()
        .stream().map(convertToDashboardProcess())
        .collect(Collectors.toList()));
  }

  private Function<IWebStartable, DashboardProcess> convertToDashboardProcess() {
    return startable -> {
      DashboardProcess process = new DashboardProcess();
      process.setName(startable.getDisplayName());
      process.setDescription(startable.getDescription());
      process.setId(startable.getId());
      process.setIcon(Optional.ofNullable(startable.customFields().value(PROCESS_ICON_CUSTOM_FIELD))
          .orElse(DEFAULT_PROCESS_ICON));
      return process;
    };
  }

  @Override
  protected List<Dashboard> collectDashboards() {
    List<Dashboard> collectedDashboards = new ArrayList<>();
    try {
      if (isPublicDashboard) {
        collectedDashboards = DashboardUtils.getPublicDashboards();
      } else {
        collectedDashboards = DashboardUtils.getPrivateDashboards();
      }
    } catch (PortalException e) {
      Ivy.log().error(e);
    }
    DashboardUtils.addDefaultTaskCaseListDashboardsIfMissing(collectedDashboards);
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

  private WidgetSample externalPageSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/ExternalPageWidget"), CUSTOM,
        "si si-network-arrow", translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/ExternalPageWidgetIntroduction"), true);
  }

  private WidgetSample processViewerSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/ProcessViewer/ProcessViewerText"), PROCESS_VIEWER,
        "si si-hierarchy-6 si-rotate-270", translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/processViewerIntroduction"), true);
  }

  private WidgetSample welcomeWidgetSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/Enums/DashboardWidgetType/WELCOME"), WELCOME,
        "welcome-widget-sample.png", translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/WelcomeWidgetIntroduction"));
  }

  private WidgetSample newsSample() {
    return new WidgetSample(translate("/Dialogs/com/axonivy/portal/dashboard/component/NewsWidgetConfiguration/NewsWidgetTitle"),
        NEWS, News.DEFAULT_NEWS_ICON,
        translate("/Dialogs/com/axonivy/portal/dashboard/component/NewsWidgetConfiguration/NewsWidgetDescription"), true);
  }

  private WidgetSample notificationSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/Enums/DashboardWidgetType/NOTIFICATION"),
        NOTIFICATION, "si si-alarm-bell", translate("/Dialogs/com/axonivy/portal/dashboard/component/NotificationWidgetConfiguration/NotificationWidgetDescription"), true);
  }

  public void restore() {
    removeWelcomeWidgetImagesOfDashboard(getSelectedDashboard());
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
      for (DashboardWidget widget : getSelectedDashboard().getWidgets()) {
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
      case CASE -> {
        newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/common/case")));
        widget = getDefaultCaseDashboardWidget();
      }
      case TASK -> {
        newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/common/tasks")));
        widget = getDefaultTaskDashboardWidget();
      }
      case PROCESS -> {
        newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/common/processes")));
        widget = getDefaultProcessDashboardWidget();
      }
      case CUSTOM -> {
        newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/ExternalPageWidget")));
        widget = getDefaultCustomDashboardWidget();
        ((CustomDashboardWidget) widget).getData().setType(DashboardCustomWidgetType.EXTERNAL_URL);
      }
      case PROCESS_VIEWER -> {
        newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/ProcessViewer/ProcessViewerText")));
        widget = getDefaultProcessViewerDashboardWidget();
      }
      case WELCOME -> {
        newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Welcome")));
        widget = getDefaultWelcomeDashboardWidget();
      }
      case NEWS -> {
        newWidgetHeader = translate("/Dialogs/com/axonivy/portal/dashboard/component/NewsWidgetConfiguration/NewsWidgetConfiguration");
        widget = getDefaultNewsWidget();
      }
      case NOTIFICATION -> {
        newWidgetHeader = translate("/Dialogs/com/axonivy/portal/dashboard/component/NotificationWidgetConfiguration/NotificationWidgetConfiguration");
        widget = getDefaultNotificationWidget();
      }
      default -> {}
    }
  }

  public void createClientStatisticWidget(ClientStatistic clientStatistic) {
    newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
        Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/statisticChartWidget")));
    widget = getDefaultClientStatisticDashboardWidget(clientStatistic.getName(), clientStatistic.getId());
  }

  public void createCustomDashboardWidget(DashboardProcess process) {
    newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
        Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/statistic/timePeriod/custom")));
    
    widget = getDefaultCustomDashboardWidget();
    var customWidget = ((CustomDashboardWidget) widget); 
    customWidget.getData().setType(DashboardCustomWidgetType.PROCESS);
    
    var iWebStartable = ProcessService.getInstance()
        .findCustomDashboardProcesses()
        .stream()
        .filter(startable -> StringUtils.endsWith(startable.getId(), process.getId()))
        .findFirst().orElse(null);

    if (Objects.isNull(iWebStartable)) {
      return;
    }

    customWidget.getData().setStartableProcessStart(iWebStartable);
    customWidget.getData().setProcessPath(iWebStartable.getId());
    customWidget.getData().setStartRequestPath(iWebStartable.getLink().getRelative());
    customWidget.loadParametersFromProcess();
  }

  public void removeWidget() {
    if (this.getDeleteWidget() != null) {
      this.getSelectedDashboard().getWidgets().remove(getDeleteWidget());
      if (WELCOME == this.deleteWidget.getType()) {
        removeWelcomeWidgetImage(this.deleteWidget);
      }
      saveSelectedDashboard();
    }
  }

  /**
   * Remove the image of welcome widget from CMS
   * 
   */
  private void removeWelcomeWidgetImage(DashboardWidget selectedWidget) {
    WelcomeDashboardWidget welcomeWidget = (WelcomeDashboardWidget) selectedWidget;
    if (StringUtils.isNotBlank(welcomeWidget.getImageLocation())) {
      WelcomeWidgetUtils.removeWelcomeImage(welcomeWidget.getImageLocation(), welcomeWidget.getImageType());
    }
    if (StringUtils.isNotBlank(welcomeWidget.getImageLocationDarkMode())) {
      WelcomeWidgetUtils.removeWelcomeImage(welcomeWidget.getImageLocationDarkMode(), welcomeWidget.getImageTypeDarkMode());
    }
  }

  /**
   * Remove images of welcome widgets
   * @param selectedDashboard
   */
  private void removeWelcomeWidgetImagesOfDashboard(Dashboard selectedDashboard) {
    for (DashboardWidget selectedWidget : selectedDashboard.getWidgets()) {
      if (WELCOME == selectedWidget.getType()) {
        removeWelcomeWidgetImage(selectedWidget);
      }
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

  private ClientStatisticDashboardWidget getDefaultClientStatisticDashboardWidget(String widgetName, String chartId) {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(CLIENT_STATISTIC);
    ClientStatisticDashboardWidget widget = null;
    widget = (ClientStatisticDashboardWidget) DashboardWidgetUtils.buildDefaultWidget(widgetId, widgetName,
        CLIENT_STATISTIC);
    widget.setChartId(chartId);
    return widget;
  }

  private CustomDashboardWidget getDefaultCustomDashboardWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(CUSTOM);
    return CustomDashboardWidget.buildDefaultWidget(widgetId, StringUtils.EMPTY);
  }

  private ProcessViewerDashboardWidget getDefaultProcessViewerDashboardWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(PROCESS_VIEWER);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourProcessViewer");
    return ProcessViewerDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  private WelcomeDashboardWidget getDefaultWelcomeDashboardWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(WELCOME);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/YourWelcomeWidget");
    return WelcomeDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  private NewsDashboardWidget getDefaultNewsWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(NEWS);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/Enums/DashboardWidgetType/NEWS");
    return NewsDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  public void saveClientStatisticWidget(ClientStatistic clientStatistic) {
    createClientStatisticWidget(clientStatistic);
    saveWidget();
  }
  private NotificationDashboardWidget getDefaultNotificationWidget() {
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(NOTIFICATION);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourNotifications");
    return NotificationDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  public void saveWidget() {
    if (CollectionUtils.isEmpty(this.getSelectedDashboard().getWidgets())) {
      this.getSelectedDashboard().setWidgets(new ArrayList<>());
    }
    List<DashboardWidget> widgets = this.getSelectedDashboard().getWidgets();
    switch (widget.getType()) {
      case PROCESS -> {
        ProcessDashboardWidget processWidget = (ProcessDashboardWidget) this.widget;
        processWidget.setPreview(false);
        if (processWidget.getDisplayMode() == ProcessWidgetMode.FULL_MODE) {
          updateProcessWidget((SingleProcessDashboardWidget) processWidget, 5, 2);
        } else if (processWidget.getDisplayMode() == ProcessWidgetMode.COMBINED_MODE) {
          updateProcessWidget((SingleProcessDashboardWidget) processWidget, 6, 5);
        } else if (processWidget.getDisplayMode() == ProcessWidgetMode.COMPACT_MODE) {
          updateProcessWidgetSize(processWidget, 5, 3);
          CompactProcessDashboardWidget compactProcessWidget = (CompactProcessDashboardWidget) processWidget;
          List<DashboardProcess> processes = compactProcessWidget.getDisplayProcesses();
          if (ProcessSorting.BY_CUSTOM_ORDER.name().equals(compactProcessWidget.getSorting())) {
            Map<String, Integer> customIndexs = new HashMap<>();
            for (int i = 0; i <processes.size();i++) {
              customIndexs.put(processes.get(i).getId(), i);
            }
            compactProcessWidget.setCustomIndexs(customIndexs);
          } else {
            compactProcessWidget.setCustomIndexs(null);
          }
          
          unifyCompactProcessCategory(compactProcessWidget);
          updateProcessesOfWidget(compactProcessWidget);
          updateApplicationForCompactProcess(compactProcessWidget);
          if (CollectionUtils.isEmpty((compactProcessWidget).getFilterableColumns())) {
            (compactProcessWidget).buildFilterableColumns(DashboardWidgetUtils.initProcessFilterableColumns());
          }
        } else if (processWidget.getDisplayMode() == ProcessWidgetMode.IMAGE_MODE) {
          updateProcessWidget((SingleProcessDashboardWidget) processWidget, 5, 2);
        }
        DashboardWidget dashboardWidget =
            widgets.stream().filter(w -> widget.getId().equals(w.getId()) && w.getType() == DashboardWidgetType.PROCESS)
                .findFirst().orElse(null);
        if (dashboardWidget != null) {
          widgets.set(widgets.indexOf(dashboardWidget), widget);
        } else {
          widgets.add(widget);
        }
      }
      case CUSTOM -> {
        CustomDashboardWidget customWidget =  (CustomDashboardWidget) widget;
        unifyCustomWidgetData(customWidget);
      }
      case WELCOME -> {
        updateWelcomeWidget(widget);
      }
      case CASE -> {
        updateCaseWidget(widget);
      }
      default -> {}
    }
    updateWidgetPosition(widget);
    resetUserFilter();

    if (widget.getType() != DashboardWidgetType.CLIENT_STATISTIC) {
      initMultipleLanguagesForWidgetName(this.widget.getName());
    }

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
    DashboardUtils.updateDashboardCache();
  }

  public void onCancel(DashboardWidget widget) {
    if (widget != null && WELCOME == widget.getType()) {
      removeTempImageOfWelcomeWidget(widget);
      WelcomeDashboardWidget welcomeWidget = (WelcomeDashboardWidget) widget;
      ContentObject welcomeImage = getWelcomeWidgetImageObject(false, welcomeWidget, false);
      if (!StringUtils.isBlank(welcomeWidget.getImageLocation()) && welcomeImage != null && !welcomeImage.exists()) {
        welcomeWidget.setImageLocation(null);
      }
      
      ContentObject welcomeImageDarkMode = getWelcomeWidgetImageObject(false, welcomeWidget, true);
      if (!StringUtils.isBlank(welcomeWidget.getImageLocationDarkMode()) && welcomeImageDarkMode != null && !welcomeImageDarkMode.exists()) {
        welcomeWidget.setImageLocationDarkMode(null);
      }
    }
  }
  
  public void onReset(@SuppressWarnings("unused") DashboardWidget widget) {
    resetUserFilter();
  }

  private void updateApplicationForCompactProcess(CompactProcessDashboardWidget compactProcessWidget) {
    compactProcessWidget.getFilterableColumns().stream()
      .filter(column -> DashboardStandardProcessColumn.APPLICATION.getField().equalsIgnoreCase(column.getField()))
      .findAny().ifPresent(applicationColumn -> {
      compactProcessWidget.setApplications(applicationColumn.getFilterList());
    });
  }
  
  private void updateProcessWidgetSize(ProcessDashboardWidget processWidget, int height, int width) {
    if (processWidget.getLayout().getHeight() == -1) {
      processWidget.getLayout().setHeight(height);
    }
    if (processWidget.getLayout().getWidth() == -1) {
      processWidget.getLayout().setWidth(width);
    }
  }

  /**
   * Add image to CMS, remove temp image CMS
   * 
   * @param widget
   */
  private void updateWelcomeWidget(DashboardWidget widget) {
    var welcomeWidget = (WelcomeDashboardWidget) widget;
    if (StringUtils.isNotBlank(welcomeWidget.getImageLocation())) {
      ContentObjectValue tempImageFile = getWelcomeWidgetImage(true, welcomeWidget, false);
      ContentObjectValue imageFile = getWelcomeWidgetImage(false, welcomeWidget, false);
      if (imageFile != null && tempImageFile != null && tempImageFile.parent().exists()) {
        Optional<DashboardWidget> oldWidgetOptional = DashboardWidgetUtils.findWidget(selectedDashboard,
            widget.getId());
        if (oldWidgetOptional.isPresent()) {
          WelcomeDashboardWidget oldWidget = (WelcomeDashboardWidget) oldWidgetOptional.get();
          Optional.ofNullable(getWelcomeWidgetImage(false, oldWidget, false)).ifPresent(co -> {
            co.delete();
          });
        }
        imageFile.write().bytes(tempImageFile.read().bytes());
        tempImageFile.delete();
      }
    }
    if (StringUtils.isNotBlank(welcomeWidget.getImageLocationDarkMode())) {
      ContentObjectValue tempImageFileDarkMode = getWelcomeWidgetImage(true, welcomeWidget, true);
      ContentObjectValue imageFileDarkMode = getWelcomeWidgetImage(false, welcomeWidget, true);
      if (imageFileDarkMode != null && tempImageFileDarkMode != null && tempImageFileDarkMode.parent().exists()) {
        Optional<DashboardWidget> oldWidgetOptional = DashboardWidgetUtils.findWidget(selectedDashboard,
            widget.getId());
        if (oldWidgetOptional.isPresent()) {
          WelcomeDashboardWidget oldWidget = (WelcomeDashboardWidget) oldWidgetOptional.get();
          Optional.ofNullable(getWelcomeWidgetImage(false, oldWidget, true)).ifPresent(co -> {
            co.delete();
          });
            
        }
        imageFileDarkMode.write().bytes(tempImageFileDarkMode.read().bytes());
        tempImageFileDarkMode.delete();
      }
    }
  }

  private void updateCaseWidget(DashboardWidget widget) {
    var caseWidget = (CaseDashboardWidget) widget;
    if (CollectionUtils.isNotEmpty(caseWidget.getFilters())) {
      caseWidget.setFilters(caseWidget.getFilters().stream()
          .filter(Objects::nonNull).filter(checkValidFilter()).collect(Collectors.toList()));
    }
  }

  private Predicate<DashboardFilter> checkValidFilter() {
    return filter -> {
      return StringUtils.isNotBlank(filter.getField())
          && filter.getOperator() != null
          && filter.getFilterType() != null;
    };
  }

  private void removeTempImageOfWelcomeWidget(DashboardWidget widget) {
    var welcomeWidget = (WelcomeDashboardWidget) widget;
    if (StringUtils.isNotBlank(welcomeWidget.getImageLocation())) {
      ContentObjectValue tempImageFile = getWelcomeWidgetImage(true, welcomeWidget, false);
      if (tempImageFile != null) {
        tempImageFile.delete();
      }
    }
    
    if (StringUtils.isNotBlank(welcomeWidget.getImageLocationDarkMode())) {
      ContentObjectValue tempImageFileDarkMode = getWelcomeWidgetImage(true, welcomeWidget, true);
      if (tempImageFileDarkMode != null) {
        tempImageFileDarkMode.delete();
      }
    }
  }

  private ContentObjectValue getWelcomeWidgetImage(boolean isTempImage, WelcomeDashboardWidget widget, boolean isDarkMode) {
    ContentObject contentObject = getWelcomeWidgetImageObject(isTempImage, widget, isDarkMode);
    if (contentObject != null) {
      return contentObject.value().get("en");
    }
    return null;
  }
  
  private ContentObject getWelcomeWidgetImageObject(boolean isTempImage, WelcomeDashboardWidget widget, boolean isDarkMode) {
    String imageName = WelcomeWidgetUtils.getFileNameOfImage(isDarkMode ? widget.getImageLocationDarkMode() : widget.getImageLocation());
    // check if default light mode is configured for dark mode
    if (isDarkMode && StringUtils.equals(widget.getImageLocationDarkMode(), widget.getImageLocation())) {
      imageName = imageName + WelcomeWidgetUtils.DARK_MODE;
    }
    imageName = isTempImage ? "temp_".concat(imageName) : imageName;
    return WelcomeWidgetUtils.getImageContentObject(imageName, isDarkMode ? widget.getImageTypeDarkMode() : widget.getImageType());
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

  private void unifyCustomWidgetData(CustomDashboardWidget customWidget) {
    if (customWidget.getData().getType() == DashboardCustomWidgetType.PROCESS) {
      // Update processPath to latest
      IWebStartable startable = Optional.ofNullable(customWidget.getData())
          .map(DashboardCustomWidgetData::getStartableProcessStart).orElse(null);
      if (Objects.isNull(startable)) {
        startable = CustomWidgetUtils.findStartableOfCustomDashboardProcess(customWidget.getData().getProcessPath());
      }
      if (Objects.nonNull(startable)) {
        customWidget.getData().setProcessPath(startable.getId());
      }
      customWidget.getData().setUrl(EMPTY);
      if (CollectionUtils.isNotEmpty(customWidget.getData().getParams())) {
        for (CustomDashboardWidgetParam param : customWidget.getData().getParams()) {
          switch (param.getType()) {
            case BOOLEAN -> {
              param.setValue(param.getValueBoolean().toString());
            }
            case DATE -> { 
              param.setValue(Dates.format(param.getValueDate()));
            }
            case USER -> {
              param.setValue(Optional.ofNullable(param.getValueUser()).map(UserDTO::getName).orElse(null));
            }
            default -> {}
          }
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

  public void save() {
    var layouts = DashboardWidgetUtils.getWidgetLayoutFromRequest(getRequestParameterMap());
    for (var layout : layouts) {
      DashboardWidget updatedWidget = getSelectedDashboard().getWidgets().stream()
          .filter(w -> w.getId().contentEquals(layout.getId()))
          .findFirst().get();

      WidgetLayout updatedLayout = new WidgetLayout();
      updatedLayout.setAxisX(layout.getAxisX());
      updatedLayout.setAxisY(layout.getAxisY());
      updatedLayout.setWidth(layout.getWidth());
      updatedLayout.setHeight(layout.getHeight());

      updatedWidget.setLayout(updatedLayout);
    }
    saveSelectedDashboard();
  }

  public void saveSelectedWidget() {
    this.dashboards.set(this.dashboards.indexOf(this.getSelectedDashboard()), this.getSelectedDashboard());
    saveSelectedDashboard();
  }

  protected void saveSelectedDashboard() {
    selectedDashboard.getWidgets().forEach(widget -> {
      DashboardWidgetUtils.simplifyWidgetColumnData(widget);
    });

    saveDashboardsWithHandlingDefaultDashboards();
    selectedDashboard.getWidgets().forEach(widget -> {
      DashboardWidgetUtils.buildWidgetColumns(widget);
    });
  }

  private Dashboard saveDashboardsWithHandlingDefaultDashboards() {
    DashboardService dashboardService = DashboardService.getInstance();
    boolean isAddingDefaultTaskListDashboard = (DEFAULT_TASK_LIST_DASHBOARD.equals(selectedDashboard.getId()))
        && dashboardService.findById(DEFAULT_TASK_LIST_DASHBOARD) == null;
    boolean isAddingDefaultCaseListDashboard = (DEFAULT_CASE_LIST_DASHBOARD.equals(selectedDashboard.getId()))
        && dashboardService.findById(DEFAULT_CASE_LIST_DASHBOARD) == null;
    if (isAddingDefaultTaskListDashboard || isAddingDefaultCaseListDashboard) {
      dashboardService.saveDefaultDashboardAsFirstDashboard(selectedDashboard);
    } else {
      dashboardService.save(selectedDashboard);
    }
    return selectedDashboard;
  }

  protected Map<String, String> getRequestParameterMap() {
    return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
  }

  private void updateWidgetPosition(DashboardWidget widget) {
    if (isEditWidget || Objects.isNull(widget)) {
      return;
    }

    if (WELCOME == widget.getType()) {
      for (var otherWidget : CollectionUtils.emptyIfNull(selectedDashboard.getWidgets())) {
        if (otherWidget.getId().contentEquals(widget.getId())) {
          continue;
        }
        int currentAxisY = otherWidget.getLayout().getAxisY();
        otherWidget.getLayout().setAxisY(currentAxisY + widget.getLayout().getHeight());
      }
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

    if (lastWidget != null) {
      var nextAxisX = lastWidget.getLayout().getAxisX() + lastWidget.getLayout().getWidth();
      var totalWidth = nextAxisX + widget.getLayout().getWidth();
      if (totalWidth <= 12) {
        widget.getLayout().setAxisX(nextAxisX);
        widget.getLayout().setAxisY(lastWidget.getLayout().getAxisY());
      } else {
        portalGridsCurrentRow = Long.valueOf(lastWidget.getLayout().getAxisY() + lastWidget.getLayout().getHeight());
        widget.getLayout().setAxisX(0);
        widget.getLayout().setAxisY(portalGridsCurrentRow.intValue());
      }
    }
    widget.getLayout().setNewWidget(true);
  }

  private void updateProcessWidget(SingleProcessDashboardWidget processWidget, int height, int width) {
    updateProcessWidgetSize(processWidget, height, width);

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
          .filter(process -> DashboardWidgetUtils.isProcessMatchedCategory(process, widget.getCategories()))
          .collect(Collectors.toList());
    }

    widget.setProcessPaths(processPaths);
    widget.setDisplayProcesses(displayProcesses);
    widget.setOriginalDisplayProcesses(displayProcesses);
  }

  private List<String> getProcessPaths(List<DashboardProcess> processes) {
    List<String> processPaths = new ArrayList<>();
    for (DashboardProcess process : processes) {
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
    DashboardWidget editWidget = findWidgetByIdInSelectedDashboard(widget);
    switch (widget.getType()) {
      case PROCESS -> {
        ProcessDashboardWidget processDashboardWidget = (ProcessDashboardWidget) editWidget;
        ProcessDashboardWidget clonedWidget = switch (processDashboardWidget.getDisplayMode()) {
          case COMPACT_MODE -> new CompactProcessDashboardWidget((CompactProcessDashboardWidget) processDashboardWidget);
          case COMBINED_MODE -> new CombinedProcessDashboardWidget((CombinedProcessDashboardWidget) processDashboardWidget);
          case FULL_MODE -> new FullProcessDashboardWidget((FullProcessDashboardWidget) processDashboardWidget);
          case IMAGE_MODE -> new ImageProcessDashboardWidget((ImageProcessDashboardWidget) processDashboardWidget);
          default -> new ProcessDashboardWidget(processDashboardWidget);
        };
        setWidget(clonedWidget);
      }
      default -> {
        setWidget(editWidget);
      }
    }
    newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/editWidgetHeader");
    isEditWidget = true;
  }

  protected DashboardWidget findWidgetByIdInSelectedDashboard(DashboardWidget widget) {
    DashboardWidget foundWidget = collectDashboards().stream()
        .filter(dashboard -> dashboard.getId().equals(selectedDashboardId))
        .map(Dashboard::getWidgets).flatMap(Collection::stream)
        .filter(dashboardWidget -> dashboardWidget.getId().equals(widget.getId()))
        .findAny().orElse(widget);
    DashboardWidgetUtils.buildWidgetColumns(foundWidget);
    return foundWidget;
  }

  public void reloadParamtersFromProcessForCustomWidget(DashboardWidget widget) {
    CustomDashboardWidget customWidget = (CustomDashboardWidget) widget;
    customWidget.loadParametersFromProcess();
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

  public Dashboard getCloneFromDashboard() {
    return cloneFromDashboard;
  }

  public void setCloneFromDashboard(Dashboard cloneFromDashboard) {
    this.cloneFromDashboard = cloneFromDashboard;
  }

  public DashboardWidget getCloneFromWidget() {
    return cloneFromWidget;
  }

  public void setCloneFromWidget(DashboardWidget cloneFromWidget) {
    this.cloneFromWidget = cloneFromWidget;
  }

  public List<Dashboard> getCloneableDashboards() {
    return cloneableDashboards;
  }

  public void setCloneableDashboards(List<Dashboard> cloneableDashboards) {
    this.cloneableDashboards = cloneableDashboards;
  }

  public List<Dashboard> initCloneableDashboards() {
    List<Dashboard> availableDashboards = new ArrayList<>();
    availableDashboards.addAll(DashboardUtils.getPublicDashboards());
    DashboardUtils
        .addDefaultTaskCaseListDashboardsIfMissing(availableDashboards);

    String dashboardInUserProperty = readDashboardBySessionUser();
    if (StringUtils.isNotBlank(dashboardInUserProperty)) {
      List<Dashboard> myDashboards = DashboardUtils
          .getVisibleDashboards(dashboardInUserProperty);
      availableDashboards.addAll(myDashboards);
    }
    return availableDashboards;
  }

  public String getRestoreDashboardMessage() {
    if (StringUtils.isBlank(restoreDashboardMessage) && Objects.nonNull(foundTemplate)) {
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

  public String getComponentToProcessOnSave() {
    var componentId = "@this";
    DashboardWidget processWidget = getWidget();
    if (processWidget != null) {
      componentId = DEFAULT_WIDGET_TITLE_ID;
      if (NEWS != processWidget.getType() && CASE != processWidget.getType() && TASK != processWidget.getType()) {
        String userFilterId = String.format(DEFAULT_USER_FILTER_ID, processWidget.getId());
        componentId = componentId.concat(" ").concat(userFilterId);
      } 
      if (PROCESS == processWidget.getType()) {
        componentId = componentId.concat(" widget-configuration-form");
      }
      if (CASE == processWidget.getType() || TASK == processWidget.getType()) {
        String userFilterId = String.format(DEFAULT_COMPLEX_USER_FILTER_ID, processWidget.getId());
        componentId = componentId.concat(" ").concat(userFilterId);
      }
    }
    return componentId;
  }

  public void updateWidgetNameByLocale() {
    String currentName = this.widget.getName();
    initMultipleLanguagesForWidgetName(currentName);
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = this.widget.getNames().stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      optional.get().setValue(currentName);
    }
  }

  private Map<String, DisplayName> getMapLanguages() {
    List<DisplayName> languages = this.widget.getNames();
    return languages.stream().collect(Collectors.toMap(o -> o.getLocale().toLanguageTag(), o -> o));
  }

  private void initMultipleLanguagesForWidgetName(String currentName) {
    Map<String, DisplayName> mapLanguage = getMapLanguages();
    List<String> supportedLanguages = getSupportedLanguages();
    for (String language : supportedLanguages) {
      DisplayName localeLanguage = mapLanguage.get(language);
      if (localeLanguage == null) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        displayName.setValue(currentName);
        this.widget.getNames().add(displayName);
      } else if (StringUtils.isBlank(localeLanguage.getValue())) {
        localeLanguage.setValue(currentName);
      }
    }
  }

  public void translate(DisplayName title) {
    translatedText = Strings.EMPTY;
    warningText = Strings.EMPTY;

    String currentLanguage = UserUtils.getUserLanguage();
    if (!title.getLocale().getLanguage().equals(currentLanguage)) {
      List<DisplayName> languages = this.widget.getNames();
      Optional<DisplayName> optional = languages.stream()
          .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
      if (optional.isPresent()) {
        try {
          translatedText = DeepLTranslationService.getInstance().translate(optional.get().getValue(),
              optional.get().getLocale(), title.getLocale());
        } catch (Exception e) {
          warningText = Ivy.cms()
              .co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/SomeThingWentWrong");
          Ivy.log().error("DeepL Translation Service error: ", e.getMessage());
        }
      }
    }

  }

  public List<DashboardProcess> getCustomWidgets() {
    return customWidgets;
  }

  public void setCustomWidgets(List<DashboardProcess> customWidgets) {
    this.customWidgets = customWidgets;
  }

  public List<ClientStatistic> getStatisticWidgets() {
    return statisticWidgets;
  }

  public void setStatisticWidgets(List<ClientStatistic> statisticWidgets) {
    this.statisticWidgets = statisticWidgets;
  }
  
  public boolean canEnableQuickSearch() {
    return Optional.ofNullable(this.widget).map(DashboardWidget::getType)
        .map(DashboardWidgetType::canEnableQuickSearch)
        .orElse(false);
  }
  
  public boolean displayWidgetInfoOption() {
    return Optional.ofNullable(this.widget).map(DashboardWidget::getType)
        .map(DashboardWidgetType::canShowWidgetInfoOption).orElse(false);
  }
  
  public boolean displayFullscreenModeOption() {
    return Optional.ofNullable(this.widget).map(DashboardWidget::getType)
        .map(DashboardWidgetType::canShowFullscreenModeOption).orElse(false);
  }

  public void onResizeColumn(ColumnResizeEvent event) {
    String widgetId = (String) event.getComponent().getAttributes()
        .getOrDefault("widgetId", "");

    if (StringUtils.isBlank(widgetId)) {
      return;
    }

    DashboardWidget targetWidget = selectedDashboard.getWidgets()
        .stream().filter(widget -> widget.getId().contentEquals(widgetId))
        .findFirst().orElse(null);

    if (targetWidget == null) {
      return;
    }

    if (targetWidget instanceof TaskDashboardWidget) {
      handleResizeColumnOfTaskWidget(
          (TaskDashboardWidget) targetWidget,
          getColumnIndexFromColumnKey(event.getColumn().getColumnKey()),
          event.getWidth());
    }
    
    if (targetWidget instanceof CaseDashboardWidget) {
      handleResizeColumnOfCaseWidget(
          (CaseDashboardWidget) targetWidget,
          getColumnIndexFromColumnKey(event.getColumn().getColumnKey()),
          event.getWidth());
    }

    selectedDashboard = saveDashboardsWithHandlingDefaultDashboards();
  }

  /**
   * Split the ID and get the last part to get the order of the column Example:
   * ID = 'task-1:task-component:dashboard-tasks:dashboard-tasks-columns:1'
   * Then, the result should be 1
   * 
   * @param columnKey
   * @return column index
   */
  private Integer getColumnIndexFromColumnKey(String columnKey) {
    List<String> idParts = Arrays.asList(columnKey.split("\\:"));
    return NumberUtils.toInt(idParts.get(idParts.size() - 1), -1);
  }

  private void handleResizeColumnOfTaskWidget(TaskDashboardWidget widget,
      int fieldPosition, int widthValue) {
    widget.getColumns().get(fieldPosition)
        .setWidth(Integer.toString(widthValue));
    widget.getColumns().forEach(col -> col.initDefaultStyle());
  }
  
  private void handleResizeColumnOfCaseWidget(CaseDashboardWidget widget,
      int fieldPosition, int widthValue) {
    widget.getColumns().get(fieldPosition)
        .setWidth(Integer.toString(widthValue));
    widget.getColumns().forEach(col -> col.initDefaultStyle());
  }

  public void cloneWidget() {
    widget = DashboardCloneUtils.cloneWidget(cloneFromWidget);

    if (widget.getType() == CLIENT_STATISTIC) {
      saveWidget();
    }

    if (cloneableDashboards != null) {
      cloneableDashboards.clear();
    }
  }

  public void initCloneWidgetDialog() {
    if (CollectionUtils.isEmpty(cloneableDashboards)) {
      cloneableDashboards = initCloneableDashboards();

      if (CollectionUtils.isNotEmpty(cloneableDashboards)) {
        cloneableDashboards = cloneableDashboards.stream()
            .filter(
                dashboard -> CollectionUtils.isNotEmpty(dashboard.getWidgets()))
            .collect(Collectors.toList());
      }
    }

    cloneFromDashboard = null;
    cloneFromWidget = null;
  }

  public String generateCloneWidgetName(DashboardWidget widget) {
    String widgetName = widget.getName();
    if (widget.getType() == CLIENT_STATISTIC) {
      ClientStatisticDashboardWidget statisticWidget = (ClientStatisticDashboardWidget) widget;
      widgetName = getStatisticWidgets().stream()
          .filter(statistic -> statistic.getId()
              .contentEquals(statisticWidget.getChartId()))
          .findFirst().map(ClientStatistic::getName).orElse("");
    }

    // For custom widget, need to build before get name
    if (widget.getType() == DashboardWidgetType.CUSTOM
        && StringUtils.isBlank(widgetName)) {
      widget = DashboardWidgetUtils.buildWidgetColumns(widget);
      CustomDashboardWidget customWidget = (CustomDashboardWidget) widget;
      widgetName = Optional.ofNullable(customWidget)
          .map(CustomDashboardWidget::getData)
          .map(DashboardCustomWidgetData::getStartableProcessStart)
          .map(IWebStartable::getDisplayName).orElse("");
    }

    return String.format("%s (%s)", widgetName, widget.getType().getLabel());
  }
  
  public void navigateToCustomStatisticWidgetPage(String id) throws IOException {
    Map<String, String> param = new HashMap<>();
    if (StringUtils.isNotEmpty(id)) {
      param.put("id", id);
    }
    if (StringUtils.isNotEmpty(selectedDashboardId)) {
      param.put("callbackDashboardId", selectedDashboardId);
    }
    FacesContext.getCurrentInstance().getExternalContext().redirect(PortalNavigator.buildCustomStatisticWidgetUrl(param));
  }
  
  public void deleteCustomStatisticById(String id) {
    boolean isRemoveSuccess = statisticWidgets.removeIf(c -> c.getId().equals(id));
    if (isRemoveSuccess) {
      List<ClientStatistic> customStatisticsToSave = statisticWidgets.stream().filter(c -> c.getIsCustom()).collect(Collectors.toList());
      ClientStatisticService.getInstance().saveJsonToVariable(customStatisticsToSave);
    }
  }
}

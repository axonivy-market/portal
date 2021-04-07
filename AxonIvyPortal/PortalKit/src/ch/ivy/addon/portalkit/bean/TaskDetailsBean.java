package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.constant.TaskDetailsWidgetType;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetails;
import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetailsCustomWidget;
import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetailsDocumentWidget;
import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetailsFilters;
import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetailsHistoryWidget;
import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetailsInformationWidget;
import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetailsWidget;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.globalvars.IGlobalVariableContext;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class TaskDetailsBean implements Serializable {

  private static final long serialVersionUID = 8566646437739271552L;
  private static final String TASK_DETAILS_CONFIGURATION_PROPERTY = "task.details.widgets";
  private static final String PORTAL_TASK_DETAILS_GLOBAL_VARIABLE = "Portal.TaskDetails";
  private static final String PORTAL_DEFAULT_TASK_DETAILS_GLOBAL_VARIABLE = "Portal.DefaultTaskDetails";

  private TaskDetails configuration;
  private List<TaskDetailsWidget> widgets;
  private ObjectMapper mapper = new ObjectMapper().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
  private boolean isReadOnlyMode = true;
  private boolean hasShowNotAvailableData;
  private boolean hasShowDurationTime;
  private GlobalSettingService globalSettingService = new GlobalSettingService();
  private List<TaskDetails> configurations;

  @PostConstruct
  public void init() {
    try {
      loadTaskDetailsSettings();
      loadWidgets();
    } catch (Exception e) {
      Ivy.log().error("Exception at method init of Class TaskDetailsBean", e);
    }
  }

  private void loadTaskDetailsSettings() {
    hasShowNotAvailableData = PermissionUtils.isSessionUserHasAdminRole();
    hasShowDurationTime = Boolean
        .parseBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.SHOW_TASK_DURATION_TIME.toString()));
  }

  private void loadWidgets() throws Exception {
    ITask currentTask = Attrs.currentContext().getAttribute("#{data.task}", ITask.class);
    if (currentTask == null) {
      return;
    }

    String configurationJson = readConfigurationJsonInProperty();
    configurations = readConfigurations(configurationJson);
    boolean foundMatchedConfig = false;
    for (TaskDetails config: configurations) {
      // found configuration for current task by predefined filters
      if (isFilterByTaskCategories(currentTask, config) || isFilterByTaskStates(currentTask, config) || config.getFilters() == null) {
        configuration = config;
        widgets = configuration.getWidgets();
        foundMatchedConfig = true;
  }
    }

    // If no configuration matched, load default configuration
    if (!foundMatchedConfig || configuration == null) {
      
      configuration = loadDefaultConfiguration();
      widgets = configuration.getWidgets();
    }

    updateUrlForCustomWidget(widgets);
  }

  private boolean isFilterByTaskStates(ITask currentTask, TaskDetails config) {
    return Optional.ofNullable(config.getFilters()).map(TaskDetailsFilters::getTaskStates).isPresent() && config.getFilters().getTaskStates().contains(currentTask.getState());
  }

  private boolean isFilterByTaskCategories(ITask currentTask, TaskDetails config) {
    return Optional.ofNullable(config.getFilters()).map(TaskDetailsFilters::getTaskCategories).isPresent() && config.getFilters().getTaskCategories().contains(currentTask.getCategoryPath());
  }

  private String readConfigurationJsonInProperty() {
    return Ivy.session().getSessionUser().getProperty(TASK_DETAILS_CONFIGURATION_PROPERTY);
  }

  private List<TaskDetails> readConfigurations(String configurationJson) throws JsonMappingException, JsonProcessingException, IOException {
    List<TaskDetails> result = new ArrayList<>();
    if (StringUtils.isBlank(configurationJson)) {
      result.addAll(loadAllConfigurations());
    } else {
      result.addAll(parseConfigurationJson(configurationJson));
  }
    return result;
  }

  private List<TaskDetails> parseConfigurationJson(String configurationJson) throws JsonMappingException, JsonProcessingException {
    return mapper.readValue(configurationJson, new TypeReference<List<TaskDetails>>() {});
  }

  private List<TaskDetails> loadAllConfigurations() throws IOException {
    String widgetsJsonData = IGlobalVariableContext.current().get(PORTAL_TASK_DETAILS_GLOBAL_VARIABLE);
    List<TaskDetails> result = mapper.readValue(widgetsJsonData, new TypeReference<List<TaskDetails>>() {});
    for (TaskDetails details : result) {
      updateWidgetsType(details);
    }
    return result;
  }

  private TaskDetails loadDefaultConfiguration() throws IOException {
    String widgetsJsonData = IGlobalVariableContext.current().get(PORTAL_DEFAULT_TASK_DETAILS_GLOBAL_VARIABLE);
    return mapper.readValue(widgetsJsonData, TaskDetails.class);
  }

  public void reset() throws IOException {
    removeConfigurationUserProperty();
    configuration = loadAllConfigurations().stream().filter(config -> config.getId().contentEquals(configuration.getId())).findFirst().orElse(null);
    if (configuration == null) {
      configuration = loadDefaultConfiguration();
    }
    widgets = configuration.getWidgets();
    updateUrlForCustomWidget(widgets);
  }

  private void updateWidgetsType(TaskDetails details) {
    for (TaskDetailsWidget widget : details.getWidgets()) {
      if (widget instanceof TaskDetailsHistoryWidget) {
        widget.setType(TaskDetailsWidgetType.HISTORY);
      } else if (widget instanceof TaskDetailsDocumentWidget) {
        widget.setType(TaskDetailsWidgetType.DOCUMENT);
      } else if (widget instanceof TaskDetailsInformationWidget) {
        widget.setType(TaskDetailsWidgetType.INFORMATION);
      } else if (widget instanceof TaskDetailsCustomWidget) {
        widget.setType(TaskDetailsWidgetType.CUSTOM);
      }
    }
  }
  private void removeConfigurationUserProperty() {
    Ivy.session().getSessionUser().removeProperty(TASK_DETAILS_CONFIGURATION_PROPERTY);
  }

  public void switchToEditMode() {
    this.isReadOnlyMode = false;
  }

  public void save() throws JsonMappingException, JsonProcessingException {
    this.isReadOnlyMode = true;
    configuration.setChanged(true);
    List<TaskDetailsWidget> widgets = getUpdatedWidgets();
    updateToConfiguration(widgets);
    saveConfigurationsToProperty();
  }

  private List<TaskDetailsWidget> getUpdatedWidgets() throws JsonMappingException, JsonProcessingException {
    List<TaskDetailsWidget> result = new ArrayList<>();
    Map<String, String> requestParamMap = getRequestParameterMap();
    String nodes = Optional.ofNullable(requestParamMap.get("nodes")).orElse(StringUtils.EMPTY);
    List<WidgetLayout> layouts = Arrays.asList(mapper.readValue(nodes, WidgetLayout[].class));

    if (CollectionUtils.isNotEmpty(layouts)) {
      for (WidgetLayout layout : layouts) {
        TaskDetailsWidget currentWidget = widgets.stream().filter(widget -> StringUtils.compare(widget.getId(), layout.getId()) == 0).findFirst().get();
        currentWidget.getLayout().setAxisX(layout.getAxisX());
        currentWidget.getLayout().setAxisY(layout.getAxisY());
        currentWidget.getLayout().setWidth(layout.getWidth());
        currentWidget.getLayout().setHeight(layout.getHeight());
        result.add(currentWidget);
  }
    }
    return result;
  }

  private Map<String, String> getRequestParameterMap() {
    Map<String, String> requestParamMap =
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    return requestParamMap;
  }

  private void updateToConfiguration(List<TaskDetailsWidget> widgets) {
    if (CollectionUtils.isNotEmpty(this.widgets)) {
      for (TaskDetailsWidget widget : widgets) {
        if (doesWidgetExist(widget)) {
          updateWidget(widget);
        }
      }
    }
    configuration.setWidgets(widgets);
    for (TaskDetails config: configurations) {
      if(StringUtils.compare(config.getId(), configuration.getId()) == 0) {
        config.setWidgets(configuration.getWidgets());
      }
    }
  }

  private boolean doesWidgetExist(TaskDetailsWidget widget) {
    return widgets.indexOf(widget) > -1;
  }

  private void updateWidget(TaskDetailsWidget widget) {
    TaskDetailsWidget updatedWidget = widgets.get(widgets.indexOf(widget));
    updatedWidget.getLayout().setId(Optional.ofNullable(widget.getLayout().getId()).orElse(""));
    updatedWidget.getLayout().setAxisX(widget.getLayout().getAxisX());
    updatedWidget.getLayout().setAxisY(widget.getLayout().getAxisY());
    updatedWidget.getLayout().setWidth(widget.getLayout().getWidth());
    updatedWidget.getLayout().setHeight(widget.getLayout().getHeight());
    updatedWidget.getLayout().setStyle((Optional.ofNullable(widget.getLayout().getStyle()).orElse("")));
    updatedWidget.getLayout().setStyleClass((Optional.ofNullable(widget.getLayout().getStyleClass()).orElse("")));
  }

  private void saveConfigurationsToProperty() throws JsonProcessingException {
    // Save default layout if user updated
    if (configurations.stream().filter(config -> config.getId().contentEquals(configuration.getId())).count() == 0) {
      configurations.add(0, configuration);
    }

    String configurationJson = mapper.writeValueAsString(configurations);
    Ivy.session().getSessionUser().setProperty(TASK_DETAILS_CONFIGURATION_PROPERTY, configurationJson);
  }

  private void updateUrlForCustomWidget(List<TaskDetailsWidget> widgets) {
    // get URL for ivy process in custom widgets
    for(TaskDetailsWidget widget : widgets) {
      if (widget instanceof TaskDetailsCustomWidget) {
        TaskDetailsCustomWidget customWidget = (TaskDetailsCustomWidget) widget;
        if (StringUtils.isNotBlank(customWidget.getData().getProcessStart())) {
          customWidget.getData().setUrl(ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath(customWidget.getData().getProcessStart()));
        }
      }
    }
  }

  public int getWidgetPositionByType(String widgetType) {
    int pos = -1;
    if (CollectionUtils.isNotEmpty(widgets)) {
      for (int i = 0; i < widgets.size(); i++) {
        if (isValidWidgetByType(i, widgetType)) {
          pos = i;
          break;
        }
      }
    }

    return pos;
  }

  private boolean isValidWidgetByType(int position, String widgetType) {
    return widgets.get(position) != null && widgets.get(position).getClass().getSimpleName().equals(widgetType);
  }
  
  public TaskDetails getConfiguration() {
    return configuration;
  }

  public List<TaskDetailsWidget> getWidgets() {
    return widgets;
  }

  public void setWidgets(List<TaskDetailsWidget> widgets) {
    this.widgets = widgets;
  }

  public boolean isReadOnlyMode() {
    return isReadOnlyMode;
  }

  public void setReadOnlyMode(boolean isReadOnlyMode) {
    this.isReadOnlyMode = isReadOnlyMode;
  }

  public boolean isShowNotAvailableData() {
    return hasShowNotAvailableData;
  }

  public void setShowNotAvailableData(boolean hasShowNotAvailableData) {
    this.hasShowNotAvailableData = hasShowNotAvailableData;
  }

  public boolean isShowDurationTime() {
    return hasShowDurationTime;
  }

  public void setShowDurationTime(boolean hasShowDurationTime) {
    this.hasShowDurationTime = hasShowDurationTime;
  }
}

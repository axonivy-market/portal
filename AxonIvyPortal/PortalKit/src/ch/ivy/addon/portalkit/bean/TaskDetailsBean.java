package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetails;
import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetailsWidget;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.globalvars.IGlobalVariableContext;

@ViewScoped
@ManagedBean
public class TaskDetailsBean implements Serializable {

  private static final long serialVersionUID = 8566646437739271552L;
  private static final String TASK_DETAILS_CONFIGURATION_PROPERTY = "task.details.widgets";
  private static final String PORTAL_TASK_DETAILS_GLOBAL_VARIABLE = "PORTAL_TASK_DETAILS";

  private TaskDetails configuration;
  private List<TaskDetailsWidget> widgets;
  private ObjectMapper mapper = new ObjectMapper().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
  private boolean isReadOnlyMode = true;
  private boolean hasShowNotAvailableData;
  private boolean hasShowDurationTime;
  private GlobalSettingService globalSettingService = new GlobalSettingService();

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
    String configurationJson = readConfigurationJsonInProperty();
    configuration = readConfiguration(configurationJson);
    widgets = configuration.getWidgets();
  }

  private String readConfigurationJsonInProperty() {
    return Ivy.session().getSessionUser().getProperty(TASK_DETAILS_CONFIGURATION_PROPERTY);
  }

  private TaskDetails readConfiguration(String configurationJson) throws JsonMappingException, JsonProcessingException, IOException {
    return StringUtils.isBlank(configurationJson) ? defaultConfiguration() : parseConfigurationJson(configurationJson);
  }

  private TaskDetails parseConfigurationJson(String configurationJson) throws JsonMappingException, JsonProcessingException {
    return mapper.readValue(configurationJson, TaskDetails.class);
  }

  private TaskDetails defaultConfiguration() throws IOException {
    String widgetsJsonData = IGlobalVariableContext.current().get(PORTAL_TASK_DETAILS_GLOBAL_VARIABLE);
    return mapper.readValue(widgetsJsonData, TaskDetails.class);
  }

  public void reset() throws IOException {
    removeConfigurationUserProperty();
    configuration = defaultConfiguration();
    widgets = configuration.getWidgets();
  }

  private void removeConfigurationUserProperty() {
    Ivy.session().getSessionUser().removeProperty(TASK_DETAILS_CONFIGURATION_PROPERTY);
  }

  public void switchToViewMode() {
    this.isReadOnlyMode = true;
  }

  public void switchToEditMode() {
    this.isReadOnlyMode = false;
  }

  public void save() throws JsonMappingException, JsonProcessingException {
    configuration.setChanged(true);
    List<TaskDetailsWidget> widgets = getUpdatedWidgets();
    updateToConfiguration(widgets);
    saveConfigurationToProperty();
  }

  private List<TaskDetailsWidget> getUpdatedWidgets() throws JsonMappingException, JsonProcessingException {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String nodes = Optional.ofNullable(requestParamMap.get("nodes")).orElse(StringUtils.EMPTY);
    return Arrays.asList(mapper.readValue(nodes, TaskDetailsWidget[].class));
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
  }

  private boolean doesWidgetExist(TaskDetailsWidget widget) {
    return widgets.indexOf(widget) > -1;
  }

  private void updateWidget(TaskDetailsWidget widget) {
    TaskDetailsWidget updatedWidget = widgets.get(widgets.indexOf(widget));
    updatedWidget.setAxisX(widget.getAxisX());
    updatedWidget.setAxisY(widget.getAxisY());
    updatedWidget.setWidth(widget.getWidth());
    updatedWidget.setHeight(widget.getHeight());
  }

  private void saveConfigurationToProperty() throws JsonProcessingException {
    String configurationJson = mapper.writeValueAsString(configuration);
    Ivy.session().getSessionUser().setProperty(TASK_DETAILS_CONFIGURATION_PROPERTY, configurationJson);
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

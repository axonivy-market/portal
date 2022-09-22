package ch.ivy.addon.portalkit.bean;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.constant.WidgetType;
import ch.ivy.addon.portalkit.dto.AbstractConfigurableContent;
import ch.ivy.addon.portalkit.dto.AbstractWidgetFilter;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.widget.AbstractWidget;
import ch.ivy.addon.portalkit.dto.widget.CustomWidget;
import ch.ivy.addon.portalkit.dto.widget.DocumentWidget;
import ch.ivy.addon.portalkit.dto.widget.HistoryWidget;
import ch.ivy.addon.portalkit.dto.widget.InformationWidget;
import ch.ivy.addon.portalkit.dto.widget.RelatedTaskWidget;
import ch.ivy.addon.portalkit.dto.widget.TechnicalCaseWidget;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.CustomWidgetUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public abstract class AbstractConfigurableContentBean<T extends AbstractConfigurableContent> implements Serializable {

  private static final long serialVersionUID = -5019885123920232407L;
  protected static ObjectMapper mapper;
  protected static GlobalSettingService globalSettingService;
  protected boolean isReadOnlyMode = true;
  protected boolean isReseted;
  protected boolean isShowNotAvailableData;

  protected T configuration;
  protected List<T> configurationList;
  protected List<? extends AbstractWidget> widgets;

  protected abstract Class<T> getConfigurationType();

  public void initConfig() {
    if (mapper == null) {
      mapper = new ObjectMapper().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    globalSettingService = new GlobalSettingService();
    isShowNotAvailableData = PermissionUtils.isSessionUserHasAdminRole();
  }

  protected void loadWidgets() throws Exception {
    this.configurationList = readConfigurations();
    boolean foundMatchedConfig = checkConfigurationByFilters();
    if (!foundMatchedConfig) {
      configuration = getDefaultConfig(this.configurationList);
    }
    if (Objects.isNull(configuration)) {
      configuration = getDefaultPortalConfig();
    }
    widgets = configuration.getWidgets();
    updateWidgetsType(widgets);
    updateUrlForCustomWidget(widgets);
  }

  private boolean checkConfigurationByFilters() throws JsonMappingException, JsonProcessingException {
    var configsChanged = this.configurationList.stream().filter(AbstractConfigurableContent::isChanged).collect(Collectors.toList());
    boolean foundMatchedConfig = findConfigByPredefinedFilters(configsChanged);
    if (!foundMatchedConfig) {
      var globalConfigs = loadGlobalConfigurations();
      foundMatchedConfig = findConfigByPredefinedFilters(globalConfigs);
    }
    return foundMatchedConfig;
  }

  protected abstract String getDefaultConfigId();

  protected boolean isFilterByCategories(String catgegoryPath, AbstractWidgetFilter filters) {
    return Optional.ofNullable(filters).map(AbstractWidgetFilter::getCategories).isPresent() && filters.getCategories().contains(catgegoryPath);
  }

  protected boolean isFilterByStates(String state,AbstractWidgetFilter filters) {
    return Optional.ofNullable(filters).map(AbstractWidgetFilter::getStates).isPresent() && filters.getStates().contains(state);
  }

  public void reset() throws IOException {
    this.isReseted = true;
    removeOldConfiguration();
    if (configuration == null) {
      configuration = getDefaultPortalConfig();
    }
    configuration.setChanged(false);
    this.widgets = configuration.getWidgets();
    updateWidgetsType(widgets);
    updateUrlForCustomWidget(this.widgets);
  }

  protected void removeOldConfiguration() throws JsonMappingException, JsonProcessingException {
    List<T> globalConfigurations = loadGlobalConfigurations();
    T configurationFromVariable = globalConfigurations.stream()
        .filter(filterByConfigurationId(this.configuration.getId()))
        .findFirst().orElse(null);
    if (configurationFromVariable == null) {
      this.configurationList.removeIf(filterByConfigurationId(this.configuration.getId()));
    }
    else {
      if (configurationFromVariable.getFilters() == null) {
        this.configuration = configurationFromVariable;
        return;
      }
    }

    boolean foundMatchedConfig = findConfigByPredefinedFilters(globalConfigurations);
    if (!foundMatchedConfig) {
      this.configuration = getDefaultConfig(globalConfigurations);
    }
    if (Objects.isNull(this.configuration)) {
      this.configuration = getDefaultPortalConfig();
    }
  }

  public abstract String getVariableKey();

  protected abstract boolean findConfigByPredefinedFilters(List<T> configurations);

  public void switchToEditMode() {
    this.isReadOnlyMode = false;
  }
  
  protected void updateUrlForCustomWidget(List<? extends AbstractWidget> widgets) {
    // get URL for ivy process in custom widgets
    for (AbstractWidget widget : widgets) {
      if (widget instanceof CustomWidget) {
        CustomWidget customWidget = (CustomWidget) widget;
        if (StringUtils.isNotBlank(customWidget.getData().getProcessStart())) {
          String url = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath(customWidget.getData().getProcessStart());
          customWidget.getData().setUrl(url);
        }
      }
    }
  }

  private String readConfigurationOfUser() {
    if (Ivy.session().isSessionUserUnknown()) {
      return EMPTY;
    }
    return getSessionUser().getProperty(getVariableKey());
  }

  private IUser getSessionUser() {
    return Ivy.session().getSessionUser();
  }

  protected List<T> readConfigurations() throws JsonMappingException, JsonProcessingException {
    List<T> result = new ArrayList<>();
    try {
      String userConfigurationJson = readConfigurationOfUser();
      if (StringUtils.isBlank(userConfigurationJson)) {
        result = loadGlobalConfigurations();
      }
      else {
        result = parseConfigurationJson(userConfigurationJson);
      }
    } catch (IOException e) {
      Ivy.log().debug("ParseUserConfiguration error: " + e);
      result = new ArrayList<>();
      result.add(getDefaultPortalConfig());
    }
    return result;
  }

  private List<T> parseConfigurationJson(String configurationJson) throws JsonMappingException, JsonProcessingException {
    return mapper.readValue(configurationJson, mapper.getTypeFactory().constructCollectionType(List.class, getConfigurationType()));
  }

  protected List<T> loadGlobalConfigurations() throws JsonMappingException, JsonProcessingException {
    var configurationJsonData = Ivy.var().get(getVariableKey());
    if (StringUtils.isNotBlank(configurationJsonData)) {
      try {
        return parseConfigurationJson(configurationJsonData);
      } catch (IOException e) {
        Ivy.log().debug("ParseUserConfiguration error: " + e);
      }
    }
    return loadDefaultGlobalConfigurations();
  }

  protected void updateWidgetsType(List<? extends AbstractWidget> widgets) {
    for (AbstractWidget widget : widgets) {
      if (widget instanceof HistoryWidget) {
        widget.setType(WidgetType.HISTORY);
      }
      else if (widget instanceof DocumentWidget) {
        widget.setType(WidgetType.DOCUMENT);
      }
      else if (widget instanceof InformationWidget) {
        widget.setType(WidgetType.INFORMATION);
      }
      else if (widget instanceof RelatedTaskWidget) {
        widget.setType(WidgetType.RELATED_TASK);
      }
      else if (widget instanceof TechnicalCaseWidget) {
        widget.setType(WidgetType.TECHINCAL_CASE);
      }
      else {
        widget.setType(WidgetType.CUSTOM);
      }
    }
  }

  public int getWidgetPositionByType(String widgetType) {
    if (CollectionUtils.isNotEmpty(widgets) && StringUtils.isNotBlank(widgetType)) {
      for (int i = 0; i < widgets.size(); i++) {
        if (isValidWidgetByType(i, widgetType)) {
          return i;
        }
      }
    }
    return -1;
  }

  private boolean isValidWidgetByType(int position, String widgetType) {
    return widgets.get(position) != null && widgets.get(position).getClass().getSimpleName().equals(widgetType);
  }

  protected List<T> loadDefaultGlobalConfigurations() throws JsonMappingException, JsonProcessingException {
    var configurationJsonData = Ivy.var().variable(getVariableKey()).defaultValue();
    return parseConfigurationJson(configurationJsonData);
  }
  
  protected T getDefaultPortalConfig() throws JsonMappingException, JsonProcessingException {
    List<T> defaultConfigurations = loadDefaultGlobalConfigurations();
    return getDefaultConfig(defaultConfigurations);
  }

  protected T getDefaultConfig(List<T> configurations) {
    return configurations.stream()
        .filter(config -> StringUtils.equals(getDefaultConfigId(), config.getId()))
        .findFirst().orElse(null);
  }

  protected void removeConfigurationUserProperty() {
    getSessionUser().removeProperty(getVariableKey());
  }

  public void save() throws IOException {
    this.isReadOnlyMode = true;
    updateToConfiguration();
    saveConfigurationsToUserProperty();
    this.isReseted = false;
  }

  private List<AbstractWidget> getUpdatedWidgets() throws JsonMappingException, JsonProcessingException {
    var result = new ArrayList<AbstractWidget>();
    List<WidgetLayout> layouts = extractWidgetLayoutFromRequest();
    CollectionUtils.emptyIfNull(layouts).forEach(layout -> {
      AbstractWidget currentWidget = widgets.stream()
                .filter(widget -> StringUtils.equals(widget.getId(), layout.getId()))
                .findFirst().get();

      currentWidget.getLayout().setAxisX(layout.getAxisX());
      currentWidget.getLayout().setAxisY(layout.getAxisY());
      currentWidget.getLayout().setWidth(layout.getWidth());
      currentWidget.getLayout().setHeight(layout.getHeight());
      result.add(currentWidget);
    });
    return result;
  }

  protected List<WidgetLayout> extractWidgetLayoutFromRequest() throws JsonProcessingException, JsonMappingException {
    Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    var nodes = Optional.ofNullable(requestParamMap.get("nodes")).orElse(EMPTY);
    return Arrays.asList(mapper.readValue(nodes, WidgetLayout[].class));
  }

  protected boolean doesWidgetExist(AbstractWidget widget) {
    return widgets.indexOf(widget) > -1;
  }

  protected void updateWidget(AbstractWidget selectedWidget) {
    WidgetLayout selectedLayout = selectedWidget.getLayout();
    AbstractWidget updatedWidget = widgets.get(widgets.indexOf(selectedWidget));
    updatedWidget.getLayout().setId(Optional.ofNullable(selectedLayout.getId()).orElse(EMPTY));
    updatedWidget.getLayout().setAxisX(selectedLayout.getAxisX());
    updatedWidget.getLayout().setAxisY(selectedLayout.getAxisY());
    updatedWidget.getLayout().setWidth(selectedLayout.getWidth());
    updatedWidget.getLayout().setHeight(selectedLayout.getHeight());
    updatedWidget.getLayout().setStyle((Optional.ofNullable(selectedLayout.getStyle()).orElse(EMPTY)));
    updatedWidget.getLayout().setStyleClass((Optional.ofNullable(selectedLayout.getStyleClass()).orElse(EMPTY)));
  }

  private void saveConfigurationsToUserProperty() throws JsonProcessingException {
    String configurationJson = mapper.writeValueAsString(configurationList);
    getSessionUser().setProperty(getVariableKey(), configurationJson);
  }

  private Predicate<? super T> filterByConfigurationId(String compareId) {
    return config -> StringUtils.equals(compareId, config.getId());
  }

  protected void updateToConfiguration() throws JsonMappingException, JsonProcessingException {
    configuration.setChanged(!this.isReseted);
    List<AbstractWidget> updateWidgets = getUpdatedWidgets();
    if (CollectionUtils.isNotEmpty(this.widgets)) {
      for (AbstractWidget widget : updateWidgets) {
        if (doesWidgetExist(widget)) {
          updateWidget(widget);
        }
      }
    }

    configuration.setWidgets(updateWidgets);
    for (T config : configurationList) {
      if (StringUtils.equals(config.getId(), configuration.getId())) {
        config.setChanged(!this.isReseted);
        config.setWidgets(configuration.getWidgets());
        break;
      }
    }
  }

  public String getPropertyByKeyPattern(Long referenceId, String keyPattern) {
    return CustomWidgetUtils.getPropertyByKeyPattern(referenceId, keyPattern);
  }

  public boolean isReadOnlyMode() {
    return isReadOnlyMode;
  }

  public void setReadOnlyMode(boolean isReadOnlyMode) {
    this.isReadOnlyMode = isReadOnlyMode;
  }

  public boolean isShowNotAvailableData() { 
    return isShowNotAvailableData;
  }

  public void setShowNotAvailableData(boolean isShowNotAvailableData) {
    this.isShowNotAvailableData = isShowNotAvailableData;
  }

  public T getConfiguration() {
    return configuration;
  }

  public void setConfiguration(T configuration) {
    this.configuration = configuration;
  }

  public List<?> getWidgets() {
    return widgets;
  }

  public void setWidgets(List<AbstractWidget> widgets) {
    this.widgets = widgets;
  }

  public List<T> getConfigurationList() {
    return configurationList;
  }

  public void setConfigurationList(List<T> configurationList) {
    this.configurationList = configurationList;
  }
}

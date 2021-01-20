package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.casedetails.CaseDetails;
import ch.ivy.addon.portalkit.dto.casedetails.CaseDetailsWidget;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.loader.ResourceLoader;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean
@ViewScoped
public class CaseDetailsBean implements Serializable {

  private static final long serialVersionUID = 1023540096176033250L;

  private static final String OPEN_CASES_LIST = "Start Processes/PortalStart/CaseListPage.ivp";
  private static final String CASE_DETAILS_CONFIGURATION_PROPERTY = "case.details.widgets";
  private static final String BREAK_LINE_CHARACTERS = "\n";

  private boolean isShowCaseDetails;
  private boolean isHideCaseDocument;
  private boolean isTaskStartedInDetails;
  private boolean showBackButton;
  private ICase selectedCase;
  private CaseActionBean caseActionBean;

  private CaseDetails configuration;
  private List<CaseDetailsWidget> widgets;
  private ObjectMapper mapper = new ObjectMapper().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
  private boolean isReadOnlyMode = true;
  private boolean hasShowNotAvailableData;
  private boolean hasApplyJsonConfigurationFile;
  private GlobalSettingService globalSettingService = new GlobalSettingService();
  

  @PostConstruct
  public void init() {
    isShowCaseDetails = PermissionUtils.hasPortalPermission(PortalPermission.SHOW_CASE_DETAILS);
    caseActionBean = ManagedBeans.get("caseActionBean");
    
    try {
      loadCaseDetailsSettings();
      loadWidgets();
    } catch (Exception e) {
      Ivy.log().error("Exception at method init of Class CaseDetailsBean", e);
    }
  }
  
  public void preRender(ICase selectedCase, boolean showBackButton) {
    this.selectedCase = selectedCase;
    this.showBackButton = showBackButton;
    this.isTaskStartedInDetails = BooleanUtils.toBooleanDefaultIfNull((Boolean) Ivy.session().getAttribute(SessionAttribute.IS_TASK_STARTED_IN_DETAILS.toString()), false);
  }
  
  private void loadCaseDetailsSettings() {
    hasShowNotAvailableData = PermissionUtils.isSessionUserHasAdminRole();
    hasApplyJsonConfigurationFile = Boolean.parseBoolean(globalSettingService
        .findGlobalSettingValue(GlobalVariable.APPLY_JSON_CONFIGURATION_FILE_FOR_TASK_DETAILS.toString()));
  }

  private void loadWidgets() throws Exception {
    if (hasApplyJsonConfigurationFile) {
      String configurationJson = readConfigurationJsonInProperty();
      configuration = readConfiguration(configurationJson);
      widgets = configuration.getWidgets();
    }
  }

  private String readConfigurationJsonInProperty() {
    return Ivy.session().getSessionUser().getProperty(CASE_DETAILS_CONFIGURATION_PROPERTY);
  }

  private CaseDetails readConfiguration(String configurationJson) throws JsonMappingException, JsonProcessingException, IOException {
    return StringUtils.isBlank(configurationJson) ? defaultConfiguration() : parseConfigurationJson(configurationJson);
  }

  private CaseDetails parseConfigurationJson(String configurationJson) throws JsonMappingException, JsonProcessingException {
    return mapper.readValue(configurationJson, CaseDetails.class);
  }

  private CaseDetails defaultConfiguration() throws IOException {
    ILibrary portalStyleLib = Ivy.wf().getApplication().findReleasedLibrary(PortalLibrary.PORTAL_STYLE.getValue());
    ResourceLoader loader = new ResourceLoader(portalStyleLib.getProcessModelVersion());
    Optional<Path> path = loader.getCaseDetailsWidgetConfiguration();
    String widgetsJsonData = String.join(BREAK_LINE_CHARACTERS, Files.readAllLines(path.get()));
    return mapper.readValue(widgetsJsonData, CaseDetails.class);
  }

  public void reset() throws IOException {
    removeConfigurationUserProperty();
    configuration = defaultConfiguration();
    widgets = configuration.getWidgets();
  }

  private void removeConfigurationUserProperty() {
    Ivy.session().getSessionUser().removeProperty(CASE_DETAILS_CONFIGURATION_PROPERTY);
  }

  public void switchToViewMode() {
    this.isReadOnlyMode = true;
  }

  public void switchToEditMode() {
    this.isReadOnlyMode = false;
  }

  public void save() throws JsonMappingException, JsonProcessingException {
    configuration.setChanged(true);
    List<CaseDetailsWidget> widgets = getUpdatedWidgets();
    updateToConfiguration(widgets);
    saveConfigurationToProperty();
  }

  private List<CaseDetailsWidget> getUpdatedWidgets() throws JsonMappingException, JsonProcessingException {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String nodes = Optional.ofNullable(requestParamMap.get("nodes")).orElse(StringUtils.EMPTY);
    return Arrays.asList(mapper.readValue(nodes, CaseDetailsWidget[].class));
  }

  private Map<String, String> getRequestParameterMap() {
    Map<String, String> requestParamMap =
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    return requestParamMap;
  }

  private void updateToConfiguration(List<CaseDetailsWidget> widgets) {
    if (CollectionUtils.isNotEmpty(this.widgets)) {
      for (CaseDetailsWidget widget : widgets) {
        if (doesWidgetExist(widget)) {
          updateWidget(widget);
        }
      }
    }
  }

  private boolean doesWidgetExist(CaseDetailsWidget widget) {
    return widgets.indexOf(widget) > -1;
  }

  private void updateWidget(CaseDetailsWidget widget) {
    CaseDetailsWidget updatedWidget = widgets.get(widgets.indexOf(widget));
    updatedWidget.setAxisX(widget.getAxisX());
    updatedWidget.setAxisY(widget.getAxisY());
    updatedWidget.setWidth(widget.getWidth());
    updatedWidget.setHeight(widget.getHeight());
  }

  private void saveConfigurationToProperty() throws JsonProcessingException {
    String configurationJson = mapper.writeValueAsString(configuration);
    Ivy.session().getSessionUser().setProperty(CASE_DETAILS_CONFIGURATION_PROPERTY, configurationJson);
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

  public void backToCasesList() {
    String friendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword("CaseListPage.ivp");
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
        && caseItem.getState() != CaseState.DONE
        && caseItem.getState() != CaseState.DESTROYED;
  }

  /**
   * Get the latest configuration of HIDE_CASE_DOCUMENT in GlobalSettingService
   * If null or empty, will return false
   */
  public void getHideCaseDocumentConfiguration() {
    isHideCaseDocument = new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_CASE_DOCUMENT.toString());
  }

  public boolean isHideCaseDocument() {
    return isHideCaseDocument;
  }

  public void setHideCaseDocument(boolean isHideCaseDocument) {
    this.isHideCaseDocument = isHideCaseDocument;
  }

  public boolean isShowCaseDetails() {
    return isShowCaseDetails;
  }

  public void setShowCaseDetails(boolean isShowCaseDetails) {
    this.isShowCaseDetails = isShowCaseDetails;
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

  public CaseDetails getConfiguration() {
    return configuration;
  }

  public List<CaseDetailsWidget> getWidgets() {
    return widgets;
  }

  public void setWidgets(List<CaseDetailsWidget> widgets) {
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

  public boolean isApplyJsonConfigurationFile() {
    return hasApplyJsonConfigurationFile;
  }

  public void setApplyJsonConfigurationFile(boolean hasApplyJsonConfigurationFile) {
    this.hasApplyJsonConfigurationFile = hasApplyJsonConfigurationFile;
  }
}

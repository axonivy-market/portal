package ch.ivy.addon.portal.generic.bean;

import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.WELCOME;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.MediaType;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.service.DeepLTranslationService;
import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DashboardModificationBean extends DashboardBean implements Serializable {

  private static final long serialVersionUID = 1L;
  protected static final String PUBLIC_DASHBOARD_DEFAULT_ICON = "si-network-share";
  protected static final String PRIVATE_DASHBOARD_DEFAULT_ICON = "si-single-neutral-shield";
  private static final String JSON_FILE_SUFFIX = "_Dashboard_Export.json";

  protected boolean isPublicDashboard;
  protected List<String> selectedDashboardPermissions;

  public void initConfigration(boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
    this.selectedDashboardPermissions = new ArrayList<>();
    collectDashboardsForManagement();
  }

  protected void collectDashboardsForManagement() {
    this.dashboards = new ArrayList<>();
    String dashboardInUserProperty = readDashboardBySessionUser();
    if (isPublicDashboard) {
      this.dashboards = DashboardUtils.getPublicDashboards();
      DashboardUtils.addDefaultTaskCaseListDashboardsIfMissing(this.dashboards);
    } else if (StringUtils.isNoneEmpty(dashboardInUserProperty)) {
      List<Dashboard> myDashboards = DashboardUtils.getPrivateDashboards();
      this.dashboards.addAll(myDashboards);
    }
  }

  public void openDashboardDetailDialog(Dashboard dashboard) {
    this.selectedDashboardPermissions = new ArrayList<>();
    this.selectedDashboard = dashboard;
    if (StringUtils.isBlank(this.selectedDashboard.getIcon())) {
      this.selectedDashboard
          .setIcon(this.isPublicDashboard ? PUBLIC_DASHBOARD_DEFAULT_ICON
              : PRIVATE_DASHBOARD_DEFAULT_ICON);
    }

    initDashboardPermissions(dashboard);
  }

  private void initDashboardPermissions(Dashboard dashboard) {
    dashboard.setPermissionDTOs(Optional.ofNullable(dashboard)
        .map(Dashboard::getPermissions).orElse(new ArrayList<>()).stream()
        .filter(Objects::nonNull).distinct()
        .map(permission -> findSecurityMemberDtoByName(permission))
        .collect(Collectors.toList()));

    this.selectedDashboardPermissions = Optional.ofNullable(dashboard)
        .map(Dashboard::getPermissionDTOs).orElse(new ArrayList<>()).stream()
        .map(SecurityMemberDTO::getName).collect(Collectors.toList());
  }
  
  private SecurityMemberDTO findSecurityMemberDtoByName(String permission) {
    return permission.startsWith("#")
        ? new SecurityMemberDTO(UserUtils.findUserByUsername(permission))
        : new SecurityMemberDTO(RoleUtils.findRole(permission));
  }

  public List<SecurityMemberDTO> completePermissions(String query) {
    return RoleUtils.findRoles(null, selectedDashboardPermissions, query).stream()
        .map(SecurityMemberDTOMapper::mapFromRoleDTO).collect(Collectors.toList());
  }

  public void saveDashboardDetail() {
    String currentTitle = this.selectedDashboard.getTitle();
    initMultipleLanguagesForDashboardName(currentTitle);
    List<SecurityMemberDTO> responsibles = this.selectedDashboard.getPermissionDTOs();
    List<String> permissions = new ArrayList<>();
    String displayedPermission = "";
    if (CollectionUtils.isNotEmpty(responsibles)) {
      Collection<SecurityMemberDTO> distinctPermissionDTOs = responsibles.stream()
          .collect(Collectors
              .toMap(SecurityMemberDTO::getMemberName, responsible -> responsible, (responsible1, responsible2) -> responsible1)).values();
      responsibles.clear();
      responsibles.addAll(distinctPermissionDTOs);
      displayedPermission = responsibles.stream().map(SecurityMemberDTO::getDisplayName).collect(Collectors.joining(", "));
      permissions = responsibles.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    }
    this.selectedDashboard.setDisplayedPermission(displayedPermission);
    this.selectedDashboard.setPermissions(permissions);
    if (!this.dashboards.contains(selectedDashboard)) {
      selectedDashboard.setVersion(DashboardJsonVersion.LATEST_VERSION.getValue());
      this.dashboards.add(selectedDashboard);
    }
    
    saveDashboards(new ArrayList<>(this.dashboards));
    updateSessionAttributeWhenDisplayTypeIsHidden();
  }
  
  private void updateSessionAttributeWhenDisplayTypeIsHidden() {
    if (!DashboardDisplayType.HIDDEN.equals(this.selectedDashboard.getDashboardDisplayType())) {
      return;
    }
    SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_SUB_DASHBOARD_ID.name());
  }
  
  public void removeDashboard() {
    removeWelcomeWidgetImagesOfDashboard(selectedDashboard);
    this.dashboards.remove(selectedDashboard);
    saveDashboards(new ArrayList<>(this.dashboards));
  }

  /**
   * Remove images of welcome widgets of a dashboard
   * @param selectedDashboard
   */
  private void removeWelcomeWidgetImagesOfDashboard(Dashboard selectedDashboard) {
    if (CollectionUtils.isEmpty(selectedDashboard.getWidgets())) {
      return;
    }
    for (DashboardWidget selectedWidget : selectedDashboard.getWidgets()) {
      if (WELCOME == selectedWidget.getType()) {
        removeWelcomeWidgetImage(selectedWidget);
      }
    }
  }

  /**
   * Remove the image of welcome widget from CMS
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

  private void saveDashboards(List<Dashboard> dashboards) {
    String dashboardJson = BusinessEntityConverter.entityToJsonValue(dashboards);
    if (isPublicDashboard) {
      Ivy.var().set(PortalVariable.DASHBOARD.key, dashboardJson);
    } else {
      currentUser().setProperty(PortalVariable.DASHBOARD.key, dashboardJson);
    }
    updateDashboardCache();
  }

  public void navigateToDashboardDetailsPage(String dashboardId) {
    PortalNavigator.navigateToDashboardDetailsPage(dashboardId, isPublicDashboard);
  }

  public void navigateToPublicDashBoardListPage() {
    PortalNavigator.navigateToDashboardConfigurationEditPageUrl(isPublicDashboard);
  }

  public void navigateToPrivateDashboardPage() {
    PortalNavigator.navigateToDashboardConfigurationEditPageUrl(isPublicDashboard);
  }

  public void onSelectedDeleteDashboard(Dashboard dashboard) {
    this.selectedDashboard = dashboard;
  }

  public void onSelectPermissionForDashboard(SelectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    this.selectedDashboardPermissions.add(selectedItem.getName());
  }

  public void onUnSelectPermissionForDashboard(UnselectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    this.selectedDashboardPermissions.remove(selectedItem.getName());
  }

  public void createDashboard() {
    collectDashboardsForManagement();
    saveDashboardDetail();
    navigateToDashboardDetailsPage(this.selectedDashboard.getId());
  }

  public void createDashboards() {
    collectDashboardsForManagement();
    saveDashboardDetail();
  }

  public boolean isPublicDashboard() {
    return isPublicDashboard;
  }

  public void setPublicDashboard(boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
  }

  public String generateDashboardPermisisonForDisplay(Dashboard dashboard) {
    return Optional.ofNullable(dashboard)
        .map(Dashboard::getPermissions)
        .filter(l -> CollectionUtils.isNotEmpty(l))
        .isPresent() ? String.join(", ", RoleUtils.getDisplayNameOfRoles(dashboard.getPermissions())) : "";
  }

  public void updateDashboardTitleByLocale() {
    String currentTitle = this.selectedDashboard.getTitle();
    initMultipleLanguagesForDashboardName(currentTitle);
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = this.selectedDashboard.getTitles().stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      optional.get().setValue(currentTitle);
    }
  }

  public void updateCurrentLanguage() {
    List<DisplayName> languages = this.selectedDashboard.getTitles();
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = languages.stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage()))
        .findFirst();
    if (optional.isPresent()) {
      this.selectedDashboard.setTitle(optional.get().getValue());
    }
  }

  public List<DisplayName> getTitles() {
    if (this.selectedDashboard == null) {
      return new ArrayList<>();
    }

    if (this.selectedDashboard.getTitles().isEmpty()) {
      List<String> supportedLanguages = getSupportedLanguages();
      for (String language : supportedLanguages) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        this.selectedDashboard.getTitles().add(displayName);
      }
    }
    return this.selectedDashboard.getTitles();
  }

  private Map<String, DisplayName> getMapLanguages() {
    List<DisplayName> languages = this.selectedDashboard.getTitles();
    return languages.stream().collect(Collectors.toMap(o -> o.getLocale().toLanguageTag(), o -> o));
  }

  private void initMultipleLanguagesForDashboardName(String currentTitle) {
    Map<String, DisplayName> mapLanguage = getMapLanguages();
    List<String> supportedLanguages = getSupportedLanguages();
    for (String language : supportedLanguages) {
      DisplayName localeLanguage = mapLanguage.get(language);
      if (localeLanguage == null) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        displayName.setValue(currentTitle);
        this.selectedDashboard.getTitles().add(displayName);
      } else if (StringUtils.isBlank(localeLanguage.getValue())) {
        localeLanguage.setValue(currentTitle);
      }
    }
  }

  public void translate(DisplayName title) {
    translatedText = Strings.EMPTY;
    warningText = Strings.EMPTY;

    String currentLanguage = UserUtils.getUserLanguage();
    if (!title.getLocale().getLanguage().equals(currentLanguage)) {
      Map<String, DisplayName> languages = getMapLanguages();
      DisplayName defaultTitle = languages.get(currentLanguage);
      if (defaultTitle != null) {
        try {
          translatedText = DeepLTranslationService.getInstance().translate(defaultTitle.getValue(),
              defaultTitle.getLocale(), title.getLocale());
        } catch (Exception e) {
          warningText = Ivy.cms()
              .co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/SomeThingWentWrong");
          Ivy.log().error("DeepL Translation Service error: ", e.getMessage());
        }
      }
    }

  }

  public boolean hasExportDashboardPermission() {
    return isPublicDashboard ?
        PermissionUtils.hasDashboardExportPublicPermission() : PermissionUtils.hasDashboardExportOwnPermission();
  }

  public boolean hasImportDashboardPermission(boolean isPublicDashboard) {
    return isPublicDashboard ?
        PermissionUtils.hasDashboardImportPublicPermission() : PermissionUtils.hasDashboardImportOwnPermission();
  }

  public StreamedContent exportToJsonFile(Dashboard dashboard) {
    dashboard.setVersion(DashboardJsonVersion.LATEST_VERSION.getValue());

    // For private dashboard, we don't need to export permission
    if (!dashboard.getIsPublic()) {
      dashboard.setPermissions(null);
    }

    Optional.ofNullable(dashboard).map(Dashboard::getWidgets).orElse(new ArrayList<>()).stream().forEach(widget -> {
      if (widget instanceof WelcomeDashboardWidget) {
        WelcomeDashboardWidget welcomeWidget = (WelcomeDashboardWidget) widget;
        welcomeWidget.setImageType(WelcomeWidgetUtils.getFileTypeOfImage(welcomeWidget.getImageType()));
        welcomeWidget.setImageContent(encodeWelcomeWidgetImage(welcomeWidget.getImageLocation(), welcomeWidget.getImageType()));
        
        welcomeWidget.setImageTypeDarkMode(WelcomeWidgetUtils.getFileTypeOfImage(welcomeWidget.getImageTypeDarkMode()));
        welcomeWidget.setImageContentDarkMode(encodeWelcomeWidgetImage(welcomeWidget.getImageLocationDarkMode(), welcomeWidget.getImageTypeDarkMode()));
      }
    });

    List<Dashboard> dashboardList = new ArrayList<>();
    dashboardList.add(dashboard);

    var inputStream = new ByteArrayInputStream(
        BusinessEntityConverter.prettyPrintEntityToJsonValue(dashboardList).getBytes(StandardCharsets.UTF_8));
    return DefaultStreamedContent
        .builder()
        .stream(() -> inputStream)
        .contentType(MediaType.APPLICATION_JSON)
        .name(getFileName(dashboard.getTitle()))
        .build();
  }

  private String encodeWelcomeWidgetImage(String imageLocation, String imageType) {
    if (StringUtils.isBlank(imageLocation)) {
      return "";
    }
    String result = "";
    ContentObject widgetImage = WelcomeWidgetUtils.getImageContentObject(imageLocation, imageType);
    if (widgetImage != null && widgetImage.exists()) {
      result = new String(Base64.getEncoder().encode(WelcomeWidgetUtils.readObjectValueOfDefaultLocale(widgetImage).read().bytes()));
    }
    return result;
  }

  private String getFileName(String dashboardName) {
    return dashboardName + JSON_FILE_SUFFIX;
  }

  public boolean isShowShareButtonOnConfig(boolean isPublicDashboard) {
    return isPublicDashboard && PermissionUtils.hasShareDashboardPermission();
  }

  public void saveArrangment() {
    if (isPublicDashboard) {
      savePublicArrangement();
    } else {
      savePrivateArrangement();
    }
    updateDashboardCache();
  }

  public void savePublicArrangement() {
    List<Dashboard> dashboards = DashboardUtils.getPublicDashboards();
    for (Dashboard dashboard : dashboards) {
      if (dashboard.getId() == null) {
        dashboard.setId(DashboardUtils.generateId());
      }
    }

    Map<String, Dashboard> idToDashboard = DashboardUtils.createMapIdToDashboard(dashboards);
    List<Dashboard> newDashboards = new ArrayList<>();
    for (Dashboard dashboardOrder : this.dashboards) {
      if (idToDashboard.containsKey(dashboardOrder.getId())) {
        newDashboards.add(idToDashboard.remove(dashboardOrder.getId()));
      }
    }
    newDashboards.addAll(idToDashboard.values());
    String dashboardsAsSJSON = BusinessEntityConverter.entityToJsonValue(newDashboards);
    Ivy.var().set(PortalVariable.DASHBOARD.key, dashboardsAsSJSON);
  }

  public void savePrivateArrangement() {
    String dashboardJson = BusinessEntityConverter.entityToJsonValue(this.dashboards);
    Ivy.session().getSessionUser().setProperty(PortalVariable.DASHBOARD.key, dashboardJson);
  }

  private void updateDashboardCache() {
    DashboardUtils.updateDashboardCache();
  }
}

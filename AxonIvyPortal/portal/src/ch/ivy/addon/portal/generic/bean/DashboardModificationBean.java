package ch.ivy.addon.portal.generic.bean;

import static ch.ivy.addon.portalkit.constant.PortalConstants.MAX_USERS_IN_AUTOCOMPLETE;
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
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
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
    } else if (StringUtils.isNoneEmpty(dashboardInUserProperty)) {
      List<Dashboard> myDashboards = getVisibleDashboards(dashboardInUserProperty);
      this.dashboards.addAll(myDashboards);
    }
  }

  public void openDashboardDetailDialog(Dashboard dashboard) {
    this.selectedDashboardPermissions = new ArrayList<>();
    this.selectedDashboard = dashboard;
    if (StringUtils.isBlank(this.selectedDashboard.getIcon())) {
      this.selectedDashboard.setIcon(this.isPublicDashboard ? PUBLIC_DASHBOARD_DEFAULT_ICON : PRIVATE_DASHBOARD_DEFAULT_ICON);
    }
    dashboard.setPermissionDTOs(new ArrayList<>());
    Map<String, SecurityMemberDTO> nameToSecurityMemberDTO = SecurityMemberUtils.findSecurityMembers("", 0, MAX_USERS_IN_AUTOCOMPLETE)
            .stream().filter(securityMember -> !securityMember.isUser())
            .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, v -> v));
    List<String> permissions = dashboard.getPermissions();
    if (CollectionUtils.isNotEmpty(permissions)) {
      var responsibles = permissions.stream().filter(Objects::nonNull).distinct()
          .filter(permission -> !permission.startsWith("#")).map(permission -> nameToSecurityMemberDTO.get(permission))
          .filter(Objects::nonNull).collect(Collectors.toSet());
      this.selectedDashboardPermissions = responsibles.stream().map(SecurityMemberDTO::getName).collect(Collectors.toList());
      dashboard.setPermissionDTOs(new ArrayList<>(responsibles));
    }
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
      if (WELCOME.equals(selectedWidget.getType())) {
        removeWelcomeWidgetImage(selectedWidget);
      }
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
  }

  private void saveDashboards(List<Dashboard> dashboards) {
    String dashboardJson = BusinessEntityConverter.entityToJsonValue(dashboards);
    if (isPublicDashboard) {
      Ivy.var().set(PortalVariable.DASHBOARD.key, dashboardJson);
    } else {
      currentUser().setProperty(PortalVariable.DASHBOARD.key, dashboardJson);
    }
  }

  private List<Dashboard> getVisibleDashboards(String dashboardJson) {
    if (isPublicDashboard) {
      return DashboardUtils.jsonToDashboards(dashboardJson);
    } else {
      return DashboardUtils.getVisibleDashboards(dashboardJson);
    }
  }

  public void navigateToDashboardDetailsPage(String dashboardId) {
    PortalNavigator.navigateToDashboardDetailsPage(dashboardId, isPublicDashboard);
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
        .isPresent() ? String.join(", ", dashboard.getPermissions()) : "";
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

    Optional.ofNullable(dashboard).map(Dashboard::getWidgets).orElse(new ArrayList<>())
      .stream().forEach(widget -> {
        if (widget instanceof WelcomeDashboardWidget) {
          var welcomeWidget = (WelcomeDashboardWidget) widget;
          welcomeWidget.setImageContent(encodeWelcomeWidgetImage(welcomeWidget));
        }
      });

    var inputStream = new ByteArrayInputStream(BusinessEntityConverter.prettyPrintEntityToJsonValue(dashboard).getBytes(StandardCharsets.UTF_8));
    return DefaultStreamedContent
        .builder()
        .stream(() -> inputStream)
        .contentType(MediaType.APPLICATION_JSON)
        .name(getFileName(dashboard.getTitle()))
        .build();
  }

  private String encodeWelcomeWidgetImage(WelcomeDashboardWidget widget) {
    if (Optional.ofNullable(widget).map(WelcomeDashboardWidget::getImageLocation).isEmpty()) {
      return null;
    }

    String result = "";
    ContentObject widgetImage = WelcomeWidgetUtils.getImageContentObject(widget.getImageLocation(), widget.getImageType());
    if (widgetImage != null && widgetImage.exists()) {
      result = new String(Base64.getEncoder().encode(WelcomeWidgetUtils.readObjectValueOfDefaultLocale(widgetImage).read().bytes()));
    }
    return result;
  }

  private String getFileName(String dashboardName) {
    return dashboardName + JSON_FILE_SUFFIX;
  }}

package com.axonivy.portal.bean;

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
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.components.util.Locales;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.dto.menu.CustomMenuItemDefinition;
import com.axonivy.portal.dto.menu.DashboardMenuItemDefinition;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition.MenuSource;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.service.CustomSubMenuItemService;
import com.axonivy.portal.service.DeepLTranslationService;
import com.axonivy.portal.service.PortalMenuItemDefinitionService;
import com.axonivy.portal.util.DefaultMenuUtils;
import com.axonivy.portal.util.MenuUtils;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

@ManagedBean
@ViewScoped
public class MenuManagementBean implements Serializable {

  private static final long serialVersionUID = -8511047442254110702L;

  private List<PortalMenuItemDefinition> menuDefinitions;
  private PortalMenuItemDefinition selectedMenuDefinition;

  private List<MenuKind> menuKinds;
  private MenuKind selectedMenuKind;
  private List<Dashboard> visibleDashboards;
  private boolean isCreate;
  private Dashboard selectedDashboard;

  // For icon
  private String selectedIcon;

  // For multi language titles
  private String translatedText;
  private String warningText;
  private String displayTitle;

  // Applications and processes
  private String selectedApplication;
  private List<String> applications;
  private List<IProcessStart> processStarts;
  private IProcessStart selectedProcess;

  // External link
  private String externalLink;
  private Boolean openInNewTab;

  // Others
  private List<String> supportedLanguages;
  private List<CustomSubMenuItem> menusFromConfiguration;

  private List<PortalMenuItemDefinition> loadCustomMenuItems() {
    List<PortalMenuItemDefinition> result = new ArrayList<>();
    List<CustomSubMenuItem> menusFromCallable = Optional.ofNullable(CustomSubMenuItemService.loadFromSubProcess())
        .orElseGet(() -> new ArrayList<>());

    if (CollectionUtils.isNotEmpty(menusFromCallable)) {
      for (var item : menusFromCallable) {
        item.setId(UUID.randomUUID().toString().replace("-", StringUtils.EMPTY));
      }

      result.addAll(menusFromCallable.stream()
          .map(item -> MenuUtils.fromCustomSubMenuItem(item, true, supportedLanguages)).collect(Collectors.toList()));
    }

    menusFromConfiguration = Optional.ofNullable(CustomSubMenuItemService.loadFromConfiguration())
        .orElseGet(() -> new ArrayList<>());
    if (CollectionUtils.isNotEmpty(menusFromConfiguration)) {
      for (var item : menusFromConfiguration) {
        item.setId(UUID.randomUUID().toString().replace("-", StringUtils.EMPTY));
      }
      result.addAll(menusFromConfiguration.stream()
          .map(item -> MenuUtils.fromCustomSubMenuItem(item, false, supportedLanguages)).collect(Collectors.toList()));
    }

    return result;
  }

  @PostConstruct
  public void init() {
    supportedLanguages = LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
    menuDefinitions = new ArrayList<>();

    RegisteredApplicationService.getInstance().findAll();

    loadMenuDefinitions();

    menuKinds = Arrays.asList(MenuKind.EXTERNAL_LINK, MenuKind.CUSTOM, MenuKind.MAIN_DASHBOARD);

    applications = IApplicationRepository.of(ISecurityContext.current()).all().stream().map(IApplication::getName)
        .toList();

    initVisibleDashboards();
  }

  private void loadMenuDefinitions() {
    menuDefinitions = new ArrayList<>();

    // Add default menu
    DefaultMenuUtils.addDefaultMenus(menuDefinitions);

    // Add menu from callable and Portal.CustomMenuItems
    addMenuDefinitions(loadCustomMenuItems());

    // Add menu from Portal.Dashboard
    List<Dashboard> dashboards = DashboardService.getInstance().loadTopMenuDashboards();
    if (CollectionUtils.isNotEmpty(dashboards)) {
      List<PortalMenuItemDefinition> dashboardMenuDefinitions = new ArrayList<>();
      for (Dashboard dashboard : dashboards) {
        // If has existing dashboard menu with same ID, replace with the new dashboard
        // Otherwise add the new dashboard menu
        PortalMenuItemDefinition existingDashboardMenu = (PortalMenuItemDefinition) menuDefinitions.stream()
            .filter(menu -> menu.getId().contentEquals(dashboard.getId())).findFirst().orElseGet(() -> null);

        if (Objects.nonNull(existingDashboardMenu)) {
          existingDashboardMenu = MenuUtils.fromDashboard(dashboard);
        } else {
          dashboardMenuDefinitions.add(MenuUtils.fromDashboard(dashboard));
        }
      }
      addMenuDefinitions(dashboardMenuDefinitions);
    }

    // Add menu from Portal.ThirdPartyApplications variable
    addMenuDefinitions(RegisteredApplicationService.getInstance().findAll().stream()
        .map(app -> MenuUtils.fromApplication(app, supportedLanguages)).collect(Collectors.toList()));

    // Initialize display permissions for menu
    menuDefinitions.forEach(menu -> initDisplayPermissions(menu));

    // Reorder menu definitions using the saved menu definitions
    reorderMenuDefinitions();
  }

  private void reorderMenuDefinitions() {
    List<PortalMenuItemDefinition> snapshotMenuDefinitions = PortalMenuItemDefinitionService.getInstance().findAll();

    for (var menu : menuDefinitions) {
      for (var menuOrder : snapshotMenuDefinitions) {
        menuOrder.setDisplayTitle(MenuUtils.getDisplayTitle(menuOrder));
        switch (menu.getType()) {

        // For dashboard menu: compare the menu ID
        case MAIN_DASHBOARD:
          if (menu.getId().contentEquals(menuOrder.getId())) {
            menu.setIndex(menuOrder.getIndex());
          }
          break;

        // For standard menu: compare the standard type (dashboard, task, case, or
        // process)
        case STANDARD:
          if (menuOrder.getType() == MenuKind.STANDARD) {
            StandardMenuItemDefinition standardMenu = (StandardMenuItemDefinition) menu;
            StandardMenuItemDefinition standardOrder = (StandardMenuItemDefinition) menuOrder;
            if (standardMenu.getStandardType() == standardOrder.getStandardType()) {
              menu.setIndex(menuOrder.getIndex());
            }
          }
          break;

        // For other menu: compare the display title because menu of these types don't
        // have ID to compare
        case CUSTOM:
        case THIRD_PARTY:
        case EXTERNAL_LINK:
          if (menu.getDisplayTitle().contentEquals(menuOrder.getDisplayTitle())) {
            menu.setIndex(menuOrder.getIndex());
          }
          break;
        default:
          break;
        }
      }
    }

    // Sort the menu definitions by index
    menuDefinitions.sort((menu1, menu2) -> menu1.getIndex() < menu2.getIndex() ? -1 : 1);
  }

  private void addMenuDefinitions(List<PortalMenuItemDefinition> newMenuDefinitions) {
    if (CollectionUtils.isEmpty(newMenuDefinitions)) {
      return;
    }

    int index = menuDefinitions.size() - 1;
    for (PortalMenuItemDefinition menu : newMenuDefinitions) {
      menu.setIndex(index++);
    }
    menuDefinitions.addAll(newMenuDefinitions);
  }

  public void initDisplayPermissions(PortalMenuItemDefinition menu) {
    initMenuPermissionDtos(menu);

    if (CollectionUtils.isEmpty(menu.getPermissionDTOs())) {
      return;
    }

    List<SecurityMemberDTO> permissions = Optional.ofNullable(menu.getPermissionDTOs())
        .orElseGet(() -> new ArrayList<>());
    String displayedPermission = StringUtils.EMPTY;

    if (CollectionUtils.isNotEmpty(permissions)) {
      Collection<SecurityMemberDTO> distinctPermissionDTOs = permissions.stream()
          .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, responsible -> responsible,
              (responsible1, responsible2) -> responsible1))
          .values();

      displayedPermission = distinctPermissionDTOs.stream().map(SecurityMemberDTO::getDisplayName)
          .collect(Collectors.joining(", "));
    }

    menu.setDisplayedPermission(displayedPermission);
  }

  public void clearSelections() {
    selectedApplication = null;
    selectedIcon = null;
    selectedMenuDefinition = null;
    selectedProcess = null;
    selectedMenuKind = null;
    openInNewTab = false;
  }

  private void initVisibleDashboards() {
    visibleDashboards = DashboardUtils.getPublicDashboards().stream()
        .filter(dashboard -> dashboard.getDashboardDisplayType() == DashboardDisplayType.SUB_MENU)
        .collect(Collectors.toList());
  }

  public List<PortalMenuItemDefinition> getMenuDefinitions() {
    return menuDefinitions;
  }

  public void setMenuDefinitions(List<PortalMenuItemDefinition> menuDefinitions) {
    this.menuDefinitions = menuDefinitions;
  }

  public PortalMenuItemDefinition getSelectedMenuDefinition() {
    return selectedMenuDefinition;
  }

  public void setSelectedMenuDefinition(PortalMenuItemDefinition selectedMenuDefinition) {
    this.selectedMenuDefinition = selectedMenuDefinition;
  }

  public String getMenuKindLabel(MenuKind kind) {
    return Ivy.cms().co(kind.getCmsUri());
  }

  public void onCreateNewMenu() {
    clearSelections();
    isCreate = true;
    selectedMenuDefinition = new CustomMenuItemDefinition();
    selectedMenuKind = MenuKind.CUSTOM;
  }

  public List<MenuKind> getMenuKinds() {
    return menuKinds;
  }

  public MenuKind getSelectedMenuKind() {
    return selectedMenuKind;
  }

  public void setSelectedMenuKind(MenuKind selectedMenuKind) {
    this.selectedMenuKind = selectedMenuKind;
  }

  public String getConfigurationDialogHeader() {
    return this.selectedMenuDefinition == null ? "Create Menu" : "Menu Details";
  }

  public void updateDisplayTitle(PortalMenuItemDefinition menu) {
    displayTitle = getDisplayMenuTitle(selectedMenuDefinition);
  }

  public String getDisplayMenuTitle(PortalMenuItemDefinition menu) {
    if (Optional.ofNullable(menu).map(PortalMenuItemDefinition::getType).isEmpty()) {
      return "";
    }
    if (menu.getType() == MenuKind.STANDARD) {
      StandardMenuItemDefinition standardMenu = (StandardMenuItemDefinition) menu;
      return Ivy.cms().co(standardMenu.getStandardType().getCmsUri());
    }

    String currentLanguage = UserUtils.getUserLanguage();

    return menu.getTitles().stream().filter(m -> m.getLocale().toLanguageTag().contentEquals(currentLanguage))
        .findFirst().map(DisplayName::getValue).orElseGet(() -> "");
  }

  public void createMenu() {
    updateTitlesByLocale();
    switch (selectedMenuDefinition.getType()) {
    case MAIN_DASHBOARD -> handleCreateDashboardMenu();
    case CUSTOM -> handleCreateCustomMenu();
    default -> {
    }
    }
    ;
    selectedMenuDefinition = null;
  }

  /**
   * Handle create menu type Dashboard. This method simply set the display type of
   * the selected public dashboard to TOP_MENU
   * 
   */
  private void handleCreateDashboardMenu() {
    DashboardMenuItemDefinition dashboardMenu = (DashboardMenuItemDefinition) selectedMenuDefinition;
    Dashboard dashboardToSave = DashboardService.getInstance().findById(dashboardMenu.getDashboard().getId());
    dashboardToSave.setDashboardDisplayType(DashboardDisplayType.TOP_MENU);
    DashboardService.getInstance().saveAllPublicConfig(Arrays.asList(dashboardToSave));
    DashboardUtils.updateDashboardCache();
  }

  private void handleCreateCustomMenu() {
    CustomSubMenuItemService
        .saveConfiguration(MenuUtils.toCustomSubMenuItem(selectedMenuDefinition, menusFromConfiguration));
  }

  public void updateMenu() {

  }

  public void onSelectMenuKind() {
    if (selectedMenuKind == null) {
      selectedMenuDefinition = null;
      return;
    }

    switch (selectedMenuKind) {
    case MAIN_DASHBOARD -> onSelectMenuKindDashboard();
    case EXTERNAL_LINK -> onSelectMenuKindExternalLink();
    case CUSTOM -> onSelectMenuKindCustom();
    default -> {
    }
    }

    selectedMenuDefinition.setPermissionDTOs(new ArrayList<>());
    selectedMenuDefinition.setPermissions(new ArrayList<>());
    updateTitlesByLocale();
  }

  public void onSelectDashboard() {
    selectedMenuDefinition = MenuUtils.fromDashboard(selectedDashboard);
    initMenuPermissionDtos(selectedMenuDefinition);
  }

  private void initMenuPermissionDtos(PortalMenuItemDefinition menu) {
    if (menu.getPermissionDTOs() == null) {
      menu.setPermissionDTOs(new ArrayList<>());
    }
    if (CollectionUtils.isNotEmpty(menu.getPermissions())) {
      for (String permission : menu.getPermissions()) {
        if (permission.startsWith("#")) {
          menu.getPermissionDTOs().add(SecurityMemberDTOMapper
              .mapFromUserDTO(new UserDTO(UserUtils.findUserByUsername(permission.substring(1)))));
        } else {
          menu.getPermissionDTOs()
              .add(SecurityMemberDTOMapper.mapFromRoleDTO(new RoleDTO(RoleUtils.findRole(permission))));
        }
      }
    }
  }

  public void onSelectApplication() {
    CustomMenuItemDefinition customMenu = (CustomMenuItemDefinition) selectedMenuDefinition;
    customMenu.setApplication(selectedApplication);

    // Initialize process starts by selected application
    IApplication selectedApp = IApplicationRepository.of(ISecurityContext.current()).findByName(selectedApplication)
        .get();

    processStarts = IWorkflowSession.current().getStartableProcessStarts();
    processStarts.stream()
        .filter(start -> start.getProcessModelVersion().getApplication().getId() == selectedApp.getId())
        .collect(Collectors.toList());
  }

  public void onSelectProcess() {
    CustomMenuItemDefinition customMenu = (CustomMenuItemDefinition) selectedMenuDefinition;
    customMenu.setProcessStart(selectedProcess);
    customMenu.setProcessStartPath(selectedProcess.getFullRequestPath());
  }

  public String getProcessStartDisplayName(IProcessStart process) {
    String name = process.getName();
    if (StringUtils.isBlank(name)) {
      name = process.getRequestPath();
    }
    return name;
  }

  private void onSelectMenuKindDashboard() {
    selectedMenuDefinition = new DashboardMenuItemDefinition();
    initVisibleDashboards();
  }

  private void onSelectMenuKindExternalLink() {
    selectedMenuDefinition = new ExternalLinkMenuItemDefinition();
    openInNewTab = false;
  }

  private void onSelectMenuKindCustom() {
    selectedMenuDefinition = new CustomMenuItemDefinition();

  }

  public List<Dashboard> getVisibleDashboards() {
    return visibleDashboards;
  }

  public void setVisibleDashboards(List<Dashboard> visibleDashboards) {
    this.visibleDashboards = visibleDashboards;
  }

  public boolean isRenderDashboardIcon() {
    return selectedMenuKind == MenuKind.DASHBOARD;
  }

  public boolean isRenderIconSelection() {
    return selectedMenuKind != MenuKind.DASHBOARD && selectedMenuDefinition != null;
  }

  public void updateTitlesByLocale() {
    initMultipleLanguagesForTitles();
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = selectedMenuDefinition.getTitles().stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      optional.get().setValue(displayTitle);
    }
  }

  private void initMultipleLanguagesForTitles() {
    if (selectedMenuDefinition.getTitles() == null) {
      selectedMenuDefinition.setTitles(new ArrayList<>());
    }

    Map<String, DisplayName> mapLanguage = getMapLanguages();
    List<String> supportedLanguages = getSupportedLanguages();
    for (String language : supportedLanguages) {
      DisplayName localeLanguage = mapLanguage.get(language);
      if (localeLanguage == null) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        displayName.setValue(selectedMenuDefinition.getDisplayTitle());
        selectedMenuDefinition.getTitles().add(displayName);
      } else if (StringUtils.isBlank(localeLanguage.getValue())) {
        localeLanguage.setValue(selectedMenuDefinition.getDisplayTitle());
      }
    }
  }

  private Map<String, DisplayName> getMapLanguages() {
    List<DisplayName> languages = this.selectedMenuDefinition.getTitles();
    if (CollectionUtils.isEmpty(languages)) {
      return new HashMap<>();
    }
    return languages.stream().collect(Collectors.toMap(o -> o.getLocale().toLanguageTag(), o -> o));
  }

  protected List<String> getSupportedLanguages() {
    return LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
  }

  public void updateCurrentLanguage() {
    List<DisplayName> languages = this.selectedMenuDefinition.getTitles();
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = languages.stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      displayTitle = optional.get().getValue();
    }
  }

  public boolean isShowTranslation(DisplayName title) {
    return DeepLTranslationService.getInstance().isShowTranslation(title.getLocale());
  }

  public boolean isFocus(DisplayName title) {
    return !isShowTranslation(title) && title.getLocale().getLanguage().equals(UserUtils.getUserLanguage());
  }

  public String getTranslatedText() {
    return translatedText;
  }

  public void applyTranslatedText(DisplayName displayName) {
    if (StringUtils.isNotBlank(getTranslatedText())) {
      displayName.setValue(getTranslatedText());
      setTranslatedText("");
    }
  }

  public boolean isRequiredField(DisplayName displayName) {
    String currentLanguage = UserUtils.getUserLanguage();
    String displayLanguage = displayName.getLocale().getLanguage();
    return currentLanguage.equals(displayLanguage);
  }

  public void setTranslatedText(String translatedText) {
    this.translatedText = translatedText;
  }

  public String getWarningText() {
    return warningText;
  }

  public void setWarningText(String warningText) {
    this.warningText = warningText;
  }

  public String getDisplayTitle() {
    return displayTitle;
  }

  public void setDisplayTitle(String displayTitle) {
    this.displayTitle = displayTitle;
  }

  public String formatName(SecurityMemberDTO responsible) {
    String responsibleName = StringUtils.EMPTY;
    if (responsible != null) {
      if (StringUtils.isBlank(responsible.getDisplayName())) {
        responsibleName = responsible.getName();
      } else {
        responsibleName = String.format("%s (%s)", responsible.getDisplayName(), responsible.getName());
      }
      return responsible.isEnabled() ? responsibleName
          : String.format("%s %s", Ivy.cms().co("/Labels/disabledUserPrefix"), responsibleName);
    }
    return responsibleName;
  }

  public List<SecurityMemberDTO> completePermissions(String query) {
    return RoleUtils.findRoles(null, selectedMenuDefinition.getPermissions(), query).stream()
        .map(SecurityMemberDTOMapper::mapFromRoleDTO).collect(Collectors.toList());
  }

  public void onSelectPermission(SelectEvent<Object> event) {
    if (selectedMenuDefinition.getPermissions() == null) {
      selectedMenuDefinition.setPermissions(new ArrayList<>());
      selectedMenuDefinition.setPermissionDTOs(new ArrayList<>());
    }

    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    selectedMenuDefinition.getPermissions().add(selectedItem.getName());
    selectedMenuDefinition.getPermissionDTOs().add(selectedItem);
  }

  public void onUnSelectPermission(UnselectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    selectedMenuDefinition.getPermissions().remove(selectedItem.getName());
    selectedMenuDefinition.getPermissionDTOs().remove(selectedItem);
  }

  public void onToggleOpenInNewTab() {
    ExternalLinkMenuItemDefinition externalMenu = (ExternalLinkMenuItemDefinition) selectedMenuDefinition;
    externalMenu.setOpenInNewTab(openInNewTab);
  }

  public String getSelectedApplication() {
    return selectedApplication;
  }

  public void setSelectedApplication(String selectedApplication) {
    this.selectedApplication = selectedApplication;
  }

  public List<String> getApplications() {
    return applications;
  }

  public void setApplications(List<String> applications) {
    this.applications = applications;
  }

  public List<IProcessStart> getProcessStarts() {
    return processStarts;
  }

  public void setProcessStarts(List<IProcessStart> processStarts) {
    this.processStarts = processStarts;
  }

  public IProcessStart getSelectedProcess() {
    return selectedProcess;
  }

  public void setSelectedProcess(IProcessStart selectedProcess) {
    this.selectedProcess = selectedProcess;
  }

  public String getSelectedIcon() {
    return selectedIcon;
  }

  public void setSelectedIcon(String selectedIcon) {
    this.selectedIcon = selectedIcon;
  }

  public String getExternalLink() {
    return externalLink;
  }

  public void setExternalLink(String externalLink) {
    this.externalLink = externalLink;
  }

  public Boolean getOpenInNewTab() {
    return openInNewTab;
  }

  public void setOpenInNewTab(Boolean openInNewTab) {
    this.openInNewTab = openInNewTab;
  }

  public void onRowReorder(ReorderEvent event) {
    MenuUtils.reorderMenuDefinitions(menuDefinitions, event.getFromIndex(), event.getToIndex());
    PortalMenuItemDefinitionService.getInstance().saveAllPublicConfig(menuDefinitions);
    loadMenuDefinitions();
  }

  public void onEditMenu(PortalMenuItemDefinition menu) {
    clearSelections();
    selectedMenuDefinition = menu;
    isCreate = false;

    if (menu.getType() == MenuKind.MAIN_DASHBOARD) {
      initVisibleDashboards();
      DashboardMenuItemDefinition dashboardMenu = (DashboardMenuItemDefinition) menu;
      visibleDashboards.add(dashboardMenu.getDashboard());
    }
  }

  public void removeMenu() {
    menuDefinitions.remove(selectedMenuDefinition);
    switch (selectedMenuDefinition.getType()) {
    case MAIN_DASHBOARD -> handleRemoveDashboardMenu();
    case CUSTOM -> handleRemoveCustomMenu();
    case EXTERNAL_LINK -> handleRemoveExternalLink();
    default -> {
    }
    }
  }

  private void handleRemoveDashboardMenu() {
    DashboardMenuItemDefinition dashboardMenu = (DashboardMenuItemDefinition) selectedMenuDefinition;
    Dashboard dashboardToSave = DashboardService.getInstance().findById(dashboardMenu.getDashboard().getId());
    dashboardToSave.setDashboardDisplayType(DashboardDisplayType.SUB_MENU);
    DashboardService.getInstance().saveAllPublicConfig(Arrays.asList(dashboardToSave));
    DashboardUtils.updateDashboardCache();
  }

  private void handleRemoveCustomMenu() {
    CustomMenuItemDefinition customMenu = (CustomMenuItemDefinition) selectedMenuDefinition;
    if (customMenu.getSource() == MenuSource.CUSTOM_MENU_CONFIGURATION) {
      CustomSubMenuItemService.removeConfiguration(MenuUtils.toCustomSubMenuItem(customMenu, menusFromConfiguration));
    }
  }

  private void handleRemoveExternalLink() {
    ExternalLinkMenuItemDefinition externalLinkMenu = (ExternalLinkMenuItemDefinition) selectedMenuDefinition;
    if (externalLinkMenu.getSource() == MenuSource.CUSTOM_MENU_CONFIGURATION) {
      CustomSubMenuItemService
          .removeConfiguration(MenuUtils.toCustomSubMenuItem(externalLinkMenu, menusFromConfiguration));
    } else if (externalLinkMenu.getSource() == MenuSource.THIRD_PARTY_APP_CONFIGURATION) {
      List<Application> apps = (RegisteredApplicationService.getInstance().findAll());
      apps.sort(Comparator.comparingInt(Application::getMenuOrdinal));

      boolean isDeleted = false;
      for (Application app : apps) {
        if (isDeleted) {
          app.setMenuOrdinal(app.getMenuOrdinal() - 1);
          continue;
        }

        Map<String, String> displayNames = DisplayNameConvertor.parseJson(app.getDisplayName()).getDisplayNameAsMap();
        String displayName = displayNames.get(Locales.getCurrentLocale().toLanguageTag());
        if (displayName.equals(selectedMenuDefinition.getDisplayTitle())) {
          apps.remove(app);
          isDeleted = true;
        }
      }
      RegisteredApplicationService.getInstance().saveAllPublicConfig(apps);
    }
  }

  public boolean isCreate() {
    return isCreate;
  }

  public Dashboard getSelectedDashboard() {
    return selectedDashboard;
  }

  public void setSelectedDashboard(Dashboard selectedDashboard) {
    this.selectedDashboard = selectedDashboard;
  }
}
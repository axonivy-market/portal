package com.axonivy.portal.bean.menu;

import static com.axonivy.portal.menu.management.enums.MenuSource.DASHBOARD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.dto.menu.CustomMenuItemDefinition;
import com.axonivy.portal.dto.menu.DashboardMenuItemDefinition;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.dto.menu.StaticPageMenuItemDefinition;
import com.axonivy.portal.menu.management.MenuCreationHandler;
import com.axonivy.portal.menu.management.MenuModificationHandler;
import com.axonivy.portal.menu.management.adapter.DashboardMenuItemDefinitionAdapter;
import com.axonivy.portal.menu.management.enums.ContentState;
import com.axonivy.portal.menu.management.enums.MenuDetailsMode;
import com.axonivy.portal.menu.management.enums.MenuSource;

import ch.ivy.addon.portal.generic.bean.IMultiLanguage;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class MenuDetailsBean extends AbstractMenuBean implements Serializable, IMultiLanguage {

  private static final long serialVersionUID = -5092027599244228536L;

  private PortalMenuItemDefinition selectedMenuDefinition;

  private MenuKind selectedMenuKind;
  private MenuDetailsMode mode;
  private List<Dashboard> visibleDashboards;
  private Dashboard selectedDashboard;
  private ContentState contentState;

  // For icon
  private String selectedIcon;

  // For multi language titles
  private String translatedText;
  private String warningText;

  // Processes
  private List<Process> processStarts;
  private DashboardProcess selectedProcess;

  // External link
  private String externalLink;
  private Boolean openInNewTab;

  // Static page
  private String staticPagePath;

  private int lastIndex;

  public PortalMenuItemDefinition getSelectedMenuDefinition() {
    return selectedMenuDefinition;
  }

  public void setSelectedMenuDefinition(PortalMenuItemDefinition selectedMenuDefinition) {
    this.selectedMenuDefinition = selectedMenuDefinition;
  }

  public MenuKind getSelectedMenuKind() {
    return selectedMenuKind;
  }

  public void setSelectedMenuKind(MenuKind selectedMenuKind) {
    this.selectedMenuKind = selectedMenuKind;
  }

  public List<Dashboard> getVisibleDashboards() {
    return visibleDashboards;
  }

  public void setVisibleDashboards(List<Dashboard> visibleDashboards) {
    this.visibleDashboards = visibleDashboards;
  }

  public Dashboard getSelectedDashboard() {
    return selectedDashboard;
  }

  public void setSelectedDashboard(Dashboard selectedDashboard) {
    this.selectedDashboard = selectedDashboard;
  }

  public String getSelectedIcon() {
    return selectedIcon;
  }

  public void setSelectedIcon(String selectedIcon) {
    this.selectedIcon = selectedIcon;
  }

  public String getTranslatedText() {
    return translatedText;
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

  public List<Process> getProcessStarts() {
    return processStarts;
  }

  public void setProcessStarts(List<Process> processStarts) {
    this.processStarts = processStarts;
  }

  public DashboardProcess getSelectedProcess() {
    return selectedProcess;
  }

  public void setSelectedProcess(DashboardProcess selectedProcess) {
    this.selectedProcess = selectedProcess;
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

  public String getStaticPagePath() {
    return staticPagePath;
  }

  public void setStaticPagePath(String staticPagePath) {
    this.staticPagePath = staticPagePath;
  }

  public boolean isReadOnly() {
    return MenuDetailsMode.READ_ONLY.equals(mode);
  }

  public boolean isCreate() {
    return MenuDetailsMode.CREATE.equals(mode);
  }

  public boolean isEdit() {
    return MenuDetailsMode.EDIT.equals(mode);
  }

  public Set<MenuKind> getMenuKinds() {
    return MenuKind.CREATABLE_MENU_KINDS;
  }

  public ContentState getContentState() {
    return contentState;
  }

  @PostConstruct
  public void init() {
    initVisibleDashboards();
    initPermissionDTOs();
  }

  private void initVisibleDashboards() {
    visibleDashboards = DashboardUtils.getPublicDashboards().stream()
        .filter(dashboard -> dashboard.getDashboardDisplayType() == DashboardDisplayType.SUB_MENU)
        .collect(Collectors.toList());
  }

  private void initPermissionDTOs() {
    if (Optional.ofNullable(selectedMenuDefinition).map(PortalMenuItemDefinition::getPermissions).isPresent()) {
      Function<String, SecurityMemberDTO> permissionMapper = permission -> {
        return permission.startsWith("#") ? new SecurityMemberDTO(UserUtils.findUserByUsername(permission))
            : new SecurityMemberDTO(RoleUtils.findRole(permission));
      };
      selectedMenuDefinition.setPermissionDTOs(
          selectedMenuDefinition.getPermissions().stream().map(permissionMapper).collect(Collectors.toList()));
    }

  }

  public void onCreateNewMenu(int lastIndex) {
    clearSelections();
    mode = MenuDetailsMode.CREATE;
    setSelectedMenuDefinition(new CustomMenuItemDefinition());
    selectedMenuKind = MenuKind.CUSTOM;
    contentState = ContentState.CUSTOM_MENU_VARIABLE_CREATE;
    selectedMenuDefinition.setPermissions(new ArrayList<>());
    selectedMenuDefinition.getPermissions().add(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
    initMenuPermissionDtos(selectedMenuDefinition);
    this.lastIndex = lastIndex;
  }

  public void onEditMenu(PortalMenuItemDefinition menu) {
    clearSelections();
    selectedMenuDefinition = menu;
    mode = MenuDetailsMode.EDIT;

    if (MenuSource.CALLABLE.equals(selectedMenuDefinition.getSource())) {
      mode = MenuDetailsMode.READ_ONLY;
    }

    selectedMenuKind = menu.getType();
    selectedIcon = menu.getIcon();

    switch (selectedMenuKind) {
      case MAIN_DASHBOARD -> {
        DashboardMenuItemDefinition dashboardMenu = (DashboardMenuItemDefinition) selectedMenuDefinition;
        visibleDashboards.clear();
        visibleDashboards.add(dashboardMenu.getDashboard());
        selectedDashboard = dashboardMenu.getDashboard();
        contentState = ContentState.DASHBOARD_EDIT;
      }
      case CUSTOM -> {
        CustomMenuItemDefinition customMenu = (CustomMenuItemDefinition) selectedMenuDefinition;
        loadProcesses();
        selectedProcess = customMenu.getProcessStart();
        contentState = customMenu.getSource() == MenuSource.CALLABLE ? ContentState.CUSTOM_MENU_CALLABLE_EDIT
            : ContentState.CUSTOM_MENU_VARIABLE_EDIT;
      }
      case EXTERNAL_LINK -> {
        ExternalLinkMenuItemDefinition externalMenu = (ExternalLinkMenuItemDefinition) selectedMenuDefinition;
        externalLink = externalMenu.getUrl();
        if (externalMenu.getSource() == MenuSource.THIRD_PARTY_APP_CONFIGURATION) {
          openInNewTab = true;
          contentState = ContentState.THIRD_PARTY_APPLICATION_EDIT;
        } else {
          openInNewTab = externalMenu.getOpenInNewTab();
          contentState = ContentState.EXTERNAL_LINK_EDIT;
        }
      }
      case STATIC_PAGE -> {
        StaticPageMenuItemDefinition staticPageMenu = (StaticPageMenuItemDefinition) selectedMenuDefinition;
        staticPagePath = staticPageMenu.getUrl();
        contentState = ContentState.STATIC_PAGE_EDIT;
      }
      default -> {}
    }
  }

  public void clearSelections() {
    selectedMenuDefinition = null;
    selectedMenuKind = null;
    selectedIcon = null;
    // dashboard menu
    selectedDashboard = null;
    // custom menu
    selectedProcess = null;
    // external link menu
    externalLink = null;
    openInNewTab = false;
    // static page
    staticPagePath = null;
  }

  public String getConfigurationDialogHeader() {
    return this.selectedMenuDefinition == null
        ? Ivy.cms().co("/Dialogs/com/axonivy/portal/page/MenuManagement/AddNewMenu")
        : Ivy.cms().co("/Dialogs/com/axonivy/portal/page/MenuManagement/MenuDetails");
  }

  public void updateDisplayTitle(PortalMenuItemDefinition menu) {
    selectedMenuDefinition.setDisplayTitle(getDisplayMenuTitle(selectedMenuDefinition));
  }

  public String getDisplayMenuTitle(PortalMenuItemDefinition menu) {
    if (Optional.ofNullable(menu).map(PortalMenuItemDefinition::getType).isEmpty()) {
      return StringUtils.EMPTY;
    }
    if (menu.getType() == MenuKind.STANDARD) {
      StandardMenuItemDefinition standardMenu = (StandardMenuItemDefinition) menu;
      return Ivy.cms().co(standardMenu.getStandardType().getCmsUri());
    }

    String currentLanguage = UserUtils.getUserLanguage();

    return menu.getTitles().stream().filter(m -> m.getLocale().toLanguageTag().contentEquals(currentLanguage))
        .findFirst().map(DisplayName::getValue).orElse(StringUtils.EMPTY);
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
      case STATIC_PAGE -> onSelectMenuKindStaticPage();
      default -> {}
    }

    selectedMenuDefinition.setPermissionDTOs(new ArrayList<>());
    selectedMenuDefinition.setPermissions(new ArrayList<>());
    updateTitlesByLocale(selectedMenuDefinition);
  }

  public void onSelectDashboard() {
    selectedMenuDefinition = DashboardMenuItemDefinitionAdapter.getInstance().toMenuDefinition(selectedDashboard,
        DASHBOARD);
    initMenuPermissionDtos(selectedMenuDefinition);
    selectedIcon = selectedMenuDefinition.getIcon();
  }

  public List<Process> findProcess(String query) {
    if (processStarts == null) {
      loadProcesses();
    }
    if (StringUtils.isBlank(query)) {
      return processStarts;
    }
    return processStarts.stream().filter(process -> process.getName().equals(query)).collect(Collectors.toList());
  }

  private void loadProcesses() {
    processStarts = new ArrayList<>();
    List<IWebStartable> processes = ProcessService.getInstance().findProcesses();
    if (CollectionUtils.isNotEmpty(processes)) {
      processes.forEach(process -> processStarts.add(new DashboardProcess(process)));
    }
  }

  private void onSelectMenuKindStaticPage() {
    selectedMenuDefinition = new StaticPageMenuItemDefinition();
    staticPagePath = null;
    contentState = ContentState.STATIC_PAGE_CREATE;
  }

  public void onSelectProcess() {
    CustomMenuItemDefinition customMenu = (CustomMenuItemDefinition) selectedMenuDefinition;
    customMenu.setProcessStart(selectedProcess);
    customMenu.setProcessStartPath(selectedProcess.getStartLink());
    customMenu.setIcon(selectedProcess.getIcon());
    customMenu.setDisplayTitle(customMenu.getProcessStart().getName());
    updateSelectedMenuTitlesByLocale();
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
    contentState = ContentState.DASHBOARD_CREATE;
  }

  private void onSelectMenuKindExternalLink() {
    selectedMenuDefinition = new ExternalLinkMenuItemDefinition();
    openInNewTab = false;
    contentState = ContentState.EXTERNAL_LINK_CREATE;
  }

  private void onSelectMenuKindCustom() {
    selectedMenuDefinition = new CustomMenuItemDefinition();
    selectedProcess = null;
    contentState = ContentState.CUSTOM_MENU_VARIABLE_CREATE;
  }

  public void updateSelectedMenuTitlesByLocale() {
    updateTitlesByLocale(selectedMenuDefinition);
  }

  private void updateTitlesByLocale(PortalMenuItemDefinition menu) {
    initMultipleLanguagesForTitles(menu);
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = menu.getTitles().stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      optional.get().setValue(menu.getDisplayTitle());
    }
  }

  private void initMultipleLanguagesForTitles(PortalMenuItemDefinition menu) {
    if (menu.getTitles() == null) {
      menu.setTitles(new ArrayList<>());
    }

    initAndSetValue(menu.getDisplayTitle(), menu.getTitles());
  }

  public void updateCurrentLanguage() {
    List<DisplayName> languages = this.selectedMenuDefinition.getTitles();
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = languages.stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      selectedMenuDefinition.setDisplayTitle(optional.get().getValue());
    }
  }

  public void applyTranslatedText(DisplayName displayName) {
    if (StringUtils.isNotBlank(getTranslatedText())) {
      displayName.setValue(getTranslatedText());
      setTranslatedText("");
    }
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

  public void createMenu() {
    MenuCreationHandler.createMenu(mapFields(selectedMenuDefinition));
    selectedMenuDefinition = null;
    clearSelections();
  }

  public void updateMenu() {
    MenuModificationHandler.updateMenu((mapFields(selectedMenuDefinition)));
    selectedMenuDefinition = null;
    clearSelections();
  }

  private PortalMenuItemDefinition mapFields(PortalMenuItemDefinition menu) {
    updateTitlesByLocale(selectedMenuDefinition);
    menu.setIcon(selectedIcon);
    menu.setIndex(lastIndex + 1);
    menu.setVersion(AbstractJsonVersion.LATEST);

    switch (menu.getType()) {
      case DASHBOARD -> {
      DashboardMenuItemDefinition dashboardMenu = (DashboardMenuItemDefinition) menu;
        dashboardMenu.setDashboard(selectedDashboard);
        dashboardMenu.setDashboardId(selectedDashboard.getId());
        return dashboardMenu;
      }
      case CUSTOM -> {
        CustomMenuItemDefinition customMenu = (CustomMenuItemDefinition) menu;
        customMenu.setProcessStart(selectedProcess);
        return customMenu;
      }
      case EXTERNAL_LINK -> {
        ExternalLinkMenuItemDefinition externalMenu = (ExternalLinkMenuItemDefinition) menu;
        externalMenu.setUrl(externalLink);
        externalMenu.setOpenInNewTab(openInNewTab);
        return externalMenu;
      }
      case STATIC_PAGE -> {
        StaticPageMenuItemDefinition staticPageMenu = (StaticPageMenuItemDefinition) menu;
        staticPageMenu.setUrl(staticPagePath);
        return staticPageMenu;
      }
      default -> {
        return menu;
      }
    }
  }
}

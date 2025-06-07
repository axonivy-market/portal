package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.FacesMessageUtils;
import com.axonivy.portal.components.util.Locales;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.service.DeepLTranslationService;

import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.GlobalVariable.Option;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.loader.ApplicationMultiLanguageNameLoader;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.DisplayNameAdaptor;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PrimeFacesUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class AdminSettingBean implements Serializable {
  private static final long serialVersionUID = 1506137118077215366L;

  public static final String PORTAL_MESSAGE_INFO = "portal-management-messages-information";
  public static final String APPLICATION_TAB_ID = "application-tab";
  public static final String ADMIN_SETTING_TAB_ID = "setting-tab";
  public static final String ANNOUNCEMENT_SETTING_TAB_ID = "announcement-tab";
  public static final String ROLE_MANAGEMENT_TAB_ID = "role-management-tab";
  public static final String PASSWORD_VALIDATION_TAB_ID = "password-validation-tab";

  private boolean isShowRoleManagementTab;
  private boolean isTabChangeEventTriggered;
  private boolean isShowPasswordValidationTab;
  private List<String> selectedApplicationPermissions;
  private List<Application> applicationList;
  private Application selectedApp;
  private String dialogTitle;
  private boolean isAddMode;

  // Add multi-language support fields
  private List<DisplayName> supportedLanguages;
  private String displayNameInCurrentLanguage;
  private List<String> languages;

  public void initAdminTabViewConfig() {
    if (isTabChangeEventTriggered) {
      isTabChangeEventTriggered = false;
      return;
    }
    isShowRoleManagementTab = canSeeRoleManagement();
    isShowPasswordValidationTab = canSeePasswordValidation();
    initApplicationTab();
  }

  public void onTabChange(TabChangeEvent<Object> tabChangeEvent) {
    if (tabChangeEvent.getComponent() instanceof TabView) {
      var tabId = tabChangeEvent.getTab().getId();
      switch (tabId) {
        case APPLICATION_TAB_ID:
          initApplicationTab();
          break;
        case ADMIN_SETTING_TAB_ID:
          initAdminSettingsTab();
          break;
        case ANNOUNCEMENT_SETTING_TAB_ID:
          initAnnouncementTab();
          break;
        case ROLE_MANAGEMENT_TAB_ID:
          break;
        case PASSWORD_VALIDATION_TAB_ID:
          break;
        default:
          break;
      }
    }
    isTabChangeEventTriggered = true;
  }

  private void initApplicationTab() {
    invokeAdminSettingsComponentLogic("#{logic.initApplicationSettings}", new Object[] {});
  }

  private void initAnnouncementTab() {
    invokeAdminSettingsComponentLogic("#{logic.initAnnouncementSettings}", new Object[] {});
  }

  private void initAdminSettingsTab() {
    PrimeFacesUtils.executeScript("PF('settingTable').filter()");
    invokeAdminSettingsComponentLogic("#{logic.initAdminSettings}", new Object[] {});
  }

  public void onApplicationReorder(ReorderEvent reorderEvent) {
    int fromIndex = reorderEvent.getFromIndex();
    int toIndex = reorderEvent.getToIndex();
    List<Application> applicationList = Attrs.currentContext().getAttribute("#{data.applicationList}", List.class);

    Application selectedApp = applicationList.remove(fromIndex);
    applicationList.add(toIndex, selectedApp);

    for (int i = 0; i < applicationList.size(); i++) {
      applicationList.get(i).setMenuOrdinal(i);
    }

    invokeAdminSettingsComponentLogic("#{logic.onApplicationReorder}", new Object[] {applicationList, selectedApp});
  }

  private void invokeAdminSettingsComponentLogic(String methodName, Object[] param) {
    var adminSettingTabChange = new IvyComponentLogicCaller<String>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    adminSettingTabChange.invokeComponentLogic(componentId, methodName, param);
  }

  public static void updatePortalManagementMessages() {
    FacesMessage message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_WARN,
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/dialogclosinginformation/informMessage"), null);
    FacesContext.getCurrentInstance().addMessage(PORTAL_MESSAGE_INFO, message);
    PrimeFaces.current().ajax().update("portal-management-messages");
  }

  private boolean canSeeRoleManagement() {
    return PermissionUtils.hasPortalPermission(PortalPermission.ROLE_MANAGEMENT);
  }

  private boolean canSeePasswordValidation() {
    return PermissionUtils.hasPortalPermission(PortalPermission.PASSWORD_VALIDATION);
  }

  public String getDropdownItemlabel(Object item) {
    if (item instanceof Option) {
      return ((Option) item).translate();
    }
    return String.valueOf(item);
  }

  public boolean isShowRoleManagementTab() {
    return isShowRoleManagementTab;
  }

  public void setShowRoleManagementTab(boolean isShowRoleManagementTab) {
    this.isShowRoleManagementTab = isShowRoleManagementTab;
  }

  public boolean isShowPasswordValidationTab() {
    return isShowPasswordValidationTab;
  }

  public void setShowPasswordValidationTab(boolean isShowPasswordValidationTab) {
    this.isShowPasswordValidationTab = isShowPasswordValidationTab;
  }

  public void initApplicationPermissions(Application application) {
    if (application == null) {
      this.selectedApplicationPermissions = new ArrayList<>();
      return;
    }

    application.setPermissionDTOs(Optional.ofNullable(application).map(Application::getPermissions)
        .orElse(new ArrayList<>()).stream().filter(Objects::nonNull).distinct()
        .map(permission -> findSecurityMemberDtoByName(permission)).collect(Collectors.toList()));

    this.selectedApplicationPermissions = Optional.ofNullable(application).map(Application::getPermissionDTOs)
        .orElse(new ArrayList<>()).stream().map(SecurityMemberDTO::getName).collect(Collectors.toList());
  }

  private SecurityMemberDTO findSecurityMemberDtoByName(String permission) {
    return permission.startsWith("#") ? new SecurityMemberDTO(UserUtils.findUserByUsername(permission))
        : new SecurityMemberDTO(RoleUtils.findRole(permission));
  }

  public List<SecurityMemberDTO> completeApplicationPermissions(String query) {
    if (this.selectedApplicationPermissions == null) {
      this.selectedApplicationPermissions = new ArrayList<>();
    }
    return RoleUtils.findRoles(null, selectedApplicationPermissions, query).stream()
        .map(SecurityMemberDTOMapper::mapFromRoleDTO).collect(Collectors.toList());
  }

  public void onSelectPermissionForApplication(SelectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    if (this.selectedApplicationPermissions == null) {
      this.selectedApplicationPermissions = new ArrayList<>();
    }
    this.selectedApplicationPermissions.add(selectedItem.getName());
  }

  public void onUnSelectPermissionForApplication(UnselectEvent<Object> event) {
    SecurityMemberDTO selectedItem = (SecurityMemberDTO) event.getObject();
    if (this.selectedApplicationPermissions == null) {
      this.selectedApplicationPermissions = new ArrayList<>();
    }
    this.selectedApplicationPermissions.remove(selectedItem.getName());
  }

  public void updateApplicationPermissions(Application application) {
    if (application == null) {
      return;
    }

    List<SecurityMemberDTO> responsibles = application.getPermissionDTOs();
    List<String> permissions = new ArrayList<>();
    String displayedPermission = "";

    if (CollectionUtils.isNotEmpty(responsibles)) {
      Collection<SecurityMemberDTO> distinctPermissionDTOs =
          responsibles.stream().collect(Collectors.toMap(SecurityMemberDTO::getMemberName, responsible -> responsible,
              (responsible1, responsible2) -> responsible1)).values();
      responsibles.clear();
      responsibles.addAll(distinctPermissionDTOs);
      displayedPermission =
          responsibles.stream().map(SecurityMemberDTO::getDisplayName).collect(Collectors.joining(", "));
      permissions = responsibles.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    }
    application.setDisplayedPermission(displayedPermission);
    application.setPermissions(permissions);

    this.selectedApplicationPermissions = new ArrayList<>(permissions);
  }

  public void addNewApplication() {
    this.selectedApp = new Application();
    this.selectedApp.setPermissions(new ArrayList<>());
    this.selectedApp.setPermissionDTOs(new ArrayList<>());

    if (applicationList != null && !applicationList.isEmpty()) {
      this.selectedApp.setMenuOrdinal(applicationList.get(applicationList.size() - 1).getMenuOrdinal() + 1);
    } else {
      this.selectedApp.setMenuOrdinal(0);
    }

    this.isAddMode = true;
    this.dialogTitle = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/addNewApplication");

    this.displayNameInCurrentLanguage = "";
    this.supportedLanguages = new ArrayList<>();
    initApplicationLanguages();

    initApplicationPermissions(this.selectedApp);
  }

  public void editApplication(Application application) {
    this.selectedApp = application;
    this.isAddMode = false;
    this.dialogTitle = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/editApplication");

    try {
      Locale currentLocale = new Locales().getCurrentLocale();
      DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(application.getDisplayName(), currentLocale);
      this.displayNameInCurrentLanguage = displayNameAdaptor.getDisplayNameAsString();

      if (this.displayNameInCurrentLanguage == null || this.displayNameInCurrentLanguage.trim().isEmpty()) {
        this.displayNameInCurrentLanguage = application.getDisplayName();
      }

      if (this.displayNameInCurrentLanguage == null || this.displayNameInCurrentLanguage.trim().isEmpty()) {
        this.displayNameInCurrentLanguage = application.getName();
      }
    } catch (Exception e) {
      this.displayNameInCurrentLanguage =
          application.getDisplayName() != null ? application.getDisplayName() : application.getName();
    }

    initApplicationLanguages();
    initSupportedLanguagesForApplication();

    initApplicationPermissions(this.selectedApp);
  }

  public void saveApplication() {
    if (this.selectedApp == null) {
      return;
    }

    try {
      if (this.displayNameInCurrentLanguage != null && !this.displayNameInCurrentLanguage.trim().isEmpty()) {
        this.selectedApp.setName(this.displayNameInCurrentLanguage.trim());
      } else if (this.selectedApp.getDisplayName() != null) {
        this.selectedApp.setName(this.selectedApp.getDisplayName());
      }

      updateApplicationPermissions(this.selectedApp);

      prepareDisplayNameForSave();

      if (isDuplicateApplication()) {
        FacesMessage errorMessage = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
            Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/duplicatePortalAppMsg"), null);
        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
        FacesContext.getCurrentInstance().validationFailed();
        return;
      }

      RegisteredApplicationService applicationService = RegisteredApplicationService.getInstance();
      this.selectedApp = applicationService.save(this.selectedApp);

      if (this.isAddMode) {
        if (this.applicationList == null) {
          this.applicationList = new ArrayList<>();
        }
        this.applicationList.add(this.selectedApp);
      } else {
        if (this.applicationList != null) {
          for (int i = 0; i < this.applicationList.size(); i++) {
            Application app = this.applicationList.get(i);
            if (app.getId().equals(this.selectedApp.getId())) {
              this.applicationList.set(i, this.selectedApp);
              break;
            }
          }
        }
      }

      Collections.sort(this.applicationList, new ApplicationIndexAscendingComparator());

      FacesMessage message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_INFO,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/applicationSaved"), null);
      FacesContext.getCurrentInstance().addMessage(null, message);

    } catch (Exception e) {
      FacesMessage errorMessage = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
          "Error saving application: " + e.getMessage(), null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
      FacesContext.getCurrentInstance().validationFailed();
    }
  }

  private boolean isDuplicateApplication() {
    if (this.selectedApp == null || this.selectedApp.getName() == null || this.applicationList == null) {
      return false;
    }

    String currentName = this.selectedApp.getName().trim();
    if (currentName.isEmpty()) {
      return false;
    }

    for (Application app : this.applicationList) {
      if (app.getName() != null && app.getName().trim().equals(currentName)) {
        if (this.isAddMode) {
          return true;
        } else {
          if (!this.selectedApp.getId().equals(app.getId())) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public void deleteApplication(Application application) {
    if (application == null || this.applicationList == null) {
      return;
    }

    try {
      RegisteredApplicationService applicationService = RegisteredApplicationService.getInstance();
      applicationService.delete(application.getId());

      this.applicationList.remove(application);

      FacesMessage message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_INFO,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/applicationDeleted"), null);
      FacesContext.getCurrentInstance().addMessage(null, message);

    } catch (Exception e) {
      FacesMessage errorMessage = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
          "Error deleting application: " + e.getMessage(), null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
    }
  }

  public void loadApplications() {
    RegisteredApplicationService applicationService = RegisteredApplicationService.getInstance();
    this.applicationList = applicationService.findAll();

    if (this.applicationList != null) {
      for (Application app : this.applicationList) {
        if (app.getName() == null || app.getName().trim().isEmpty()) {
          try {
            // Try to get the display name in the current locale
            String displayName = ApplicationMultiLanguage.getDisplayNameInCurrentLocale(app);
            if (displayName != null && !displayName.trim().isEmpty()) {
              app.setName(displayName.trim());
            } else if (app.getDisplayName() != null) {
              app.setName(app.getDisplayName());
            }
          } catch (Exception e) {
            if (app.getDisplayName() != null) {
              app.setName(app.getDisplayName());
            }
          }
        }
      }
    }

    Collections.sort(this.applicationList, new ApplicationIndexAscendingComparator());
  }

  public List<String> getSelectedApplicationPermissions() {
    return selectedApplicationPermissions;
  }

  public void setSelectedApplicationPermissions(List<String> selectedApplicationPermissions) {
    this.selectedApplicationPermissions = selectedApplicationPermissions;
  }

  // Getters and setters
  public List<Application> getApplicationList() {
    if (applicationList == null) {
      loadApplications();
    }
    return applicationList;
  }

  public void setApplicationList(List<Application> applicationList) {
    this.applicationList = applicationList;
  }

  public Application getSelectedApp() {
    return selectedApp;
  }

  public void setSelectedApp(Application selectedApp) {
    this.selectedApp = selectedApp;
  }

  public String getDialogTitle() {
    return dialogTitle;
  }

  public void setDialogTitle(String dialogTitle) {
    this.dialogTitle = dialogTitle;
  }

  public boolean isAddMode() {
    return isAddMode;
  }

  public void setAddMode(boolean isAddMode) {
    this.isAddMode = isAddMode;
  }

  public String getAppNameInCurrentLocale(Application application) {
    try {
      return ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application);
    } catch (Exception e) {
      return application.getDisplayName();
    }
  }

  public List<DisplayName> getSupportedLanguages() {
    if (supportedLanguages == null) {
      LanguageService languageService = LanguageService.getInstance();
      List<String> languages = languageService.getIvyLanguageOfUser().getSupportedLanguages();

      supportedLanguages = new ArrayList<>();
      for (String language : languages) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        displayName.setValue(""); // Will be set when editing
        supportedLanguages.add(displayName);
      }
    }
    return supportedLanguages;
  }

  public String getDisplayNameInCurrentLanguage() {
    return displayNameInCurrentLanguage;
  }

  public void setDisplayNameInCurrentLanguage(String displayNameInCurrentLanguage) {
    this.displayNameInCurrentLanguage = displayNameInCurrentLanguage;

    if (this.selectedApp != null && displayNameInCurrentLanguage != null
        && !displayNameInCurrentLanguage.trim().isEmpty()) {
      this.selectedApp.setName(displayNameInCurrentLanguage.trim());

      try {
        Locale currentLocale = new Locales().getCurrentLocale();
        DisplayNameAdaptor displayNameAdaptor =
            new DisplayNameAdaptor(this.selectedApp.getDisplayName(), currentLocale);
        displayNameAdaptor.add(currentLocale, displayNameInCurrentLanguage.trim());
        this.selectedApp.setDisplayName(displayNameAdaptor.toJson());
      } catch (Exception e) {
        this.selectedApp.setDisplayName(displayNameInCurrentLanguage);
      }
    }
  }

  public List<String> getLanguages() {
    if (languages == null) {
      languages = getSupportedLanguages().stream().map(displayName -> displayName.getLocale().getLanguage())
          .collect(Collectors.toList());
    }
    return languages;
  }

  public void loadApplicationMultiLanguageNames(Application application) {
    if (application != null) {
      try {
        Locale currentLocale = new Locales().getCurrentLocale();
        DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(application.getDisplayName(), currentLocale);
        Map<String, String> displayNames = displayNameAdaptor.getDisplayNameAsMap();

        ApplicationMultiLanguageNameLoader loader =
            new ApplicationMultiLanguageNameLoader().currentDisplayName(displayNameAdaptor.getDisplayNameAsString())
                .currentDisplayNames(displayNames).supportedLanguages(getLanguageList());

        this.supportedLanguages = loader.load();
      } catch (Exception e) {
        // Fallback to empty list
        this.supportedLanguages = new ArrayList<>();
      }
    }
  }

  public void saveApplicationMultiLanguageNames(Application application) {
    if (application != null) {
      prepareDisplayNameForSave();
    }
  }

  public void updateApplicationDisplayName(Application application, String newDisplayName) {
    if (application != null) {
      application.setDisplayName(newDisplayName);
      try {
        Locale currentLocale = new Locales().getCurrentLocale();
        DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(application.getDisplayName(), currentLocale);
        displayNameAdaptor.add(currentLocale, newDisplayName);
        application.setDisplayName(displayNameAdaptor.toJson());
      } catch (Exception e) {
        application.setDisplayName(newDisplayName);
      }
    }
  }

  public String getApplicationDisplayNameInLanguage(Application application, String language) {
    if (application == null || language == null) {
      return null;
    }

    try {
      Locale currentLocale = new Locales().getCurrentLocale();
      DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(application.getDisplayName(), currentLocale);
      Map<String, String> displayNames = displayNameAdaptor.getDisplayNameAsMap();
      return displayNames.get(language);
    } catch (Exception e) {
      return application.getDisplayName();
    }
  }

  public void setApplicationDisplayNameInLanguage(Application application, String language, String displayName) {
    if (application != null && language != null) {
      try {
        Locale currentLocale = new Locales().getCurrentLocale();
        DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(application.getDisplayName(), currentLocale);
        displayNameAdaptor.add(Locale.forLanguageTag(language), displayName);
        application.setDisplayName(displayNameAdaptor.toJson());
      } catch (Exception e) {
        application.setDisplayName(displayName);
      }
    }
  }

  public void removeApplicationDisplayNameInLanguage(Application application, String language) {
    if (application != null && language != null) {
      try {
        Locale currentLocale = new Locales().getCurrentLocale();
        DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(application.getDisplayName(), currentLocale);
        Map<String, String> displayNames = displayNameAdaptor.getDisplayNameAsMap();
        displayNames.remove(language);
        DisplayNameConvertor convertor = new DisplayNameConvertor();
        for (Map.Entry<String, String> entry : displayNames.entrySet()) {
          convertor.add(Locale.forLanguageTag(entry.getKey()), entry.getValue());
        }
        application.setDisplayName(convertor.toJson());
      } catch (Exception e) {
      }
    }
  }

  public void addApplicationDisplayNameInLanguage(Application application, String language, String displayName) {
    if (application != null && language != null) {
      try {
        Locale currentLocale = new Locales().getCurrentLocale();
        DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(application.getDisplayName(), currentLocale);
        displayNameAdaptor.add(Locale.forLanguageTag(language), displayName);
        application.setDisplayName(displayNameAdaptor.toJson());
      } catch (Exception e) {
        application.setDisplayName(displayName);
      }
    }
  }

  public void initApplicationLanguages() {
    LanguageService languageService = LanguageService.getInstance();
    this.languages = languageService.getIvyLanguageOfUser().getSupportedLanguages();

    if (this.displayNameInCurrentLanguage == null) {
      this.displayNameInCurrentLanguage = "";
    }
  }

  public void processLanguage() {
    if (this.selectedApp == null) {
      return;
    }

    try {
      Locale currentLocale = new Locales().getCurrentLocale();
      DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(this.selectedApp.getDisplayName(), currentLocale);
      Map<String, String> displayNameMap = displayNameAdaptor.getDisplayNameAsMap();

      if (this.supportedLanguages == null || this.supportedLanguages.isEmpty()) {
        if (this.languages != null && !this.languages.isEmpty()) {
          for (String language : this.languages) {
            String existingName = displayNameMap.get(language);
            if (existingName == null || existingName.isEmpty()) {
              displayNameAdaptor.add(Locale.forLanguageTag(language), this.displayNameInCurrentLanguage);
            }
          }
        } else {
          displayNameAdaptor.add(currentLocale, this.displayNameInCurrentLanguage);
        }
      } else {
        for (DisplayName displayName : this.supportedLanguages) {
          if (displayName.getValue() == null || displayName.getValue().isEmpty()) {
            displayName.setValue(this.displayNameInCurrentLanguage);
          }
          displayNameAdaptor.add(displayName.getLocale(), displayName.getValue());
        }
      }

      displayNameAdaptor.add(currentLocale, this.displayNameInCurrentLanguage);
      this.selectedApp.setDisplayName(displayNameAdaptor.toJson());

    } catch (Exception e) {
      this.selectedApp.setDisplayName(this.displayNameInCurrentLanguage);
    }
  }

  public List<String> getLanguageList() {
    if (this.languages == null) {
      initApplicationLanguages();
    }
    return this.languages;
  }

  public void initSupportedLanguagesForApplication() {
    if (this.selectedApp == null) {
      return;
    }

    try {
      if (this.isAddMode) {
        this.supportedLanguages =
            new ApplicationMultiLanguageNameLoader().currentDisplayName(this.displayNameInCurrentLanguage)
                .currentDisplayNames(this.supportedLanguages != null ? this.supportedLanguages : new ArrayList<>())
                .supportedLanguages(getLanguageList()).load();
      } else {
        Locale currentLocale = new Locales().getCurrentLocale();
        DisplayNameAdaptor displayNameAdaptor =
            new DisplayNameAdaptor(this.selectedApp.getDisplayName(), currentLocale);
        Map<String, String> displayNames = displayNameAdaptor.getDisplayNameAsMap();

        this.supportedLanguages =
            new ApplicationMultiLanguageNameLoader().currentDisplayName(this.displayNameInCurrentLanguage)
                .currentDisplayNames(displayNames).supportedLanguages(getLanguageList()).load();
      }
    } catch (Exception e) {
      // Fallback to empty list
      this.supportedLanguages = new ArrayList<>();
    }
  }

  public void prepareDisplayNameForSave() {
    if (this.selectedApp == null) {
      return;
    }

    try {
      DisplayNameConvertor convertor = new DisplayNameConvertor();

      if (this.supportedLanguages == null || this.supportedLanguages.isEmpty()) {
        if (this.languages != null && !this.languages.isEmpty()) {
          for (String language : this.languages) {
            convertor.add(Locale.forLanguageTag(language), this.displayNameInCurrentLanguage);
          }
        } else {
          convertor.add(new Locales().getCurrentLocale(), this.displayNameInCurrentLanguage);
        }
      } else {
        for (DisplayName displayName : this.supportedLanguages) {
          if (displayName.getValue() == null || displayName.getValue().isEmpty()) {
            displayName.setValue(this.displayNameInCurrentLanguage);
          }
          convertor.add(displayName.getLocale(), displayName.getValue());
        }
      }

      this.selectedApp.setDisplayName(convertor.toJson());

    } catch (Exception e) {
      // Fallback to simple display name
      this.selectedApp.setDisplayName(this.displayNameInCurrentLanguage);
    }
  }

  // Required methods for MultiLanguageConfiguration component
  private String translatedText = "";
  private String warningText = "";

  public boolean isRequiredField(DisplayName displayName) {
    String currentLanguage = UserUtils.getUserLanguage();
    String displayLanguage = displayName.getLocale().getLanguage();
    return currentLanguage.equals(displayLanguage);
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

  public void setTranslatedText(String translatedText) {
    this.translatedText = translatedText;
  }

  public String getWarningText() {
    return warningText;
  }

  public void setWarningText(String warningText) {
    this.warningText = warningText;
  }

  public void translate(DisplayName title) {
    translatedText = Strings.EMPTY;
    warningText = Strings.EMPTY;

    String currentLanguage = UserUtils.getUserLanguage();
    if (!title.getLocale().getLanguage().equals(currentLanguage)) {
      if (this.supportedLanguages != null && !this.supportedLanguages.isEmpty()) {
        Optional<DisplayName> optional = this.supportedLanguages.stream()
            .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
        if (optional.isPresent()) {
          try {
            translatedText = DeepLTranslationService.getInstance().translate(optional.get().getValue(),
                optional.get().getLocale(), title.getLocale());
          } catch (Exception e) {
            warningText =
                Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/SomeThingWentWrong");
            Ivy.log().error("DeepL Translation Service error: ", e.getMessage());
          }
        }
      }
    }
  }

  public void applyTranslatedText(DisplayName displayName) {
    if (StringUtils.isNotBlank(translatedText)) {
      displayName.setValue(translatedText);
      translatedText = "";
    }
  }

  public void updateDisplayNameByLocale() {
    if (this.selectedApp != null && this.displayNameInCurrentLanguage != null) {
      String currentName = this.displayNameInCurrentLanguage;
      initSupportedLanguagesForApplication();
      DisplayNameConvertor.setValue(currentName, this.supportedLanguages);
    }
  }

  public List<DisplayName> getTitles() {
    return this.supportedLanguages;
  }

  public void updateCurrentLanguage() {
    if (this.supportedLanguages != null && !this.supportedLanguages.isEmpty()) {
      String currentLanguage = UserUtils.getUserLanguage();
      Optional<DisplayName> currentDisplayName = this.supportedLanguages.stream()
          .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
      if (currentDisplayName.isPresent() && StringUtils.isNotBlank(currentDisplayName.get().getValue())) {
        this.displayNameInCurrentLanguage = currentDisplayName.get().getValue();
      }
    }
  }
}

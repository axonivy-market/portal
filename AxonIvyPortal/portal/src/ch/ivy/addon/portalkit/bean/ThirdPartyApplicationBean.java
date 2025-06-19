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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.FacesMessageUtils;
import com.axonivy.portal.components.util.Locales;
import com.axonivy.portal.components.util.RoleUtils;
import com.axonivy.portal.service.DeepLTranslationService;

import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.loader.ApplicationMultiLanguageNameLoader;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.DisplayNameAdaptor;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ThirdPartyApplicationBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private List<String> selectedApplicationPermissions;
  private List<Application> applicationList;
  private Application selectedApp;
  private String dialogTitle;
  private boolean isAddMode;

  // Multi-language support fields
  private List<DisplayName> supportedLanguages;
  private String displayNameInCurrentLanguage;
  private List<String> languages;

  // Translation fields
  private String translatedText = "";
  private String warningText = "";

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
      updateApplicationPermissions(this.selectedApp);
      prepareDisplayNameForSave();

      syncApplicationNameWithCurrentLocaleDisplayName();

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
        try {
          String displayName = ApplicationMultiLanguage.getDisplayNameInCurrentLocale(app);
          if (displayName != null && !displayName.trim().isEmpty()) {
            app.setName(displayName.trim());
          } else if (app.getName() == null || app.getName().trim().isEmpty()) {
            if (app.getDisplayName() != null) {
              app.setName(app.getDisplayName());
            }
          }
        } catch (Exception e) {
          if (app.getName() == null || app.getName().trim().isEmpty()) {
            if (app.getDisplayName() != null) {
              app.setName(app.getDisplayName());
            }
          }
        }
        // Initialize permissions for each application
        initApplicationPermissions(app);
        updateApplicationPermissions(app);
      }
    }
    Collections.sort(this.applicationList, new ApplicationIndexAscendingComparator());
  }

  public String getAppNameInCurrentLocale(Application application) {
    try {
      return ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application);
    } catch (Exception e) {
      return application.getDisplayName();
    }
  }

  public void initApplicationLanguages() {
    LanguageService languageService = LanguageService.getInstance();
    this.languages = languageService.getIvyLanguageOfUser().getSupportedLanguages();

    if (this.displayNameInCurrentLanguage == null) {
      this.displayNameInCurrentLanguage = "";
    }
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
      this.supportedLanguages = new ArrayList<>();
    }
  }

  public void prepareDisplayNameForSave() {
    if (this.selectedApp == null) {
      return;
    }

    try {
      DisplayNameConvertor convertor = new DisplayNameConvertor();
      Locale currentLocale = new Locales().getCurrentLocale();
      String currentLanguage = currentLocale.getLanguage();
      convertor.add(currentLocale, this.displayNameInCurrentLanguage != null ? this.displayNameInCurrentLanguage : "");

      if (this.supportedLanguages != null && !this.supportedLanguages.isEmpty()) {
        for (DisplayName displayName : this.supportedLanguages) {
          if (!displayName.getLocale().getLanguage().equals(currentLanguage)) {
            String value = displayName.getValue();
            if (value == null || value.isEmpty()) {
              value = this.displayNameInCurrentLanguage != null ? this.displayNameInCurrentLanguage : "";
            }
            convertor.add(displayName.getLocale(), value);
          }
        }
      } else {
        try {
          DisplayNameAdaptor existing = new DisplayNameAdaptor(this.selectedApp.getDisplayName(), currentLocale);
          Map<String, String> existingNames = existing.getDisplayNameAsMap();

          for (Map.Entry<String, String> entry : existingNames.entrySet()) {
            if (!entry.getKey().equals(currentLanguage)) {
              convertor.add(Locale.forLanguageTag(entry.getKey()), entry.getValue());
            }
          }
        } catch (Exception e) {
          if (this.languages != null && !this.languages.isEmpty()) {
            for (String language : this.languages) {
              if (!language.equals(currentLanguage)) {
                convertor.add(Locale.forLanguageTag(language),
                    this.displayNameInCurrentLanguage != null ? this.displayNameInCurrentLanguage : "");
              }
            }
          }
        }
      }

      String newJson = convertor.toJson();
      this.selectedApp.setDisplayName(newJson);
    } catch (Exception e) {
      this.selectedApp
          .setDisplayName(this.displayNameInCurrentLanguage != null ? this.displayNameInCurrentLanguage : "");
    }
  }

  private void syncApplicationNameWithCurrentLocaleDisplayName() {
    if (this.selectedApp == null) {
      return;
    }
    try {
      String localeDisplayName = ApplicationMultiLanguage.getDisplayNameInCurrentLocale(this.selectedApp);

      if (localeDisplayName != null && !localeDisplayName.trim().isEmpty()) {
        this.selectedApp.setName(localeDisplayName.trim());
      } else if (this.displayNameInCurrentLanguage != null && !this.displayNameInCurrentLanguage.trim().isEmpty()) {
        this.selectedApp.setName(this.displayNameInCurrentLanguage.trim());
      } else if (this.selectedApp.getDisplayName() != null) {
        this.selectedApp.setName(this.selectedApp.getDisplayName());
      }
    } catch (Exception e) {
      if (this.displayNameInCurrentLanguage != null && !this.displayNameInCurrentLanguage.trim().isEmpty()) {
        this.selectedApp.setName(this.displayNameInCurrentLanguage.trim());
      } else if (this.selectedApp.getDisplayName() != null) {
        this.selectedApp.setName(this.selectedApp.getDisplayName());
      }
    }
  }

  public void updateDisplayNameByLocale() {
    if (this.selectedApp != null && this.displayNameInCurrentLanguage != null) {
      String currentName = this.displayNameInCurrentLanguage;
      initSupportedLanguagesForApplication();
      DisplayNameConvertor.setValue(currentName, this.supportedLanguages);
    }
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

  // Translation methods
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

  public void onApplicationReorder(List<Application> reorderedApplications, Application selectedApp) {
    if (reorderedApplications == null || reorderedApplications.isEmpty()) {
      return;
    }

    try {
      RegisteredApplicationService applicationService = RegisteredApplicationService.getInstance();

      for (int i = 0; i < reorderedApplications.size(); i++) {
        reorderedApplications.get(i).setMenuOrdinal(i);
        applicationService.save(reorderedApplications.get(i));
      }

      // Update the local application list
      this.applicationList = reorderedApplications;

      FacesMessage message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_INFO,
          "Application order has been updated successfully.", null);
      FacesContext.getCurrentInstance().addMessage(null, message);

    } catch (Exception e) {
      FacesMessage errorMessage = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
          "Error updating application order: " + e.getMessage(), null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
    }
  }

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

  public List<String> getLanguageList() {
    if (this.languages == null) {
      initApplicationLanguages();
    }
    return this.languages;
  }

  public List<DisplayName> getSupportedLanguages() {
    if (supportedLanguages == null) {
      LanguageService languageService = LanguageService.getInstance();
      List<String> languages = languageService.getIvyLanguageOfUser().getSupportedLanguages();

      supportedLanguages = new ArrayList<>();
      for (String language : languages) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        displayName.setValue("");
        supportedLanguages.add(displayName);
      }
    }
    return supportedLanguages;
  }

  public List<DisplayName> getTitles() {
    return this.supportedLanguages;
  }

  public List<String> getSelectedApplicationPermissions() {
    return selectedApplicationPermissions;
  }

  public void setSelectedApplicationPermissions(List<String> selectedApplicationPermissions) {
    this.selectedApplicationPermissions = selectedApplicationPermissions;
  }

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

  public String getDisplayNameInCurrentLanguage() {
    return displayNameInCurrentLanguage;
  }

  public void setDisplayNameInCurrentLanguage(String displayNameInCurrentLanguage) {
    this.displayNameInCurrentLanguage = displayNameInCurrentLanguage;
    if (this.selectedApp != null && displayNameInCurrentLanguage != null
        && !displayNameInCurrentLanguage.trim().isEmpty()) {
      this.selectedApp.setName(displayNameInCurrentLanguage.trim());

      if (this.supportedLanguages != null) {
        try {
          Locale currentLocale = new Locales().getCurrentLocale();
          String currentLanguage = currentLocale.getLanguage();

          for (DisplayName displayName : this.supportedLanguages) {
            if (displayName.getLocale().getLanguage().equals(currentLanguage)) {
              displayName.setValue(displayNameInCurrentLanguage.trim());
              break;
            }
          }
        } catch (Exception e) {
        }
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
}

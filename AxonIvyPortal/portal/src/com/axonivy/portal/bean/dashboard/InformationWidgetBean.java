package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import com.axonivy.portal.dto.InformationDTO;
import com.axonivy.portal.service.DeepLTranslationService;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class InformationWidgetBean implements Serializable {

  private static final long serialVersionUID = 6318866086036423536L;

  private final LanguageService languageService = LanguageService.getInstance();
  private final List<Locale> supportedLanguages = new ArrayList<>();
  private final List<InformationDTO> editingInformationList = new ArrayList<>();
  private Locale defaultLanguage;
  private int activeTabIndex = 0;

  public Locale getDefaultLanguage() {
    return defaultLanguage;
  }

  public void setDefaultLanguage(Locale defaultLanguage) {
    this.defaultLanguage = defaultLanguage;
  }

  public String getWarningText() {
    return warningText;
  }

  public void setWarningText(String warningText) {
    this.warningText = warningText;
  }

  public String getTranslatedText() {
    return translatedText;
  }

  public void setTranslatedText(String translatedText) {
    this.translatedText = translatedText;
  }

  public boolean isCanManageInformation() {
    return canManageInformation;
  }

  public void setCanManageInformation(boolean canManageInformation) {
    this.canManageInformation = canManageInformation;
  }

  public LanguageService getLanguageService() {
    return languageService;
  }

  public List<Locale> getSupportedLanguages() {
    return supportedLanguages;
  }

  public List<InformationDTO> getEditingInformationList() {
    return editingInformationList;
  }

  private String warningText = "";
  private String translatedText = "";
  private boolean canManageInformation;

  @PostConstruct
  public void init() {
    canManageInformation = PermissionUtils.hasPortalPermission(PortalPermission.INFORMATION_WIGET_MANAGEMENT);
    loadSupportedLanguages();
    initializeEditingList();
  }

  private void loadSupportedLanguages() {
    supportedLanguages.addAll(languageService.getContentLocales().stream()
        .filter(distinctByLanguageTag(Locale::toLanguageTag)).collect(Collectors.toList()));
    defaultLanguage = languageService.getDefaultLanguage();
  }

  private void initializeEditingList() {
    supportedLanguages.forEach(locale -> editingInformationList.add(new InformationDTO(locale)));
  }

  private static <T> Predicate<T> distinctByLanguageTag(Function<? super T, ?> keyExtractor) {
    Set<Object> keySet = ConcurrentHashMap.newKeySet();
    return t -> keySet.add(keyExtractor.apply(t));
  }

  private Optional<InformationDTO> getDefaultInfo() {
    return editingInformationList.stream()
        .filter(info -> defaultLanguage.getLanguage().equals(info.getLocale().getLanguage())).findFirst();
  }

  public void translateTitle(Locale targetLanguage) {
    translatedText = "";
    getDefaultInfo().ifPresent(defaultInfo -> {
      String translatedTitle = translate(defaultInfo.getName(), targetLanguage);
      editingInformationList.stream().filter(info -> info.getLocale().equals(targetLanguage)).findFirst()
          .ifPresent(info -> info.setName(translatedTitle));
      translatedText = translatedTitle;
    });
  }

  public void translateContent(Locale targetLanguage) {
    getDefaultInfo().ifPresent(defaultInfo -> {
      String translatedContent = translate(defaultInfo.getContent(), targetLanguage);
      editingInformationList.stream().filter(info -> info.getLocale().equals(targetLanguage)).findFirst()
          .ifPresent(info -> info.setContent(translatedContent));
    });
  }

  private String translate(String text, Locale targetLanguage) {
    warningText = "";
    try {
      return DeepLTranslationService.getInstance().translate(text, defaultLanguage, targetLanguage);
    } catch (Exception e) {
      warningText = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/SomeThingWentWrong");
      Ivy.log().error("Translation Error: " + e.getMessage(), e);
      return "";
    }
  }

  public void resetTranslation() {
    translatedText = "";
  }

  public int activeTabIndex() {
    return Math.max(supportedLanguages.indexOf(defaultLanguage), 0);
  }

  public int getActiveTabIndex() {
    return activeTabIndex;
  }

  public void onTabChange(TabChangeEvent<TabView> event) {
    String selectedLanguageTag = event.getTab().getTitle();
    supportedLanguages.stream().filter(locale -> locale.getDisplayLanguage().equalsIgnoreCase(selectedLanguageTag))
        .findFirst().ifPresent(locale -> activeTabIndex = supportedLanguages.indexOf(locale));
  }
}


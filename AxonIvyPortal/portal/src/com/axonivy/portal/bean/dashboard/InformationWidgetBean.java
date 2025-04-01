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

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import com.axonivy.portal.dto.InformationDTO;
import com.axonivy.portal.service.DeepLTranslationService;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class InformationWidgetBean implements Serializable{

  private static final long serialVersionUID = 6318866086036423536L;

  private List<Locale> supportedLanguages;
  private Locale defaultLanguage;
  private InformationDTO informationDTO;
  private boolean canManageInformation;
  private List<InformationDTO> editingInformationList;
  private String warningText;
  private String translatedText;

  @PostConstruct
  public void init() {
    canManageInformation = PermissionUtils.hasPortalPermission(PortalPermission.INFORMATION_WIGET_MANAGEMENT);
    initInformationManagement();
    editingInformationList = new ArrayList<>();
    for (var language : supportedLanguages) {
      var info = new InformationDTO();
      info.setLocale(language);
      editingInformationList.add(info);
    }
    Ivy.log().error("haha");
  }

  private void initInformationManagement() {
    if (CollectionUtils.isEmpty(supportedLanguages)) {
      supportedLanguages = LanguageService.getInstance().getContentLocales().stream()
          .filter(distinctBylanguageTag(Locale::toLanguageTag)).collect(Collectors.toList());
      defaultLanguage = LanguageService.getInstance().getDefaultLanguage();
    }
  }

  public static <T> Predicate<T> distinctBylanguageTag(Function<? super T, ?> keyExtractor) {
    Set<Object> keySet = ConcurrentHashMap.newKeySet();
    return t -> keySet.add(keyExtractor.apply(t));
  }

  public List<Locale> getSupportedLanguages() {
    return supportedLanguages;
  }

  public void setSupportedLanguages(List<Locale> supportedLanguages) {
    this.supportedLanguages = supportedLanguages;
  }

  public Locale getDefaultLanguage() {
    return defaultLanguage;
  }

  public void setDefaultLanguage(Locale defaultLanguage) {
    this.defaultLanguage = defaultLanguage;
  }

  public InformationDTO getInformationDTO() {
    return informationDTO;
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

  public void setInformationDTO(InformationDTO informationDTO) {
    this.informationDTO = informationDTO;
  }

  public boolean isCanManageInformation() {
    return canManageInformation;
  }

  public void setCanManageInformation(boolean canManageInformation) {
    this.canManageInformation = canManageInformation;
  }

  public List<InformationDTO> getEditingInformationList() {
    return editingInformationList;
  }

  public void setEditingInformationList(List<InformationDTO> editingInformationList) {
    this.editingInformationList = editingInformationList;
  }

  public int getActiveTabIndex() {
    int activeIndex = 0;
    if (CollectionUtils.isNotEmpty(supportedLanguages)) {
      activeIndex = supportedLanguages.indexOf(defaultLanguage);
    }
    return activeIndex < 0 ? 0 : activeIndex;
  }

  public void translateTitle(Locale language) {
    translatedText = "";
    Optional<InformationDTO> optionalDefaultInfos = getDefaultiInfos();
    if (optionalDefaultInfos.isPresent()) {
      String translatedTitle = translate(optionalDefaultInfos.get().getName(), language);
      Optional<InformationDTO> optionalCurrentNews = editingInformationList.stream()
          .filter(news -> news.getLocale().getLanguage().equals(language.getLanguage())).findFirst();
      if (optionalCurrentNews.isPresent()) {
        optionalCurrentNews.get().setName(translatedTitle);
      }
      translatedText = translatedTitle;
    }

  }

  private Optional<InformationDTO> getDefaultiInfos() {
    return editingInformationList.stream()
        .filter(lang -> defaultLanguage.getLanguage().equals(lang.getLocale().getLanguage())).findFirst();
  }

  public String translate(String text, Locale target) {
    String translatedText = Strings.EMPTY;
    warningText = Strings.EMPTY;
    try {
      translatedText = DeepLTranslationService.getInstance().translate(text, defaultLanguage, target);
    } catch (Exception e) {
      warningText = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/SomeThingWentWrong");
      Ivy.log().error("DeepL Translation Service error: ", e.getMessage());
    }
    return translatedText;

  }

  public void resetTranslation() {
    translatedText = "";
  }

  public void translateContent(Locale language) {
    Optional<InformationDTO> optionalDefaultInfos = getDefaultiInfos();
    if (optionalDefaultInfos.isPresent()) {
      String translatedContent = translate(optionalDefaultInfos.get().getContent(), language);
      Optional<InformationDTO> optionalCurrentNews = editingInformationList.stream()
          .filter(infos -> infos.getLocale().getLanguage().equals(language.getLanguage())).findFirst();
      if (optionalCurrentNews.isPresent()) {
        optionalCurrentNews.get().setContent(translatedContent);
      }
    }
  }
}

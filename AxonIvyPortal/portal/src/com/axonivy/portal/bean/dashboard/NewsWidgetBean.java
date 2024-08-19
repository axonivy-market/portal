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
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import com.axonivy.portal.dto.News;
import com.axonivy.portal.service.DeepLTranslationService;
import com.axonivy.portal.service.NewsService;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class NewsWidgetBean implements Serializable {

  private static final long serialVersionUID = 2567936194197940599L;

  private List<Locale> supportLanguages;
  private Locale defaultLanguage;
  private List<News> editingNewsList;
  private News selectedNews;
  private String manageNewsDialogTitle;
  private boolean canManageNews;
  private String translatedText;
  private String warningText;

  @PostConstruct
  public void init() {
    canManageNews = PermissionUtils.hasPortalPermission(PortalPermission.NEWS_MANAGEMENT);
  }

  public void preAddingNews() {
    initNewsManagement();
    manageNewsDialogTitle = Ivy.cms()
        .co("/Dialogs/com/axonivy/portal/dashboard/component/NewsWidgetConfiguration/AddNews");
    editingNewsList = new ArrayList<>();
    for (var language : supportLanguages) {
      var news = new News();
      news.setLocale(language);
      editingNewsList.add(news);
    }
  }

  public void publishNews() {
    if (FacesContext.getCurrentInstance().isValidationFailed() || CollectionUtils.isEmpty(editingNewsList)) {
      return;
    }
    NewsService.getInstance().saveOrUpdate(editingNewsList);
    editingNewsList.clear();
  }

  public void preEditingNews(News news) {
    initNewsManagement();
    manageNewsDialogTitle = Ivy.cms()
        .co("/Dialogs/com/axonivy/portal/dashboard/component/NewsWidgetConfiguration/EditNews");
    editingNewsList = NewsService.getInstance().findNewsBySupportedLocale(news.getId(), supportLanguages);
  }

  private void initNewsManagement() {
    if (CollectionUtils.isEmpty(supportLanguages)) {
      supportLanguages = LanguageService.getInstance().getContentLocales().stream()
          .filter(distinctBylanguageTag(Locale::toLanguageTag)).collect(Collectors.toList());
      defaultLanguage = LanguageService.getInstance().getDefaultLanguage();
    }
  }

  public static <T> Predicate<T> distinctBylanguageTag(Function<? super T, ?> keyExtractor) {
    Set<Object> keySet = ConcurrentHashMap.newKeySet();
    return t -> keySet.add(keyExtractor.apply(t));
  }

  public void deleteNews() {
    if (selectedNews == null) {
      return;
    }
    NewsService.getInstance().deleteNewsById(selectedNews.getId());
    selectedNews = null;
  }

  public int getActiveTabIndex() {
    int activeIndex = 0;
    if (CollectionUtils.isNotEmpty(supportLanguages)) {
      activeIndex = supportLanguages.indexOf(defaultLanguage);
    }
    return activeIndex < 0 ? 0 : activeIndex;
  }

  public void translateTitle(Locale language) {
    translatedText = "";
    Optional<News> optionalDefaultNews = getDefaultNews();
    if (optionalDefaultNews.isPresent()) {
      String translatedTitle = translate(optionalDefaultNews.get().getName(), language);
      Optional<News> optionalCurrentNews = editingNewsList.stream()
          .filter(news -> news.getLocale().getLanguage().equals(language.getLanguage())).findFirst();
      if (optionalCurrentNews.isPresent()) {
        optionalCurrentNews.get().setName(translatedTitle);
      }
      translatedText = translatedTitle;
    }

  }

  public void translateDescription(Locale language) {
    Optional<News> optionalDefaultNews = getDefaultNews();
    if (optionalDefaultNews.isPresent()) {
      String translatedDescription = translate(optionalDefaultNews.get().getDescription(), language);
      Optional<News> optionalCurrentNews = editingNewsList.stream()
          .filter(news -> news.getLocale().getLanguage().equals(language.getLanguage())).findFirst();
      if (optionalCurrentNews.isPresent()) {
        optionalCurrentNews.get().setDescription(translatedDescription);
      }
    }
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
  
  private Optional<News> getDefaultNews() {
    return editingNewsList.stream()
        .filter(lang -> defaultLanguage.getLanguage().equals(lang.getLocale().getLanguage())).findFirst();
  }

  public void resetTranslation() {
    translatedText = "";
  }

  public String getTranslatedText() {
    return translatedText;
  }

  public String getWarningText() {
    return warningText;
  }

  public List<Locale> getSupportLanguages() {
    return supportLanguages;
  }

  public List<News> getEditingNewsList() {
    return editingNewsList;
  }

  public void setEditingNewsList(List<News> editingNewsList) {
    this.editingNewsList = editingNewsList;
  }

  public Locale getDefaultLanguage() {
    return defaultLanguage;
  }

  public News getSelectedNews() {
    return selectedNews;
  }

  public void setSelectedNews(News selectedNews) {
    this.selectedNews = selectedNews;
  }

  public String getManageNewsDialogTitle() {
    return manageNewsDialogTitle;
  }

  public boolean isCanManageNews() {
    return canManageNews;
  }
}

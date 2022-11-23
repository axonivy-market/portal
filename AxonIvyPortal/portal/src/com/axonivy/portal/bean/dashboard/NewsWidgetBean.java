package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.News;
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

  @PostConstruct
  public void init() {
    supportLanguages = LanguageService.newInstance().getContentLocales();
    defaultLanguage = LanguageService.newInstance().getDefaultEmailLanguage();
    canManageNews = PermissionUtils.hasPortalPermission(PortalPermission.NEWS_MANAGEMENT);
  }

  public void preAddingNews() {
    manageNewsDialogTitle =
        Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/NewsWidgetConfiguration/AddNews");
    editingNewsList = new ArrayList<>();
    for (var language : supportLanguages) {
      var news = new News();
      news.setLocale(language);
      editingNewsList.add(news);
    }
  }

  public void publishNews() {
    if (FacesContext.getCurrentInstance().isValidationFailed()
        || CollectionUtils.isEmpty(editingNewsList)) {
      return;
    }
    NewsService.getInstance().saveOrUpdate(editingNewsList);
    editingNewsList.clear();
  }

  public void preEditingNews(News news) {
    manageNewsDialogTitle =
        Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/NewsWidgetConfiguration/EditNews");
    editingNewsList = NewsService.getInstance().findNewsBySupportedLocale(news.getId(), supportLanguages);
  }

  public void deleteNews() {
    if (selectedNews == null) {
      return;
    }
    NewsService.getInstance().deleteNewsById(selectedNews.getId());
    selectedNews = null;
  }

  public int getActiveTabIndex() {
    var indexOfDefaultLocale = supportLanguages.indexOf(defaultLanguage);
    return indexOfDefaultLocale < 0 ? 0 : indexOfDefaultLocale;
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

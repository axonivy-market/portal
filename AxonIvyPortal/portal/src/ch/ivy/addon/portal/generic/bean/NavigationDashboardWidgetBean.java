package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;

@SessionScoped
@ManagedBean
public class NavigationDashboardWidgetBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;
  private Boolean isNavigateToTargetDashboard;
  private Deque<String> pageHistory = new ArrayDeque<>();
  
  public void pushPage(String pageId) {
      if (!pageHistory.isEmpty() && pageHistory.peek().equals(pageId)) {
          return;
      }
      
      pageHistory.push(pageId);
  }
  
  public String getPreviousPage() {
      return pageHistory.peek();
  }
  
  private void removeLast() {
    pageHistory.pop();
  }
  
  public boolean hasHistory() {
      return !pageHistory.isEmpty();
  }
  
  public void clearHistory() {
      pageHistory.clear();
  }

  public void redirectToDashboard(NavigationDashboardWidget widget, Dashboard currentDashboard) throws IOException {
    pushPage(currentDashboard.getId());
    setIsNavigateToTargetDashboard(Boolean.TRUE);
    navigateToDashboard(widget.getTargetDashboardId());
    removeSelectedSubDashboardId();
  }
  
  public void backToPreviousDashboard() throws IOException {
    if (hasHistory()) {
      navigateToDashboard(getPreviousPage());
      removeLast();
      if (pageHistory.isEmpty()) {
        setIsNavigateToTargetDashboard(Boolean.FALSE);
      }
      removeSelectedSubDashboardId();
    }
    else {
      FacesContext.getCurrentInstance().getExternalContext().redirect(PortalNavigator.getPortalStartUrl());
    }
  }
  
  public void removeNavigationDashboardBackButton() {
    if (hasHistory()) {
      clearHistory();
      setIsNavigateToTargetDashboard(Boolean.FALSE);
    }
    if (DashboardUtils.isHiddenDashboard((String) Ivy.session().getAttribute(SessionAttribute.SELECTED_SUB_DASHBOARD_ID.name()))) {
      removeSelectedSubDashboardId();
    }
  }
  
  public void removeSelectedSubDashboardId() {
    SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_SUB_DASHBOARD_ID.name());
  }

  public void navigateToDashboard(String id) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(getDashboardUrlByDashboard(id));
  }

  private String getDashboardUrlByDashboard(String id) {
    return UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(id);
  }

  public Boolean getIsNavigateToTargetDashboard() {
    return this.isNavigateToTargetDashboard;
  }

  public void setIsNavigateToTargetDashboard(Boolean isNavigateToTargetDashboard) {
    this.isNavigateToTargetDashboard = isNavigateToTargetDashboard;
  }
  
  public Boolean isNotClickable(NavigationDashboardWidget widget, Boolean isReadOnlyMode) {
    if (!isReadOnlyMode) {
      return true;
    }
    return !DashboardUtils.collectDashboardIds().contains(widget.getTargetDashboardId());
  }
  
  public void updateButtonNameByLocale(NavigationDashboardWidget widget) {
    String currentButtonName = widget.getButtonName();
    initMultipleLanguagesForButtonWidgetName(widget, currentButtonName);
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = widget.getButtonNames().stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      optional.get().setValue(currentButtonName);
    }
  }
  
  private void initMultipleLanguagesForButtonWidgetName(NavigationDashboardWidget widget, String currentName) {
    Map<String, DisplayName> mapLanguage = getMapLanguages(widget);
    List<String> supportedLanguages = getSupportedLanguages();
    for (String language : supportedLanguages) {
      DisplayName localeLanguage = mapLanguage.get(language);
      if (localeLanguage == null) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        displayName.setValue(currentName);
        widget.getButtonNames().add(displayName);
      } else if (StringUtils.isBlank(localeLanguage.getValue())) {
        localeLanguage.setValue(currentName);
      }
    }
  }
  
  private Map<String, DisplayName> getMapLanguages(NavigationDashboardWidget widget) {
    List<DisplayName> languages = widget.getButtonNames();
    return languages.stream().collect(Collectors.toMap(o -> o.getLocale().toLanguageTag(), o -> o));
  }
  
  private List<String> getSupportedLanguages() {
    return LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
  }
}

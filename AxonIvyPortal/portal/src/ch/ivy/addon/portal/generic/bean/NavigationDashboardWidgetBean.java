package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.util.ImageUploadUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.Attrs;
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
  private NavigationDashboardWidget widget;
  
  protected static final String DEFAULT_IMAGE_CMS_URI = "/Images/process/MUGBEGIN";

  public void init() {
    widget = Attrs.currentContext().getAttribute("#{cc.attrs.widget}", NavigationDashboardWidget.class);
  }
  
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
    addSessionAttributeNavigateToDashboard();
    navigateToDashboard(widget.getTargetDashboardId());
    removeSelectedSubDashboardId();
  }
  
  public void backToPreviousDashboard() throws IOException {
    if (hasHistory()) {
      navigateToDashboard(getPreviousPage());
      removeLast();
      if (pageHistory.isEmpty()) {
        setIsNavigateToTargetDashboard(Boolean.FALSE);
        removeSessionAttributeNavigateToDashboard();
      }
      removeSelectedSubDashboardId();
      removeDrillDownDashboardIfExist();
    }
    else {
      FacesContext.getCurrentInstance().getExternalContext().redirect(PortalNavigator.getPortalStartUrl());
    }
  }

  private void removeDrillDownDashboardIfExist() {
    Ivy.session().removeAttribute(SessionAttribute.DRILL_DOWN_DASHBOARD.name());
  }
  
  private void addSessionAttributeNavigateToDashboard() {
    Ivy.session().setAttribute(SessionAttribute.NAVIGATE_TO_DASHBOARD.name(), isNavigateToTargetDashboard);
  }
  
  private void removeSessionAttributeNavigateToDashboard() {
    Ivy.session().removeAttribute(SessionAttribute.NAVIGATE_TO_DASHBOARD.name());
  }
  
  public void removeNavigationDashboardBackButton() {
    if (hasHistory()) {
      clearHistory();
      setIsNavigateToTargetDashboard(Boolean.FALSE);
      removeSessionAttributeNavigateToDashboard();
    }
    if (DashboardUtils.isHiddenDashboard((String) Ivy.session().getAttribute(SessionAttribute.SELECTED_SUB_DASHBOARD_ID.name()))) {
      removeSelectedSubDashboardId();
    }
    removeDrillDownDashboardIfExist();
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
  
  private void deduplicateButtonNames(NavigationDashboardWidget widget) {
    List<DisplayName> names = widget.getButtonNames();
    if (names != null) {
      Set<String> seen = new LinkedHashSet<>();
      names.removeIf(name -> name.getLocale() == null || !seen.add(name.getLocale().toLanguageTag()));
    }
  }

  private void initMultipleLanguagesForButtonWidgetName(NavigationDashboardWidget widget, String currentName) {
    deduplicateButtonNames(widget);
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
    return languages.stream()
        .filter(o -> o.getLocale() != null)
        // Use a merge function to keep the first DisplayName for each language and
        // ignore later duplicates,
        // preventing IllegalStateException from duplicate keys in Collectors.toMap.
        .collect(Collectors.toMap(o -> o.getLocale().toLanguageTag(), o -> o, (existing, replacement) -> existing));
  }
  
  private List<String> getSupportedLanguages() {
    return LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
  }
  
  public void navigateToDrillDownDashboard(String currentDashboardId) {
    try {
      Dashboard drillDownDashboard = retrieveDrillDownDashboard();
      if (drillDownDashboard != null && !StringUtils.isBlank(currentDashboardId)) {
        pushPage(currentDashboardId);
        setIsNavigateToTargetDashboard(Boolean.TRUE);
        navigateToDashboard(drillDownDashboard.getId());
      }
    } catch (Exception e) {
      Ivy.log().warn("Error when trying going to the drill down dashboard ", e);
      PortalNavigatorAPI.navigateToPortalHome();
    }
  }
  
  private Dashboard retrieveDrillDownDashboard() {
    Object drillDownDashboard = Ivy.session().getAttribute(SessionAttribute.DRILL_DOWN_DASHBOARD.name());
    return drillDownDashboard instanceof Dashboard ? (Dashboard) drillDownDashboard : null;
  }

  public NavigationDashboardWidget getWidget() {
    return widget;
  }

  public void setWidget(NavigationDashboardWidget widget) {
    this.widget = widget;
  }
  
  public String getDefaultImageLink() {
    return DEFAULT_IMAGE_CMS_URI;
  }

  public boolean isRenderDefaultImage(String imageLocation, String imageType) {
    return !ImageUploadUtils.isValidImageUrl(imageLocation, imageType);
  }
}

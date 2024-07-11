package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.service.DeepLTranslationService;

import ch.addon.portal.generic.menu.MenuView;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;

  protected List<Dashboard> dashboards;
  protected Dashboard selectedDashboard;
  private String selectedDashboardId;
  protected DashboardWidget widget;
  protected boolean isReadOnlyMode = true;
  private int currentDashboardIndex;
  private List<WidgetFilterModel> widgetFilters;
  private List<WidgetFilterModel> deleteFilters;
  private List<DashboardTemplate> dashboardTemplates;
  protected String translatedText;
  protected String warningText;
  protected String dashboardUrl;
  protected List<Dashboard> importedDashboards;
  private String clientStatisticApiUri;
  private String selectedDashboardName;

  @PostConstruct
  public void init() {
    currentDashboardIndex = 0;
    dashboards = collectDashboards();

    if (isReadOnlyMode) {
      MenuView menuView = (MenuView) ManagedBeans.get("menuView");
      menuView.updateDashboardCache(dashboards);
    }

    if (CollectionUtils.isNotEmpty(dashboards)) {
      selectedDashboardId = readDashboardFromSession();
      currentDashboardIndex = findIndexOfDashboardById(selectedDashboardId);
      selectedDashboard = dashboards.get(currentDashboardIndex);
      String selectedDashboardName = selectedDashboard.getTitles().stream()
          .filter(displayName -> displayName.getLocale().equals(Ivy.session().getContentLocale())).findFirst()
          .orElseGet(() -> selectedDashboard.getTitles().get(0)).getValue();
      setSelectedDashboardName(selectedDashboardName);
      initShareDashboardLink(selectedDashboard);
      // can not find dashboard by dashboard id session in view mode
      if (StringUtils.isBlank(selectedDashboardId)
          || (!selectedDashboardId.equalsIgnoreCase(selectedDashboard.getId()) && dashboards.size() > 1)) {
        storeDashboardInSession(selectedDashboard.getId());
      }
      if (isReadOnlyMode) {
        DashboardUtils.highlightDashboardMenuItem(selectedDashboard.getId());
      }
    }
    buildWidgetModels(selectedDashboard);

    buildClientStatisticApiUri();
  }

  private void buildClientStatisticApiUri() {
    this.clientStatisticApiUri = FacesContext.getCurrentInstance()
        .getExternalContext().getRequestContextPath() + "/api/statistics/data";
  }

  protected List<Dashboard> collectDashboards() {
    return DashboardUtils.collectDashboards();
  }

  public void loadDashboardTemplate() {
    this.dashboardTemplates = DashboardUtils.getDashboardTemplates();
  }

  protected List<Dashboard> jsonToDashboards(String dashboardJSON) {
    List<Dashboard> mappingDashboards = BusinessEntityConverter.jsonValueToEntities(dashboardJSON, Dashboard.class);
    for (Dashboard dashboard : mappingDashboards) {
      if (CollectionUtils.isEmpty(dashboard.getPermissions())) {
        ArrayList<String> defaultPermissions = new ArrayList<>();
        defaultPermissions.add(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
        dashboard.setPermissions(defaultPermissions);
      }
    }
    return mappingDashboards;
  }

  protected String readDashboardBySessionUser() {
    return currentUser().getProperty(PortalVariable.DASHBOARD.key);
  }

  protected void buildWidgetModels(Dashboard dashboard) {
    if (dashboard == null || CollectionUtils.isEmpty(dashboard.getWidgets())) {
      return;
    }
    for (var widget : dashboard.getWidgets()) {
      DashboardWidgetUtils.buildWidgetColumns(widget);
      if (!(widget instanceof SingleProcessDashboardWidget)) {
        WidgetFilterService.getInstance().applyUserFilterFromSession(widget);
      }
      DashboardWidgetUtils.removeStyleNewWidget(widget);
    }
  }

  protected List<Dashboard> getVisiblePublicDashboards() {
    String dashboardJson = Ivy.var().get(PortalVariable.DASHBOARD.key);
    List<Dashboard> visibleDashboards = DashboardUtils.getVisibleDashboards(dashboardJson);
    setDashboardAsPublic(visibleDashboards);
    return visibleDashboards;
  }

  private void setDashboardAsPublic(List<Dashboard> visibleDashboards) {
    visibleDashboards.stream().forEach(dashboard -> dashboard.setIsPublic(true));
  }

  public List<Dashboard> getDashboards() {
    return dashboards;
  }

  public void navigateToSelectedTaskDetails(SelectEvent<Object> event) {
    String uuid = ((ITask) event.getObject()).uuid();
    PortalNavigator.navigateToPortalTaskDetails(uuid);
  }

  protected IUser currentUser() {
    return Ivy.session().getSessionUser();
  }

  public void onDashboardChange(int index) {
    currentDashboardIndex = index;
    selectedDashboard = dashboards.get(index);
    buildWidgetModels(selectedDashboard);
  }

  public String createExtractedTextFromHtml(String text) {
    return HtmlParser.extractTextFromHtml(text);
  }

  public int getCurrentTabIndex() {
    return dashboards.indexOf(getSelectedDashboard());
  }

  public DashboardWidget getWidget() {
    return widget;
  }

  public void setWidget(DashboardWidget widget) {
    this.widget = widget;
  }

  public boolean getIsReadOnlyMode() {
    return isReadOnlyMode;
  }

  public void setIsReadOnlyMode(boolean isReadOnlyMode) {
    this.isReadOnlyMode = isReadOnlyMode;
  }

  public int getCurrentDashboardIndex() {
    return currentDashboardIndex;
  }

  public void setCurrentDashboardIndex(int currentDashboardIndex) {
    this.currentDashboardIndex = currentDashboardIndex;
  }

  public boolean getIsEditMode() {
    return false;
  }

  protected String translate(String cms) {
    return Ivy.cms().co(cms);
  }

  protected String translate(String cms, List<Object> params) {
    return Ivy.cms().co(cms, params);
  }

  public Dashboard getSelectedDashboard() {
    return selectedDashboard;
  }

  public String getSelectedDashboardId() {
    return selectedDashboardId;
  }

  public void setSelectedDashboardId(String selectedDashboardId) {
    this.selectedDashboardId = selectedDashboardId;
  }

  public void loadAllWidgetSavedFilters() {
    widgetFilters = new ArrayList<>();
    deleteFilters = new ArrayList<>();
    widgetFilters.addAll(WidgetFilterService.getInstance().findAll());

    // Update latest widget name
    widgetFilters.forEach(filter -> {
      var selectedWidget = selectedDashboard.getWidgets().stream()
          .filter(widget -> widget.getId().equals(filter.getWidgetId()))
          .findFirst().orElse(null);
      if (selectedWidget != null) {
        filter.setWidgetName(selectedWidget.getName());
      }
    });
  }

  public void onClickSavedFilterItem(WidgetFilterModel filter, DashboardWidget widget) {
    if (filter == null || widget == null) {
      return;
    }

    if (widget.isSavedFilterSelected(filter)) {
      widget.getUserFilterCollection().getSelectedWidgetFilters().removeIf(WidgetFilterModel.isEqualFilter(filter));
    } else {
      widget.getUserFilterCollection().getSelectedWidgetFilters().add(filter);
    }

    if (widget.getType() == DashboardWidgetType.CASE) {
      CaseDashboardWidget caseWidget = ((CaseDashboardWidget) widget);

      if (CollectionUtils.isEmpty(caseWidget.getUserFilters())) {
        caseWidget.setUserFilters(new ArrayList<>());
      }

      List<DashboardFilter> savedFilters = caseWidget.getUserFilterCollection()
          .getSelectedWidgetFilters().stream()
          .map(WidgetFilterModel::getUserFilters)
          .filter(list -> CollectionUtils.isNotEmpty(list))
          .collect(ArrayList::new, List::addAll, List::addAll);
      caseWidget.setUserFilters(savedFilters);
      return;
    }
    
    if (widget.getType() == DashboardWidgetType.TASK) {
      TaskDashboardWidget taskWidget = ((TaskDashboardWidget) widget);

      if (CollectionUtils.isEmpty(taskWidget.getUserFilters())) {
        taskWidget.setUserFilters(new ArrayList<>());
      }

      List<DashboardFilter> savedFilters = taskWidget.getUserFilterCollection()
          .getSelectedWidgetFilters().stream()
          .map(WidgetFilterModel::getUserFilters)
          .filter(list -> CollectionUtils.isNotEmpty(list))
          .collect(ArrayList::new, List::addAll, List::addAll);
      taskWidget.setUserFilters(savedFilters);
      return;
    }


    var filterableColumns = new ArrayList<ColumnModel>();

    if (DashboardWidgetType.PROCESS == widget.getType()) {
      filterableColumns.addAll(((CompactProcessDashboardWidget) widget).getFilterableColumns());
    }
    WidgetFilterService.getInstance().buildFilterOptions(widget, filterableColumns);
    WidgetFilterService.getInstance().updateUserFilterOptionMap(widget);
  }

  public void deleteSavedFilter() {
    CollectionUtils.emptyIfNull(deleteFilters).forEach(filter -> {
      WidgetFilterService.getInstance().delete(filter.getId());
    });
    loadAllWidgetSavedFilters();
  }

  public List<WidgetFilterModel> getWidgetFilters() {
    return widgetFilters;
  }

  public void setWidgetFilters(List<WidgetFilterModel> widgetFilters) {
    this.widgetFilters = widgetFilters;
  }

  public List<WidgetFilterModel> getDeleteFilters() {
    return deleteFilters;
  }

  public void setDeleteFilters(List<WidgetFilterModel> deleteFilters) {
    this.deleteFilters = deleteFilters;
  }

  public List<DashboardTemplate> getDashboardTemplates() {
    if (CollectionUtils.isEmpty(dashboardTemplates)) {
      loadDashboardTemplate();
    }
    return dashboardTemplates;
  }

  public void setDashboardTemplates(List<DashboardTemplate> dashboardTemplates) {
    this.dashboardTemplates = dashboardTemplates;
  }

  private String readDashboardFromSession() {
    return (String) Ivy.session().getAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString());
  }

  private void storeDashboardInSession(String id) {
    Ivy.session().setAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString(), id);
  }

  private int findIndexOfDashboardById(String selectedDashboardId) {
    int currentDashboardIndex = 0;
    if(StringUtils.isNotBlank(selectedDashboardId)) {
      currentDashboardIndex = dashboards.indexOf(dashboards.stream()
          .filter(dashboard -> dashboard.getId().contentEquals(selectedDashboardId)).findFirst().orElse(null));
      if(currentDashboardIndex == -1) {
        currentDashboardIndex = 0;
      }
    }
    return currentDashboardIndex;
  }

  public int getMaxRowNumberInExcel() {
    return Exporter.MAX_ROW_NUMBER_IN_EXCEL;
  }

  protected List<String> getSupportedLanguages() {
    return LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
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
    if (StringUtils.isNotBlank(translatedText)) {
      displayName.setValue(translatedText);
      translatedText = "";
    }
  }

  public boolean isRequiredField(DisplayName displayName) {
    String currentLanguage = UserUtils.getUserLanguage();
    String displayLanguage = displayName.getLocale().getLanguage();
    return currentLanguage.equals(displayLanguage);
  }

  public String getWarningText() {
    return warningText;
  }
  public String getDashboardUrl() {
    return dashboardUrl;
  }

  public void setDashboardUrl(String dashboardUrl) {
    this.dashboardUrl = dashboardUrl;
  }

  public void initShareDashboardLink(Dashboard dashboard) {
    setDashboardUrl(UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(dashboard.getId()));
  }

  public boolean isShowShareButtonOnDashboard() {
    return PermissionUtils.hasShareDashboardPermission() && selectedDashboard != null && !getIsEditMode() && selectedDashboard.getIsPublic();
  }

  public List<Dashboard> getImportedDashboards() {
    return importedDashboards;
  }

  public void setImportedDashboards(List<Dashboard> importedDashboards) {
    this.importedDashboards = importedDashboards;
  }

  public String getClientStatisticApiUri() {
    return this.clientStatisticApiUri;
  }

  public boolean canEnableQuickSearch(DashboardWidget widget) {
    return widget.getType().canEnableQuickSearch();
  }
  
  public void setSelectedDashboardName(String dashboardName) {
    this.selectedDashboardName = dashboardName;
  }
  
  public String getSelectedDashboardName() {
    if (selectedDashboardName.isBlank()) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/dashboard");
    }
    return selectedDashboardName;
  }
}

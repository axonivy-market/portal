package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;

  protected List<Dashboard> dashboards;
  protected Dashboard selectedDashboard;
  private String selectedDashboardId;
  protected DashboardWidget widget;
  protected boolean isReadOnlyMode;
  private int currentDashboardIndex;
  private boolean canEdit;
  private List<WidgetFilterModel> widgetFilters;
  private List<WidgetFilterModel> deleteFilters;

  @PostConstruct
  public void init() {
    canEdit = PermissionUtils.hasDashboardWritePermission() && !isMobileDevice();
    currentDashboardIndex = 0;
    isReadOnlyMode = true;
    dashboards = defaultDashboards();
    if (CollectionUtils.isNotEmpty(dashboards)) {
      mergeUserDashboard();
      selectedDashboard = dashboards.get(0);
    }
    buildWidgetModels(selectedDashboard);
  }

  private boolean isMobileDevice() {
    HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String userAgent = request.getHeader("user-agent");
    return userAgent.matches(".*Android.*|.*webOS.*|.*iPhone.*|.*iPad.*|.*iPod.*|.*BlackBerry.*|.*IEMobile.*|.*Opera Mini.*");
  }

  public void mergeUserDashboard() {
    var userDashboardList = DashboardService.getInstance().getDashboardBySessionUser();
    if (CollectionUtils.isEmpty(userDashboardList)) {
      return;
    }
    for (Dashboard userDashboard : userDashboardList) {
      if (dashboards.contains(userDashboard)) {
        dashboards.set(dashboards.indexOf(userDashboard), userDashboard);
      }
    }
  }

  protected void removeDashboardInUserProperty() {
    currentUser().removeProperty(PortalVariable.DASHBOARD.key);
  }

  protected void buildWidgetModels(Dashboard dashboard) {
    if (dashboard == null || CollectionUtils.isEmpty(dashboard.getWidgets())) {
      return;
    }
    for (var widget : dashboard.getWidgets()) {
      String cmsUri = "";
      DashboardWidgetUtils.buildWidgetColumns(widget);
      switch (widget.getType()) {
        case TASK:
          for (ColumnModel columnModel : ((TaskDashboardWidget) widget).getColumns()) {
            DashboardWidgetUtils.updateTypeForWidgetColumn(columnModel);
          }
          cmsUri = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourTasks";
          break;
        case CASE:
          for (ColumnModel columnModel : ((CaseDashboardWidget) widget).getColumns()) {
            DashboardWidgetUtils.updateTypeForWidgetColumn(columnModel);
          }
          cmsUri = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCases";
          break;
        case PROCESS:
          cmsUri = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourProcesses";
          break;
        default:
          break;
      }
      if (StringUtils.isBlank(widget.getName())) {
        widget.setName(translate(cmsUri));
      }
      WidgetFilterService.getInstance().applyUserFilterFromSession(widget);
      DashboardWidgetUtils.removeStyleNewWidget(widget);
    }
  }

  protected List<Dashboard> defaultDashboards() {
    var availableDashboards = DashboardService.getInstance().getDefaultDashboards();
    var dashboards = new ArrayList<Dashboard>();
    for (var dashboard : availableDashboards) {
      boolean canRead = false;
      List<String> permissions = dashboard.getPermissions();
      if (CollectionUtils.isEmpty(permissions)) {
        canRead = true;
      } else {
        for (String permission : permissions) {
          canRead = isSessionUserHasPermisson(permission);
          if (canRead) {
            break;
          }
        }
      }
      if (canRead) {
        dashboards.add(dashboard);
      }
    }
    return dashboards;
  }

  private boolean isSessionUserHasPermisson(String permission) {
    return StringUtils.startsWith(permission, "#") ? StringUtils.equals(currentUser().getMemberName(), permission)
        : PermissionUtils.doesSessionUserHaveRole(permission);
  }

  public List<Dashboard> getDashboards() {
    return dashboards;
  }

  public void save() {
    var layouts = DashboardWidgetUtils.getWidgetLayoutFromRequest(getRequestParameterMap());
    for (var layout : layouts) {
      DashboardWidget updatedWidget = getSelectedDashboard().getWidgets().stream()
          .filter(w -> w.getId().contentEquals(layout.getId()))
          .findFirst().get();

      WidgetLayout updatedLayout = new WidgetLayout();
      updatedLayout.setAxisX(layout.getAxisX());
      updatedLayout.setAxisY(layout.getAxisY());
      updatedLayout.setWidth(layout.getWidth());
      updatedLayout.setHeight(layout.getHeight());

      updatedWidget.setLayout(updatedLayout);
    }
    DashboardService.getInstance().saveDashboardForSessionUser(getSelectedDashboard());
  }

  public void saveSelectedWidget() {
    this.dashboards.set(this.dashboards.indexOf(this.getSelectedDashboard()), this.getSelectedDashboard());
    DashboardService.getInstance().saveDashboardForSessionUser(getSelectedDashboard());
  }

  protected Map<String, String> getRequestParameterMap() {
    return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
  }

  public void navigateToSelectedTaskDetails(SelectEvent event) {
    Long taskId = ((ITask) event.getObject()).getId();
    PortalNavigator.navigateToPortalTaskDetails(taskId);
  }

  public void navigateToSelectedCaseDetails(SelectEvent event) {
    Long caseId = ((ICase) event.getObject()).getId();
    PortalNavigator.navigateToPortalCaseDetails(caseId);
  }

  private IUser currentUser() {
    return Ivy.session().getSessionUser();
  }

  public void onDashboardChange(int index) {
    currentDashboardIndex = index;
    selectedDashboard = dashboards.get(index);
    buildWidgetModels(selectedDashboard);
  }

  public void onDashboardChangeByDropdown() {
    if (selectedDashboardId != null) {
      currentDashboardIndex = dashboards.indexOf(dashboards.stream()
          .filter(dashboard -> dashboard.getId().contentEquals(selectedDashboardId)).findFirst().orElse(null));
      selectedDashboard = dashboards.get(currentDashboardIndex);
      buildWidgetModels(selectedDashboard);
    }
  }

  public void startTask(ITask task) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(task.getStartLinkEmbedded().getRelative());
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

  public boolean getCanEdit() {
    return canEdit;
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

  public void setReadOnlyMode(boolean isReadOnlyMode) {
    this.isReadOnlyMode = isReadOnlyMode;
  }

  public String getSelectedDashboardId() {
    return selectedDashboardId;
  }

  public void setSelectedDashboardId(String selectedDashboardId) {
    this.selectedDashboardId = selectedDashboardId;
  }

  public void navigatetoConfigurationPage() {
    PortalNavigator.navigateToNewDashboardConfiguration();
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

    var filterableColumns = new ArrayList<ColumnModel>();
    if (DashboardWidgetType.TASK == widget.getType()) {
      filterableColumns.addAll(((TaskDashboardWidget) widget).getFilterableColumns());
    }
    if (DashboardWidgetType.CASE == widget.getType()) {
      filterableColumns.addAll(((CaseDashboardWidget) widget).getFilterableColumns());
    }
    if (DashboardWidgetType.PROCESS == widget.getType()) {
      filterableColumns.addAll(((ProcessDashboardWidget) widget).getFilterableColumns());
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
}

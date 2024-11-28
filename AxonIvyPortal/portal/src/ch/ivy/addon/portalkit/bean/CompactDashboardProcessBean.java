package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn.APPLICATION;
import static ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn.CATEGORY;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.enums.ProcessSorting;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;

@ManagedBean
@ViewScoped
public class CompactDashboardProcessBean
    implements DashboardProcessBeanHandler<CompactProcessDashboardWidget>, Serializable {

  private static final long serialVersionUID = 1L;
  private List<DashboardProcess> portalCompactProcesses;
  private DashboardProcessBean dashboardProcessBean;

  public CompactProcessDashboardWidget getWidget() {
    return (CompactProcessDashboardWidget) dashboardProcessBean.getWidget();
  }

  @PostConstruct
  public void init() {
    dashboardProcessBean = ManagedBeans.get("dashboardProcessBean");
    portalCompactProcesses = new ArrayList<>();
  }


  public void toggleSelectAllProcesses() {
    getWidget().setSelectedAllProcess(
        getWidget().getDisplayProcesses().size() == dashboardProcessBean.getPortalDashboardProcesses().size());
  }

  @Override
  public void preRender(CompactProcessDashboardWidget widget) {
    dashboardProcessBean.preRender(widget);
    getWidget().setInConfiguration(true);
    preRenderCompactProcessStartWidget();
  }

  private void preRenderCompactProcessStartWidget() {
    CompactProcessDashboardWidget widget = getWidget();
    if (widget.isSelectedAllProcess()) {
      widget.setDisplayProcesses(getAllPortalProcesses());
    } else if (CollectionUtils.isNotEmpty(widget.getProcessPaths())) {
      List<DashboardProcess> selectedProcesses = preRenderDefinedCompactProcesses();
      widget.setProcesses(selectedProcesses);
    } else {
      updatePortalCompactProcesses();
    }
  }

  private List<DashboardProcess> preRenderDefinedCompactProcesses() {
    List<DashboardProcess> selectedProcesses = new ArrayList<>();
    if (getWidget().getCategories() != null) {
      setPortalCompactProcesses(new ArrayList<>(filterByCategory(getAllPortalProcesses())));
    }
    for (String processPath : getWidget().getProcessPaths()) {
      selectedProcesses.addAll(getPortalCompactProcesses().stream()
          .filter(process -> process.getId().equalsIgnoreCase(processPath)).collect(Collectors.toList()));
    }

    return selectedProcesses;
  }

  private void updatePortalCompactProcesses() {
    var processes = new ArrayList<DashboardProcess>();
    if (getWidget().getCategories() == null) {
      processes = new ArrayList<>(getAllPortalProcesses());
    } else {
      processes = new ArrayList<>(filterByCategory(getAllPortalProcesses()));
    }
    getWidget().setDisplayProcesses(processes.stream().collect(Collectors.toList()));
    setPortalCompactProcesses(processes);
  }

  private List<DashboardProcess> filterByCategory(List<DashboardProcess> dashboardProcesses) {
    return CollectionUtils.emptyIfNull(dashboardProcesses).stream()
        .filter(process -> DashboardWidgetUtils.isProcessMatchedCategory(process, getWidget().getCategories()))
        .collect(Collectors.toList());
  }
  
  private List<DashboardProcess> filterByApplication() {
    return getAllPortalProcesses().stream()
        .filter(process -> isProcessMatchedApplication(process, getWidget().getApplications())).collect(Collectors.toList());
  }
  
  private boolean isProcessMatchedApplication(DashboardProcess process, List<String> applications) {
    if (CollectionUtils.isEmpty(applications)) {
      return true;
    }
    return applications.contains(process.getApplication());
  }

  @Override
  public void preview() {
    dashboardProcessBean.preview();
    CompactProcessDashboardWidget widget = getWidget();
    boolean isEmptyProcess = CollectionUtils.isEmpty(widget.getProcesses());
    List<DashboardProcess> displayProcesses = new ArrayList<>();
    List<DashboardProcess> processAfterSorting = new ArrayList<>();
    if (isEmptyProcess) {
      displayProcesses = getAllPortalProcesses();
      widget.setSelectedAllProcess(true);
    } else {
      widget.setSelectedAllProcess(false);
      displayProcesses = widget.getProcesses();
    }
    ColumnModel applicationFilter = getFilterableColumnByField(APPLICATION);
    if (applicationFilter != null) {
      widget.setApplications(applicationFilter.getFilterList());
      if (isEmptyProcess) {
        displayProcesses = filterByApplication();
      }
    }
    ColumnModel categoryFilter = getFilterableColumnByField(CATEGORY);
    if (categoryFilter != null) {
      widget.setCategories(categoryFilter.getFilterList());
      if (isEmptyProcess) {
        filterByCategory(displayProcesses);
      }
    }
    widget.setDisplayProcesses(processAfterSorting);
  }

  private ColumnModel getFilterableColumnByField(DashboardStandardProcessColumn column) {
    return getWidget()
        .getFilterableColumns()
        .stream()
        .filter(filter -> column.getField().equalsIgnoreCase(filter.getField()))
        .findAny().orElse(null);
  }

  public void startProcessWithCompactMode(DashboardProcess process) throws IOException {
    startProcessWithCompactMode(process, false);
  }

  public void startProcessWithCompactMode(DashboardProcess process,
      boolean isAiResult) throws IOException {
    Objects.requireNonNull(process, "Process must not be null");
    String link = process.getStartLink();

    if (isAiResult) {
      handleNavigateAsAiResult(process, link);
      return;
    }

    if (dashboardProcessBean.isExternalLink(process)) {
      dashboardProcessBean.redirectToLink(link, false);
      return;
    }

    dashboardProcessBean.redirectToLink(link, true);
  }

  private void handleNavigateAsAiResult(DashboardProcess process, String link)
      throws IOException {
    link = dashboardProcessBean.getRedirectLink(link,
        !dashboardProcessBean.isExternalLink(process));

    String statement = "parent.parent.redirectToUrlCommand([{name: 'url', value: '"
        + URLDecoder.decode(link, StandardCharsets.UTF_8) + "'}])";
    PrimeFaces.current().executeScript(statement);
  }

  public boolean isBrokenLink(DashboardProcess dashboardProcess) {
    return !getAllPortalProcesses()
        .stream()
        .filter(process -> process.getId().equals(dashboardProcess.getId()))
        .findFirst().isPresent();
  }

  public List<DashboardProcess> getPortalCompactProcesses() {
    if (CollectionUtils.isEmpty(portalCompactProcesses)) {
      portalCompactProcesses = new ArrayList<>(dashboardProcessBean.getPortalDashboardProcesses());
    }
    return portalCompactProcesses;
  }

  public void setPortalCompactProcesses(List<DashboardProcess> portalCompactProcesses) {
    this.portalCompactProcesses = portalCompactProcesses;
  }

  public List<DashboardProcess> getAllPortalProcesses() {
    return dashboardProcessBean.getPortalDashboardProcesses();
  }

  @Override
  public void onChangeDisplayMode() {
    CompactProcessDashboardWidget newWidget = new CompactProcessDashboardWidget(dashboardProcessBean.getWidget());
    newWidget.buildFilterableColumns(DashboardWidgetUtils.initProcessFilterableColumns());
    dashboardProcessBean.updateWidget(newWidget);
  }

  @Override
  public void onChangeApplications(List<String> applications) {
    dashboardProcessBean.setApplications(applications);
  }

  public ProcessSorting[] getProcessSorting() {
    return ProcessSorting.values();
  }
  
  public boolean isPreviewCustomOrder() {
    ProcessDashboardWidget widget = dashboardProcessBean.getWidget();
    if (widget == null || !(widget instanceof CompactProcessDashboardWidget)) {
      return false;
    }
    CompactProcessDashboardWidget compactProcessWidget = (CompactProcessDashboardWidget)widget;
    return compactProcessWidget.isPreview() && ProcessSorting.BY_CUSTOM_ORDER.name().equals(compactProcessWidget.getSorting());
  }
}

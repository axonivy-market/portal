package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;

@ManagedBean
@ViewScoped
public class CompactDashboardProcessBean
    implements DashboardProcessBeanHandler<CompactProcessDashboardWidget>, Serializable {

  private static final long serialVersionUID = 1L;
  private List<DashboardProcess> allPortalProcesses;
  private List<DashboardProcess> portalCompactProcesses;
  private DashboardProcessBean dashboardProcessBean;

  public CompactProcessDashboardWidget getWidget() {
    return (CompactProcessDashboardWidget) dashboardProcessBean.getWidget();
  }

  @PostConstruct
  public void init() {
    dashboardProcessBean = ManagedBeans.get("dashboardProcessBean");
    allPortalProcesses = new ArrayList<>();
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
    if (getWidget().isSelectedAllProcess()) {
      getWidget().setDisplayProcesses(getAllPortalProcesses());
    } else if (CollectionUtils.isNotEmpty(getWidget().getProcessPaths())) {
      List<DashboardProcess> selectedProcesses = preRenderDefinedCompactProcesses();
      getWidget().setProcesses(selectedProcesses);
    } else {
      updatePortalCompactProcesses();
    }
  }

  private List<DashboardProcess> preRenderDefinedCompactProcesses() {
    List<DashboardProcess> selectedProcesses = new ArrayList<>();
    if (getWidget().getCategories() != null) {
      setPortalCompactProcesses(new ArrayList<>(filterByCategory()));
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
      processes = new ArrayList<>(filterByCategory());
    }
    getWidget().setDisplayProcesses(processes.stream().collect(Collectors.toList()));
    setPortalCompactProcesses(processes);
  }

  private List<DashboardProcess> filterByCategory() {
    return getAllPortalProcesses().stream()
        .filter(process -> isProcessMatchedCategory(process, getWidget().getCategories())).collect(Collectors.toList());
  }

  private boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    return categories.indexOf(process.getCategory()) > -1
        || (StringUtils.isBlank(process.getCategory()) && hasNoCategory);
  }

  @Override
  public void preview() {
    dashboardProcessBean.preview();
    List<DashboardProcess> displayProcesses = new ArrayList<>();
    if (CollectionUtils.isEmpty(getWidget().getProcesses())) {
      displayProcesses = getAllPortalProcesses();
      getWidget().setSelectedAllProcess(true);
    } else {
      getWidget().setSelectedAllProcess(false);
      displayProcesses = getWidget().getProcesses();
    }
    var categoryFilter = getWidget().getFilterableColumns().stream()
        .filter(filter -> DashboardStandardProcessColumn.CATEGORY.getField().equalsIgnoreCase(filter.getField()))
        .findAny().orElse(null);
    if (categoryFilter != null && CollectionUtils.isNotEmpty(categoryFilter.getFilterList())) {
      getWidget().setCategories(categoryFilter.getFilterList());
      if (CollectionUtils.isEmpty(getWidget().getProcesses())) {
        displayProcesses = filterByCategory();
      }
    }
    getWidget().setDisplayProcesses(displayProcesses);
  }

  public void startProcessWithCompactMode(DashboardProcess process) throws IOException {
    Objects.requireNonNull(process, "Process must not be null");
    String link = process.getStartLink();
    if (dashboardProcessBean.isExternalLink(process)) {
      dashboardProcessBean.redirectToLink(link, false);
      return;
    }

    if (dashboardProcessBean.isExpressProcess(process) && StringUtils.isNotBlank(process.getId())) {
      ProcessStartCollector processStartCollector = new ProcessStartCollector();
      String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
      if (StringUtils.isNotBlank(expressStartLink)) {
        dashboardProcessBean.redirectToLink(expressStartLink + "?workflowID=" + process.getId(), false);
        return;
      }
    }

    dashboardProcessBean.redirectToLink(link, true);
  }

  public boolean isBrokenLink(DashboardProcess dashboardProcess) {
    return !getAllPortalProcesses().stream().filter(process -> process.getId().equals(dashboardProcess.getId()))
        .findFirst().isPresent();
  }

  public List<DashboardProcess> getPortalCompactProcesses() {
    portalCompactProcesses = new ArrayList<>(dashboardProcessBean.getPortalDashboardProcesses());
    return portalCompactProcesses;
  }

  public void setPortalCompactProcesses(List<DashboardProcess> portalCompactProcesses) {
    this.portalCompactProcesses = portalCompactProcesses;
  }

  public List<DashboardProcess> getAllPortalProcesses() {
    allPortalProcesses = findAllPortalProcesses();
    return allPortalProcesses;
  }

  public void setAllPortalProcesses(List<DashboardProcess> allPortalProcesses) {
    this.allPortalProcesses = allPortalProcesses;
  }

  public List<DashboardProcess> findAllPortalProcesses() {
    allPortalProcesses = new ArrayList<>(dashboardProcessBean.getPortalDashboardProcesses());
    return allPortalProcesses;
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

}

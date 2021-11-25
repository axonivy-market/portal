package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class DashboardProcessBean extends AbstractProcessBean implements Serializable {

  private static final long serialVersionUID = -6664090186198762432L;
  private List<ProcessWidgetMode> displayModes;
  private ProcessDashboardWidget widget;
  private List<DashboardProcess> allPortalProcesses;
  private List<DashboardProcess> portalCompactProcesses;
  private List<DashboardProcess> portalCombinedProcesses;
  private List<IProcessStart> startableProcessStarts;

  @Override
  @PostConstruct
  public void init() {
    displayModes = Arrays.asList(ProcessWidgetMode.values()).stream()
        .sorted((mode1, mode2) -> mode1.getLabel().compareToIgnoreCase(mode2.getLabel()))
        .collect(Collectors.toList());
    allPortalProcesses = new ArrayList<>();
    portalCompactProcesses = new ArrayList<>();
    portalCombinedProcesses = new ArrayList<>();
  }

  public Function<? super Process, ? extends DashboardProcess> toDashboardProcess() {
    return process -> new DashboardProcess(process);
  }

  private void initStartableProcessStarts() {
    startableProcessStarts = Ivy.session().getStartableProcessStarts();
  }

  @Override
  protected List<Process> findProcesses() {
    List<IWebStartable> processes = ProcessService.newInstance().findProcesses().getProcesses();
    List<Process> defaultPortalProcesses = new ArrayList<>();
    processes.forEach(process -> defaultPortalProcesses.add(new DashboardProcess(process)));
    return defaultPortalProcesses;
  }

  public void toggleSelectAllProcesses() {
    this.widget.setSelectedAllProcess(this.widget.getDisplayProcesses().size() == getPortalDashboardProcesses().size());
  }

  public void preRender(ProcessDashboardWidget widget) {
    this.widget = widget;
    this.widget.setInConfiguration(true);
    if (this.widget.getDisplayMode() == ProcessWidgetMode.COMPACT_MODE) {
      preRenderCompactProcessStartWidget();
      return;
    }

    if (this.widget.getDisplayMode() == ProcessWidgetMode.COMBINED_MODE) {
      this.widget.setShowCases(false);
    }
  }

  private void preRenderCompactProcessStartWidget() {
    if (this.widget.isSelectedAllProcess()) {
      this.widget.setDisplayProcesses(getAllPortalProcesses());
    } else if (CollectionUtils.isNotEmpty(this.widget.getProcesses())) {
      List<DashboardProcess> selectedProcesses = new ArrayList<>();
      for (DashboardProcess selectedProcess : widget.getProcesses()) {
        selectedProcesses.addAll(getPortalDashboardProcesses().stream()
            .filter(process -> process.getId().equalsIgnoreCase(selectedProcess.getId()))
            .collect(Collectors.toList()));
      }
      this.widget.setProcesses(selectedProcesses);
    } else {
      var processes = new ArrayList<DashboardProcess>();
      if (this.widget.getCategories() == null) {
        processes = new ArrayList<>(getAllPortalProcesses());
      } else {
        processes = new ArrayList<>(filterByCategory());
      }
      this.widget.setDisplayProcesses(processes.stream().collect(Collectors.toList()));
      setPortalCompactProcesses(processes);
    }
  }

  private List<DashboardProcess> filterByCategory() {
    return getAllPortalProcesses().stream().
        filter(process -> isProcessMatchedCategory(process, this.widget.getCategories()))
        .collect(Collectors.toList());
  }

  private boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    return categories.indexOf(process.getCategory()) > -1
        || (StringUtils.isBlank(process.getCategory()) && hasNoCategory);
  }

  public void preview() {
    if (widget.getDisplayMode() == ProcessWidgetMode.COMPACT_MODE) {
      List<DashboardProcess> displayProcesses = new ArrayList<>();
      if (CollectionUtils.isEmpty(widget.getProcesses())) {
        displayProcesses = getAllPortalProcesses();
        widget.setSelectedAllProcess(true);
      } else {
        widget.setSelectedAllProcess(false);
        displayProcesses = widget.getProcesses();
      }
      var categoryFilter = widget.getFilterableColumns().stream()
          .filter(filter -> DashboardStandardProcessColumn.CATEGORY.getField().equalsIgnoreCase(filter.getField()))
          .findAny().orElse(null);
      if (categoryFilter != null && CollectionUtils.isNotEmpty(categoryFilter.getFilterList())) {
        widget.setCategories(categoryFilter.getFilterList());
        if (CollectionUtils.isEmpty(widget.getProcesses())) {
          displayProcesses = filterByCategory();
        }
      }
      widget.setDisplayProcesses(displayProcesses);
    } 
  }

  public void selectProcessMode(ProcessWidgetMode mode) {
    this.widget.setDisplayMode(mode);
  }

  public List<DashboardProcess> completeProcesses(String query) {
    return getPortalDashboardProcesses().stream()
        .filter(process -> StringUtils.containsIgnoreCase(process.getName(), query))
        .collect(Collectors.toList());
  }

  public List<DashboardProcess> completeProcessesWithoutExternalLink(String query) {
    return getPortalCombinedProcesses().stream()
        .filter(process -> StringUtils.containsIgnoreCase(process.getName(), query)).collect(Collectors.toList());
  }

  public ProcessDashboardWidget getWidget() {
    return widget;
  }

  public void setWidget(ProcessDashboardWidget widget) {
    this.widget = widget;
  }

  public List<ProcessWidgetMode> getDisplayModes() {
    return displayModes;
  }

  public void setDisplayModes(List<ProcessWidgetMode> displayModes) {
    this.displayModes = displayModes;
  }

  public boolean isExpressProcess(DashboardProcess process) {
    return process != null && process.getType() == ProcessType.EXPRESS_PROCESS;
  }

  public boolean isExternalLink(DashboardProcess process) {
    return process != null && process.getType() == ProcessType.EXTERNAL_LINK;
  }

  public String targetToStartProcess(DashboardProcess process) {
    return isExternalLink(process) ? "_blank" : "_self";
  }

  public void startProcessWithFullMode(DashboardProcess process) throws IOException {
    boolean isEmbedInFrame = !isExpressProcess(process) && !isExternalLink(process);
    redirectToLink(process.getStartLink(), isEmbedInFrame);
  }

  public void startProcessWithCompactMode(DashboardProcess process) throws IOException {
    Objects.requireNonNull(process, "Process must not be null");
    String link = process.getStartLink();
    if (isExternalLink(process)) {
      redirectToLink(link, false);
      return;
    }

    if (isExpressProcess(process) && StringUtils.isNotBlank(process.getId())) {
      ProcessStartCollector processStartCollector = new ProcessStartCollector();
      String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
      if (StringUtils.isNotBlank(expressStartLink)) {
        redirectToLink(expressStartLink + "?workflowID=" + process.getId(), false);
        return;
      }
    }

    redirectToLink(link, true);
  }

  private void redirectToLink(String link, boolean isEmbedInFrame) throws IOException {
    if (isEmbedInFrame) {
      link += (link.contains("?") ? "&" : "?" + "embedInFrame");
    } 
    FacesContext.getCurrentInstance().getExternalContext().redirect(link);
  }
  
  public boolean isCaseMap(DashboardProcess process) {
    return !Objects.isNull(process) && process.getStartLink().endsWith(".icm");
  }

  public String getProcessInformationPageUrl(DashboardProcess process) {
    return PortalNavigator.buildProcessInfoUrl(process.getId());
  }

  public void updateProcessStartId() {
    if (CollectionUtils.isEmpty(startableProcessStarts)) {
      initStartableProcessStarts();
    }

    if (widget != null && widget.getDisplayMode() == ProcessWidgetMode.COMBINED_MODE && widget.getProcess() != null
        && widget.getProcess().getType() != ProcessType.EXPRESS_PROCESS) {
      String startLink = widget.getProcess().getStartLink();
      IProcessStart processStart = startableProcessStarts.stream()
          .filter(process -> process.getLink().getRelative().equals(startLink)).findFirst().get();
      widget.getProcess().setProcessStartId(processStart.getId());
    }
  }

  public boolean isBrokenLink(DashboardProcess dashboardProcess) {
    return !getAllPortalProcesses()
        .stream()
        .filter(process -> process.getId().equals(dashboardProcess.getId()))
        .findFirst()
        .isPresent();
  }

  public List<DashboardProcess> getPortalCombinedProcesses() {
    if (CollectionUtils.isEmpty(portalCombinedProcesses)) {
      portalCombinedProcesses = new ArrayList<>(getPortalDashboardProcesses().stream()
          .filter(process -> process.getType() != ProcessType.EXTERNAL_LINK)
          .collect(Collectors.toList()));
    }
    return portalCombinedProcesses;
  }

  public List<DashboardProcess> getAllPortalProcesses() {
    return CollectionUtils.isEmpty(allPortalProcesses) ? findAllPortalProcesses() : allPortalProcesses;
  }

  public void setAllPortalProcesses(List<DashboardProcess> allPortalProcesses) {
    this.allPortalProcesses = allPortalProcesses;
  }

  public List<DashboardProcess> findAllPortalProcesses() {
    allPortalProcesses = new ArrayList<>(getPortalDashboardProcesses());
    return allPortalProcesses;
  }

  public List<DashboardProcess> getPortalDashboardProcesses() {
    if (CollectionUtils.isEmpty(portalProcesses)) {
      portalProcesses = new ArrayList<>();
      super.init();
    }
    return portalProcesses.stream().map(toDashboardProcess()).collect(Collectors.toList());
  }

  public List<DashboardProcess> getPortalCompactProcesses() {
    if (CollectionUtils.isEmpty(portalCompactProcesses)) {
      portalCompactProcesses = new ArrayList<>(getPortalDashboardProcesses());
    }
    return portalCompactProcesses;
  }

  public void setPortalCompactProcesses(List<DashboardProcess> portalCompactProcesses) {
    this.portalCompactProcesses = portalCompactProcesses;
  }

}

package ch.ivy.addon.portal.generic.bean;

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
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bean.AbstractProcessBean;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivy.addon.portalkit.util.ProcessTreeUtils;
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
  private List<DashboardProcess> portalCombinedProcesses;
  private List<IProcessStart> startableProcessStarts;
  private CheckboxTreeNode categoryTree;
  private CheckboxTreeNode[] categoryNodes;

  @Override
  @PostConstruct
  public void init() {
    initStartableProcessStarts();
    this.displayModes = Arrays.asList(ProcessWidgetMode.COMBINED_MODE, ProcessWidgetMode.COMPACT_MODE, ProcessWidgetMode.FULL_MODE);
    allPortalProcesses = new ArrayList<>();
    portalCombinedProcesses = new ArrayList<>();
    super.init();

    portalProcesses = portalProcesses.stream()
        .map(toDashboardProcess()).
        collect(Collectors.toList());
    portalCombinedProcesses.addAll(portalProcesses.stream()
        .filter(process -> process.getType() != ProcessType.EXTERNAL_LINK)
        .map(toDashboardProcess())
        .collect(Collectors.toList()));
    portalProcesses.forEach(process -> allPortalProcesses.add(new DashboardProcess(process)));
    categoryTree = ProcessTreeUtils.buildProcessCategoryCheckboxTreeRoot(allPortalProcesses);
  }

  public Function<? super Process, ? extends DashboardProcess> toDashboardProcess() {
    return process -> new DashboardProcess(process);
  }

  private void initStartableProcessStarts() {
    startableProcessStarts = Ivy.session().getStartableProcessStarts();
    startableProcessStarts = CollectionUtils.isNotEmpty(startableProcessStarts) ? startableProcessStarts : new ArrayList<>();
  }

  @Override
  protected List<Process> findProcesses() {
    List<IWebStartable> processes = ProcessService.newInstance().findProcesses().getProcesses();
    List<Process> defaultPortalProcesses = new ArrayList<>();
    processes.forEach(process -> defaultPortalProcesses.add(new DashboardProcess(process)));
    return defaultPortalProcesses;
  }

  public void toggleSelectAllProcesses() {
    this.widget.setSelectedAllProcess(this.widget.getDisplayProcesses().size() == this.portalProcesses.size());
  }

  public void preRender(ProcessDashboardWidget widget) {
    this.widget = widget;
    if (this.widget.getDisplayMode().equals(ProcessWidgetMode.COMPACT_MODE)) {
      preRenderCompactProcessStartWidget();
      return;
    }

    if (this.widget.getDisplayMode().equals(ProcessWidgetMode.COMBINED_MODE)) {
      this.widget.setShowCases(false);
    }
  }

  private void preRenderCompactProcessStartWidget() {
    if (this.widget.isSelectedAllProcess()) {
      this.widget.setDisplayProcesses(allPortalProcesses);
    } else if (CollectionUtils.isNotEmpty(this.widget.getProcesses())) {
      List<DashboardProcess> selectedProcesses = new ArrayList<>();
      for (DashboardProcess selectedProcess : widget.getProcesses()) {
        selectedProcesses.addAll(portalProcesses.stream()
            .filter(process -> process.getId().equalsIgnoreCase(selectedProcess.getId()))
            .map(toDashboardProcess())
            .collect(Collectors.toList()));
      }
      this.widget.setProcesses(selectedProcesses);
    } else {
      if (this.widget.getCategories() == null) {
        portalProcesses = allPortalProcesses.stream().map(process -> new DashboardProcess(process)).collect(Collectors.toList());
      } else {
        portalProcesses = allPortalProcesses.stream()
            .filter(process -> isProcessMatchedCategory(process, this.widget.getCategories()))
            .collect(Collectors.toList());
      }
      this.widget.setDisplayProcesses(portalProcesses.stream().map(toDashboardProcess()).collect(Collectors.toList()));
    }
    buildCategoryTree();
  }

  private void buildCategoryTree() {
    this.categoryNodes = CategoryUtils.recoverSelectedCategories(this.categoryTree, this.widget.getCategories());
  }

  public void setCategoryNodes() {
    this.widget.setCategories(CategoryUtils.getCategoryPaths(this.categoryNodes));
    if (CollectionUtils.isEmpty(this.widget.getCategories())) {
      portalProcesses = new ArrayList<>(allPortalProcesses);
    } else {
      portalProcesses =
          allPortalProcesses.stream().filter(process -> isProcessMatchedCategory(process, this.widget.getCategories()))
              .collect(Collectors.toList());
      this.widget.setProcesses(new ArrayList<>());
      this.widget.setDisplayProcesses(new ArrayList<>());
    }
  }

  private boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    return categories.indexOf(process.getCategory()) > -1
        || (StringUtils.isBlank(process.getCategory()) && hasNoCategory);
  }

  public String getDisplayCategories() {
    List<String> categories = CategoryUtils.getCategoryPaths(categoryNodes);
    return CollectionUtils.isNotEmpty(categories) ? CategoryUtils.getNodeValue(categories) : "";
  }

  public void preview() {
    if (widget.getDisplayMode() == ProcessWidgetMode.COMPACT_MODE) {
      List<DashboardProcess> displayProcesses =
          CollectionUtils.isEmpty(widget.getProcesses()) ? allPortalProcesses : widget.getProcesses();
      if (CollectionUtils.isNotEmpty(widget.getCategories())) {
        if (CollectionUtils.isEmpty(widget.getProcesses())) {
          displayProcesses =
              allPortalProcesses.stream().filter(process -> isProcessMatchedCategory(process, widget.getCategories()))
                  .collect(Collectors.toList());
        }
        categoryNodes = CategoryUtils.recoverSelectedCategories(categoryTree, widget.getCategories());
      }

      widget.setDisplayProcesses(displayProcesses);
    }
  }

  public void selectProcessMode(ProcessWidgetMode mode) {
    this.widget.setDisplayMode(mode);
  }

  public List<DashboardProcess> completeProcesses(String query) {
    return this.portalProcesses.stream()
        .filter(process -> StringUtils.containsIgnoreCase(process.getName(), query))
        .map(toDashboardProcess())
        .collect(Collectors.toList());
  }

  public List<DashboardProcess> completeProcessesWithoutExternalLink(String query) {
    return this.portalCombinedProcesses.stream()
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
    return process != null && ProcessStartUtils.isExpressProcess(process.getType());
  }

  public boolean isExternalLink(DashboardProcess process) {
    return process != null && ProcessStartUtils.isExternalLink(process.getType());
  }

  public String targetToStartProcess(DashboardProcess process) {
    String target = "_self";
    if (process != null && process.getType() == ProcessType.EXTERNAL_LINK) {
      target = "_blank";
    }
    return target;
  }

  public void startProcessWithFullMode(DashboardProcess process) throws IOException {
    String link = process.getStartLink();
    if (isExpressProcess(process) || isExternalLink(process)) {
      redirectToLink(link, false);
      return;
    }

    redirectToLink(link, true);
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
      link += link.contains("?") ? "&" : "?";
      FacesContext.getCurrentInstance().getExternalContext().redirect(link + "embedInFrame");
    } else {
      FacesContext.getCurrentInstance().getExternalContext().redirect(link);
    }
  }

  public boolean isCaseMap(DashboardProcess process) {
    return !Objects.isNull(process) && process.getStartLink().endsWith(".icm");
  }

  public String getProcessInformationPageUrl(DashboardProcess process) {
    return PortalNavigator.buildProcessInfoUrl(process.getId());
  }

  public void updateProcessStartId() {
    if (widget != null && widget.getDisplayMode() == ProcessWidgetMode.COMBINED_MODE && widget.getProcess() != null
        && widget.getProcess().getType() != ProcessType.EXPRESS_PROCESS) {
      String startLink = widget.getProcess().getStartLink();
      IProcessStart processStart = startableProcessStarts.stream()
          .filter(process -> process.getLink().getRelative().equals(startLink)).findFirst().get();
      widget.getProcess().setProcessStartId(processStart.getId());
    }
  }

  public boolean isBrokenLink(DashboardProcess dashboardProcess) {
    return !allPortalProcesses.stream().filter(process -> process.getId().equals(dashboardProcess.getId())).findFirst()
        .isPresent();
  }

  public CheckboxTreeNode[] getCategoryNodes() {
    return categoryNodes;
  }

  public void setCategoryNodes(CheckboxTreeNode[] categoryNodes) {
    this.categoryNodes = categoryNodes;
  }

  public CheckboxTreeNode getCategoryTree() {
    return categoryTree;
  }

  public void setCategoryTree(CheckboxTreeNode categoryTree) {
    this.categoryTree = categoryTree;
  }

  public List<DashboardProcess> getAllPortalProcesses() {
    return allPortalProcesses;
  }

  public void setAllPortalProcesses(List<DashboardProcess> allPortalProcesses) {
    this.allPortalProcesses = allPortalProcesses;
  }

}

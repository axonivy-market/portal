package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.constant.DashboardConstants.REMOTE_COMMAND_PATTERN;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.PrimeFaces;
import org.primefaces.model.SortMeta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.datamodel.DashboardProcessCaseLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.DashboardProcessTaskLazyDataModel;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.dto.dashboard.process.ProcessColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardProcessSearchCriteria;
import ch.ivy.addon.portalkit.service.DashboardWidgetInformationService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProcessDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;
  private ProcessWidgetMode displayMode = ProcessWidgetMode.COMPACT_MODE;
  @JsonIgnore
  private DashboardProcessSearchCriteria criteria;
  private String processPath; // For full, combine, image mode
  private List<String> processPaths; // For compact mode

  private int rowsPerPage = 5;
  @JsonIgnore
  private List<DashboardProcess> processes;
  @JsonIgnore
  private DashboardProcess process;
  @JsonIgnore
  private List<DashboardProcess> displayProcesses;
  @JsonIgnore
  private List<DashboardProcess> originalDisplayProcesses;
  @JsonIgnore
  private DashboardProcessTaskLazyDataModel taskDataModel;
  @JsonIgnore
  private DashboardProcessCaseLazyDataModel caseDataModel;
  @JsonIgnore
  private Map<ProcessType, Long> processByTypeStatistic;
  @JsonIgnore
  private boolean showCases;
  @JsonIgnore
  private List<ColumnModel> filterableColumns;
  @JsonIgnore
  private boolean isPreview;

  public ProcessDashboardWidget() {
    displayMode = ProcessWidgetMode.COMPACT_MODE;
    isPreview = false;
    processes = new ArrayList<>();
    filterableColumns = new ArrayList<>();
    criteria = new DashboardProcessSearchCriteria();
  }

  public ProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
    // Properties of ProcessDashboardWidget
    displayMode = widget.getDisplayMode();
    criteria = widget.getCriteria();
    processPath = widget.getProcessPath();
    processPaths = widget.getProcessPaths();
    rowsPerPage = widget.getRowsPerPage();
    processes = widget.getProcesses();
    process = widget.getProcess();
    displayProcesses = widget.getDisplayProcesses();
    originalDisplayProcesses = widget.getOriginalDisplayProcesses();
    taskDataModel = widget.getTaskDataModel();
    caseDataModel = widget.getCaseDataModel();
    processByTypeStatistic = widget.getProcessByTypeStatistic();
    showCases = widget.isShowCases();
    filterableColumns = widget.getFilterableColumns();
    isPreview = widget.isPreview();
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.PROCESS;
  }

  @JsonIgnore
  public void buildProcessDataFirstTime() {
    DashboardWidgetUtils.loadProcessesOfWidget(this);
  }

  @Override
  @JsonIgnore
  public void onApplyUserFilters() {
    filterProcessesByUser();
    super.onApplyUserFilters();
  }

  @JsonIgnore
  public void filterProcessesByUser() {
    if (ProcessWidgetMode.COMPACT_MODE != displayMode) {
      return;
    }
    this.displayProcesses = this.criteria.searchProcessesByFilters(this);
  }

  @Override
  public void buildStatisticInfos() {
    PrimeFaces.current().executeScript(String.format(REMOTE_COMMAND_PATTERN, "buildStatisticProcessTypes", id));
  }

  @JsonIgnore
  public void buildProcessByTypeStatistic() {
    processByTypeStatistic = DashboardWidgetInformationService.getInstance().buildStatisticOfProcessByType(displayProcesses);
  }

  @Override
  @JsonIgnore
  public void resetWidgetFilters() {
    DashboardWidgetUtils.resetUserFilterOnColumns(getFilterableColumns());
    this.displayProcesses = this.originalDisplayProcesses;
  }

  public ProcessWidgetMode getDisplayMode() {
    return displayMode;
  }

  public void setDisplayMode(ProcessWidgetMode displayMode) {
    this.displayMode = displayMode;
  }

  public List<DashboardProcess> getProcesses() {
    return processes;
  }

  public void setProcesses(List<DashboardProcess> processes) {
    this.processes = processes;
  }

  public DashboardProcess getProcess() {
    return process;
  }

  public void setProcess(DashboardProcess process) {
    this.process = process;
    if (ProcessWidgetMode.COMBINED_MODE == displayMode && this.process != null) {
      this.taskDataModel = new DashboardProcessTaskLazyDataModel(this.process.getProcessStartId(), this.process.getName());
      this.caseDataModel = new DashboardProcessCaseLazyDataModel(this.process.getProcessStartId(), this.process.getName());
    }
  }

  public DashboardProcessTaskLazyDataModel getTaskDataModel() {
    return taskDataModel;
  }

  public DashboardProcessCaseLazyDataModel getCaseDataModel() {
    return caseDataModel;
  }

  public boolean isShowCases() {
    return showCases;
  }

  public void setShowCases(boolean showCases) {
    this.showCases = showCases;
  }

  public Map<ProcessType, Long> getProcessByTypeStatistic() {
    return processByTypeStatistic;
  }

  public void setProcessByTypeStatistic(Map<ProcessType, Long> processByTypeStatistic) {
    this.processByTypeStatistic = processByTypeStatistic;
  }

  public List<DashboardProcess> getDisplayProcesses() {
    return displayProcesses;
  }

  public void setDisplayProcesses(List<DashboardProcess> displayProcesses) {
    this.displayProcesses = displayProcesses;
  }

  public boolean isSelectedAllProcess() {
    return this.criteria.isSelectedAllProcess();
  }

  public void setSelectedAllProcess(boolean isSelectedAllProcess) {
    this.criteria.setSelectedAllProcess(isSelectedAllProcess);
  }

  public int getRowsPerPage() {
    return rowsPerPage;
  }

  public void setRowsPerPage(int rowsPerPage) {
    this.rowsPerPage = rowsPerPage;
  }

  public List<String> getCategories() {
    return this.criteria.getCategories();
  }

  public void setCategories(List<String> categories) {
    this.criteria.setCategories(categories);
  }

  public List<DashboardProcess> getOriginalDisplayProcesses() {
    return originalDisplayProcesses;
  }

  public void setOriginalDisplayProcesses(List<DashboardProcess> originalDisplayProcesses) {
    this.originalDisplayProcesses = originalDisplayProcesses;
  }

  public String getProcessPath() {
    return processPath;
  }

  public void setProcessPath(String processPath) {
    this.processPath = processPath;
  }

  public List<String> getProcessPaths() {
    return processPaths;
  }

  public void setProcessPaths(List<String> processPaths) {
    this.processPaths = processPaths;
  }

public List<ColumnModel> getFilterableColumns() {
    return filterableColumns;
  }

  public void setFilterableColumns(List<ColumnModel> columns) {
    this.filterableColumns = columns;
  }

  @JsonIgnore
  public void buildFilterableColumns(List<ProcessColumnModel> columns) {
    this.filterableColumns = columns.stream().collect(Collectors.toList());
  }

  @JsonIgnore
  public void setInConfiguration(boolean isInConfiguration) {
    this.criteria.setInConfiguration(isInConfiguration);
  }

  @JsonIgnore
  public boolean isPreview() {
    return isPreview;
  }

  @JsonIgnore
  public void setPreview(boolean isPreview) {
    this.isPreview = isPreview;
  }

  @JsonIgnore
  public SortMeta getSortByName() {
    return SortFieldUtil.buildSortMeta("name", false);
  }

  public DashboardProcessSearchCriteria getCriteria() {
    return criteria;
  }

}

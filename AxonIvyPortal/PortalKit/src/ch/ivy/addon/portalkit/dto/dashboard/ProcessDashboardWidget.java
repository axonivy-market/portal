package ch.ivy.addon.portalkit.dto.dashboard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.datamodel.DashboardProcessCaseLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.DashboardProcessTaskLazyDataModel;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcessUserFilter;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.util.CategoryUtils;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProcessDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;
  private ProcessWidgetMode displayMode = ProcessWidgetMode.COMPACT_MODE;
  private boolean isSelectedAllProcess;
  private List<String> categories;
  private String processPath;
  
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
  private DashboardProcessUserFilter userFilter;

  public ProcessDashboardWidget() {
    this.displayMode = ProcessWidgetMode.COMPACT_MODE;
    this.processes = new ArrayList<>();
    this.userFilter = new DashboardProcessUserFilter();
    processByTypeStatistic = new HashMap<>();
    processByTypeStatistic.put(ProcessType.IVY_PROCESS, 0L);
    processByTypeStatistic.put(ProcessType.EXPRESS_PROCESS, 0L);
    processByTypeStatistic.put(ProcessType.EXTERNAL_LINK, 0L);
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.PROCESS;
  }

  @JsonIgnore
  public static ProcessDashboardWidget buildDefaultWidget(String id, String name) {
    ProcessDashboardWidget widget = new ProcessDashboardWidget();
    widget.setId(id);
    widget.setName(name);
    widget.setLayout(new WidgetLayout());
    widget.getLayout().setWidth(2);
    widget.getLayout().setHeight(6);
    widget.getLayout().setAxisX(0);
    widget.getLayout().setAxisY(0);
    widget.setAutoPosition(true);
    widget.setSelectedAllProcess(true);
    return widget;
  }

  @Override
  @JsonIgnore
  public void onApplyUserFilters() throws ParseException {
    countDefinedUserFilter();
    super.onApplyUserFilters();
  }

  @JsonIgnore
  public void countDefinedUserFilter() {
    this.displayProcesses = this.originalDisplayProcesses;
    int numberOfFilters = 0;
    if (StringUtils.isNotBlank(userFilter.getProcessName())) {
      this.displayProcesses = this.displayProcesses.stream()
          .filter(process -> process.getName().toLowerCase().indexOf(userFilter.getProcessName().toLowerCase()) > -1)
          .collect(Collectors.toList());
      numberOfFilters++;
      if (CollectionUtils.isNotEmpty(userFilter.getProcessTypes())) {
        this.displayProcesses = this.displayProcesses.stream()
            .filter(process -> userFilter.getProcessTypes().contains(process.getType())).collect(Collectors.toList());
        numberOfFilters++;
      }
    } else if (CollectionUtils.isNotEmpty(userFilter.getProcessTypes())) {
      this.displayProcesses = this.displayProcesses.stream()
          .filter(process -> userFilter.getProcessTypes().contains(process.getType())).collect(Collectors.toList());
      numberOfFilters++;
    }

    if (CollectionUtils.isNotEmpty(this.userFilter.getCategories())) {
      this.displayProcesses = this.displayProcesses.stream()
          .filter(process -> isProcessMatchedCategory(process, this.userFilter.getCategories()))
          .collect(Collectors.toList());
      numberOfFilters++;
    }

    this.userDefinedFiltersCount =
        numberOfFilters == 0 ? Optional.empty() : Optional.of(String.valueOf(numberOfFilters));
  }

  private boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    return categories.indexOf(process.getCategory()) > -1
        || (StringUtils.isBlank(process.getCategory()) && hasNoCategory);
  }

  @Override
  public void buildStatisticInfos() throws ParseException {
    buildProcessByTypeStatistic();
  }

  private void buildProcessByTypeStatistic() {
    Long numberOfIvyProcesses =
        this.displayProcesses.stream().filter(process -> process.getType().equals(ProcessType.IVY_PROCESS)).count();
    processByTypeStatistic.put(ProcessType.IVY_PROCESS, numberOfIvyProcesses);
    Long numberOfExpressProcesses =
        this.displayProcesses.stream().filter(process -> process.getType().equals(ProcessType.EXPRESS_PROCESS)).count();
    processByTypeStatistic.put(ProcessType.EXPRESS_PROCESS, numberOfExpressProcesses);
    Long numberOfExternalLink =
        this.displayProcesses.stream().filter(process -> process.getType().equals(ProcessType.EXTERNAL_LINK)).count();
    processByTypeStatistic.put(ProcessType.EXTERNAL_LINK, numberOfExternalLink);
  }

  @Override
  @JsonIgnore
  public void resetWidgetFilters() {
    this.userFilter.setProcessName("");
    this.userFilter.setProcessTypes(new ArrayList<>());
    this.userFilter.setCategories(new ArrayList<>());
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
    if (displayMode == ProcessWidgetMode.COMBINED_MODE && this.process != null) {
      this.taskDataModel =
          new DashboardProcessTaskLazyDataModel(this.process.getProcessStartId(), this.process.getName());
      this.caseDataModel =
          new DashboardProcessCaseLazyDataModel(this.process.getProcessStartId(), this.process.getName());
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

  public DashboardProcessUserFilter getUserFilter() {
    return userFilter;
  }

  public void setUserFilter(DashboardProcessUserFilter userFilter) {
    this.userFilter = userFilter;
  }

  public List<DashboardProcess> getDisplayProcesses() {
    return displayProcesses;
  }

  public void setDisplayProcesses(List<DashboardProcess> displayProcesses) {
    this.displayProcesses = displayProcesses;
  }

  public boolean isSelectedAllProcess() {
    return isSelectedAllProcess;
  }

  public void setSelectedAllProcess(boolean isSelectedAllProcess) {
    this.isSelectedAllProcess = isSelectedAllProcess;
  }

  public int getRowsPerPage() {
    return rowsPerPage;
  }

  public void setRowsPerPage(int rowsPerPage) {
    this.rowsPerPage = rowsPerPage;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
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
}

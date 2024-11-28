package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.enums.ProcessWidgetMode.COMPACT_MODE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardProcessSearchCriteria;
import ch.ivy.addon.portalkit.service.DashboardWidgetInformationService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CompactProcessDashboardWidget extends ProcessDashboardWidget {

  private static final long serialVersionUID = 1L;

  private List<String> processPaths;
  protected String sorting;
  private Map<String, Integer> customIndexs;

  @JsonIgnore
  private List<DashboardProcess> displayProcesses;
  @JsonIgnore
  private Map<ProcessType, Long> processByTypeStatistic;
  @JsonIgnore
  private List<DashboardProcess> originalDisplayProcesses;
  @JsonIgnore
  private List<DashboardProcess> processes;
  private boolean enableQuickSearch;

  public CompactProcessDashboardWidget(CompactProcessDashboardWidget widget) {
    super(widget);
    setDisplayMode(COMPACT_MODE);
    processPaths = widget.getProcessPaths();
    displayProcesses = widget.getDisplayProcesses();
    processByTypeStatistic = widget.getProcessByTypeStatistic();
    filterableColumns = widget.getFilterableColumns();
    originalDisplayProcesses = widget.getOriginalDisplayProcesses();
    processes = widget.getProcesses();
    sorting = widget.getSorting();
    customIndexs = widget.getCustomIndexs();
    setEnableQuickSearch(widget.enableQuickSearch);
  }

  public CompactProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
    setDisplayMode(COMPACT_MODE);
    filterableColumns = new ArrayList<>();
    processes = new ArrayList<>();
    displayProcesses = new ArrayList<DashboardProcess>();
  }

  public CompactProcessDashboardWidget() {
    super();
    setDisplayMode(COMPACT_MODE);
    filterableColumns = new ArrayList<>();
    processes = new ArrayList<>();
    setEnableQuickSearch(true);
  }

  public List<DashboardProcess> getDisplayProcesses() {
    return displayProcesses;
  }

  public void setDisplayProcesses(List<DashboardProcess> displayProcesses) {
    this.displayProcesses = displayProcesses;
  }

  public Map<ProcessType, Long> getProcessByTypeStatistic() {
    return processByTypeStatistic;
  }

  public void setProcessByTypeStatistic(Map<ProcessType, Long> processByTypeStatistic) {
    this.processByTypeStatistic = processByTypeStatistic;
  }

  @JsonIgnore
  public boolean isSelectedAllProcess() {
    return this.criteria.isSelectedAllProcess();
  }

  @JsonIgnore
  public void setSelectedAllProcess(boolean isSelectedAllProcess) {
    this.criteria.setSelectedAllProcess(isSelectedAllProcess);
  }

  public List<String> getCategories() {
    return this.criteria.getCategories();
  }

  public void setCategories(List<String> categories) {
    this.criteria.setCategories(categories);
  }
  
  @Override
  public void onApplyUserFilters() {
    filterProcessesByUser();
    super.onApplyUserFilters();
  }

  public void filterProcessesByUser() {
    if (ProcessWidgetMode.COMPACT_MODE != getDisplayMode()) {
      return;
    }
    this.displayProcesses = this.criteria.searchProcessesByFilters(this);
  }

  public void buildProcessByTypeStatistic() {
    processByTypeStatistic =
        DashboardWidgetInformationService.getInstance().buildStatisticOfProcessByType(displayProcesses);
  }

  @Override
  public void resetWidgetFilters() {
    DashboardWidgetUtils.resetUserFilterOnColumns(getFilterableColumns());
    this.displayProcesses = this.originalDisplayProcesses;
  }

  @Override
  public DashboardProcessSearchCriteria getCriteria() {
    return criteria;
  }

  public List<DashboardProcess> getOriginalDisplayProcesses() {
    return originalDisplayProcesses;
  }

  public void setOriginalDisplayProcesses(List<DashboardProcess> originalDisplayProcesses) {
    this.originalDisplayProcesses = originalDisplayProcesses;
  }

  public List<DashboardProcess> getProcesses() {
    return processes;
  }

  public void setProcesses(List<DashboardProcess> processes) {
    this.processes = processes;
  }


  public List<String> getProcessPaths() {
    return processPaths;
  }

  public void setProcessPaths(List<String> processPaths) {
    this.processPaths = processPaths;
  }

  public String getSorting() {
    return sorting;
  }

  public void setSorting(String sorting) {
    this.sorting = sorting;
  }

  public Map<String, Integer> getCustomIndexs() {
    return customIndexs;
  }

  public void setCustomIndexs(Map<String, Integer> customIndexs) {
    this.customIndexs = customIndexs;
  }

  public boolean isEnableQuickSearch() {
      return enableQuickSearch;
    }

  public void setEnableQuickSearch(boolean enableQuickSearch) {
     this.enableQuickSearch = enableQuickSearch;
    }
  
  @Override
  public void setQuickSearchKeyword(String quickSearchKeyword) {
    if (BooleanUtils.isTrue(this.enableQuickSearch)) {
      super.setQuickSearchKeyword(quickSearchKeyword);
    }
  }
}

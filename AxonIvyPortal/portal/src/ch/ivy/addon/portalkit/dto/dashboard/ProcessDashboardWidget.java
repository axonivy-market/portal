package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.constant.DashboardConstants.REMOTE_COMMAND_PATTERN;

import java.util.List;
import java.util.stream.Collectors;

import org.primefaces.PrimeFaces;
import org.primefaces.model.SortMeta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.dto.dashboard.process.ProcessColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardProcessSearchCriteria;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProcessDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;

  @JsonIgnore
  private ProcessWidgetMode displayMode;
  @JsonIgnore
  protected List<ColumnModel> filterableColumns;
  @JsonIgnore
  private boolean isPreview;
  @JsonIgnore
  protected DashboardProcessSearchCriteria criteria;
  private boolean showFullscreenMode;
  private boolean showWidgetInfo;
  private boolean enableQuickSearch;

  public ProcessDashboardWidget() {
    criteria = new DashboardProcessSearchCriteria();
    setShowFullscreenMode(true);
    setShowWidgetInfo(true);
    setEnableQuickSearch(true);
  }

  public ProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
    criteria = widget.getCriteria();
    displayMode = widget.getDisplayMode();
    isPreview = widget.isPreview();
    showFullscreenMode = widget.showFullscreenMode;
    showWidgetInfo = widget.showWidgetInfo;
    setEnableQuickSearch(widget.enableQuickSearch);
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.PROCESS;
  }

  @JsonIgnore
  public void buildProcessDataFirstTime(boolean isInConfiguration) {
    setInConfiguration(isInConfiguration);
    DashboardWidgetUtils.loadProcessesOfWidget(this);
  }

  @Override
  public void buildStatisticInfos() {
    PrimeFaces.current().executeScript(String.format(REMOTE_COMMAND_PATTERN, "buildStatisticProcessTypes", id));
  }

  @Override
  @JsonIgnore
  public void resetWidgetFilters() {}

  public ProcessWidgetMode getDisplayMode() {
    return displayMode;
  }

  public void setDisplayMode(ProcessWidgetMode displayMode) {
    this.displayMode = displayMode;
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

  public List<ColumnModel> getFilterableColumns() {
    return filterableColumns;
  }

  public void setFilterableColumns(List<ColumnModel> columns) {
    this.filterableColumns = columns;
  }

  public void buildFilterableColumns(List<ProcessColumnModel> columns) {
    this.filterableColumns = columns.stream().collect(Collectors.toList());
  }

  public DashboardProcessSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(DashboardProcessSearchCriteria criteria) {
    this.criteria = criteria;
  }
  
  @JsonIgnore
  public void setInConfiguration(boolean isInConfiguration) {
    this.criteria.setInConfiguration(isInConfiguration);
  }
  
  public List<String> getApplications() {
    return this.criteria.getApplications();
  }
  
  public void setApplications(List<String> applications) {
    this.criteria.setApplications(applications);
  }

  public void setShowFullscreenMode(boolean showFullscreenMode) {
    this.showFullscreenMode = showFullscreenMode;
  }

  public boolean isShowFullscreenMode() {
    return showFullscreenMode;
  }

  public void setShowWidgetInfo(boolean showWidgetInfo) {
    this.showWidgetInfo = showWidgetInfo;
  }

  public boolean isShowWidgetInfo() {
    return showWidgetInfo;
  }

  @Override
  public void setQuickSearchKeyword() {
    this.criteria.setQuickSearchKeyword(this.getQuickSearchKeyword());
  }

  public boolean isEnableQuickSearch() {
    return enableQuickSearch;
  }

  public void setEnableQuickSearch(boolean enableQuickSearch) {
    this.enableQuickSearch = enableQuickSearch;
  }
}

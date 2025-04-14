package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.constant.DashboardConstants.REMOTE_COMMAND_PATTERN;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.SortMeta;

import com.axonivy.portal.dto.dashboard.WidgetInformationCategoryStatisticData;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.DashboardWidgetInformationService;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class TaskDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3246735956282078091L;
  protected static final String LIKE_FORMAT = "%%%s%%";

  @JsonIgnore
  private DashboardTaskLazyDataModel dataModel;
  @JsonIgnore
  private Map<TaskBusinessState, Long> taskByStateStatistic;
  @JsonIgnore
  private List<WidgetInformationCategoryStatisticData> taskByCategoryStatistic;
  @JsonIgnore
  private Long numberOfTasksExpireThisWeek;
  @JsonIgnore
  private Long numberOfTasksExpireToday;
  @JsonIgnore
  private List<ColumnModel> filterableColumns;
  private boolean enableQuickSearch;
  private boolean showWidgetInfo;
  private boolean showFullscreenMode;
  private boolean showPinnedToggle;

  @JsonIgnore
  private List<String> errors;

  public TaskDashboardWidget() {
    dataModel = new DashboardTaskLazyDataModel();
    setColumns(new ArrayList<>());
    setFilters(new ArrayList<>());
    setUserFilters(new ArrayList<>());
    setShowWidgetInfo(true);
    setShowFullscreenMode(true);
    setShowPinnedToggle(true);
  }

  @Override
  public void buildStatisticInfos() {
    String combinedAjaxCommand = String.format(REMOTE_COMMAND_PATTERN, "buildStatisticTaskExpiry", id)
        .concat(String.format(REMOTE_COMMAND_PATTERN, "buildStatisticTaskStates", id))
        .concat(String.format(REMOTE_COMMAND_PATTERN, "buildStatisticTaskCategory", id));
    PrimeFaces.current().executeScript(combinedAjaxCommand);
  }

  @JsonIgnore
  public void buildTaskByStateStatistic() {
    taskByStateStatistic = DashboardWidgetInformationService.getInstance().buildStatisticOfTaskByState(dataModel);
  }

  @JsonIgnore
  public void buildTaskExpiryStatistic() {
    var numberOfTasksExpireMap = DashboardWidgetInformationService.getInstance().buildStatisticOfTaskExpiry(dataModel);
    numberOfTasksExpireToday = numberOfTasksExpireMap.get(DashboardWidgetInformationService.TASKS_EXPIRE_TODAY);
    numberOfTasksExpireThisWeek = numberOfTasksExpireMap.get(DashboardWidgetInformationService.TASKS_EXPIRE_THIS_WEEK);
  }

  @JsonIgnore
  public void buildTaskByCategoryStatistic() {
    taskByCategoryStatistic = DashboardWidgetInformationService.getInstance().buildStatisticOfTaskByCategory(dataModel);
  }

  public List<ColumnModel> getFilterableColumns() {
    return filterableColumns;
  }

  @JsonIgnore
  public void buildFilterableColumns(List<TaskColumnModel> taskColumns) {
    this.filterableColumns = DashboardWidgetUtils.buildTaskFilterableColumns(taskColumns);
  }

  public boolean getCanWorkOn() {
    return this.dataModel.getCanWorkOn();
  }
  
  public void setCanWorkOn(boolean canWorkOn) {
    this.dataModel.setCanWorkOn(canWorkOn);
  }

  @JsonIgnore
  public SortMeta getSortBy() {
    List<TaskColumnModel> columnModels = getColumns();
    if (CollectionUtils.isNotEmpty(columnModels)) {
      String sortField = getSortField();
      for (TaskColumnModel taskColumnModel : columnModels) {
        if (taskColumnModel.getField().equalsIgnoreCase(sortField)) {
          return SortFieldUtil.buildSortMeta(taskColumnModel.getField(), isSortDescending());
        }
      }
    }
    return null;
  }

  public String getSortField() {
    return this.dataModel.getCriteria().getSortField();
  }
  
  public void setSortField(String sortField) {
    this.dataModel.getCriteria().setSortField(sortField);
  }
  
  public boolean isSortDescending() {
    return this.dataModel.getCriteria().isSortDescending();
  }
  
  public void setSortDescending(boolean sortDescending) {
    this.dataModel.getCriteria().setSortDescending(sortDescending);
  }

  public DashboardTaskLazyDataModel getDataModel() {
    return dataModel;
  }
  
  public void setDataModel(DashboardTaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }
  
  public List<TaskColumnModel> getColumns() {
    return this.dataModel.getCriteria().getColumns();
  }

  public void setColumns(List<TaskColumnModel> columns) {
    this.dataModel.getCriteria().setColumns(columns);
    buildFilterableColumns(columns);
  }

  @JsonIgnore
  public int getTaskCount() {
    return getDataModel().getRowCount();
  }
  
  @JsonIgnore
  public boolean isInConfiguration() {
    return this.dataModel.getCriteria().isInConfiguration();
  }
  
  public void setInConfiguration(boolean isInConfiguration) {
    this.dataModel.getCriteria().setInConfiguration(isInConfiguration);
  }

  public Map<TaskBusinessState, Long> getTaskByStateStatistic() {
    return taskByStateStatistic;
  }

  public Long getNumberOfTasksExpireThisWeek() {
    return numberOfTasksExpireThisWeek;
  }

  public void setNumberOfTasksExpireThisWeek(Long numberOfTasksExpireThisWeek) {
    this.numberOfTasksExpireThisWeek = numberOfTasksExpireThisWeek;
  }

  public Long getNumberOfTasksExpireToday() {
    return numberOfTasksExpireToday;
  }

  public void setNumberOfTasksExpireToday(Long numberOfTasksExpireToday) {
    this.numberOfTasksExpireToday = numberOfTasksExpireToday;
  }

  public List<WidgetInformationCategoryStatisticData> getTaskByCategoryStatistic() {
    return taskByCategoryStatistic;
  }

  public void setTaskByCategoryStatistic(List<WidgetInformationCategoryStatisticData> taskByCategoryStatistic) {
    this.taskByCategoryStatistic = taskByCategoryStatistic;
  }

  @Override
  @JsonIgnore
  public void resetWidgetFilters() {
    setUserFilters(new ArrayList<>());
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.TASK;
  }

  @Override
  public void setQuickSearchKeyword() {
    if (BooleanUtils.isTrue(this.enableQuickSearch)) {
      this.dataModel.getCriteria().setQuickSearchKeyword(this.getQuickSearchKeyword());
    }
      else {
        this.setQuickSearchKeyword(StringUtils.EMPTY);
  }}

  public List<DashboardFilter> getFilters() {
    return this.dataModel.getCriteria().getFilters();
  }

  public void setFilters(List<DashboardFilter> filters) {
    this.dataModel.getCriteria().setFilters(filters);
  }
  
  @JsonIgnore
  public List<DashboardFilter> getUserFilters() {
    return this.dataModel.getCriteria().getUserFilters();
  }

  @JsonIgnore
  public void setUserFilters(List<DashboardFilter> userFilters) {
    this.dataModel.getCriteria().setUserFilters(userFilters);
  }

  public boolean isEnableQuickSearch() {
    return enableQuickSearch;
  }

  public void setEnableQuickSearch(boolean enableQuickSearch) {
    this.enableQuickSearch = enableQuickSearch;
  }
  
  public void setShowWidgetInfo(boolean showWidgetInfo) {
    this.showWidgetInfo = showWidgetInfo;
  }
  
  public boolean isShowWidgetInfo() {
    return showWidgetInfo;
  }
  
  public void setShowFullscreenMode(boolean showFullscreenMode) {
    this.showFullscreenMode = showFullscreenMode;
  }
  
  public boolean isShowFullscreenMode() {
    return showFullscreenMode;
  }

  @Override
  @JsonIgnore
  public void loadUserFilter() {
    updateSavedFiltersSelection();

    // Don't load user filters when already loaded from session
    List<DashboardFilter> userFilters = getUserFilters();
    if (CollectionUtils.isNotEmpty(userFilters)) {
      // Clear temporary filters
      setUserFilters(userFilters.stream().filter(filter -> !filter.isTemp())
          .collect(Collectors.toList()));
      return;
    }

    var latestUserFilterOptions = getUserFilterCollection().getLatestFilterOption();
    WidgetFilterService.getInstance().updateFilterOptionsData(this, latestUserFilterOptions);
  }
  

  @JsonIgnore
  @Override
  public void onApplyUserFilters() {
    setUserFilters(this.getUserFilters().stream()
      .filter(Objects::nonNull)
        .filter(filter -> StringUtils.isNotBlank(filter.getField()))
      .collect(Collectors.toList()));

    getUserFilters().forEach(filter -> filter.setTemp(false));

    var filterService = WidgetFilterService.getInstance();
    userFilterCollection.updateUserFilterOptionValue(this);    
    filterService.storeUserSelectedFiltersToSession(id, getType(), userFilterCollection);
    userDefinedFiltersCount = DashboardWidgetUtils.countDefinedUserFilter(this);
  }

  @Override
  public void cancelUserFilter() {
    setUserFilters(getUserFilters().stream().filter(filter -> !filter.isTemp()).collect(Collectors.toList()));
  }


  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  @Override
  public void toggleShowPinned() {
    this.dataModel.setShowPinnedItem(isShowPinnedItem);
  }

  public boolean isShowPinnedToggle() {
    return showPinnedToggle;
  }

  public void setShowPinnedToggle(boolean showPinnedToggle) {
    this.showPinnedToggle = showPinnedToggle;
  }

}

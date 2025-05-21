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

import ch.ivy.addon.portalkit.datamodel.DashboardCaseLazyDataModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.DashboardWidgetInformationService;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;

public class CaseDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;

  @JsonIgnore
  private DashboardCaseLazyDataModel dataModel;
  @JsonIgnore
  private Map<CaseBusinessState, Long> caseByStateStatistic;
  @JsonIgnore
  private List<WidgetInformationCategoryStatisticData> caseByCategoryStatistic;
  @JsonIgnore
  private List<ColumnModel> filterableColumns;
  private boolean enableQuickSearch;
  private boolean showWidgetInfo;
  private boolean showFullscreenMode;
  private boolean showPinnedToggle;
  
  public CaseDashboardWidget() {
    dataModel = new DashboardCaseLazyDataModel();
    setColumns(new ArrayList<>());
    setFilters(new ArrayList<>());
    setUserFilters(new ArrayList<>());
    setShowWidgetInfo(true);
    setShowFullscreenMode(true);
    setShowPinnedToggle(true);
  }

  @JsonIgnore
  @Override
  public void buildStatisticInfos() {
    String combinedAjaxCommand = String.format(REMOTE_COMMAND_PATTERN, "buildStatisticCaseStates", id)
        .concat(String.format(REMOTE_COMMAND_PATTERN, "buildStatisticCaseCategory", id));
    PrimeFaces.current().executeScript(combinedAjaxCommand);
  }

  @JsonIgnore
  public void buildCaseByStateStatistic() {
    caseByStateStatistic = DashboardWidgetInformationService.getInstance().buildStatisticOfCaseByState(dataModel);
  }

  @JsonIgnore
  public void buildCaseByCategoryStatistic() {
    caseByCategoryStatistic = DashboardWidgetInformationService.getInstance().buildStatisticOfCaseByCategory(dataModel);
  }

  @JsonIgnore
  public List<ColumnModel> getFilterableColumns() {
    return filterableColumns;
  }

  @JsonIgnore
  public void buildFilterableColumns(List<CaseColumnModel> caseColumns) {
    filterableColumns = DashboardWidgetUtils.buildCaseFilterableColumns(caseColumns);
  }

  @JsonIgnore
  public SortMeta getSortBy() {
    List<CaseColumnModel> columnModels = getColumns();
    if (CollectionUtils.isNotEmpty(columnModels)) {
      String sortField = getSortField();
      for (CaseColumnModel caseColumnModel : columnModels) {
        if (caseColumnModel.getField().equalsIgnoreCase(sortField)) {
          return SortFieldUtil.buildSortMeta(caseColumnModel.getField(), isSortDescending());
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

  public DashboardCaseLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(DashboardCaseLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public List<CaseColumnModel> getColumns() {
    return this.dataModel.getCriteria().getColumns();
  }

  public void setColumns(List<CaseColumnModel> columns) {
    this.dataModel.getCriteria().setColumns(columns);
  }

  @JsonIgnore
  public int getCaseCount() {
    return getDataModel().getRowCount();
  }

  @JsonIgnore
  public boolean isInConfiguration() {
    return this.dataModel.getCriteria().isInConfiguration();
  }

  @JsonIgnore
  public void setInConfiguration(boolean isInConfiguration) {
    this.dataModel.getCriteria().setInConfiguration(isInConfiguration);
  }

  @JsonIgnore
  public Map<CaseBusinessState, Long> getCaseByStateStatistic() {
    return caseByStateStatistic;
  }

  @JsonIgnore
  public List<WidgetInformationCategoryStatisticData> getCaseByCategoryStatistic() {
    return caseByCategoryStatistic;
  }

  public boolean isEnableQuickSearch() {
    return enableQuickSearch;
  }

  public void setEnableQuickSearch(boolean enableQuickSearch) {
    this.enableQuickSearch = enableQuickSearch;
  }

  @Override
  @JsonIgnore
  public void resetWidgetFilters() {
    setUserFilters(new ArrayList<>());
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

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.CASE;
  }

  public List<DashboardFilter> getFilters() {
    return this.dataModel.getCriteria().getFilters();
  }

  public void setFilters(List<DashboardFilter> filters) {
    this.dataModel.getCriteria().setFilters(filters);
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

  @JsonIgnore
  public List<DashboardFilter> getUserFilters() {
    return this.dataModel.getCriteria().getUserFilters();
  }

  @JsonIgnore
  public void setUserFilters(List<DashboardFilter> userFilters) {
    this.dataModel.getCriteria().setUserFilters(userFilters);
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

  @Override
  public void setQuickSearchKeyword() {
    if (BooleanUtils.isTrue(enableQuickSearch)) {
      this.dataModel.getCriteria().setQuickSearchKeyword(this.getQuickSearchKeyword());
    } 
    else {
      this.setQuickSearchKeyword(StringUtils.EMPTY);
    }
  }

  @Override
  public void toggleShowPinned() {
    this.dataModel.setShowPinnedItem(showPinnedItem);
  }

  public boolean isShowPinnedToggle() {
    return showPinnedToggle;
  }

  public void setShowPinnedToggle(boolean showPinnedToggle) {
    this.showPinnedToggle = showPinnedToggle;
  }
}

package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivyteam.ivy.environment.Ivy;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @Type(value = TaskDashboardWidget.class, name = "task"),
  @Type(value = CaseDashboardWidget.class, name = "case"),
  @Type(value = ProcessDashboardWidget.class, name = "process")
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class DashboardWidget implements Serializable {

  private static final long serialVersionUID = 4580715578128184706L;

  @JsonIgnore
  public static final int MAX_NOTI_FILTERS = 9;
  @JsonIgnore
  public static final String MAX_NOTI_PATTERN = "%d+";
  
  protected String id;
  protected String name;
  private WidgetLayout layout;

  @JsonIgnore
  protected boolean autoPosition;
  @JsonIgnore
  protected boolean hasPredefinedFilter;
  @JsonIgnore
  protected Optional<String> userDefinedFiltersCount;
  @JsonIgnore
  protected String searchSavedFilterKeyword;
  @JsonIgnore
  protected List<WidgetFilterModel> savedFilters;
  @JsonIgnore
  protected UserFilterCollection userFilterCollection;

  public DashboardWidget() {}

  public DashboardWidget(String id, String name, WidgetLayout layout) {
    this.id = id;
    this.name = name;
    this.layout = layout;
  }

  @JsonIgnore
  @SuppressWarnings("unused")
  public void buildStatisticInfos() throws ParseException {}

  @JsonIgnore
  public void onResetUserFilters() throws ParseException {
    setSearchSavedFilterKeyword("");
    this.setUserDefinedFiltersCount(Optional.empty());
    resetWidgetFilters();
    userFilterCollection = new UserFilterCollection(id, getType());
    onApplyUserFilters();
  }
  
  @JsonIgnore
  public abstract void resetWidgetFilters();

  @JsonIgnore
  public void onCancelUserFilters() {}
  
  @JsonIgnore
  @SuppressWarnings("unused")
  public void onApplyUserFilters() throws ParseException {
    var filterService = WidgetFilterService.getInstance();
    filterService.consolidateSelectedFilters(this);
    userFilterCollection.updateUserFilterOptionValue(this);
    filterService.storeUserSelectedFiltersToSession(id, getType(), userFilterCollection);
  }

  @JsonIgnore
  @SuppressWarnings("unused")
  public void buildPredefinedFilterData() throws ParseException {}

  @JsonIgnore
  public void loadUserFilter() throws ParseException {
    updateSavedFiltersSelection();

    var latestUserFilterOptions = getUserFilterCollection().getLatestFilterOption();
    WidgetFilterService.getInstance().updateFilterOptionsData(this, latestUserFilterOptions);
  }

  @JsonIgnore
  public void updateSavedFiltersSelection() {
    setSearchSavedFilterKeyword("");
    var result = WidgetFilterService.getInstance().findFiltersByWidgetId(getId());
    setSavedFilters(result);
    getUserFilterCollection().setWidgetFilterSelections(result);
  }

  @JsonIgnore
  public void onClickSaveUserFilters() {
    WidgetFilterService.getInstance().prepareSaveFilter(this);
  }

  @JsonIgnore
  public void searchSavedFilters() {
    var savedFilterSelections = getUserFilterCollection().getWidgetFilterSelections();
    if (StringUtils.isEmpty(searchSavedFilterKeyword)) {
      if (CollectionUtils.isNotEmpty(savedFilterSelections)) {
        setSavedFilters(savedFilterSelections);
      }
      return;
    }
    if (CollectionUtils.isEmpty(savedFilterSelections)) {
      getUserFilterCollection().setWidgetFilterSelections(savedFilters);
    }

    var searchResult = getUserFilterCollection().getWidgetFilterSelections().stream()
        .filter(filter -> StringUtils.containsIgnoreCase(filter.getName(), searchSavedFilterKeyword))
        .collect(Collectors.toList());
    setSavedFilters(searchResult);
  }

  @JsonIgnore
  public boolean isSavedFilterSelected(WidgetFilterModel filter) {
    return getUserFilterCollection().getSelectedWidgetFilters().stream()
        .filter(WidgetFilterModel.isEqualFilter(filter))
        .count() > 0;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    if (StringUtils.startsWithIgnoreCase(name, DashboardConfigurationPrefix.CMS)) {
      return Ivy.cms().co(StringUtils.removeStart(name, DashboardConfigurationPrefix.CMS));
    }
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WidgetLayout getLayout() {
    return layout;
  }

  public void setLayout(WidgetLayout layout) {
    this.layout = layout;
  }
  
  public boolean getAutoPosition() {
    return autoPosition;
  }
  
  public void setAutoPosition(boolean autoPosition) {
    this.autoPosition = autoPosition;
  }

  public boolean isHasPredefinedFilter() {
    return hasPredefinedFilter;
  }

  public void setHasPredefinedFilter(boolean hasPredefinedFilter) {
    this.hasPredefinedFilter = hasPredefinedFilter;
  }

  public Optional<String> getUserDefinedFiltersCount() {
    return userDefinedFiltersCount;
  }

  public void setUserDefinedFiltersCount(Optional<String> userDefinedFiltersCount) {
    this.userDefinedFiltersCount = userDefinedFiltersCount;
  }

  @JsonIgnore
  public abstract DashboardWidgetType getType();

  public List<WidgetFilterModel> getSavedFilters() {
    return savedFilters;
  }

  public void setSavedFilters(List<WidgetFilterModel> savedFilters) {
    this.savedFilters = savedFilters;
  }

  public String getSearchSavedFilterKeyword() {
    return searchSavedFilterKeyword;
  }

  public void setSearchSavedFilterKeyword(String searchSavedFilterKeyword) {
    this.searchSavedFilterKeyword = searchSavedFilterKeyword;
  }

  public UserFilterCollection getUserFilterCollection() {
    if (userFilterCollection == null) {
      userFilterCollection = new UserFilterCollection(id, getType());
    }
    return userFilterCollection;
  }

  public void setUserFilterCollection(UserFilterCollection userFilterCollection) {
    this.userFilterCollection = userFilterCollection;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DashboardWidget other = (DashboardWidget) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}

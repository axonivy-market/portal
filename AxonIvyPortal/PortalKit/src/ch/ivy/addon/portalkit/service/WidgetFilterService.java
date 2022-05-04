package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bean.WidgetFilterHelperBean;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.FilterColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.UserFilterCollection;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.environment.Ivy;

public class WidgetFilterService extends JsonConfigurationService<WidgetFilterModel> {

  public static final String WIDGET_FILTER_KEY_PATTERN = "WIDGET_FILTER_KEY_%s_%s";
  
  private static WidgetFilterService instance;

  public static WidgetFilterService getInstance() {
    if (instance == null) {
      instance = new WidgetFilterService();
    }
    return instance;
  }

  public List<WidgetFilterModel> findFiltersByWidgetId(String widgetId) {
    return CollectionUtils.emptyIfNull(findAll()).stream()
        .filter(filter -> filter.getWidgetId().equals(widgetId))
        .collect(Collectors.toList());
  }

  @Override
  public Class<WidgetFilterModel> getType() {
    return WidgetFilterModel.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.WIDGET_FILTER.key;
  }

  public WidgetFilterModel prepareSaveFilter(DashboardWidget widget) {
    var filter = new WidgetFilterModel(null, widget.getType());
    filter.setWidgetName(widget.getName());
    filter.setWidgetId(widget.getId());
    var filterableColumns = new ArrayList<ColumnModel>();
    switch (widget.getType()) {
      case TASK:
        filterableColumns.addAll(((TaskDashboardWidget) widget).getFilterableColumns());
        break;
      case CASE:
        filterableColumns.addAll(((CaseDashboardWidget) widget).getFilterableColumns());
        break;
      case PROCESS:
        filterableColumns.addAll(((CompactProcessDashboardWidget) widget).getFilterableColumns());
        break;
      default:
        break;
    }
    filter.addFilterableColumns(filterableColumns);
    // Update savedFilter for HelperBean
    ((WidgetFilterHelperBean) ManagedBeans.get("widgetFilterHelperBean")).setSaveFilter(filter);
    return filter;
  }

  public void applyUserFilterFromSession(DashboardWidget widget) {
    var selectedFilterObject = Ivy.session().getAttribute(buildWidgetKey(widget.getId(), widget.getType()));
    var userFilterCollection = BusinessEntityConverter.jsonValueToEntity(String.valueOf(selectedFilterObject), UserFilterCollection.class);
    if (userFilterCollection == null) {
      return;
    }

    if (CollectionUtils.isEmpty(userFilterCollection.getWidgetFilterSelections())) {
      var availableWidgetFilters = findFiltersByWidgetId(widget.getId());
      userFilterCollection.setWidgetFilterSelections(availableWidgetFilters);
    }
    widget.setUserFilterCollection(userFilterCollection);
    updateFilterOptionsData(widget, userFilterCollection.getLatestFilterOption());
  }

  public void unifyUserFilters(DashboardWidget widget) {
    updateUserFilterOptionMap(widget);
    consolidateSelectedFilters(widget);
  }

  public void storeUserSelectedFiltersToSession(String widgetId, DashboardWidgetType widgetType,
      UserFilterCollection userFilterCollection) {
    if (StringUtils.isEmpty(widgetId)) {
      return;
    }

    if (userFilterCollection != null) {
      var jsonValue = BusinessEntityConverter.entityToJsonValue(userFilterCollection);
      Ivy.session().setAttribute(buildWidgetKey(widgetId, widgetType), jsonValue);
    }
  }

  public void updateFilterOptionsData(DashboardWidget widget, WidgetFilterModel userFilterOptions) {
    updateSelectedSavedFiltersByUser(widget);
    if (userFilterOptions != null) {
      var widgetFilterableColumns = new ArrayList<ColumnModel>();
      switch (widget.getType()) {
        case TASK:
          widgetFilterableColumns.addAll(((TaskDashboardWidget) widget).getFilterableColumns());
          break;
        case CASE:
          widgetFilterableColumns.addAll(((CaseDashboardWidget) widget).getFilterableColumns());
          break;
        case PROCESS:
          var processWidget = (ProcessDashboardWidget) widget;
          if (ProcessWidgetMode.COMPACT_MODE == processWidget.getDisplayMode()) {
            widgetFilterableColumns.addAll(((CompactProcessDashboardWidget) processWidget).getFilterableColumns());
          }
          break;
        default:
          break;
      }
      for (var widgetColumn : widgetFilterableColumns) {
        widgetColumn.resetUserFilter();
        mergeUserFilterInput(userFilterOptions, widgetColumn);
      }
    }
    widget.setUserDefinedFiltersCount(DashboardWidgetUtils.countDefinedUserFilter(widget));
  }

  private void mergeUserFilterInput(WidgetFilterModel userFilterOptions, ColumnModel column) {
    userFilterOptions.getFilterableColumns().stream()
        .filter(userSelected -> userSelected.getField().equals(column.getField())).findFirst()
        .ifPresent(selectedColumn -> {
          column.setUserFilter(selectedColumn.getUserFilter());
          column.setUserFilterList(selectedColumn.getUserFilterList());
          column.setUserFilterFrom(selectedColumn.getUserFilterFrom());
          column.setUserFilterTo(selectedColumn.getUserFilterTo());
        });
  }

  private void updateSelectedSavedFiltersByUser(DashboardWidget widget) {
    var widgetSavedFilters = CollectionUtils.emptyIfNull(widget.getUserFilterCollection().getWidgetFilterSelections());
    var selectedSavedFilterIds = CollectionUtils.emptyIfNull(widget.getUserFilterCollection().getSelectedWidgetFilterIds());
    var newUserSelectedFilterOptions = widgetSavedFilters.stream()
        .filter(savedFilter -> selectedSavedFilterIds.contains(savedFilter.getId()))
        .collect(Collectors.toList());
    widget.getUserFilterCollection().setSelectedWidgetFilters(newUserSelectedFilterOptions);
  }

  public void buildFilterOptions(DashboardWidget widget, List<ColumnModel> filterableColumns) {
    for (var widgetColumn : filterableColumns) {
      widgetColumn.resetUserFilter();
      var userFilter = "";
      Date filterFrom = null;
      Date filterTo = null;
      var filterList = new HashSet<String>();

      for (var selected : widget.getUserFilterCollection().getSelectedWidgetFilters()) {
        var selectedColumn = selected.getFilterableColumns().stream()
            .filter(compare -> compare.getField().equals(widgetColumn.getField())).findFirst().orElse(null);
        if (selectedColumn != null) {
          if (StringUtils.isNotEmpty(selectedColumn.getUserFilter())) {
            userFilter = userFilter.concat(selectedColumn.getUserFilter());
          }

          if (CollectionUtils.isNotEmpty(selectedColumn.getUserFilterList())) {
            filterList.addAll(selectedColumn.getUserFilterList());
          }

          if (StringUtils.isNotEmpty(selectedColumn.getUserFilterFrom())) {
            filterFrom = getLessDate(selectedColumn.getUserFilterFrom(), filterFrom);
          }
          if (StringUtils.isNotEmpty(selectedColumn.getUserFilterTo())) {
            filterTo = getGreaterDate(selectedColumn.getUserFilterTo(), filterTo);
          }
        }
      }
      widgetColumn.setUserFilter(userFilter);
      widgetColumn.setUserFilterList(new ArrayList<>(filterList));
      widgetColumn.setUserFilterFrom(formatDateToString(filterFrom));
      widgetColumn.setUserFilterTo(formatDateToString(filterTo));
    }
  }

  public void consolidateSelectedFilters(DashboardWidget widget) {
    boolean isFilterModified = false;
    Map<String, FilterColumnModel> filterOptionMap = widget.getUserFilterCollection().getSelectedFilterOptionMap();
    List<ColumnModel> filterableColumns = new ArrayList<>();
    switch (widget.getType()) {
      case TASK:
        filterableColumns = ((TaskDashboardWidget) widget).getFilterableColumns();
        break;
      case CASE:
        filterableColumns = ((CaseDashboardWidget) widget).getFilterableColumns();
        break;
      case PROCESS:
        filterableColumns = ((CompactProcessDashboardWidget) widget).getFilterableColumns();
        break;
      default:
        break;
    }

    for (var column : filterableColumns) {
      if (filterOptionMap.containsKey(column.getField())) {
        var columnData = filterOptionMap.get(column.getField());
        if (isNotEqualStringFilter(column.getUserFilter(), columnData.getUserFilter())
            || !StringUtils.equals(column.getUserFilterFrom(), columnData.getUserFilterFrom())
            || !StringUtils.equals(column.getUserFilterTo(), columnData.getUserFilterTo())
            || isNotEqualListFilterSelection(column.getUserFilterList(), columnData.getUserFilterList())) {
          isFilterModified = true;
          break;
        }
      }
    }

    if (isFilterModified) {
      widget.getUserFilterCollection().setSelectedWidgetFilterIds(new ArrayList<>());
      widget.getUserFilterCollection().setSelectedWidgetFilters(new ArrayList<>());
      widget.getUserFilterCollection().setSelectedFilterOptionMap(new HashMap<>());
    }
  }

  public void updateUserFilterOptionMap(DashboardWidget widget) {
    Map<String, FilterColumnModel> filterOptionMap = widget.getUserFilterCollection().getSelectedFilterOptionMap();
    filterOptionMap.clear();
    for (var savedFilter : widget.getUserFilterCollection().getSelectedWidgetFilters()) {
      for (var column : savedFilter.getFilterableColumns()) {
        FilterColumnModel columnData = new FilterColumnModel();
        var userFilter = "";
        Date filterFrom = null;
        Date filterTo = null;
        var filterList = new HashSet<String>();
        if (filterOptionMap.containsKey(column.getField())) {
          columnData = filterOptionMap.get(column.getField());
          userFilter = columnData.getUserFilter();
          filterFrom = Dates.parse(columnData.getUserFilterFrom());
          filterTo = Dates.parse(columnData.getUserFilterTo());
          filterList.addAll(columnData.getUserFilterList());
        }
        if (StringUtils.isNotEmpty(column.getUserFilter())) {
          userFilter = userFilter.concat(column.getUserFilter());
        }
        if (CollectionUtils.isNotEmpty(column.getUserFilterList())) {
          filterList.addAll(column.getUserFilterList());
        }
        if (StringUtils.isNotEmpty(column.getUserFilterFrom())) {
          filterFrom = getLessDate(column.getUserFilterFrom(), filterFrom);
        }
        if (StringUtils.isNotEmpty(column.getUserFilterTo())) {
          filterTo = getGreaterDate(column.getUserFilterTo(), filterTo);
        }
        columnData.setUserFilter(userFilter);
        columnData.setUserFilterList(new ArrayList<>(filterList));
        columnData.setUserFilterFrom(formatDateToString(filterFrom));
        columnData.setUserFilterTo(formatDateToString(filterTo));
        filterOptionMap.put(column.getField(), columnData);
      }
    }
  }

  private static String buildWidgetKey(String widgetId, DashboardWidgetType widgetType) {
    return String.format(WIDGET_FILTER_KEY_PATTERN, widgetType, widgetId);
  }

  private String formatDateToString(Date date) {
    return Objects.isNull(date) ? null : Dates.format(date);
  }

  private Date getLessDate(String selectedDateFrom, Date date) {
    var selectedDate = Dates.parse(selectedDateFrom);
    if (date == null || selectedDate.before(date)) {
      return selectedDate;
    }
    return date;
  }

  private Date getGreaterDate(String selectedDateFrom, Date date) {
    var selectedDate = Dates.parse(selectedDateFrom);
    if (date == null || selectedDate.after(date)) {
      return selectedDate;
    }
    return date;
  }

  private boolean isNotEqualStringFilter(String value, String compareValue) {
    if (value != null && compareValue != null && !StringUtils.equals(value, compareValue)) {
      return true;
    }
    return false;
  }

  private <T> boolean isNotEqualListFilterSelection(List<T> values, List<T> compareValues) {
    if (values != null && compareValues != null && !CollectionUtils.containsAll(values, compareValues)) {
      return true;
    }
    return false;
  }
}

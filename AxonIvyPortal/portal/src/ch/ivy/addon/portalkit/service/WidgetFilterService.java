package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

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
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

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
      case TASK -> prepareSaveTaskFilter(filter, (TaskDashboardWidget) widget);
      case CASE -> prepareSaveCaseFilter(filter, (CaseDashboardWidget) widget);
      case PROCESS -> filterableColumns.addAll(((CompactProcessDashboardWidget) widget).getFilterableColumns());
      default -> {}
    }

    if (CollectionUtils.isEmpty(filter.getUserFilters())) {
      filter.addFilterableColumns(filterableColumns);
    }

    // Update savedFilter for HelperBean
    ((WidgetFilterHelperBean) ManagedBeans.get("widgetFilterHelperBean")).setSaveFilter(filter);
    return filter;
  }

  public void prepareSaveCaseFilter(WidgetFilterModel filter, CaseDashboardWidget widget) {
    if (CollectionUtils.isEmpty(widget.getUserFilters())) {
      return;
    }

    filter.setUserFilters(widget.getUserFilters().stream().filter(Objects::nonNull)
        .filter(userFilter -> StringUtils.isNotBlank(userFilter.getField())).collect(Collectors.toList()));
  }
  
  public void prepareSaveTaskFilter(WidgetFilterModel filter, TaskDashboardWidget widget) {
    if (CollectionUtils.isEmpty(widget.getUserFilters())) {
      return;
    }

    filter.setUserFilters(widget.getUserFilters().stream().filter(Objects::nonNull)
        .filter(userFilter -> StringUtils.isNotBlank(userFilter.getField())).collect(Collectors.toList()));
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
    widget.setQuickSearchKeyword(userFilterCollection.getQuickSearchKeyword());
    widget.updateQuickSearchKeyword();
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
          ((TaskDashboardWidget) widget).getUserFilters()
              .addAll(Optional.ofNullable(userFilterOptions.getUserFilters()).orElse(new ArrayList<>()));
          break;
        case CASE:
          ((CaseDashboardWidget) widget).getUserFilters()
              .addAll(Optional.ofNullable(userFilterOptions.getUserFilters()).orElse(new ArrayList<>()));
          break;
        case PROCESS:
          var processWidget = (ProcessDashboardWidget) widget;
          if (ProcessWidgetMode.COMPACT_MODE == processWidget.getDisplayMode()) {
            widgetFilterableColumns.addAll(processWidget.getFilterableColumns());
          }
          break;
        default:
          break;
      }

      if (CollectionUtils.isNotEmpty(widgetFilterableColumns)) {
        for (var widgetColumn : widgetFilterableColumns) {
          widgetColumn.resetUserFilter();
          mergeUserFilterInput(userFilterOptions, widgetColumn);
        }
      }
    }
    widget.setUserDefinedFiltersCount(DashboardWidgetUtils.countDefinedUserFilter(widget));
  }

  private void mergeUserFilterInput(WidgetFilterModel userFilterOptions, ColumnModel column) {
    userFilterOptions.getFilterableColumns().stream()
        .filter(userSelected -> userSelected.getField() != null)
    .filter(userSelected -> userSelected.getField().equals(column.getField()) 
        && userSelected.getType( )== column.getType()).findFirst()
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
      String filterFrom = null;
      String filterTo = null;
      var filterList = new HashSet<String>();

      for (var selected : widget.getUserFilterCollection().getSelectedWidgetFilters()) {
        var selectedColumn = selected.getFilterableColumns().stream()
            .filter(compare -> compare.getField() != null && compare.getField().equals(widgetColumn.getField())
                && compare.getType() == widgetColumn.getType()).findFirst().orElse(null);
        if (selectedColumn != null) {
          if (StringUtils.isNotEmpty(selectedColumn.getUserFilter())) {
            userFilter = userFilter.concat(selectedColumn.getUserFilter());
          }

          if (CollectionUtils.isNotEmpty(selectedColumn.getUserFilterList())) {
            filterList.addAll(selectedColumn.getUserFilterList());
          }

          if (StringUtils.isNotEmpty(selectedColumn.getUserFilterFrom())) {
            filterFrom = getLessValue(widgetColumn.getFormat(), selectedColumn.getUserFilterFrom(), filterFrom);
          }
          if (StringUtils.isNotEmpty(selectedColumn.getUserFilterTo())) {
            filterTo = getGreaterValue(widgetColumn.getFormat(), selectedColumn.getUserFilterTo(), filterTo);
          }
        }
      }
      widgetColumn.setUserFilter(userFilter);
      widgetColumn.setUserFilterList(new ArrayList<>(filterList));
      widgetColumn.setUserFilterFrom(filterFrom);
      widgetColumn.setUserFilterTo(filterTo);
    }
  }

  public void consolidateSelectedFilters(DashboardWidget widget) {
    boolean isFilterModified = false;
    Map<String, FilterColumnModel> filterOptionMap = widget.getUserFilterCollection().getSelectedFilterOptionMap();
    List<ColumnModel> filterableColumns = new ArrayList<>();
    switch (widget.getType()) {
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
        String filterFrom = null;
        String filterTo = null;
        DashboardWidgetUtils.buildCustomColumn(ICustomFieldMeta.cases(), column, column.getField());
        var filterList = new HashSet<String>();
        if (filterOptionMap.containsKey(column.getField())) {
          columnData = filterOptionMap.get(column.getField());
          userFilter = columnData.getUserFilter();
          filterFrom = columnData.getUserFilterFrom();
          filterTo = columnData.getUserFilterTo();
          filterList.addAll(columnData.getUserFilterList());
        }
        if (StringUtils.isNotEmpty(column.getUserFilter())) {
          userFilter = userFilter.concat(column.getUserFilter());
        }
        if (CollectionUtils.isNotEmpty(column.getUserFilterList())) {
          filterList.addAll(column.getUserFilterList());
        }
        if (StringUtils.isNotEmpty(column.getUserFilterFrom())) {
          filterFrom = getLessValue(column.getFormat(), column.getUserFilterFrom(), filterFrom);
        }
        if (StringUtils.isNotEmpty(column.getUserFilterTo())) {
          filterTo = getGreaterValue(column.getFormat(), column.getUserFilterTo(), filterTo);
        }
        columnData.setUserFilter(userFilter);
        columnData.setUserFilterList(new ArrayList<>(filterList));
        columnData.setUserFilterFrom(filterFrom);
        columnData.setUserFilterTo(filterTo);
        filterOptionMap.put(column.getField(), columnData);
      }
    }
  }

  private static String buildWidgetKey(String widgetId, DashboardWidgetType widgetType) {
    return String.format(WIDGET_FILTER_KEY_PATTERN, widgetType, widgetId);
  }

  private String getLessValue(DashboardColumnFormat format, String selectedValueFrom, String value) {
    switch (format) {
      case NUMBER:
        Double selectedNumber = NumberUtils.createDouble(selectedValueFrom);
        Double valueNumber = NumberUtils.createDouble(value);
        if (valueNumber == null || (selectedNumber != null && selectedNumber < valueNumber)) {
          return selectedValueFrom;
        }
        break;
      case TIMESTAMP:
        try {
          var selectedDate = Dates.parse(selectedValueFrom);
          if (value == null || selectedDate.before(Dates.parse(value))) {
            return selectedValueFrom;
          }
        } catch (PortalException e) {
          return null;
        }
        break;
      default:
        return value;
    }
    return value;
  }

  private String getGreaterValue(DashboardColumnFormat format, String selectedValueFrom, String value) {
    switch (format) {
      case NUMBER:
        Double selectedNumber = NumberUtils.createDouble(selectedValueFrom);
        Double valueNumber = NumberUtils.createDouble(value);
        if (valueNumber == null || (selectedNumber != null && selectedNumber > valueNumber)) {
          return selectedValueFrom;
        }
        break;
      case TIMESTAMP:
        try {
          var selectedDate = Dates.parse(selectedValueFrom);
          if (value == null || selectedDate.after(Dates.parse(value))) {
            return selectedValueFrom;
          }
        } catch (PortalException e) {
          return null;
        }
        break;
      default:
        return value;
    }
    return value;
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

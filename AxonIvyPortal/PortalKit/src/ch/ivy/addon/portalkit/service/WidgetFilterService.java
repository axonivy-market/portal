package ch.ivy.addon.portalkit.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bean.WidgetFilterHelperBean;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.FilterColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.UserFilterCollection;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.environment.Ivy;

public class WidgetFilterService extends JsonConfigurationService<WidgetFilterModel> {

  public static final String WIDGET_FILTER_KEY = "UserSelectedFiltersSet";
  public static final String WIDGET_FILTER_KEY_PATTERN = "%s_%s_%s";
  private static final String CATEGORY_FILTER = "category";
  private static final String PROCESS_NAME_FILTER = "processName";
  private static final String PROCESS_TYPE_FILTER = "processType";
  
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
    switch (widget.getType()) {
      case TASK:
        var taskWidget = (TaskDashboardWidget) widget;
        filter.addFilterableColumns(taskWidget.getFilterableColumns());
        filter.setUserFilterCategories(taskWidget.getUserFilterCategories());
        break;
      case CASE:
        var caseWidget = (CaseDashboardWidget) widget;
        filter.addFilterableColumns(caseWidget.getFilterableColumns());
        filter.setUserFilterCategories(caseWidget.getUserFilterCategories());
        break;
      case PROCESS:
        var processWidget = (ProcessDashboardWidget) widget;
        filter.setProcessUserFilter(processWidget.getUserFilter());
        break;
      default:
        break;
    }
    // Update savedFilter for HelperBean
    ((WidgetFilterHelperBean) ManagedBeans.get("widgetFilterHelperBean")).setSaveFilter(filter);
    return filter;
  }

  public void applyUserFilterFromSession(DashboardWidget widget) throws ParseException {
    var selectedFilterObject = Ivy.session().getAttribute(buildWidgetKey(widget.getId(), widget.getType()));
    var userFilterCollection = BusinessEntityConverter.jsonValueToEntity(String.valueOf(selectedFilterObject), UserFilterCollection.class);
    if (userFilterCollection == null) {
      return;
    }
    var storedFilterOptions = userFilterCollection.getLatestFilterOption();
    widget.setUserFilterCollection(userFilterCollection);
    updateFilterOptionsData(widget, storedFilterOptions);
  }

  public void buildProcessFilters(ProcessDashboardWidget processWidget) {
    var processName = "";
    var processType = new HashSet<ProcessType>();
    var categories = new HashSet<String>();
    for (var selectedFilter : processWidget.getUserFilterCollection().getSelectedWidgetFilters()) {
      var userFilter = selectedFilter.getProcessUserFilter();
      if (StringUtils.isNotEmpty(userFilter.getProcessName())) {
        processName = processName.concat(userFilter.getProcessName());
      }

      if (CollectionUtils.isNotEmpty(userFilter.getProcessTypes())) {
        processType.addAll(userFilter.getProcessTypes());
      }

      if (CollectionUtils.isNotEmpty(userFilter.getCategories())) {
        categories.addAll(userFilter.getCategories());
      }
    }
    processWidget.getUserFilter().setProcessName(processName);
    processWidget.getUserFilter().setProcessTypes(new ArrayList<>(processType));
    processWidget.getUserFilter().setCategories(new ArrayList<>(categories));
    processWidget.countDefinedUserFilter();
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

  public void updateFilterOptionsData(DashboardWidget widget, WidgetFilterModel userFilterOptions)
      throws ParseException {
    updateSelectedSavedFiltersByUser(widget);
    switch (widget.getType()) {
      case TASK:
        var taskWidget = (TaskDashboardWidget) widget;
        if (userFilterOptions != null) {
          for (var widgetColumn : ((TaskDashboardWidget) widget).getFilterableColumns()) {
            widgetColumn.resetUserFilter();
            mergeUserFilterInput(userFilterOptions, widgetColumn);
          }
          taskWidget.setUserFilterCategories(userFilterOptions.getUserFilterCategories());
        } else {
          taskWidget.setUserFilterCategories(new ArrayList<>());
        }
        taskWidget.setUserDefinedFiltersCount(TaskDashboardWidget.countDefinedUserFilter(taskWidget));

        break;
      case CASE:
        var caseWidget = (CaseDashboardWidget) widget;
        if (userFilterOptions != null) {
          for (var widgetColumn : ((CaseDashboardWidget) widget).getFilterableColumns()) {
            widgetColumn.resetUserFilter();
            mergeUserFilterInput(userFilterOptions, widgetColumn);
          }
          caseWidget.setUserFilterCategories(userFilterOptions.getUserFilterCategories());
        } else {
          caseWidget.setUserFilterCategories(new ArrayList<>());
        }
        caseWidget.setUserDefinedFiltersCount(CaseDashboardWidget.countDefinedUserFilter(caseWidget));

        break;
      case PROCESS:
        if (userFilterOptions != null) {
          var processWidget = (ProcessDashboardWidget) widget;
          if (ProcessWidgetMode.COMPACT_MODE == processWidget.getDisplayMode()) {
            processWidget.setUserFilter(userFilterOptions.getProcessUserFilter());
            processWidget.countDefinedUserFilter();
          }
        }
        break;
      default:
        break;
    }
  }

  private void mergeUserFilterInput(WidgetFilterModel userFilterOptions, ColumnModel widgetColumn) {
    userFilterOptions.getFilterableColumns().stream()
        .filter(userSelected -> userSelected.getField().equals(widgetColumn.getField())).findFirst()
        .ifPresent(selectedColumn -> {
          widgetColumn.setUserFilter(selectedColumn.getUserFilter());
          widgetColumn.setUserFilterList(selectedColumn.getUserFilterList());
          widgetColumn.setUserFilterFrom(selectedColumn.getUserFilterFrom());
          widgetColumn.setUserFilterTo(selectedColumn.getUserFilterTo());
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

  public void buildFilterOptions(DashboardWidget widget, List<ColumnModel> filterableColumns,
      List<String> userFilterCategories) throws ParseException {
    var userCategories = new HashSet<String>();
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
        if (CollectionUtils.isNotEmpty(selected.getUserFilterCategories())) {
          userCategories.addAll(selected.getUserFilterCategories());
        }
      }
      widgetColumn.setUserFilter(userFilter);
      widgetColumn.setUserFilterList(new ArrayList<>(filterList));
      widgetColumn.setUserFilterFrom(formatDateToString(filterFrom));
      widgetColumn.setUserFilterTo(formatDateToString(filterTo));
    }
    userFilterCategories.addAll(new ArrayList<>(userCategories));
  }

  public void consolidateSelectedFilters(DashboardWidget widget) {
    boolean isFilterModified = false;
    Map<String, FilterColumnModel> filterOptionMap = widget.getUserFilterCollection().getSelectedFilterOptionMap();
    switch (widget.getType()) {
      case TASK:
      case CASE:
        List<String> userCategories = new ArrayList<String>();
        List<ColumnModel> filterableColumns = new ArrayList<>();
        if (DashboardWidgetType.CASE == widget.getType()) {
          filterableColumns = ((CaseDashboardWidget) widget).getFilterableColumns();
          userCategories = ((CaseDashboardWidget) widget).getUserFilterCategories();
        } else {
          filterableColumns = ((TaskDashboardWidget) widget).getFilterableColumns();
          userCategories = ((TaskDashboardWidget) widget).getUserFilterCategories();
        }

        for (var column : filterableColumns) {
          if (filterOptionMap.containsKey(column.getField())) {
            var columnData = filterOptionMap.get(column.getField());
            if (!StringUtils.equals(column.getUserFilter(), columnData.getUserFilter())
                || !CollectionUtils.containsAll(column.getUserFilterList(), columnData.getUserFilterList())
                || !StringUtils.equals(column.getUserFilterFrom(), columnData.getUserFilterFrom())
                || !StringUtils.equals(column.getUserFilterTo(), columnData.getUserFilterTo())) {
              isFilterModified = true;
              break;
            }
          }
        }

        if (!isFilterModified && filterOptionMap.containsKey(CATEGORY_FILTER)) {
          var category = filterOptionMap.get(CATEGORY_FILTER);
          if (!CollectionUtils.containsAll(userCategories, category.getUserFilterList())) {
            isFilterModified = true;
          }
        }
        break;
      case PROCESS:
        var userFilter = ((ProcessDashboardWidget) widget).getUserFilter();
        if (filterOptionMap.containsKey(PROCESS_NAME_FILTER)
            && !StringUtils.equals(userFilter.getProcessName(), filterOptionMap.get(PROCESS_NAME_FILTER).getUserFilter())) {
          isFilterModified = true;
          break;
        }
        if (filterOptionMap.containsKey(PROCESS_TYPE_FILTER)) {
          var userProcessTypes = userFilter.getProcessTypes()
                .stream().map(ProcessType::getValue)
                .collect(Collectors.toList());
          var storedProcessTypes = filterOptionMap.get(PROCESS_TYPE_FILTER).getUserFilterList();
          if (!CollectionUtils.containsAll(userProcessTypes, storedProcessTypes)) {
            isFilterModified = true;
            break;
          }
        }
        if (filterOptionMap.containsKey(CATEGORY_FILTER)
            && !CollectionUtils.containsAll(userFilter.getCategories(), filterOptionMap.get(CATEGORY_FILTER).getUserFilterList())) {
          isFilterModified = true;
        }
        break;
      default:
        break;
    }

    if (isFilterModified) {
      widget.getUserFilterCollection().setSelectedWidgetFilterIds(new ArrayList<>());
      widget.getUserFilterCollection().setSelectedWidgetFilters(new ArrayList<>());
      widget.getUserFilterCollection().setSelectedFilterOptionMap(new HashMap<>());
    }
  }

  public void updateUserFilterOptionMap(DashboardWidget widget) throws ParseException {
    Map<String, FilterColumnModel> filterOptionMap = widget.getUserFilterCollection().getSelectedFilterOptionMap();
    filterOptionMap.clear();
    Set<String> categories = new HashSet<>();
    for (var savedFilter : widget.getUserFilterCollection().getSelectedWidgetFilters()) {
      switch (widget.getType()) {
        case TASK:
        case CASE:
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
          if (CollectionUtils.isNotEmpty(savedFilter.getUserFilterCategories())) {
            categories.addAll(savedFilter.getUserFilterCategories());
          }
          break;
        case PROCESS:
          // process name
          if (StringUtils.isNotEmpty(savedFilter.getProcessUserFilter().getProcessName())) {
            var nameFilter = new FilterColumnModel();
            var procesName = savedFilter.getProcessUserFilter().getProcessName();
            if (filterOptionMap.containsKey(PROCESS_NAME_FILTER)) {
              procesName = procesName.concat(filterOptionMap.get(PROCESS_NAME_FILTER).getUserFilter());
            }
            nameFilter.setUserFilter(procesName);
            filterOptionMap.put(PROCESS_NAME_FILTER, nameFilter);
          }
          // process type
          if (CollectionUtils.isNotEmpty(savedFilter.getProcessUserFilter().getProcessTypes())) {
            var processTypeFilter = new FilterColumnModel();
            Set<String> procesTypes = new HashSet<>();
            procesTypes.addAll(savedFilter.getProcessUserFilter().getProcessTypes()
                  .stream().map(ProcessType::getValue)
                  .collect(Collectors.toSet()));
            if (filterOptionMap.containsKey(PROCESS_TYPE_FILTER)) {
              procesTypes.addAll(filterOptionMap.get(PROCESS_TYPE_FILTER).getUserFilterList());
            }
            processTypeFilter.setUserFilterList(new ArrayList<>(procesTypes));
            filterOptionMap.put(PROCESS_TYPE_FILTER, processTypeFilter);
          }
          // process category
          if (CollectionUtils.isNotEmpty(savedFilter.getProcessUserFilter().getCategories())) {
            categories.addAll(savedFilter.getProcessUserFilter().getCategories());
          }
          break;
        default:
          break;
      }
    }
    var categoryFilter = new FilterColumnModel();
    categoryFilter.setUserFilterList(new ArrayList<>(categories));
    filterOptionMap.put(CATEGORY_FILTER, categoryFilter);
  }

  private static String buildWidgetKey(String widgetId, DashboardWidgetType widgetType) {
    return String.format(WIDGET_FILTER_KEY_PATTERN, widgetType, widgetId, WIDGET_FILTER_KEY);
  }

  private String formatDateToString(Date date) {
    return Objects.isNull(date) ? null : Dates.format(date);
  }

  private Date getLessDate(String selectedDateFrom, Date date) throws ParseException {
    var selectedDate = Dates.parse(selectedDateFrom);
    if (date == null || selectedDate.before(date)) {
      return selectedDate;
    }
    return date;
  }

  private Date getGreaterDate(String selectedDateFrom, Date date) throws ParseException {
    var selectedDate = Dates.parse(selectedDateFrom);
    if (date == null || selectedDate.after(date)) {
      return selectedDate;
    }
    return date;
  }
}

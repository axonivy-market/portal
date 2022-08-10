package ch.ivy.addon.portalkit.util;

import static ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix.CMS;
import static ch.ivy.addon.portalkit.constant.DashboardConstants.MAX_NOTI_FILTERS;
import static ch.ivy.addon.portalkit.constant.DashboardConstants.MAX_NOTI_PATTERN;
import static ch.ivy.addon.portalkit.constant.DashboardConstants.NEW_WIDGET_STYLE_CLASS;
import static ch.ivy.addon.portalkit.constant.DashboardConstants.WIDGET_ID_PATTERN;
import static ch.ivy.addon.portalkit.enums.DashboardColumnFormat.NUMBER;
import static ch.ivy.addon.portalkit.enums.DashboardColumnFormat.STRING;
import static ch.ivy.addon.portalkit.enums.DashboardColumnFormat.TEXT;
import static ch.ivy.addon.portalkit.enums.DashboardColumnFormat.TIMESTAMP;
import static ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn.CATEGORY;
import static ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn.ID;
import static ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn.PRIORITY;
import static ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn.RESPONSIBLE;
import static ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn.START;
import static ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn.STATE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bean.CompactDashboardProcessBean;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.AbstractColumn;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.StatisticDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.dto.dashboard.process.ProcessColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

public class DashboardWidgetUtils {

  public static DashboardWidget buildWidgetColumns(DashboardWidget widget) {
    switch (widget.getType()) {
      case TASK:
        buildTaskColumns((TaskDashboardWidget) widget);
        break;
      case CASE:
        buildCaseColumns((CaseDashboardWidget) widget);
        break;
      case PROCESS:
        ProcessDashboardWidget processWidget = (ProcessDashboardWidget) widget;
        if (ProcessWidgetMode.COMPACT_MODE == processWidget.getDisplayMode()) {
          CompactProcessDashboardWidget compactProcessDashboardWidget = (CompactProcessDashboardWidget) widget;
          compactProcessDashboardWidget.buildFilterableColumns(initProcessFilterableColumns());
          buildProcessColumns(compactProcessDashboardWidget);
        }
        break;
      default:
        break;
    }
    return widget;
  }

  public static ProcessDashboardWidget buildProcessColumns(CompactProcessDashboardWidget processWidget) {
    for (var filter : processWidget.getFilterableColumns()) {
      if (DashboardStandardProcessColumn.CATEGORY.getField().equalsIgnoreCase(filter.getField())) {
        filter.setFilterList(processWidget.getCategories());
      }
    }
    return processWidget;
  }

  public static TaskDashboardWidget buildTaskColumns(TaskDashboardWidget widget) {
    List<TaskColumnModel> columns = widget.getColumns();
    for (int i = 0; i < columns.size(); i++) {
      TaskColumnModel column = columns.get(i);
      String field = column.getField();
      if (equals(DashboardStandardTaskColumn.START, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.StartColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.PRIORITY, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.PriorityColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.ID, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.IdColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.NAME, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.NameColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.DESCRIPTION, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.DescriptionColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.RESPONSIBLE, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.ResponsibleColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.STATE, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.StateColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.CREATED, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.CreatedDateColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.EXPIRY, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.ExpiryDateColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.CATEGORY, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.CategoryColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.APPLICATION, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.ApplicationColumnModel.class);
      } else if (equals(DashboardStandardTaskColumn.ACTIONS, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.ActionsColumnModel.class);
      } else {
        column.setType(DashboardColumnType.CUSTOM);
      }

      column.initDefaultValue();
      if (column.getType() == DashboardColumnType.CUSTOM) {
        buildCustomColumn(ICustomFieldMeta.tasks(), column, field);
      }
      columns.set(i, column);
    }
    widget.buildFilterableColumns(columns);
    return widget;
  }

  private static boolean equals(DashboardStandardTaskColumn taskColumn,String field) {
    return taskColumn.getField().equalsIgnoreCase(field);
  }
  
  private static boolean equals(DashboardStandardCaseColumn caseColumn,String field) {
    return caseColumn.getField().equalsIgnoreCase(field);
  }

  private static void buildCustomColumn(Set<ICustomFieldMeta> customFieldMetas, AbstractColumn column,
      String field) {
    var fieldMeta = customFieldMetas.stream().filter(meta -> meta.name().equals(field)).findFirst();
    if (fieldMeta.isPresent()) {
      column.setHeader(fieldMeta.get().label());
      column.setFormat(DashboardColumnFormat.valueOf(fieldMeta.get().type().name()));
    } else if (StringUtils.isBlank(column.getHeader())) {
      column.setHeader(field);
    }
  }

  public static List<ColumnModel> buildTaskFilterableColumns(List<TaskColumnModel> columns) {
    if (CollectionUtils.isEmpty(columns)) {
      return new ArrayList<>();
    }
    return columns.stream().filter(Objects::nonNull)
        .filter(col -> !StringUtils.equalsIgnoreCase(col.getField(), START.toString())
                  && !StringUtils.equalsIgnoreCase(col.getField(), ID.toString()))
        .collect(Collectors.toList());
  }

  public static CaseDashboardWidget buildCaseColumns(CaseDashboardWidget widget) {
    List<CaseColumnModel> columns = widget.getColumns();
    for (int i = 0; i < columns.size(); i++) {
      CaseColumnModel column = columns.get(i);
      String field = column.getField();
      if (equals(DashboardStandardCaseColumn.ID, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.IdColumnModel.class);
      } else if (equals(DashboardStandardCaseColumn.NAME, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.NameColumnModel.class);
      } else if (equals(DashboardStandardCaseColumn.DESCRIPTION, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.DescriptionColumnModel.class);
      } else if (equals(DashboardStandardCaseColumn.CREATOR, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CreatorColumnModel.class);
      } else if (equals(DashboardStandardCaseColumn.STATE, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.StateColumnModel.class);
      } else if (equals(DashboardStandardCaseColumn.CREATED, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CreatedDateColumnModel.class);
      } else if (equals(DashboardStandardCaseColumn.FINISHED, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.FinishedDateColumnModel.class);
      } else if (equals(DashboardStandardCaseColumn.OWNER, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.OwnerColumnModel.class);
      } else if (equals(DashboardStandardCaseColumn.CATEGORY, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CategoryColumnModel.class);
      } else if (equals(DashboardStandardCaseColumn.APPLICATION, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.ApplicationColumnModel.class);
      } else if (equals(DashboardStandardCaseColumn.ACTIONS, field)) {
        column = BusinessEntityConverter.convertValue(column, ch.ivy.addon.portalkit.dto.dashboard.casecolumn.ActionsColumnModel.class);
      } else {
        column.setType(DashboardColumnType.CUSTOM);
      }

      column.initDefaultValue();
      if (column.getType() == DashboardColumnType.CUSTOM) {
        buildCustomColumn(ICustomFieldMeta.cases(), column, field);
      }
      columns.set(i, column);
    }
    widget.buildFilterableColumns(columns);
    return widget;
  }

  public static List<ColumnModel> buildCaseFilterableColumns(List<CaseColumnModel> caseColumns) {
    List<ColumnModel> filterableColumns = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(caseColumns)) {
      filterableColumns = caseColumns.stream().filter(Objects::nonNull)
          .filter(col -> !StringUtils.equalsIgnoreCase(col.getField(), DashboardStandardCaseColumn.ID.toString()))
          .collect(Collectors.toList());
    }
    var enableCaseOwner = GlobalSettingService.getInstance().isCaseOwnerEnabled();
    if (!enableCaseOwner) {
      filterableColumns.removeIf(col -> StringUtils.equalsIgnoreCase(col.getField(), DashboardStandardCaseColumn.OWNER.toString()));
    }
    return filterableColumns;
  }

  public static DashboardWidget simplifyWidgetColumnData(DashboardWidget widget) {
    List<String> deprecatedFields = new ArrayList<>();
    switch (widget.getType()) {
      case TASK:
        var taskCustomFieldMetas = ICustomFieldMeta.tasks();
        List<TaskColumnModel> columns = ((TaskDashboardWidget) widget).getColumns();
        columns.forEach(column -> {
          simplifyColumnData(column, taskCustomFieldMetas, deprecatedFields);
        });
        deprecatedFields.forEach(field -> {
          columns.removeIf(column -> column.getField().equals(field));
        });
        break;
      case CASE:
        var caseCustomFieldMetas = ICustomFieldMeta.cases();
        List<CaseColumnModel> caseColumns = ((CaseDashboardWidget) widget).getColumns();
        caseColumns.forEach(column -> {
          simplifyColumnData(column, caseCustomFieldMetas, deprecatedFields);
        });
        deprecatedFields.forEach(field -> {
          caseColumns.removeIf(column -> column.getField().equals(field));
        });
        break;
      default:
        break;
    }
    return widget;
  }

  private static void simplifyColumnData(AbstractColumn column, Set<ICustomFieldMeta> customFieldMetas,
      List<String> deprecatedFields) {
    if (column == null) {
      return;
    }

    if (column.getType() == DashboardColumnType.STANDARD) {
      column.setType(null);
      if (StringUtils.equals(column.getHeader(), CMS + column.getDefaultHeaderCMS())) {
        column.setHeader(null);
      }
      if (StringUtils.equals(column.getDefaultStyleClass(), column.getStyleClass())) {
        column.setStyleClass(null);
      }
      if (StringUtils.equals(column.getDefaultStyle(), column.getStyle())) {
        column.setStyle(null);
      }
      if (column.getDefaultFormat() == column.getFormat()) {
        column.setFormat(null);
      }
      if (column.getDefaultSortable() == column.getSortable()) {
        column.setSortable(null);
      }
    } else {
      column.setHeader(null);
      var foundCustomField = customFieldMetas.stream().anyMatch(meta -> meta.name().equals(column.getField()));
      if (!foundCustomField) {
        deprecatedFields.add(column.getField());
      }
    }
    if (DashboardColumnFormat.STRING == column.getFormat()) {
      column.setFormat(null);
    }
    if (BooleanUtils.isTrue(column.getVisible())) {
      column.setVisible(null);
    }
    if (BooleanUtils.isTrue(column.getSortable())) {
      column.setSortable(null);
    }
  }

  public static void removeStyleNewWidget(DashboardWidget widget) {
    if (StringUtils.contains(widget.getLayout().getStyleClass(), NEW_WIDGET_STYLE_CLASS)) {
      var styleClass = widget.getLayout().getStyleClass();
      widget.getLayout().setStyleClass(styleClass.replace(NEW_WIDGET_STYLE_CLASS, ""));
    }
  }

  public static List<WidgetLayout> getWidgetLayoutFromRequest(Map<String, String> requestParamMap) {
    var widgetLayouts = new ArrayList<WidgetLayout>();
    var nodes = Optional.ofNullable(requestParamMap.get("nodes"));
    nodes.ifPresent(jsonNodes -> {
      widgetLayouts.addAll(Arrays.asList(BusinessEntityConverter
          .jsonValueToEntity(jsonNodes, WidgetLayout[].class)));
    });
    return widgetLayouts;
  }

  public static boolean hasPredefinedFilter(DashboardWidget widget) {
    boolean hasPredefinedFilter = false;
    switch (widget.getType()) {
      case TASK:
        hasPredefinedFilter = hasPredefinedTaskFilter((TaskDashboardWidget) widget);
        break;
      case CASE:
        hasPredefinedFilter = hasPredefinedCaseFilter((CaseDashboardWidget) widget);
        break;
      default:
        break;
    }
    return hasPredefinedFilter;
  }

  public static boolean hasPredefinedCaseFilter(CaseDashboardWidget widget) {
    boolean hasPredefinedFilter = false;
    List<ColumnModel> filterableColumns = widget.getFilterableColumns();
    if (CollectionUtils.isEmpty(filterableColumns)) {
      return hasPredefinedFilter;
    }
    for (ColumnModel col : filterableColumns) {
      if (hasPredefinedFilter) {
        break;
      }
      if ((DashboardStandardCaseColumn.STATE.getField().equalsIgnoreCase(col.getField())
          || DashboardStandardCaseColumn.CREATOR.getField().equalsIgnoreCase(col.getField())
          || DashboardStandardCaseColumn.OWNER.getField().equalsIgnoreCase(col.getField())
          || DashboardStandardCaseColumn.CATEGORY.getField().equalsIgnoreCase(col.getField()))
          && CollectionUtils.isNotEmpty(col.getFilterList())) {
        hasPredefinedFilter = true;
      } else {
        hasPredefinedFilter = hasPredefinedCustomField(col);
      }
    }
    return hasPredefinedFilter;
  }

  public static boolean hasPredefinedTaskFilter(TaskDashboardWidget widget) {
    boolean hasPredefinedFilter = false;
    List<ColumnModel> filterableColumns = widget.getFilterableColumns();
    if (CollectionUtils.isEmpty(filterableColumns)) {
      return hasPredefinedFilter;
    }
    for (ColumnModel col : filterableColumns) {
      if (hasPredefinedFilter) {
        break;
      }
      if ((PRIORITY.getField().equalsIgnoreCase(col.getField()) || STATE.getField().equalsIgnoreCase(col.getField())
          || RESPONSIBLE.getField().equalsIgnoreCase(col.getField())
          || CATEGORY.getField().equalsIgnoreCase(col.getField()))
          && !CollectionUtils.isEmpty(col.getFilterList())) {
        hasPredefinedFilter = true;
      } else {
        hasPredefinedFilter = hasPredefinedCustomField(col);
      }
    }
    return hasPredefinedFilter;
  }

  public static boolean hasPredefinedCustomField(ColumnModel col) {
    if (((TEXT == col.getFormat() || STRING == col.getFormat()) && !(CollectionUtils.isEmpty(col.getFilterList()) && StringUtils.isBlank(col.getFilter())))
        || (NUMBER == col.getFormat() && !(StringUtils.isBlank(col.getFilterFrom()) && StringUtils.isBlank(col.getFilterTo())))
        || (TIMESTAMP == col.getFormat() && !(col.getDateFilterFrom() == null && col.getDateFilterTo() == null))) {
      return true;
    }
    return false;
  }

  public static Optional<String> countDefinedUserFilter(DashboardWidget widget) {
    List<ColumnModel> filterableColumns = new ArrayList<>();
    switch (widget.getType()) {
      case CASE:
        filterableColumns = ((CaseDashboardWidget) widget).getFilterableColumns();
        break;
      case TASK:
        filterableColumns = ((TaskDashboardWidget) widget).getFilterableColumns();
        break;
      case PROCESS:
        filterableColumns = ((CompactProcessDashboardWidget) widget).getFilterableColumns();
        break;
      default:
        break;
    }
    if (CollectionUtils.isEmpty(filterableColumns)) {
      return Optional.empty();
    }

    int numberOfFilters = 0;
    for (ColumnModel col : filterableColumns) {
      if (StringUtils.isNotEmpty(col.getUserFilter()) || CollectionUtils.isNotEmpty(col.getUserFilterList())
          || StringUtils.isNotEmpty(col.getUserFilterFrom()) || col.getUserDateFilterFrom() != null
          || StringUtils.isNotEmpty(col.getUserFilterTo()) || col.getUserDateFilterTo() != null) {
        numberOfFilters++;
      }
      if (numberOfFilters > MAX_NOTI_FILTERS) {
        break;
      }
    }
    if (numberOfFilters == 0) {
      return Optional.empty();
    }

    return Optional.of(numberOfFilters <= MAX_NOTI_FILTERS ? String.valueOf(numberOfFilters)
        : String.format(MAX_NOTI_PATTERN, MAX_NOTI_FILTERS));
  }

  public static DashboardWidget buildDefaultWidget(String id, String name, DashboardWidgetType type) {
    DashboardWidget widget = null;
    switch (type) {
      case TASK:
        widget = buildDefaultTaskWidget(id, name);
        break;
      case CASE:
        widget = buildDefaultCaseWidget(id, name);
        break;
      case PROCESS:
        widget = buildDefaultProcessWidget(id, name);
        break;
      case STATISTIC:
        widget = buildDefaultStatisticWidget(id, name);
        break;
      default:
        break;
    }
    return widget;
  }


  private static DashboardWidget buildDefaultStatisticWidget(String id, String name) {
    var widget = new StatisticDashboardWidget();
    widget.setId(id);
    widget.setName(name);
    var layout = new WidgetLayout();
    layout.setWidth(5);
    layout.setHeight(5);
    layout.setAxisX(0);
    layout.setAxisY(0);
    widget.setLayout(layout);
    widget.setAutoPosition(true);
    return widget;
  }

  public static ProcessDashboardWidget buildDefaultProcessWidget(String id, String name) {
    CompactProcessDashboardWidget widget = new CompactProcessDashboardWidget();
    widget.setId(id);
    widget.setName(name);
    widget.setLayout(new WidgetLayout());
    widget.getLayout().setWidth(-1);
    widget.getLayout().setHeight(-1);
    widget.getLayout().setAxisX(0);
    widget.getLayout().setAxisY(0);
    widget.setAutoPosition(true);
    widget.setSelectedAllProcess(true);
    widget.buildFilterableColumns(initProcessFilterableColumns());
    return widget;
  }

  public static List<ProcessColumnModel> initProcessFilterableColumns() {
    List<ProcessColumnModel> columnModels = new ArrayList<>();
    for (var col : DashboardStandardProcessColumn.values()) {
      if (DashboardStandardProcessColumn.ID == col) {
        continue;
      }
      var columnModel = new ProcessColumnModel();
      columnModel.setField(col.getField());
      if (DashboardStandardProcessColumn.NAME == col) {
        columnModel = new ch.ivy.addon.portalkit.dto.dashboard.process.NameColumnModel();
      } else if (DashboardStandardProcessColumn.TYPE == col) {
        columnModel = new ch.ivy.addon.portalkit.dto.dashboard.process.TypeColumnModel();
      } else if (DashboardStandardProcessColumn.CATEGORY == col) {
        columnModel = new ch.ivy.addon.portalkit.dto.dashboard.process.CategoryColumnModel();
      } else if (DashboardStandardProcessColumn.APPLICATION == col) {
        columnModel = new ch.ivy.addon.portalkit.dto.dashboard.process.ApplicationColumnModel();
      }
      
      columnModel.initDefaultValue();
      columnModels.add(columnModel);
    }
    return columnModels;
  }

  public static TaskDashboardWidget buildDefaultTaskWidget(String id, String name) {
    TaskDashboardWidget widget = new TaskDashboardWidget();
    widget.setId(id);
    widget.setName(name);

    WidgetLayout layout = new WidgetLayout();
    layout.setWidth(12);
    layout.setHeight(8);
    widget.setLayout(layout);

    widget.setAutoPosition(true);
    widget.setSortField(TaskSortField.ID.toString());
    widget.setSortDescending(true);
    widget.setColumns(initStandardTaskColumns());
    return buildTaskColumns(widget);
  }

  public static List<TaskColumnModel> initStandardTaskColumns() {
    List<TaskColumnModel> columnModels = new ArrayList<>();
    for (var col : DashboardStandardTaskColumn.values()) {
      TaskColumnModel columnModel = new TaskColumnModel();
      columnModel.setField(col.getField());
      columnModels.add(columnModel);
    }
    return columnModels;
  }

  public static void resetUserFilterOnColumns(List<? extends ColumnModel> columns) {
    if (CollectionUtils.isEmpty(columns)) {
      return;
    }
    for (var column : columns) {
      column.setUserFilter(StringUtils.EMPTY);
      column.setUserFilterList(new ArrayList<>());
      column.setUserFilterFrom(StringUtils.EMPTY);
      column.setUserFilterTo(StringUtils.EMPTY);
      column.setUserDateFilterFrom(null);
      column.setUserDateFilterTo(null);
    }
  }

  public static CaseDashboardWidget buildDefaultCaseWidget(String id, String name) {
    CaseDashboardWidget widget = new CaseDashboardWidget();
    widget.setId(id);
    widget.setName(name);

    WidgetLayout layout = new WidgetLayout();
    layout.setWidth(9);
    layout.setHeight(8);
    widget.setLayout(layout);

    widget.setAutoPosition(true);
    widget.setSortField(CaseSortField.ID.toString());
    widget.setSortDescending(true);
    widget.setColumns(initStandardColumns());
    return DashboardWidgetUtils.buildCaseColumns(widget);
  }

  public static List<CaseColumnModel> initStandardColumns() {
    List<CaseColumnModel> columnModels = new ArrayList<>();
    for (DashboardStandardCaseColumn col : DashboardStandardCaseColumn.values()) {
      CaseColumnModel columnModel = new CaseColumnModel();
      columnModel.setField(col.getField());
      columnModels.add(columnModel);
    }
    return columnModels;
  }

  public static void loadProcessesOfWidget(ProcessDashboardWidget widget) {
    if (widget.getDisplayMode() == ProcessWidgetMode.COMPACT_MODE) {
      loadCompactProcesses((CompactProcessDashboardWidget) widget);
    } else {
      loadProcessByPath((SingleProcessDashboardWidget) widget);
    }
  }

  private static void loadProcessByPath(SingleProcessDashboardWidget processWidget) {
    var processPath = processWidget.getProcessPath();
    if (processPath == null || processWidget.getProcess() != null) {
      return;
    }
    for (DashboardProcess process : getAllPortalProcesses()) {
      if (process.getId() != null && process.getId().contains(processPath)) {
        updateProcessStartIdForCombined(processWidget, process);
        processWidget.setProcess(process);
        break;
      }
    }
  }

  private static void updateProcessStartIdForCombined(ProcessDashboardWidget processWidget, DashboardProcess process) {
    if (processWidget.getDisplayMode() == ProcessWidgetMode.COMBINED_MODE && process.getProcessStartId() == null) {
      Ivy.session().getStartableProcessStarts().stream()
          .filter(processStart -> processStart.getLink().getRelative().equals(process.getStartLink())).findFirst()
          .ifPresent(optional -> {
            process.setProcessStartId(optional.getId());
          });
    }
  }

  private static void loadCompactProcesses(CompactProcessDashboardWidget processWidget) {
    List<DashboardProcess> processes = processWidget.isPreview() ? getCompactProcessesForPreview(processWidget) : getCompactProcessesOfWidget(processWidget);
    processWidget.setDisplayProcesses(processes);
    processWidget.setOriginalDisplayProcesses(processes);
    processWidget.filterProcessesByUser();
  }

  private static List<DashboardProcess> getCompactProcessesForPreview(CompactProcessDashboardWidget processWidget) {
    List<DashboardProcess> processes;
    if (processWidget.isSelectedAllProcess()) {
      processes = getAllPortalProcesses();
    } 
    if (CollectionUtils.isNotEmpty(processWidget.getProcesses())) {
      processes = filterProcessesByProcesses(processWidget.getProcesses());
      processWidget.setProcesses(processes);
    } else {
      processes = filterProcessesByCategories(processWidget.getCategories());
    }

    return processes;
  }

  private static List<DashboardProcess> getCompactProcessesOfWidget(CompactProcessDashboardWidget processWidget) {
    List<DashboardProcess> processes;
    if (processWidget.isSelectedAllProcess()) {
      processes = getAllPortalProcesses();
    } 
    if (CollectionUtils.isNotEmpty(processWidget.getProcessPaths())) {
      processes = filterProcessesByProcessPaths(processWidget.getProcessPaths());
      processWidget.setProcesses(processes);
    } else {
      processes = filterProcessesByCategories(processWidget.getCategories());
    }

    return processes;
  }

  public static List<DashboardProcess> getAllPortalProcesses() {
    CompactDashboardProcessBean dashboardProcessBean = ManagedBeans.get("compactDashboardProcessBean");
    return dashboardProcessBean == null ? new ArrayList<>() : dashboardProcessBean.getAllPortalProcesses();
  }

  private static List<DashboardProcess> filterProcessesByProcesses(List<DashboardProcess> selectedProcesses) {
    List<DashboardProcess> processes = new ArrayList<>();
    for (DashboardProcess selectedProcess : selectedProcesses) {
      processes.addAll(getAllPortalProcesses().stream()
          .filter(process -> process.getId().equalsIgnoreCase(selectedProcess.getId()))
          .collect(Collectors.toList()));
    }

    return processes;
  }

  private static List<DashboardProcess> filterProcessesByProcessPaths(List<String> processPaths) {
    List<DashboardProcess> processes = new ArrayList<>();
    for (String processPath : processPaths) {
      processes.addAll(getAllPortalProcesses().stream()
          .filter(process -> process.getId() != null && process.getId().contains(processPath)).collect(Collectors.toList()));
    }

    return processes;
  }
  
  private static List<DashboardProcess> filterProcessesByCategories(List<String> categories) {
    List<DashboardProcess> processes;
    if (CollectionUtils.isNotEmpty(categories)) {
      processes = getAllPortalProcesses().stream()
          .filter(process -> isProcessMatchedCategory(process, categories))
          .collect(Collectors.toList());
    } else {
      processes = getAllPortalProcesses();
    }

    return processes;
  }

  private static boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    return categories.indexOf(process.getCategory()) > -1
        || (StringUtils.isBlank(process.getCategory()) && hasNoCategory);
  }

  public static String generateNewWidgetId(DashboardWidgetType type) {
    return String.format(WIDGET_ID_PATTERN, type.name(), DashboardUtils.generateId()).toLowerCase();
  }
}

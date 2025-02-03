package ch.ivy.addon.portalkit.util;

import static ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix.CMS;
import static ch.ivy.addon.portalkit.constant.DashboardConstants.MAX_NOTI_FILTERS;
import static ch.ivy.addon.portalkit.constant.DashboardConstants.MAX_NOTI_PATTERN;
import static ch.ivy.addon.portalkit.constant.DashboardConstants.WIDGET_ID_PATTERN;
import static ch.ivy.addon.portalkit.enums.DashboardColumnFormat.NUMBER;
import static ch.ivy.addon.portalkit.enums.DashboardColumnFormat.STRING;
import static ch.ivy.addon.portalkit.enums.DashboardColumnFormat.TEXT;
import static ch.ivy.addon.portalkit.enums.DashboardColumnFormat.TIMESTAMP;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bean.DashboardProcessBean;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.AbstractColumn;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
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
import ch.ivy.addon.portalkit.enums.ProcessSorting;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

public class DashboardWidgetUtils {

  public static DashboardWidget buildWidgetColumns(DashboardWidget widget) {
    return switch (widget.getType()) {
      case TASK -> buildTaskColumns((TaskDashboardWidget) widget);
      case CASE -> buildCaseColumns((CaseDashboardWidget) widget);
      case PROCESS -> {
        ProcessDashboardWidget processWidget = (ProcessDashboardWidget) widget;
        processWidget.buildFilterableColumns(initProcessFilterableColumns());
        yield buildProcessColumns(processWidget);
      }
      case CUSTOM -> {
        CustomWidgetUtils.loadDataForCustomWidget(widget);
        yield widget;
      }
      case PROCESS_VIEWER -> {
        CustomWidgetUtils.loadDataForProcessViewerWidget(widget);
        yield widget;
      }
      default -> widget;
    };
  }

  public static ProcessDashboardWidget buildProcessColumns(ProcessDashboardWidget processWidget) {
    for (var filter : processWidget.getFilterableColumns()) {
      if (DashboardStandardProcessColumn.CATEGORY.getField().equalsIgnoreCase(filter.getField()) && 
          processWidget instanceof CompactProcessDashboardWidget) {
        filter.setFilterList(((CompactProcessDashboardWidget)processWidget).getCategories());
      } else if (DashboardStandardProcessColumn.APPLICATION.getField().equalsIgnoreCase(filter.getField())) {
        filter.setFilterList(processWidget.getApplications());
      }
    }
    return processWidget;
  }

  public static TaskDashboardWidget buildTaskColumns(TaskDashboardWidget widget) {
    List<TaskColumnModel> columns = widget.getColumns();
    for (int i = 0; i < columns.size(); i++) {
      TaskColumnModel column = columns.get(i);
      
      String field = column.getField();
      Class<? extends TaskColumnModel> taskColumnModelClass = null;
      if (equals(DashboardStandardTaskColumn.START, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.StartColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.PRIORITY, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.PriorityColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.ID, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.IdColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.NAME, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.NameColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.DESCRIPTION, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.DescriptionColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.RESPONSIBLE, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.ResponsibleColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.STATE, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.StateColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.CREATED, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.CreatedDateColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.EXPIRY, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.ExpiryDateColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.CATEGORY, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.CategoryColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.APPLICATION, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.ApplicationColumnModel.class;
      } else if (equals(DashboardStandardTaskColumn.ACTIONS, field)) {
        taskColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.ActionsColumnModel.class;
      }
      
      if (taskColumnModelClass != null) {
        column = BusinessEntityConverter.convertValue(column, taskColumnModelClass);
      } 
      
      column.initDefaultValue();
      if (column.getType() == DashboardColumnType.CUSTOM) {
        buildCustomColumn(ICustomFieldMeta.tasks(), column, field);
      }
      if (column.getType() == DashboardColumnType.CUSTOM_CASE
          || column.getType() == DashboardColumnType.CUSTOM_BUSINESS_CASE) {
        buildCustomColumn(ICustomFieldMeta.cases(), column, field);
        column.setSortable(null);
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

  public static void buildCustomColumn(Set<ICustomFieldMeta> customFieldMetas, AbstractColumn column,
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
        .filter(col -> !StringUtils.equalsIgnoreCase(col.getField(), DashboardStandardTaskColumn.START.toString())
                  && !StringUtils.equalsIgnoreCase(col.getField(), DashboardStandardTaskColumn.ID.toString()))
        .collect(Collectors.toList());
  }

  public static CaseDashboardWidget buildCaseColumns(CaseDashboardWidget widget) {
    List<CaseColumnModel> columns = widget.getColumns();
    for (int i = 0; i < columns.size(); i++) {
      CaseColumnModel column = columns.get(i);
      Class<? extends CaseColumnModel> caseColumnModelClass = null;
      String field = column.getField();
      if (equals(DashboardStandardCaseColumn.ID, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.IdColumnModel.class;
      } else if (equals(DashboardStandardCaseColumn.NAME, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.NameColumnModel.class;
      } else if (equals(DashboardStandardCaseColumn.DESCRIPTION, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.DescriptionColumnModel.class;
      } else if (equals(DashboardStandardCaseColumn.CREATOR, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CreatorColumnModel.class;
      } else if (equals(DashboardStandardCaseColumn.STATE, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.StateColumnModel.class;
      } else if (equals(DashboardStandardCaseColumn.CREATED, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CreatedDateColumnModel.class;
      } else if (equals(DashboardStandardCaseColumn.FINISHED, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.FinishedDateColumnModel.class;
      } else if (equals(DashboardStandardCaseColumn.OWNER, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.OwnerColumnModel.class;
      } else if (equals(DashboardStandardCaseColumn.CATEGORY, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CategoryColumnModel.class;
      } else if (equals(DashboardStandardCaseColumn.APPLICATION, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.ApplicationColumnModel.class;
      } else if (equals(DashboardStandardCaseColumn.ACTIONS, field)) {
        caseColumnModelClass = ch.ivy.addon.portalkit.dto.dashboard.casecolumn.ActionsColumnModel.class;
      } else {
        column.setType(DashboardColumnType.CUSTOM);
      }
      
      if (caseColumnModelClass != null) {
        column = BusinessEntityConverter.convertValue(column, caseColumnModelClass);
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
    boolean disableCaseCreator = GlobalSettingService.getInstance().isHideCaseCreator();
    if (!enableCaseOwner) {
      filterableColumns.removeIf(col -> StringUtils.equalsIgnoreCase(col.getField(), DashboardStandardCaseColumn.OWNER.toString()));
    }
    if (disableCaseCreator) {
      filterableColumns
          .removeIf(col -> StringUtils.equalsIgnoreCase(col.getField(), DashboardStandardCaseColumn.CREATOR.name()));
    }
    return filterableColumns;
  }

  public static DashboardWidget simplifyWidgetColumnData(DashboardWidget widget) {
    List<String> deprecatedFields = new ArrayList<>();
    return switch (widget.getType()) {
    case TASK -> {
      var taskCustomFieldMetas = ICustomFieldMeta.tasks();

      var caseCustomFieldMetas = ICustomFieldMeta.cases();
      taskCustomFieldMetas.addAll(caseCustomFieldMetas);
      List<TaskColumnModel> columns = ((TaskDashboardWidget) widget).getColumns();
      columns.forEach(column -> {
        simplifyColumnData(column, taskCustomFieldMetas, deprecatedFields);
      });
      deprecatedFields.forEach(field -> {
        columns.removeIf(column -> column.getField().equals(field));
      });
      yield widget;
    }
    case CASE -> {
      var caseCustomFieldMetas = ICustomFieldMeta.cases();
      List<CaseColumnModel> caseColumns = ((CaseDashboardWidget) widget).getColumns();
      caseColumns.forEach(column -> {
        simplifyColumnData(column, caseCustomFieldMetas, deprecatedFields);
      });
      deprecatedFields.forEach(field -> {
        caseColumns.removeIf(column -> column.getField().equals(field));
      });
      yield widget;
    }
    default -> widget;
    };
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
      if (StringUtils.equals(column.initDefaultStyle(), column.getStyle())) {
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
    widget.getLayout().setNewWidget(false);
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
    return switch (widget.getType()) {
      case TASK -> hasPredefinedTaskFilter((TaskDashboardWidget) widget);
      case CASE -> hasPredefinedCaseFilter((CaseDashboardWidget) widget);
      default -> false;
    };
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
      List<String> asList = Arrays.asList(DashboardStandardCaseColumn.STATE.getField(), 
                                          DashboardStandardCaseColumn.CREATOR.getField(), 
                                          DashboardStandardCaseColumn.OWNER.getField(), 
                                          DashboardStandardCaseColumn.CATEGORY.getField(), 
                                          DashboardStandardCaseColumn.APPLICATION.getField());
      if (asList.stream().anyMatch(col.getField()::equalsIgnoreCase) && CollectionUtils.isNotEmpty(col.getFilterList())) {
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
      List<String> asList = Arrays.asList(DashboardStandardTaskColumn.PRIORITY.getField(), 
                                          DashboardStandardTaskColumn.STATE.getField(), 
                                          DashboardStandardTaskColumn.RESPONSIBLE.getField(), 
                                          DashboardStandardTaskColumn.CATEGORY.getField(), 
                                          DashboardStandardTaskColumn.APPLICATION.getField());
      if (asList.stream().anyMatch(col.getField()::equalsIgnoreCase)&& !CollectionUtils.isEmpty(col.getFilterList())) {
        hasPredefinedFilter = true;
      } else {
        hasPredefinedFilter = hasPredefinedCustomField(col);
      }
    }
    return hasPredefinedFilter;
  }

  public static boolean hasPredefinedCustomField(ColumnModel col) {
    return ((TEXT == col.getFormat() || STRING == col.getFormat()) && !(CollectionUtils.isEmpty(col.getFilterList()) && StringUtils.isBlank(col.getFilter())))
        || (NUMBER == col.getFormat() && !(StringUtils.isBlank(col.getFilterFrom()) && StringUtils.isBlank(col.getFilterTo())))
        || (TIMESTAMP == col.getFormat() && !(col.getDateFilterFrom() == null && col.getDateFilterTo() == null));
  }

  public static Optional<String> countDefinedUserFilter(DashboardWidget widget) {
    List<ColumnModel> filterableColumns = switch (widget.getType()) {
      case CASE -> ((CaseDashboardWidget) widget).getFilterableColumns();
      case TASK -> ((TaskDashboardWidget) widget).getFilterableColumns();
      case PROCESS -> ((CompactProcessDashboardWidget) widget).getFilterableColumns();
      default ->  new ArrayList<>();
    };
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
    return switch (type) {
      case TASK -> buildDefaultTaskWidget(id, name);
      case CASE -> buildDefaultCaseWidget(id, name);
      case PROCESS -> buildDefaultProcessWidget(id, name);
      case STATISTIC -> buildDefaultStatisticWidget(id, name);
      default -> null;
    };
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
      switch (col) {
        case NAME -> {columnModel = new ch.ivy.addon.portalkit.dto.dashboard.process.NameColumnModel();}
        case TYPE -> {columnModel = new ch.ivy.addon.portalkit.dto.dashboard.process.TypeColumnModel();}
        case APPLICATION -> {columnModel = new ch.ivy.addon.portalkit.dto.dashboard.process.ApplicationColumnModel();}
        case CATEGORY -> {columnModel = new ch.ivy.addon.portalkit.dto.dashboard.process.CategoryColumnModel();}
        default -> {}
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
    layout.setHeight(5);
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
    layout.setWidth(12);
    layout.setHeight(5);
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
    String processPath = processWidget.getProcessPath();
    if (processPath == null || processWidget.getProcess() != null) {
      processWidget.setEmptyProcessMessage(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/processes/noProcessSelected"));
      return;
    }

    List<String> publicExternalLinkIdsNotForIvySessionUser = getPublicExternalLinkIdsNotForIvySessionUser();

    // check permission with external processes
    if (publicExternalLinkIdsNotForIvySessionUser.indexOf(processPath) > -1) {
      processWidget.setHasPermissionToSee(false);
      processWidget.setEmptyProcessMessage(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/processes/noPermissionToSee"));
      return;
    } else {
      processWidget.setHasPermissionToSee(true);
    }
    
    for (DashboardProcess process : getAllPortalProcesses()) {
      if (process.getId() != null && process.getId().contains(processPath)) {
        updateProcessStartIdForCombined(processWidget, process);
        processWidget.setProcess(process);
        return;
      }
    }
    
    processWidget.setEmptyProcessMessage(Ivy.cms().co("/Dialogs/com/axonivy/portal/components/ProcessViewer/ProcessNotFound"));
  }
  
  private static List<String> getPublicExternalLinkIdsNotForIvySessionUser() {
    List<ExternalLink> publicExternalLinksNotForIvySessionUser = ExternalLinkService.getInstance().filterPublicExternalLinksNotForIvySessionUser();
    return publicExternalLinksNotForIvySessionUser.stream().map(link -> link.getId()).toList();
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
    List<DashboardProcess> processes = getCompactProcesses(processWidget);
    processWidget.setDisplayProcesses(processes);
    processWidget.setOriginalDisplayProcesses(processes);
    if (!processWidget.getCriteria().isInConfiguration() || processWidget.isEnableQuickSearch()) {
      processWidget.filterProcessesByUser();
    }
  }

  private static List<DashboardProcess> getCompactProcesses(CompactProcessDashboardWidget processWidget) {
    List<DashboardProcess> processes;
    List<DashboardProcess> processesAfterSorting = new ArrayList<>();
    String processSorting = processWidget.getSorting();
    if (processWidget.isSelectedAllProcess()) {
      processes = getAllPortalProcesses();
    }
    if (processWidget.isPreview()) {
      if (CollectionUtils.isNotEmpty(processWidget.getProcesses())) {
        processes = filterProcessesByProcesses(processWidget.getProcesses());
        processWidget.setProcesses(processes);
      } else {
        processes = filterProcessesByCategories(processWidget.getCategories());
      }
    } else {
      if (CollectionUtils.isNotEmpty(processWidget.getProcessPaths())) {
        processes = filterProcessesByProcessPaths(processWidget.getProcessPaths());
        processWidget.setProcesses(processes);
      } else {
        processes = filterProcessesByCategories(processWidget.getCategories());
      }
    }
    if (processSorting == null || ProcessSorting.BY_ALPHABETICALLY.name().equals(processSorting)) {
      processesAfterSorting = sortProcessByAlphabet(processes);
    } else if (ProcessSorting.BY_INDEX.name().equals(processSorting)) {
      processesAfterSorting = sortProcessByIndex(processes);
    } else if (ProcessSorting.BY_CUSTOM_ORDER.name().equals(processSorting)) {
      Map<String, Integer> customIndexs = processWidget.getCustomIndexs();
      processesAfterSorting = sortProcessByCustomOrder(processes, customIndexs);
    }
    return processesAfterSorting;
  }
  
  public static List<DashboardProcess> getAllPortalProcesses() {
    DashboardProcessBean dashboardProcessBean = ManagedBeans.get("dashboardProcessBean");
    return dashboardProcessBean == null ? new ArrayList<>() : dashboardProcessBean.getPortalDashboardProcesses();
  }

  private static List<DashboardProcess> filterProcessesByProcesses(List<DashboardProcess> selectedProcesses) {
    List<String> ids = ListUtilities.transformList(selectedProcesses, DashboardProcess::getId);
    Predicate<? super DashboardProcess> predicate = process -> ids.contains(process.getId());
    return ListUtilities.filterList(getAllPortalProcesses(), predicate);
  }

  private static List<DashboardProcess> filterProcessesByProcessPaths(List<String> processPaths) {
    Predicate<? super DashboardProcess> predicate = process -> process.getId() != null && doesIdContainPath(process.getId(), processPaths);
    return ListUtilities.filterList(getAllPortalProcesses(), predicate);
  }
  
  private static boolean doesIdContainPath(String id, List<String> paths) {
    for (String path: paths) {
      if (id.contains(path)) {
        return true;
      }
    }
    return false;
  }
  
  private static List<DashboardProcess> filterProcessesByCategories(List<String> categories) {
    Predicate<? super DashboardProcess> predicate = process -> isProcessMatchedCategory(process, categories);
    return ListUtilities.filterList(getAllPortalProcesses(), predicate);
  }

  public static boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    if (CollectionUtils.isEmpty(categories) || Objects.isNull(process)) {
      return true;
    }
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    if (Objects.isNull(process.getCategory())) {
      return hasNoCategory;
    }
    return categories.indexOf(process.getCategory().getCmsUri()) > -1
        || (StringUtils.isBlank(process.getCategory().getPath()) && hasNoCategory);
  }

  public static String generateNewWidgetId(DashboardWidgetType type) {
    return String.format(WIDGET_ID_PATTERN, type.name(), DashboardUtils.generateId()).toLowerCase();
  }

  public static Optional<DashboardWidget> findWidget(Dashboard dashboard, String widgetId) {
    return Optional.ofNullable(dashboard)
        .map(Dashboard::getWidgets)
        .orElse(new ArrayList<>())
        .stream()
        .filter(Objects::nonNull)
        .filter(widget -> widget.getId() != null && widget.getId().equals(widgetId))
        .findFirst();
  }

  public static List<DashboardProcess> sortProcessByAlphabet(List<DashboardProcess> processes) {
    Locale currentLocale = Ivy.session().getContentLocale();
    Collator collator = Collator.getInstance(currentLocale);

    Comparator<DashboardProcess> comparator =
        Comparator.comparing(process -> process.getName().toLowerCase(), collator::compare);

    return processes.stream()
        .sorted(comparator).collect(Collectors.toList());
  }

  public static List<DashboardProcess> sortProcessByIndex(List<DashboardProcess> processes) {
    Locale currentLocale = Ivy.session().getContentLocale();
    Collator collator = Collator.getInstance(currentLocale);

    Comparator<DashboardProcess> byName =
        Comparator.comparing(process -> process.getName().toLowerCase(), collator::compare);

    // First, compare by sort index (as integers or 0 if parsing fails)
    List<DashboardProcess> processWithIndex = processes.stream()
        .filter(process -> StringUtils.isNoneEmpty(process.getSortIndex()))
        .sorted(Comparator.<DashboardProcess, Integer>comparing(process -> {
          try {
            return Integer.parseInt(process.getSortIndex().trim());
          } catch (NumberFormatException e) {
            Ivy.log().warn(e);
            return 0;
          }
          // Then, if sort index is equal, compare by name (case-insensitive)
        }).thenComparing(byName))
        .collect(Collectors.toList());

    List<DashboardProcess> processWithoutIndex = processes.stream()
        .filter(process -> StringUtils.isEmpty(process.getSortIndex()))
        .sorted(byName)
        .collect(Collectors.toList());

    processWithIndex.addAll(processWithoutIndex);

    return processWithIndex;
  }
  
  public static List<DashboardProcess> sortProcessByCustomOrder(List<DashboardProcess> processes, Map<String, Integer> indexes) {
    List<DashboardProcess> result = new ArrayList<>();
    if (MapUtils.isNotEmpty(indexes)) {
      indexes.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> {
        String processId = entry.getKey();
        for (int i = 0; i < processes.size(); i ++) {
          if (processes.get(i).getId().equals(processId)) {
            result.add(processes.get(i));
            processes.remove(i);
            break;
          } 
        }
      });
      // add the rest of processes which are not indexed to the end of the list
      result.addAll(processes);
    } else {
      result.addAll(processes);
    }
    return result;
    
  }

  public static CustomFieldType findTaskCustomFieldType(String field) {
    for (ICustomFieldMeta customField : ICustomFieldMeta.tasks()) {
      if (customField.name().contentEquals(field)) {
        return customField.type();
      }
    }
    return null;
  }

  public static CustomFieldType findCaseCustomFieldType(String field) {
    for (ICustomFieldMeta customField : ICustomFieldMeta.cases()) {
      if (customField.name().contentEquals(field)) {
        return customField.type();
      }
    }
    return null;
  }
}

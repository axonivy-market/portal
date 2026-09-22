package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestSortableFieldRegression {

  private static final String CUSTOM_FIELD_JOIN = "IWA_TaskCustom";

  private static final List<String> QUERY_SUPPORTED_SORT_FIELDS = List.of("name", "id", "state", "priority",
      "worker", "startTimestamp", "endTimestamp", "expiryTimestamp", "businessCaseId", "technicalCaseId");

  private TaskColumnModel column(String field, DashboardColumnType type) {
    TaskColumnModel model = new TaskColumnModel();
    model.setField(field);
    model.setType(type);
    model.initDefaultValue();
    return model;
  }

  private TaskDashboardWidget widgetWith(TaskColumnModel... columns) {
    TaskDashboardWidget widget = new TaskDashboardWidget();
    widget.setId("task_1");
    widget.setColumns(new ArrayList<>(List.of(columns)));
    return widget;
  }

  private String taskQuery(List<TaskColumnModel> columns, String sortField, boolean descending) {
    DashboardTaskSearchCriteria criteria = new DashboardTaskSearchCriteria();
    criteria.setColumns(columns);
    criteria.setFilters(new ArrayList<>());
    criteria.setUserFilters(new ArrayList<>());
    criteria.setSortField(sortField);
    criteria.setSortDescending(descending);
    return criteria.buildQuery().toString();
  }

  private String standardTaskQuery(String sortField, boolean descending) {
    return taskQuery(new ArrayList<>(List.of(
        column("name", DashboardColumnType.STANDARD),
        column("activator", DashboardColumnType.STANDARD),
        column("application", DashboardColumnType.STANDARD),
        column("description", DashboardColumnType.STANDARD),
        column("actions", DashboardColumnType.STANDARD),
        column("worker", DashboardColumnType.STANDARD))), sortField, descending);
  }

  private String processTaskQuery(String sortField, boolean descending) {
    DashboardProcessTaskSearchCriteria criteria = new DashboardProcessTaskSearchCriteria(null);
    criteria.setSortField(sortField);
    criteria.setSortDescending(descending);
    return criteria.buildQuery().toString();
  }

  @Test
  void processWidget_sortDescendingByUnsupportedField_doesNotThrow() {
    DashboardProcessTaskSearchCriteria criteria = new DashboardProcessTaskSearchCriteria(null);
    criteria.setSortField("description");
    criteria.setSortDescending(true);

    assertThatCode(() -> criteria.buildQuery()).doesNotThrowAnyException();
  }

  @Test
  void processWidget_sortAscendingByUnsupportedField_appliesNoOrdering() {
    assertThat(processTaskQuery("description", false)).isEqualTo(processTaskQuery(null, false));
  }

  @ParameterizedTest
  @ValueSource(strings = {"activator", "application", "description", "actions"})
  void taskWidget_sortByNonSortableStandardField_doesNotJoinCustomField(String field) {
    assertThat(standardTaskQuery(field, false)).doesNotContain(CUSTOM_FIELD_JOIN);
  }

  @Test
  void taskWidget_sortByWorker_ordersByWorkerDisplayNameOnly() {
    String query = standardTaskQuery("worker", false);

    assertThat(query).doesNotContain(CUSTOM_FIELD_JOIN);
    assertThat(query).contains("ORDER BY Column(WORKER.DisplayName) ASCENDING");
  }

  @Test
  void taskWidget_sortByWorkerDescending_appliesDescendingToWorkerDisplayName() {
    assertThat(standardTaskQuery("worker", true))
        .contains("ORDER BY Column(WORKER.DisplayName) DESCENDING");
  }

  @Test
  void taskWidget_sortByRealCustomField_stillOrdersByCustomField() {
    String query = taskQuery(new ArrayList<>(List.of(column("Citizenship", DashboardColumnType.CUSTOM))),
        "Citizenship", false);

    assertThat(query).contains(CUSTOM_FIELD_JOIN);
    assertThat(query).contains("ORDER BY Column(TskCstmStrngFld_Citizenship.Value) ASCENDING");
  }

  @Test
  void taskWidget_sortBySupportedStandardField_isUnaffected() {
    assertThat(standardTaskQuery("name", false))
        .doesNotContain(CUSTOM_FIELD_JOIN)
        .contains("ORDER BY Column(IWA_TaskLocalized.Name) ASCENDING");
  }

  @Test
  void buildTaskColumns_jsonForcesSortableOnUnsupportedField_isOverriddenToFalse() {
    TaskColumnModel forced = new TaskColumnModel();
    forced.setField("activator");
    forced.setType(DashboardColumnType.STANDARD);
    forced.setSortable(true);
    TaskDashboardWidget widget = widgetWith(forced);

    DashboardWidgetUtils.buildTaskColumns(widget);

    assertThat(widget.getColumns().get(0).getSortable()).isFalse();
  }

  @Test
  void buildTaskColumns_realCustomFieldStaysSortable() {
    TaskColumnModel custom = new TaskColumnModel();
    custom.setField("Citizenship");
    custom.setType(DashboardColumnType.CUSTOM);
    TaskDashboardWidget widget = widgetWith(custom);

    DashboardWidgetUtils.buildTaskColumns(widget);

    assertThat(widget.getColumns().get(0).getSortable()).isTrue();
  }

  @Test
  void buildTaskColumns_supportedStandardFieldStaysSortable() {
    TaskColumnModel name = new TaskColumnModel();
    name.setField("name");
    name.setType(DashboardColumnType.STANDARD);
    TaskDashboardWidget widget = widgetWith(name);

    DashboardWidgetUtils.buildTaskColumns(widget);

    assertThat(widget.getColumns().get(0).getSortable()).isTrue();
  }

  @Test
  void widgetSortBy_persistedNonSortableSortField_isIgnored() {
    TaskDashboardWidget widget = widgetWith(column("activator", DashboardColumnType.STANDARD));
    widget.setSortField("activator");

    assertThat(widget.getSortBy()).isNull();
  }

  @Test
  void widgetSortBy_persistedSortFieldIsCaseInsensitive() {
    TaskDashboardWidget widget = widgetWith(column("id", DashboardColumnType.STANDARD));
    widget.setSortField("ID");

    assertThat(widget.getSortBy()).isNotNull();
  }

  @Test
  void sortableEnumFlags_matchWhatTheQueryAppenderHandles() {
    assertThat(Arrays.stream(DashboardStandardTaskColumn.values())
        .filter(DashboardStandardTaskColumn::isSortable)
        .map(DashboardStandardTaskColumn::getField))
        .containsExactlyInAnyOrderElementsOf(QUERY_SUPPORTED_SORT_FIELDS);
  }

  @Test
  void isNonSortableStandardField_isCaseInsensitive() {
    assertThat(DashboardStandardTaskColumn.isNonSortableStandardField("ID")).isFalse();
    assertThat(DashboardStandardTaskColumn.isNonSortableStandardField("ACTIVATOR")).isTrue();
    assertThat(DashboardStandardTaskColumn.isNonSortableStandardField("Citizenship")).isFalse();
    assertThat(DashboardStandardTaskColumn.isNonSortableStandardField(null)).isFalse();
  }
}

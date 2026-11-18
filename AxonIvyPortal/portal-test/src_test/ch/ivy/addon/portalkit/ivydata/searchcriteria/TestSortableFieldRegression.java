package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;

@IvyTest
class TestSortableFieldRegression {

  private TaskColumnModel column(String field, DashboardColumnType type) {
    TaskColumnModel model = new TaskColumnModel();
    model.setField(field);
    model.setType(type);
    model.initDefaultValue();
    return model;
  }

  private String taskQuery(String sortField, boolean descending) {
    DashboardTaskSearchCriteria criteria = new DashboardTaskSearchCriteria();
    criteria.setColumns(new ArrayList<>(List.of(
        column("name", DashboardColumnType.STANDARD),
        column("worker", DashboardColumnType.STANDARD))));
    criteria.setFilters(new ArrayList<>());
    criteria.setUserFilters(new ArrayList<>());
    criteria.setSortField(sortField);
    criteria.setSortDescending(descending);
    return criteria.buildQuery().toString();
  }

  private String processTaskQuery(String sortField, boolean descending) {
    DashboardProcessTaskSearchCriteria criteria = new DashboardProcessTaskSearchCriteria(null);
    criteria.setSortField(sortField);
    criteria.setSortDescending(descending);
    return criteria.buildQuery().toString();
  }

  private String processCaseQuery(String sortField, boolean descending) {
    DashboardProcessCaseSearchCriteria criteria = new DashboardProcessCaseSearchCriteria(null);
    criteria.setSortField(sortField);
    criteria.setSortDescending(descending);
    return criteria.buildQuery().toString();
  }

  @Test
  void taskWidget_sortByWorker_doesNotJoinCustomFieldTable() {
    assertThat(taskQuery("worker", false)).doesNotContain("IWA_TaskCustom");
    assertThat(taskQuery("worker", true)).doesNotContain("IWA_TaskCustom");
  }

  @Test
  void taskWidget_sortByWorker_ordersByWorkerDisplayName() {
    assertThat(taskQuery("worker", false)).contains("ORDER BY Column(WORKER.DisplayName) ASCENDING");
    assertThat(taskQuery("worker", true)).contains("ORDER BY Column(WORKER.DisplayName) DESCENDING");
  }

  @Test
  void taskWidget_sortByRealCustomField_stillOrdersByCustomField() {
    DashboardTaskSearchCriteria criteria = new DashboardTaskSearchCriteria();
    criteria.setColumns(new ArrayList<>(List.of(column("Citizenship", DashboardColumnType.CUSTOM))));
    criteria.setFilters(new ArrayList<>());
    criteria.setUserFilters(new ArrayList<>());
    criteria.setSortField("Citizenship");

    assertThat(criteria.buildQuery().toString())
        .contains("ORDER BY Column(TskCstmStrngFld_Citizenship.Value) ASCENDING");
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

  @Test
  void processWidget_sortBySupportedFieldStillWorks() {
    assertThat(processTaskQuery("name", true)).isNotEqualTo(processTaskQuery(null, false));
  }

  @Test
  void customFieldFormat_textIsNotSortable() {
    assertThat(DashboardColumnFormat.TEXT.isSortableCustomField()).isFalse();
  }

  @Test
  void customFieldFormat_stringNumberTimestampAreSortable() {
    assertThat(DashboardColumnFormat.STRING.isSortableCustomField()).isTrue();
    assertThat(DashboardColumnFormat.NUMBER.isSortableCustomField()).isTrue();
    assertThat(DashboardColumnFormat.TIMESTAMP.isSortableCustomField()).isTrue();
  }

  @Test
  void everyCustomFieldTypeIsCoveredByAColumnFormat() {
    for (CustomFieldType type : CustomFieldType.values()) {
      assertThat(DashboardColumnFormat.valueOf(type.name())).isNotNull();
    }
  }

  @Test
  void taskWidget_sortByName_ordersByNameThenIdDescending() {
    String query = taskQuery("name", false);
    assertThat(query).contains(
        "ORDER BY Column(IWA_TaskLocalized.Name) ASCENDING, Column(IWA_Task.TaskId) DESCENDING");
  }

  @Test
  void taskWidget_sortByIdField_doesNotDoublesort() {
    String query = taskQuery("id", false);
    assertThat(query).contains("ORDER BY Column(IWA_Task.TaskId) ASCENDING");
    int count = query.split("Column\\(IWA_Task.TaskId\\)").length - 1;
    assertThat(count).isEqualTo(1);
  }

  @Test
  void taskWidget_sortByPriority_includesSecondaryIdSort() {
    String query = taskQuery("priority", true);
    assertThat(query).contains(
        "ORDER BY Column(IWA_Task.Priority) DESCENDING, Column(IWA_Task.TaskId) DESCENDING");
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  void processCaseWidget_sortByName_ordersByNameThenIdDescending(boolean descending) {
    String direction = descending ? "DESCENDING" : "ASCENDING";
    assertThat(processCaseQuery("name", descending)).contains(
        "ORDER BY Column(IWA_CaseLocalized.Name) " + direction + ", Column(IWA_Case.CaseId) DESCENDING");
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  void processCaseWidget_sortById_ordersByIdOnce(boolean descending) {
    String direction = descending ? "DESCENDING" : "ASCENDING";
    String query = processCaseQuery("id", descending);
    String orderBy = query.substring(query.indexOf("ORDER BY"));
    assertThat(orderBy).contains("ORDER BY Column(IWA_Case.CaseId) " + direction);
    assertThat(orderBy.split("Column\\(IWA_Case.CaseId\\)", -1)).hasSize(2);
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  void processCaseWidget_withoutSupportedSort_appliesNoOrdering(boolean descending) {
    assertThat(processCaseQuery(null, descending)).doesNotContain("ORDER BY");
    assertThat(processCaseQuery("description", descending)).isEqualTo(processCaseQuery(null, descending));
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  void processWidget_sortByName_ordersByNameThenIdDescending(boolean descending) {
    String query = processTaskQuery("name", descending);
    String direction = descending ? "DESCENDING" : "ASCENDING";
    assertThat(query).contains(
        "ORDER BY Column(IWA_TaskLocalized.Name) " + direction + ", Column(IWA_Task.TaskId) DESCENDING");
  }
}

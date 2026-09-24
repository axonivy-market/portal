package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

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
}

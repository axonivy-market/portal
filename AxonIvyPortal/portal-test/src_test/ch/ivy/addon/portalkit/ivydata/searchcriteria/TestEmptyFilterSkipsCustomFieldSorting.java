package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.IvyTest;

/**
 * When an EMPTY (IS NULL) filter is set on the same number/timestamp custom field that is sorted,
 * every result row is NULL on that field, so the ORDER BY on it must be skipped to let the engine
 * optimize the query.
 */
@IvyTest
class TestEmptyFilterSkipsCustomFieldSorting {

  private static final String FIELD = "SortedField";
  private static final String OTHER_FIELD = "OtherField";

  private DashboardFilter filter(String field, FilterOperator operator, DashboardColumnType type) {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(field);
    filter.setOperator(operator);
    filter.setFilterType(type);
    return filter;
  }

  private String taskOrderBy(DashboardColumnFormat format, boolean descending, DashboardFilter filter) {
    TaskColumnModel column = new TaskColumnModel();
    column.setField(FIELD);
    column.setType(DashboardColumnType.CUSTOM);
    column.setFormat(format);
    column.initDefaultValue();

    DashboardTaskSearchCriteria criteria = new DashboardTaskSearchCriteria();
    criteria.setColumns(new ArrayList<>(List.of(column)));
    criteria.setFilters(new ArrayList<>(List.of(filter)));
    criteria.setUserFilters(new ArrayList<>());
    criteria.setSortField(FIELD);
    criteria.setSortDescending(descending);
    return orderByClause(criteria.buildQuery().toString());
  }

  private String caseOrderBy(DashboardColumnFormat format, boolean descending, DashboardFilter filter) {
    CaseColumnModel column = new CaseColumnModel();
    column.setField(FIELD);
    column.setType(DashboardColumnType.CUSTOM);
    column.setFormat(format);
    column.initDefaultValue();

    DashboardCaseSearchCriteria criteria = new DashboardCaseSearchCriteria();
    criteria.setColumns(new ArrayList<>(List.of(column)));
    criteria.setFilters(new ArrayList<>());
    criteria.setUserFilters(new ArrayList<>(List.of(filter)));
    criteria.setSortField(FIELD);
    criteria.setSortDescending(descending);
    return orderByClause(criteria.buildQuery().toString());
  }

  private static String orderByClause(String query) {
    return StringUtils.defaultString(StringUtils.substringAfter(query, "ORDER BY"));
  }

  @ParameterizedTest
  @CsvSource({"NUMBER,false", "NUMBER,true", "TIMESTAMP,false", "TIMESTAMP,true"})
  void task_emptyFilterOnSortedField_skipsCustomFieldOrderBy(DashboardColumnFormat format, boolean descending) {
    String orderBy = taskOrderBy(format, descending, filter(FIELD, FilterOperator.EMPTY, DashboardColumnType.CUSTOM));
    assertThat(orderBy).doesNotContain(FIELD).contains("TaskId");
  }

  @Test
  void task_notEmptyFilterOnSortedField_keepsCustomFieldOrderBy() {
    String orderBy = taskOrderBy(DashboardColumnFormat.NUMBER, false,
        filter(FIELD, FilterOperator.NOT_EMPTY, DashboardColumnType.CUSTOM));
    assertThat(orderBy).contains(FIELD);
  }

  @Test
  void task_emptyFilterOnOtherField_keepsCustomFieldOrderBy() {
    String orderBy = taskOrderBy(DashboardColumnFormat.NUMBER, false,
        filter(OTHER_FIELD, FilterOperator.EMPTY, DashboardColumnType.CUSTOM));
    assertThat(orderBy).contains(FIELD);
  }

  @Test
  void task_emptyFilterOnStringField_keepsCustomFieldOrderBy() {
    String orderBy = taskOrderBy(DashboardColumnFormat.STRING, false,
        filter(FIELD, FilterOperator.EMPTY, DashboardColumnType.CUSTOM));
    assertThat(orderBy).contains(FIELD);
  }

  @Test
  void task_emptyFilterOnCaseFieldWithSameName_keepsCustomFieldOrderBy() {
    String orderBy = taskOrderBy(DashboardColumnFormat.NUMBER, false,
        filter(FIELD, FilterOperator.EMPTY, DashboardColumnType.CUSTOM_CASE));
    assertThat(orderBy).contains(FIELD);
  }

  @ParameterizedTest
  @CsvSource({"NUMBER,false", "NUMBER,true", "TIMESTAMP,false", "TIMESTAMP,true"})
  void case_emptyFilterOnSortedField_skipsCustomFieldOrderBy(DashboardColumnFormat format, boolean descending) {
    String orderBy = caseOrderBy(format, descending, filter(FIELD, FilterOperator.EMPTY, DashboardColumnType.CUSTOM));
    assertThat(orderBy).doesNotContain(FIELD).contains("CaseId");
  }

  @Test
  void case_notEmptyFilterOnSortedField_keepsCustomFieldOrderBy() {
    String orderBy = caseOrderBy(DashboardColumnFormat.TIMESTAMP, false,
        filter(FIELD, FilterOperator.NOT_EMPTY, DashboardColumnType.CUSTOM));
    assertThat(orderBy).contains(FIELD);
  }

  @Test
  void case_emptyFilterOnOtherField_keepsCustomFieldOrderBy() {
    String orderBy = caseOrderBy(DashboardColumnFormat.TIMESTAMP, false,
        filter(OTHER_FIELD, FilterOperator.EMPTY, DashboardColumnType.CUSTOM));
    assertThat(orderBy).contains(FIELD);
  }

  @Test
  void case_emptyFilterOnStringField_keepsCustomFieldOrderBy() {
    String orderBy = caseOrderBy(DashboardColumnFormat.STRING, false,
        filter(FIELD, FilterOperator.EMPTY, DashboardColumnType.CUSTOM));
    assertThat(orderBy).contains(FIELD);
  }
}

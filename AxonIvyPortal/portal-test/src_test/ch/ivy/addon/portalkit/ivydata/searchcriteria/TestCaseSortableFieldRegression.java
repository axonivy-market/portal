package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestCaseSortableFieldRegression {

  private static final String CUSTOM_FIELD_JOIN = "IWA_CaseCustom";

  private static final List<String> QUERY_SUPPORTED_SORT_FIELDS =
      List.of("name", "id", "state", "creator", "startTimestamp", "endTimestamp");

  private CaseColumnModel column(String field, DashboardColumnType type) {
    CaseColumnModel model = new CaseColumnModel();
    model.setField(field);
    model.setType(type);
    model.initDefaultValue();
    return model;
  }

  private CaseDashboardWidget widgetWith(CaseColumnModel... columns) {
    CaseDashboardWidget widget = new CaseDashboardWidget();
    widget.setId("case_1");
    widget.setColumns(new ArrayList<>(List.of(columns)));
    return widget;
  }

  private String caseQuery(List<CaseColumnModel> columns, String sortField, boolean descending) {
    DashboardCaseSearchCriteria criteria = new DashboardCaseSearchCriteria();
    criteria.setColumns(columns);
    criteria.setFilters(new ArrayList<>());
    criteria.setUserFilters(new ArrayList<>());
    criteria.setSortField(sortField);
    criteria.setSortDescending(descending);
    return criteria.buildQuery().toString();
  }

  private String standardCaseQuery(String sortField, boolean descending) {
    return caseQuery(new ArrayList<>(List.of(
        column("name", DashboardColumnType.STANDARD),
        column("owner", DashboardColumnType.STANDARD),
        column("category", DashboardColumnType.STANDARD),
        column("application", DashboardColumnType.STANDARD),
        column("description", DashboardColumnType.STANDARD),
        column("actions", DashboardColumnType.STANDARD))), sortField, descending);
  }

  @ParameterizedTest
  @ValueSource(strings = {"owner", "category", "application", "description", "actions"})
  void caseWidget_sortByNonSortableStandardField_doesNotJoinCustomField(String field) {
    assertThat(standardCaseQuery(field, false)).doesNotContain(CUSTOM_FIELD_JOIN);
  }

  @Test
  void caseWidget_sortByRealCustomField_stillOrdersByCustomField() {
    assertThat(caseQuery(new ArrayList<>(List.of(column("Product", DashboardColumnType.CUSTOM))),
        "Product", false)).contains(CUSTOM_FIELD_JOIN);
  }

  @Test
  void caseWidget_sortBySupportedStandardField_isUnaffected() {
    assertThat(standardCaseQuery("name", false)).doesNotContain(CUSTOM_FIELD_JOIN);
  }

  @Test
  void processWidget_sortDescendingByUnsupportedField_doesNotThrow() {
    DashboardProcessCaseSearchCriteria criteria = new DashboardProcessCaseSearchCriteria(null);
    criteria.setSortField("owner");
    criteria.setSortDescending(true);

    assertThatCode(() -> criteria.buildQuery()).doesNotThrowAnyException();
  }

  @Test
  void buildCaseColumns_jsonForcesSortableOnUnsupportedField_isOverriddenToFalse() {
    CaseColumnModel forced = new CaseColumnModel();
    forced.setField("owner");
    forced.setType(DashboardColumnType.STANDARD);
    forced.setSortable(true);
    CaseDashboardWidget widget = widgetWith(forced);

    DashboardWidgetUtils.buildCaseColumns(widget);

    assertThat(widget.getColumns().get(0).getSortable()).isFalse();
  }

  @Test
  void buildCaseColumns_supportedStandardFieldStaysSortable() {
    CaseColumnModel name = new CaseColumnModel();
    name.setField("name");
    name.setType(DashboardColumnType.STANDARD);
    CaseDashboardWidget widget = widgetWith(name);

    DashboardWidgetUtils.buildCaseColumns(widget);

    assertThat(widget.getColumns().get(0).getSortable()).isTrue();
  }

  @Test
  void widgetSortBy_persistedNonSortableSortField_isIgnored() {
    CaseDashboardWidget widget = widgetWith(column("owner", DashboardColumnType.STANDARD));
    widget.setSortField("owner");

    assertThat(widget.getSortBy()).isNull();
  }

  @Test
  void widgetSortBy_persistedSortFieldIsCaseInsensitive() {
    CaseDashboardWidget widget = widgetWith(column("id", DashboardColumnType.STANDARD));
    widget.setSortField("ID");

    assertThat(widget.getSortBy()).isNotNull();
  }

  @Test
  void sortableEnumFlags_matchWhatTheQueryAppenderHandles() {
    assertThat(Arrays.stream(DashboardStandardCaseColumn.values())
        .filter(DashboardStandardCaseColumn::isSortable)
        .map(DashboardStandardCaseColumn::getField))
        .containsExactlyInAnyOrderElementsOf(QUERY_SUPPORTED_SORT_FIELDS);
  }
}

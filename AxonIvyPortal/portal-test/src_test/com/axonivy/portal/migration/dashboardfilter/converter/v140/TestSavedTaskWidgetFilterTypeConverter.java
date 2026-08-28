package com.axonivy.portal.migration.dashboardfilter.converter.v140;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardUtils.PortalDashboardItemWrapper;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.IvyTest;

/**
 * A saved filter stores no columns of its own, so the converter resolves the case a field refers to
 * from the columns of the widget the filter was saved for. These tests seed that widget into the
 * session dashboard cache.
 */
@IvyTest
class TestSavedTaskWidgetFilterTypeConverter {

  private static final String WIDGET_ID = "task-widget-1";
  private static final String FIELD = "CustomerName";
  private static final String CUSTOM_CASE = "custom_case";
  private static final String CUSTOM_BUSINESS_CASE = "custom_business_case";

  private SavedTaskWidgetFilterTypeConverter converter;

  @BeforeEach
  void setUp() {
    converter = new SavedTaskWidgetFilterTypeConverter();
  }

  @AfterEach
  void tearDown() {
    IvyCacheService.getInstance().invalidateSessionEntry(IvyCacheIdentifier.PORTAL_DASHBOARDS,
        UserUtils.getSessionIdentifierAttribteWithInitIfEmpty());
  }

  @Test
  void version_isTheReleaseWhichIntroducesTheCorrection() {
    assertThat(converter.version().getValue()).isEqualTo("14.0.0");
  }

  @Test
  void filterOfBusinessCaseColumn_isRetypedToBusinessCase() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter(FIELD, CUSTOM_CASE));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo(CUSTOM_BUSINESS_CASE);
  }

  @Test
  void filterOfSubCaseColumn_staysSubCase() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter(FIELD, CUSTOM_CASE));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo(CUSTOM_CASE);
  }

  @Test
  void fieldAddedAsBothColumns_resolvesToBusinessCase() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_CASE),
        column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter(FIELD, CUSTOM_CASE));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo(CUSTOM_BUSINESS_CASE);
  }

  @Test
  void standardFilter_isNeverRetyped() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter("name", "standard"));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo("standard");
  }

  @Test
  void taskCustomFilter_isNeverRetyped() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter(FIELD, "custom"));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo("custom");
  }

  @Test
  void filterWithoutMatchingColumn_isLeftUntouched() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter("OrderNumber", CUSTOM_CASE));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo(CUSTOM_CASE);
  }

  /**
   * A filter saved for a widget which no longer exists cannot be resolved, so it must be left as it is
   * rather than guessed at.
   */
  @Test
  void filterOfUnknownWidget_isLeftUntouched() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter("deleted-widget", userFilter(FIELD, CUSTOM_CASE));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo(CUSTOM_CASE);
  }

  @Test
  void filterWithoutWidgetId_isLeftUntouched() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(null, userFilter(FIELD, CUSTOM_CASE));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo(CUSTOM_CASE);
  }

  @Test
  void savedCaseWidgetFilter_isLeftUntouched() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter(FIELD, CUSTOM_CASE));
    savedFilter.put("widgetType", "CASE");

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo(CUSTOM_CASE);
  }

  @Test
  void savedFilterWithoutUserFilters_doesNotFail() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = JsonNodeFactory.instance.objectNode();
    savedFilter.put("widgetType", "TASK");
    savedFilter.put("widgetId", WIDGET_ID);
    savedFilter.putArray("userFilters");

    converter.convert(savedFilter);

    assertThat(savedFilter.get("userFilters")).isEmpty();
  }

  @Test
  void convert_isIdempotent() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter(FIELD, CUSTOM_CASE));

    converter.convert(savedFilter);
    String afterFirstRun = savedFilter.toString();
    converter.convert(savedFilter);

    assertThat(savedFilter.toString()).isEqualTo(afterFirstRun);
    assertThat(userFilterType(savedFilter)).isEqualTo(CUSTOM_BUSINESS_CASE);
  }

  /**
   * Saved filter JSON is deserialized with {@code ACCEPT_CASE_INSENSITIVE_ENUMS}, so a hand written
   * value may use either case.
   */
  @Test
  void upperCaseFilterType_isStillRetyped() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter(FIELD, "CUSTOM_CASE"));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo(CUSTOM_BUSINESS_CASE);
  }

  @Test
  void upperCaseStandardFilter_isNeverRetyped() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter("name", "STANDARD"));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo("STANDARD");
  }

  /** An unknown type must be left alone, not treated as untyped and guessed at. */
  @Test
  void unknownFilterType_isNeverRetyped() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, userFilter(FIELD, "not_a_type"));

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo("not_a_type");
  }

  /** A filter with no type at all is inferred from the column, which is the point of the converter. */
  @Test
  void untypedFilter_isTypedFromItsColumn() {
    cacheWidgetWithColumns(column(FIELD, DashboardColumnType.CUSTOM_BUSINESS_CASE));
    ObjectNode untyped = JsonNodeFactory.instance.objectNode().put("field", FIELD);
    ObjectNode savedFilter = savedTaskFilter(WIDGET_ID, untyped);

    converter.convert(savedFilter);

    assertThat(userFilterType(savedFilter)).isEqualTo(CUSTOM_BUSINESS_CASE);
  }

  // ── fixtures ───────────────────────────────────────────────────────────────

  private static void cacheWidgetWithColumns(TaskColumnModel... columns) {
    TaskDashboardWidget widget = new TaskDashboardWidget();
    widget.setId(WIDGET_ID);
    widget.setColumns(List.of(columns));

    Dashboard dashboard = new Dashboard();
    dashboard.setWidgets(List.<DashboardWidget>of(widget));

    IvyCacheService.getInstance().setSessionCache(IvyCacheIdentifier.PORTAL_DASHBOARDS,
        UserUtils.getSessionIdentifierAttribteWithInitIfEmpty(),
        new PortalDashboardItemWrapper(List.of(dashboard)));

    // Guards the fixture itself: without a cached widget every assertion below would pass vacuously.
    assertThat(DashboardUtils.getSessionDashboards()).isNotEmpty();
  }

  private static TaskColumnModel column(String field, DashboardColumnType type) {
    TaskColumnModel column = new TaskColumnModel();
    column.setField(field);
    column.setType(type);
    return column;
  }

  private static ObjectNode savedTaskFilter(String widgetId, ObjectNode... userFilters) {
    ObjectNode savedFilter = JsonNodeFactory.instance.objectNode();
    savedFilter.put("name", "My filter");
    savedFilter.put("widgetType", "TASK");
    savedFilter.put("version", "11.3.0");
    if (widgetId != null) {
      savedFilter.put("widgetId", widgetId);
    }
    ArrayNode filterArray = savedFilter.putArray("userFilters");
    for (ObjectNode userFilter : userFilters) {
      filterArray.add(userFilter);
    }
    return savedFilter;
  }

  private static ObjectNode userFilter(String field, String type) {
    return JsonNodeFactory.instance.objectNode().put("field", field).put("type", type);
  }

  private static String userFilterType(ObjectNode savedFilter) {
    return savedFilter.get("userFilters").get(0).get("type").asText();
  }
}

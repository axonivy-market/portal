package ch.ivy.addon.portalkit.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.ISecurityConstants;

@IvyTest
class TestDashboardUtils {

  private static final String MALFORMED_JSON = "{not-valid-json";

  @BeforeEach
  void resetImageUploadSizeLimit() {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getDefaultValue());
  }

  private Dashboard buildDashboard(DashboardWidget widget) {
    Dashboard dashboard = new Dashboard();
    dashboard.setId("dashboard-1");
    dashboard.setTitles(List.of(new DisplayName(Locale.ENGLISH, "My Dashboard")));
    dashboard.setWidgets(List.of(widget));
    return dashboard;
  }

  private Dashboard buildPlainDashboard(String id, String title) {
    Dashboard dashboard = new Dashboard();
    dashboard.setId(id);
    dashboard.setVersion(DashboardJsonVersion.LATEST_VERSION.getValue());
    dashboard.setTitles(List.of(new DisplayName(Locale.ENGLISH, title)));
    return dashboard;
  }

  private Dashboard buildDashboard(String id, DashboardDisplayType displayType) {
    Dashboard dashboard = buildPlainDashboard(id, id);
    dashboard.setDashboardDisplayType(displayType);
    return dashboard;
  }

  @Test
  void hasOversizedWidgetImage_nullDashboard_returnsFalse() {
    assertThat(DashboardUtils.hasOversizedWidgetImage(null)).isFalse();
  }

  @Test
  void hasOversizedWidgetImage_noWidgets_returnsFalse() {
    Dashboard dashboard = new Dashboard();
    dashboard.setId("dashboard-1");
    assertThat(DashboardUtils.hasOversizedWidgetImage(dashboard)).isFalse();
  }

  @Test
  void hasOversizedWidgetImage_welcomeWidgetWithinLimit_returnsFalse() {
    WelcomeDashboardWidget widget = new WelcomeDashboardWidget();
    widget.setId("welcome_1");
    widget.setImageContent("QUJDREVGRw==");
    widget.setImageType("png");
    Dashboard dashboard = buildDashboard(widget);

    assertThat(DashboardUtils.hasOversizedWidgetImage(dashboard)).isFalse();
  }

  @Test
  void hasOversizedWidgetImage_welcomeWidgetOverLimit_returnsTrue() {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), "0");
    WelcomeDashboardWidget widget = new WelcomeDashboardWidget();
    widget.setId("welcome_1");
    widget.setImageContent("QUJDREVGRw==");
    widget.setImageType("png");
    Dashboard dashboard = buildDashboard(widget);

    assertThat(DashboardUtils.hasOversizedWidgetImage(dashboard)).isTrue();
  }

  @Test
  void hasOversizedWidgetImage_welcomeWidgetDarkModeOverLimit_returnsTrue() {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), "0");
    WelcomeDashboardWidget widget = new WelcomeDashboardWidget();
    widget.setId("welcome_1");
    widget.setImageContentDarkMode("QUJDREVGRw==");
    widget.setImageTypeDarkMode("png");
    Dashboard dashboard = buildDashboard(widget);

    assertThat(DashboardUtils.hasOversizedWidgetImage(dashboard)).isTrue();
  }

  @Test
  void hasOversizedWidgetImage_navigationWidgetOverLimit_returnsTrue() {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), "0");
    NavigationDashboardWidget widget = new NavigationDashboardWidget();
    widget.setId("navigation_1");
    widget.setImageContent("QUJDREVGRw==");
    widget.setImageType("png");
    Dashboard dashboard = buildDashboard(widget);

    assertThat(DashboardUtils.hasOversizedWidgetImage(dashboard)).isTrue();
  }

  @Test
  void hasOversizedWidgetImage_navigationWidgetWithinLimit_returnsFalse() {
    NavigationDashboardWidget widget = new NavigationDashboardWidget();
    widget.setId("navigation_1");
    widget.setImageContent("QUJDREVGRw==");
    widget.setImageType("png");
    Dashboard dashboard = buildDashboard(widget);

    assertThat(DashboardUtils.hasOversizedWidgetImage(dashboard)).isFalse();
  }

  @Test
  void generateId_returnsThirtyTwoHexCharsWithNoDashes() {
    String id = DashboardUtils.generateId();
    assertThat(id).hasSize(32).doesNotContain("-").matches("[0-9a-f]{32}");
  }

  @Test
  void generateId_isUniquePerCall() {
    assertThat(DashboardUtils.generateId()).isNotEqualTo(DashboardUtils.generateId());
  }

  @Test
  void isDefaultTaskListDashboard_matchingId_returnsTrue() {
    Dashboard dashboard = buildPlainDashboard(DashboardUtils.DEFAULT_TASK_LIST_DASHBOARD, "Tasks");
    assertThat(DashboardUtils.isDefaultTaskListDashboard(dashboard)).isTrue();
  }

  @Test
  void isDefaultTaskListDashboard_otherId_returnsFalse() {
    Dashboard dashboard = buildPlainDashboard("dashboard-1", "Tasks");
    assertThat(DashboardUtils.isDefaultTaskListDashboard(dashboard)).isFalse();
  }

  @Test
  void isDefaultTaskListDashboard_nullDashboard_returnsFalse() {
    assertThat(DashboardUtils.isDefaultTaskListDashboard(null)).isFalse();
  }

  @Test
  void isDefaultCaseListDashboard_matchingId_returnsTrue() {
    Dashboard dashboard = buildPlainDashboard(DashboardUtils.DEFAULT_CASE_LIST_DASHBOARD, "Cases");
    assertThat(DashboardUtils.isDefaultCaseListDashboard(dashboard)).isTrue();
  }

  @Test
  void isDefaultCaseListDashboard_otherId_returnsFalse() {
    Dashboard dashboard = buildPlainDashboard("dashboard-1", "Cases");
    assertThat(DashboardUtils.isDefaultCaseListDashboard(dashboard)).isFalse();
  }

  @Test
  void isDefaultCaseListDashboard_nullDashboard_returnsFalse() {
    assertThat(DashboardUtils.isDefaultCaseListDashboard(null)).isFalse();
  }

  @Test
  void canSessionUserAccessDashboard_nullDashboard_returnsFalse() {
    assertThat(DashboardUtils.canSessionUserAccessDashboard(null)).isFalse();
  }

  @Test
  void canSessionUserAccessDashboard_emptyPermissions_returnsFalse() {
    Dashboard dashboard = buildPlainDashboard("dashboard-1", "Title");
    dashboard.setPermissions(List.of());
    assertThat(DashboardUtils.canSessionUserAccessDashboard(dashboard)).isFalse();
  }

  @Test
  void canSessionUserAccessDashboard_nullPermissions_returnsFalse() {
    Dashboard dashboard = buildPlainDashboard("dashboard-1", "Title");
    assertThat(DashboardUtils.canSessionUserAccessDashboard(dashboard)).isFalse();
  }

  @Test
  void canSessionUserAccessDashboard_containsTopLevelRole_returnsTrue() {
    Dashboard dashboard = buildPlainDashboard("dashboard-1", "Title");
    dashboard.setPermissions(List.of(ISecurityConstants.TOP_LEVEL_ROLE_NAME));
    assertThat(DashboardUtils.canSessionUserAccessDashboard(dashboard)).isTrue();
  }

  @Test
  void createMapIdToDashboard_mapsEachDashboardByItsId() {
    Dashboard first = buildPlainDashboard("dashboard-1", "First");
    Dashboard second = buildPlainDashboard("dashboard-2", "Second");

    Map<String, Dashboard> result = DashboardUtils.createMapIdToDashboard(List.of(first, second));

    assertThat(result).containsOnlyKeys("dashboard-1", "dashboard-2");
    assertThat(result.get("dashboard-1")).isSameAs(first);
    assertThat(result.get("dashboard-2")).isSameAs(second);
  }

  @Test
  void setDashboardAsPublic_marksEveryDashboardPublic() {
    Dashboard first = buildPlainDashboard("dashboard-1", "First");
    Dashboard second = buildPlainDashboard("dashboard-2", "Second");

    DashboardUtils.setDashboardAsPublic(List.of(first, second));

    assertThat(first.getIsPublic()).isTrue();
    assertThat(second.getIsPublic()).isTrue();
  }

  @Test
  void convertDashboardsToLatestVersion_validJson_returnsDashboards() {
    Dashboard dashboard = buildPlainDashboard("dashboard-1", "My Dashboard");
    String json = BusinessEntityConverter.entityToJsonValue(List.of(dashboard));

    List<Dashboard> result = DashboardUtils.convertDashboardsToLatestVersion(json);

    assertThat(result).hasSize(1);
    assertThat(result.get(0).getId()).isEqualTo("dashboard-1");
  }

  @Test
  void convertDashboardsToLatestVersion_malformedJson_returnsNull() {
    assertThat(DashboardUtils.convertDashboardsToLatestVersion(MALFORMED_JSON)).isNull();
  }

  @Test
  void convertDashboardsFromUploadFileToLatestVersion_validJson_returnsDashboards() throws IOException {
    Dashboard dashboard = buildPlainDashboard("dashboard-1", "My Dashboard");
    String json = BusinessEntityConverter.entityToJsonValue(List.of(dashboard));

    List<Dashboard> result = DashboardUtils
        .convertDashboardsFromUploadFileToLatestVersion(new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)));

    assertThat(result).hasSize(1);
    assertThat(result.get(0).getId()).isEqualTo("dashboard-1");
  }

  @Test
  void convertDashboardsFromUploadFileToLatestVersion_malformedJson_returnsNull() throws IOException {
    List<Dashboard> result = DashboardUtils.convertDashboardsFromUploadFileToLatestVersion(
        new ByteArrayInputStream(MALFORMED_JSON.getBytes(StandardCharsets.UTF_8)));
    assertThat(result).isNull();
  }

  @Test
  void convertDashboardToLatestVersion_validJson_returnsDashboard() throws IOException {
    Dashboard dashboard = buildPlainDashboard("dashboard-1", "My Dashboard");
    String json = BusinessEntityConverter.entityToJsonValue(dashboard);

    Dashboard result =
        DashboardUtils.convertDashboardToLatestVersion(new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)));

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo("dashboard-1");
  }

  @Test
  void convertDashboardToLatestVersion_malformedJson_returnsNull() throws IOException {
    Dashboard result = DashboardUtils
        .convertDashboardToLatestVersion(new ByteArrayInputStream(MALFORMED_JSON.getBytes(StandardCharsets.UTF_8)));
    assertThat(result).isNull();
  }

  @Test
  void updatePropertiesToNullIfCurrentValueIsDefaultValue_customColumn_headersCleared() {
    TaskColumnModel customColumn = new TaskColumnModel();
    customColumn.setType(DashboardColumnType.CUSTOM);
    customColumn.setHeaders(List.of(new DisplayName(Locale.ENGLISH, "Custom Header")));

    TaskDashboardWidget widget = new TaskDashboardWidget();
    widget.setId("task_1");
    widget.setColumns(List.of(customColumn));

    Dashboard dashboard = buildDashboard(widget);
    DashboardUtils.updatePropertiesToNullIfCurrentValueIsDefaultValue(List.of(dashboard));

    assertThat(customColumn.getHeaders()).isEmpty();
  }

  @Test
  void updatePropertiesToNullIfCurrentValueIsDefaultValue_standardColumn_headersUntouched() {
    TaskColumnModel standardColumn = new TaskColumnModel();
    standardColumn.setType(DashboardColumnType.STANDARD);
    List<DisplayName> originalHeaders = List.of(new DisplayName(Locale.ENGLISH, "Standard Header"));
    standardColumn.setHeaders(originalHeaders);

    TaskDashboardWidget widget = new TaskDashboardWidget();
    widget.setId("task_1");
    widget.setColumns(List.of(standardColumn));

    Dashboard dashboard = buildDashboard(widget);
    DashboardUtils.updatePropertiesToNullIfCurrentValueIsDefaultValue(List.of(dashboard));

    assertThat(standardColumn.getHeaders()).isEqualTo(originalHeaders);
  }

  @Test
  void updatePropertiesToNullIfCurrentValueIsDefaultValue_emptyDashboardList_doesNothing() {
    DashboardUtils.updatePropertiesToNullIfCurrentValueIsDefaultValue(List.of());
  }

  @Test
  void updatePropertiesToNullIfCurrentValueIsDefaultValue_null_doesNotThrow() {
    DashboardUtils.updatePropertiesToNullIfCurrentValueIsDefaultValue(null);
  }

  @Test
  void updateDashboardInSession_thenGetSelectedMainDashboardId_roundTrips() {
    DashboardUtils.updateDashboardInSession(SessionAttribute.SELECTED_DASHBOARD_ID, "dashboard-42");
    assertThat(DashboardUtils.getSelectedMainDashboardIdFromSession()).isEqualTo("dashboard-42");
  }

  @Test
  void findIndexOfDashboardById_exactIdMatch_returnsItsIndexRegardlessOfDisplayType() {
    List<Dashboard> dashboards = List.of(
        buildDashboard("tasks", DashboardDisplayType.TOP_MENU),
        buildDashboard("dashboard-1", DashboardDisplayType.TOP_MENU));

    assertThat(DashboardUtils.findIndexOfDashboardById(dashboards, "dashboard-1")).isEqualTo(1);
  }

  @Test
  void findIndexOfDashboardById_idNotFound_fallsBackToFirstSubMenuDashboard() {
    List<Dashboard> dashboards = List.of(
        buildDashboard("tasks", DashboardDisplayType.TOP_MENU),
        buildDashboard("dashboard-1", DashboardDisplayType.SUB_MENU));

    assertThat(DashboardUtils.findIndexOfDashboardById(dashboards, "does-not-exist")).isEqualTo(1);
  }

  @Test
  void findIndexOfDashboardById_blankId_returnsFirstSubMenuDashboard() {
    List<Dashboard> dashboards = List.of(
        buildDashboard("dashboard-1", DashboardDisplayType.SUB_MENU),
        buildDashboard("tasks", DashboardDisplayType.TOP_MENU));

    assertThat(DashboardUtils.findIndexOfDashboardById(dashboards, "")).isEqualTo(0);
  }

  @Test
  void findIndexOfDashboardById_noSubMenuDashboardExists_returnsMinusOne() {
    List<Dashboard> dashboards = List.of(
        buildDashboard("tasks", DashboardDisplayType.TOP_MENU),
        buildDashboard("cases", DashboardDisplayType.TOP_MENU));

    assertThat(DashboardUtils.findIndexOfDashboardById(dashboards, null)).isEqualTo(-1);
  }

  @Test
  void findIndexOfDashboardById_emptyDashboardList_returnsMinusOne() {
    assertThat(DashboardUtils.findIndexOfDashboardById(List.of(), "dashboard-1")).isEqualTo(-1);
  }

  @Test
  void findIndexOfFirstSubMenuDashboard_returnsIndexOfFirstMatch() {
    List<Dashboard> dashboards = List.of(
        buildDashboard("tasks", DashboardDisplayType.TOP_MENU),
        buildDashboard("dashboard-1", DashboardDisplayType.SUB_MENU),
        buildDashboard("dashboard-2", DashboardDisplayType.SUB_MENU));

    assertThat(DashboardUtils.findIndexOfFirstSubMenuDashboard(dashboards)).isEqualTo(1);
  }

  @Test
  void findIndexOfFirstSubMenuDashboard_none_returnsMinusOne() {
    List<Dashboard> dashboards = List.of(buildDashboard("tasks", DashboardDisplayType.TOP_MENU));

    assertThat(DashboardUtils.findIndexOfFirstSubMenuDashboard(dashboards)).isEqualTo(-1);
  }

  @Test
  void isSubMenuDashboard_matchIsSubMenu_returnsTrue() {
    List<Dashboard> dashboards = List.of(buildDashboard("dashboard-1", DashboardDisplayType.SUB_MENU));

    assertThat(DashboardUtils.isSubMenuDashboard(dashboards, "dashboard-1")).isTrue();
  }

  @Test
  void isSubMenuDashboard_matchWasPromotedToTopMenu_returnsFalse() {
    // Simulates a dashboard that used to be a submenu dashboard (and so was remembered as the
    // last-selected submenu dashboard in the session) but has since been promoted to a sidebar entry.
    List<Dashboard> dashboards = List.of(buildDashboard("dashboard-1", DashboardDisplayType.TOP_MENU));

    assertThat(DashboardUtils.isSubMenuDashboard(dashboards, "dashboard-1")).isFalse();
  }

  @Test
  void isSubMenuDashboard_idNotFound_returnsFalse() {
    List<Dashboard> dashboards = List.of(buildDashboard("dashboard-1", DashboardDisplayType.SUB_MENU));

    assertThat(DashboardUtils.isSubMenuDashboard(dashboards, "does-not-exist")).isFalse();
  }
}

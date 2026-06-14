package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

@IvyWebTest
public class DashboardFilterOperatorPolicyTest extends BaseTest {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String DASHBOARD_NAME = "Dashboard";
  private static final String NAME_FIELD = "name";
  private static final String DESCRIPTION_FIELD = "description";
  private static final String STATE_FIELD = "state";
  private static final String CONTAINS_KEY = "CONTAINS";
  private static final String IN_KEY = "IN";
  private static final String PRIORITY_FIELD = "Prio";
  private static final String PRIORITY_CM_FIELD = "priority";
  private static final String HIGH_PRIORITY = "HIGH";
  private static final String PRIORITY_HIGH_FILTER_NAME = "Priority High Filter";
  private static final String FILTER_OPERATOR_POLICY_KEY = Variable.FILTER_OPERATOR_POLICY.getKey();

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }

  @AfterEach
  public void resetGlobalOperatorPolicy() {
    updateGlobalVariable(FILTER_OPERATOR_POLICY_KEY, "");
  }

  @Test
  public void testUnselectOperatorUpdateCounting() {
    TaskEditWidgetNewDashBoardPage taskEditWidget = openTaskWidgetColumnManagement();

    String summaryBefore = taskEditWidget.getOperatorSummaryByField(NAME_FIELD);
    int selectedBefore = parseSelectedCount(summaryBefore);
    int totalBefore = parseTotalCount(summaryBefore);

    assertThat(selectedBefore).isGreaterThan(0);
    assertThat(totalBefore).isGreaterThanOrEqualTo(selectedBefore);

    if (!taskEditWidget.isFilterClicked(NAME_FIELD)) {
      taskEditWidget.clickOnFilterCheckBoxByField(NAME_FIELD);
    }

    taskEditWidget.unselectFirstOperatorByField(NAME_FIELD);

    String summaryAfter = taskEditWidget.getOperatorSummaryByField(NAME_FIELD);
    int selectedAfter = parseSelectedCount(summaryAfter);
    int totalAfter = parseTotalCount(summaryAfter);

    assertThat(totalAfter).isEqualTo(totalBefore);
    assertThat(selectedAfter).isEqualTo(selectedBefore - 1);
  }

  @Test
  public void testUncheckAllOpsTurnsFilterOffAndReEnable() {
    TaskEditWidgetNewDashBoardPage taskEditWidget = openTaskWidgetColumnManagement();
    ensureFilterEnabled(taskEditWidget, NAME_FIELD);

    String summaryBefore = taskEditWidget.getOperatorSummaryByField(NAME_FIELD);
    int selectedBefore = parseSelectedCount(summaryBefore);
    int totalBefore = parseTotalCount(summaryBefore);

    assertThat(selectedBefore).isGreaterThan(0);

    taskEditWidget.unselectAllOperatorsByField(NAME_FIELD);

    assertThat(taskEditWidget.isFilterClicked(NAME_FIELD)).isFalse();

    taskEditWidget.clickOnFilterCheckBoxByField(NAME_FIELD);

    String summaryAfterReEnable = taskEditWidget.getOperatorSummaryByField(NAME_FIELD);
    int selectedAfterReEnable = parseSelectedCount(summaryAfterReEnable);
    int totalAfterReEnable = parseTotalCount(summaryAfterReEnable);

    assertThat(taskEditWidget.isFilterClicked(NAME_FIELD)).isTrue();
    assertThat(totalAfterReEnable).isEqualTo(totalBefore);
    assertThat(selectedAfterReEnable).isGreaterThan(0);
  }

  @Test
  public void testGlobalDisabledOperatorIsDisabledAndUncheckedInColumnManagement() {
    setGlobalOperatorEnabled(CONTAINS_KEY, false);

    TaskEditWidgetNewDashBoardPage taskEditWidget = openTaskWidgetColumnManagement();
    ensureFilterEnabled(taskEditWidget, NAME_FIELD);

    assertThat(taskEditWidget.isOperatorDisabledByField(NAME_FIELD, FilterOperator.CONTAINS.getValue())).isTrue();
    assertThat(taskEditWidget.isOperatorSelectedByField(NAME_FIELD, FilterOperator.CONTAINS.getValue())).isFalse();
  }

  @Test
  public void testGlobalDisabledOperatorIsHiddenInComplexFilterUsage() {
    setGlobalOperatorEnabled(CONTAINS_KEY, false);

    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", null);

    assertThat(taskWidget.isOperatorOptionAvailableForFilterField("Name", FilterOperator.CONTAINS.getValue()))
        .isFalse();
  }

  @Test
  public void testFieldWithNoGloballyEnabledOperatorsDisablesFilterToggle() {
    setGlobalOperatorEnabled(IN_KEY, false);

    TaskEditWidgetNewDashBoardPage taskEditWidget = openTaskWidgetColumnManagement();

    assertThat(taskEditWidget.isFilterToggleDisabled(STATE_FIELD)).isTrue();
    assertThat(taskEditWidget.isFilterClicked(STATE_FIELD)).isFalse();
  }

  @Test
  public void testGlobalReEnableKeepsExplicitSubsetAndRestoresImplicitDefault() {
    TaskEditWidgetNewDashBoardPage taskEditWidget = openTaskWidgetColumnManagement();
    ensureFilterEnabled(taskEditWidget, NAME_FIELD);
    taskEditWidget.unselectOperatorByField(NAME_FIELD, FilterOperator.CONTAINS.getValue());
    taskEditWidget.saveColumn();
    taskEditWidget.save();

    setGlobalOperatorEnabled(CONTAINS_KEY, false);
    setGlobalOperatorEnabled(CONTAINS_KEY, true);

    TaskEditWidgetNewDashBoardPage refreshedTaskEditWidget = openTaskWidgetColumnManagement();
    ensureFilterEnabled(refreshedTaskEditWidget, NAME_FIELD);
    ensureFilterEnabled(refreshedTaskEditWidget, DESCRIPTION_FIELD);

    assertThat(refreshedTaskEditWidget.isOperatorSelectedByField(NAME_FIELD, FilterOperator.CONTAINS.getValue()))
        .isFalse();
    assertThat(
        refreshedTaskEditWidget.isOperatorSelectedByField(DESCRIPTION_FIELD, FilterOperator.CONTAINS.getValue()))
            .isTrue();
  }

  @Test
  public void testDisabledInOperatorPrunesSessionAndPredefinedFilters() {
    // The widget already has a predefined State IN filter by default.
    // Add Prio IN [HIGH] as a session filter and apply.
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.addFilter(PRIORITY_FIELD, null);
    selectPriorityValue(HIGH_PRIORITY);
    taskWidget.applyFilter();

    // Disable IN globally
    setGlobalOperatorEnabled(IN_KEY, false);

    // Verify IN is greyed out in Column Management: State and Priority filter toggles are both disabled
    TaskEditWidgetNewDashBoardPage taskEditWidget = openTaskWidgetColumnManagement();
    assertThat(taskEditWidget.isFilterToggleDisabled(STATE_FIELD)).isTrue();
    assertThat(taskEditWidget.isFilterToggleDisabled(PRIORITY_CM_FIELD)).isTrue();
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();

    // Verify both the predefined (State IN) and session (Priority IN) filters are pruned
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();

    assertThat(taskWidget.getNumberOfFilter()).isEqualTo(0);
  }

  @Test
  public void testDisabledInOperatorPrunesSavedFilterOnLoad() {
    // Add Priority IN [HIGH] as a session filter, save it, and apply
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.addFilter(PRIORITY_FIELD, null);
    selectPriorityValue(HIGH_PRIORITY);
    taskWidget.saveFilter(PRIORITY_HIGH_FILTER_NAME);
    taskWidget.applyFilter();

    // Disable IN globally
    setGlobalOperatorEnabled(IN_KEY, false);

    // Verify IN is greyed out in Column Management: State and Priority filter toggles are both disabled
    TaskEditWidgetNewDashBoardPage taskEditWidget = openTaskWidgetColumnManagement();
    assertThat(taskEditWidget.isFilterToggleDisabled(STATE_FIELD)).isTrue();
    assertThat(taskEditWidget.isFilterToggleDisabled(PRIORITY_CM_FIELD)).isTrue();
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();

    // Load the saved filter and apply; the Priority IN filter must be pruned by the apply guard
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.selectSavedFilter(PRIORITY_HIGH_FILTER_NAME);
    taskWidget.applyFilter();

    taskWidget.openFilterWidget();
    assertThat(taskWidget.getNumberOfFilter()).isEqualTo(0);
  }

  private TaskEditWidgetNewDashBoardPage openTaskWidgetColumnManagement() {
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName(DASHBOARD_NAME);

    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openColumnManagementDialog();
    return taskEditWidget;
  }

  private void ensureFilterEnabled(TaskEditWidgetNewDashBoardPage taskEditWidget, String fieldName) {
    if (!taskEditWidget.isFilterClicked(fieldName) && !taskEditWidget.isFilterToggleDisabled(fieldName)) {
      taskEditWidget.clickOnFilterCheckBoxByField(fieldName);
    }
    assertThat(taskEditWidget.isFilterClicked(fieldName)).isTrue();
  }

  private void setGlobalOperatorEnabled(String operatorKey, boolean shouldBeSelected) {
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();

    AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
    adminSettingsPage.updateGlobalMultiSelectionItem(FILTER_OPERATOR_POLICY_KEY, operatorKey, shouldBeSelected);
    adminSettingsPage.closeConfirmationDialog();

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }

  private void selectPriorityValue(String priorityValue) {
    $(".dashboard-widget-filter__main-panel")
        .$$( "div[class*='dashboard-widget-filter__filter-wrapper']").last()
        .$("div[id$=':priorities']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("div.ui-selectcheckboxmenu-panel[style*='display: block']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$( "li.ui-selectcheckboxmenu-item").filter(text(priorityValue)).first()
        .$("div.ui-chkbox-box").click();
    $("div.ui-selectcheckboxmenu-panel[style*='display: block']")
        .$("a.ui-selectcheckboxmenu-close").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  private int parseSelectedCount(String summary) {
    return Integer.parseInt(summary.trim().split("/")[0]);
  }

  private int parseTotalCount(String summary) {
    return Integer.parseInt(summary.trim().split("/")[1]);
  }
}

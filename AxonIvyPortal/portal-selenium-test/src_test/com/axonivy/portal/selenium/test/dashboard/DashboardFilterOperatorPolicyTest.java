package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
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
  private static final String STATE_FIELD = "state";
  private static final String CONTAINS_KEY = "CONTAINS";
  private static final String IN_KEY = "IN";
  private static final String SAVED_FILTER = "Saved Filter";
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
    String allOperators = Arrays.stream(FilterOperator.values())
      .map(FilterOperator::name)
      .collect(Collectors.joining(","));
    updateGlobalVariable(FILTER_OPERATOR_POLICY_KEY, allOperators); 
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
  public void testDisabledInOperatorPrunesExistingAndSavedFilters() {
    // Create a Priority IN filter and save it
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.addFilter(STATE_FIELD, null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.saveFilter(SAVED_FILTER);
    taskWidget.applyFilter();

    // Disable IN globally
    setGlobalOperatorEnabled(IN_KEY, false);

    // Verify IN is no longer available for affected fields
    TaskEditWidgetNewDashBoardPage taskEditWidget = openTaskWidgetColumnManagement();
    assertThat(taskEditWidget.isFilterToggleDisabled(STATE_FIELD)).isTrue();
    assertThat(taskEditWidget.isFilterToggleDisabled(STATE_FIELD)).isTrue();

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();

    // Verify active filters (predefined + session) are pruned
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();

    assertThat(taskWidget.getNumberOfFilter()).isEqualTo(0);

    // Verify saved filter is also pruned when loaded
    taskWidget.selectSavedFilter(SAVED_FILTER);
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

  private void setGlobalOperatorEnabled(String operatorKey, boolean shouldBeSelected) {
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();

    AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
    adminSettingsPage.updateGlobalMultiSelectionItem(FILTER_OPERATOR_POLICY_KEY, operatorKey, shouldBeSelected);
    adminSettingsPage.closeConfirmationDialog();

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }
}

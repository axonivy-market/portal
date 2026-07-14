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
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

@IvyWebTest
public class DashboardFilterOperatorPolicyTest extends BaseTest {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String CONTAINS_KEY = "CONTAINS";
  private static final String IN_KEY = "IN";
  private static final String PRIORITY_FIELD = "Prio";
  private static final String HIGH_PRIORITY = "HIGH";
  private static final String PRIORITY_HIGH_FILTER_NAME = "Priority High Filter";
  private static final String FILTER_OPERATOR_POLICY_KEY = Variable.FILTER_OPERATOR_POLICY.getKey();

  private NewDashboardPage newDashboardPage;

  @BeforeEach
  public void setupTest() {
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
  public void testDisabledInOperatorPrunesSessionFilters() {
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.addFilter(PRIORITY_FIELD, null);
    taskWidget.selectPriorityFilterValue(HIGH_PRIORITY);
    taskWidget.applyFilter();

    setGlobalOperatorEnabled(IN_KEY, false);

    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    assertThat(taskWidget.getNumberOfFilter()).isEqualTo(0);
  }

  @Test
  public void testDisabledInOperatorPrunesSavedFilterOnLoad() {
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.addFilter(PRIORITY_FIELD, null);
    taskWidget.selectPriorityFilterValue(HIGH_PRIORITY);
    taskWidget.saveFilter(PRIORITY_HIGH_FILTER_NAME);
    taskWidget.applyFilter();

    setGlobalOperatorEnabled(IN_KEY, false);

    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.selectSavedFilter(PRIORITY_HIGH_FILTER_NAME);
    taskWidget.applyFilter();

    taskWidget.openFilterWidget();
    assertThat(taskWidget.getNumberOfFilter()).isEqualTo(0);
  }

  @Test
  public void testReEnableOperatorMakesItAvailableAgain() {
    setGlobalOperatorEnabled(CONTAINS_KEY, false);

    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", null);
    assertThat(taskWidget.isOperatorOptionAvailableForFilterField("Name", FilterOperator.CONTAINS.getValue()))
        .isFalse();

    setGlobalOperatorEnabled(CONTAINS_KEY, true);

    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", null);
    assertThat(taskWidget.isOperatorOptionAvailableForFilterField("Name", FilterOperator.CONTAINS.getValue()))
        .isTrue();
    

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

package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.ElementsCollection;

@IvyWebTest
public class DashboardCaseWidgetActionTest extends BaseTest {

  // WIDGET NAME
  private static final String YOUR_CASES_WIDGET = "Your Cases";
  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testVisibilityCaseActionForNormalUser() {
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    // In progress
    assertCaseActionsByCaseState("In progress", Arrays.asList("Details", "Process overview", "Process Viewer"));
  }

  @Test
  public void testVisibilityCaseActionForAdminUser() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    // In progress
    assertCaseActionsByCaseState("In progress", Arrays.asList("Details", "Process overview", "Business details", "Destroy", "Process Viewer"));
    // Done
    assertCaseActionsByCaseState("Done", Arrays.asList("Details", "Process overview", "Business details", "Process Viewer"));
    // Destroyed
    filterByCaseState("In progress");
    caseWidget.destroyCase(0);
    assertCaseActionsByCaseState("Destroyed", Arrays.asList("Details", "Process overview", "Business details", "Process Viewer"));
  }

  private void assertCaseActionsByCaseState(String state, List<String> caseActionsInCase) {
    filterByCaseState(state);
    CaseWidgetNewDashBoardPage caseWidget = new CaseWidgetNewDashBoardPage();
    ElementsCollection actions = caseWidget.getActiveCaseActions(0);
    actions.shouldHaveSize(caseActionsInCase.size());
    assertTrue(actions.texts().containsAll(caseActionsInCase));
    caseWidget.turnOffActionsPanel(0);
  }

  private void filterByCaseState(String state) {
    CaseWidgetNewDashBoardPage caseWidget = new CaseWidgetNewDashBoardPage();
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    caseWidget.openFilterWidget();
    caseWidget.resetFilter();
    caseWidget.openFilterWidget();
    caseWidget.filterCaseState();
    caseWidget.selectState(state);
    caseWidget.applyFilter();
  }
}

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
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.ElementsCollection;

@IvyWebTest
public class DashBoardCaseWidgetActionTest extends BaseTest {

  // WIDGET NAME
  private static final String YOUR_CASES_WIDGET = "Your Cases";

  private NewDashboardPage newDashboardPage;
  private CaseWidgetNewDashBoardPage caseWidget;

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
    caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    // In progress
    assertTaskActionsByCaseState("In progress", Arrays.asList("Details"));
  }

  @Test
  public void testVisibilityCaseActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToNewDashBoard();
    caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    // In progress
    assertTaskActionsByCaseState("In progress", Arrays.asList("Details", "Business details", "Destroy"));
    // Destroyed
    caseWidget.clickOnCaseActionLink(0);
    caseWidget.destroyCase(0);
    assertTaskActionsByCaseState("Destroyed", Arrays.asList("Details", "Business details"));
    // Done
    assertTaskActionsByCaseState("Done", Arrays.asList("Details", "Business details"));
  }

  private void assertTaskActionsByCaseState(String state, List<String> taskActionsInCase) {
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    caseWidget.openFilterWidget();
    caseWidget.resetFilter();
    caseWidget.openFilterWidget();
    caseWidget.filterCaseState();
    caseWidget.selectState(state);
    caseWidget.applyFilter();
    ElementsCollection actions = caseWidget.getActiveCaseActions(0);
    actions.shouldHaveSize(taskActionsInCase.size());
    assertTrue(actions.texts().containsAll(taskActionsInCase));
  }
}

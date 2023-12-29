package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardWidgetEmptyMessageTest extends BaseTest {
  // WIDGET
  private static final String YOUR_CASES_WIDGET = "Your Cases";
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  // CASES
  private static final String WRONG_LEAVE_REQUEST_CASE_NAME = "Wrong Leave Request Name";

  // TASKS
  private static final String WRONG_TASK_NAME = "Wrong Task Name";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testCaseWidgetEmptyMessage() {
    newDashboardPage.checkDisplayedCaseWidgetContainer();
    newDashboardPage.getCaseWidgetEmptyMessageWhenNotFilter().shouldBe(Condition.appear);
    newDashboardPage.getCaseWidgetTable().shouldBe(Condition.disappear);

    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    newDashboardPage.checkDisplayedCaseWidgetContainer();
    newDashboardPage.getCaseWidgetTable().shouldBe(Condition.appear);

    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    caseWidget.openFilterWidget();
    caseWidget.filterCaseName(WRONG_LEAVE_REQUEST_CASE_NAME);
    caseWidget.applyFilter();
    newDashboardPage.getCaseWidgetEmptyMessageWhenFilter().shouldBe(Condition.appear);
    newDashboardPage.getCaseWidgetTable().shouldBe(Condition.disappear);
  }

  @Test
  public void testTaskWidgetEmptyMessage() {
    newDashboardPage.checkDisplayedTaskWidgetContainer();
    newDashboardPage.getTaskWidgetEmptyMessageWhenNotFilter().shouldBe(Condition.appear);
    newDashboardPage.getTaskWidgetTable().shouldBe(Condition.disappear);

    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    newDashboardPage.checkDisplayedTaskWidgetContainer();
    newDashboardPage.getTaskWidgetTable().shouldBe(Condition.appear);

    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(WRONG_TASK_NAME);
    taskWidget.applyFilter();
    newDashboardPage.getTaskWidgetEmptyMessageWhenFilter().shouldBe(Condition.appear);
    newDashboardPage.getTaskWidgetTable().shouldBe(Condition.disappear);
  }

}

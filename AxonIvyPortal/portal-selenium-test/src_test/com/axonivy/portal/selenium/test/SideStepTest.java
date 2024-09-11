package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AdhocPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class SideStepTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingCaseMapUrl);
  }

  @Test
  public void testSideStepInCaseList() {
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    int sideSteps = casePage.countSideStepItems();
    assertEquals(2, sideSteps);
  }

  @Test
  public void testSideSteps() {
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.clickTaskActionMenu();
    taskTemplatePage.countSideSteps();
    assertEquals(2, taskTemplatePage.countSideSteps());
  }

  private TaskTemplatePage startATask() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    return taskTemplatePage;
  }

  @Test
  public void testAddAdhocTask() {
    login(TestAccount.ADMIN_USER);
    int firstTask = 0;
    final String TASK_NAME = "Case Map Leave Request";
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(2));
    assertEquals(taskWidgetPage.getNameOfTaskAt(0), TASK_NAME);
    AdhocPage adhocPage = taskWidgetPage.addAdhoc(firstTask);
    adhocPage.enterSubject("Collect Information");
    adhocPage.addResponsible(TestAccount.DEMO_USER.getFullName());
    adhocPage.startWorkflow();
    adhocPage = new AdhocPage();
    login(TestAccount.DEMO_USER);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(1));
    assertEquals(taskWidgetPage.getNameOfTaskAt(0), "Collect Information");
    taskWidgetPage.startTask(0);
    adhocPage = new AdhocPage();
    adhocPage.addDescription("Annual leaves are available");
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(1));
    assertEquals(taskWidgetPage.getNameOfTaskAt(0), TASK_NAME);
  }
}

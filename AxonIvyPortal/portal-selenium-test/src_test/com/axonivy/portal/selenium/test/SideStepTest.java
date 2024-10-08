package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

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

}

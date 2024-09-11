package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class PageRefreshingTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestingTasks();
  }

  @Test
  public void testTasksInPortalTaskPageUpdatedAfterReloading() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.countTasks().shouldBe(CollectionCondition.size(3));
    launchBrowserAndGotoRelativeLink(createTestingTasksUrl);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.countTasks().shouldBe(CollectionCondition.size(6));
  }
  
  @Test
  public void testTasksInPortalHomePageUpdatedAfterExpandToFullMode() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks().size());

    launchBrowserAndGotoRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(6, taskWidgetPage.countTasks().size());
  }
  
  
  @Test
  public void testTasksInPortalHomePageUpdatedAfterReloading() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks().size());

    launchBrowserAndGotoRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    refreshPage();
    assertEquals(6, taskWidgetPage.countTasks().size());

  }
  
}

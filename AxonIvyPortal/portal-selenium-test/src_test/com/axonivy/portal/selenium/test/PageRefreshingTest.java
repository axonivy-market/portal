package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest(headless = false)
public class PageRefreshingTest extends BaseTest{

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
}

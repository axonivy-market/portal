package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
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
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.countAllTasks().shouldBe(CollectionCondition.size(4));
    launchBrowserAndGotoRelativeLink(createTestingTasksUrl);
    NavigationHelper.navigateToTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.countAllTasks().shouldBe(CollectionCondition.size(8));
  }
}

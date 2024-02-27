package com.axonivy.portal.selenium.test.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class TaskCategoryMenuTest extends BaseTest {

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void testSelectTaskCategoryMenuAsNormalUser() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(3), DEFAULT_TIMEOUT);
  }

  @Test
  public void testSelectTaskCategoryMenuAsAdminRole() {
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(4), DEFAULT_TIMEOUT);
  }
}

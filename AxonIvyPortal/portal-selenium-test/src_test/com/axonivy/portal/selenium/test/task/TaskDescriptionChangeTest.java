package com.axonivy.portal.selenium.test.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;

@IvyWebTest
public class TaskDescriptionChangeTest extends BaseTest {


  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
  }

  @Test
  public void testChangeTaskDescription() {
    login(TestAccount.ADMIN_USER);
    int firstTask = 0;
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();

    taskWidget.openTaskDetailsPageByAction(firstTask);
    testChangeTaskDescription("Hello World!", "Hello World!", taskWidget);
    testChangeTaskDescription(
        "<b>HTML</b> description could contain malicious script <script>alert('Attacking')</script> but it will be sanitized.",
        "HTML description could contain malicious script but it will be sanitized.", taskWidget);
    testChangeTaskDescription("", "No description", taskWidget);
    testChangeTaskDescription("And you can change description if it is empty",
        "And you can change description if it is empty", taskWidget);
  }

  private void testChangeTaskDescription(String newDescription, String shownDescriptionInDetails,
      TopMenuTaskWidgetPage taskWidgetPage) {
    taskWidgetPage.changeDescriptionOfTask(newDescription);
    assertEquals(taskWidgetPage.getTaskDescription(), shownDescriptionInDetails);
  }

  @Test
  public void testUserWithoutPermissionCannotChangeTaskName() {
    int firstTask = 0;
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openTaskDetailsPageByAction(firstTask);
    assertFalse(taskWidget.isTaskNameChangeComponentPresented(firstTask));
  }


  public void changeLanguage(int index) {
    NewDashboardPage taskList = NavigationHelper.navigateToTaskList();
    UserProfilePage userProfilePage = taskList.openMyProfilePage();
    userProfilePage.selectLanguage(index);
    userProfilePage.save();
    NavigationHelper.navigateToTaskList();
  }

  @AfterEach
  public void resetlanguage() {
    resetLanguageOfCurrentUser();
  }
}

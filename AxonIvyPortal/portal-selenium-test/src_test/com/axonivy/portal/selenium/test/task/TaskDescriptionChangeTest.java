package com.axonivy.portal.selenium.test.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;

@IvyWebTest
public class TaskDescriptionChangeTest extends BaseTest {

  private TaskWidgetPage taskWidgetPage;

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
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.openTaskDetails(firstTask);
    testChangeTaskDescription("Hello World!", "Hello World!", taskWidgetPage);
    testChangeTaskDescription(
        "<b>HTML</b> description could contain malicious script <script>alert('Attacking')</script> but it will be sanitized.",
        "HTML description could contain malicious script but it will be sanitized.", taskWidgetPage);
    testChangeTaskDescription("", "No description", taskWidgetPage);
    testChangeTaskDescription("And you can change description if it is empty",
        "And you can change description if it is empty", taskWidgetPage);
  }

  private void testChangeTaskDescription(String newDescription, String shownDescriptionInDetails,
      TaskWidgetPage taskWidgetPage) {
    taskWidgetPage.changeDescriptionOfTask(newDescription);
    assertEquals(taskWidgetPage.getTaskDescription(), shownDescriptionInDetails);
  }

  @Test
  public void testUserWithoutPermissionCannotChangeTaskName() {
    int firstTask = 0;
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.openTaskDetails(firstTask);
    assertFalse(taskWidgetPage.isTaskNameChangeComponentPresented(firstTask));
  }


  public void changeLanguage(int index) {
    UserProfilePage userProfilePage = taskWidgetPage.openMyProfilePage();
    userProfilePage.selectLanguage(index);
    userProfilePage.save();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
  }

  @AfterEach
  public void resetlanguage() {
    resetLanguageOfCurrentUser();
  }
}

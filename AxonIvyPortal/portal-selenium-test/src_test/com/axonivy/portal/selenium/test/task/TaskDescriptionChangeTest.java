package com.axonivy.portal.selenium.test.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.HomePage;
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
    userProfilePage.saveAndGoToHomePage();
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
  }

  /**
   * Note: commit Id: 05805eb9247ae0fafd3488a411a2302aa7fd422f and 3ac1a13297902a94218850373362d5f3cd504a52
   */
  @Test
  @Disabled("This test is ignored because it need specific configuration on engine to run correctly")
  public void testChangeTaskDescriptionInMultiLanguage() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToNewDashBoard();
    login(TestAccount.ADMIN_USER);
    int firstTask = 0;
    var taskNameEn = "Maternity Leave Request";
    var taskNameGer = "Antrag auf Mutterschaftsurlaub";
    var taskDescriptionEn = "Hello World! - English";
    var taskDescriptionGer = "Hello World! - German";
    HomePage homePage = new HomePage();
    taskWidgetPage = homePage .getTaskWidget();
    taskWidgetPage.expand();
    taskWidgetPage.filterTasksInExpandedModeBy(taskNameEn);
    taskWidgetPage.openTaskDetails(firstTask);
    testChangeTaskDescription(taskDescriptionEn, taskDescriptionEn, taskWidgetPage);
    
    // Change language to German then change description
    changeLanguage(3);
    taskWidgetPage.filterTasksInExpandedModeBy(taskNameGer);
    taskWidgetPage.openTaskDetails(firstTask);
    testChangeTaskDescription(taskDescriptionGer, taskDescriptionGer, taskWidgetPage);

    // Change to English then verify description
    changeLanguage(1);
    taskWidgetPage.filterTasksInExpandedModeBy(taskNameEn);
    taskWidgetPage.openTaskDetails(firstTask);
    assertEquals(taskWidgetPage.getTaskDescription(), taskDescriptionEn);

    // Change to German then verify description
    changeLanguage(3);
    taskWidgetPage.filterTasksInExpandedModeBy(taskNameGer);
    taskWidgetPage.openTaskDetails(firstTask);
    assertEquals(taskWidgetPage.getTaskDescription(), taskDescriptionGer);
  }

  @AfterEach
  public void resetlanguage() {
    resetLanguageOfCurrentUser();
  }
  
}

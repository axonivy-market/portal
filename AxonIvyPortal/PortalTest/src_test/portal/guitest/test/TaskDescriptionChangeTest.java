package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.Variable;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.UserProfilePage;

public class TaskDescriptionChangeTest extends BaseTest {

  private TaskWidgetPage taskWidgetPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
  }

  @Test
  public void testChangeTaskDescription() {
    login(TestAccount.ADMIN_USER);
    int firstTask = 0;
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    testChangeTaskDescription("Hello World!", "Hello World!", taskWidgetPage);
    testChangeTaskDescription(
        "<b>HTML</b> description could contain malicious script <script>alert('Attacking')</script> but it will be sanitized.",
        "HTML description could contain malicious script but it will be sanitized.", taskWidgetPage);
    testChangeTaskDescription("", "No description", taskWidgetPage);
    testChangeTaskDescription("And you can change description if it is empty",
        "And you can change description if it is empty", taskWidgetPage);
  }

  private void testChangeTaskDescription(String newDescription, String shownDescriptionInDetails, TaskWidgetPage taskWidgetPage) {
    taskWidgetPage.changeDescriptionOfTask(newDescription);
    assertEquals(taskWidgetPage.getTaskDescription(), shownDescriptionInDetails);
  }

  @Test
  public void testUserWithoutPermissionCannotChangeTaskName() {
    int firstTask = 0;
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    assertFalse(taskWidgetPage.isTaskNameChangeComponentPresented(firstTask));
  }

  @Test
  public void testChangeTaskDescriptionInMultiLanguage() {
    login(TestAccount.ADMIN_USER);
    int firstTask = 0;
    var taskNameEn = "Maternity Leave Request";
    var taskNameGer = "Antrag auf Mutterschaftsurlaub";
    var taskDescriptionEn = "Hello World! - English";
    var taskDescriptionGer = "Hello World! - German";
    taskWidgetPage = new TaskWidgetPage();
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
  
  public void changeLanguage(int index) {
    UserProfilePage userProfilePage = taskWidgetPage.openMyProfilePage();
    userProfilePage.selectLanguage(index);
    userProfilePage.save();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
  }
  
  @After
  public void resetlanguage() {
    resetLanguageOfCurrentUser();
  }
}

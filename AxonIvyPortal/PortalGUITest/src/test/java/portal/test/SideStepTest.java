package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.AdhocPage;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.TaskTemplatePage;
import portal.page.TaskWidgetPage;

public class SideStepTest extends BaseTest {

  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    navigateToCaseMapUrl(createTestingCaseMapUrl);
  }

  @Test
  public void testSideSteps() {
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.openSideStepMenu();
    assertEquals(2, taskTemplatePage.countSideSteps());
  }

  private TaskTemplatePage startATask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    return taskTemplatePage;
  }

  @Test
  public void testAddAdhocTask() {
    int firstTask = 0;
    final String TASK_NAME = "Create Leave Request";
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertTrue(taskWidgetPage.countTasks() == 1);
    assertEquals(taskWidgetPage.getNameOfTaskAt(0), TASK_NAME);
    taskWidgetPage.expand();
    AdhocPage adhocPage = taskWidgetPage.addAdhoc(firstTask);
    adhocPage.enterSubject("Collect Information");
    adhocPage.addTask();
    taskWidgetPage = adhocPage.startWorkflow();
    assertTrue(taskWidgetPage.countTasks() == 1);
    assertEquals(taskWidgetPage.getNameOfTaskAt(0), "TASK Collect Information");
    taskWidgetPage.startTask(0);
    adhocPage = new AdhocPage();
    adhocPage.addComment("Annual leaves are available");
    taskWidgetPage = adhocPage.send();
    assertTrue(taskWidgetPage.countTasks() == 1);
    assertEquals(taskWidgetPage.getNameOfTaskAt(0), TASK_NAME);
  }
}

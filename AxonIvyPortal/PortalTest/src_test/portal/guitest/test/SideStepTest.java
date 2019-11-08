package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.AdhocPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class SideStepTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  @Ignore
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
  @Ignore
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
  
  @Test
  public void testSideStepInCaseList() {
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    int sideSteps = casePage.countSideStepItems();
    assertEquals(2, sideSteps);
  }
}

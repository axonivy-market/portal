package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.page.AdhocPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class SideStepTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingCaseMapUrl);
  }

  @Test
  @Ignore
  public void testSideSteps() {
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.clickTaskActionMenu();
    assertEquals(2, taskTemplatePage.countSideSteps());
  }

  private TaskTemplatePage startATask() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTasList();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    return taskTemplatePage;
  }

  @Test
  @Ignore
  public void testAddAdhocTask() {
    int firstTask = 0;
    final String TASK_NAME = "Create Leave Request";
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTasList();
    assertTrue(taskWidgetPage.countTasks() == 1);
    assertEquals(taskWidgetPage.getNameOfTaskAt(0), TASK_NAME);
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
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    MainMenuPage mainMenuPage = newDashboardPage2.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    int sideSteps = casePage.countSideStepItems();
    assertEquals(2, sideSteps);
  }
}

package portal.guitest.test;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.ExpressFormDefinitionPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.TemplatePage.GlobalSearch;
import portal.guitest.page.WorkingTaskDialogPage;
import portal.guitest.page.WorkingTaskDialogPageOfApplicationMenu;

public class GlobalGrowlTest extends BaseTest {

  private static final String TASK_FINISHED_WITH_DETAILS =
      "You have finished the task successfully. Click here for details.";
  private static final String TASK_LEFT_WITH_DETAILS =
      "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list. Click here for details.";
  private static final String TASK_LEFT =
      "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.";
  private static final String CUSTOM_GROWL_URL = "portalExamples/16A7BB2ADC9580A8/start.ivp";
  private static final String SKIP_TASK_LIST_URL = "portalExamples/16FA8B451814E32A/start.ivp";

  @Test
  public void testDisplayCustomGrowlAfterFinishTask() {
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertEquals("Task is done successfully", homePage.getGlobalGrowlMessage());
  }

  @Test
  public void testDisplayDefaultGrowlAfterFinishTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.inputFields("Employee", "1.1.2019", "1.1.2019", "Representation");
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertEquals(TASK_FINISHED_WITH_DETAILS, homePage.getGlobalGrowlMessage());
  }

  @Test
  public void testDisplayDefaultGrowlAfterFinishFirstTask() {
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.inputFields("Employee", "1.1.2019", "1.1.2019", "Representation");
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertEquals(TASK_FINISHED_WITH_DETAILS, homePage.getGlobalGrowlMessage());
  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertEquals(TASK_LEFT_WITH_DETAILS, homePage.getGlobalGrowlMessage());
  }

  @Test
  public void testDisplayCustomGrowlAfterCancelTask() {
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertEquals("You have cancelled and left the task successfully", homePage.getGlobalGrowlMessage());
  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelFirstTask() {
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertEquals(TASK_LEFT_WITH_DETAILS, homePage.getGlobalGrowlMessage());
  }

  @Test
  public void testTaskLeft() {
    leftTaskWhenClickingOnLogo();
    leftTaskWhenClickingOnMenu();
    leftTaskWhenGlobalSearch();
    leftExpressWorkflowDefinition();
    leftExpressFormDefinition();
    /////////////// SCENARIO ///////////////
    // finishing creating express process, show growl, SEE PortalExpressTest#testAbleToStartAdministratedWorkflow
  }

  private void leftExpressFormDefinition() {
    redirectToRelativeLink(createExpressProcess);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test approval", "Test description");
    expressProcessPage.createTask(0, 0, "Task 1", "Task 1 description",
        Arrays.asList(new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false)));
    ExpressFormDefinitionPage expressFormDefinitionPage = expressProcessPage.goToFormDefinition();
    HomePage homePage = expressFormDefinitionPage.cancel();
    assertEquals(TASK_LEFT, homePage.getGlobalGrowlMessage());
  }

  private void leftExpressWorkflowDefinition() {
    redirectToRelativeLink(createExpressProcess);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.cancel();
    assertEquals(TASK_LEFT, expressProcessPage.getGlobalGrowlMessage());
  }

  private void leftTaskWhenGlobalSearch() {
    redirectToRelativeLink(createExpressProcess);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    GlobalSearch globalSearch = expressProcessPage.getGlobalSearch();
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword("a");
    assertEquals(TASK_LEFT, searchResultPage.getGlobalGrowlMessage());
  }

  private void leftTaskWhenClickingOnMenu() {
    redirectToRelativeLink(createExpressProcess);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    MainMenuPage mainMenuPage = expressProcessPage.openMainMenu();
    WorkingTaskDialogPageOfApplicationMenu leaveTaskDialogOfMenu = mainMenuPage.selectDashboardMenu();
    HomePage homePage = leaveTaskDialogOfMenu.leaveTask();
    assertEquals(TASK_LEFT, homePage.getGlobalGrowlMessage());
  }

  private void leftTaskWhenClickingOnLogo() {
    redirectToRelativeLink(createExpressProcess);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    HomePage homePage = dialogPage.leaveTask();
    assertEquals(TASK_LEFT, homePage.getGlobalGrowlMessage());
  }

}

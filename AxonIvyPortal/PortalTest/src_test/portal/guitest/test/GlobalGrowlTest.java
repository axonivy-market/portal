package portal.guitest.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.DateTimePattern;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.ExpressFormDefinitionPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.TemplatePage;
import portal.guitest.page.TemplatePage.GlobalSearch;
import portal.guitest.page.WorkingTaskDialogPage;
import portal.guitest.page.WorkingTaskDialogPageOfApplicationMenu;

public class GlobalGrowlTest extends BaseTest {

  private static final String CUSTOM_FINISH_MESSAGE = "Task is done successfully\nClick here for details.";
  private static final String FINISH_MESSAGE = "You have finished the task successfully";
  private static final String FINISH_MESSAGE_WITH_DETAILS = "You have finished the task successfully.\nClick here for details.";
  private static final String CUSTOM_CANCEL_MESSAGE = "You have cancelled and left the task successfully\nClick here for details.";
  private static final String CANCEL_MESSAGE = "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.";
  private static final String CANCEL_MESSAGE_WITH_DETAILS = "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.\nClick here for details.";
  private static final String CUSTOM_GROWL_URL = "portal-developer-examples/16A7BB2ADC9580A8/start.ivp";
  private static final String SKIP_TASK_LIST_URL = "portal-developer-examples/16FA8B451814E32A/start.ivp";
  
  @Test
  public void testDisplayCustomGrowlAfterFinishTask() {
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertGrowlMessage(homePage, CUSTOM_FINISH_MESSAGE);
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterFinishTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    String today =  LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskTemplatePage.inputFields("Employee", today, today, "Representation");
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertGrowlMessage(homePage, FINISH_MESSAGE_WITH_DETAILS);
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterFinishFirstTask() {
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    String today =  LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskTemplatePage.inputFields("Employee", today, today, "Representation");
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertGrowlMessage(homePage, FINISH_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertGrowlMessage(homePage, CANCEL_MESSAGE_WITH_DETAILS); 
  }
  
  @Test
  public void testDisplayCustomGrowlAfterCancelTask() {
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertGrowlMessage(homePage, CUSTOM_CANCEL_MESSAGE);
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterCancelFirstTask() {
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertGrowlMessage(homePage, CANCEL_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testSaveExpressFormDefinition() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(false, true, "Test approval", "Test description");
    ExpressFormDefinitionPage formDefinition = configureExpressProcess(expressProcessPage);
    HomePage homePage = formDefinition.save();
    assertGrowlMessage(homePage, FINISH_MESSAGE_WITH_DETAILS);
  }
  
  @Test
  public void testFinishExpressFormDefinition() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(false, true, "Test approval", "Test description");
    ExpressFormDefinitionPage formDefinition = configureExpressProcess(expressProcessPage);
    formDefinition.finishWorkflow();
    HomePage homePage = new HomePage();
    assertGrowlMessage(homePage, FINISH_MESSAGE);
  }
  
  private ExpressFormDefinitionPage configureExpressProcess(ExpressProcessPage expressProcessPage) {
    ExpressResponsible responsible = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
    expressProcessPage.createTask(0, 0, "Task 1", "Task 1 description", Arrays.asList(responsible));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createTextInputField("Input Text", 0, false);
    formDefinition.moveAllElementToDragAndDrogPanel();
    return formDefinition;
  }

  @Test
  public void testTaskLeft() {
    leftTaskWhenClickingOnLogo();
    leftTaskWhenClickingOnMenu();
    leftTaskWhenGlobalSearch();
    leftExpressWorkflowDefinition();
    leftExpressFormDefinition();
  }

  private void leftExpressFormDefinition() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test approval", "Test description");
    expressProcessPage.createTask(0, 0, "Task 1", "Task 1 description",
        Arrays.asList(new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false)));
    ExpressFormDefinitionPage expressFormDefinitionPage = expressProcessPage.goToFormDefinition();
    HomePage homePage = expressFormDefinitionPage.cancel();
    assertGrowlMessage(homePage, CANCEL_MESSAGE);
  }

  private void leftExpressWorkflowDefinition() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    HomePage homePage = expressProcessPage.cancelWorkflowDefinition();
    assertGrowlMessage(homePage, CANCEL_MESSAGE);
  }

  private void leftTaskWhenGlobalSearch() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    GlobalSearch globalSearch = expressProcessPage.getGlobalSearch();
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword("a");
    assertGrowlMessage(searchResultPage, CANCEL_MESSAGE);
  }

  private void leftTaskWhenClickingOnMenu() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    MainMenuPage mainMenuPage = expressProcessPage.openMainMenu();
    WorkingTaskDialogPageOfApplicationMenu leaveTaskDialogOfMenu = mainMenuPage.selectDashboardMenu();
    HomePage homePage = leaveTaskDialogOfMenu.leaveTask();
    assertGrowlMessage(homePage, CANCEL_MESSAGE);
  }

  private void leftTaskWhenClickingOnLogo() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    HomePage homePage = dialogPage.leaveTask();
    assertGrowlMessage(homePage, CANCEL_MESSAGE);
  }

  private void assertGrowlMessage(TemplatePage templatePage, String message) {
    WaitHelper.assertTrueWithWait(() -> templatePage.getGlobalGrowlMessage().equals(message));
  }
}

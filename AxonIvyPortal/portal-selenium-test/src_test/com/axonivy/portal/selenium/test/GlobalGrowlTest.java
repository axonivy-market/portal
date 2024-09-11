package com.axonivy.portal.selenium.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.ExpressFormDefinitionPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
import com.axonivy.portal.selenium.page.GlobalGrowlIframeTemplatePage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.SearchResultPage;
import com.axonivy.portal.selenium.page.TaskIFrameTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.TemplatePage;
import com.axonivy.portal.selenium.page.TemplatePage.GlobalSearch;
import com.axonivy.portal.selenium.page.WorkingTaskDialogPage;
import com.axonivy.portal.selenium.page.WorkingTaskDialogPageOfApplicationMenu;
import com.codeborne.selenide.Condition;

import ch.ivyteam.ivy.project.portal.test.ExpressResponsible;

@IvyWebTest
public class GlobalGrowlTest extends BaseTest {

  private static final String FINISH_MESSAGE = "You have finished the task successfully";
  private static final String FINISH_MESSAGE_WITH_DETAILS =
      "You have finished the task successfully.\nClick here for details.";
  private static final String CANCEL_MESSAGE =
      "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.";
  private static final String CANCEL_MESSAGE_WITH_DETAILS =
      "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.\nClick here for details.";
  private static final String GROWL_STANDARD_MESSAGE_URL =
      "portal-developer-examples/16A7BB2ADC9580A8/frameStandardMessage.ivp";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void testDisplayDefaultGrowlAfterFinishTaskWithoutIFrame() {
    redirectToRelativeLink(createTestingTasksUrl);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskIFrameTemplatePage taskTemplatePage = taskWidgetPage.startTaskIFrame(0);
    String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskTemplatePage.inputValue("Employee", today, today, "Representation");
    taskTemplatePage.clickOnSubmitButton();
    taskTemplatePage.switchBackToParent();
    taskWidgetPage = new TaskWidgetPage();
    assertGrowlMessage(taskWidgetPage, FINISH_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testDisplayDefaultGrowlAfterFinishTaskWithIFrame() {
    redirectToRelativeLink(GROWL_STANDARD_MESSAGE_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Growl Standard Message", 1);
    taskWidgetPage.startTaskIFrame(0);
    GlobalGrowlIframeTemplatePage taskPage = new GlobalGrowlIframeTemplatePage();
    TaskWidgetPage returnedTaskWidgetPage = taskPage.clickProceed();
    assertGrowlMessage(returnedTaskWidgetPage, FINISH_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelTaskWithoutIFrame() {
    redirectToRelativeLink(createTestingTasksUrl);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    TaskWidgetPage taskWidgetPage = newDashboardPage.openTaskList();
    taskWidgetPage.waitForPageLoad();
    TaskIFrameTemplatePage taskTemplatePage = taskWidgetPage.startTaskIFrame(0);
    taskTemplatePage.waitForIFrameContentVisible();
    taskTemplatePage.clickOnCancelButton();
    taskTemplatePage.switchBackToParent();
    assertGrowlMessage(taskWidgetPage, CANCEL_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelTaskWithIFrame() {
    redirectToRelativeLink(GROWL_STANDARD_MESSAGE_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Growl Standard Message", 1);
    taskWidgetPage.startTaskIFrame(0);
    GlobalGrowlIframeTemplatePage taskPage = new GlobalGrowlIframeTemplatePage();
    TaskWidgetPage returnedTaskWidgetPage = taskPage.clickCancel();
    assertGrowlMessage(returnedTaskWidgetPage, CANCEL_MESSAGE_WITH_DETAILS);
  }

  public void waitForTemplateRender() {
    WaitHelper.waitForPresenceOfElementLocatedInFrame("[class$='task-template-container']");
  }

  @Test
  public void testSaveExpressFormDefinition() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(false, true, "Test approval", "Test description");
    ExpressFormDefinitionPage formDefinition = configureExpressProcess(expressProcessPage);
    NewDashboardPage newDashboardPage = formDefinition.save();
    assertGrowlMessage(newDashboardPage, FINISH_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testFinishExpressFormDefinition() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(false, true, "Test approval1", "Test description");
    ExpressFormDefinitionPage formDefinition = configureExpressProcess(expressProcessPage);
    formDefinition.finishWorkflow();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    assertGrowlMessage(newDashboardPage, FINISH_MESSAGE);
  }

  @Test
  public void testTaskLeft() {
    leftTaskWhenClickingOnLogo();
    leftTaskWhenClickingOnMenu();
    leftTaskWhenGlobalSearch();
    leftExpressWorkflowDefinition();
    leftExpressFormDefinition();
  }

  private ExpressFormDefinitionPage configureExpressProcess(ExpressProcessPage expressProcessPage) {
    ExpressResponsible responsible = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
    expressProcessPage.createTask(0, 0, "Task 1", "Task 1 description", Arrays.asList(responsible));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));
    formDefinition.createTextInputField("Input Text", 0, false);
    formDefinition.countElementPrepareToDrag(1);
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.countElementPrepareToDrag(0);
    return formDefinition;
  }

  private void leftExpressWorkflowDefinition() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    NewDashboardPage newDashboardPage = expressProcessPage.cancelWorkflowDefinition();
    assertGrowlMessage(newDashboardPage, CANCEL_MESSAGE);
  }

  private void leftTaskWhenGlobalSearch() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    GlobalSearch globalSearch = expressProcessPage.getGlobalSearch();
    SearchResultPage searchResultPage = globalSearch.inputSearchKeywordForWorkingTask("a");
    assertGrowlMessage(searchResultPage, CANCEL_MESSAGE);
  }

  private void leftTaskWhenClickingOnMenu() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    MainMenuPage mainMenuPage = expressProcessPage.openMainMenu();
    WorkingTaskDialogPageOfApplicationMenu leaveTaskDialogOfMenu = mainMenuPage.selectDashboardMenu();
    NewDashboardPage newDashboardPage = leaveTaskDialogOfMenu.leaveTask();
    assertGrowlMessage(newDashboardPage, CANCEL_MESSAGE);
  }

  private void leftTaskWhenClickingOnLogo() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    NewDashboardPage newDashboardPage = dialogPage.leaveTask();
    assertGrowlMessage(newDashboardPage, CANCEL_MESSAGE);
  }

  private void leftExpressFormDefinition() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test approval", "Test description");
    expressProcessPage.createTask(0, 0, "Task 1", "Task 1 description",
        Arrays.asList(setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false)));
    ExpressFormDefinitionPage expressFormDefinitionPage = expressProcessPage.goToFormDefinition();
    NewDashboardPage newDashboardPage = expressFormDefinitionPage.cancel();
    assertGrowlMessage(newDashboardPage, CANCEL_MESSAGE);
  }

  private void assertGrowlMessage(TemplatePage templatePage, String message) {
    templatePage.getGlobalGrowlMessage().shouldBe(Condition.text(message));
  }
}

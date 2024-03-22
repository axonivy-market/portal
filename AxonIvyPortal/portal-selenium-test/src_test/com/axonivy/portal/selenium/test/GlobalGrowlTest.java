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
import com.axonivy.portal.selenium.page.ExpressFormDefinitionPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
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
  private static final String CUSTOM_FINISH_MESSAGE = "Task is done successfully\nClick here for details.";
  private static final String CUSTOM_CANCEL_MESSAGE =
      "You have cancelled and left the task successfully\nClick here for details.";
  private static final String CUSTOM_GROWL_URL =
      "portal-developer-examples/16A7BB2ADC9580A8/frame8CustomizedMessage.ivp";
  private static final String SKIP_TASK_LIST_URL =
      "portal-developer-examples/16A7BB2ADC9580A8/frame8StandardMessageAndSkipTasklist.ivp";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
  }

  // TODO Write test for Growl in IFrame, in version 10 it has the test public void
  // This test is ignored cause by this bug: https://1ivy.atlassian.net/browse/IVYPORTAL-16400
  // @Test
  public void testDisplayCustomGrowlAfterFinishTask() {
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskIFrameTemplatePage taskTemplatePage = taskWidgetPage.startTaskIFrame(0);
    taskTemplatePage.clickSubmitButtonProceed();
    taskTemplatePage.switchBackToParent();
    assertGrowlMessage(taskWidgetPage, CUSTOM_FINISH_MESSAGE);
  }

  @Test
  public void testDisplayDefaultGrowlAfterFinishTask() {
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

  // TODO Write test for Growl in IFrame, in version 10 it has the test public void
  // This test is ignored cause by this bug: https://1ivy.atlassian.net/browse/IVYPORTAL-16400
  // @Test
  public void testDisplayDefaultGrowlAfterFinishFirstTask() {
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskIFrameTemplatePage taskTemplatePage = taskWidgetPage.startTaskIFrame(0);
    taskTemplatePage.clickSubmitButtonProceed();
    taskTemplatePage.switchBackToParent();
    TaskWidgetPage homePage = new TaskWidgetPage();
    assertGrowlMessage(homePage, FINISH_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskIFrameTemplatePage taskTemplatePage = taskWidgetPage.startTaskIFrame(0);
    taskTemplatePage.clickCancelAndLeftButton();
    taskTemplatePage.waitPageDisappear();
    taskTemplatePage.switchBackToParent();
    taskWidgetPage = new TaskWidgetPage();
    assertGrowlMessage(taskWidgetPage, CANCEL_MESSAGE_WITH_DETAILS);
  }

  // TODO Write test for Growl in IFrame, in version 10 it has the test public void
  // This test is ignored cause by this bug: https://1ivy.atlassian.net/browse/IVYPORTAL-16400
  // @Test
  public void testDisplayCustomGrowlAfterCancelTask() {
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskIFrameTemplatePage taskTemplatePage = taskWidgetPage.startTaskIFrame(0);
    taskTemplatePage.clickCancelAndLeftButton();//
    taskTemplatePage.switchBackToParent();
    taskWidgetPage = new TaskWidgetPage();
    assertGrowlMessage(taskWidgetPage, CUSTOM_CANCEL_MESSAGE);
  }

  // TODO Write test for Growl in IFrame, in version 10 it has the test public void
  // This test is ignored cause by this bug: https://1ivy.atlassian.net/browse/IVYPORTAL-16400
  // @Test
  public void testDisplayDefaultGrowlAfterCancelFirstTask() {
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskIFrameTemplatePage taskTemplatePage = new TaskIFrameTemplatePage();
    taskTemplatePage.switchToIFrameOfTask();
    taskTemplatePage.clickCancelAndLeftButton();
    taskTemplatePage = new TaskIFrameTemplatePage();
    assertGrowlMessage(taskTemplatePage, CANCEL_MESSAGE_WITH_DETAILS);
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

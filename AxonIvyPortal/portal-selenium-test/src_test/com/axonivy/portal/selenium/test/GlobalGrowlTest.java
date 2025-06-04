package com.axonivy.portal.selenium.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.bean.ExpressResponsible;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.ExpressFormDefinitionPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessViewerPage;
import com.axonivy.portal.selenium.page.SearchResultPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.TemplatePage;
import com.axonivy.portal.selenium.page.TemplatePage.GlobalSearch;
import com.axonivy.portal.selenium.page.WorkingTaskDialogPage;
import com.codeborne.selenide.Condition;

import io.github.bonigarcia.wdm.WebDriverManager;


@IvyWebTest
public class GlobalGrowlTest extends BaseTest {

  private static final String FINISH_MESSAGE = "You have finished the task successfully";
  private static final String FINISH_MESSAGE_WITH_DETAILS =
      "You have finished the task successfully.\nClick here for details.";

  private static final String CANCEL_MESSAGE =
      "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.";
  private static final String CANCEL_MESSAGE_WITH_DETAILS =
      "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.\nClick here for details.";
  private static final String CUSTOM_CANCEL_MESSAGE = "You have cancelled and left the task successfully\nClick here for details.";
  private static final String CLOSE_PROCESS_VIEWER_MESSAGE = "You closed the process viewer.";
  private static final String CUSTOM_FINISH_MESSAGE = "Task is done successfully\nClick here for details.";
  private static final String CUSTOM_GROWL_URL = "portal-developer-examples/16A7BB2ADC9580A8/start.ivp";
  private static final String SKIP_TASK_LIST_URL = "portal-developer-examples/16FA8B451814E32A/start.ivp";


  @Override
  @BeforeEach
  public void setup() {
    WebDriverManager.firefoxdriver().setup();

    super.setup();
    login(TestAccount.ADMIN_USER);
  }
  
  @Test
  public void testDisplayCustomGrowlAfterFinishTask() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidget = homePage.getTaskWidget();
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskWidget.startTaskInLegacyMode(0);
    taskTemplatePage.clickSubmitButton();
    assertGrowlMessage(new HomePage(), CUSTOM_FINISH_MESSAGE);
  }

  @Test
  public void testDisplayDefaultGrowlAfterFinishTask() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(createTestingTasksUrl);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    NavigationHelper.navigateToDeveloperExamplesTaskList();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskTemplatePage.inputValue("Employee", today, today, "Representation");
    taskTemplatePage.clickSubmitButton();
    assertGrowlMessage(taskWidgetPage, FINISH_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testDisplayDefaultGrowlAfterFinishFirstTask() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    String today =  LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskTemplatePage.inputValue("Employee", today, today, "Representation");
    taskTemplatePage.clickSubmitButton();
    HomePage homePage = new HomePage();
    assertGrowlMessage(homePage, FINISH_MESSAGE_WITH_DETAILS);

  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelTask() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(createTestingTasksUrl);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    NavigationHelper.navigateToDeveloperExamplesTaskList();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskWidgetPage = taskTemplatePage.clickCancelAndLeftButton();
    assertGrowlMessage(taskWidgetPage, CANCEL_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testDisplayCustomGrowlAfterCancelTask() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTaskInLegacyMode(0);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.clickCancelAndLeftButton();
    HomePage homePage =  new HomePage();
    assertGrowlMessage(homePage, CUSTOM_CANCEL_MESSAGE);
  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelFirstTask() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.clickCancelAndLeftButton();
    HomePage homePage = new HomePage();
    assertGrowlMessage(homePage, CANCEL_MESSAGE_WITH_DETAILS);
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

  @Test
  public void testLeftExpressWorkflowDefinitionScenario() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    NewDashboardPage newDashboardPage = expressProcessPage.cancelWorkflowDefinition();
    assertGrowlMessage(newDashboardPage, CANCEL_MESSAGE);
  }

  @Test
  public void testLeftTaskWhenGlobalSearchScenario() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    GlobalSearch globalSearch = expressProcessPage.getGlobalSearch();
    SearchResultPage searchResultPage = globalSearch.inputSearchKeywordForWorkingTask("a");
    assertGrowlMessage(searchResultPage , CANCEL_MESSAGE);
  }

  @Test
  public void testLeftTaskWhenClickingOnMenuScenario() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.clickOnLogo();
    WorkingTaskDialogPage leaveTaskDialogOfMenu = new WorkingTaskDialogPage();
    NewDashboardPage newDashboardPage = leaveTaskDialogOfMenu.leaveTask();
    assertGrowlMessage(newDashboardPage, CANCEL_MESSAGE);
  }

  @Test
  public void testLeftTaskWhenClickingOnLogoScenario() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    NewDashboardPage newDashboardPage = dialogPage.leaveTask();
    assertGrowlMessage(newDashboardPage, CANCEL_MESSAGE);
  }

  @Test
  public void testLeftExpressFormDefinitionScenario() {
    redirectToRelativeLink(expressStartLink);
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test approval", "Test description");
    expressProcessPage.createTask(0, 0, "Task 1", "Task 1 description",
        Arrays.asList(setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false)));
    ExpressFormDefinitionPage expressFormDefinitionPage = expressProcessPage.goToFormDefinition();
    NewDashboardPage newDashboardPage = expressFormDefinitionPage.cancel();
    assertGrowlMessage(newDashboardPage, CANCEL_MESSAGE);
  }

  @Test
  public void testDisplayDefaultGrowlAfterCloseProcessViewer() {
    redirectToRelativeLink(createTestingTasksUrl);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    TaskWidgetPage taskWidgetPage = newDashboardPage.openTaskList();
    taskWidgetPage.openTaskProcessViewer(0);
    newDashboardPage.switchLastBrowserTab();
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    processViewerPage.clickOnCloseButton();
    assertGrowlMessage(taskWidgetPage, CLOSE_PROCESS_VIEWER_MESSAGE);
  }
  
  private void assertGrowlMessage(TemplatePage templatePage, String message) {
    templatePage.getGlobalGrowlMessage().shouldBe(Condition.text(message));
  }
}

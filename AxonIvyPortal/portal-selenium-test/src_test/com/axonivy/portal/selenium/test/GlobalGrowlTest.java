package com.axonivy.portal.selenium.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.GlobalGrowlIframeTemplatePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessViewerPage;
import com.axonivy.portal.selenium.page.TaskIFrameTemplatePage;
import com.axonivy.portal.selenium.page.TemplatePage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class GlobalGrowlTest extends BaseTest {

  private static final String FINISH_MESSAGE_WITH_DETAILS =
      "You have finished the task successfully.\nClick here for details.";
  private static final String CANCEL_MESSAGE_WITH_DETAILS =
      "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.\nClick here for details.";
  private static final String CLOSE_PROCESS_VIEWER_MESSAGE = "You closed the process view.";
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
    NewDashboardPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    TaskIFrameTemplatePage taskTemplatePage = taskWidget.startTaskIFrameByIndex(0);
    String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskTemplatePage.inputValue("Employee", today, today, "Representation");
    taskTemplatePage.clickOnSubmitButton();
    taskTemplatePage.switchBackToParent();
    taskWidgetPage = new NewDashboardPage();
    assertGrowlMessage(taskWidgetPage, FINISH_MESSAGE_WITH_DETAILS);
  }

  
  @Test
  public void testDisplayDefaultGrowlAfterFinishTaskWithIFrame() {
    redirectToRelativeLink(GROWL_STANDARD_MESSAGE_URL);
    NewDashboardPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Growl Standard Message");
    taskWidget.applyFilter();
    taskWidget.startTaskIFrameByIndex(0);
    GlobalGrowlIframeTemplatePage taskPage = new GlobalGrowlIframeTemplatePage();
    taskWidgetPage = taskPage.clickProceed();
    assertGrowlMessage(taskWidgetPage, FINISH_MESSAGE_WITH_DETAILS);
  }


  @Test
  public void testDisplayDefaultGrowlAfterCancelTaskWithoutIFrame() {
    redirectToRelativeLink(createTestingTasksUrl);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    NewDashboardPage taskWidgetPage = newDashboardPage.openTaskList();
    
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.waitForPageLoad();
    TaskIFrameTemplatePage taskTemplatePage = taskWidget.startTaskIFrameByIndex(0);
    taskTemplatePage.waitForIFrameContentVisible();
    taskTemplatePage.clickOnCancelButton();
    taskTemplatePage.switchBackToParent();
    assertGrowlMessage(taskWidgetPage, CANCEL_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelTaskWithIFrame() {
    redirectToRelativeLink(GROWL_STANDARD_MESSAGE_URL);
    NewDashboardPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Growl Standard Message");
    taskWidget.applyFilter();
    taskWidget.startTaskIFrameByIndex(0);
    taskWidget.waitForPageLoad();
    GlobalGrowlIframeTemplatePage taskPage = new GlobalGrowlIframeTemplatePage();
    taskWidgetPage = taskPage.clickCancel();
    assertGrowlMessage(taskWidgetPage, CANCEL_MESSAGE_WITH_DETAILS);
  }

  @Test
  public void testDisplayDefaultGrowlAfterCloseProcessViewer() {
    redirectToRelativeLink(createTestingTasksUrl);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    NewDashboardPage taskWidgetPage = newDashboardPage.openTaskList();
    
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.waitForPageLoad();
    taskWidget.openTaskProcessViewer(0);
    WaitHelper.assertTrueWithWait(() -> newDashboardPage.countBrowserTab() > 1);
    taskWidget.switchLastBrowserTab();
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    processViewerPage.clickOnCloseButton();
    taskWidgetPage = new NewDashboardPage();
    assertGrowlMessage(taskWidgetPage, CLOSE_PROCESS_VIEWER_MESSAGE);
  }
  
  public void waitForTemplateRender() {
    WaitHelper.waitForPresenceOfElementLocatedInFrame("[class$='task-template-container']");
  }

  private void assertGrowlMessage(TemplatePage templatePage, String message) {
    templatePage.getGlobalGrowlMessage().shouldBe(Condition.text(message));
  }
}

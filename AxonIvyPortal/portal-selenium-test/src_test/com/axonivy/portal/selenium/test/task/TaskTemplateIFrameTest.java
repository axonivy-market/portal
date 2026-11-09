package com.axonivy.portal.selenium.test.task;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskIFrameTemplatePage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.WebDriverRunner;

@IvyWebTest
public class TaskTemplateIFrameTest extends BaseTest {

  private static final String CUSTOM_PARAMS_TEMPLATE_TASK_URL = "portal-developer-examples/1718293B3F6E5478/start.ivp";
  private static final String IFRAME_TASK_URL =
      "portal-developer-examples/16E5DB746865BCEC/CreateInvestment.ivp?embedInFrame";

  private static final String HEADER_MODE_DEFAULT = "DEFAULT";
  private static final String HEADER_MODE_LEGACY = "LEGACY";
  private static final String HEADER_MODE_HIDDEN = "HIDDEN";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.TASK_HEADER_MODE.getKey(), HEADER_MODE_DEFAULT);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
  }

  @AfterEach
  public void restoreTaskHeaderMode() {
    WebDriverRunner.getWebDriver().switchTo().defaultContent();
    updatePortalSetting(Variable.TASK_HEADER_MODE.getKey(), HEADER_MODE_DEFAULT);
  }

  @Test
  public void testDefaultTaskHeaderModeShowsHeader() {
    TaskIFrameTemplatePage taskTemplatePage = startApproveInvestmentTaskWithHeaderMode(HEADER_MODE_DEFAULT);
    taskTemplatePage.getTaskHeaderContainer().shouldBe(Condition.visible, DEFAULT_TIMEOUT)
        .shouldNot(Condition.cssClass("task-header-legacy"));
    taskTemplatePage.getTaskHeaderTitle().shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    taskTemplatePage.getTaskActionsElement().shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    taskTemplatePage.getCaseInfoButtonElement().shouldBe(Condition.visible, DEFAULT_TIMEOUT);
  }

  @Test
  public void testLegacyTaskHeaderModeKeepsHeaderControlsFunctional() {
    TaskIFrameTemplatePage taskTemplatePage = startApproveInvestmentTaskWithHeaderMode(HEADER_MODE_LEGACY);
    taskTemplatePage.getTaskHeaderContainer().shouldBe(Condition.visible, DEFAULT_TIMEOUT)
        .shouldHave(Condition.cssClass("task-header-legacy"));
    taskTemplatePage.getTaskActionsElement().shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    taskTemplatePage.getCaseInfoButtonElement().shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    taskTemplatePage.openCaseInfo();
    taskTemplatePage.getCaseItemDetails().shouldBe(Condition.visible, DEFAULT_TIMEOUT);
  }

  @Test
  public void testHiddenTaskHeaderModeRemovesHeaderElements() {
    TaskIFrameTemplatePage taskTemplatePage = startApproveInvestmentTaskWithHeaderMode(HEADER_MODE_HIDDEN);
    taskTemplatePage.getTaskHeaderContainer().shouldNot(Condition.exist, DEFAULT_TIMEOUT);
    taskTemplatePage.getTaskHeaderTitle().shouldNot(Condition.exist, DEFAULT_TIMEOUT);
    taskTemplatePage.getTaskActionsElement().shouldNot(Condition.exist, DEFAULT_TIMEOUT);
    taskTemplatePage.getCaseInfoButtonElement().shouldNot(Condition.exist, DEFAULT_TIMEOUT);
  }

  @Test
  public void testHiddenTaskHeaderModeRemovesVerticalProcessChain() {
    TaskIFrameTemplatePage taskTemplatePage = startVerticalLineChainTaskWithHeaderMode(HEADER_MODE_HIDDEN);
    taskTemplatePage.getTaskHeaderContainer().shouldNot(Condition.exist, DEFAULT_TIMEOUT);
    taskTemplatePage.getTaskHeaderTitle().shouldNot(Condition.exist, DEFAULT_TIMEOUT);
    taskTemplatePage.getTaskActionsElement().shouldNot(Condition.exist, DEFAULT_TIMEOUT);
    taskTemplatePage.getCaseInfoButtonElement().shouldNot(Condition.exist, DEFAULT_TIMEOUT);
    taskTemplatePage.getVerticalProcessChainContainer().shouldNot(Condition.exist, DEFAULT_TIMEOUT);
    taskTemplatePage.getTaskContentContainer().shouldBe(Condition.visible, DEFAULT_TIMEOUT)
        .shouldNotHave(Condition.cssClass("vertical-process-chain"))
        .shouldNotHave(Condition.cssClass("vertical-chain-shape-line"));
    taskTemplatePage.getIFrameLayoutContainer()
        .shouldNotHave(Condition.cssClass("vertical-process-chain"))
        .shouldNotHave(Condition.cssClass("vertical-chain-shape-line"));
    taskTemplatePage.getElementInPortalIFramTask("[id='form:invested-amount']")
        .shouldBe(Condition.visible, DEFAULT_TIMEOUT).shouldBe(Condition.editable)
        .setValue("1").shouldHave(Condition.value("1"));
  }

  @Test
  public void testVisibleTaskHeaderModeKeepsVerticalLineProcessChain() {
    resizeBrowserToFullHDResolution();
    TaskIFrameTemplatePage taskTemplatePage = startVerticalLineChainTaskWithHeaderMode(HEADER_MODE_DEFAULT);
    taskTemplatePage.getTaskHeaderContainer().shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    taskTemplatePage.getTaskHeaderTitle().shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    taskTemplatePage.getVerticalProcessChainContainer().shouldBe(Condition.visible, DEFAULT_TIMEOUT)
        .shouldHave(Condition.cssClass("vertical-chain-shape-line"));
    taskTemplatePage.getVerticalProcessChainSteps().shouldHave(CollectionCondition.sizeGreaterThan(0));
    taskTemplatePage.getTaskContentContainer().shouldBe(Condition.visible, DEFAULT_TIMEOUT)
        .shouldHave(Condition.cssClass("vertical-process-chain"))
        .shouldHave(Condition.cssClass("vertical-chain-shape-line"));
    taskTemplatePage.getIFrameLayoutContainer()
        .shouldHave(Condition.cssClass("vertical-process-chain"))
        .shouldHave(Condition.cssClass("vertical-chain-shape-line"));
    taskTemplatePage.getElementInPortalIFramTask("[id='form:invested-amount']")
        .shouldBe(Condition.visible, DEFAULT_TIMEOUT).shouldBe(Condition.editable)
        .setValue("1").shouldHave(Condition.value("1"));
  }

  private TaskIFrameTemplatePage startVerticalLineChainTaskWithHeaderMode(String headerMode) {
    updatePortalSetting(Variable.TASK_HEADER_MODE.getKey(), headerMode);
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    return new TaskIFrameTemplatePage();
  }

  private TaskIFrameTemplatePage startApproveInvestmentTaskWithHeaderMode(String headerMode) {
    updatePortalSetting(Variable.TASK_HEADER_MODE.getKey(), headerMode);
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    new TaskTemplatePage().finishCreateInvestmentTask();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Approve Investment");
    taskWidget.applyFilter();
    TaskIFrameTemplatePage taskTemplatePage = taskWidget.startTaskIFrameByIndex(0);
    taskTemplatePage.switchToDefaultContent();
    waitForTemplateRender();
    return taskTemplatePage;
  }

  @Test
  public void testCustomParamsForIFrameTaskTemplate() {
    redirectToRelativeLink(CUSTOM_PARAMS_TEMPLATE_TASK_URL);
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "IFrame task with custom params");
    taskWidget.applyFilter();
    TaskIFrameTemplatePage taskTemplatePage = taskWidget.startTaskIFrameByIndex(0);
    assertFalse(taskTemplatePage.isTaskNameDisplayed());
    assertFalse(taskTemplatePage.isTaskActionDisplayed());
    assertFalse(taskTemplatePage.isCaseInfoButtonDisplayed());
  }

  @Test
  public void testDisplayWarningInIFrameTaskTemplate() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.clickOnLogo();
    By leaveButton = By.id("task-leave-warning-component:leave-button");
    WaitHelper.assertTrueWithWait(() -> taskTemplatePage.isElementDisplayed(leaveButton));
  }

  @Test
  public void testNotDisplayWarningInIFrameTaskTemplate() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    taskTemplatePage1.finishCreateInvestmentTask();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Approve Investment");
    taskWidget.applyFilter();
    TaskIFrameTemplatePage taskTemplatePage2 = taskWidget.startTaskIFrameByIndex(0);
    taskTemplatePage2.waitForPageLoad();
    taskTemplatePage2.clickOnHomeLogo();
  }

  @Test
  public void testRedirectToApplicationHome() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    taskTemplatePage1.finishCreateInvestmentTask();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Approve Investment");
    taskWidget.applyFilter();
    TaskIFrameTemplatePage taskTemplatePage2 = taskWidget.startTaskIFrameByIndex(0);
    taskTemplatePage2.backToHomeInIFrameApprovalTask();
  }

  @Test
  public void testStickyTaskList() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    taskTemplatePage1.finishCreateInvestmentTask();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Approve Investment");
    taskWidget.applyFilter();
    assertEquals(1, taskWidget.getFilterNotiNumber());
    TaskIFrameTemplatePage taskTemplatePage2 = taskWidget.startTaskIFrameByIndex(0);
    NewDashboardPage taskWidgetPage2 = taskTemplatePage2.finishIFrameReviewTask();
    $("[id$=':actions-widget-form:actions-menu-button_button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    WaitHelper.assertTrueWithWait(() -> taskWidgetPage2
        .isElementDisplayed(By.cssSelector("[id$='task-default_task_list_dashboard_task_1:actions-widget-form:info-menu-item-0']")));
  }

  public void waitForTemplateRender() {
    WaitHelper.waitForPresenceOfElementLocatedInFrame(".task-template-container:not(.u-invisibility)",
        Duration.ofSeconds(45));
  }

  @Test
  public void testTextOutIFrameChangeWithSkipTaskList() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    taskTemplatePage1.finishCreateInvestmentTask();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Approve Investment");
    taskWidget.applyFilter();
    TaskIFrameTemplatePage taskTemplatePage2 = taskWidget.startTaskIFrameByIndex(0);
    taskTemplatePage2.waitForIFrameContentVisible();
    assertEquals("Review Request (Skip Tasklist in IFrame)",
        taskTemplatePage2.getTaskNameOutsideIFrameWithSkipTaskList());
  }

  @Test
  public void testShowCategoryInCaseByDefaultIframe() {
    redirectToRelativeLink("InternalSupport/15B1EA24CCF377E8/saleAndInform.ivp");
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "sale department");
    taskWidget.applyFilter();
    TaskIFrameTemplatePage startTask = taskWidget.startTaskIFrameByIndex(0);
    startTask.openCaseInfo();
    resizeBrowserTo2kResolution();
    assertTrue(startTask.isCategoryColumnDisplayed());
  }
}

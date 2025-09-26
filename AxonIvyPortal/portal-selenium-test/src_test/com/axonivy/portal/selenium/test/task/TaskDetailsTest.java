package com.axonivy.portal.selenium.test.task;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class TaskDetailsTest extends BaseTest {

  // TASK NAME
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String TAKE_ORDER = "Take Order";

  // NOTE CONTENT
  private static final String NOTE_TASK_DETAIL_TECHNICAL_CASE =
      "Note is added on Task Details and the task has Technical Case";
  private static final String NOTE_TASK_DETAIL_BUSINESS_CASE =
      "Note is added on Task Details and the task has only Business Case";

  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";
  private static final String CREATE_NOTES = "InternalSupport/14B2FC03D2E87141/processWithSystemNote.ivp";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }

  @Test
  public void testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithoutTechnicalCase() throws IOException {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();

    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    
    TaskDetailsPage taskDetailsPage = taskWidget.openDashboardTaskDetails(ORDER_PIZZA);
    taskDetailsPage.waitForPageLoad();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_FOLDER + "testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithoutTechnicalCase1-checkTasks");
    taskDetailsPage.addNote(NOTE_TASK_DETAIL_BUSINESS_CASE);
    refreshPage();
    taskDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_BUSINESS_CASE).shouldHave(size(1));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_FOLDER + "testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithoutTechnicalCase2-checkTasks");
    taskDetailsPage.gotoBusinessCase();
    CaseDetailsPage caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_BUSINESS_CASE).shouldHave(size(1));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_FOLDER + "testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithoutTechnicalCase3-checkTasks");
  }

  @Test
  public void testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithTechnicalCase() throws IOException {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_FOLDER + "testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithTechnicalCase1-checkTasks");

    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openDashboardTaskDetails(TAKE_ORDER);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.waitForPageLoad();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_FOLDER + "testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithTechnicalCase2-checkIfAddNoteElementExisting");
    taskDetailsPage.addNote(NOTE_TASK_DETAIL_TECHNICAL_CASE);
    refreshPage();
    taskDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_TECHNICAL_CASE).shouldHave(size(1));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_FOLDER + "testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithTechnicalCase3-addNotes");
    CaseDetailsPage caseDetailsPage = taskDetailsPage.gotoTechnicalCase();
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_FOLDER + "testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithTechnicalCase4-technicalCases");
    caseDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_TECHNICAL_CASE).shouldHave(size(1));
    caseDetailsPage.gotoBusinessCase();
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_TECHNICAL_CASE).shouldHave(size(1));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_FOLDER + "testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithTechnicalCase5-businessCase");
  }

  @Test
  public void testShareTaskDetails() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(grantShareLinkTaskDetailsPermission);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openDashboardTaskDetails(TAKE_ORDER);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getShareButton().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    taskDetailsPage.getShareDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    redirectToRelativeLink(denyShareLinkTaskDetailsPermission);
    redirectToNewDashBoard();
    mainMenuPage.openTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openDashboardTaskDetails(TAKE_ORDER);
    taskDetailsPage.getShareButton().shouldBe(Condition.disappear);
  }

  @Test
  public void testShowTaskStatusBannerOnTaskDetails() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    resizeBrowserToFullHDResolution();
    grantSpecificPortalPermission(PortalPermission.SYSTEM_TASK_READ_ALL);
    grantSpecificPortalPermission(PortalPermission.TASK_DISPLAY_DESTROY_ACTION);
    redirectToNewDashBoard();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();

    taskWidget.openDashboardTaskDetails("Maternity Leave Request");
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.clickStartTask();
    redirectToNewDashBoard();

    NavigationHelper.navigateToTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openDashboardTaskDetails("Maternity Leave Request");
    taskDetailsPage.getStatusBanner().shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    NavigationHelper.navigateToTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Sick Leave Request");
    taskWidget.applyFilter();
    taskWidget.destroyTask(0);
    taskWidget.openDashboardTaskDetails("Sick Leave Request");
    taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.waitForPageLoad();
    taskDetailsPage.getStatusBanner().shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    NavigationHelper.navigateToTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.removeFilter(0);
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, TAKE_ORDER);
    taskWidget.applyFilter();
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.startTask(0);
    // at this stage, start task then it's done
    // user will be redirected to task list
    // then we should click on the task again to go to task detail as I observe
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    refreshPage();
    taskWidget.clickOnTaskName(TAKE_ORDER);
    taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.waitForPageLoad();
    taskDetailsPage.getStatusBanner().shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    redirectToNewDashBoard();
    NavigationHelper.navigateToTaskList();
    taskWidget.openFilterWidget();
    taskWidget.removeFilter(0);
    taskWidget.applyFilter();
    taskWidget.openDashboardTaskDetails(TAKE_ORDER);
    taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.waitForPageLoad();
    taskDetailsPage.getStatusBanner().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  @Test
  public void testMultipleExpiryResponsible() {
    redirectToRelativeLink(taskWithMultiResponsibles);
    login(TestAccount.ADMIN_USER);
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openDashboardTaskDetails("task with 8 responsible");
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getAllResponsible().shouldHave(Condition.text("8"));
    taskDetailsPage.getAllExpiryResponsible().shouldHave(Condition.text("6"));
    taskDetailsPage.getAllExpiryResponsible().click();
    taskDetailsPage.getExpiryResponsibleDialog().should(Condition.appear);
  }

  @Test
  public void testUncheckSystemNotesByDefaultForAdminUser() {
    updateGlobalVariable(Variable.CHECK_SYSTEM_NOTES_BY_DEFAULT.getKey(), "false");
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(CREATE_NOTES);

    redirectToNewDashBoard();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openDashboardTaskDetails("User: create note");
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();

    taskDetailsPage.getNotesWithContent("System note").shouldHave(size(0));
    taskDetailsPage.clickOnSystemNotesCheckbox(true);
    taskDetailsPage.getNotesWithContent("System note").shouldHave(size(1));
  }

  @Test
  public void testCheckSystemNotesByDefaultForNormalUser() {
    updateGlobalVariable(Variable.HIDE_SYSTEM_NOTES_FROM_HISTORY.getKey(), "false");
    updateGlobalVariable(Variable.CHECK_SYSTEM_NOTES_BY_DEFAULT.getKey(), "true");
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(CREATE_NOTES);

    redirectToNewDashBoard();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openDashboardTaskDetails("User: create note");
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();

    taskDetailsPage.getNotesWithContent("System note").shouldHave(size(1));
    taskDetailsPage.clickOnSystemNotesCheckbox(false);
    taskDetailsPage.getNotesWithContent("System note").shouldHave(size(0));
  }
}

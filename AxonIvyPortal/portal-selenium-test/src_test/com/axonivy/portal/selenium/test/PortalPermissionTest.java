package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.size;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class PortalPermissionTest extends BaseTest {

  protected NewDashboardPage newDashboardPage;
  private String grantSpecificPortalPermissionLink = "portalKitTestHelper/14DE09882B540AD5/grantSpecificPortalPermission.ivp?portalPermission=%s";
  private String denySpecificPortalPermissionLink = "portalKitTestHelper/14DE09882B540AD5/denySpecificPortalPermission.ivp?portalPermission=%s";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    grantAccessFullListPermissions();
    newDashboardPage = new NewDashboardPage();
  }
  
  @Override
  @AfterEach
  public void tearDown() {
    grantTaskActionsPermissions();
    grantAccessFullListPermissions();
    grantShowHideNotePermissions();
    grantDocumentOfInvolvedCaseWritePemissionToCurrentUser();
    grantCasePermissions();
  }

  @Test
  public void testShowHideSubMenuItems() {
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    denyAccessFullListPermissions();
    assertFalse(mainMenuPage.isProcessesDisplayed());
    assertFalse(mainMenuPage.isTasksDisplayed());
    assertFalse(mainMenuPage.isCasesDisplayed());

    grantAccessFullListPermissions();
    newDashboardPage = new NewDashboardPage();
    WaitHelper.assertTrueWithWait(() -> mainMenuPage.isProcessesDisplayed());
    assertTrue(mainMenuPage.isTasksDisplayed());
    assertTrue(mainMenuPage.isCasesDisplayed());
  }

  @Test
  public void testShowHideSystemTaskByPermission() {
    // TEST SHOW SYSTEM TASK
    login(TestAccount.ADMIN_USER);
    grantSpecificPortalPermission(PortalPermission.SYSTEM_TASK_READ_ALL);
    redirectToRelativeLink(createSystemTaskUrl);

    // Navigate full task list to check system task
    NewDashboardPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.waitPageLoaded();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Delayed");
    taskWidget.applyFilter();
    boolean systemTaskAppear = taskWidget.checkNameOfTaskAt(0, "I'm a system task with delay");
    assertTrue(systemTaskAppear);

    // Navigate to Dashboard widget to check system task
    CaseWidgetNewDashBoardPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    caseWidgetPage.waitPageLoaded();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase("Create System Task");
    caseDetailsPage.waitPageLoaded();
    int countTask = caseDetailsPage.countRelatedTasks().size();
    assertTrue(countTask == 3);

    // TEST HIDE SYSTEM TASK
    denySpecificPortalPermission(PortalPermission.SYSTEM_TASK_READ_ALL);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.waitPageLoaded();
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("state", null);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);

    caseWidgetPage = NavigationHelper.navigateToCaseList();
    caseWidgetPage.waitPageLoaded();
    caseDetailsPage = caseWidgetPage.openDetailsCase("Create System Task");
    caseDetailsPage.waitPageLoaded();
    countTask = caseDetailsPage.countRelatedTasks().size();
    assertTrue(countTask == 2);
  }

  @Test
  public void testShowHideTaskActions() {
    denyTaskActionsPermissions();
    createTestingTasks();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.waitPageLoaded();
    assertTrue(taskWidget.isTaskResetNotDisplayed(0));
    taskWidget.clickOnTaskActionLink(0);
    assertTrue(taskWidget.isTaskDelegateNotDisplayed(0));
    taskWidget.clickOnTaskActionLink(0);
    assertTrue(taskWidget.isTaskReserverNotDisplayed(0));

    grantTaskActionsPermissions();
    NavigationHelper.navigateToTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    assertFalse(taskWidget.isTaskResetNotDisplayed(0));
    taskWidget.clickOnTaskActionLink(0);
    assertFalse(taskWidget.isTaskDelegateNotDisplayed(0));
    taskWidget.clickOnTaskActionLink(0);
    assertFalse(taskWidget.isTaskReserverNotDisplayed(0));
  }

  @Test
  public void testShowHideNoteAndDocumentButtons() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
    denyShowHideNotePermissions();
    denyDocumentOfInvolvedCaseWritePemissionFromCurrentUser();
    newDashboardPage.waitPageLoaded();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetNewDashBoardPage caseWidgetPage = mainMenuPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase("Leave Request");
    assertTrue(caseDetailsPage.isAddNoteButtonDisplayed(false));
    assertTrue(caseDetailsPage.isShowMoreNoteButtonDisplayed(false));
    assertTrue(caseDetailsPage.isAddDocumentLinkDisplayed(false));
    mainMenuPage.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    TaskDetailsPage taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    assertTrue(taskDetailsPage.isAddNoteButtonDisplayed(false));
    assertTrue(taskDetailsPage.isShowMoreNoteButtonDisplayed(false));
    assertTrue(taskDetailsPage.isAddDocumentLinkDisplayed(false));

    grantShowHideNotePermissions();
    grantDocumentOfInvolvedCaseWritePemissionToCurrentUser();
    mainMenuPage.openCaseList();
    caseDetailsPage = caseWidgetPage.openDetailsCase("Leave Request");
    assertTrue(caseDetailsPage.isAddNoteButtonDisplayed(true));
    assertTrue(caseDetailsPage.isShowMoreNoteButtonDisplayed(true));
    assertTrue(caseDetailsPage.isAddDocumentLinkDisplayed(true));
    mainMenuPage.openTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    assertTrue(taskDetailsPage.isAddNoteButtonDisplayed(true));
    assertTrue(taskDetailsPage.isShowMoreNoteButtonDisplayed(true));
    assertTrue(taskDetailsPage.isAddDocumentLinkDisplayed(true));
  }

  @Test
  public void testShowHideLinkRelatedToCasePermissions() {
    createTestingTasks();
    denyCasePermissions();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetNewDashBoardPage caseWidgetPage = mainMenuPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase("Leave Request");
    assertTrue(caseDetailsPage.isShowDetailsDisplayed(false));

    grantCasePermissions();
    mainMenuPage.openCaseList();
    caseDetailsPage = caseWidgetPage.openDetailsCase("Leave Request");
    caseDetailsPage.openActionMenu();
    assertTrue(caseDetailsPage.isShowDetailsDisplayed(true));
  }

  @Test
  public void testShowHideMyProfileItems() {
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    denyAccessFullListPermissions();
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    assertFalse(userProfilePage.isProcessSettingDisplayed());

    grantAccessFullListPermissions();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    userProfilePage = homePage.openMyProfilePage();
    assertTrue(userProfilePage.isProcessSettingDisplayed());
  }

  private void grantAccessFullListPermissions() {
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_PROCESS_LIST.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_CASE_LIST.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_TASK_LIST.getValue()));
  }

  private void denyAccessFullListPermissions() {
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_PROCESS_LIST.getValue()));
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_CASE_LIST.getValue()));
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_TASK_LIST.getValue()));
  }

  private void grantTaskActionsPermissions() {
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_RESET_ACTION.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_DELEGATE_ACTION.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_RESERVE_ACTION.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_DESTROY_ACTION.getValue()));
  }

  private void denyTaskActionsPermissions() {
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_RESET_ACTION.getValue()));
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_DELEGATE_ACTION.getValue()));
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_RESERVE_ACTION.getValue()));
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_DESTROY_ACTION.getValue()));
  }

  private void grantCasePermissions() {
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.SHOW_CASE_DETAILS.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.SHOW_ALL_TASKS_OF_CASE.getValue()));
  }

  private void denyCasePermissions() {
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.SHOW_CASE_DETAILS.getValue()));
  }

  private void grantShowHideNotePermissions() {
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_CASE_ADD_NOTE.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_CASE_SHOW_MORE_NOTE.getValue()));
  }

  private void denyShowHideNotePermissions() {
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_CASE_ADD_NOTE.getValue()));
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_CASE_SHOW_MORE_NOTE.getValue()));
  }
}

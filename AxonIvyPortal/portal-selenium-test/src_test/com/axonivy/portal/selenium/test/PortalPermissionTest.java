package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class PortalPermissionTest extends BaseTest {

  protected NewDashboardPage newDashboardPage;
  private String grantSpecificPortalPermissionLink =
      "portalKitTestHelper/14DE09882B540AD5/grantSpecificPortalPermission.ivp?portalPermission=%s";
  private String denySpecificPortalPermissionLink =
      "portalKitTestHelper/14DE09882B540AD5/denySpecificPortalPermission.ivp?portalPermission=%s";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    grantAccessFullListPermissions();
    newDashboardPage = new NewDashboardPage();
  }

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
    assertFalse(mainMenuPage.isStatisticsDisplayed());

    grantAccessFullListPermissions();
    newDashboardPage = new NewDashboardPage();
    WaitHelper.assertTrueWithWait(() -> mainMenuPage.isProcessesDisplayed());
    assertTrue(mainMenuPage.isTasksDisplayed());
    assertTrue(mainMenuPage.isCasesDisplayed());
    assertTrue(mainMenuPage.isStatisticsDisplayed());
  }

  @Test
  public void testShowHideTaskActions() {
    denyTaskActionsPermissions();
    createTestingTasks();
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.waitPageLoaded();
    assertTrue(taskWidgetPage.isTaskResetDisplayed(false));
    assertTrue(taskWidgetPage.isTaskDelegateDisplayed(false));
    assertTrue(taskWidgetPage.isTaskReserverDisplayed(false));
    assertTrue(taskWidgetPage.isAdhocSideStepDisplayed(false));

    grantTaskActionsPermissions();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.sideStepMenuOnActionButton(0);
    assertTrue(taskWidgetPage.isTaskResetDisplayed(true));
    assertTrue(taskWidgetPage.isTaskDelegateDisplayed(true));
    assertTrue(taskWidgetPage.isTaskReserverDisplayed(true));
    assertTrue(taskWidgetPage.isAdhocSideStepDisplayed(true));
  }

  @Test
  public void testShowHideNoteAndDocumentButtons() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
    denyShowHideNotePermissions();
    denyDocumentOfInvolvedCaseWritePemissionFromCurrentUser();
    newDashboardPage.waitPageLoaded();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    assertTrue(caseDetailsPage.isAddNoteButtonDisplayed(false));
    assertTrue(caseDetailsPage.isShowMoreNoteButtonDisplayed(false));
    assertTrue(caseDetailsPage.isAddDocumentLinkDisplayed(false));
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    TaskDetailsPage taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    assertTrue(taskDetailsPage.isAddNoteButtonDisplayed(false));
    assertTrue(taskDetailsPage.isShowMoreNoteButtonDisplayed(false));
    assertTrue(taskDetailsPage.isAddDocumentLinkDisplayed(false));

    grantShowHideNotePermissions();
    grantDocumentOfInvolvedCaseWritePemissionToCurrentUser();
    mainMenuPage.openCaseList();
    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    assertTrue(caseDetailsPage.isAddNoteButtonDisplayed(true));
    assertTrue(caseDetailsPage.isShowMoreNoteButtonDisplayed(true));
    assertTrue(caseDetailsPage.isAddDocumentLinkDisplayed(true));
    mainMenuPage.openTaskList();
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    assertTrue(taskDetailsPage.isAddNoteButtonDisplayed(true));
    assertTrue(taskDetailsPage.isShowMoreNoteButtonDisplayed(true));
    assertTrue(taskDetailsPage.isAddDocumentLinkDisplayed(true));
  }

  @Test
  public void testShowHideLinkRelatedToCasePermissions() {
    createTestingTasks();
    denyCasePermissions();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    assertTrue(caseDetailsPage.isShowDetailsDisplayed(false));

    grantCasePermissions();
    mainMenuPage.openCaseList();
    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    caseDetailsPage.openActionMenu();
    assertTrue(caseDetailsPage.isShowDetailsDisplayed(true));
  }

  private void grantAccessFullListPermissions() {
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_PROCESS_LIST.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_CASE_LIST.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_TASK_LIST.getValue()));
    redirectToRelativeLink(
        String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_STATISTICS_LIST.getValue()));
  }

  private void denyAccessFullListPermissions() {
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_PROCESS_LIST.getValue()));
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_CASE_LIST.getValue()));
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_TASK_LIST.getValue()));
    redirectToRelativeLink(
        String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_STATISTICS_LIST.getValue()));
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
  
  @Test
  public void testShowHideMyProfileItems() {
    denyAccessFullListPermissions();
    showNewDashboard();
    NewDashboardPage dashboard = new NewDashboardPage();
    UserProfilePage userProfilePage = dashboard.openMyProfilePage();

    assertFalse(userProfilePage.isProcessSettingDisplayed());
    assertFalse(userProfilePage.isTaskListSettingDisplayed());
    assertFalse(userProfilePage.isCaseListSettingDisplayed());

    grantAccessFullListPermissions();
    dashboard = new NewDashboardPage();
    userProfilePage = dashboard.openMyProfilePage();

    assertTrue(userProfilePage.isProcessSettingDisplayed());
    assertTrue(userProfilePage.isTaskListSettingDisplayed());
    assertTrue(userProfilePage.isCaseListSettingDisplayed());
  }
}

package portal.guitest.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import portal.guitest.common.BaseTest;
import portal.guitest.common.Variable;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class PortalPermissionTest extends BaseTest{
  
  protected HomePage homePage;
  private String grantSpecificPortalPermissionLink = "portalKitTestHelper/14DE09882B540AD5/grantSpecificPortalPermission.ivp?portalPermission=%s";
  private String denySpecificPortalPermissionLink = "portalKitTestHelper/14DE09882B540AD5/denySpecificPortalPermission.ivp?portalPermission=%s";
  
  @Override
  @Before
  public void setup() {
    super.setup();
    grantAccessFullListPermissions();
    homePage = new HomePage();
  }
  
  @After
  public void tearDown() {
    grantTaskActionsPermissions();
    grantAccessFullListPermissions();
    grantShowHideNotePermissions();
    grantDocumentOfInvolvedCaseWritePemissionToCurrentUser();
    grantCasePermissions();
  }
  
  @Test
  public void testShowHideSubMenuItems() {
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    denyAccessFullListPermissions();
    Assert.assertFalse(mainMenuPage.isProcessesDisplayed());
    Assert.assertFalse(mainMenuPage.isTasksDisplayed());
    Assert.assertFalse(mainMenuPage.isCasesDisplayed());
    Assert.assertFalse(mainMenuPage.isStatisticsDisplayed());
    Assert.assertFalse(homePage.isShowAllProcessesLinkDisplayed());
    Assert.assertFalse(homePage.isShowTaskListLinkDisplayed());
    Assert.assertFalse(homePage.isShowAllChartsLinkDisplayed());
    
    grantAccessFullListPermissions();
    homePage = new HomePage();
    WaitHelper.assertTrueWithWait(() -> mainMenuPage.isProcessesDisplayed());
    Assert.assertTrue(mainMenuPage.isTasksDisplayed());
    Assert.assertTrue(mainMenuPage.isCasesDisplayed());
    Assert.assertTrue(mainMenuPage.isStatisticsDisplayed());
    Assert.assertTrue(homePage.isShowAllProcessesLinkDisplayed());
    Assert.assertTrue(homePage.isShowTaskListLinkDisplayed());
    Assert.assertTrue(homePage.isShowAllChartsLinkDisplayed());
  }
  
  @Test
  public void testShowHideTaskActions() {
    denyTaskActionsPermissions();
    createTestingTasks();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    Assert.assertFalse(taskWidgetPage.isTaskResetDisplayed());
    Assert.assertFalse(taskWidgetPage.isTaskDelegateDisplayed());
    Assert.assertFalse(taskWidgetPage.isTaskReserverDisplayed());
    Assert.assertFalse(taskWidgetPage.isAdhocSideStepDisplayed());
    
    grantTaskActionsPermissions();
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.sideStepMenuOnActionButton(0);
    Assert.assertTrue(taskWidgetPage.isTaskResetDisplayed());
    Assert.assertTrue(taskWidgetPage.isTaskDelegateDisplayed());
    Assert.assertTrue(taskWidgetPage.isTaskReserverDisplayed());
    Assert.assertTrue(taskWidgetPage.isAdhocSideStepDisplayed());
  }
  
  @Test
  public void testShowHideNoteAndDocumentButtons() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
    denyShowHideNotePermissions();
    denyDocumentOfInvolvedCaseWritePemissionFromCurrentUser();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    Assert.assertFalse(caseDetailsPage.isAddNoteButtonDisplayed());
    Assert.assertFalse(caseDetailsPage.isShowMoreNoteButtonDisplayed());
    Assert.assertFalse(caseDetailsPage.isAddDocumentLinkDisplayed());
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    TaskDetailsPage taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    Assert.assertFalse(taskDetailsPage.isAddNoteButtonDisplayed());
    Assert.assertFalse(taskDetailsPage.isShowMoreNoteButtonDisplayed());
    Assert.assertFalse(taskDetailsPage.isAddDocumentLinkDisplayed());
    
    grantShowHideNotePermissions();
    grantDocumentOfInvolvedCaseWritePemissionToCurrentUser();
    mainMenuPage.openCaseList();
    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    Assert.assertTrue(caseDetailsPage.isAddNoteButtonDisplayed());
    Assert.assertTrue(caseDetailsPage.isShowMoreNoteButtonDisplayed());
    Assert.assertTrue(caseDetailsPage.isAddDocumentLinkDisplayed());
    mainMenuPage.openTaskList();
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    Assert.assertTrue(taskDetailsPage.isAddNoteButtonDisplayed());
    Assert.assertTrue(taskDetailsPage.isShowMoreNoteButtonDisplayed());
    Assert.assertTrue(taskDetailsPage.isAddDocumentLinkDisplayed());
  }
  
  @Test
  public void testShowHideLinkRelatedToCasePermissions() {
    createTestingTasks();
    denyCasePermissions();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    Assert.assertFalse(caseDetailsPage.isShowDetailsDisplayed());
    
    grantCasePermissions();
    mainMenuPage.openCaseList();
    caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    caseDetailsPage.openActionMenu();
    Assert.assertTrue(caseDetailsPage.isShowDetailsDisplayed());
  }
  
  private void grantAccessFullListPermissions() {
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_PROCESS_LIST.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_CASE_LIST.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_TASK_LIST.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_STATISTICS_LIST.getValue()));
  }
  
  private void denyAccessFullListPermissions() {
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_PROCESS_LIST.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_CASE_LIST.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_TASK_LIST.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.ACCESS_FULL_STATISTICS_LIST.getValue()));
  }
  
  private void grantTaskActionsPermissions() {
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_RESET_ACTION.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_DELEGATE_ACTION.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_RESERVE_ACTION.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_DESTROY_ACTION.getValue()));
  }
  
  private void denyTaskActionsPermissions() {
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_RESET_ACTION.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_DELEGATE_ACTION.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_RESERVE_ACTION.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_DESTROY_ACTION.getValue()));
  }
  
  private void grantCasePermissions() {
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.SHOW_CASE_DETAILS.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.SHOW_ALL_TASKS_OF_CASE.getValue()));
  }
  
  private void denyCasePermissions() {
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.SHOW_CASE_DETAILS.getValue()));
  }
  
  private void grantShowHideNotePermissions() {
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_CASE_ADD_NOTE.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.TASK_CASE_SHOW_MORE_NOTE.getValue()));
  }
  
  private void denyShowHideNotePermissions() {
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_CASE_ADD_NOTE.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_CASE_SHOW_MORE_NOTE.getValue()));
  }
}

package portal.guitest.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;

public class PortalPermissionTest extends BaseTest{
  
  protected HomePage homePage;
  private String grantSpecificPortalPermissionLink = "portalKitTestHelper/14DE09882B540AD5/grantSpecificPortalPermission.ivp?portalPermission=%s";
  private String denySpecificPortalPermissionLink = "portalKitTestHelper/14DE09882B540AD5/denySpecificPortalPermission.ivp?portalPermission=%s";
  
  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    grantAccessFullListPermissions();
    homePage = new HomePage();
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
    Assert.assertTrue(mainMenuPage.isProcessesDisplayed());
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
    taskWidgetPage.openTaskList();
    Assert.assertFalse(taskWidgetPage.isShowMoreNoteButtonDisplayed());
    
    grantTaskActionsPermissions();
    taskWidgetPage.openTaskList();
    taskWidgetPage.sideStepMenuOnMoreButton(0);
    Assert.assertTrue(taskWidgetPage.isTaskResetDisplayed());
    Assert.assertTrue(taskWidgetPage.isTaskDelegateDisplayed());
    Assert.assertTrue(taskWidgetPage.isTaskReserverDisplayed());
    Assert.assertTrue(taskWidgetPage.isAdhocSideStepDisplayed());
  }
  
  @Test
  public void testShowHideNoteAndDocumentButtons() {
    createTestingTasks();
    denyShowHideNotePermissions();
    denyDocumentOfInvolvedCaseWritePemissionFromCurrentUser();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
    caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    Assert.assertFalse(caseWidgetPage.isAddNoteButtonDisplayed());
    Assert.assertFalse(caseWidgetPage.isShowMoreNoteButtonDisplayed());
    Assert.assertFalse(caseWidgetPage.isAddDocumentLinkDisplayed());
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.openTaskDetails(0);
    Assert.assertFalse(taskWidgetPage.isAddNoteButtonDisplayed());
    Assert.assertFalse(taskWidgetPage.isShowMoreNoteButtonDisplayed());
    Assert.assertFalse(taskWidgetPage.isAddDocumentLinkDisplayed());
    
    grantShowHideNotePermissions();
    grantDocumentOfInvolvedCaseWritePemissionToCurrentUser();
    mainMenuPage.openCaseList();
    caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    Assert.assertTrue(caseWidgetPage.isAddNoteButtonDisplayed());
    Assert.assertTrue(caseWidgetPage.isShowMoreNoteButtonDisplayed());
    Assert.assertTrue(caseWidgetPage.isAddDocumentLinkDisplayed());
    mainMenuPage.openTaskList();
    taskWidgetPage.openTaskDetails(0);
    Assert.assertTrue(taskWidgetPage.isAddNoteButtonDisplayed());
    Assert.assertTrue(taskWidgetPage.isShowMoreNoteButtonDisplayed());
    Assert.assertTrue(taskWidgetPage.isAddDocumentLinkDisplayed());
  }
  
  @Test
  public void testShowHideLinkRelatedToCasePermissions() {
    createTestingTasks();
    denyCasePermissions();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
    caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    Assert.assertFalse(caseWidgetPage.isShowDetailsDisplayed());
    Assert.assertFalse(caseWidgetPage.isShowAllTasksDisplayed());
    
    grantCasePermissions();
    mainMenuPage.openCaseList();
    caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    Assert.assertTrue(caseWidgetPage.isShowDetailsDisplayed());
    Assert.assertTrue(caseWidgetPage.isShowAllTasksDisplayed());
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
  }
  
  private void denyTaskActionsPermissions() {
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_RESET_ACTION.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_DELEGATE_ACTION.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_RESERVE_ACTION.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS.getValue()));
  }
  
  private void grantCasePermissions() {
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.SHOW_CASE_DETAILS.getValue()));
    redirectToRelativeLink(String.format(grantSpecificPortalPermissionLink, PortalPermission.SHOW_ALL_TASKS_OF_CASE.getValue()));
  }
  
  private void denyCasePermissions() {
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.SHOW_CASE_DETAILS.getValue()));
    redirectToRelativeLink(String.format(denySpecificPortalPermissionLink, PortalPermission.SHOW_ALL_TASKS_OF_CASE.getValue()));
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

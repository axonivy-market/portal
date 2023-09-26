package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.TestRole;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.ProcessWidgetPage.AddNewExternalLinkDialog;

public class FullProcessPageTest extends BaseTest{
  
  private static final String AAGOOGLE_LINK = "11AAGoogle";
  protected NewDashboardPage2 newDashboardPage2;
  protected ProcessWidgetPage processWidgetPage;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    newDashboardPage2 = new NewDashboardPage2();
    MainMenuPage mainMenuPage = newDashboardPage2.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
  }
  
  @Test
  public void testFindProcess() {
    processWidgetPage.selectViewMode("COMPACT");
    processWidgetPage.waitForCompactProcessListDisplayed();
    processWidgetPage.enterSearchKeyword("Alpha Company");
    assertTrue(processWidgetPage.isProcessDisplay("Create Alpha Company"));
    assertTrue(processWidgetPage.isProcessGroupDisplay("C"));
    processWidgetPage.enterSearchKeyword("random keyword never match with any process");
    assertTrue(processWidgetPage.isNoProcessFound());
    assertFalse(processWidgetPage.isProcessGroupDisplay("A"));
  }
  
  @Test
  public void testChangeProcessViewMode() {
    String currentView = processWidgetPage.getCurrentViewMode();
    processWidgetPage.selectViewMode(ProcessWidgetPage.GRID_MODE);
    String newView = processWidgetPage.getCurrentViewMode();
    assertFalse("Current view is not changed", StringUtils.equals(currentView, newView));
  }
  
  @Test
  public void testEditDeleteProcessIcon() {
    login(TestAccount.ADMIN_USER);
    newDashboardPage2 = new NewDashboardPage2();
    MainMenuPage mainMenuPage = newDashboardPage2.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
    createPublicExternalTestProcess(AAGOOGLE_LINK, AAGOOGLE_LINK, TestRole.TESTER_ROLE);

    processWidgetPage.selectViewMode(ProcessWidgetPage.GRID_MODE);
    processWidgetPage.waitForGridProcessListDisplayed();
    processWidgetPage.enterSearchKeyword("link");
    String currentIcon = processWidgetPage.getProcessItemIcon(0);
    processWidgetPage.selectViewMode(ProcessWidgetPage.IMAGE_MODE);
    processWidgetPage.waitForImageProcessListDisplayed();
    processWidgetPage.enterSearchKeyword("link");
    processWidgetPage.clickMoreButtonOfFirstImageProcess();
    processWidgetPage.clickOnProcessEditMenu(0);
    processWidgetPage.changeProcessIcon();
    processWidgetPage.addNewRolePermission(TestRole.HR_ROLE);
    processWidgetPage.saveEditProcessDialog();
    processWidgetPage.selectViewMode(ProcessWidgetPage.GRID_MODE);
    processWidgetPage.waitForGridProcessListDisplayed();
    processWidgetPage.enterSearchKeyword("link");
    String newIcon = processWidgetPage.getProcessItemIcon(0);
    assertFalse("Current Icon is not changed", StringUtils.equals(currentIcon, newIcon));

    login(TestAccount.CASE_OWNER_USER);
    newDashboardPage2 = new NewDashboardPage2();
    mainMenuPage = newDashboardPage2.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
    processWidgetPage.enterSearchKeyword(AAGOOGLE_LINK);
    assertTrue(processWidgetPage.isNoProcessFound());

    login(TestAccount.HR_ROLE_USER);
    newDashboardPage2 = new NewDashboardPage2();
    mainMenuPage = newDashboardPage2.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
    processWidgetPage.enterSearchKeyword(AAGOOGLE_LINK);
    assertTrue(processWidgetPage.isProcessDisplay(AAGOOGLE_LINK));

    login(TestAccount.ADMIN_USER);
    newDashboardPage2 = new NewDashboardPage2();
    mainMenuPage = newDashboardPage2.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
    processWidgetPage.selectViewMode(ProcessWidgetPage.GRID_MODE);
    processWidgetPage.waitForGridProcessListDisplayed();
    processWidgetPage.enterSearchKeyword(AAGOOGLE_LINK);
    processWidgetPage.clickMoreButtonOfGridProcess(AAGOOGLE_LINK);
    processWidgetPage.deleteGridProcess(AAGOOGLE_LINK);
    processWidgetPage.enterSearchKeyword("link");
    assertTrue("Still see processes", processWidgetPage.isNoProcessFound());
  }
  
  private void createPublicExternalTestProcess(String processName, String processLink, String rolePermission) {
    AddNewExternalLinkDialog addNewExternalLinkDialog = processWidgetPage.openNewExternalLinkDialog();
    addNewExternalLinkDialog.inputDataForPublicExternalLink(processName, processLink, rolePermission);
    addNewExternalLinkDialog.submitForm();
  }
}

package com.axonivy.portal.selenium.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.TestRole;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage.AddNewExternalLinkDialog;

@IvyWebTest
public class FullProcessPageTest extends BaseTest {

  private static final String AAGOOGLE_LINK = "11AAGoogle";
  protected NewDashboardPage newDashboardPage;
  protected ProcessWidgetPage processWidgetPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
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
    processWidgetPage.selectGridMode();
    String newView = processWidgetPage.getCurrentViewMode();
    assertFalse(StringUtils.equals(currentView, newView));
  }

  @Test
  public void testEditDeleteProcessIcon() {
    login(TestAccount.ADMIN_USER);
    newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
    createPublicExternalTestProcess(AAGOOGLE_LINK, AAGOOGLE_LINK, TestRole.TESTER_ROLE);

    refreshPage();
    processWidgetPage.selectGridMode();
    processWidgetPage.waitForGridProcessListDisplayed();
    processWidgetPage.enterSearchKeyword("link");
    String currentIcon = processWidgetPage.getProcessItemIcon(0);
    processWidgetPage.selectImageMode();
    processWidgetPage.waitForImageProcessListDisplayed();
    processWidgetPage.enterSearchKeyword("link");
    processWidgetPage.clickMoreButtonOfFirstImageProcess();
    processWidgetPage.clickOnProcessEditMenu(0);
    processWidgetPage.changeProcessIcon();
    processWidgetPage.addNewRolePermission(TestRole.HR_ROLE);
    processWidgetPage.saveEditProcessDialog();
    
    refreshPage();
    processWidgetPage.selectGridMode();
    processWidgetPage.waitForGridProcessListDisplayed();
    processWidgetPage.enterSearchKeyword("link");
    String newIcon = processWidgetPage.getProcessItemIcon(0);
    assertFalse(StringUtils.equals(currentIcon, newIcon));

    login(TestAccount.CASE_OWNER_USER);
    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
    processWidgetPage.enterSearchKeyword(AAGOOGLE_LINK);
    assertTrue(processWidgetPage.isNoProcessFound());

    login(TestAccount.HR_ROLE_USER);
    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
    processWidgetPage.enterSearchKeyword(AAGOOGLE_LINK);
    assertTrue(processWidgetPage.isProcessDisplay(AAGOOGLE_LINK));

    login(TestAccount.ADMIN_USER);
    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
    processWidgetPage.selectGridMode();
    processWidgetPage.waitForGridProcessListDisplayed();
    processWidgetPage.enterSearchKeyword(AAGOOGLE_LINK);
    processWidgetPage.clickMoreButtonOfGridProcess(AAGOOGLE_LINK);
    processWidgetPage.deleteGridProcess(AAGOOGLE_LINK);
    processWidgetPage.enterSearchKeyword("link");
    assertTrue(processWidgetPage.isNoProcessFound());
  }

  private void createPublicExternalTestProcess(String processName, String processLink, String rolePermission) {
    AddNewExternalLinkDialog addNewExternalLinkDialog = processWidgetPage.openNewExternalLinkDialog();
    addNewExternalLinkDialog.inputDataForPublicExternalLink(processName, processLink, rolePermission);
    addNewExternalLinkDialog.submitForm();
  }
}

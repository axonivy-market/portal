package portal.guitest.test;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.ExpressManagementPage;
import portal.guitest.page.ExpressTaskPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessWidgetPage;

public class ExpressManagementTest extends BaseTest {

  private HomePage homePage;
  private ProcessWidgetPage processWidget;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void testImportUnsupportedExtension() {
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openExpressManagementTab();
    ExpressManagementPage expressManagementPage = new ExpressManagementPage();
    expressManagementPage.openImportDialog();
    expressManagementPage.selectJSONFile("unsupportedExtension.abc");
    String message = expressManagementPage.getUploadMessage();
    expressManagementPage.clickOnCloseButton();
    assertEquals("Invalid file type", message);
  }

  @Test
  public void testImportExpressProcess() {
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.openExpressManagementTab();
    ExpressManagementPage expressManagementPage = new ExpressManagementPage();
    expressManagementPage.openImportDialog();
    expressManagementPage.selectJSONFile("express-test.json");
    expressManagementPage.clickOnDeployExpress();
    Sleeper.sleep(5000);
    expressManagementPage.clickOnCloseButton();
    adminSettingsPage.closeAdminSettingDialog();
    adminSettingsPage.closeInformConfigDialog();
    Sleeper.sleep(2000);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    startExpressHelperProcess("Express Test 1");
    executeUserTask();
  }

  private void startExpressHelperProcess(String processName) {
    homePage = new HomePage();
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    assertTrue(processWidget.isExpandedMode());
    processWidget.enterSearchKeyword(processName);
    assertTrue(processWidget.isProcessDisplay(processName));
    processWidget.startProcess(processName);
  }

  private void executeUserTask() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.finish();
    HomePage home = new HomePage();
    home.waitForPageLoaded();
  }
}

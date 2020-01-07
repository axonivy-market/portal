package portal.guitest.test;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
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
    homePage = new HomePage();
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void testImportUnsupportedExtension() {
    startExpressHelperProcess("Express Management");
    ExpressManagementPage expressManagementPage = new ExpressManagementPage();
    expressManagementPage.openImportDialog();
    String message = expressManagementPage.selectJSONFile("unsupportedExtension.abc");
    assertEquals("This file type is not accepted!", message);
  }

  @Test
  public void testImportExpressProcess() {
    startExpressHelperProcess("Express Management");
    ExpressManagementPage expressHelperPage = new ExpressManagementPage();
    expressHelperPage.openImportDialog();
    expressHelperPage.selectJSONFile("express-test.json");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    startExpressHelperProcess("Express Test 1");
    Sleeper.sleep(1000);
    executeUserTask();
  }

  private void startExpressHelperProcess(String processName) {
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

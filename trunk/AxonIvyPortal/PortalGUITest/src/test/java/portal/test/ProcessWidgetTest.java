package portal.test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.ProcessWidgetPage;
import portal.page.ProcessWidgetPage.AddNewProcessDialog;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

public class ProcessWidgetTest extends BaseTest {

  private static final String CLEAN_ALL_FAVORITE_PROCESSES = "(For autotest) Clean all favorite processes";
  private static final String CASE_MAP_LEAVES = "caseMapLeaves";
  
  private HomePage homePage;
  ProcessWidgetPage processWidget;

  @Before
  @Override
  public void setup() {
    super.setup();

    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    homePage = new HomePage();
  }

  @Test
  public void testCanExpandCollapse() {
    processWidget = homePage.getProcessWidget();

    assertTrue(processWidget.isCompactMode());

    processWidget.expand();

    assertTrue(processWidget.isExpandedMode());
  }
  
  @Test
  public void testCaseMapIsDisplayedInExpandedMode() {
    processWidget = homePage.getProcessWidget();
    assertTrue(processWidget.isCompactMode());
    processWidget.expand();
    assertTrue(processWidget.isExpandedMode());
    assertNotEquals(processWidget.getProcess(CASE_MAP_LEAVES), null);
  }

  @Test
  public void testDeleteProcess() {
    String processName = "Google";
    String processLink = "google.com";
    processWidget = homePage.getProcessWidget();
    createTestProcess(processName, processLink);
    int numberOfProcesses = processWidget.getNumberOfUserProcesses();
    processWidget.clickDeletionSwitchLink();
    int indexOfLastProcess = numberOfProcesses - 1;
    processWidget.checkDeleteItem(indexOfLastProcess);;
    assertTrue(processWidget.isDeleteProcessItemSelected(indexOfLastProcess));
    processWidget.clickDeleteProcess();
    assertEquals(numberOfProcesses - 1, processWidget.getNumberOfUserProcesses());;
  }
  
  @Test //May not run on IE version 11.0.20 or later due to Selenium.
  public void testOpenExternalProcessInNewTab() {
    String processName = "Google";
    String processLink = "google.com";
    processWidget = homePage.getProcessWidget();
    if (processWidget.getProcess(processName) == null) {
      createTestProcess(processName, processLink);
    }
    assertEquals(1, homePage.countBrowserTab());

    processWidget.startProcess(processName);
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
    assertEquals("Google", homePage.getPageTitle());
  }

  @Test
  public void testAddProcessBySelectingIvyProcess() {
    processWidget = homePage.getProcessWidget();
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectIvyProcessByName(CLEAN_ALL_FAVORITE_PROCESSES);
    addNewProcessDialog.submitForm();
    assertNotEquals(processWidget.getProcess(CLEAN_ALL_FAVORITE_PROCESSES), null);
  }
  
  @Test
  public void testAddCaseMapBySelectingCaseMap() {
    processWidget = homePage.getProcessWidget();
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectIvyProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();
    assertNotEquals(processWidget.getProcess(CASE_MAP_LEAVES), null);
  }

  private void createTestProcess(String processName, String processLink) {
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.inputData(processName, processLink);
    addNewProcessDialog.submitForm();
  }
}

package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.ProcessWidgetPage.AddNewProcessDialog;
public class ProcessWidgetTest extends BaseTest {

  private static final String CLEAN_ALL_FAVORITE_PROCESSES = "(For autotest) Clean all favorite processes";
  private static final String CASE_MAP_LEAVES = "Case Map Leave";

  private HomePage homePage;
  ProcessWidgetPage processWidget;

  @Before
  @Override
  public void setup() {
    super.setup();

    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
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
    assertNotNull(processWidget.getProcess(CASE_MAP_LEAVES));
  }

  @Test
  public void testDeleteProcess() {
    String processName = "Google";
    String processLink = "google.com";
    processWidget = homePage.getProcessWidget();
    createExternalTestProcess(processName, processLink);
    int numberOfProcesses = processWidget.getNumberOfFavoriteUserProcesses();
    processWidget.clickEditSwitchLink();
    int indexOfProcess = numberOfProcesses - 1;
    processWidget.checkDeleteItem(indexOfProcess);
    assertTrue(processWidget.isDeleteProcessItemSelected(indexOfProcess));
    processWidget.clickSaveProcess();
    assertEquals(numberOfProcesses - 1, processWidget.getNumberOfFavoriteUserProcesses());
  }

  @Test
  public void testSortFavoriteProcessByName() {
    processWidget = homePage.getProcessWidget();
    String processName = "Google";
    String processLink = "google.com";
    createExternalTestProcess(processName, processLink);
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectIvyProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();
    addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectIvyProcessByName(CLEAN_ALL_FAVORITE_PROCESSES);
    addNewProcessDialog.submitForm();
    processWidget.clickSortFavoriteProcessByName();
    assertEquals(CLEAN_ALL_FAVORITE_PROCESSES, processWidget.getProcessNameFromFavoriteProcessList(0));
    assertEquals(CASE_MAP_LEAVES, processWidget.getProcessNameFromFavoriteProcessList(1));
    assertEquals(processName, processWidget.getProcessNameFromFavoriteProcessList(2));
  }

  @Test
  public void testSortDefaultProcessByName() {
    processWidget = homePage.getProcessWidget();
    String alphaHistoryTask = "Alpha Company Task";
    String betaHistoryTask = "Beta Company Task";
    String viewAlphaHistory = "View Alpha Process History";
    String viewBetaHistory = "View Beta Process History";
    processWidget.clickSortDefaultProcessByName();
    assertEquals(alphaHistoryTask, processWidget.getProcessNameFromDefaultProcessList(0));
    assertEquals(betaHistoryTask, processWidget.getProcessNameFromDefaultProcessList(1));
    assertEquals(viewAlphaHistory, processWidget.getProcessNameFromDefaultProcessList(2));
    assertEquals(viewBetaHistory, processWidget.getProcessNameFromDefaultProcessList(3));
  }

  @Test
  public void testSortDefaultProcessByIndex() {
    String alphaHistoryTask = "Alpha Company Task";
    String betaHistoryTask = "Beta Company Task";
    String viewAlphaHistory = "View Alpha Process History";
    String viewBetaHistory = "View Beta Process History";
    processWidget = homePage.getProcessWidget();
    assertEquals(alphaHistoryTask, processWidget.getProcessNameFromDefaultProcessList(0));
    assertEquals(betaHistoryTask, processWidget.getProcessNameFromDefaultProcessList(1));
    assertEquals(viewAlphaHistory, processWidget.getProcessNameFromDefaultProcessList(2));
    assertEquals(viewBetaHistory, processWidget.getProcessNameFromDefaultProcessList(3));
  }

  @Test
  public void testReorderFavoriteProcess() {
    processWidget = homePage.getProcessWidget();
    String processName = "Google";
    String processLink = "google.com";
    createExternalTestProcess(processName, processLink);
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectIvyProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();
    addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectIvyProcessByName(CLEAN_ALL_FAVORITE_PROCESSES);
    addNewProcessDialog.submitForm();
    processWidget.clickEditSwitchLink();
    processWidget.moveFavoriteProcess(3, 1);
    processWidget.clickSaveProcess();
    assertEquals(CLEAN_ALL_FAVORITE_PROCESSES, processWidget.getProcessNameFromFavoriteProcessList(0));
    assertEquals(CASE_MAP_LEAVES, processWidget.getProcessNameFromFavoriteProcessList(2));
    processName = "AGoogle";
    processLink = "google.com";
    createExternalTestProcess(processName, processLink);
    assertEquals(processName, processWidget.getProcessNameFromFavoriteProcessList(3));
  }

  @Test
  // May not run on IE version 11.0.20 or later due to Selenium.
  public void testOpenExternalProcessInNewTab() {
    String processName = "Google";
    String processLink = "google.com";
    processWidget = homePage.getProcessWidget();
    if (processWidget.getProcess(processName) == null) {
      createExternalTestProcess(processName, processLink);
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
    assertNotNull(processWidget.getProcess(CLEAN_ALL_FAVORITE_PROCESSES));
  }

  @Test
  public void testDefaultProcessIsExcludedFromSearchProcessToAdd() {
    processWidget = homePage.getProcessWidget();
    String processName = "Axon.ivy Selfservice";
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    assertFalse(addNewProcessDialog.isIvyProcessByNameSearchable(processName));
  }

  @Test
  public void testAddCaseMapBySelectingCaseMap() {
    processWidget = homePage.getProcessWidget();
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectIvyProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();
    assertNotNull(processWidget.getProcess(CASE_MAP_LEAVES));
  }

  @Test
  public void testDefaultIconOfANewFavoriteProcess() {
    processWidget = homePage.getProcessWidget();
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    assertTrue(addNewProcessDialog.isDefaultIcon());

  }

  @Test
  public void testSearchAnExistedIcon() {
    processWidget = homePage.getProcessWidget();
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.clickChangeIconButton();
    addNewProcessDialog.inputSearchedIconName("area chart");
    assertEquals(1, addNewProcessDialog.getDisplayedIconAmount());

  }

  @Test
  public void testSearchANonExistedIcon() {
    processWidget = homePage.getProcessWidget();
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.clickChangeIconButton();
    addNewProcessDialog.inputSearchedIconName("chart1");
    assertEquals(0, addNewProcessDialog.getDisplayedIconAmount());

  }

  private void createExternalTestProcess(String processName, String processLink) {
    AddNewProcessDialog addNewProcessDialog = processWidget.openNewProcessDialog();
    processWidget.selectProcessTypeExternal();
    addNewProcessDialog.inputDataForExternalProcess(processName, processLink);
    addNewProcessDialog.submitForm();
  }
}

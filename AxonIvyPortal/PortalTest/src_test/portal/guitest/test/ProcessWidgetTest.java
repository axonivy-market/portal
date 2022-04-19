package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.ProcessWidgetPage.AddNewExternalLinkDialog;
import portal.guitest.page.UserFavoriteProcessPage;
import portal.guitest.page.UserFavoriteProcessPage.SettingProcessLanguageDialog;
import portal.guitest.page.UserProfilePage;

public class ProcessWidgetTest extends BaseTest {

  private static final String EXAMPLE_APPLICATION_PROCESSES = "portal-developer-examples/178ED537303DFF8E/start.ivp";
  private static final String CLEAN_ALL_FAVORITE_PROCESSES = "(For autotest) Clean all favorite processes";
  private static final String CASE_MAP_LEAVES = "Case Map: Leave Request";
  private static final String APPRAISAL = "Appraisal";
  private static final String AGOOGLE_LINK = "AGoogle";
  private static final String AAGOOGLE_LINK = "AAGoogle";
  private static final String ALPHA_HISTORY_TASK = "Alpha Company Task";
  private static final String VIEW_ALPHA_HISTORY = "View Alpha Process History";
  private static final String VIEW_ALPHA_HISTORY_IN_DIALOG = "View Alpha Process History in Dialog";

  private HomePage homePage;
  ProcessWidgetPage processWidget;

  @Before
  @Override
  public void setup() {
    super.setup();
    homePage = new HomePage();
  }

  @Test
  public void testCanExpandCollapse() {
    processWidget = homePage.getProcessWidget();
    assertTrue(processWidget.isCompactMode());
    processWidget.expand();
    assertTrue(processWidget.isExpandedMode());
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testCaseMapIsDisplayedInExpandedMode() {
    processWidget = homePage.getProcessWidget();
    assertTrue(processWidget.isCompactMode());
    processWidget.expand();
    assertTrue(processWidget.isExpandedMode());
    assertNotNull(processWidget.getProcess(CASE_MAP_LEAVES));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testDeleteProcess() {
    String processName = "AGoogle";
    String processLink = "google.com";
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    createExternalTestProcess(processName, processLink, false);
    
    processName = "AAGoogle";
    processLink = "google.com";
    createExternalTestProcess(processName, processLink, false);
    
    backToCompactProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(AAGOOGLE_LINK);
    addNewProcessDialog.submitForm();
    
    addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(AGOOGLE_LINK);
    addNewProcessDialog.submitForm();
    
    int numberOfProcesses = processWidget.getNumberOfFavoriteUserProcesses();
    processWidget.clickEditSwitchLink();
    int indexOfProcess = numberOfProcesses - 1;
    processWidget.checkDeleteItem(indexOfProcess);
    assertTrue(processWidget.isDeleteProcessItemSelected(indexOfProcess));
    processWidget.clickSaveProcess();
    assertEquals(numberOfProcesses - 1, processWidget.getNumberOfFavoriteUserProcesses());
    
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    processWidget.selectViewMode("GRID");
    processWidget.waitForGridProcessListDisplayed();
    processWidget.clickMoreButtonOfFirstGridProcess();
    processWidget.deleteProcess(0);
    backToCompactProcessWidget();
    
    assertEquals(0, processWidget.getNumberOfFavoriteUserProcesses());
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testSortFavoriteProcessByName() {
    processWidget = homePage.getProcessWidget();
    String processName = "AGoogle";
    String processLink = "google.com";
    processWidget.expand();
    createExternalTestProcess(processName, processLink, false);
    backToCompactProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();
    addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(CLEAN_ALL_FAVORITE_PROCESSES);
    addNewProcessDialog.submitForm();
    addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(processName);
    addNewProcessDialog.submitForm();
    processWidget.clickSortFavoriteProcessByName();
    assertEquals(CLEAN_ALL_FAVORITE_PROCESSES, processWidget.getProcessNameFromFavoriteProcessList(0));
    assertEquals(processName, processWidget.getProcessNameFromFavoriteProcessList(1));
    assertEquals(CASE_MAP_LEAVES, processWidget.getProcessNameFromFavoriteProcessList(2));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testSortDefaultProcessByName() {
    redirectToRelativeLink(EXAMPLE_APPLICATION_PROCESSES);
    processWidget = new ProcessWidgetPage();
    processWidget.clickSortDefaultProcessByName();
    assertEquals(ALPHA_HISTORY_TASK, processWidget.getProcessNameFromDefaultProcessList(0));
    assertEquals("Favorite Express Process Display Name", processWidget.getProcessNameFromDefaultProcessList(1));
    assertEquals("Favorite External Process Display Name", processWidget.getProcessNameFromDefaultProcessList(2));
    assertEquals(VIEW_ALPHA_HISTORY, processWidget.getProcessNameFromDefaultProcessList(3));
    assertEquals(VIEW_ALPHA_HISTORY_IN_DIALOG, processWidget.getProcessNameFromDefaultProcessList(4));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testSortDefaultProcessByIndex() {
    redirectToRelativeLink(EXAMPLE_APPLICATION_PROCESSES);
    processWidget = new ProcessWidgetPage();
    assertEquals(ALPHA_HISTORY_TASK, processWidget.getProcessNameFromDefaultProcessList(0));
    assertEquals(VIEW_ALPHA_HISTORY, processWidget.getProcessNameFromDefaultProcessList(1));
    assertEquals(VIEW_ALPHA_HISTORY_IN_DIALOG, processWidget.getProcessNameFromDefaultProcessList(2));
    assertEquals("Favorite Express Process Display Name", processWidget.getProcessNameFromDefaultProcessList(3));
    assertEquals("Favorite External Process Display Name", processWidget.getProcessNameFromDefaultProcessList(4));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testReorderFavoriteProcess() {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    String processName = "AGoogle";
    String processLink = "google.com";
    createExternalTestProcess(processName, processLink,false);
    processName = "AAGoogle";
    processLink = "google.com";
    createExternalTestProcess(processName, processLink, false);
    
    backToCompactProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(AGOOGLE_LINK);
    addNewProcessDialog.submitForm();
    
    addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();

    addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(CLEAN_ALL_FAVORITE_PROCESSES);
    addNewProcessDialog.submitForm();

    processWidget.clickEditSwitchLink();
    processWidget.moveFavoriteProcess(3, 1);
    WaitHelper.assertTrueWithWait(() -> CLEAN_ALL_FAVORITE_PROCESSES.equals(processWidget.findElementByCssSelector(".process-start-list-item-name").getText()));
    processWidget.clickSaveProcess();

    assertEquals(CLEAN_ALL_FAVORITE_PROCESSES, processWidget.getProcessNameFromFavoriteProcessList(0));
    assertEquals(AGOOGLE_LINK, processWidget.getProcessNameFromFavoriteProcessList(1));

    addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(AAGOOGLE_LINK);
    addNewProcessDialog.submitForm();
    assertEquals(processName, processWidget.getProcessNameFromFavoriteProcessList(3));
    resetLanguageOfCurrentUser();
  }

  @Test
  // May not run on IE version 11.0.20 or later due to Selenium.
  public void testOpenExternalProcessInNewTab() {
    String processName = "AGoogle";
    String processLink = "google.com";
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    createExternalTestProcess(processName, processLink, false);
    
    backToCompactProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(AGOOGLE_LINK);
    addNewProcessDialog.submitForm();
    
    assertEquals(1, homePage.countBrowserTab());

    processWidget.startProcess(AGOOGLE_LINK);
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.getPageTitle().length() > 1);
    assertEquals("Google", homePage.getPageTitle());
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testAddProcessBySelectingIvyProcess() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(CLEAN_ALL_FAVORITE_PROCESSES);
    addNewProcessDialog.submitForm();
    assertNotNull(processWidget.getProcess(CLEAN_ALL_FAVORITE_PROCESSES));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testAddProcessWithMultilingual() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(APPRAISAL);
    SettingProcessLanguageDialog settingProcessLanguageDialog = addNewProcessDialog.openAddlanguageDialog();
    settingProcessLanguageDialog.fillProcessNamesByLocaleName();
    addNewProcessDialog.submitForm();
    
    assertNotNull(processWidget.getProcess(String.format("%s - %s", APPRAISAL, "English Name*")));
    
    // Change language to German
    changeLanguage(0);
    
    processWidget = homePage.getProcessWidget();
    assertNotNull(processWidget.getProcess(String.format("%s - %s", APPRAISAL, "German Name")));
    resetLanguageOfCurrentUser();
  }

  private void changeLanguage(int selectionIndex) {
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    userProfilePage.selectLanguage(selectionIndex);
    homePage = userProfilePage.save();
  }
  
  @Test
  public void testDefaultProcessIsExcludedFromSearchProcessToAdd() {
    processWidget = homePage.getProcessWidget();
    String processName = "Axon Ivy Selfservice";
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    assertFalse(addNewProcessDialog.isIvyProcessByNameSearchable(processName));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testAddCaseMapBySelectingCaseMap() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();
    assertNotNull(processWidget.getProcess(CASE_MAP_LEAVES));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testDefaultIconOfANewFavoriteProcess() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    assertTrue(addNewProcessDialog.isDefaultIcon());
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testSearchAnExistedIcon() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.clickChangeIconButton();
    addNewProcessDialog.inputSearchedIconName("area chart");
    assertEquals(1, addNewProcessDialog.getDisplayedIconAmount());
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testSearchANonExistedIcon() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.clickChangeIconButton();
    addNewProcessDialog.inputSearchedIconName("chart1");
    assertEquals(0, addNewProcessDialog.getDisplayedIconAmount());
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testBreadCrumb() {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    assertEquals("Processes", processWidget.getTextOfCurrentBreadcrumb());

    processWidget.goToHomeFromBreadcrumb();
    homePage = new HomePage();
    assertEquals(true, homePage.isDisplayed());
    resetLanguageOfCurrentUser();
  }

  private void createExternalTestProcess(String processName, String processLink, boolean isPublic) {
    AddNewExternalLinkDialog addNewExternalLinkDialog = processWidget.openNewExternalLinkDialog();
    addNewExternalLinkDialog.inputDataForExternalLink(processName, processLink, isPublic);
    addNewExternalLinkDialog.submitForm();
  }

  public void backToCompactProcessWidget() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    processWidget = homePage.getProcessWidget();
  }
}

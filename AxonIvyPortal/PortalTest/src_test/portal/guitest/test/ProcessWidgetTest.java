package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.ProcessWidgetPage.AddNewExternalLinkDialog;
import portal.guitest.page.UserFavoriteProcessPage;
import portal.guitest.page.UserProfilePage;
import portal.guitest.page.UserFavoriteProcessPage.SettingProcessLanguageDialog;

public class ProcessWidgetTest extends BaseTest {

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
    processWidget.deleteExternalLinkByFieldsetIndexAndIndex(0, 0);
    backToCompactProcessWidget();
    
    assertEquals(0, processWidget.getNumberOfFavoriteUserProcesses());
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
  }

  @Test
  public void testSortDefaultProcessByName() {
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    homePage = new HomePage();
    processWidget = homePage.getProcessWidget();
    processWidget.clickSortDefaultProcessByName();
    assertEquals(ALPHA_HISTORY_TASK, processWidget.getProcessNameFromDefaultProcessList(0));
    assertEquals(VIEW_ALPHA_HISTORY, processWidget.getProcessNameFromDefaultProcessList(1));
    assertEquals(VIEW_ALPHA_HISTORY_IN_DIALOG, processWidget.getProcessNameFromDefaultProcessList(2));
  }

  @Test
  public void testSortDefaultProcessByIndex() {
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    homePage = new HomePage();
    processWidget = homePage.getProcessWidget();
    assertEquals(ALPHA_HISTORY_TASK, processWidget.getProcessNameFromDefaultProcessList(0));
    assertEquals(VIEW_ALPHA_HISTORY, processWidget.getProcessNameFromDefaultProcessList(1));
    assertEquals(VIEW_ALPHA_HISTORY_IN_DIALOG, processWidget.getProcessNameFromDefaultProcessList(2));
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
    processWidget.moveFavoriteProcess(1, 3);
    WaitHelper.assertTrueWithWait(() -> CASE_MAP_LEAVES.equals(processWidget.findElementByCssSelector(".process-start-list-item-name").getText()));
    processWidget.clickSaveProcess();

    assertEquals(CASE_MAP_LEAVES, processWidget.getProcessNameFromFavoriteProcessList(0));
    assertEquals(CLEAN_ALL_FAVORITE_PROCESSES, processWidget.getProcessNameFromFavoriteProcessList(1));

    addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(AAGOOGLE_LINK);
    addNewProcessDialog.submitForm();
    assertEquals(processName, processWidget.getProcessNameFromFavoriteProcessList(3));
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
  }

  @Test
  public void testAddProcessBySelectingIvyProcess() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(CLEAN_ALL_FAVORITE_PROCESSES);
    addNewProcessDialog.submitForm();
    assertNotNull(processWidget.getProcess(CLEAN_ALL_FAVORITE_PROCESSES));
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
  }

  private void changeLanguage(int selectionIndex) {
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    userProfilePage.selectLanguage(selectionIndex);
    userProfilePage.save();
    homePage = userProfilePage.clickOnCancelLink();
  }
  
  @Test
  public void testDefaultProcessIsExcludedFromSearchProcessToAdd() {
    processWidget = homePage.getProcessWidget();
    String processName = "Axon.ivy Selfservice";
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    assertFalse(addNewProcessDialog.isIvyProcessByNameSearchable(processName));
  }

  @Test
  public void testAddCaseMapBySelectingCaseMap() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.selectProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();
    assertNotNull(processWidget.getProcess(CASE_MAP_LEAVES));
  }

  @Test
  public void testDefaultIconOfANewFavoriteProcess() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    assertTrue(addNewProcessDialog.isDefaultIcon());
  }

  @Test
  public void testSearchAnExistedIcon() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.clickChangeIconButton();
    addNewProcessDialog.inputSearchedIconName("area chart");
    assertEquals(1, addNewProcessDialog.getDisplayedIconAmount());
  }

  @Test
  public void testSearchANonExistedIcon() {
    processWidget = homePage.getProcessWidget();
    UserFavoriteProcessPage addNewProcessDialog = processWidget.openNewProcessDialog();
    addNewProcessDialog.clickChangeIconButton();
    addNewProcessDialog.inputSearchedIconName("chart1");
    assertEquals(0, addNewProcessDialog.getDisplayedIconAmount());
  }

  @Test
  public void testBreadCrumb() {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    assertEquals("Processes", processWidget.getTextOfCurrentBreadcrumb());

    processWidget.goToHomeFromBreadcrumb();
    homePage = new HomePage();
    assertEquals(true, homePage.isDisplayed());
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
  
  @AfterClass
  @BeforeClass
  public static void cleanUpBrowsers() {
    killBrowsers();
  }
  
  @After
  public void revertChanges() {
    resetLanguageOfCurrentUser();
  }
}

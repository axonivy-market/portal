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
  private static final String EMPLOYEE_SEARCH = "Employee Search";
  private static final String EMPLOYEE_SEARCH_IN_IFRAME = "Employee Search in Iframe";
  private static final String CREATE_INVESTMENT = "Create Investment";

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
    createPrivateExternalTestProcess(processName, processLink);
    
    processName = "AAGoogle";
    processLink = "google.com";
    createPrivateExternalTestProcess(processName, processLink);
    
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
    processWidget.selectViewMode(ProcessWidgetPage.GRID_MODE);
    processWidget.waitForGridProcessListDisplayed();
    processWidget.clickMoreButtonOfFirstGridProcess();
    processWidget.deleteGridProcess(0);
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
    createPrivateExternalTestProcess(processName, processLink);
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
    assertEquals(CREATE_INVESTMENT, processWidget.getProcessNameFromDefaultProcessList(0));
    assertEquals(EMPLOYEE_SEARCH, processWidget.getProcessNameFromDefaultProcessList(1));
    assertEquals(EMPLOYEE_SEARCH_IN_IFRAME, processWidget.getProcessNameFromDefaultProcessList(2));
    assertEquals("Favorite Express Process Display Name", processWidget.getProcessNameFromDefaultProcessList(3));
    assertEquals("Favorite External Process Display Name", processWidget.getProcessNameFromDefaultProcessList(4));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testSortDefaultProcessByIndex() {
    redirectToRelativeLink(EXAMPLE_APPLICATION_PROCESSES);
    processWidget = new ProcessWidgetPage();
    assertEquals(EMPLOYEE_SEARCH, processWidget.getProcessNameFromDefaultProcessList(0));
    assertEquals(EMPLOYEE_SEARCH_IN_IFRAME, processWidget.getProcessNameFromDefaultProcessList(1));
    assertEquals(CREATE_INVESTMENT, processWidget.getProcessNameFromDefaultProcessList(2));
    assertEquals("Favorite Express Process Display Name", processWidget.getProcessNameFromDefaultProcessList(3));
    assertEquals("Favorite External Process Display Name", processWidget.getProcessNameFromDefaultProcessList(4));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testReorderFavoriteProcess() {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    processWidget.openMainMenu();
    String processName = "AGoogle";
    String processLink = "google.com";
    createPrivateExternalTestProcess(processName, processLink);
    processName = "AAGoogle";
    processLink = "google.com";
    createPrivateExternalTestProcess(processName, processLink);
    
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

    var processNameBeforeChanging = processWidget.findListElementsByCssSelector(".process-start-list-item-name").get(1).getText();
    processWidget.clickEditSwitchLink();
    processWidget.moveFavoriteProcess(3, 1);
    WaitHelper.assertTrueWithWait(() -> !processNameBeforeChanging
        .equals(processWidget.findListElementsByCssSelector(".process-start-list-item-name").get(1).getText()));
    processWidget.clickSaveProcess();

    assertFalse(processNameBeforeChanging.equals(processWidget.getProcessNameFromFavoriteProcessList(1)));

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
    createPrivateExternalTestProcess(processName, processLink);
    
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
    changeLanguage(3);
    
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

  private void createPrivateExternalTestProcess(String processName, String processLink) {
    AddNewExternalLinkDialog addNewExternalLinkDialog = processWidget.openNewExternalLinkDialog();
    addNewExternalLinkDialog.inputDataForPrivateExternalLink(processName, processLink);
    addNewExternalLinkDialog.submitForm();
  }

  public void backToCompactProcessWidget() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    processWidget = homePage.getProcessWidget();
  }
}

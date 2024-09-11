package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage.AddNewExternalLinkDialog;
import com.axonivy.portal.selenium.page.UserFavoriteProcessPage;
import com.axonivy.portal.selenium.page.UserFavoriteProcessPage.SettingProcessLanguageDialog;

import com.axonivy.portal.selenium.page.UserProfilePage;

@IvyWebTest
public class ProcessWidgetTest extends BaseTest {

  private static final String CASE_MAP_LEAVES = "Case Map: Leave Request";
  private static final String AGOOGLE_LINK = "AGoogle";
  private static final String AAGOOGLE_LINK = "AAGoogle";
  private static final String APPRAISAL = "Appraisal";
  private static final String CLEAN_ALL_FAVORITE_PROCESSES = "(For autotest) Clean all favorite processes";
  private static final String EXAMPLE_APPLICATION_PROCESSES = "portal-developer-examples/178ED537303DFF8E/start.ivp";
  private static final String EMPLOYEE_SEARCH = "Employee Search";
  private static final String EMPLOYEE_SEARCH_IN_IFRAME = "Employee Search in Iframe";
  private static final String CREATE_INVESTMENT = "Create Investment";





  private HomePage homePage;
  private NewDashboardPage newDashboardPage;
  private ProcessWidgetPage processWidget;

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testCaseMapIsDisplayedInExpandedMode() {
    processWidget = NavigationHelper.navigateToProcessList();
    processWidget.waitPageLoaded();
    assertNotNull(processWidget.getProcess(CASE_MAP_LEAVES));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testDeleteProcess() {
    String processName = "AGoogle";
    String processLink = "google.com";
    processWidget = NavigationHelper.navigateToProcessList();
    createPrivateExternalTestProcess(processName, processLink);
    processWidget = NavigationHelper.navigateToProcessList();
    processWidget.selectViewMode(ProcessWidgetPage.GRID_MODE);
    processWidget.waitForGridProcessListDisplayed();
    processWidget.clickMoreButtonOfFirstGridProcess();
    processWidget.deleteGridProcess(0);
    resetLanguageOfCurrentUser();
  }

  @Test
  // May not run on IE version 11.0.20 or later due to Selenium.
  public void testOpenExternalProcessInNewTab() {
    String processName = "AGoogle";
    String processLink = "google.com";
    processWidget = NavigationHelper.navigateToProcessList();
    createPrivateExternalTestProcess(processName, processLink);
    assertEquals(1, newDashboardPage.countBrowserTab());
    processWidget = NavigationHelper.navigateToProcessList();
    processWidget.waitPageLoaded();
    assertNotNull(processWidget.getProcess(AGOOGLE_LINK));
    processWidget.startProcess(AGOOGLE_LINK);
    webDriverWait().until((webDriver) -> newDashboardPage.countBrowserTab() > 1);
    newDashboardPage.switchLastBrowserTab();
    webDriverWait().until((webDriver) -> newDashboardPage.getPageTitle().length() > 1);
    assertEquals("Google", newDashboardPage.getPageTitle());
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testBreadCrumb() {
    processWidget = NavigationHelper.navigateToProcessList();
    assertEquals("Processes", processWidget.getTextOfCurrentBreadcrumb());

    processWidget.goToHomeFromBreadcrumb();
    newDashboardPage = new NewDashboardPage();
    assertEquals(true, newDashboardPage.isDisplayed());
    resetLanguageOfCurrentUser();
  }

  private void createPrivateExternalTestProcess(String processName, String processLink) {
    AddNewExternalLinkDialog addNewExternalLinkDialog = processWidget.openNewExternalLinkDialog();
    addNewExternalLinkDialog.inputDataForPrivateExternalLink(processName, processLink);
    addNewExternalLinkDialog.submitForm();
  }

  @Test
  public void testAddCaseMapBySelectingCaseMap() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();

    processWidget = homePage.getProcessWidget();
    processWidget.openNewProcessDialog();
    UserFavoriteProcessPage addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.selectProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();
    assertNotNull(processWidget.getProcess(CASE_MAP_LEAVES));
    resetLanguageOfCurrentUser();
  }
  
  @Test
  public void testAddProcessBySelectingIvyProcess() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    
    processWidget = homePage.getProcessWidget();
    processWidget.openNewProcessDialog();
    UserFavoriteProcessPage addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.selectProcessByName(CLEAN_ALL_FAVORITE_PROCESSES);
    addNewProcessDialog.submitForm();
    assertNotNull(processWidget.getProcess(CLEAN_ALL_FAVORITE_PROCESSES));
    resetLanguageOfCurrentUser();
  }
  
  @Test
  public void testAddProcessWithMultilingual() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    
    processWidget = homePage.getProcessWidget();
    processWidget.openNewProcessDialog();
    UserFavoriteProcessPage addNewProcessDialog = new UserFavoriteProcessPage();
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
    homePage = userProfilePage.saveAndGoToHomePage();
  }

  
  @Test
  public void testCanExpandCollapse() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    
    processWidget = homePage.getProcessWidget();
    assertTrue(processWidget.isCompactMode());
    processWidget.expand();
    assertTrue(processWidget.isExpandedMode());
    resetLanguageOfCurrentUser();
  }
  
  @Test
  public void testDefaultIconOfANewFavoriteProcess() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();

    processWidget = homePage.getProcessWidget();
    processWidget.openNewProcessDialog();
    UserFavoriteProcessPage addNewProcessDialog = new UserFavoriteProcessPage();
    assertTrue(addNewProcessDialog.isDefaultIcon());
    resetLanguageOfCurrentUser();
  }
  
  @Test
  public void testDefaultProcessIsExcludedFromSearchProcessToAdd() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    
    processWidget = homePage.getProcessWidget();
    String processName = "Axon Ivy Selfservice";
    processWidget.openNewProcessDialog();
    UserFavoriteProcessPage addNewProcessDialog = new UserFavoriteProcessPage();
    assertFalse(addNewProcessDialog.isIvyProcessByNameSearchable(processName));
    resetLanguageOfCurrentUser();
  }
  
  @Test
  public void testReorderFavoriteProcess() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToNewDashBoard();
    homePage = new HomePage();
    
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    processWidget.openMainMenu();
    String processName = "AGoogle";
    String processLink = "google.com";
    createPrivateExternalTestProcess(processName, processLink);
    processName = "AAGoogle";
    processLink = "google.com";
    createPrivateExternalTestProcess(processName, processLink);
    homePage.clickOnLogo();
    
    homePage = new HomePage();
    processWidget = homePage.getProcessWidget();
    processWidget.openNewProcessDialog();
    UserFavoriteProcessPage addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.selectProcessByName(AGOOGLE_LINK);
    addNewProcessDialog.submitForm();

    processWidget.openAddNewUserProcess();
    addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.selectProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();

    processWidget.openAddNewUserProcess();
    addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.selectProcessByName(CLEAN_ALL_FAVORITE_PROCESSES);
    addNewProcessDialog.submitForm();

    var processNameBeforeChanging = processWidget.getProcessStartListItem(1);
    processWidget.clickEditSwitchLink();
    processWidget.moveFavoriteProcess(3, 1);
    assertTrue(!processNameBeforeChanging.equals(processWidget.getProcessStartListItem(1)));
    processWidget.clickSaveProcess();

    assertFalse(processNameBeforeChanging.equals(processWidget.getProcessNameFromFavoriteProcessList(1)));

    processWidget.openAddNewUserProcess();
    addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.selectProcessByName(AAGOOGLE_LINK);
    addNewProcessDialog.submitForm();
    assertEquals(processName, processWidget.getProcessNameFromFavoriteProcessList(3));
    resetLanguageOfCurrentUser();
  }
  
  public void backToCompactProcessWidget() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    processWidget = homePage.getProcessWidget();
  }

  
  @Test
  public void testSearchAnExistedIcon() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToNewDashBoard();
    homePage = new HomePage();

    processWidget = homePage.getProcessWidget();
    processWidget.openNewProcessDialog();
    UserFavoriteProcessPage addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.clickChangeIconButton();
    addNewProcessDialog.inputSearchedIconName("area chart");
    assertEquals(1, addNewProcessDialog.getDisplayedIconAmount());
    resetLanguageOfCurrentUser();
  }
  
  @Test
  public void testSearchANonExistedIcon() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToNewDashBoard();
    homePage = new HomePage();
    
    processWidget = homePage.getProcessWidget();
    processWidget.openNewProcessDialog();
    UserFavoriteProcessPage addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.clickChangeIconButton();
    addNewProcessDialog.inputSearchedIconName("chart1");
    assertEquals(0, addNewProcessDialog.getDisplayedIconAmount());
    resetLanguageOfCurrentUser();
  }
  
  @Test
  public void testSortDefaultProcessByIndex() {
    redirectToRelativeLink(EXAMPLE_APPLICATION_PROCESSES);
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();

    processWidget = homePage.getProcessWidget();
    assertEquals(EMPLOYEE_SEARCH, processWidget.getProcessNameFromDefaultProcessList(0));
    assertEquals(EMPLOYEE_SEARCH_IN_IFRAME, processWidget.getProcessNameFromDefaultProcessList(1));
    assertEquals(CREATE_INVESTMENT, processWidget.getProcessNameFromDefaultProcessList(2));
    assertEquals("Favorite Express Process Display Name", processWidget.getProcessNameFromDefaultProcessList(3));
    assertEquals("Favorite External Process Display Name", processWidget.getProcessNameFromDefaultProcessList(4));
    resetLanguageOfCurrentUser();
  }
  
  @Test
  public void testSortDefaultProcessByName() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(EXAMPLE_APPLICATION_PROCESSES);
    redirectToNewDashBoard();
    homePage = new HomePage();

    processWidget = homePage.getProcessWidget();
    assertEquals(EMPLOYEE_SEARCH, processWidget.getProcessNameFromDefaultProcessList(0));
    processWidget.clickSortDefaultProcessByName();
    assertEquals(CREATE_INVESTMENT, processWidget.getProcessNameFromDefaultProcessList(0));
    assertEquals(EMPLOYEE_SEARCH, processWidget.getProcessNameFromDefaultProcessList(1));
    assertEquals(EMPLOYEE_SEARCH_IN_IFRAME, processWidget.getProcessNameFromDefaultProcessList(2));
    assertEquals("Favorite Express Process Display Name", processWidget.getProcessNameFromDefaultProcessList(3));
    assertEquals("Favorite External Process Display Name", processWidget.getProcessNameFromDefaultProcessList(4));
    resetLanguageOfCurrentUser();
  }
  
  @Test
  public void testSortFavoriteProcessByName() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    redirectToRelativeLink(EXAMPLE_APPLICATION_PROCESSES);
    redirectToNewDashBoard();
    homePage = new HomePage();
    
    processWidget = homePage.getProcessWidget();
    String processName = "AGoogle";
    String processLink = "google.com";
    processWidget.expand();
    createPrivateExternalTestProcess(processName, processLink);
    backToCompactProcessWidget();
    processWidget.openNewProcessDialog();
    UserFavoriteProcessPage addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.selectProcessByName(CASE_MAP_LEAVES);
    addNewProcessDialog.submitForm();

    processWidget.openAddNewUserProcess();
    addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.selectProcessByName(CLEAN_ALL_FAVORITE_PROCESSES);
    addNewProcessDialog.submitForm();

    processWidget.openAddNewUserProcess();
    addNewProcessDialog = new UserFavoriteProcessPage();
    addNewProcessDialog.selectProcessByName(processName);
    addNewProcessDialog.submitForm();
    processWidget.clickSortFavoriteProcessByName();
    assertEquals(CLEAN_ALL_FAVORITE_PROCESSES, processWidget.getProcessNameFromFavoriteProcessList(0));
    assertEquals(processName, processWidget.getProcessNameFromFavoriteProcessList(1));
    assertEquals(CASE_MAP_LEAVES, processWidget.getProcessNameFromFavoriteProcessList(2));
    resetLanguageOfCurrentUser();
  }
}

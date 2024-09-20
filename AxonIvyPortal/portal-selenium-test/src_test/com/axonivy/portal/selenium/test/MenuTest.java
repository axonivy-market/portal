package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class MenuTest extends BaseTest {

  private static final String CASES_PAGE_TITLE = "Cases - Portal - Axon Ivy";
  private static final String TASKS_PAGE_TITLE = "Tasks - Portal - Axon Ivy";
  private static final String PROCESSES_PAGE_TITLE = "Processes - Portal - Axon Ivy";
  private static final String DASHBOARD_PAGE_TITLE = "Dashboard - Portal - Axon Ivy";


  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void testLoadCustomMenuItems() {
    redirectToRelativeLink(cleanupDataLink);
    createJSonFile("default-dashboard.json", PortalVariable.DASHBOARD.key);
    createJSonFile("custom-menu-items.json", PortalVariable.CUSTOM_MENU_ITEMS.key);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);

    redirectToNewDashBoard();
    NewDashboardPage home = new NewDashboardPage();
    home.waitForCaseWidgetLoaded();

    MainMenuPage mainMenuPage = new MainMenuPage();
    String expected =
        "Dashboard,Processes,Tasks,Cases,User example guide,Google,Testing link google,Testing example,A link,B link";
    assertEquals(expected, mainMenuPage.getMenuItemsAsString());
  }

  @Test
  public void testKeepOpenStateWhenNavigateToAnotherPage() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    assertTrue(taskWidgetPage.isMainMenuOpen());
  }

  @Test
  public void testKeepClosedStateWhenNavigateToAnotherPage() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetPage dashboardPage = mainMenuPage.openCaseList();
    dashboardPage.waitForPageLoad();

    dashboardPage.closeMainMenu();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
    assertFalse(newDashboardPage.isMainMenuOpen());
  }

  @Test
  public void testNavigateToThirdPartyApp() {
    createThirdPartyApp();
    login(TestAccount.DEMO_USER);
    // to refresh cache
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    mainMenuPage.clickThirdPartyApp();
    mainMenuPage.assertThirdPartyApp("https://www.google.com/");
  }
  
  @Test
  public void testCustomizeIconMainMenuEntry() {
	redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    createJSonFile("custom-main-menu-entry.json", PortalVariable.DASHBOARD_MAIN_MENU_ENTRY.key);
    login(TestAccount.DEMO_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
	redirectToRelativeLink(createSampleDashboardUrl);
    String iconClass = mainMenuPage.getIconClassMainMenuEntryAsString();
    Assertions.assertEquals("si si-layout-bullets",iconClass);
  }
  
  @Test
  public void testCustomizeNameMainMenuEntry() {
	redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    createJSonFile("custom-main-menu-entry.json", PortalVariable.DASHBOARD_MAIN_MENU_ENTRY.key);
    login(TestAccount.DEMO_USER);
	redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
	redirectToRelativeLink(createSampleDashboardUrl);
    String menuName = mainMenuPage.getMainMenuName();
    Assertions.assertEquals("Dashboard Test EN",menuName);	  
  }
  
  @Test
  public void testCustomizeMainMenuEntryMultiLanguage() {
	redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
	createJSonFile("custom-main-menu-entry.json", PortalVariable.DASHBOARD_MAIN_MENU_ENTRY.key);
	login(TestAccount.DEMO_USER);
	NewDashboardPage newDashboardPage = new NewDashboardPage();
	// Set French
	setUserLanguage(newDashboardPage,2);
	MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
	redirectToRelativeLink(createSampleDashboardUrl);
	Assertions.assertEquals("Dashboard Test FR",mainMenuPage.getMainMenuName());
	
	// Set German
	setUserLanguage(newDashboardPage,3);
	Assertions.assertEquals("Dashboard Test DE",mainMenuPage.getMainMenuName());
	
	// Set English
	setUserLanguage(newDashboardPage,1);
	Assertions.assertEquals("Dashboard Test EN",mainMenuPage.getMainMenuName());
  }
  
  @Test
  public void testBrowserTitleChangeFollowPage() {
    redirectToRelativeLink(cleanupDataLink);
    createJSonFile("application-name.json", Variable.APPLICATION_NAME.getKey());
    login(TestAccount.DEMO_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    assertEquals(DASHBOARD_PAGE_TITLE, newDashboardPage.getPageTitle());

    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    String processesPageTitle = mainMenuPage.openProcessList().getPageTitle();
    String taskListPageTitle = mainMenuPage.openTaskList().getPageTitle();
    String caseListPageTitle = mainMenuPage.openCaseList().getPageTitle();

    assertEquals(PROCESSES_PAGE_TITLE, processesPageTitle);
    assertEquals(TASKS_PAGE_TITLE, taskListPageTitle);
    assertEquals(CASES_PAGE_TITLE, caseListPageTitle);
  }

  
  private void setUserLanguage(NewDashboardPage newDashboardPage, int index) {
	UserProfilePage userProfilePage = newDashboardPage.openMyProfilePage();
	userProfilePage.selectLanguage(index);
    newDashboardPage = userProfilePage.save();
  }
}

package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.Selenide.$;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.enums.PortalPermission;
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
        "Dashboard,Processes,Tasks,Cases,Google,Testing link google,A link,B link,Static page";
    assertEquals(expected, mainMenuPage.getMenuItemsAsString());
  }

  @Test
  public void testAddingDashboardAsTopMenuItem() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();
    createJSonFile("default-dashboard.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    String name = "Dashboard Menu Item";
    String icon = "ti-coffee";
    String description = "Dashboard Menu Item";
    List<String> permissions = new ArrayList<>();
    permissions.add("Everybody");
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromScratch(name, icon, description, permissions, DashboardDisplayType.TOP_MENU);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    configurationPage = newDashboardDetailsEditPage.backToConfigurationPage();
    newDashboardPage = configurationPage.backToHomePage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    mainMenuPage.clickMainMenuItem(name);
    mainMenuPage.assertMainMenuItem(name);
    redirectToNewDashBoard();
    mainMenuPage = new MainMenuPage();
    String expected =
        "Dashboard,Processes,Tasks,Cases," + name + ",Google";
    String menuItemAsString = mainMenuPage.getMenuItemsAsString();
    assertEquals(expected, menuItemAsString);
  }

  @Test
  public void testKeepOpenStateWhenNavigateToAnotherPage() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    NewDashboardPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    assertTrue(taskWidgetPage.isMainMenuOpen());
  }

  @Test
  public void testKeepClosedStateWhenNavigateToAnotherPage() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetNewDashBoardPage dashboardPage = mainMenuPage.openCaseList();
    dashboardPage.waitForPageLoad();

    dashboardPage.closeMainMenu();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
    assertFalse(newDashboardPage.isMainMenuOpen());
  }

  @Test
  public void testNavigateToExternalLink() {
    createThirdPartyApp();
    login(TestAccount.DEMO_USER);
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    mainMenuPage.clickThirdPartyMenuItem();
    mainMenuPage.assertNavigateToExternalLink("https://www.google.com/");
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
    Assertions.assertEquals("ti ti-file-pencil",iconClass);
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

  @Test
  public void keepSidebarExpand() {
    updatePortalSetting(Variable.SIDEBAR_MODE.getKey(), "STICK");
    login(TestAccount.DEMO_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.isSidebarAlwaysExpand();
    mainMenuPage.openProcessList();
    mainMenuPage.isSidebarAlwaysExpand();
  }

  @Test
  public void testSidebarClickModeBasicToggle() {
    updatePortalSetting(Variable.SIDEBAR_MODE.getKey(), "CLICK");
    login(TestAccount.DEMO_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();

    // Verify initial collapsed state
    mainMenuPage.isSidebarClickModeCollapsed();

    // Click toggle button to expand
    mainMenuPage.clickSidebarToggleButton();
    mainMenuPage.isSidebarClickModeExpanded();

    // Click toggle button to collapse
    mainMenuPage.clickSidebarToggleButton();
    mainMenuPage.isSidebarClickModeCollapsed();
  }

  @Test
  public void testSidebarClickModeResetsToCollapsedAfterNavigation() {
    updatePortalSetting(Variable.SIDEBAR_MODE.getKey(), "CLICK");
    login(TestAccount.DEMO_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();

    mainMenuPage.isSidebarClickModeCollapsed();
    mainMenuPage.clickSidebarToggleButton();
    mainMenuPage.isSidebarClickModeExpanded();

    mainMenuPage.openProcessList();
    mainMenuPage.isSidebarClickModeCollapsed();
  }

  @Test
  public void testSidebarHiddenMode() {
    updatePortalSetting(Variable.SIDEBAR_MODE.getKey(), "HIDDEN");
    login(TestAccount.DEMO_USER);
    NewDashboardPage dashboardPage = new NewDashboardPage();

    dashboardPage.isSidebarHidden();
  }

  /**
   * The "Portal configuration" sidebar item must only be rendered for users who have at least one of the
   * dashboard/portal-configuration permissions. login() grants both dashboard write permissions by
   * default, so both are explicitly denied here to reproduce a user without any of them.
   */
  @Test
  public void testPortalConfigurationMenuItemHiddenWithoutPermission() {
    login(TestAccount.DEMO_USER);
    permissions().denyDashboardWriteOwnPermission();
    permissions().denyDashboardWritePublicPermission();
    permissions().denySpecificPortalPermission(PortalPermission.PORTAL_SIDEBAR_CONFIGURATION);
    permissions().denySpecificPortalPermission(PortalPermission.PORTAL_PACKAGE_MANAGEMENT);
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();

    newDashboardPage.getPortalConfigurationMenuItem().shouldNotBe(Condition.exist);
  }

  /**
   * Reverse of {@link #testPortalConfigurationMenuItemHiddenWithoutPermission()}: once the user is granted
   * a dashboard/portal-configuration permission, the sidebar item must appear.
   */
  @Test
  public void testPortalConfigurationMenuItemVisibleWithPermission() {
    login(TestAccount.DEMO_USER);
    permissions().grantDashboardWriteOwnPermission();
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();

    newDashboardPage.getPortalConfigurationMenuItem().shouldBe(Condition.exist).shouldBe(Condition.visible);
  }

  /**
   * The "Portal configuration" item lives in the sidebar-footer, pinned below the scrollable menu list
   * (Dashboard/Processes/Tasks/Cases/...), not inside that scrollable list itself.
   */
  @Test
  public void testPortalConfigurationMenuItemPinnedAtBottomOfSidebar() {
    login(TestAccount.DEMO_USER);
    permissions().grantDashboardWriteOwnPermission();
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();

    newDashboardPage.getSidebarFooter().shouldBe(Condition.visible);
    newDashboardPage.getSidebarFooter().$("#dashboard-configuration-menuitem").shouldBe(Condition.exist)
        .shouldBe(Condition.visible);
    // it must not be one of the regular, scrollable main menu items
    $(".sidebar-scroll-content #dashboard-configuration-menuitem").shouldNotBe(Condition.exist);
  }

  /**
   * The item must follow the same icon-only (collapsed) vs icon+label (expanded) behavior as every other
   * sidebar menu item.
   */
  @Test
  public void testPortalConfigurationMenuItemFollowsSidebarCollapseExpandBehavior() {
    updatePortalSetting(Variable.SIDEBAR_MODE.getKey(), "CLICK");
    login(TestAccount.DEMO_USER);
    permissions().grantDashboardWriteOwnPermission();
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    var regularItemLabel = $(".layout-menu li[role='menuitem'] a.PROCESS_LIST span");

    // anchor: the item must actually be rendered before asserting on its visual state
    mainMenuPage.getPortalConfigurationMenuItem().shouldBe(Condition.exist);

    // Collapsed: icon shown, label hidden - same as a regular sidebar item (e.g. Processes)
    mainMenuPage.isSidebarClickModeCollapsed();
    mainMenuPage.getPortalConfigurationMenuItem().$("i").shouldBe(Condition.visible);
    mainMenuPage.getPortalConfigurationMenuItem().$("a span").shouldNotBe(Condition.visible);
    regularItemLabel.shouldNotBe(Condition.visible);

    // Expanded: icon and label both shown - same as a regular sidebar item
    mainMenuPage.clickSidebarToggleButton();
    mainMenuPage.isSidebarClickModeExpanded();
    mainMenuPage.getPortalConfigurationMenuItem().$("i").shouldBe(Condition.visible);
    mainMenuPage.getPortalConfigurationMenuItem().$("a span").shouldBe(Condition.visible);
    regularItemLabel.shouldBe(Condition.visible);
  }

  /**
   * Clicking the sidebar "Portal configuration" item navigates to the Portal Configuration page.
   */
  @Test
  public void testClickPortalConfigurationMenuItemNavigatesToConfigurationPage() {
    login(TestAccount.DEMO_USER);
    permissions().grantDashboardWriteOwnPermission();
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();

    configurationPage.getDashboardConfigurationPage().shouldBe(Condition.visible);
    assertTrue(newDashboardPage.getDriver().getCurrentUrl().contains("PortalDashboardConfiguration"));
  }

  private void setUserLanguage(NewDashboardPage newDashboardPage, int index) {
	UserProfilePage userProfilePage = newDashboardPage.openMyProfilePage();
	userProfilePage.selectLanguage(index);
    newDashboardPage = userProfilePage.save();
  }

}

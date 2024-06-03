package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.UserProfilePage;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class MenuTest extends BaseTest {

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
    String expected = "Dashboard,Processes,Tasks,Cases,Statistics,User example guide,Google,Testing link google,Testing example,A link,B link";
    assertEquals(expected, mainMenuPage.getMenuItemsAsString());
  }

  @Test
  public void testCustomizeIconMainMenuEntry() {
    redirectToRelativeLink(cleanupDataLink);
    createJSonFile("custom-main-menu-entry.json", PortalVariable.DASHBOARD_MAIN_MENU_ENTRY.key);
    login(TestAccount.DEMO_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    redirectToRelativeLink(createSampleDashboardUrl);
    String iconClass = mainMenuPage.getIconClassMainMenuEntryAsString();
    Assertions.assertEquals("si si-layout-bullets", iconClass);
  }

  @Test
  public void testCustomizeNameMainMenuEntry() {
    redirectToRelativeLink(cleanupDataLink);
    createJSonFile("custom-main-menu-entry.json", PortalVariable.DASHBOARD_MAIN_MENU_ENTRY.key);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    redirectToRelativeLink(createSampleDashboardUrl);
    String menuName = mainMenuPage.getMainMenuName();
    Assertions.assertEquals("Dashboard Test EN", menuName);
  }

  @Test
  public void testCustomizeMainMenuEntryMultiLanguage() {
    redirectToRelativeLink(cleanupDataLink);
    createJSonFile("custom-main-menu-entry.json", PortalVariable.DASHBOARD_MAIN_MENU_ENTRY.key);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    // Set French
    setUserLanguage(newDashboardPage, 2);
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    redirectToRelativeLink(createSampleDashboardUrl);
    Assertions.assertEquals("Dashboard Test FR", mainMenuPage.getMainMenuName());

    // Set German
    setUserLanguage(newDashboardPage, 3);
    Assertions.assertEquals("Dashboard Test DE", mainMenuPage.getMainMenuName());

    // Set English
    setUserLanguage(newDashboardPage, 1);
    Assertions.assertEquals("Dashboard Test EN", mainMenuPage.getMainMenuName());
  }

  @Test
  public void testBrowserTitleChangeFollowPage() {
    redirectToRelativeLink(cleanupDataLink);
    createJSonFile("application-name.json", Variable.APPLICATION_NAME.getKey());
    login(TestAccount.DEMO_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    assertEquals("Dashboard - Portal - Axon Ivy", newDashboardPage.getPageTitle());

    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    String processesPageTitle = mainMenuPage.openProcessList().getPageTitle();
    String taskListPageTitle = mainMenuPage.openTaskList().getPageTitle();
    String caseListPageTitle = mainMenuPage.openCaseList().getPageTitle();

    assertEquals("Processes - Portal - Axon Ivy", processesPageTitle);
    assertEquals("Tasks - Portal - Axon Ivy", taskListPageTitle);
    assertEquals("Cases - Portal - Axon Ivy", caseListPageTitle);
  }

  private void setUserLanguage(NewDashboardPage newDashboardPage, int index) {
    UserProfilePage userProfilePage = newDashboardPage.openMyProfilePage();
    userProfilePage.selectLanguage(index);
    userProfilePage.save();
  }
}
package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

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
}
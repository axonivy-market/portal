package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.PortalConfigurationPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest (headless = false)
public class MenuManagementTest extends BaseTest {

  private static final String STANDARD_MENU_DASHBOARD = "Dashboard";
  private static final String STANDARD_MENU_PROCESSES = "Processes";

  private static final String TYPE_STANDARD = "Standard";
  private static final String TYPE_EXTERNAL_LINK = "External Link";

  private static final String EXPECTED_PAGE_TITLE = "Portal configuration";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(grantDashboardWritePublicPermissionUrl);
    redirectToRelativeLink(grantDashboardWriteOwnPermissionUrl);
  }

  @Test
  public void testPageTitleIsPortalConfiguration() {
    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    String headingText = page.getPageHeadingText();
    assertEquals(EXPECTED_PAGE_TITLE, headingText);
  }

  @Test
  public void testThreeTabsAreVisible() {
    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.getPrivateDashboardTab().shouldBe(appear, DEFAULT_TIMEOUT);
    page.getPublicDashboardTab().shouldBe(appear, DEFAULT_TIMEOUT);
    page.getMenuManagementTab().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testSwitchToPrivateDashboardTab() {
    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectPrivateDashboardTab();
    $(".dashboard-configuration__content.js-private-dashboard-configuration").shouldBe(visible, DEFAULT_TIMEOUT);
    $("[id$='menu-table']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testSwitchToPublicDashboardTab() {
    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectPublicDashboardTab();
    $(".dashboard-configuration__content.js-public-dashboard-configuration").shouldBe(visible, DEFAULT_TIMEOUT);
    $("[id$='menu-table']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testSwitchToMenuManagementTab() {
    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectMenuManagementTab();
    $("[id$='menu-table']").shouldBe(visible, DEFAULT_TIMEOUT);
    $("button[id$='create-menu-action']").shouldBe(visible, DEFAULT_TIMEOUT);
    $(".js-private-dashboard-configuration").shouldBe(disappear, DEFAULT_TIMEOUT);
    $(".js-public-dashboard-configuration").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testMenuTableContainsDefaultEntries() {
    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectMenuManagementTab();

    page.getMenuTableRows().shouldHave(sizeGreaterThan(0));
    page.findMenuRowByTitle(STANDARD_MENU_DASHBOARD).shouldBe(appear, DEFAULT_TIMEOUT);
    page.findMenuRowByTitle(STANDARD_MENU_PROCESSES).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testTypeColumnShowsStandardForBuiltInMenuItems() {
    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectMenuManagementTab();

    assertEquals(TYPE_STANDARD, page.getMenuTypeByTitle(STANDARD_MENU_DASHBOARD));
    assertEquals(TYPE_STANDARD, page.getMenuTypeByTitle(STANDARD_MENU_PROCESSES));
  }

  @Test
  public void testStandardMenuItemDashboardHasNoDeleteAction() {
    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectMenuManagementTab();

    boolean deleteAvailable = page.isDeleteActionAvailableForMenuTitle(STANDARD_MENU_DASHBOARD);
    assertFalse(deleteAvailable, "STANDARD 'Dashboard' menu item must not expose a Delete action");
  }

  @Test
  public void testStandardMenuItemProcessesHasNoDeleteAction() {
    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectMenuManagementTab();

    boolean deleteAvailable = page.isDeleteActionAvailableForMenuTitle(STANDARD_MENU_PROCESSES);
    assertFalse(deleteAvailable, "STANDARD 'Processes' menu item must not expose a Delete action");
  }

  @Test
  public void testCreateExternalLinkMenu() {
    String menuTitle = "Test External Link";
    String externalUrl = "https://www.example.com";

    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectMenuManagementTab();

    page.clickAddNewMenu();
    page.selectMenuType(TYPE_EXTERNAL_LINK);
    page.setExternalLink(externalUrl);
    page.setMenuTitle(menuTitle);
    page.submitCreateMenu();

    page.selectMenuManagementTab();
    SelenideElement newRow = page.findMenuRowByTitle(menuTitle);
    newRow.shouldBe(appear, DEFAULT_TIMEOUT);
    newRow.$("td:nth-child(3)").shouldHave(text(TYPE_EXTERNAL_LINK));
  }

  @Test
  public void testEditExternalLinkMenuTitle() {
    String originalTitle = "Editable Link Menu";
    String updatedTitle = "Updated Link Menu";
    String externalUrl = "https://edit.example.com";

    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectMenuManagementTab();
    page.clickAddNewMenu();
    page.selectMenuType(TYPE_EXTERNAL_LINK);
    page.setExternalLink(externalUrl);
    page.setMenuTitle(originalTitle);
    page.submitCreateMenu();

    page.selectMenuManagementTab();
    page.clickEditMenuByTitle(originalTitle);
    page.setMenuTitle(updatedTitle);
    page.submitUpdateMenu();

    page.selectMenuManagementTab();
    page.findMenuRowByTitle(updatedTitle).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testDeleteExternalLinkMenu() {
    String menuTitle = "Menu To Delete";
    String externalUrl = "https://delete.example.com";

    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectMenuManagementTab();
    page.clickAddNewMenu();
    page.selectMenuType(TYPE_EXTERNAL_LINK);
    page.setExternalLink(externalUrl);
    page.setMenuTitle(menuTitle);
    page.submitCreateMenu();

    page.selectMenuManagementTab();
    page.clickDeleteMenuByTitle(menuTitle);
    $("[id$='remove-menu-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    page.confirmRemoveMenu();

    page.selectMenuManagementTab();
    page.getMenuTableRows().asFixedIterable()
        .forEach(row -> row.$("td:first-child").shouldNotHave(Condition.text(menuTitle)));
  }

  @Test
  public void testCancelDeleteMenuKeepsItem() {
    String menuTitle = "Menu Cancel Delete";
    String externalUrl = "https://cancel-delete.example.com";

    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectMenuManagementTab();
    page.clickAddNewMenu();
    page.selectMenuType(TYPE_EXTERNAL_LINK);
    page.setExternalLink(externalUrl);
    page.setMenuTitle(menuTitle);
    page.submitCreateMenu();

    page.selectMenuManagementTab();
    page.clickDeleteMenuByTitle(menuTitle);
    $("[id$='remove-menu-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    page.cancelRemoveMenu();

    page.findMenuRowByTitle(menuTitle).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testAddNewMenuDialogOpens() {
    PortalConfigurationPage page = LinkNavigator.navigateToPortalConfiguration();
    page.selectMenuManagementTab();

    SelenideElement dialog = page.clickAddNewMenu();
    dialog.shouldBe(appear, DEFAULT_TIMEOUT);
    dialog.$("[id$='menu-configuration-form:menu-type']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id$='create-button']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}

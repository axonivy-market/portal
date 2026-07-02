package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class PortalConfigurationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#configuration-group";
  }

  private void waitForTabsAppear() {
    $("[id$='dashboard-configuration-type']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getPrivateDashboardTab() {
    waitForTabsAppear();
    return $("a[id$='private-dashboard-type']");
  }

  public SelenideElement getPublicDashboardTab() {
    waitForTabsAppear();
    return $("a[id$='public-dashboard-type']");
  }

  public SelenideElement getMenuManagementTab() {
    waitForTabsAppear();
    return $("a[id$='sidebar-navigation-type']");
  }

  public void selectPrivateDashboardTab() {
    waitForTabsAppear();
    $("a[id$='private-dashboard-type']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $(".dashboard-configuration__content.js-private-dashboard-configuration").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectPublicDashboardTab() {
    waitForTabsAppear();
    $("a[id$='public-dashboard-type']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $(".dashboard-configuration__content.js-public-dashboard-configuration").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectMenuManagementTab() {
    waitForTabsAppear();
    $("a[id$='sidebar-navigation-type']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$='sidebar-navigation-content']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id$='menu-table']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectMenuManagementTabWhenSidebarMayBeDisabled() {
    waitForTabsAppear();
    $("a[id$='sidebar-navigation-type']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$='sidebar-navigation-content']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String getPageHeadingText() {
    return $("div[id$='configuration-group'] .dashboard-configuration__header h2")
        .shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public SelenideElement getMenuTable() {
    return $("[id$='menu-table']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getMenuTableRows() {
    return $("[id$='menu-table_data']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("tr.ui-widget-content");
  }

  public SelenideElement findMenuRowByTitle(String menuTitle) {
    // Lazy filter: Selenide re-queries on every interaction, so the returned element
    // survives AJAX re-renders of the menu table (which would otherwise produce
    // StaleElementReferenceException after the create/update dialog closes).
    return getMenuTableRows().filterBy(text(menuTitle)).first().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String getMenuTypeByTitle(String menuTitle) {
    // Table columns: 1-Label, 2-Target, 3-Permissions, 4-Type, 5-Source, 6-Action, 7-Reorder
    return findMenuRowByTitle(menuTitle).$("td:nth-child(4)").getText();
  }

  public SelenideElement clickAddNewMenu() {
    $("button[id$='add-menu-item-action']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return waitForMenuConfigurationDialogVisible();
  }

  public SelenideElement clickEditMenuByTitle(String menuTitle) {
    SelenideElement overlayMenu = openActionMenuForRow(findMenuRowByTitle(menuTitle));
    overlayMenu.$("[id$='menu-edit']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return waitForMenuConfigurationDialogVisible();
  }

  public SelenideElement clickDeleteMenuByTitle(String menuTitle) {
    SelenideElement overlayMenu = openActionMenuForRow(findMenuRowByTitle(menuTitle));
    overlayMenu.$("[id$='menu-delete']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return waitForRemoveMenuDialogVisible();
  }

  public boolean isDeleteActionAvailableForMenuTitle(String menuTitle) {
    if (!isActionButtonRenderedForMenuTitle(menuTitle)) {
      return false;
    }
    SelenideElement overlayMenu = openActionMenuForRow(findMenuRowByTitle(menuTitle));
    boolean visible = overlayMenu.$$("[id$='menu-delete']").filter(Condition.visible).size() > 0;
    $("body").click();
    return visible;
  }

  public boolean isActionButtonRenderedForMenuTitle(String menuTitle) {
    SelenideElement row = findMenuRowByTitle(menuTitle);
    return row.$$("button[id$='menu-action-button']").filter(Condition.visible).size() > 0;
  }

  public void confirmRemoveMenu() {
    $("button[id$='remove-menu-button']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("[id$='remove-menu-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
    $("[id$='menu-table-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void cancelRemoveMenu() {
    $("[id$='remove-menu-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("a").filter(Condition.text("Cancel")).first()
        .shouldBe(getClickableCondition()).click();
    $("[id$='remove-menu-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement waitForMenuConfigurationDialogVisible() {
    SelenideElement dialog = $("[id$='menu-configuration-dialog']");
    dialog.shouldBe(appear, DEFAULT_TIMEOUT);
    return dialog;
  }

  public void selectMenuType(String menuKindLabel) {
    $("[id$='menu-configuration-form:menu-type']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("[id$='menu-type_panel']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("li").filter(Condition.text(menuKindLabel)).first()
        .shouldBe(getClickableCondition()).click();
    $("[id$='menu-configuration-form']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void setExternalLink(String url) {
    SelenideElement input = $("[id$='menu-configuration-form:external-link']").shouldBe(appear, DEFAULT_TIMEOUT);
    input.clear();
    input.sendKeys(url);
  }

  public void setMenuTitle(String title) {
    SelenideElement input = $("[id$='menu-configuration-form:menu-titles']").shouldBe(appear, DEFAULT_TIMEOUT);
    input.clear();
    input.sendKeys(title);
  }

  public void submitCreateMenu() {
    $("[id$='create-button']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$='menu-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
    $("[id$='menu-table-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void submitCreateMenuExpectingValidationError() {
    $("[id$='create-button']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$='menu-configuration-messages']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void submitUpdateMenu() {
    $("[id$='update-button']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$='menu-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
    $("[id$='menu-table-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String getMenuConfigurationDialogHeader() {
    return $("[id$='menu-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$(".ui-dialog-title").getText();
  }

  public SelenideElement getSidebarSettingsCard() {
    return $(".sidebar-settings-card").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getDisableSidebarSwitch() {
    return $("[id$='disable-sidebar-switch']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getSidebarBehaviourPanel() {
    return $("[id$='sidebar-behaviour-panel']");
  }

  public void clickDisableSidebarSwitch() {
    $("[id$='disable-sidebar-switch']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("[id$='sidebar-settings-form']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void ensureSidebarEnabled() {
    if (getSidebarBehaviourPanel().is(disappear)) {
      clickDisableSidebarSwitch();
      getSidebarBehaviourPanel().shouldBe(appear, DEFAULT_TIMEOUT);
    }
  }

  public void ensureSidebarDisabled() {
    if (getSidebarBehaviourPanel().is(appear)) {
      clickDisableSidebarSwitch();
      getSidebarBehaviourPanel().shouldBe(disappear, DEFAULT_TIMEOUT);
    }
  }

  public SelenideElement getSidebarReloadWarningBanner() {
    return $("[id$='sidebar-settings-form'] .p-message-warn");
  }

  public SelenideElement getSidebarReloadButton() {
    return $("[id$='sidebar-reload-button']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getMenuConfigValidationMessages() {
    return $("[id$='menu-configuration-messages']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openSidebarBehaviourDropdown() {
    $("[id$='sidebar-mode-select']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("[id$='sidebar-mode-select_items']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getSidebarBehaviourDropdownItems() {
    return $("[id$='sidebar-mode-select_items']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getMenuTableHeaderRow() {
    return $("[id$='menu-table'] thead tr").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getTopMenuDashboardTable() {
    return $("[id$='top-menu-dashboard-table']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getSubmenuDashboardTable() {
    return $("[id$=':dashboard-table']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getHiddenDashboardTable() {
    return $("[id$='hidden-dashboard-table']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getInfoPanelButton() {
    return $("[id$='dashboard-info-icon']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void reorderMenu(String fromMenuTitle, String toMenuTitle) {
    SelenideElement fromHandle = findMenuRowByTitle(fromMenuTitle).$("i.ti-menu-2");
    SelenideElement toHandle = findMenuRowByTitle(toMenuTitle).$("i.ti-menu-2");
    Actions builder = new Actions(WebDriverRunner.getWebDriver());
    Action dragAndDrop = builder.clickAndHold(fromHandle).pause(500)
        .moveToElement(toHandle, 50, 20).pause(500).release(toHandle)
        .pause(500)
        .build();
    dragAndDrop.perform();
  }

  private SelenideElement waitForRemoveMenuDialogVisible() {
    SelenideElement dialog = $("[id$='remove-menu-dialog']");
    dialog.shouldBe(appear, DEFAULT_TIMEOUT);
    return dialog;
  }

  private SelenideElement openActionMenuForRow(SelenideElement row) {
    row.$("button[id$='menu-action-button']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    return $$("[id$='menu-configuration-action-menu']").filter(Condition.appear).first()
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }
}

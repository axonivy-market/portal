package com.axonivy.portal.selenium.page;

import static com.axonivy.portal.selenium.common.Variable.CLIENT_SIDE_TIMEOUT;
import static com.axonivy.portal.selenium.common.Variable.GLOBAL_FOOTER_INFO;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.Sleeper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class AdminSettingsPage extends TemplatePage {

  private static final String PORTAL_MANAGEMENT_PAGE_ID = "portal-management-container";

  public AdminSettingsPage() {
    waitForElementDisplayed(By.id(PORTAL_MANAGEMENT_PAGE_ID), true);
  }

  @Override
  protected String getLoadedLocator() {
    return "[id='" + PORTAL_MANAGEMENT_PAGE_ID + "']";
  }

  public boolean isDisplayed() {
    return $(By.id(PORTAL_MANAGEMENT_PAGE_ID)).isDisplayed();
  }

  public void openSettingTab() {
    waitForElementClickableThenClick("a[href$='#admin-setting-component:adminTabView:setting-tab']");
    waitForElementDisplayed(By.cssSelector("[id$=':adminTabView:settingForm']"), true);
    waitForAjaxIndicatorDisplayNone();
  }

  private void editGlobalVariable(String variableName, String variableValue, boolean isBooleanType) {
    $("button[id$='admin-setting-component:adminTabView:restore-all-to-default-button']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition());
    waitForElementDisplayed(By.id("admin-setting-component:adminTabView:settingTable"), true);

    var variableRow = $("[id='admin-setting-component:adminTabView:settingTable']").$$(".setting-key")
        .filter(Condition.text(variableName)).get(0);

    if (variableRow != null) {
      SelenideElement editButton = variableRow.ancestor("tr").$(By.cssSelector("a[id$=edit]"));
      editButton.shouldBe(clickable(), DEFAULT_TIMEOUT).click();
    }
    waitForElementDisplayed(By.cssSelector("[id$=':settingDialogForm']"), true);
    saveGlobalVariable(variableValue, isBooleanType);
  }

  public void resetAllSettings() {
    openSettingTab();
    waitForElementClickableThenClick("[id='admin-setting-component:adminTabView:restore-all-to-default-button']");
    waitForElementClickableThenClick("[id='admin-setting-component:reset-settings']");
    closeConfirmationDialog();
  }

  private void saveGlobalVariable(String value, boolean isBooleanType) {
    if (!isBooleanType) {
      WebElement valueInput = $("[id='admin-setting-component:valueSetting']");
      valueInput.clear();
      valueInput.sendKeys(value);
    } else {
      waitForElementClickableThenClick("[id='admin-setting-component:valueSetting_label']");
      waitForElementDisplayed(By.id("admin-setting-component:valueSetting_panel"), true);
      boolean boolValue = Boolean.parseBoolean(value);
      int index = boolValue ? 1 : 0;
      $(By.id(String.format("admin-setting-component:valueSetting_%d", index)))
          .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }
    waitForElementClickableThenClick("[id='admin-setting-component:save-setting']");
  }

  public void clickOnbackToNewDashboardPageOnAdminSetting() {
    WaitHelper.waitForNavigation(() -> waitForElementClickableThenClick(findElementById("back-to-home-button")));
  }

  public void setClientSideTimeout(String timeout) {
    openSettingTab();
    editGlobalVariable(CLIENT_SIDE_TIMEOUT.getKey(), timeout, false);
    closeConfirmationDialog();
  }

  public void closeConfirmationDialog() {
    clickOnbackToNewDashboardPageOnAdminSetting();
  }

  public void setGlobalFooterInfo() {
    openSettingTab();
    editGlobalVariable(GLOBAL_FOOTER_INFO.getKey(), "Dev Team: Wawa, Env: Dev", false);
    closeConfirmationDialog();
  }

  public boolean isWarningDialogShowWhenTimeoutIsLosing() {
    waitForElementDisplayed(By.cssSelector("div[id$=':timeout-warning-dialog']"), true, 121);
    return $(By.cssSelector("div[id$=':timeout-warning-dialog']")).shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .isDisplayed();
  }

  public boolean isInformDialogShowAfterTimeout() {
    waitForElementDisplayed(By.id("warning-before-lost-session:timeout-dialog"), true, 181);
    return $(By.id("warning-before-lost-session:timeout-dialog")).shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .isDisplayed();
  }

  public AnnouncementPage openAnnouncementTab() {
    waitForElementClickable($(By.xpath("//a[@href='#admin-setting-component:adminTabView:announcement-tab']"))).click();
    waitForElementDisplayed(By.id("admin-setting-component:adminTabView:announcement-tab"), true);
    return new AnnouncementPage();
  }

  public ExpressManagementPage openExpressManagementTab() {
    waitForElementClickableThenClick(
        $(By.xpath(("//a[@href='#admin-setting-component:adminTabView:express-management-tab']"))));
    waitForElementPresent(By.id("admin-setting-component:adminTabView:express-management-tab"), true);
    return new ExpressManagementPage();
  }

  public RoleManagementPage openRoleManagementTab() {
    waitForElementDisplayed(By.cssSelector("[id$='admin-setting-component:adminTabView']"), true);
    waitForElementClickableThenClick("a[href='#admin-setting-component:adminTabView:role-management-tab']");
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-form:role-tree-table']"),
        true);
    return new RoleManagementPage();
  }

  public boolean isRoleAssingmentTabViewPresent() {
    return isElementPresent(By.cssSelector("a[href='#admin-setting-component:adminTabView:role-management-tab']"));
  }

  public boolean isPasswordValidationTabViewPresent() {
    return isElementPresent(By.cssSelector("a[href='#admin-setting-component:adminTabView:password-validation-tab']"));
  }

  public PasswordValidationPage openPasswordValidationTab() {
    waitForElementDisplayed(By.cssSelector("[id$='admin-setting-component:adminTabView']"), true);
    waitForElementClickableThenClick($("a[href='#admin-setting-component:adminTabView:password-validation-tab']"));
    waitForElementDisplayed(
        By.cssSelector("[id$=':password-validation-component:password-validation-form:password-policy-setting']"),
        true);
    return new PasswordValidationPage();
  }

  public WebElement getAdminSettingContainer() {
    return findElementById("admin-settings-container");
  }

  public WebElement getAddApplicationDialog() {
    waitForElementClickableThenClick($(By.id("admin-setting-component:adminTabView:add-application-btn")));
    waitForElementDisplayed(By.id("admin-setting-component:appDialog"), true);
    Sleeper.sleep(300);// Wait a bit focus effects, just only use this for capture screenshot
    return findElementById("admin-setting-component:appDialog");
  }

  public WebElement getEditSettingDialogOfFirstRow() {
    waitForElementClickableThenClick($(By.id("admin-setting-component:adminTabView:settingTable:0:edit")));
    waitForElementDisplayed(By.id("admin-setting-component:settingDialog"), true);
    Sleeper.sleep(300);// Wait a bit focus effects, just only use this for capture screenshot
    return findElementById("admin-setting-component:settingDialog");
  }

  public void closeAddApplicationDialog() {
    $("[id='admin-setting-component:appDialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("a.ui-dialog-titlebar-close").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='admin-setting-component:appDialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void closeEditSettingDialog() {
    $("[id='admin-setting-component:settingDialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("a.ui-dialog-titlebar-close").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='admin-setting-component:settingDialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
}


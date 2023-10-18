package com.axonivy.portal.selenium.page;


import static com.axonivy.portal.selenium.common.Variable.CLIENT_SIDE_TIMEOUT;
import static com.axonivy.portal.selenium.common.Variable.GLOBAL_FOOTER_INFO;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.Sleeper;
import com.axonivy.portal.selenium.common.WaitHelper;

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
    waitAjaxIndicatorDisappear();
  }

  private void editGlobalVariable(String variableName, String variableValue, boolean isBooleanType) {
    List<WebElement> tableRows = getAdminTable().findElements(By.tagName("tr"));
    for (WebElement row : tableRows) {
      List<WebElement> columns = row.findElements(By.tagName("td"));
      if (!CollectionUtils.isEmpty(columns)) {
        WebElement keyColumn = columns.get(0);
        if (keyColumn.getText().equals(variableName)) {
          WebElement editButton = row.findElement(By.cssSelector("a[id$=edit]"));
          editButton.click();
          waitForElementDisplayed(By.cssSelector("[id$=':settingDialogForm']"), true);
          saveGlobalVariable(variableValue, isBooleanType);
          return;
        }
      }
    }
  }

  private WebElement getAdminTable() {
    return $("[id$=':adminTabView:settingTable']");
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
      $(By.id(String.format("admin-setting-component:valueSetting_%d", index))).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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
    return isElementDisplayed(By.cssSelector("div[id$=':timeout-warning-dialog']"));
  }

  public boolean isInformDialogShowAfterTimeout() {
    waitForElementDisplayed(By.id("warning-before-lost-session:timeout-dialog"), true, 181);
    return isElementDisplayed(By.id("warning-before-lost-session:timeout-dialog"));
  }

  public AnnouncementPage openAnnouncementTab() {
    waitForElementClickable($(By.xpath("//a[@href='#admin-setting-component:adminTabView:announcement-tab']"))).click();
    waitForElementDisplayed(By.id("admin-setting-component:adminTabView:announcement-tab"), true);
    waitAjaxIndicatorDisappear();
    return new AnnouncementPage();
  }

//  @SuppressWarnings("deprecation")
//  public ExpressManagementPage openExpressManagementTab() {
//    WebElement settingTabLink = findElementByXpath("//a[@href='#admin-setting-component:adminTabView:express-management-tab']");
//    click(settingTabLink);
//    waitForElementPresent(By.id("admin-setting-component:adminTabView:express-management-tab"), true);
//    waitAjaxIndicatorDisappear();
//    return new ExpressManagementPage();
//  }

  public RoleManagementPage openRoleManagementTab() {
    waitForElementDisplayed(By.cssSelector("[id$='admin-setting-component:adminTabView']"), true);
    waitForElementClickableThenClick("a[href='#admin-setting-component:adminTabView:role-management-tab']");
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-form:role-tree-table']"), true);
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
    waitForElementDisplayed(By.cssSelector("[id$=':password-validation-component:password-validation-form:password-policy-setting']"), true);
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
    Sleeper.sleep(300);//Wait a bit focus effects, just only use this for capture screenshot
    return findElementById("admin-setting-component:settingDialog");
  }
}
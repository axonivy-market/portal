package portal.guitest.page;

import static portal.guitest.common.Variable.CLIENT_SIDE_TIMEOUT;
import static portal.guitest.common.Variable.SHOW_ENVIRONMENT_INFO;
import static portal.guitest.common.Variable.SHOW_LEGACY_UI;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.Sleeper;

public class AdminSettingsPage extends TemplatePage {

  private static final String PORTAL_MANAGEMENT_PAGE_ID = "portal-management-container";

  public AdminSettingsPage() {
    waitForElementDisplayed(By.id(PORTAL_MANAGEMENT_PAGE_ID), true);
  }

  @Override
  protected String getLoadedLocator() {
    return "id('" + PORTAL_MANAGEMENT_PAGE_ID + "')";
  }

  @Override
  public boolean isDisplayed() {
    return findElementById(PORTAL_MANAGEMENT_PAGE_ID).isDisplayed();
  }

  @SuppressWarnings("deprecation")
  public void openSettingTab() {
    WebElement settingTabLink = findElementByCssSelector("a[href$='#admin-setting-component:adminTabView:setting-tab']");
    click(settingTabLink);
    waitForElementPresent(By.cssSelector("[id$=':adminTabView:settingForm']"), true);
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
          click(editButton);
          waitForElementPresent(By.cssSelector("[id$=':settingDialogForm']"), true);
          saveGlobalVariable(variableValue, isBooleanType);
          return;
        }
      }
    }
  }

  private void resetGlobalVariable(String variableName) {
    List<WebElement> tableRows = getAdminTable().findElements(By.tagName("tr"));
    for (WebElement row : tableRows) {
      List<WebElement> columns = row.findElements(By.tagName("td"));
      if (!CollectionUtils.isEmpty(columns)) {
        WebElement keyColumn = columns.get(0);
        if (keyColumn.getText().equals(variableName)) {
          WebElement editButton = row.findElement(By.cssSelector("a[id$=reset]"));
          click(editButton);
          waitForElementPresent(By.cssSelector("[id$=':resetConfirmationDialog']"), true);
          waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
          WebElement restoreButton = findElementById("admin-setting-component:reset-setting");
          click(restoreButton);
          return;
        }
      }
    }
  }

  private WebElement getAdminTable() {
    return findElementByCssSelector("[id$=':adminTabView:settingTable']");
  }

  public void resetAllSettings() {
    openSettingTab();
    WebElement restoreAllToDefaultButton = findElementById("admin-setting-component:adminTabView:restore-all-to-default-button");
    click(restoreAllToDefaultButton);
    waitForElementPresent(By.id("admin-setting-component:reset-settings"), true);
    WebElement restoreButton = findElementById("admin-setting-component:reset-settings");
    click(restoreButton);
    closeConfirmationDialog();
  }

  private void saveGlobalVariable(String value, boolean isBooleanType) {
    if (!isBooleanType) {
      WebElement valueInput = findElementById("admin-setting-component:valueSetting");
      valueInput.clear();
      valueInput.sendKeys(value);
    } else {
      click(By.id("admin-setting-component:valueSetting_label"));
      waitForElementDisplayed(By.id("admin-setting-component:valueSetting_panel"), true);
      boolean boolValue = Boolean.parseBoolean(value);
      int index = boolValue ? 1 : 0;
      click(By.id(String.format("admin-setting-component:valueSetting_%d", index)));
    }
    WebElement saveButton = findElementById("admin-setting-component:save-setting");
    click(saveButton);
  }

  public void clickOnbackToHomepageOnAdminSetting() {
    WebElement closeButton = findElementById("back-to-home-button");
    click(closeButton);
  }

  public void setClientSideTimeout(String timeout) {
    openSettingTab();
    editGlobalVariable(CLIENT_SIDE_TIMEOUT.getKey(), timeout, false);
    closeConfirmationDialog();
  }

  public void closeConfirmationDialog() {
    clickOnbackToHomepageOnAdminSetting();
  }

  public void setEnviromentInfo() {
    openSettingTab();
    editGlobalVariable(SHOW_ENVIRONMENT_INFO.getKey(), "true", true);
    closeConfirmationDialog();
  }

  public void setShowLegacyUI() {
    openSettingTab();
    editGlobalVariable(SHOW_LEGACY_UI.getKey(), "true", true);
    closeConfirmationDialog();
  }

  public void resetShowLegacyUI() {
    openSettingTab();
    resetGlobalVariable(SHOW_LEGACY_UI.getKey());
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

  @SuppressWarnings("deprecation")
  public AnnouncementPage openAnnouncementTab() {
    WebElement settingTabLink = findElementByXpath("//a[@href='#admin-setting-component:adminTabView:announcement-tab']");
    click(settingTabLink);
    waitForElementPresent(By.id("admin-setting-component:adminTabView:announcement-tab"), true);
    waitAjaxIndicatorDisappear();
    return new AnnouncementPage();
  }

  @SuppressWarnings("deprecation")
  public ExpressManagementPage openExpressManagementTab() {
    WebElement settingTabLink = findElementByXpath("//a[@href='#admin-setting-component:adminTabView:express-management-tab']");
    click(settingTabLink);
    waitForElementPresent(By.id("admin-setting-component:adminTabView:express-management-tab"), true);
    waitAjaxIndicatorDisappear();
    return new ExpressManagementPage();
  }

  public RoleManagementPage openRoleManagementTab() {
    waitForElementDisplayed(By.cssSelector("[id$='admin-setting-component:adminTabView']"), true);
    clickByCssSelector("a[href='#admin-setting-component:adminTabView:role-management-tab']");
    waitForElementPresent(By.cssSelector("[id$=':role-management-component:role-management-form:role-tree-table']"), true);
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
    clickByCssSelector("a[href='#admin-setting-component:adminTabView:password-validation-tab']");
    waitForElementPresent(By.cssSelector("[id$=':password-validation-component:password-validation-form:password-policy-setting']"), true);
    return new PasswordValidationPage();
  }
  
  public WebElement getAdminSettingContainer() {
    return findElementById("admin-settings-container");
  }
  
  public WebElement getAddApplicationDialog( ) {
    click(By.id("admin-setting-component:adminTabView:add-application-btn"));
    waitForElementDisplayed(By.id("admin-setting-component:appDialog"), true);
    Sleeper.sleep(300);//Wait a bit focus effects, just only use this for capture screenshot
    return findElementById("admin-setting-component:appDialog");
  }
  
  public WebElement getEditSettingDialogOfFirstRow() {
    click(By.id("admin-setting-component:adminTabView:settingTable:0:edit"));
    waitForElementDisplayed(By.id("admin-setting-component:settingDialog"), true);
    Sleeper.sleep(300);//Wait a bit focus effects, just only use this for capture screenshot
    return findElementById("admin-setting-component:settingDialog");
  }
}
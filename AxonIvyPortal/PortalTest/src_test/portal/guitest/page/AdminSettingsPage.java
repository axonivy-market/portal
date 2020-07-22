package portal.guitest.page;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.Sleeper;

public class AdminSettingsPage extends TemplatePage {

  private static final String DIALOG_TITLE = "admin-ui-dialog_title";

  public AdminSettingsPage() {
    waitForElementDisplayed(By.id(DIALOG_TITLE), true);
  }

  @Override
  protected String getLoadedLocator() {
    return "id('" + DIALOG_TITLE + "')";
  }

  @Override
  public boolean isDisplayed() {
    return findElementById(DIALOG_TITLE).isDisplayed();
  }

  public void openSettingTab() {
    WebElement settingTabLink = findElementByXpath("//a[@href='#adminui:adminTabView:settingTab']");
    click(settingTabLink);
    waitForElementPresent(By.id("adminui:adminTabView:settingForm"), true);
    waitAjaxIndicatorDisappear();
  }

  private void editGlobalVariable(String variableName, String variableValue, boolean isBooleanType) {
    WebElement table = findElementById("adminui:adminTabView:settingTable");
    List<WebElement> tableRows = table.findElements(By.tagName("tr"));
    for (WebElement row : tableRows) {
      List<WebElement> columns = row.findElements(By.tagName("td"));
      if (!CollectionUtils.isEmpty(columns)) {
        WebElement keyColumn = columns.get(0);
        if (keyColumn.getText().equals(variableName)) {
          WebElement editButton = row.findElement(By.cssSelector("a[id$=edit]"));
          click(editButton);
          waitForElementPresent(By.id("adminui:settingDialogForm"), true);
          saveGlobalVariable(variableValue, isBooleanType);
          return;
        }
      }
    }
  }

  private void saveGlobalVariable(String value, boolean isBooleanType) {
    if (!isBooleanType) {
      WebElement valueInput = findElementById("adminui:valueSetting");
      valueInput.clear();
      valueInput.sendKeys(value);
    } else {
      click(By.id("adminui:valueSetting_label"));
      waitForElementDisplayed(By.id("adminui:valueSetting_panel"), true);
      boolean boolValue = Boolean.parseBoolean(value);
      int index = boolValue ? 1 : 0;
      click(By.id(String.format("adminui:valueSetting_%d", index)));
    }
    WebElement saveButton = findElementById("adminui:save-setting");
    click(saveButton);
  }

  public void closeAdminSettingDialog() {
    WebElement closeButton = findElementById("close-button");
    click(closeButton);
    waitForElementDisplayed(By.id("dialog-closing-information"), true);
  }

  public void closeInformConfigDialog() {
    String closeButtonId = "close-dialog-button";
    click(findElementById(closeButtonId));
    waitForElementDisplayed(By.id(closeButtonId), false);
  }

  public void setClientSideTimeout(String timeout) {
    openSettingTab();
    editGlobalVariable("CLIENT_SIDE_TIMEOUT", timeout, false);
    closeConfirmationDialog();
  }

  public void closeConfirmationDialog() {
    closeAdminSettingDialog();
    closeInformConfigDialog();
  }

  public void setEnviromentInfo() {
    openSettingTab();
    editGlobalVariable("SHOW_ENVIRONMENT_INFO", "true", true);
    closeConfirmationDialog();
  }
  
  public boolean isWarningDialogShowWhenTimeoutIsLosing() {
    waitForElementDisplayed(By.id("warning-before-lost-session:timeout-warning-dialog"), true, 120);
    return isElementDisplayed(By.id("warning-before-lost-session:timeout-warning-dialog"));
  }

  public boolean isInformDialogShowAfterTimeout() {
    waitForElementDisplayed(By.id("warning-before-lost-session:timeout-dialog"), true, 180);
    return isElementDisplayed(By.id("warning-before-lost-session:timeout-dialog"));
  }

  public AnnouncementPage openAnnouncementTab() {
    WebElement settingTabLink = findElementByXpath("//a[@href='#adminui:adminTabView:announcement-tab']");
    click(settingTabLink);
    waitForElementPresent(By.id("adminui:adminTabView:announcement-tab"), true);
    waitAjaxIndicatorDisappear();
    return new AnnouncementPage();
  }

  public ExpressManagementPage openExpressManagementTab() {
    WebElement settingTabLink = findElementByXpath("//a[@href='#adminui:adminTabView:express-management-tab']");
    click(settingTabLink);
    waitForElementPresent(By.id("adminui:adminTabView:express-management-tab"), true);
    waitAjaxIndicatorDisappear();
    return new ExpressManagementPage();
  }
  
  public WebElement getAdminSettingDialog() {
    return findElementById("admin-ui-dialog");
  }
  
  public WebElement getAddApplicationDialog( ) {
    click(By.id("adminui:adminTabView:add-application-btn"));
    waitForElementDisplayed(By.id("adminui:appDialog"), true);
    Sleeper.sleep(300);//Wait a bit focus effects, just only use this for capture screenshot
    return findElementById("adminui:appDialog");
  }
  
  public WebElement getEditSettingDialogOfFirstRow() {
    click(By.id("adminui:adminTabView:settingTable:0:edit"));
    waitForElementDisplayed(By.id("adminui:settingDialog"), true);
    Sleeper.sleep(300);//Wait a bit focus effects, just only use this for capture screenshot
    return findElementById("adminui:settingDialog");
  }
}

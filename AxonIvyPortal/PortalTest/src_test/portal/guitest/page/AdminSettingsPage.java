package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
  
  private void clickToSettingTab(){
    WebElement settingTabLink = findElementByXpath("//a[@href='#adminui:adminTabView:settingTab']");
    settingTabLink.click();
    waitForElementPresent(By.id("adminui:adminTabView:settingForm"), true);
  }
  
  private void clickAddNewGlobalVariable(){
    WebElement addNewButton = findElementById("adminui:adminTabView:add-global-variable-button");
    addNewButton.click();
    waitForElementPresent(By.id("adminui:settingDialogForm"), true);
  }

  private void setClientSideTimeoutVar(String timeout){
    WebElement keyInput = findElementById("adminui:keySetting");
    keyInput.sendKeys("CLIENT_SIDE_TIMEOUT");
    WebElement valueInput = findElementById("adminui:valueSetting");
    valueInput.sendKeys(timeout);
    WebElement saveButton = findElementById("adminui:save-setting");
    saveButton.click();
  }
  
  private void closeAdminSettingDialog(){
    WebElement closeButton = findElementById("close-button");
    closeButton.click();
    waitForElementDisplayed(By.id("dialog-closing-information"), true);
  }
  
  private void closeInformConfigDialog(){
    WebElement closeButton = findElementById("close-dialog-button");
    closeButton.click();
  }

  public void setClientSideTimeout(String timeout){
    clickToSettingTab();
    clickAddNewGlobalVariable();
    setClientSideTimeoutVar(timeout);
    closeAdminSettingDialog();
    closeInformConfigDialog();
  }
  
  public boolean isWarningDialogShowWhenTimeoutIsLosing(){
    waitForElementDisplayed(By.id("warning-before-lost-session:timeout-warning-dialog"), true, 90);
    return isElementDisplayed(By.id("warning-before-lost-session:timeout-warning-dialog"));
  }

  public boolean isInformDialogShowAfterTimeout(){
    waitForElementDisplayed(By.id("viewExpiredExceptionDialog"), true, 150);
    return isElementDisplayed(By.id("viewExpiredExceptionDialog"));
  }
}

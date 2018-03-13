package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ch.xpertline.base.client.Browser;

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
  
  public void openDesignTab(){
    WebElement settingTabLink = findElementByXpath("//a[@href='#adminui:adminTabView:designTab']");
    settingTabLink.click();
    waitForElementPresent(By.id("adminui:adminTabView:logo-color-form"), true);
  }
  
  public void chooseMainColor(String color) {
    click(By.id("adminui:adminTabView:logo-color-form:main-color-chooser_button"));
    waitForElementDisplayed(By.className("ui-colorpicker-container"), true);
    
    WebDriver driver = Browser.getBrowser().getDriver();
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("document.querySelector('div.ui-colorpicker_hex > input').value='"+ color +"';");
    jse.executeScript("document.getElementById('adminui:adminTabView:logo-color-form:main-color-chooser_input').value='"+ color +"';");
  }
  
  public String getMainColor() {
    return findElementById("adminui:adminTabView:logo-color-form:main-color-chooser_input").getText();
  }
  
  public HomePage applyNewColor(){
    click(By.id("adminui:adminTabView:logo-color-form:apply-button"));
    return new HomePage();
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
    waitForElementDisplayed(By.id("warning-before-lost-session:timeout-dialog"), true, 150);
    return isElementDisplayed(By.id("warning-before-lost-session:timeout-dialog"));
  }
}

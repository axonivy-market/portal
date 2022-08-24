package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PasswordValidationPage extends TemplatePage {
  private static final String PASSWORD_VALIDATION_PAGE_ID = "admin-setting-component:adminTabView:password-validation-component:password-validation-form";

  public PasswordValidationPage() {
    waitForElementDisplayed(By.id(PASSWORD_VALIDATION_PAGE_ID), true);
  }

  @Override
  protected String getLoadedLocator() {
    return "id('admin-setting-component:adminTabView:password-validation-tab')";
  }

  @Override
  public boolean isDisplayed() {
    return findElementById(PASSWORD_VALIDATION_PAGE_ID).isDisplayed();
  }

  public boolean isPasswordValidationTogglePresent() {
    return isElementPresent(By.cssSelector(
        "[id$=':password-validation-component:password-validation-form:enable-password-validation-button']"));
  }

  public WebElement getPasswordValidationToggle() {
    return findElementByCssSelector(
        "[id$=':password-validation-component:password-validation-form:enable-password-validation-button']");
  }

  public WebElement getSaveButton() {
    return findElementByCssSelector(
        "[id$=':password-validation-component:password-validation-form:save-password-validation-btn']");
  }

  public boolean isPasswordValidationToggleChecked() {
    return getPasswordValidationToggle().getAttribute("class").contains("ui-toggleswitch-checked");
  }

  public void clickOnPasswordValidationToggle() {
    getPasswordValidationToggle().click();
  }

  public boolean isSaveButtonPresent() {
    return isElementPresent(
        By.cssSelector("[id$=':password-validation-component:password-validation-form:save-password-validation-btn']"));
  }

  public boolean isEnableSaveButton() {
    return getSaveButton().isEnabled();
  }

  public void clickOnSaveButton() {
    getSaveButton().click();
    waitForElementEnabled(
        By.cssSelector("[id$=':password-validation-component:password-validation-form:save-password-validation-btn']"),
        false, DEFAULT_TIMEOUT);
  }

  public boolean isCheckBoxInTableEnabled() {
    return findElementByClassName("ui-chkbox-box").getAttribute("class").contains("ui-state-active");
  }
}

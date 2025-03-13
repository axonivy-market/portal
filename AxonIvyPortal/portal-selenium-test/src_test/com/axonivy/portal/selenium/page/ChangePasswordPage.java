package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.Sleeper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ChangePasswordPage extends TemplatePage {


  @Override
  protected String getLoadedLocator() {
    return "[id='change-password-dialog_title']";
  }

  public void changePassword(String currentPassword, String newPassword) {
    inputCurrentPassword(currentPassword);
    inputConfirmNewPassword(newPassword);
    inputNewPassword(newPassword);
    waitForElementClickableThenClick($(By.id(("change-password-form:password-setting:save-password-setting"))));
  }

  private void inputCurrentPassword(String currentPassword) {
    inputField("change-password-form:password-setting:current-password", currentPassword);
  }

  private void inputNewPassword(String newPassword) {
    inputField("change-password-form:password-setting:new-password", newPassword);
  }

  private void inputConfirmNewPassword(String newPassword) {
    inputField("change-password-form:password-setting:confirm-new-password", newPassword);
  }

  private void inputField(String inputId, String value) {
    SelenideElement inputField = findElementById(inputId);
    inputField.sendKeys(value);
  }

  public void isWrongCurrentPasswordError() {
    $("#change-password-form\\:password-setting\\:change-password-messages .ui-messages-error-detail")
        .shouldHave(Condition.text("Authentication failed, your password seems to be wrong!"), DEFAULT_TIMEOUT);
  }

  public void isNewPasswordNotStrongEnough() {
    $("#change-password-form\\:password-setting\\:change-password-messages .ui-messages-error-detail").shouldHave(
        Condition.text(
            "Password must\n be at least 4 characters long\n contain at least 1 lowercase character\n contain at least 1 uppercase character\n contain at least 1 number\n contain at least 1 special character"),
        DEFAULT_TIMEOUT);
  }

  public WebElement getChangePasswordDialog() {
    Sleeper.sleep(200);// sleep a bit to make focus field effect before taking the screenshot
    return findElementById("change-password-dialog");
  }
}

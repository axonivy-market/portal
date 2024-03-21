package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class PasswordResetPage extends TemplatePage {

  private SelenideElement newPasswordTextField;
  private SelenideElement passwordConfirmationTextField;
  private SelenideElement resetButton;

  @Override
  protected String getLoadedLocator() {
    return "[id='password-reset:reset-password-form:reset-command']";
  }

  public PasswordResetPage() {
    this.newPasswordTextField = $("input[id='password-reset:reset-password-form:new-password']");
    this.passwordConfirmationTextField = $("input[id='password-reset:reset-password-form:password-confirmation']");
    this.resetButton = $("button[id='password-reset:reset-password-form:reset-command']");
  }

  public void resetPassword(String newPassword, Boolean strongPasswordEnough) {
    newPasswordTextField.sendKeys(newPassword);
    $(".login-footer").click();
    passwordConfirmationTextField.sendKeys(newPassword);
    $("[id='password-reset:reset-password-form:password-confirmation_panel']").shouldBe(Condition.appear,
        DEFAULT_TIMEOUT);
    $(".login-footer").click();
    clickByJavaScript(resetButton);

    if (strongPasswordEnough) {
      $("[id='password-reset:reset-password-form:result-message']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    } else {
      $("span[class='ui-messages-error-summary']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);;
    }
  }

  public void isReset() {
    $(".result-message").shouldBe(Condition.not(Condition.empty));
  }

  public void goHome() {
    waitForElementClickableThenClick(findElementById("password-reset:reset-password-form:go-home-button"));
    waitForElementDisplayed(By.id("login:login-form:login-command"), true);
  }

  public void isNewPasswordNotStrongEnough() {
    findElementByCssSelector("span[class='ui-messages-error-summary']").shouldBe(Condition.text(
        "Password must be at least 4 characters long, contain at least 1 lowercase character, contain at least 1 uppercase character, contain at least 1 number, contain at least 1 special character."));
  }

}

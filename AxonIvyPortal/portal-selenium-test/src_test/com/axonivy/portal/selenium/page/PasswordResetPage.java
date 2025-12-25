package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class PasswordResetPage extends TemplatePage {

  private SelenideElement newPasswordTextField;
  private SelenideElement passwordConfirmationTextField;
  private SelenideElement resetButton;
  private final String PASSWORD_WEAK_CUSTOM_MESSAGE = "Password must has at least 5 characters.";

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
    passwordConfirmationTextField.sendKeys(newPassword);
    $(resetButton).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    
    if (strongPasswordEnough) {
      $("[id='password-reset:reset-password-form:result-message']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    } else {
      $("[id='password-reset:reset-password-form:password-reset-message']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
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
    findElementByCssSelector("[id='password-reset:reset-password-form:password-reset-message']")
        .shouldBe(Condition.text(PASSWORD_WEAK_CUSTOM_MESSAGE), DEFAULT_TIMEOUT);
  }
}

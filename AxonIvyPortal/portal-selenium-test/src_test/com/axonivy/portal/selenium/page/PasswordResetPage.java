package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class PasswordResetPage extends TemplatePage{

  private SelenideElement newPasswordTextField;
  private SelenideElement passwordConfirmationTextField;
  private SelenideElement resetButton;

  @Override
  protected String getLoadedLocator() {
    return "[id='password-reset:reset-password-form:reset-command']";
  }

  public PasswordResetPage() {
    this.newPasswordTextField = $("input[id='password-reset:reset-password-form:new-password']");
    this.passwordConfirmationTextField = $(
        "input[id='password-reset:reset-password-form:password-confirmation']");
    this.resetButton = $("button[id='password-reset:reset-password-form:reset-command']");
  }
  
  @SuppressWarnings("deprecation")
  public void resetPassword(String newPassword, Boolean strongPasswordEnough) {
    newPasswordTextField.sendKeys(newPassword);
    passwordConfirmationTextField.sendKeys(newPassword);
    resetButton.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    if (strongPasswordEnough) {
      $("[id='password-reset:reset-password-form:result-message']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    } else {
      $("span[class='ui-messages-error-summary']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);;
    }
  }

}

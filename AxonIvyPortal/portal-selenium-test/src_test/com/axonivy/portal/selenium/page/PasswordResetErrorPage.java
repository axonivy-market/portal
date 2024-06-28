package com.axonivy.portal.selenium.page;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class PasswordResetErrorPage extends TemplatePage {

  private SelenideElement goForgotPasswordButton;

  @Override
  protected String getLoadedLocator() {
    return "[id$='password-reset-error:go-forgot-password-button']";
  }

  public PasswordResetErrorPage() {
    waitPageLoaded();
    this.goForgotPasswordButton =
        findElementByCssSelector("button[id='password-reset-error:go-forgot-password-button']");
  }

  public void goForgotPassword() {
    waitForElementClickableThenClick(goForgotPasswordButton);
    waitForElementDisplayed(By.id("forgot-password:forgot-password-form:send-command"), true);
  }
}

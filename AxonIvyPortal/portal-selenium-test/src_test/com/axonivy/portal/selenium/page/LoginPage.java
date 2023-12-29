package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class LoginPage extends TemplatePage {
  
  public static final String ID_PROPERTY = "id";
  private SelenideElement usernameTextField;
  private SelenideElement passwordField;
  private SelenideElement loginButton;
  private SelenideElement forgotPasswordLink;

  @Override
  protected String getLoadedLocator() {
    return "#login\\:login-form\\:login-command";
  }
  
  public LoginPage() {
    this.usernameTextField = $("input[id='login:login-form:username']");
    this.passwordField = $("input[id='login:login-form:password']");
    this.loginButton = $("button[id='login:login-form:login-command']");
    this.forgotPasswordLink = $("a[id='login:login-form:forgot-password-link']");
  }
  
  public void forgotPassword() {
    this.forgotPasswordLink.click();
    $("button[id$='forgot-password:forgot-password-form:send-command']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void waitForEmailAddressIsFocused() {
    $("input[id$='forgot-password:forgot-password-form:email']").shouldBe(Condition.focused, DEFAULT_TIMEOUT);
  }
  
  public void waitForUsernameInputIsFocused() {
    $("input[id$='login:login-form:username']").shouldBe(Condition.focused, DEFAULT_TIMEOUT);
  }
}

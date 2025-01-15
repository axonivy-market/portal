package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.axonivy.portal.selenium.common.TestAccount;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class LoginPage extends TemplatePage {

  private static final long LOGIN_TIMEOUT = 60;
  private SelenideElement usernameTextField;
  private SelenideElement passwordField;
  private SelenideElement loginButton;
  private SelenideElement forgotPasswordLink;

  private TestAccount testAccount;

  @Override
  protected String getLoadedLocator() {
    return "#login\\:login-form\\:login-command";
  }

  public LoginPage(TestAccount testAccount) {
    this();
    this.testAccount = testAccount;
  }

  public void login() {
    usernameTextField.sendKeys(testAccount.getUsername());
    passwordField.sendKeys(testAccount.getPassword());
    waitForElementClickableThenClick(loginButton);
    waitForElementDisplayed(By.id("left-menu"), true, LOGIN_TIMEOUT);
  }

  public void login(String username, String password) {
    usernameTextField.sendKeys(username);
    passwordField.sendKeys(password);
    waitForElementClickableThenClick(loginButton);
    waitForElementDisplayed(By.id("left-menu"), true, LOGIN_TIMEOUT);
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

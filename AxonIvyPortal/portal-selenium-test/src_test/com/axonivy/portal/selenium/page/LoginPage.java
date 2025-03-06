package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.axonivy.portal.selenium.common.TestAccount;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class LoginPage extends TemplatePage {

  public static final String ID_PROPERTY = "id";
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

  public void login(boolean shouldBeSuccess) {
    usernameTextField.sendKeys(testAccount.getUsername());
    passwordField.sendKeys(testAccount.getPassword());
    waitForElementClickableThenClick(loginButton);
    if (shouldBeSuccess) {
      waitForElementDisplayed(By.id("left-menu"), true, LOGIN_TIMEOUT);
    } else {
      $("div[id='login:login-form:login-message'] div ul li span.ui-messages-error-summary").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    }
  }

  public void login(String username, String password, boolean shouldBeSuccess) {
    usernameTextField.sendKeys(username);
    passwordField.sendKeys(password);
    waitForElementClickableThenClick(loginButton);
    if (shouldBeSuccess) {
      waitForElementDisplayed(By.id("left-menu"), true, LOGIN_TIMEOUT);
    } else {
      $("div[id='login:login-form:login-message'] div ul li span.ui-messages-error-summary").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    }
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

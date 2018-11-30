package ch.ivyteam.ivy.project.portal.guitest.mobile.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ch.ivyteam.ivy.project.portal.guitest.mobile.common.TestAccount;

public class MobileLoginPage extends MobileTemplatePage{

  private static final long LOGIN_TIMEOUT = 60;
  private WebElement usernameTextField;
  private WebElement passwordField;
  private WebElement loginButton;
  private TestAccount testAccount;

  @Override
  protected String getLoadedLocator() {
    return "id('login:login-form:login-command')";
  }

  public MobileLoginPage(TestAccount testAccount) {
    waitForPageLoaded();
    this.usernameTextField = findElementById("login:login-form:username");
    this.passwordField = findElementById("login:login-form:password");
    this.loginButton = findElementById("login:login-form:login-command");
    this.testAccount = testAccount;
  }

  public void login() {
    usernameTextField.sendKeys(testAccount.getUsername());
    passwordField.sendKeys(testAccount.getPassword());
    loginButton.click();
    waitForElementDisplayed(By.id("mobile-menu"), true, LOGIN_TIMEOUT);
  }
  
  public void login(String username, String password) {
    usernameTextField.sendKeys(username);
    passwordField.sendKeys(password);
    loginButton.click();
    waitForElementDisplayed(By.id("mobile-menu"), true, LOGIN_TIMEOUT);
  }
}

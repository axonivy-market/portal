package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.TestAccount;
import vn.wawa.guitest.base.page.AbstractPage;

public class LoginPage extends AbstractPage {
  private static final long LOGIN_TIMEOUT = 60;
  private WebElement usernameTextField;
  private WebElement passwordField;
  private WebElement loginButton;
  private WebElement forgotPasswordLink;
  private TestAccount testAccount;

  @Override
  protected String getLoadedLocator() {
    return "id('login:login-form:login-command')";
  }

  public LoginPage() {
    waitForPageLoaded();
    this.usernameTextField = findElementByCssSelector("input[id='login:login-form:username']");
    this.passwordField = findElementByCssSelector("input[id='login:login-form:password']");
    this.loginButton = findElementByCssSelector("button[id='login:login-form:login-command']");
    this.forgotPasswordLink =  findElementByCssSelector("a[id='login:login-form:forgot-password-link']");
  }

  public LoginPage(TestAccount testAccount) {
    this();
    this.testAccount = testAccount;
  }

  public void login() {
    usernameTextField.sendKeys(testAccount.getUsername());
    passwordField.sendKeys(testAccount.getPassword());
    click(loginButton);
    waitForElementDisplayed(By.id("left-menu"), true, LOGIN_TIMEOUT);
  }
  
  public void login(String username, String password) {
    usernameTextField.sendKeys(username);
    passwordField.sendKeys(password);
    click(loginButton);
    waitForElementDisplayed(By.id("left-menu"), true, LOGIN_TIMEOUT);
  }

  public void forgotPassword() {
    click(forgotPasswordLink);
    waitForElementDisplayed(By.id("forgot-password:forgot-password-form:send-command"), true, LOGIN_TIMEOUT);
  }

}

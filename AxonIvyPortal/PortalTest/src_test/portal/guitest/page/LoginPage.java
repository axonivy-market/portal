package portal.guitest.page;

import org.openqa.selenium.WebElement;

import portal.guitest.common.TestAccount;
import ch.xpertline.base.pages.AbstractPage;

public class LoginPage extends AbstractPage {
  private WebElement usernameTextField;
  private WebElement passwordField;
  private WebElement loginButton;
  private TestAccount testAccount;

  @Override
  protected String getLoadedLocator() {
    return "id('login:login-form:login-command')";
  }

  public LoginPage(TestAccount testAccount) {
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
  }

}

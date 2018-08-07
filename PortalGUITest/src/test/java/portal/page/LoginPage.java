package portal.page;

import org.openqa.selenium.WebElement;

import portal.common.TestAccount;
import ch.xpertline.base.pages.AbstractPage;

public class LoginPage extends AbstractPage {
  private WebElement usernameTextField;
  private WebElement passwordField;
  private WebElement loginButton;
  private TestAccount testAccount;

  @Override
  protected String getLoadedLocator() {
    return "id('login-form:main-form:login-button')";
  }

  public LoginPage(TestAccount testAccount) {
    waitForPageLoaded();
    this.usernameTextField = findElementById("login-form:main-form:username");
    this.passwordField = findElementById("login-form:main-form:password");
    this.loginButton = findElementById("login-form:main-form:login-button");
    this.testAccount = testAccount;
  }

  public void login() {
    usernameTextField.sendKeys(testAccount.getUsername());
    passwordField.sendKeys(testAccount.getPassword());
    loginButton.click();
  }

}

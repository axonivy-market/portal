package portal.guitest.page;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import vn.wawa.guitest.base.page.AbstractPage;

public class PasswordResetPage extends AbstractPage {
  private static final long PASSWORD_RESET_TIMEOUT = 60;

  private WebElement newPasswordTextField;
  private WebElement passwordConfirmationTextField;
  private WebElement resetButton;

  @Override
  protected String getLoadedLocator() {
    return "id('password-reset:reset-password-form:reset-command')";
  }

  public PasswordResetPage() {
    waitForPageLoaded();
    this.newPasswordTextField = findElementByCssSelector("input[id='password-reset:reset-password-form:new-password']");
    this.passwordConfirmationTextField = findElementByCssSelector("input[id='password-reset:reset-password-form:password-confirmation']");
    this.resetButton =  findElementByCssSelector("button[id='password-reset:reset-password-form:reset-command']");
  }

  public void resetPassword() {
    newPasswordTextField.sendKeys("123");
    passwordConfirmationTextField.sendKeys("123");
    click(resetButton);
    waitForElementDisplayed(By.id("password-reset:reset-password-form:result-message"), true, PASSWORD_RESET_TIMEOUT);
  }

  public boolean isReset() {
    String resultMessage = findElementByClassName("result-message").getText();
    return StringUtils.isNotBlank(resultMessage);
  }

  public void goHome() {
    WebElement goHomeButton = findElementById("password-reset:reset-password-form:go-home-button");
    click(goHomeButton);
    waitForElementDisplayed(By.id("login:login-form:login-command"), true, PASSWORD_RESET_TIMEOUT);
  }
}

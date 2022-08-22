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
    this.passwordConfirmationTextField = findElementByCssSelector(
        "input[id='password-reset:reset-password-form:password-confirmation']");
    this.resetButton = findElementByCssSelector("button[id='password-reset:reset-password-form:reset-command']");
  }

  public void resetPassword(String newPassword, Boolean strongPasswordEnough) {
    newPasswordTextField.sendKeys(newPassword);
    passwordConfirmationTextField.sendKeys(newPassword);
    click(resetButton);

    if (strongPasswordEnough) {
      waitForElementDisplayed(By.id("password-reset:reset-password-form:result-message"), true, PASSWORD_RESET_TIMEOUT);
    } else {
      waitForElementDisplayedByCssSelector("span[class='ui-messages-error-summary']", (int) PASSWORD_RESET_TIMEOUT);
    }
  }

  public boolean isNewPasswordNotStrongEnough() {
    WebElement element = findElementByCssSelector("span[class='ui-messages-error-summary']");
    return element.getText().equalsIgnoreCase(
        "New password is not strong enough. Must be at least 4 characters long. Must contains at least 1 lowercase character. Must contains at least 1 uppercase character. Must contains at least 1 number. Must contains at least 1 special character.");
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

  public void waitForPasswordInputIsFocused() {
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "password-reset\\:reset-password-form\\:new-password", "id");
  }
}

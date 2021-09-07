package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import vn.wawa.guitest.base.page.AbstractPage;

public class PasswordResetErrorPage extends AbstractPage {
  private static final long PASSWORD_RESET_TIMEOUT = 60;

  private WebElement goForgotPasswordButton;

  @Override
  protected String getLoadedLocator() {
    return "id('password-reset-error:go-forgot-password-button')";
  }

  public PasswordResetErrorPage() {
    waitForPageLoaded();
    this.goForgotPasswordButton =  findElementByCssSelector("button[id='password-reset-error:go-forgot-password-button']");
  }

  public void goForgotPassword() {
    click(goForgotPasswordButton);
    waitForElementDisplayed(By.id("forgot-password:forgot-password-form:send-command"), true, PASSWORD_RESET_TIMEOUT);
  }
}

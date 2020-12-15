package portal.guitest.page;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.TestAccount;
import vn.wawa.guitest.base.page.AbstractPage;

public class ForgotPasswordPage extends AbstractPage {
  private static final long FORGOT_PASSWORD_TIMEOUT = 60;

  private WebElement emailTextField;
  private WebElement sendButton;
  private TestAccount testAccount;

  @Override
  protected String getLoadedLocator() {
    return "id('forgot-password:forgot-password-form:send-command')";
  }

  public ForgotPasswordPage() {
    waitForPageLoaded();
    this.emailTextField = findElementByCssSelector("input[id='forgot-password:forgot-password-form:email']");
    this.sendButton =  findElementByCssSelector("button[id='forgot-password:forgot-password-form:send-command']");
  }

  public ForgotPasswordPage(TestAccount testAccount) {
    this();
    this.testAccount = testAccount;
  }

  public void send() {
    emailTextField.sendKeys(testAccount.getEmail());
    click(sendButton);
    waitForElementDisplayed(By.id("forgot-password:forgot-password-form:forgot-password-message"), true, FORGOT_PASSWORD_TIMEOUT);
  }

  public boolean isProcessed() {
    WebElement summayMessageSpan = findElementByCssSelector("span[class='ui-messages-info-summary'], span[class='ui-messages-error-summary']");
    return summayMessageSpan != null && StringUtils.isNotBlank(summayMessageSpan.getText());
  }
}

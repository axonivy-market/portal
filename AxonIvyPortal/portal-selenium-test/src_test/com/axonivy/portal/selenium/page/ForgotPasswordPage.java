package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.axonivy.portal.selenium.common.TestAccount;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ForgotPasswordPage extends TemplatePage {
  private static final String RESULT_MESSAGE_SELECTOR =
      "span[class='ui-messages-info-summary'], span[class='ui-messages-error-summary']";

  private SelenideElement emailTextField;
  private SelenideElement sendButton;
  private TestAccount testAccount;

  @Override
  protected String getLoadedLocator() {
    return "[id$='forgot-password:forgot-password-form:send-command']";
  }

  public ForgotPasswordPage() {
    waitForPageLoad();
    this.emailTextField = $("input[id='forgot-password:forgot-password-form:email']");
    this.sendButton = $("button[id='forgot-password:forgot-password-form:send-command']");
  }

  public ForgotPasswordPage(TestAccount testAccount) {
    this();
    this.testAccount = testAccount;
  }

  public void send() {
    emailTextField.sendKeys(testAccount.getEmail());
    waitForElementClickableThenClick(sendButton);
    waitForElementDisplayed(By.cssSelector(RESULT_MESSAGE_SELECTOR), true);
  }

  public void isProcessed() {
    findElementByCssSelector(RESULT_MESSAGE_SELECTOR).shouldBe(Condition.not(Condition.empty));
  }
}

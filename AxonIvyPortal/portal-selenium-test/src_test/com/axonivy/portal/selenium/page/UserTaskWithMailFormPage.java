package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.JavascriptExecutor;

import com.codeborne.selenide.WebDriverRunner;

public class UserTaskWithMailFormPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='task-form']";
  }

  public void selectEmailTab() {
    boolean alignToTop = false;
    $("a[href*='mail-tab']").shouldBe(appear, DEFAULT_TIMEOUT).scrollIntoView(alignToTop);
    $("a[href*='mail-tab']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='information-email:email-content_editor']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void inputData(String recipient, String subject, String content) {
    inputRecipient(recipient);
    inputSubject(subject);
    inputContent(content);
  }

  private void inputRecipient(String recipient) {
    $("[id='task-form:task-view:information-email:email-recipients']").shouldBe(appear, DEFAULT_TIMEOUT)
        .sendKeys(recipient);
  }

  private void inputSubject(String content) {
    $("[id='task-form:task-view:information-email:email-subject']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(content);
  }

  private void inputContent(String content) {
    JavascriptExecutor jse = (JavascriptExecutor) WebDriverRunner.getWebDriver();
    jse.executeScript(
        "document.querySelector(\"input[name='task-form:task-view:information-email:email-content_input'\").value='"
            + content + "';");
  }

  public void finish() {
    $("[id='task-form:ok-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }
}

package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class ExpressTaskPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".task-template-container";
  }

  public SelenideElement findExpressTask() {
    return $(".js-task-header-container").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id='task-template-title']");
  }

  public void waitForExpressFieldSetDisplay() {
    $(".express-fieldset").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void enterRequiredInputFieldByLabel(String label, String data) {
    $(String.format("input[data-p-rmsg*='%s']", label)).shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(data);
  }

  public void finish() {
    $("[id='form:ok-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public boolean isDocumentTableVisible() {
    return isElementPresent(By.xpath("//div[contains(@id, 'fileUploadComponent:document-table')]"));
  }

  public boolean isDocumentUploadButtonVisible() {
    return isElementPresent(By.xpath("//div[contains(@id, 'fileUploadComponent:document-upload')]"));
  }

  public void clickTaskActionMenu() {
    clickByJavaScript($("button[id$='horizontal-task-actions']"));
    waitForElementDisplayed(By.cssSelector("[id$=':horizontal-task-action-menu']"), true);
  }

  public void clickChatGroup() {
    $("a[id$='chat-group']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickCreateGroupChatBtn() {
    waitForElementDisplayed(By.id("chat-assignee-selection-form:chat-group-create-button"), true);
    waitForElementClickableThenClick(By.id("chat-assignee-selection-form:chat-group-create-button"));
    waitForElementDisplayed(By.id("chat-assignee-selection-form:chat-group-create-button"), false);
  }

  public void joinProcessChatAlreadyCreated() {
    waitForElementDisplayed(By.id("chat-group-join-form:chat-group-join-button"), true);
    waitForElementClickableThenClick($(By.id("chat-group-join-form:chat-group-join-button")));
    waitForElementDisplayed(By.id("chat-form:group-chat-container"), true);
  }
}

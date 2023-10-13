package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TaskTemplatePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#content-container";
  }

  public SelenideElement getDisplayedTaskTitle() {
    return getTaskTitle().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getTaskTitle() {
    return $("span[id='title']");
  }

  public SelenideElement getStartedTaskTemplateTitle() {
    return $("span[id='title']").shouldBe(appear, DEFAULT_TIMEOUT);
  }


  public SelenideElement getElementInPortalIFramTask(String cssSelector) {
    WaitHelper.waitForIFrameAvailable(WebDriverRunner.getWebDriver(), "iFrame");
    return $(cssSelector);
  }
  
  public void clickCancelButton() {
    $("a[id$='button-cancel']").shouldBe(getClickableCondition()).click();
  }

  public void clickActionButton() {
    $("[id='horizontal-task-actions']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='horizontal-task-action-form:horizontal-task-action-menu']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOnStartAdhocLink() {
    $("div[id$='horizontal-task-action-form:horizontal-task-action-menu']").$("a[id$='start-adhoc']")
      .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='adhoc-task-reset-confirmation-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickAdhocOkButton() {
    $("button[id$='start-adhoc-ok-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='adhoc-task-reset-confirmation-dialog_content']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void closeAdhocHistoryDialog() {
    $("button[id$='close-adhoc-dialog-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='adhoc-task-history-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public WebElement openAdhocHistoryDialog() {
    clickActionButton();
    $("[id='horizontal-task-action-form:show-adhoc-history']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $("div[id$='adhoc-task-history-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickChatGroup() {
    $("a[id$='chat-group']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public WebElement getAddMemberToChatDialog() {
    return $("[id='chat-assignee-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickCreateGroupChatButton() {
    $("[id='chat-assignee-selection-form:chat-group-create-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='chat-assignee-selection-form:chat-group-create-button']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
}

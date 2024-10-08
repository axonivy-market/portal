package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.refresh;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import ch.ivyteam.ivy.project.portal.test.Responsible;

public class ChatPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='toggle-chat-panel-command']";
  }

  public void openFirstGroupChat() {
    ElementsCollection chatNames =
        $("[id='chat-form:group-chat-container']").shouldBe(appear, DEFAULT_TIMEOUT).$$(".js-group-card-name");
    if (!chatNames.isEmpty()) {
      chatNames.get(0).shouldBe(appear, DEFAULT_TIMEOUT).click();
    }
  }

  public void addUserToChatGroup(List<Responsible> responsibles) {
    waitForElementDisplayed(By.id("chat-assignee-dialog"), true);
    for (Responsible responsible : responsibles) {
      chooseResponsible(responsible.getResponsibleName(), responsible.getIsGroup());
    }
  }

  public void chooseResponsible(String responsible, boolean isGroup) {
    if (isGroup) {
      selectRoleAssigneeCheckbox();
      $(By.cssSelector("input[id$='selection_input']")).sendKeys(responsible);
      waitForElementDisplayed(
          By.id("chat-assignee-selection-form:chat-role-selection-component:chat-role-selection_panel"), true);
      waitForElementClickableThenClick(
          "span[id='chat-assignee-selection-form:chat-role-selection-component:chat-role-selection_panel'] .gravatar");
    } else {
      $(By.cssSelector("input[id$='selection_input']")).sendKeys(responsible);
      waitForElementDisplayed(By.cssSelector("span[id$='chat-user-selection_panel']"), true);
      $(By.xpath(
          "//*[@id='chat-assignee-selection-form:chat-user-selection-component:chat-user-selection_panel']/table/tbody/tr"))
              .shouldBe(getClickableCondition()).click();
    }
    waitForElementEnabled(By.id("chat-assignee-selection-form:chat-add-assignee-button"), true);
    waitForElementClickableThenClick($(By.id("chat-assignee-selection-form:chat-add-assignee-button")));
  }

  private void selectRoleAssigneeCheckbox() {
    waitForElementClickableThenClick(
        $(By.xpath(String.format("//label[@for='%s']", "chat-assignee-selection-form:chat-assignee-type:1"))));
    waitForElementDisplayed(By.cssSelector("input[id$='role-selection_input']"), true);
  }

  public void sendMessage(String chatMessage) {
    waitForElementExisted(By.cssSelector("textarea[id='message-input-field']"), true);
    waitForElementEnabled(By.id("message-input-field"), true);
    WebElement input = findElementById("message-input-field");
    input.sendKeys(chatMessage);
    input.sendKeys(Keys.ENTER);
    WaitHelper.assertTrueWithWait(() -> getAllMessagesChatLog().contains(chatMessage));
  }

  public String getAllMessagesChatLog() {
    waitForElementDisplayed(By.id("chat-message-list"), true);
    return $$(".js-message").asFixedIterable().stream().map(WebElement::getText).collect(Collectors.joining(","));
  }

  public int refreshAndCountGroupChat() {
    refresh();
    getChat();
    waitForElementDisplayed(By.id("chat-form:group-chat-container"), true);
    ElementsCollection chatGroups = findElementById("chat-form:group-chat-container").findAll(".js-group-card-name");
    return chatGroups.size();
  }

  public void closeChatMessageList() {
    waitForElementDisplayed(By.cssSelector(".js-close-message-list"), true);
    waitForElementClickableThenClick(By.cssSelector(".js-close-message-list"));
  }

  public void isNotificationBadgeChat() {
    waitForElementDisplayed(By.cssSelector("a.notification-badge"), true);
    $(By.cssSelector("a[data-badge=' ']")).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }


  public void isNotificationContactChat() {
    $(By.cssSelector("span[class$='js-notification']")).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void selectChatUser(String name) {
    waitForElementDisplayed(By.xpath("//span[text()='" + name + "']"), true);
    waitForElementClickableThenClick(By.xpath("//span[text()='" + name + "']"));
  }
}

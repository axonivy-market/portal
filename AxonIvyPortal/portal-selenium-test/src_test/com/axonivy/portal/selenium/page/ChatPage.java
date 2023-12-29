package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;

public class ChatPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='toggle-chat-panel-command']";
  }

  public void openFirstGroupChat() {
    ElementsCollection chatNames = $("[id='chat-form:group-chat-container']").shouldBe(appear, DEFAULT_TIMEOUT).$$(".js-group-card-name");
    if (!chatNames.isEmpty()) {
      chatNames.get(0).shouldBe(appear, DEFAULT_TIMEOUT).click();
    }
  }
}

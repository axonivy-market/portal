package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class UserMenuPage extends TemplatePage {

  private static final String USER_SETTING_CONTAINER_SELECTOR = "[id='user-setting-container']";

  @Override
  protected String getLoadedLocator() {
    return USER_SETTING_CONTAINER_SELECTOR;
  }

  public SelenideElement findMenu(String title) {
    return $(USER_SETTING_CONTAINER_SELECTOR).waitUntil(appear, DEFAULT_TIMEOUT).$$("li").filter(Condition.text(title)).first();
  }
  
  public void accessMenu(String title) {
    findMenu(title).shouldBe(getClickableCondition()).click();
  }
}

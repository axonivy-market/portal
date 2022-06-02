package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class UserMenuPage extends TemplatePage {

  public UserMenuPage() {
    $("[id='user-setting-container']").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement findMenu(String title) {
    return $("[id='user-setting-container']").waitUntil(appear, DEFAULT_TIMEOUT).$$("li").filter(Condition.text(title)).first();
  }
  
  public void accessMenu(String title) {
    findMenu(title).shouldBe(getClickableCondition()).click();
  }
}

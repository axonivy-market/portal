package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Selenide.$;

public class AnnouncementPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='admin-setting-component:adminTabView:announcement-tab']";
  }

  public String getInfoSummary() {
    return $("div[id$='messages'] span[class$='summary']").shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public void publish() {
    $("button[id$='publish-announcement']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("button[id$='publish-announcement']").shouldBe(disabled, DEFAULT_TIMEOUT);
  }

  public void dePublish() {
    $("button[id$='delete-announcement']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("button[id$='delete-announcement']").shouldBe(disabled, DEFAULT_TIMEOUT);
  }

  public void setAnnoucement(int Language, String content) {
    $("input[id$='" + Language + ":announcement-input']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(content);
  }
}

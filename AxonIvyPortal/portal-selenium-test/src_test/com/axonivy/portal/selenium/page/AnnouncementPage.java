package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class AnnouncementPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='admin-setting-component:adminTabView:announcement-tab']";
  }

  public String getInfoSummary() {
    return $("div[id$='announcement-tab']").shouldBe(appear, DEFAULT_TIMEOUT).$("span.ui-messages-error-summary").shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public void clickOnSwitchButton() {
    waitForElementClickableThenClick("div[id$='announcement-toggle-switch']");
  }

  public void setAnnoucement(int Language, String content) {
    $("input[id$='" + Language + ":announcement-input']").sendKeys(content);
  }
  
  public void clickOnSaveButton() {
    $("[id$='save-changes-announcement']").shouldBe(getClickableCondition()).click();
  }

}

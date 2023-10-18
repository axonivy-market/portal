package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.WebDriverRunner;
public class AnnouncementPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='admin-setting-component:adminTabView:announcement-tab']";
  }

  public String getInfoSummary() {
    return WebDriverRunner.getWebDriver().findElement(By.cssSelector("div[id$='messages'] span[class$='summary']")).getText();
  }

  @SuppressWarnings("deprecation")
  public void publish() {
    waitForElementClickableThenClick("button[id$='publish-announcement']");
    waitAjaxIndicatorDisappear();
  }

  @SuppressWarnings("deprecation")
  public void dePublish() {
    waitForElementClickableThenClick("button[id$='delete-announcement']");
    waitAjaxIndicatorDisappear();
  }

  public void setAnnoucement(int Language, String content) {
    $("input[id$='" + Language + ":announcement-input']").sendKeys(content);
  }


}

package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;

import com.codeborne.selenide.SelenideElement;

public class ProcessInformationPage extends TemplatePage {

  public ProcessInformationPage() {}

  @Override
  protected String getLoadedLocator() {
    return "#process-information";
  }

  public SelenideElement getDisplayedBackLink() {
    return getBackLink().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getBackLink() {
    return $("a[id='back-link']");
  }
  
  public void checkBackLinkAndStartButtonShown() {
    getDisplayedBackLink();
    getDisplayedStartButton().shouldBe(enabled);
  }

  public SelenideElement getDisplayedStartButton() {
    return getStartButton().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getStartButton() {
    return $("button[id='start-process-button']");
  }
}

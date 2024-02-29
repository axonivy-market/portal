package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProcessInformationPage extends TemplatePage {

  public ProcessInformationPage() {}

  @Override
  protected String getLoadedLocator() {
    return "#process-information";
  }

  public SelenideElement getDisplayedBackLink() {
    return getBackLink().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getBackLink() {
    return $("a[id='back-link']");
  }

  public void checkBackLinkAndStartButtonShown() {
    getDisplayedBackLink();
    getDisplayedStartButton().shouldBe(Condition.enabled);
  }

  public SelenideElement getDisplayedStartButton() {
    return getStartButton().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getStartButton() {
    return $("button[id='start-process-button']");
  }

  public String getProcessName() {
    waitForElementDisplayed(By.cssSelector("[id$='header']"), true);
    return findElementByCssSelector("[id='header'] > h2 ").getText();
  }

  public String getProcessDescription() {
    waitForElementDisplayed(By.cssSelector("[id$='process-description']"), true);
    return findElementByCssSelector("[id$='process-description']").getText();
  }

  public void startProcess() {
    waitForElementClickableThenClick("[id$='start-process-button']");
  }

  public void back() {
    waitForElementClickableThenClick("[id$='back-link']");
  }

  public String getProcessInfoWrapperContent() {
    waitForElementDisplayed(By.cssSelector("[id$='process-info-wrapper']"), true);
    return findElementByCssSelector("[id$='process-info-wrapper'] > h3 > span").getText();
  }
}

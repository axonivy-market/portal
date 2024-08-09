package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class LendingDetailPage extends TemplatePage {
  int index;

  @Override
  protected String getLoadedLocator() {
    return "[id='lending-detail-header']";
  }

  public LendingDetailPage(int index) {
    this.index = index;
  }

  public SelenideElement getHearText() {
    return findElementByCssSelector("[id$='lending-detail-header']");
  }

  public SelenideElement getStageName() {
    return findElementByCssSelector(String.format("[id$='stage-%d-name']", index));
  }

  public SelenideElement getProcessName(int index) {
    return findElementByCssSelector(String.format("[id$=':%d:process-name']", index));
  }

  public SelenideElement getSideStepName(int index) {
    return findElementByCssSelector(String.format("[id$=':%d:side-step-name']", index));
  }

  public LendingOverviewPage navigateToLendingOverview() {
    clickByJavaScript($("[id$='back-to-case-map']"));
    waitForElementDisplayed(By.cssSelector("[id$='lending-header']"), true);
    return new LendingOverviewPage();
  }

  public LendingDetailPage navigateToPreviousDetail() {
    clickByJavaScript($("[id$='previous-stage']"));
    waitForElementDisplayed(By.cssSelector(String.format("[id$='stage-%d-name']", index - 1)), true);
    return new LendingDetailPage(index - 1);
  }

  public LendingDetailPage navigateToNextDetail() {
    clickByJavaScript($("[id$='next-stage']"));
    waitForElementDisplayed(By.cssSelector(String.format("#stage-%d-name", index + 1)), true);
    return new LendingDetailPage(index + 1);
  }
}

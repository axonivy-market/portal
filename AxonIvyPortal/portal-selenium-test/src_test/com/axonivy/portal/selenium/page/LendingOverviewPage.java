package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class LendingOverviewPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "[id='lending-header']";
  }

  public SelenideElement getHearText() {
    return findElementByCssSelector("[id$='lending-header']");
  }

  public SelenideElement getStageName(int index) {
    return findElementByCssSelector(String.format("[id$=':%d:stage']", index));
  }

  public LendingDetailPage navigateToStageDetail(int index) {
    clickByJavaScript($(String.format("[id$=':%d:detail-form:detail-stage']", index)));
    return new LendingDetailPage(index);
  }

  public CaseMapPage startLendingCase() {
    clickByJavaScript($(By.cssSelector("#start")));
    waitForElementDisplayed(By.cssSelector("input[id$='form:first-name']"), true);
    switchBackToParent();
    return new CaseMapPage();
  }
}

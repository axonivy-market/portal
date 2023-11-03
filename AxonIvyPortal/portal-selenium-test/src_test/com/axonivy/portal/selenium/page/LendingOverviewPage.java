package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class LendingOverviewPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "[id='lending-header']";
  }

  public String getHearText() {
    WebElement element = findElementByCssSelector("[id$='lending-header']");
    return element.getText();
  }
  
  public String getStageName(int index) {
    WebElement element = findElementByCssSelector(String.format("[id$=':%d:stage']", index));
    return element.getText();
  }
  
  public LendingDetailPage navigateToStageDetail(int index) {
    waitForElementClickableThenClick(String.format("[id$=':%d:detail-form:detail-stage']", index));
    return new LendingDetailPage(index);
  }
  
  public CaseMapPage startLendingCase() {
    waitForElementClickableThenClick($(By.cssSelector("#start")));
    waitForElementDisplayed(By.cssSelector("input[id$='form:first-name']"), true);
    return new CaseMapPage();
  }
}

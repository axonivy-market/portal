package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class CaseMapPage extends TemplatePage{

  @Override
  protected String getLoadedLocator() {
    return "[id='content-container']";
  }

  public void waitForIFrameContentVisible() {
    $("button[id='form:submit-request']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public NewDashboardPage clickSubmitRequestButton() {
    $("button[id$='submit-request']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    switchBackToParent();
    return new NewDashboardPage();
  }
  
  public NewDashboardPage clickSubmitButtonAndBackToTaskList() {
    $("button[id$='submit-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    switchBackToParent();
    return new NewDashboardPage();
  }

  public String getHeader() {
    return findElementByCssSelector("#header").getText();
  }
}

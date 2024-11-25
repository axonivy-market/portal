package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

public class GlobalGrowlIframeTemplatePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#content";
  }

  public NewDashboardPage clickCancel() {
    return clickButton("content-form:cancel");
  }

  public NewDashboardPage clickProceed() {
    return clickButton("content-form:proceed");
  }

  private NewDashboardPage clickButton(String idSelector) {
    waitForElementDisplayed(By.id(idSelector), true);
    $("button[id='" + idSelector + "']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    clickByJavaScript($("button[id='" + idSelector + "']"));
    switchToDefaultContent();
    return new NewDashboardPage();
  }

}

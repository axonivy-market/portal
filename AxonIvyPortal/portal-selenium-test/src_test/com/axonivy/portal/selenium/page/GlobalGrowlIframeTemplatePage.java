package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class GlobalGrowlIframeTemplatePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#content";
  }

  public TaskWidgetPage clickCancel() {
    TaskWidgetPage taskWidgetPage = clickButton("content-form:cancel");
    waitForPageLoad();
    return taskWidgetPage;
  }

  public TaskWidgetPage clickProceed() {
    TaskWidgetPage taskWidgetPage = clickButton("content-form:proceed");
    waitForPageLoad();
    return taskWidgetPage;
  }

  private TaskWidgetPage clickButton(String idSelector) {
    waitForElementDisplayed(By.id(idSelector), true);
    $("button[id='" + idSelector + "']").click();
    switchToDefaultContent();
    return new TaskWidgetPage();
  }

}

package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class GlobalGrowlIframeTemplatePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#content";
  }

  public TaskWidgetPage clickCancel() {
    return clickButton("content-form:cancel");
  }

  public TaskWidgetPage clickProceed() {
    return clickButton("content-form:proceed");
  }

  private TaskWidgetPage clickButton(String idSelector) {
    waitForElementDisplayed(By.id(idSelector), true);
    $("button[id='" + idSelector + "']").click();
    switchToDefaultContent();
    return new TaskWidgetPage();
  }

}

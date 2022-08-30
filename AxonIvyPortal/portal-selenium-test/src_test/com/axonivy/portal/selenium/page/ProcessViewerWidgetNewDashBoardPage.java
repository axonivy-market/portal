package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProcessViewerWidgetNewDashBoardPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id$='selected-process']";
  }

  public void selectProcessAndSaveWidget(String processName) {
    selectProcess(processName);
    clickSaveProcessViewerWidget();
  }

  private void selectProcess(String processName) {
    getSelectedProcess().click();
    getSelectedProcess().find("input").clear();
    getSelectedProcess().find("input").sendKeys(processName);
    $("tr[data-item-label='" + processName + "']").click();
  }

  public SelenideElement getSelectedProcess() {
    return $("span[id$=':selected-process']");
  }

  public void clickSaveProcessViewerWidget() {
    $("button[id='widget-configuration-save-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }
}

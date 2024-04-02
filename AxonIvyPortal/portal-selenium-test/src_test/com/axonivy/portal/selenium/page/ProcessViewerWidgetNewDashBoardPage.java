package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
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
    $("button[id='widget-configuration-save-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void findProcess(String processName) {
    getSelectedProcess().click();
    getSelectedProcess().find("input").clear();
    getSelectedProcess().find("input").sendKeys(processName);
  }

  public ElementsCollection getSelectedProcessList() {
    return $$("span[id$=':selected-process_panel'] tr");
  }
}

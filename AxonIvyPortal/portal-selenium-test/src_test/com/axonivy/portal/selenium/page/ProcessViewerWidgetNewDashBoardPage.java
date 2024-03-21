package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

  public void selectProcess(String processName) {
    getSelectedProcess().shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getSelectedProcess().find("input").clear();
    getSelectedProcess().find("input").sendKeys(processName);
    $("tr[data-item-label='" + processName + "']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void unfocusAllInputs() {
    $("[id='new-widget-configuration-dialog_title']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getSelectedProcess() {
    return $("span[id$=':selected-process']");
  }

  public void clickSaveProcessViewerWidget() {
    $("button[id='widget-configuration-save-button']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void findProcess(String processName) {
    getSelectedProcess().click();
    getSelectedProcess().find("input").clear();
    getSelectedProcess().find("input").sendKeys(processName);
  }

  public ElementsCollection getSelectedProcessList() {
    return $$("span[id$=':selected-process_panel'] tr");
  }

  public SelenideElement getConfigurationDialog() {
    unfocusAllInputs();
    return $("div[id='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}

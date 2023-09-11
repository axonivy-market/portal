package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class CustomWidgetNewDashBoardPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id$='widget-configuration-form']";
  }

  public void selectProcessAndSaveWidget(String processName) {
    selectProcess(processName);
    clickSaveCustomWidget();
  }

  public void selectProcess(String processName) {
    getSelectedProcess().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("input[id$=':selected-process_input']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).sendKeys(processName);
  }

  public SelenideElement getSelectedProcess() {
    return $("span[id$=':selected-process']");
  }

  public void clickSaveCustomWidget() {
    $("button[id='widget-configuration-save-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getSelectedProcessList() {
    return $$("span[id$=':selected-process_panel'] tr");
  }

  public void selectWidgetType(String type) {
    getWidgetType().shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("label[id$=':custom-widget-type_label']")
        .shouldBe(getClickableCondition()).click();
    $("li[data-label='" + type + "']").click();
  }

  private SelenideElement getWidgetType() {
    return $("div[id$=':custom-widget-type']");
  }

  public ElementsCollection getProcessList() {
    return $("span[id$=':selected-process_panel']").shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr");
  }

  public SelenideElement processParam() {
    return $("div[id$=':parameters-panel']");
  }
}

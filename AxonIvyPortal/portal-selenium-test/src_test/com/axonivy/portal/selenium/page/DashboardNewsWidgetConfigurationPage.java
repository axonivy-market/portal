package com.axonivy.portal.selenium.page;


import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class DashboardNewsWidgetConfigurationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id$='news-widget-configuration-header']";
  }

  public void changeWidgetTitle(String newWidgetName) {
    SelenideElement widgetTitle = $(".widget-configuration.news-widget-configuration").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("[id$=':new-widget-configuration-component:widget-title']").shouldBe(editable, DEFAULT_TIMEOUT);
    widgetTitle.clear();
    widgetTitle.sendKeys(newWidgetName);
  }

  public void save() {
    $("button[id$='widget-configuration-save-button']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id$='new-widget-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getConfigurationDialog() {
    return $("div[id='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}

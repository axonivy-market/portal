package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class StatisticEditWidgetNewDashboardPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".statistic-configuration__select-chart";
  }

  private SelenideElement widgetTitle() {
    return $("[id$=':new-widget-configuration-component:widget-title-group']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("span[id$='widget-title-group']").$("input[id$='widget-title']");
  }

  public void changeWidgetTitle(String name) {
    widgetTitle().clear();
    widgetTitle().sendKeys(name);
  }
  
  public void save() {
    $("[id$='new-widget-configuration-dialog']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("button[id$='widget-configuration-save-button']").shouldBe(getClickableCondition()).click();
  }

  public void selectFirstChart() {
    $("[id$=':new-widget-configuration-component:user-filter']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("[id$=':new-widget-configuration-component:statistic-list']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("button.ui-autocomplete-dropdown").waitUntil(Condition.exist, DEFAULT_TIMEOUT).waitUntil(getClickableCondition(), DEFAULT_TIMEOUT).click();
    var selectionOptions = $("[id$=':new-widget-configuration-component:statistic-list_panel']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("tr.ui-autocomplete-item.ui-autocomplete-row");
    selectionOptions.get(0).click();
    $("[id$=':new-widget-configuration-component:statistic-list_panel']").waitUntil(disappear, DEFAULT_TIMEOUT);
  }

}

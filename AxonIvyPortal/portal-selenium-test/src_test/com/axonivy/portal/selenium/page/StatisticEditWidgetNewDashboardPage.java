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
    return $("[id$=':new-widget-configuration-component:widget-title-group']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("span[id$='widget-title-group']").$("input[id$='widget-title']");
  }

  public void changeWidgetTitle(String name) {
    widgetTitle().clear();
    widgetTitle().sendKeys(name);
  }
  
  public void save() {
    $("[id$='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("button[id$='widget-configuration-save-button']").shouldBe(getClickableCondition()).click();
  }

  public void selectFirstChart() {
    $("[id$=':new-widget-configuration-component:user-filter']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("[id$=':new-widget-configuration-component:statistic-list']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("button.ui-autocomplete-dropdown").shouldBe(Condition.exist, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    var selectionOptions = $("[id$=':new-widget-configuration-component:statistic-list_panel']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("tr.ui-autocomplete-item.ui-autocomplete-row");
    selectionOptions.get(0).click();
    $("[id$=':new-widget-configuration-component:statistic-list_panel']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

}

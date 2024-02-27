package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.common.Sleeper;
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
    $("div[id='new-widget-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void selectFirstChart() {
    $("[id$=':new-widget-configuration-component:user-filter']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("[id$=':new-widget-configuration-component:statistic-list']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("button.ui-autocomplete-dropdown").shouldBe(Condition.exist, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    var selectionOptions = $("[id$=':new-widget-configuration-component:statistic-list_panel']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("tr.ui-autocomplete-item.ui-autocomplete-row");
    selectionOptions.get(0).click();
    $("[id$=':new-widget-configuration-component:statistic-list_panel']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void clickPreviewButton() {
    $("button[id$='preview-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='widget-configuration-form:new-widget-configuration-component:preview-statistic']").find("canvas")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$='preview-button']").shouldBe(appear, DEFAULT_TIMEOUT).hover();
  }

  public SelenideElement getConfigurationDialog() {
    SelenideElement statisticDialog = $("div[id='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    // We use Sleeper here to wait for chart render completely, because the
    // statistic dialog was render with an animation by canvas.
    Sleeper.sleep(1000);
    return statisticDialog;
  }
}

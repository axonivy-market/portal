package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class StatisticWidgetDashboardPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".statistic-chart-widget__content";
  }

  public ElementsCollection countStatisticCharts() {
    return $$(".statistic-chart-widget .widget__header-title");
  }

  public SelenideElement getStatisticInfoIconOfChart(String chartId) {
    return $(String.format("[id$='chart-%s:info-sidebar-link-0']", chartId)).shouldBe(appear, DEFAULT_TIMEOUT)
        .should(getClickableCondition());
  }

  public void openStatisticInfoPanel(String chartId) {
    getStatisticInfoIconOfChart(chartId).click();
    $(String.format("[id$='chart-%s:info-overlay-panel-0']", chartId)).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection countFilterOfStatistic(String chartId) {
    return $(String.format("[id$='chart-%s:info-overlay-panel-0']", chartId)).shouldBe(appear, DEFAULT_TIMEOUT)
        .$("table[id$=':chart-filter-container']").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr");
  }

  public String getChartName(String chartId) {
    return $(String.format("span[id$='chart-%s:widget__header']", chartId)).shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public SelenideElement getEditIconOfChart(String chartId) {
    return $(String.format("[id$='chart-%s:widget-header-actions']", chartId)).shouldBe(appear, DEFAULT_TIMEOUT)
        .$(String.format("[id$='chart-%s:edit-widget-0']", chartId)).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String openEditStatisticWidgetDialog(String chartId) {
    getEditIconOfChart(chartId).should(getClickableCondition()).click();
    return $("[id$='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("span[id$='new-widget-configuration-dialog_title']").shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public void clickOnCancelConfiguration() {
    $("[id$='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("a[id$='task-configuration-cancel-link']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("[id$='new-widget-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
}

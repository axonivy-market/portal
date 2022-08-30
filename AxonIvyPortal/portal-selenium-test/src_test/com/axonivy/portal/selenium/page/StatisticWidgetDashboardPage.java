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
    return $$("span.statistic-chart-widget__header");
  }
  
  public SelenideElement getStatisticInfoIconOfChart(int chartIndex) {
    return $(String.format("[id$=':chart-info-link-%d']", chartIndex)).waitUntil(appear, DEFAULT_TIMEOUT)
        .should(getClickableCondition());
  }

  public void openStatisticInfoPanel(int chartIndex) {
    $(String.format("[id$=':chart-info-link-%d']", chartIndex)).waitUntil(appear, DEFAULT_TIMEOUT)
        .should(getClickableCondition()).click();
    $(String.format("[id$=':info-overlay-panel-%d']", chartIndex)).waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection countFilterOfStatistic(int chartIndex) {
    return $(String.format("[id$=':info-overlay-panel-%d']", chartIndex)).waitUntil(appear, DEFAULT_TIMEOUT)
          .$(".chart-information-container").waitUntil(appear, DEFAULT_TIMEOUT)
              .$("table[id$=':chart-filter-container']").waitUntil(appear, DEFAULT_TIMEOUT)
                  .$$("tr");
  }

  public String getChartName(int chartIndex) {
    return $(String.format("span[id$=':statistic-chart-name-%d']", chartIndex)).waitUntil(appear, DEFAULT_TIMEOUT)
        .getText();
  }

  public SelenideElement getEditIconOfChart(int chartIndex) {
    return $(".statistic-chart-widget__header").waitUntil(appear, DEFAULT_TIMEOUT)
      .$(String.format("[id$=':edit-widget-%d']", chartIndex)).waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public String openEditStatisticWidgetDialog(int chartIndex) {
    getEditIconOfChart(chartIndex).should(getClickableCondition()).click();
    return $("[id$='new-widget-configuration-dialog']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("span[id$='new-widget-configuration-dialog_title']").waitUntil(appear, DEFAULT_TIMEOUT).getText();
  }

  public void clickOnCancelConfiguration() {
    $("[id$='new-widget-configuration-dialog']").waitUntil(appear, DEFAULT_TIMEOUT).
        $("a[id$='task-configuration-cancel-link']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$='new-widget-configuration-dialog']").waitUntil(disappear, DEFAULT_TIMEOUT);
  }
}

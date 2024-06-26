package com.axonivy.portal.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class ClientStatisticWidgetNewDashboardPage extends TemplatePage {

  private String widgetId;
  private String widgetName;

  @Override
  protected String getLoadedLocator() {
    return ".js-client-statistic-chart";
  }

  public String getChartNameAt(String index) {
    waitForElementDisplayed(By.className("js-client-statistic-chart"), true);
    SelenideElement chartId = $("[id$='client-statistic-client_statistic_" + index + "']");
    return chartId.getText();
  }

  public ClientStatisticWidgetNewDashboardPage(String chartName) {
    this("div[id$='stat-chart-widget__content']", chartName);
  }

  public ClientStatisticWidgetNewDashboardPage(String widgetId, String widgetName) {
    this.widgetId = widgetId;
    this.widgetName = widgetName;
  }

  public ElementsCollection getAllChartLabels() {
    return $(widgetId).$$("div.chart-content-card > div.chart-label-container");
  }
  
  public WebElement getWidget() {
    return $$("div.card-widget-panel").filter(Condition.text(widgetName)).first();
  }
  
  public void clickOnButtonExpand() {
    getChartWidgetHeader().$("div[id$='widget-header-actions']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }
  
  public ElementsCollection getAllChartNumbers() {
    return $(widgetId).$$("div.chart-content-card > div.chart-number-container > span.card-number");
  }
  
  public boolean isEmptyMessageAppear() {
    return $("div[id$='widget-content']").$("div[class*='statistic-chart-widget__content']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("div.empty-message-container").shouldBe(appear, DEFAULT_TIMEOUT).isDisplayed();
  }
  
  private SelenideElement getChartWidgetHeader() {
    return $$("div.widget__header").filter(text(widgetName)).first();
  }
}

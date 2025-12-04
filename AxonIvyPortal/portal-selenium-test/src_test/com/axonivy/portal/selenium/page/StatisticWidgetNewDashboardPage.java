package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class StatisticWidgetNewDashboardPage extends TemplatePage {

  private String widgetId;
  private String widgetName;

  @Override
  protected String getLoadedLocator() {
    return ".js-statistic-chart";
  }

  public String getChartNameAt(String index) {
    waitForElementDisplayed(By.className("js-statistic-chart"), true);
    SelenideElement chartId = $("[id$='statistic-client_statistic_" + index + "']");
    return chartId.getText();
  }

  public StatisticWidgetNewDashboardPage(String chartName) {
    this("div[id$='stat-chart-widget__content']", chartName);
  }

  public StatisticWidgetNewDashboardPage(String widgetId, String widgetName) {
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
  
  public boolean isChartNumberElementClickable() {
    SelenideElement el = $(widgetId).$("div.chart-number-container").shouldBe(appear, DEFAULT_TIMEOUT).$("span.card-number").shouldBe(appear, DEFAULT_TIMEOUT);
    String cursorStyle = el.getCssValue("cursor");
    return Objects.nonNull(cursorStyle) && cursorStyle.equals("pointer");
  }
  
  public NewDashboardPage clickOnElementOnNumberChart(String index) {
    SelenideElement targetCard = getTargetElementOnNumberChart(index);
    targetCard.click();
    return new NewDashboardPage();
  }
  
  public int getValueOfTargetElementOnNumberChart(String index) {
    SelenideElement cardNumberElement = getTargetElementOnNumberChart(index).$("span.card-number").shouldBe(appear, DEFAULT_TIMEOUT);
    return Integer.valueOf(cardNumberElement.getText());
  }

  private SelenideElement getTargetElementOnNumberChart(String index) {
    ElementsCollection chartCards = $(widgetId).$("div.js-statistic-chart").shouldBe(appear, DEFAULT_TIMEOUT).$$("div.chart-content-card");
    SelenideElement targetCard = chartCards.filter(Condition.attribute("data-index", index))
        .first().shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition());
    return targetCard;
  }
}

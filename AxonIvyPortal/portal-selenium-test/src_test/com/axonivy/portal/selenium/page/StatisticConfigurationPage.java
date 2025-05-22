package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.axonivy.portal.selenium.common.ComplexFilterHelper;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

public class StatisticConfigurationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".statistic-configuration__preview";
  }

  public void setChartName(String name) {
    $("input[id$='config-form:name']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(name);
  }

  public void changeChartTargetTo(int chartTargetIndex) {
    $("div[id$='config-form:target'] div.ui-selectonemenu-trigger").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("li[id$='config-form:target_" + chartTargetIndex + "']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }

  public void changeChartTypeTo(int chartTypeIndex) {
    $("div[id$='config-form:type'] div.ui-selectonemenu-trigger").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("li[id$='config-form:type_" + chartTypeIndex + "']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }
  
  public void changeGroupByTo(int groupIndex) {
    $("div[id$='config-form:aggregates'] div.ui-selectonemenu-trigger").shouldBe(getClickableCondition()).click();
    $("li[id$='config-form:aggregates_" + groupIndex + "']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }

  public ElementsCollection getPermissions() {
    $("div[id='config-form:permissions']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $$("div[id='config-form:permissions'] ul li");
  }

  public ElementsCollection getAggregationItems() {
    return $$("select[id$='config-form:aggregates_input'] option");
  }

  public ElementsCollection getTimeIntervalItems() {
    $("div[id='config-form:aggregates-custom-fields-intervals']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $$("select[id$='config-form:aggregates-custom-fields-intervals_input'] option");
  }

  public ElementsCollection getCaseCustomFieldItems() {
    $("div[id='config-form:aggregates-custom-fields']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $$("select[id$='config-form:aggregates-custom-fields_input'] option");
  }

  public String getPreviewChartNumberLabel() {
    return $("div[id$='config-form:stat-chart-widget__content']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$$("div.chart-content-card > div.chart-label-container").first().text();
  }

  public int getPreviewChartNumberValue() {
    String numberStr = $("div[id$='config-form:stat-chart-widget__content']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$$("div.chart-content-card > div.chart-number-container").first().text();
    return Integer.parseInt(numberStr);
  }

  public void backgroundColorVisible() {
    $("div[id$='config-form:background-colors-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void axisXYVisible() {
    $("div[id$='config-form:x-axis-title-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("div[id$='config-form:y-axis-title-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void autoRefeshToggleVisible() {
    $("div[id$='config-form:refresh-interval-enabled']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void hideLabelVisible() {
    $("div[id$='config-form:hide-label']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void chartCanvasVisible() {
    $("canvas[id$='preview-statistic-chart']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void clickGeneratePreviewChart() {
    $("button[id$='config-form:generate-statistic-chart'").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    waitForElementClickable($("button[id$='config-form:generate-statistic-chart'"));
  }

  public void clickCreateStatisticChart() {
    $("button[id$='config-form:create-statistic-chart'").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }

  public void addFilter(String columnName, FilterOperator operator) {
    ComplexFilterHelper.addFilter(columnName, operator);
  }

  public void inputValueOnLatestFilter(FilterValueType type, Object... values) {
    ComplexFilterHelper.inputValueOnLatestFilter(type, values);
  }

  public void changeOperator(String filterLabel, FilterOperator operator, String type) {
    String typeInput = String.format("div[id$=':%s-filter-operator-panel']", type);
    $("div[id$='widget-filter-content']").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$=':filter-container']").$$("span[id$=':field-selection_label']").filter(text(filterLabel)).first().shouldBe(appear, DEFAULT_TIMEOUT);

    $(typeInput).shouldBe(getClickableCondition()).$("span[id$=':operator-selection_label']").click();

    $$("li").filter(text(operator.getValue())).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }
}

package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.axonivy.portal.selenium.common.ComplexFilterHelper;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.Sleeper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class StatisticConfigurationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".statistic-configuration__preview";
  }

  public void setChartName(String name) {
    $("input[id$='config-form:name']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(name);
  }

  public void changeChartTarget(String chartTargetName) {
    $("div[id$='config-form:target'] div.ui-selectonemenu-trigger").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $$("li[id^='config-form:target_']").filter(text(chartTargetName)).first().shouldBe(getClickableCondition()).click();
  }

  public void changeChartType(String chartTypeName) {
    $("div[id$='config-form:type'] div.ui-selectonemenu-trigger").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $$("li[id^='config-form:type_']").filter(text(chartTypeName)).first().shouldBe(getClickableCondition()).click();
  }
  
  public void changeGroupBy(String groupByName) {
    $("div[id$='config-form:aggregates'] div.ui-selectonemenu-trigger").shouldBe(getClickableCondition()).click();
    $$("li[id^='config-form:aggregates_']").filter(text(groupByName)).first().shouldBe(getClickableCondition()).click();
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
  
  public void toggleConditionBasedColoring() {
    Sleeper.sleep(5000);
    SelenideElement toggle = $("div[id$='config-form:condition-based-coloring-enabled-toggle']");
    toggle.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    toggle.click();
  }

  private SelenideElement getUserThreshold() {
    return getConditionBackgroundColorsContainer().shouldBe(appear, DEFAULT_TIMEOUT).$("span[id$='config-form:user-threshold']");
  }
  
  public void addNewCondition() {
    getUserThreshold().shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='config-form:add-threshold']").shouldBe(clickable()).click();
  }
  
  private SelenideElement getChartBackgroundColor() {
    return $("span[id$='config-form:chart-background-colors']");
  }
  
  private SelenideElement getConditionBackgroundColorsContainer() {
    return getChartBackgroundColor().shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='config-form:condition-background-colors-container']");
  }
  
  public void verifyColoringScopeVisible() {
    $("div[id$='config-form:condition-based-coloring-scope']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void selectColoringScope(String scope) {
    $("div[id$='config-form:coloring-option'] div.ui-selectonemenu-trigger").shouldBe(getClickableCondition()).click();
    $$("li[id^='config-form:coloring-option_']").filter(text(scope)).first().shouldBe(getClickableCondition()).click();
  }
  
  public void configureThreshold(int index, String operator, String value, String color) {
    String thresholdPrefix = String.format("div[id$='config-form:condition-background-color-list:%d:threshold-component:threshold-panel']", index);
    
    $(thresholdPrefix).$(".threshold-operator-value div.ui-selectonemenu-trigger").shouldBe(getClickableCondition()).click();
    $$("li").filter(text(operator)).first().shouldBe(getClickableCondition()).click();
    
    $(thresholdPrefix).$(".threshold-input-value input").shouldBe(appear, DEFAULT_TIMEOUT).clear();
    $(thresholdPrefix).$(".threshold-input-value input").sendKeys(value);
    
    $(thresholdPrefix).$("input.threshold-color-value").shouldBe(appear, DEFAULT_TIMEOUT).clear();
    $(thresholdPrefix).$("input.threshold-color-value").sendKeys(color);
  }
  
  public void configureThresholdWithCategory(int index, String operator, String value, String color, String category) {
    String thresholdPrefix = String.format("div[id$='config-form:condition-background-color-list:%d:threshold-component:threshold-panel']", index);
    
    $(thresholdPrefix).$(".threshold-category-data div.ui-selectonemenu-trigger").shouldBe(getClickableCondition()).click();
    $$("li").filter(text(category)).first().shouldBe(getClickableCondition()).click();
    
    $(thresholdPrefix).$(".threshold-operator-value div.ui-selectonemenu-trigger").shouldBe(getClickableCondition()).click();
    $$("li").filter(text(operator)).first().shouldBe(getClickableCondition()).click();
    
    $(thresholdPrefix).$(".threshold-input-value input").shouldBe(appear, DEFAULT_TIMEOUT).clear();
    $(thresholdPrefix).$(".threshold-input-value input").sendKeys(value);
    
    $(thresholdPrefix).$("input.threshold-color-value").shouldBe(appear, DEFAULT_TIMEOUT).clear();
    $(thresholdPrefix).$("input.threshold-color-value").sendKeys(color);
  }
  
  public void clearAllConditions() {
    ElementsCollection deleteButtons = $$("button[id$=':remove-threshold']");
    while (deleteButtons.size() > 0) {
      deleteButtons.first().shouldBe(getClickableCondition()).click();
      deleteButtons = $$("button[id$=':remove-threshold']");
    }
  }
  
  public void verifyDefaultColorPickerVisible() {
    $("div[id$='config-form:statistic_defaultBackgroundColor']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void verifyConditionBasedColoringNotVisible() {
    $("div[id$='config-form:condition-based-coloring-enabled-toggle']").shouldNot(appear);
  }
  
  public void selectCustomField(String customFieldName) {
    $("div[id$='config-form:aggregates-custom-fields'] div.ui-selectonemenu-trigger").shouldBe(getClickableCondition()).click();
    $$("li").filter(text(customFieldName)).first().shouldBe(getClickableCondition()).click();
  }
  
  public void verifyCustomFieldSelectionVisible() {
    $("div[id$='config-form:aggregates-custom-fields']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void verifyNoDataAvailableMessage() {
    $("div[id$='config-form:condition-background-colors-container'] div.col-12")
      .shouldBe(appear, DEFAULT_TIMEOUT);
  }
}

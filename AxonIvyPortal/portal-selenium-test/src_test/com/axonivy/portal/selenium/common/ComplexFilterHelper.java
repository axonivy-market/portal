package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;
import java.util.Arrays;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;

public class ComplexFilterHelper {
  protected final static Duration DEFAULT_TIMEOUT = Duration.ofSeconds(45);

  public static SelenideElement getNewFilter(int filterIndex) {
    String latestFilter = String.format("div[id$=':%s:filter-component:filter-selection-panel']", filterIndex);
    return $(".dashboard-widget-filter__main-panel").$(latestFilter).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public static void selectFilterColumnName(String columnName, int filterIndex) {
    var filterElement = getNewFilter(filterIndex);
    filterElement.$("div[id$=':filter-component:field-selection']").shouldBe(getClickableCondition()).click();
    String columnSelection = String.format("div[id$=':%s:filter-component:field-selection_panel'][style*='display: block']", filterIndex);
    $(columnSelection).$$("ul li").filter(text(columnName)).first().click();
  }

  public static void selectFilterOperator(FilterOperator operator, int filterIndex) {
    var filterElement = getNewFilter(filterIndex);
    filterElement.$("div[id$=':operator-selection']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    WaitHelper.waitForActionComplete(".dashboard-widget-filter__main-panel",
        () -> $("div[id$=':operator-selection_panel'] ul[id$=':operator-selection_items']").$$("li")
            .filter(text(operator.getValue())).first().click());
  }

  public static void inputValueOnLatestFilter(FilterValueType type, Object... values) {
    int currentIndex = $(".dashboard-widget-filter__main-panel").$$("div[id$=':filter-component:filter-selection-panel']").size();
    if (currentIndex < 1) {
      return;
    }
    var filterElement = getNewFilter(currentIndex - 1);
    switch (type) {
      case STATE_TYPE:
        handleFilterState(filterElement, values);
        break;
      case TEXT:
        handleFilterText(filterElement, values);
        break;
      case NUMBER:
        handleFilterNumber(filterElement, values);
        break;
      case NUMBER_BETWEEN:
        handleFilterNumberBetween(filterElement, values);
        break;
      case DATE_BETWEEN:
        handleFilterDateBetween(filterElement, values);
        break;
      case DATE:
        handleFilterDate(filterElement, values);
        break;
      case CREATOR_TYPE:
        handleFilterCreator(filterElement, values);
        break;
      case RESPONSIBLE_TYPE:
        handleFilterResponsible(filterElement, values);
        break;
      case CATEGORY_TYPE:
        handleFilterCategory(filterElement, values);
        break;
      case DATE_CURRENT:
        handleFilterDateCurrent(filterElement, values);
        break;
      case WITHIN:
        handleFilterWithin(filterElement, values);
        break;
      default:
        break;
    }
  }
  
  public static SelenideElement addFilter(String columnName, FilterOperator operator) {
    int currentIndex = $(".dashboard-widget-filter__main-panel").$$("div[id$=':filter-component:filter-selection-panel']").size();
    $("button[id$=':add-filter']").shouldBe(getClickableCondition()).click();
    $(".dashboard-widget-filter__main-panel").$$("div[id$=':filter-component:filter-selection-panel']")
        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(currentIndex + 1), DEFAULT_TIMEOUT);
    ComplexFilterHelper.selectFilterColumnName(columnName, currentIndex);
    if (operator != null) {
      ComplexFilterHelper.selectFilterOperator(operator, currentIndex);
    }
    return ComplexFilterHelper.getNewFilter(currentIndex);
  }

  private static void handleFilterState(SelenideElement filterElement, Object... values) {
    filterElement.$("div[id$=':states']").shouldBe(getClickableCondition()).click();
    for (int i = 0; i < values.length; i++) {
      getValueOfCheckBox(String.valueOf(values[i])).shouldBe(getClickableCondition()).click();
    }
    getCloseCheckBox().shouldBe(getClickableCondition()).click();
  }

  private static void handleFilterText(SelenideElement filterElement, Object... values) {
    var textField = filterElement.$("div[id$='-list-panel']").$("div[class*='ui-chips ui-widget']").$("input")
        .shouldBe(Condition.editable);
    for (int i = 0; i < values.length; i++) {
      textField.clear();
      String textFieldIdSelector = "[id=\"" + textField.getAttribute("id")
          + "\"]";
      textField.sendKeys(String.valueOf(values[i]));
      WaitHelper.waitForActionComplete(textFieldIdSelector, () -> {
        textField.pressEnter();
      });
    }
  }
  
  private static void handleFilterNumber(SelenideElement filterElement, Object... values) {
    var textField = filterElement.$("div[id*='number-panel']").$("span[class*='ui-widget']").$("input")
        .shouldBe(Condition.editable);
    for (int i = 0; i < values.length; i++) {
      textField.clear();
      textField.sendKeys(String.valueOf(values[i]));
      textField.pressEnter();
    }
  }

  private static void handleFilterWithin(SelenideElement filterElement, Object... values) {
    var numberPeriodInput = filterElement.$("div[id$=':number-of-periods-panel']").$("input").shouldBe(appear);
    numberPeriodInput.clear();
    numberPeriodInput.shouldBe(Condition.empty, DEFAULT_TIMEOUT).sendKeys(String.valueOf(values[0]));

    $("div[id$=':period-type-panel']").shouldBe(getClickableCondition()).click();

    $$("li").filter(text(String.valueOf(values[1]))).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
  }

  private static void handleFilterDateCurrent(SelenideElement filterElement, Object... values) {
    var date_current = filterElement.$("[id$=':current-period-selection']").shouldBe(getClickableCondition());
    date_current.click();
    var selectPanel = $("div[id$=':current-period-selection_panel']").shouldBe(appear);
    selectPanel.$$("ul li").filter(text(String.valueOf(values[0]))).first().shouldBe(getClickableCondition())
        .click();
  }

  private static void handleFilterCategory(SelenideElement filterElement, Object... values) {
    String[] categories = Arrays.copyOf(values, values.length, String[].class);
    filterCategories(filterElement, categories);
  }

  private static void handleFilterCreator(SelenideElement filterElement, Object... values) {
    var creatorInput = filterElement.$("div[id$=':creators']").$("input").shouldBe(appear);
    for (int i = 0; i < values.length; i++) {
      creatorInput.clear();
      creatorInput.sendKeys(String.valueOf(values[i]));
      var selectPanel = $("span[id$=':creators_panel'][style*='display: block']").shouldBe(appear);
      selectPanel.$(".ui-avatar-text").shouldBe(appear);
      selectPanel.shouldBe(getClickableCondition()).click();
      selectPanel.shouldBe(disappear);
      filterElement.$("div[id$=':creators']").$("ul li.ui-helper-hidden").should(disappear);
    }
  }
  
  private static void handleFilterResponsible(SelenideElement filterElement, Object... values) {
    var creatorInput = filterElement.$("div[id$=':responsibles']").$("input").shouldBe(appear);
    for (int i = 0; i < values.length; i++) {
      creatorInput.clear();
      creatorInput.sendKeys(String.valueOf(values[i]));
      var selectPanel = $("span[id$=':responsibles_panel'][style*='display: block']").shouldBe(appear);
      selectPanel.$(".ui-avatar-text").shouldBe(appear);
      selectPanel.shouldBe(getClickableCondition()).click();
      selectPanel.shouldBe(disappear);
      filterElement.$("div[id$=':responsibles']").$("ul li.ui-helper-hidden").should(disappear);
    }
  }

  private static void handleFilterDate(SelenideElement filterElement, Object... values) {
    var dateInput = filterElement.$$(".date-picker-panel input")
        .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(values.length));
    for (int i = 0; i < dateInput.size(); i++) {
      dateInput.get(i).clear();
      dateInput.get(i).shouldBe(Condition.empty, DEFAULT_TIMEOUT).sendKeys(String.valueOf(values[i]));
      filterElement.$("span button").$("span[class*='ui-icon-calendar']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    }
  }

  private static void handleFilterNumberBetween(SelenideElement filterElement, Object... values) {
    var fromInput = filterElement.$("div[id$=':between-number-panel-from']").$("input[id$=':from-number_input']")
        .shouldBe(Condition.editable);
    fromInput.clear();
    fromInput.sendKeys(String.valueOf(values[0]));

    var toInput = filterElement.$("div[id$=':between-number-panel-to']").$("input[id$=':to-number_input']")
        .shouldBe(Condition.editable);
    toInput.clear();
    toInput.sendKeys(String.valueOf(values[1]));
  }

  private static void handleFilterDateBetween(SelenideElement filterElement, Object... values) {
    var fromInput = filterElement.$("div[id$=':between-dates-panel-from']").$("input[id$=':from-date_input']")
        .shouldBe(Condition.editable);
    fromInput.clear();
    fromInput.sendKeys(String.valueOf(values[0]));

    var toInput = filterElement.$("div[id$=':between-dates-panel-to']").$("input[id$=':to-date_input']")
        .shouldBe(Condition.editable);
    toInput.clear();
    toInput.sendKeys(String.valueOf(values[1]));
    filterElement.$("div[id$=':between-dates-panel-to']").$("button").$("span[class*='ui-icon-calendar']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  private static SelenideElement getValueOfCheckBox(String value) {
    return $("div.ui-selectcheckboxmenu-panel[style*='display: block']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }

  protected static WebElementCondition getClickableCondition() {
    return and("should be clickable", visible, exist);
  }

  private static SelenideElement getCloseCheckBox() {
    return $("div.ui-selectcheckboxmenu-panel[style*='display: block']").shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-selectcheckboxmenu-close");
  }

  private static void filterCategories(SelenideElement filterEl, String... categories) {
    filterEl.$("[id$=':widget-filter-category']").shouldBe(getClickableCondition()).click();
    var categoriesPanel = $("[id$=':widget-filter-category-panel']").shouldBe(appear, DEFAULT_TIMEOUT);
    categoriesPanel.$("[id$=':widget-category-filter-tree']").$$(".ui-chkbox").first().shouldBe(getClickableCondition())
        .click();

    categoriesPanel.$$(".ui-treenode").asDynamicIterable().forEach(leaf -> {
      for (var category : categories) {
        var leafValue = leaf.$(".ui-treenode-label").getText();
        if (category.equalsIgnoreCase(leafValue)) {
          leaf.$(".ui-chkbox").shouldBe(getClickableCondition()).click();
          break;
        }
      }
    });

    categoriesPanel.$("button[id$=':update-command']").shouldBe(getClickableCondition()).click();
    categoriesPanel.shouldBe(disappear, DEFAULT_TIMEOUT);
  }

}

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

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ComplexFilterHelper {
  protected final static Duration DEFAULT_TIMEOUT = Duration.ofSeconds(45);

  public static SelenideElement getNewFilter(int filterIndex) {
    String latestFilter = String.format("div[id$=':%s:filter-component:filter-selection-panel']", filterIndex);
    return $(latestFilter).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public static void selectFilterColumnName(String columnName, int filterIndex) {
    var filterElement = getNewFilter(filterIndex);
    filterElement.$("div[id$=':filter-component:field-selection']").shouldBe(getClickableCondition()).click();
    String columnSelection = String.format("div[id$=':%s:filter-component:field-selection_panel']", filterIndex);
    $(columnSelection).$$("ul li").filter(text(columnName)).first().click();
  }

  public static void selectFilterOperator(FilterOperator operator, int filterIndex) {
    var filterElement = getNewFilter(filterIndex);
    filterElement.$("div[id$=':operator-selection']").shouldBe(getClickableCondition()).click();
    WaitHelper.waitForActionComplete(".dashboard-widget-filter__main-panel",
        () -> $("div[id$=':operator-selection_panel'] ul[id$=':operator-selection_items']").$$("li")
            .filter(text(operator.getValue())).first().click());
  }

  public static void inputValueOnLatestFilter(FilterValueType type, Object... values) {
    int currentIndex = $$("div[id$=':filter-component:filter-selection-panel']").size();
    if (currentIndex < 1) {
      return;
    }
    var filterElement = getNewFilter(currentIndex - 1);
    switch (type) {
      case STATE_TYPE:
        filterElement.$("div[id$=':states']").shouldBe(getClickableCondition()).click();
        for (int i = 0; i < values.length; i++) {
          getValueOfCheckBox(String.valueOf(values[i])).shouldBe(getClickableCondition()).click();
        }
        getCloseCheckBox().shouldBe(getClickableCondition()).click();
        break;
      case TEXT, NUMBER:
        var textField = filterElement.$("div[id$=':text-list-panel']").$(".ui-chips.ui-widget").$("input")
            .shouldBe(Condition.editable);
        for (int i = 0; i < values.length; i++) {
          textField.clear();
          textField.sendKeys(String.valueOf(values[i]));
          textField.pressEnter();
        }
        break;
      case NUMBER_BETWEEN:
        filterElement.$("div[id$=':between-number-panel-from']").$("input[id$=':from-number_input']")
            .shouldBe(Condition.editable).sendKeys(String.valueOf(values[0]));
        filterElement.$("div[id$=':between-number-panel-to']").$("input[id$=':to-number_input']")
            .shouldBe(Condition.editable).sendKeys(String.valueOf(values[1]));
        break;
      case DATE_BETWEEN:
        var fromInput = filterElement.$("div[id$=':between-dates-panel-from']").$("input[id$=':from-date_input']")
            .shouldBe(Condition.editable);
        fromInput.clear();
        fromInput.sendKeys(String.valueOf(values[0]));
        fromInput.pressEnter();

        var toInput = filterElement.$("div[id$=':between-dates-panel-to']").$("input[id$=':to-date_input']")
            .shouldBe(Condition.editable);
        toInput.clear();
        toInput.sendKeys(String.valueOf(values[1]));
        toInput.pressEnter();
        break;
      case DATE:
        var dateInput = filterElement.$$(".date-picker-panel input")
            .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(values.length));
        for (int i = 0; i < dateInput.size(); i++) {
          dateInput.get(i).clear();
          dateInput.get(i).sendKeys(String.valueOf(values[i]));
        }
        break;
      case CREATOR_TYPE:
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
        break;
      case CATEGORY_TYPE:
        var catogoryField = filterElement.$("div[id$=':category-list-panel']").$("input").shouldBe(appear);
        for (int i = 0; i < values.length; i++) {
          catogoryField.clear();
          catogoryField.sendKeys(String.valueOf(values[i]));
          catogoryField.pressEnter();
        }
        break;
      default:
        break;
    }
  }

  private static SelenideElement getValueOfCheckBox(String value) {
    return $("div.ui-selectcheckboxmenu-items-wrapper").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }

  protected static Condition getClickableCondition() {
    return and("should be clickable", visible, exist);
  }

  private static SelenideElement getCloseCheckBox() {
    return $("div.ui-selectcheckboxmenu-panel").shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-selectcheckboxmenu-close");
  }
}

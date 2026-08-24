package com.axonivy.portal.selenium.page.component;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.page.TemplatePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class UserSelectionComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='form:assign-selected-user-btn']";
  }

  public SelenideElement getNormalUserSelectionComponent() {
    return $("[id='user-by-role-component']");
  }

  public SelenideElement getFloatingLabelUserSelectionComponent() {
    return $("[id='floating-label-component']");
  }

  public SelenideElement getAjaxEventUserSelectionComponent() {
    return $("[id='item-select-with-ajax-event-component']");
  }

  public String selectFirstItemForNormalUserSelectionComponent(String keyword) {
    SelenideElement userElement = selectUserComponent("user-by-role-autocomplete", keyword);
    userElement.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("span[id$='user-by-role-autocomplete_panel']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    return userElement.getCssValue("data-item-value");
  }

  public String getNormalUserSelection() {
    return getNormalUserSelectionElement().getCssValue("value");
  }

  public SelenideElement getNormalUserSelectionElement() {
    return $("input[id$='user-by-role-autocomplete_input']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String selectFirstItemForFloatingLabelUserSelectionComponent(String keyword) {
    SelenideElement userElement = selectUserComponent("all-user-autocomplete", keyword);
    userElement.shouldBe(getClickableCondition()).click();
    $("span[id$='all-user-autocomplete_panel']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    return userElement.getCssValue("data-item-value");
  }

  public String getFloatingLabelUserSelection() {
    return $("input[id$='all-user-autocomplete_input']").getCssValue("value");
  }

  public SelenideElement getFloatingLabelUserSelectionElem() {
    return $("input[id$='all-user-autocomplete_input']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String selectFirstItemForAjaxEventUserSelectionComponent(String keyword) {
    SelenideElement userElement = selectUserComponent("item-select-event-for-user-selection", keyword);
    userElement.shouldBe(getClickableCondition()).click();
    $("[id='form:item-select-event-component:item-select-event-for-user-selection-message_info-detail']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return userElement.getCssValue("data-item-value");
  }

  public String getAjaxEventUserSelection() {
    return $("input[id$='item-select-event-for-user-selection_input']").getCssValue("value");
  }

  public void openSelectionPanelForAjaxEventUserSelectionComponent(String keyword) {
    SelenideElement input = $("input[id$='item-select-event-for-user-selection_input']");
    input.clear();
    typeIntoAutoComplete(input, keyword);
    $("span[id$='item-select-event-for-user-selection_panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).hover();
  }

  private SelenideElement selectUserComponent(String componentId, String keyword) {
    SelenideElement input = $("input[id$='" + componentId + "_input']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    input.clear();
    typeIntoAutoComplete(input, keyword);
    ElementsCollection selectionItems = $("span[id$='" + componentId + "_panel']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).findAll(".ui-autocomplete-item");
    return selectionItems.get(0);
  }

  private void typeIntoAutoComplete(SelenideElement input, String text) {
    Selenide.executeJavaScript(
        "var kd=new KeyboardEvent('keydown',{key:'a',bubbles:true});arguments[0].dispatchEvent(kd);"
            + "arguments[0].value=arguments[1];"
            + "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));",
        input, text);
  }

}

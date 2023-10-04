package com.axonivy.portal.selenium.page.component;

import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.page.TemplatePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class RoleSelectionComponentPage extends TemplatePage{

  @Override
  protected String getLoadedLocator() {
    return "[id='form:assign-selected-role-btn']";
  }
  
  public SelenideElement getDefaultRoleSelectionComponent() {
    return $("[id='default-role-selection-component']");
  }
  
  public SelenideElement getFloatingLabelAndExcludeRoleSelectionComponent() {
    return $("[id='floating-label-and-exclude-role-component']");
  }
  
  public SelenideElement getRoleFromDefinedRoleSelectionComponent() {
    return $("[id='role-from-defined-role-component']");
  }
  
  public SelenideElement getAjaxEventRoleSelectionComponent() {
    return $("[id='item-select-with-ajax-event-component']");
  }
  
  @SuppressWarnings("deprecation")
  public String selectFirstItemForDefaultRoleSelectionComponent(String keyword) {
    SelenideElement roleElement = selectRoleSelection("default-role-autocomplete", keyword);
    roleElement.shouldBe(getClickableCondition()).click();
    $("span[id$='default-role-autocomplete_panel']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    return roleElement.getCssValue("data-item-value");
  }

  @SuppressWarnings("deprecation")
  public String selectFirstItemForFloatingLabelAndExcludeRoleSelectionComponent(String keyword) {
    SelenideElement roleElement = selectRoleSelection("floating-label-and-exclude-role-autocomplete", keyword);
    roleElement.shouldBe(getClickableCondition()).click();
    $("span[id$='floating-label-and-exclude-role-autocomplete_panel']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    return roleElement.getCssValue("data-item-value");
  }
  
  @SuppressWarnings("deprecation")
  public String selectFirstItemForRoleFromDefinedRoleSelectionComponent(String keyword) {
    SelenideElement roleElement = selectRoleSelection("role-from-defined-role-autocomplete", keyword);
    roleElement.shouldBe(getClickableCondition()).click();
    $("span[id$='role-from-defined-role-autocomplete_panel']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    return roleElement.getCssValue("data-item-value");
  }
  
  @SuppressWarnings("deprecation")
  public String selectFirstItemForAjaxEventRoleSelectionComponent(String keyword) {
    SelenideElement roleElement = selectRoleSelection("item-select-event-for-role-selection", keyword);
    roleElement.shouldBe(getClickableCondition()).click();
    $("[id$='item-select-event-for-role-selection-message_info-detail']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return roleElement.getCssValue("data-item-value");
  }
  
  public void openSelectionPanelForAjaxEventRoleSelectionComponent(String keyword) {
    $("input[id$='item-select-event-for-role-selection_input']").clear();
    $("input[id$='item-select-event-for-role-selection_input']").sendKeys(keyword);
    $("span[id$='item-select-event-for-role-selection_panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  private SelenideElement selectRoleSelection(String componentId, String keyword) {
    $("input[id$='"+componentId+"_input']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).clear();
    $("input[id$='"+componentId+"_input']").sendKeys(keyword);
    ElementsCollection selectionItems = $("span[id$='"+componentId+"_panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).findAll(".ui-autocomplete-item");
    return selectionItems.get(0);
  }
}

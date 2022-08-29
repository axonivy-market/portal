package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RoleSelectionComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('form:assign-selected-role-btn')";
  }

  public WebElement getDefaultRoleSelectionComponent() {
    return findElementById("default-role-selection-component");
  }
  
  public WebElement getFloatingLabelAndExcludeRoleSelectionComponent() {
    return findElementById("floating-label-and-exclude-role-component");
  }
  
  public WebElement getRoleFromDefinedRoleSelectionComponent() {
    return findElementById("role-from-defined-role-component");
  }
  
  public WebElement getAjaxEventRoleSelectionComponent() {
    return findElementById("item-select-with-ajax-event-component");
  }
  
  public String selectFirstItemForDefaultRoleSelectionComponent(String keyword) {
    WebElement roleElement = selectRoleSelection("default-role-autocomplete", keyword);
    click(roleElement);
    waitForElementDisplayed(By.cssSelector("span[id$='default-role-autocomplete_panel']"), false);
    return roleElement.getCssValue("data-item-value");
  }
  
  public String getDefaultRoleSelection() {
    return findElementByCssSelector("input[id$='default-role-autocomplete_hinput']").getCssValue("value");
  }
  
  public String selectFirstItemForRoleFromDefinedRoleSelectionComponent(String keyword) {
    WebElement roleElement = selectRoleSelection("role-from-defined-role-autocomplete", keyword);
    click(roleElement);
    waitForElementDisplayed(By.cssSelector("span[id$='role-from-defined-role-autocomplete_panel']"), false);
    return roleElement.getCssValue("data-item-value");
  }
  
  public String getRoleFromDefinedRoleSelection() {
    return findElementByCssSelector("input[id$='role-from-defined-role-autocomplete_hinput']").getCssValue("value");
  }
  
  public String selectFirstItemForFloatingLabelAndExcludeRoleSelectionComponent(String keyword) {
    WebElement roleElement = selectRoleSelection("floating-label-and-exclude-role-autocomplete", keyword);
    click(roleElement);
    waitForElementDisplayed(By.cssSelector("span[id$='floating-label-and-exclude-role-autocomplete_panel']"), false);
    return roleElement.getCssValue("data-item-value");
  }
  
  public String getFloatingLabelAndExcludeRoleSelection() {
    return findElementByCssSelector("input[id$='floating-label-and-exclude-role-autocomplete_hinput']").getCssValue("value");
  }
  
  public String selectFirstItemForAjaxEventRoleSelectionComponent(String keyword) {
    WebElement roleElement = selectRoleSelection("item-select-event-for-role-selection", keyword);
    click(roleElement);
    waitForElementDisplayed(By.cssSelector("[id$='item-select-event-for-role-selection-message_info-detail']"), true);
    return roleElement.getCssValue("data-item-value");
  }
  
  public String getAjaxEventRoleSelection() {
    return findElementByCssSelector("input[id$='item-select-event-for-role-selection_hinput']").getCssValue("value");
  }
  
  public void openSelectionPanelForAjaxEventRoleSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='item-select-event-for-role-selection_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='item-select-event-for-role-selection_panel']"), true);
  }
  
  private WebElement selectRoleSelection(String componentId, String keyword) {
    type(By.cssSelector("input[id$='"+componentId+"_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='"+componentId+"_panel']"), true);
    List<WebElement> selectionItems = findElementByCssSelector("span[id$='"+componentId+"_panel']").findElements(By.className("ui-autocomplete-item"));
    return selectionItems.get(0);
  }
}

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
  
  public void selectFirstItemForDefaultRoleSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='default-role-autocomplete_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='default-role-autocomplete_panel']"), true);
    List<WebElement> selectionItems = findElementByCssSelector("span[id$='default-role-autocomplete_panel']").findElements(By.className("ui-autocomplete-item"));
    selectionItems.get(0).click();
    waitForElementDisplayed(By.cssSelector("span[id$='default-role-autocomplete_panel']"), false);
  }
  
  public void selectFirstItemForRoleFromDefinedRoleSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='role-from-defined-role-autocomplete_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='role-from-defined-role-autocomplete_panel']"), true);
    List<WebElement> selectionItems = findElementByCssSelector("span[id$='role-from-defined-role-autocomplete_panel']").findElements(By.className("ui-autocomplete-item"));
    selectionItems.get(0).click();
    waitForElementDisplayed(By.cssSelector("span[id$='role-from-defined-role-autocomplete_panel']"), false);
  }
  
  public void selectFirstItemForFloatingLabelAndExcludeRoleSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='floating-label-and-exclude-role-autocomplete_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='floating-label-and-exclude-role-autocomplete_panel']"), true);
    List<WebElement> selectionItems = findElementByCssSelector("span[id$='floating-label-and-exclude-role-autocomplete_panel']").findElements(By.className("ui-autocomplete-item"));
    selectionItems.get(0).click();
    waitForElementDisplayed(By.cssSelector("span[id$='floating-label-and-exclude-role-autocomplete_panel']"), false);
  }
  
  public void selectFirstItemForAjaxEventRoleSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='item-select-event-for-role-selection_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='item-select-event-for-role-selection_panel']"), true);
    List<WebElement> selectionItems = findElementByCssSelector("span[id$='item-select-event-for-role-selection_panel']").findElements(By.className("ui-autocomplete-item"));
    selectionItems.get(0).click();
    waitForElementDisplayed(By.id("form:item-select-event-component:item-select-event-for-role-selection-message_info-detail"), true);
  }
  
  public void openSelectionPanelForAjaxEventRoleSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='item-select-event-for-role-selection_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='item-select-event-for-role-selection_panel']"), true);
  }
}

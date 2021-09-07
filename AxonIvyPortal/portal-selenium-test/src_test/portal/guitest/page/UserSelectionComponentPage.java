package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserSelectionComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('form:assign-selected-user-btn')";
  }

  public WebElement getNormalUserSelectionComponent() {
    return findElementById("user-by-role-component");
  }
  
  public WebElement getFloatingLabelUserSelectionComponent() {
    return findElementById("floating-label-component");
  }
  
  public WebElement getAjaxEventUserSelectionComponent() {
    return findElementById("item-select-with-ajax-event-component");
  }
  
  public void selectFirstItemForNormalUserSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='user-by-role-autocomplete_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='user-by-role-autocomplete_panel']"), true);
    List<WebElement> selectionItems = findElementByCssSelector("span[id$='user-by-role-autocomplete_panel']").findElements(By.className("ui-autocomplete-item"));
    click(selectionItems.get(0));
    waitForElementDisplayed(By.cssSelector("span[id$='user-by-role-autocomplete_panel']"), false);
  }
  
  public void selectFirstItemForFloatingLabelUserSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='all-user-autocomplete_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='all-user-autocomplete_panel']"), true);
    List<WebElement> selectionItems = findElementByCssSelector("span[id$='all-user-autocomplete_panel']").findElements(By.className("ui-autocomplete-item"));
    click(selectionItems.get(0));
    waitForElementDisplayed(By.cssSelector("span[id$='all-user-autocomplete_panel']"), false);
  }
  
  public void selectFirstItemForAjaxEventUserSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='item-select-event-for-user-selection_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='item-select-event-for-user-selection_panel']"), true);
    List<WebElement> selectionItems = findElementByCssSelector("span[id$='item-select-event-for-user-selection_panel']").findElements(By.className("ui-autocomplete-item"));
    click(selectionItems.get(0));
    waitForElementDisplayed(By.id("form:item-select-event-component:item-select-event-for-user-selection-message_info-detail"), true);
  }
  
  public void openSelectionPanelForAjaxEventUserSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='item-select-event-for-user-selection_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='item-select-event-for-user-selection_panel']"), true);
  }
}

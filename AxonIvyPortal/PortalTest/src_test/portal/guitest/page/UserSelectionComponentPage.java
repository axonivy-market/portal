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
    click(selectUserComponent("user-by-role-autocomplete", keyword));
    waitForElementDisplayed(By.cssSelector("span[id$='user-by-role-autocomplete_panel']"), false);
  }
  
  public String getFirstItemForNormalUserSelectionComponent(String keyword) {
    return selectUserComponent("user-by-role-autocomplete", keyword).getCssValue("data-item-value");
  }
  
  public String getNormalUserSelection() {
    return findElementByCssSelector("input[id$='user-by-role-autocomplete_hinput']").getCssValue("value");
  }
  
  public void selectFirstItemForFloatingLabelUserSelectionComponent(String keyword) {   
    click(selectUserComponent("all-user-autocomplete", keyword));
    waitForElementDisplayed(By.cssSelector("span[id$='all-user-autocomplete_panel']"), false);
  }
  
  public String getFirstItemForFloatingLabelUserSelectionComponent(String keyword) {   
    return selectUserComponent("all-user-autocomplete", keyword).getCssValue("data-item-value");
  }
  
  public String getFloatingLabelUserSelection() {
    return findElementByCssSelector("input[id$='all-user-autocomplete_hinput']").getCssValue("value");
  }
  
  public void selectFirstItemForAjaxEventUserSelectionComponent(String keyword) {
    click(selectUserComponent("item-select-event-for-user-selection", keyword));
    waitForElementDisplayed(By.id("form:item-select-event-component:item-select-event-for-user-selection-message_info-detail"), true);
  }
  
  public String getFirstItemForAjaxEventUserSelectionComponent(String keyword) {
    return selectUserComponent("item-select-event-for-user-selection", keyword).getCssValue("data-item-value");
  }
  
  public String getAjaxEventUserSelection() {
    return findElementByCssSelector("input[id$='item-select-event-for-user-selection_hinput']").getCssValue("value");
  }
  
  public void openSelectionPanelForAjaxEventUserSelectionComponent(String keyword) {
    type(By.cssSelector("input[id$='item-select-event-for-user-selection_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='item-select-event-for-user-selection_panel']"), true);
  }
  
  private WebElement selectUserComponent(String componentId, String keyword) {
    waitForElementDisplayed(By.cssSelector("input[id$='"+componentId+"_input']"), true);
    type(By.cssSelector("input[id$='"+componentId+"_input']"), keyword);
    waitForElementDisplayed(By.cssSelector("span[id$='"+componentId+"_panel']"), true);
    List<WebElement> selectionItems = findElementByCssSelector("span[id$='"+componentId+"_panel']").findElements(By.className("ui-autocomplete-item"));
    return selectionItems.get(0);
  }
}

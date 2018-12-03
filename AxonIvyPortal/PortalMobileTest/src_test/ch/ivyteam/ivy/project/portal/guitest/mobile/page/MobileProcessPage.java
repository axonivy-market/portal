package ch.ivyteam.ivy.project.portal.guitest.mobile.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileProcessPage extends MobileTemplatePage{
  private static final String DICTONARY_PROCESS_GROUP_SELECTION_PANEL_ID = "process-widget:dictonary-process-group-selection_items";
  private static final String PROCESS_NAME_SEARCH_INPUT_ID = "process-widget:process-search:non-ajax-keyword-filter";
  private static final String DICTIONARY_GROUP_ID = "process-widget:dictonary-process-group-selection";
  private static final String PROCESS_ITEM_CSS_SELECTOR = 
      "[id$=':%d:none-express-processes:%d:process-item-form:process-item']"; //First input: Index of field set, Second input: Index of process item in that field set
  @Override
  protected String getLoadedLocator() {
    return "id('" + PROCESS_NAME_SEARCH_INPUT_ID + "')";
  }
  
  public void filterProcess(String keyword) {
    WebElement keywordFilter = findElementById(PROCESS_NAME_SEARCH_INPUT_ID);
    keywordFilter.clear();
    keywordFilter.sendKeys(keyword);
    sleep(Duration.ofMillis(500));
  }
  
  public MobileTaskPage startFirstProcess() {
    click(findElementByCssSelector(String.format(PROCESS_ITEM_CSS_SELECTOR, 1, 1)));
    return new MobileTaskPage();
  }
  
  public void selectGroup(String group) {
    click(By.id(DICTIONARY_GROUP_ID));
    waitForElementDisplayed(By.id(DICTONARY_PROCESS_GROUP_SELECTION_PANEL_ID), true, DEFAULT_TIMEOUT);
    WebElement selectGroupItemPanel = findElementById(DICTONARY_PROCESS_GROUP_SELECTION_PANEL_ID);
    List<WebElement> options = selectGroupItemPanel.findElements(By.tagName("li"));
    for(WebElement option : options) {
      if(option.getText().equalsIgnoreCase(group)) {
        option.click();
        break;
      }
    }
    sleep(Duration.ofMillis(500));
  }
  
  public List<WebElement> getAllProcessUnderGroup(String group) {
    WebElement fieldSetGroup = findElementByClassName("js-process-group-" + group);
    return fieldSetGroup.findElements(By.cssSelector("span.js-process-start-list-item-name"));
  }
  
}

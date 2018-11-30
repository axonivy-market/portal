package ch.ivyteam.ivy.project.portal.guitest.mobile.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileTaskPage extends MobileTemplatePage {
  private static final String KEYWORD_FILTER_SELECTOR =
      "input[id='task-widget:filter-form-mobile:filter-container:ajax-keyword-filter']";
  private static final String ID_END = "*[id$='";

  @Override
  protected String getLoadedLocator() {
    return "id('task-widget:filter-form-mobile:filter-container:ajax-keyword-filter')";
  }

  public int countTasks() {
    List<WebElement> taskElements = findListElementsByCssSelector("div[class*='task-start-list-item']");
    return taskElements.size();
  }

  public void filterTasksBy(String keyword) {
    WebElement keywordFilter = findElementByCssSelector(KEYWORD_FILTER_SELECTOR);
    keywordFilter.clear();
    keywordFilter.sendKeys(keyword);
    sleep(Duration.ofMillis(2000));
    waitAjaxIndicatorDisappear();
  }
  
  public void sortTaskList(String option) {
    click(By.id("task-widget:sort-form-mobile:sort-task-mobile"));
    waitForElementDisplayed(By.id("task-widget:sort-form-mobile:sort-task-mobile_items"), true, DEFAULT_TIMEOUT);
    WebElement taskSortItemPanel = findElementById("task-widget:sort-form-mobile:sort-task-mobile_items");
    List<WebElement> items = taskSortItemPanel.findElements(By.tagName("li"));
    for(WebElement item : items) {
      if(item.getText().equals(option)) {
        item.click();
        break;
      }
    }
    waitAjaxIndicatorDisappear();    
  }
  
  public String getNameOfTaskAt(int index) {
    WebElement name = findElementByCssSelector(ID_END + index + ":task-item:task-start-item-view:task-start-task-name']");
    return name.getText();
  }
}

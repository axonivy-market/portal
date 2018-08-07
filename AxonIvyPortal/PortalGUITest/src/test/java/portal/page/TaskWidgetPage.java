package portal.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class TaskWidgetPage extends TemplatePage {

  private static final String KEYWORD_FILTER_SELECTOR = "input[id='task-widget:filter-form:filter-container:ajax-keyword-filter']";

  @Override
  protected String getLoadedLocator() {
    return "id('task-widget:priority-sort')";
  }
  
  public void expand() {
    WebElement fullModeButton = findElementById("task-widget:switch-mode-command");
    fullModeButton.click();
    waitAjaxIndicatorDisappear();
    waitForElementEnabled(By.id("task-start-0-task-action-container"), true, DEFAULT_TIMEOUT);
  }

  public void openTaskDetails(int index) {
    clickOnTaskEntryInFullMode(index, true);
  }

  public void closeTaskDetails(int index) {
    clickOnTaskEntryInFullMode(index, false);
  }

  private void clickOnTaskEntryInFullMode(int index, boolean isDetailsShown) {
    WebElement taskShowHideDetailsLink =
        findElementById("task-widget:task-start:" + index + ":show-hide-task-details-command");
    taskShowHideDetailsLink.click();
    waitForElementDisplayed(By.id("task-start-" + index + "-task-details"), isDetailsShown, DEFAULT_TIMEOUT);
  }

  public boolean isTaskShowDetails(int index) {
    try {
      WebElement taskDetails = findElementById("task-start-" + index + "-task-details");
      return taskDetails.isDisplayed();
    } catch (NoSuchElementException exception) {
      return false;
    }
  }

  public TaskTemplatePage startTask(int index) {
    findElementById("task-widget:task-start:" + index + ":task-start-command").click();
    waitForElementPresent(By.id("copy-clipboard"), true);
    return new TaskTemplatePage();
  }

  public int countTasks() {
    List<WebElement> taskElements = findListElementsByCssSelector("div[class*='task-start-list-item']");
    return taskElements.size();
  }

  public void filterTasksBy(String keyword) {
    WebElement keywordFilter = findElementByCssSelector(KEYWORD_FILTER_SELECTOR);
    keywordFilter.sendKeys(keyword);
    Sleeper.sleepTight(2000);
    waitAjaxIndicatorDisappear();
  }

  public boolean isFilterDisplayed() {
    return isElementPresent(By.cssSelector(KEYWORD_FILTER_SELECTOR));
  }
}

package portal.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends TemplatePage {

  private static final String WIDGET_HEADER_MAIN_TITLE = "widget-header-main-title";

  public SearchResultPage(String type, String keyword) {
    waitForElementDisplayed(By.className(WIDGET_HEADER_MAIN_TITLE), true, DEFAULT_TIMEOUT);
    WebElement title = findElementByClassName(WIDGET_HEADER_MAIN_TITLE);
    waitUntilTextToBePresentInElement(title, type + " search results for \"" + keyword + "\"", DEFAULT_TIMEOUT);
  }

  public int countProcesses() {
    return findListElementsByClassName("process-start-list-item").size();
  }

  public int countTasks() {
    return findListElementsByClassName("task-start-list-item").size();
  }

  public int countCases() {
    return findListElementsByClassName("js-case-item").size();
  }

  public boolean isTaskItemSelected(int index) {
    return findElementById("task-widget:task-list-scroller:" + index + ":task-item:task-start").getAttribute("class").contains(
        "show-task-details-mode");
  }

  public boolean isCaseItemSelected(int index) {
    return findElementById("case-widget:case-items:" + index + ":case-item").getAttribute("class").contains(
        "mod-expanded");
  }
}

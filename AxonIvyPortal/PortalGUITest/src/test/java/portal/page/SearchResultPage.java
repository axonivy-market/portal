package portal.page;

import org.openqa.selenium.WebElement;

public class SearchResultPage extends TemplatePage {

  public SearchResultPage(String type, String keyword) {
    WebElement title = findElementByClassName("widget-header-main-title");
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
    return findElementById("task-start-" + index).getAttribute("class").contains("show-task-details-mode");
  }
  
  public boolean isCaseItemSelected(int index) {
    return findElementById("case-widget:case-items:" + index + ":case-item").getAttribute("class").contains("is-selected");
  }
}

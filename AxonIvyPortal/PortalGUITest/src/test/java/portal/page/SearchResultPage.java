package portal.page;

import org.openqa.selenium.By;

public class SearchResultPage extends TemplatePage {

  public SearchResultPage(String type) {
    waitForElementDisplayed(By.className(type + "-widget"), true, DEFAULT_TIMEOUT);
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

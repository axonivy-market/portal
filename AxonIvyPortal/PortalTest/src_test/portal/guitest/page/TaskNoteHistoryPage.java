package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TaskNoteHistoryPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('task-note-history')";
  }

  public boolean isShowWorkflowEventsLinkDisplayed() {
    return isElementDisplayedById("form:show-workflow-event");
  }

  public String openWorkflowEventDialog() {
    clickOnShowWorkflowEventLink();
    return getDataOfWorkflowEventsTable();
  }

  private String getDataOfWorkflowEventsTable() {
    WebElement eventTable = findElementByCssSelector("tbody[id$='events-table_data']");
    List<WebElement> cells = eventTable.findElements(By.cssSelector("td"));
    return String.join(",", cells.stream().map(WebElement::getText).collect(Collectors.toList()));
  }

  public void clickOnShowWorkflowEventLink() {
    click(findElementByCssSelector("a[id$=':show-workflow-event']"));
    waitForElementDisplayed(By.cssSelector("div[id$='events-table']"), true);
  }

  public WebElement getWorkflowEventsTable() {
    waitForElementDisplayed(By.cssSelector("th[id*='events-table:']"), true);
    return findElementByCssSelector("div[id$='workflow-events-dialog']");
  }

}

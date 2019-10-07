package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TaskDetailsPage extends TemplatePage {

  private WebElement taskDetailsItem;

  @Override
  protected String getLoadedLocator() {
    return "id('task-detail-template:task-detail-container')";
  }

  public TaskDetailsPage() {}

  public String getCreatedOnDateText() {
    WebElement findElement = taskDetailsItem
        .findElement(By.cssSelector("#task-data > table > tbody > tr:nth-child(8) > td.task-details-data-2nd-column"));
    return findElement.getText();
  }

  public List<String> getTaskNoteAuthors() {
    WebElement taskHistoriesDiv = findElementById("task-note");
    List<WebElement> noteAuthorElements = taskHistoriesDiv.findElements(By.cssSelector("span[id$=user]"));
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }
}

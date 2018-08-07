package portal.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;


public class CaseDetailsPage extends TemplatePage {

  private static final String HISTORY_LIST_CSS_SELECTOR = "div[id$='case-histories'] .grid-item-content-list-item";
  private WebElement caseItem;

  @Override
  protected String getLoadedLocator() {
    return "id('case-widget-form')";
  }

  public CaseDetailsPage(WebElement caseItem) {
    this.caseItem = caseItem;
  }

  public String getProcessModelName() {
    return caseItem.findElement(By.cssSelector("span[id$='process-model-name']")).getText();
  }

  public int getNumberOfHistory() {
    return caseItem.findElements(By.cssSelector(HISTORY_LIST_CSS_SELECTOR)).size();
  }

  public void addNote(String content) {
    caseItem.findElement(By.cssSelector("a[id$='add-note-command']")).click();
    Sleeper.sleepTight(1000);
    WebElement addNoteDialog = findElementByCssSelector("div.ui-dialog[aria-hidden='false']");
    addNoteDialog.findElement(By.cssSelector("textarea[id$='note-content']")).sendKeys(content);
    addNoteDialog.findElement(By.cssSelector("button[id$='save-add-note-command']")).click();
    waitAjaxIndicatorDisappear();

  }

  public String getLatestHistory() {
    List<WebElement> histories = caseItem.findElements(By.cssSelector(HISTORY_LIST_CSS_SELECTOR));
    if (!histories.isEmpty()) {
      WebElement history = histories.get(0);
      List<WebElement> notes = history.findElements(By.cssSelector(".history-note-content"));
      if (notes.size() == 1) {
        return notes.get(0).getText();
      } else {
        return history.getText();
      }
    } else {
      return "";
    }
  }
}

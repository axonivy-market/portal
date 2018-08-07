package portal.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;


public class CaseDetailsPage extends TemplatePage {
  private static final String DOCUMENT_COMPONENT_ID = "case-widget:case-items:0:case-item:document";
  private static final String HISTORY_COMPONENT_ID = "case-widget:case-items:0:case-item:history";
  private static final String RELATED_TASKS_COMPONENT_ID = "case-widget:case-items:0:case-item:related-tasks";
  private static final String HISTORY_LIST_CSS_SELECTOR = "div[id$='case-histories'] .grid-item-content-list-item";
  private static final String GENERAL_INFORMATION_COMPONENT_ID = "case-widget:case-items:0:case-item:general-information";
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
  
  private WebElement getGeneralInformationComponent() {
    return findChildElementById(caseItem, GENERAL_INFORMATION_COMPONENT_ID);
  }

  public boolean isGeneralInformationComponentPresented() {
    return getGeneralInformationComponent().isDisplayed();
  }
  
  private WebElement getRelatedTasksComponent(){
    return findChildElementById(caseItem, RELATED_TASKS_COMPONENT_ID);
  }

  public boolean isRelatedTasksComponentPresented() {
    return getRelatedTasksComponent().isDisplayed();
  }

  public boolean isHistoryComponentPresented() {
    WebElement historyComponent = getHistoryComponent();
    return historyComponent.isDisplayed();
  }

  private WebElement getHistoryComponent() {
    WebElement historyComponent = findChildElementById(caseItem, HISTORY_COMPONENT_ID);
    return historyComponent;
  }

  public boolean isDocumentComponentPresented() {
    WebElement documentComponent = getDocumentComponent();
    return documentComponent.isDisplayed();
  }

  private WebElement getDocumentComponent() {
    WebElement documentComponent = findChildElementById(caseItem, DOCUMENT_COMPONENT_ID);
    return documentComponent;
  }
}

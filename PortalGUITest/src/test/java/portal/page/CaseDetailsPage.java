package portal.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class CaseDetailsPage extends TemplatePage {
  private static final String DOCUMENT_COMPONENT_ID = "case-widget:case-list-scroller:0:case-item:document";
  private static final String HISTORY_COMPONENT_ID = "case-widget:case-list-scroller:0:case-item:history:case-histories";
  private static final String RELATED_TASKS_COMPONENT_ID = "case-widget:case-list-scroller:0:case-item:related-tasks";
  private static final String HISTORY_LIST_CSS_SELECTOR = "div[id$='case-histories'] .author";
  private static final String GENERAL_INFORMATION_COMPONENT_ID =
      "case-widget:case-list-scroller:0:case-item:general-information";
  private static final String HISTORY_NOTE_ID = "case-widget:case-list-scroller:0:case-item:history:histories:0:note-content";
  private static final String AUTHOR_USER_ID = "case-widget:case-list-scroller:0:case-item:history:histories:0:user-full-name:user";
  private static final String NOTE_CONTENT_ID = "case-widget:case-list-scroller:0:case-item:history:histories:0:note-content";
  private static final String VIEW_NOTE_DIALOG_ID = "case-widget:case-list-scroller:0:case-item:history:view-note-dialog";
  private WebElement caseItem;

  @Override
  protected String getLoadedLocator() {
    return "id('case-widget:case-widget-form')";
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

  public int countTasks() {
    return caseItem.findElements(By.cssSelector("a[id$='task-name']")).size();
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
    List<WebElement> histories = caseItem.findElements(By.id(HISTORY_COMPONENT_ID));
    if (!histories.isEmpty()) {
      WebElement history = histories.get(0);
      List<WebElement> notes = history.findElements(By.id(HISTORY_NOTE_ID));
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

  private WebElement getRelatedTasksComponent() {
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
  
  public TaskWidgetPage openTasksOfCasePage(int index) {
    click(By.cssSelector("a[id$='tasks:" + index + ":task-name']"));
    return new TaskWidgetPage();
  }

  public String getHistoryAuthor() {
    return findElementById(AUTHOR_USER_ID).getText();
  }

  public void clickViewNote() {
    findElementById(NOTE_CONTENT_ID).click();
    Sleeper.sleepTight(1000);
    getViewNoteDialog();
  }

  public boolean isViewNoteDialogPresented() {
    return getViewNoteDialog().isDisplayed();
  }
  
  private WebElement getViewNoteDialog() {
    return findElementById(VIEW_NOTE_DIALOG_ID);
  }

  public void changeCaseName(String newCaseName, int caseIndex) {
    String caseNameInplaceId = String.format("case-widget:case-list-scroller:%d:case-item:case-name-edit-inplace_display", caseIndex);
    WebElement caseNameInplace = findElementById(caseNameInplaceId);
    caseNameInplace.click();              
    String caseNameInputId = String.format("case-widget:case-list-scroller:%d:case-item:case-name-input", caseIndex);
    WebElement caseNameInput = findElementById(caseNameInputId);
    waitForElementDisplayed(caseNameInput, true);
    caseNameInput.clear();
    caseNameInput.sendKeys(newCaseName);
    WebElement editor = findElementById(String.format("case-widget:case-list-scroller:%d:case-item:case-name-edit-inplace_editor", caseIndex));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitForPageLoaded();
  }

  public String getNameOfCaseAt(int caseIndex) {
    String caseNameId = String.format("case-widget:case-list-scroller:%d:case-item:case-name-edit-inplace_display", caseIndex);
    waitForElementDisplayed(By.id(caseNameId), true);
    WebElement taskName = findElementById(caseNameId);
    return taskName.getText();
  }

  public boolean isCaseNameChangeComponentPresented(int caseIndex) {
    return isElementPresent(By.id(String.format("case-widget:case-list-scroller:%d:case-item:case-name-input", caseIndex)));
  }
  
  public void changeCaseDescription(String newCaseDescription, int caseIndex) {
    String caseNameInplaceId = String.format("case-widget:case-list-scroller:%d:case-item:case-description-inplace_display", caseIndex);
    WebElement caseNameInplace = findElementById(caseNameInplaceId);
    caseNameInplace.click();              
    String caseNameInputId = String.format("case-widget:case-list-scroller:%d:case-item:case-description-input", caseIndex);
    WebElement caseNameInput = findElementById(caseNameInputId);
    waitForElementDisplayed(caseNameInput, true);
    caseNameInput.clear();
    caseNameInput.sendKeys(newCaseDescription);
    WebElement editor = findElementById(String.format("case-widget:case-list-scroller:%d:case-item:case-description-inplace_editor", caseIndex));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitForPageLoaded();
  }

  public String getDescriptionOfCaseAt(int caseIndex) {
    String caseDescriptionId = String.format("case-widget:case-list-scroller:%d:case-item:case-description-inplace_display", caseIndex);
    waitForElementDisplayed(By.id(caseDescriptionId), true);
    WebElement caseDescription = findElementById(caseDescriptionId);
    return caseDescription.getText();
  }

  public boolean isCaseDescriptionChangeComponentPresented(int caseIndex) {
    return isElementPresent(By.id(String.format("case-widget:case-list-scroller:%d:case-item:case-description-input", caseIndex)));
  }
}

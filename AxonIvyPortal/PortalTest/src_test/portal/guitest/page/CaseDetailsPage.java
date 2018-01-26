package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class CaseDetailsPage extends TemplatePage {
  private static final String DOCUMENT_COMPONENT_ID = "case-widget:case-list-scroller:0:case-item:document";
  private static final String CASE_ICONS_CONTAINER_COMPONENT_CSS_SELECTOR = "span[id$='responsive-handle-container']";
  private static final String HISTORY_COMPONENT_ID =
      "case-widget:case-list-scroller:0:case-item:history:case-histories";
  private static final String RELATED_TASKS_COMPONENT_ID = "case-widget:case-list-scroller:0:case-item:related-tasks";
  private static final String HISTORY_LIST_CSS_SELECTOR = "div[id$='case-histories'] .author";
  private static final String GENERAL_INFORMATION_COMPONENT_ID =
      "case-widget:case-list-scroller:0:case-item:general-information";
  private static final String HISTORY_NOTE_CONTENT_CSS_SELECTOR = "a[id$='note-content']";
  private static final String ADDITIONAL_CASE_DETAILS_URL_CSS_SELECTOR = "a[id$='additional-case-details-link']";
  private static final String AUTHOR_USER_CSS_SELECTOR = "span[id$='user-full-name:user']";
  private static final String VIEW_NOTE_DIALOG_ID =
      "case-widget:case-list-scroller:0:case-item:history:view-note-dialog";
  private WebElement caseItem;

  @Override
  protected String getLoadedLocator() {
    return "id('case-widget:case-list')";
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

  public int countRelatedTasks() {
    return caseItem.findElement(By.cssSelector("div[id$='related-tasks']")).findElements(By.cssSelector("a[id$='task-name']")).size();
  }

  public int countTechnicalCases() {
    return caseItem.findElements(By.cssSelector("div[id$=':technicalCase']")).size();
  }

  public void addNote(String content) {
    onClickHistoryIcon();
    caseItem.findElement(By.cssSelector("a[id$='add-note-command']")).click();
    waitAjaxIndicatorDisappear();
    String engineUrl = System.getProperty("engineUrl");
    if (ENGINE_URL_LOCAL.equals(engineUrl)) {
        Sleeper.sleepTight(2000);
    }
    WebElement addNoteDialog = findElementByCssSelector("div.ui-dialog[aria-hidden='false']");
    waitForElementDisplayed(addNoteDialog, true);
    addNoteDialog.findElement(By.cssSelector("textarea[id$='note-content']")).sendKeys(content);
    addNoteDialog.findElement(By.cssSelector("button[id$='save-add-note-command']")).click();
    waitAjaxIndicatorDisappear();
  }
  
  public void showNoteHistory() {
    click(caseItem.findElement(By.cssSelector("a[id$='show-more-note-link']")));
  }

  public String getLatestHistoryContent() {
    WebElement historyComponent = caseItem.findElement(By.id(HISTORY_COMPONENT_ID));
    if (historyComponent != null) {
      List<WebElement> noteContents = historyComponent.findElements(By.cssSelector(HISTORY_NOTE_CONTENT_CSS_SELECTOR));
      if (noteContents.size() >= 1) {
        return noteContents.get(0).getText();
      }
    }
    return "";
  }
  
  public void openAdditionalCaseDetailsPage() {
    click(caseItem.findElement(By.cssSelector(ADDITIONAL_CASE_DETAILS_URL_CSS_SELECTOR)));
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
    return findElementByCssSelector(AUTHOR_USER_CSS_SELECTOR).getText();
  }

  public void clickViewNote() {
    findElementByCssSelector(HISTORY_NOTE_CONTENT_CSS_SELECTOR).click();
    waitAjaxIndicatorDisappear();
    WebElement noteDialog = getViewNoteDialog();
    waitForElementDisplayed(noteDialog, true);
  }

  public boolean isViewNoteDialogPresented() {
    return getViewNoteDialog().isDisplayed();
  }

  private WebElement getViewNoteDialog() {
    return findElementById(VIEW_NOTE_DIALOG_ID);
  }

  public void changeCaseName(String newCaseName, int caseIndex) {
    onClickNameInplace(caseIndex);
    onChangeNameInput(newCaseName, caseIndex);
    onSubmitNameInplaceEditor(caseIndex);
    waitForPageLoaded();
  }

  public void changeCaseDescription(String newDescription, int caseIndex) {
    onClickDescriptionEditIcon();
    onClickDescriptionInplace(caseIndex);
    onChangeDescriptionInput(newDescription, caseIndex);
    onSubmitDescriptionInplaceEditor(caseIndex);
    waitForPageLoaded();
  }

  public String getNameOfCaseAt(int caseIndex) {
    WebElement taskName =
        findElementById(String.format(
            "case-widget:case-list-scroller:%d:case-item:case-header:case-name-form:case-name-edit-inplace_display", caseIndex));
    waitForElementDisplayed(taskName, true);
    return taskName.getText();
  }

  public boolean isCaseNameChangeComponentPresented(int caseIndex) {
    return isElementPresent(By.id(String.format(
        "case-widget:case-list-scroller:%d:case-item:case-header:case-name-form:case-name-input", caseIndex)));
  }

  public String getDescriptionOfCaseAt(int caseIndex) {
    WebElement caseDescription =
        findElementById(String.format("case-widget:case-list-scroller:%d:case-item:case-header:description-cell",
            caseIndex));
    waitForElementDisplayed(caseDescription, true);
    return caseDescription.getText();
  }

  public boolean isCaseDescriptionChangeComponentPresented(int caseIndex) {
    return isElementPresent(By.id(String.format("case-widget:case-list-scroller:%d:case-item:case-description-input",
        caseIndex)));
  }

  private void onSubmitDescriptionInplaceEditor(int caseIndex) {
    WebElement editor =
        findElementById(String.format(
            "case-widget:case-list-scroller:%d:case-item:description:case-description-form:case-desription-inplace_editor", caseIndex));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitAjaxIndicatorDisappear();
  }

  private void onChangeDescriptionInput(String newDescription, int caseIndex) {
    WebElement caseDescriptionInput =
        findElementById(String.format("case-widget:case-list-scroller:%d:case-item:description:case-description-form:case-description-input",
            caseIndex));
    waitForElementDisplayed(caseDescriptionInput, true);
    caseDescriptionInput.clear();
    caseDescriptionInput.sendKeys(newDescription);
  }

  private void onClickDescriptionInplace(int caseIndex) {
    WebElement caseDescriptionInplace =
        findElementById(String.format(
            "case-widget:case-list-scroller:%d:case-item:description:case-description-form:case-desription-inplace_display", caseIndex));
    waitForElementDisplayed(caseDescriptionInplace, true);
    caseDescriptionInplace.click();
  }

  public void onClickDescriptionEditIcon() {
    try {
      WebElement caseIcons = caseItem.findElement(By.cssSelector(CASE_ICONS_CONTAINER_COMPONENT_CSS_SELECTOR));
      WebElement descriptionIcon = caseIcons.findElement(By.cssSelector("a[class*='fa fa-clipboard']"));
      if (descriptionIcon != null) {
        descriptionIcon.click();
      }
    } catch (Exception e) {
      return;
    }
  }

  public void onClickHistoryIcon() {
    try {
      WebElement caseIcons = caseItem.findElement(By.cssSelector(CASE_ICONS_CONTAINER_COMPONENT_CSS_SELECTOR));
      WebElement noteIcon = caseIcons.findElement(By.cssSelector("a[class*='fa fa-align-left']"));
      if (noteIcon != null) {
        noteIcon.click();
      }
    } catch (Exception e) {
      return;
    }
  }

  public void onClickDocumentIcon() {
    try {
      WebElement caseIcons = caseItem.findElement(By.cssSelector(CASE_ICONS_CONTAINER_COMPONENT_CSS_SELECTOR));
      WebElement documentIcon = caseIcons.findElement(By.cssSelector("a[class*='fa fa-file']"));
      if (documentIcon != null) {
        documentIcon.click();
      }
    } catch (Exception e) {
      return;
    }
  }

  private void onSubmitNameInplaceEditor(int caseIndex) {
    WebElement editor =
        findElementById(String.format(
            "case-widget:case-list-scroller:%d:case-item:case-header:case-name-form:case-name-edit-inplace_editor", caseIndex));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitAjaxIndicatorDisappear();
  }

  private void onChangeNameInput(String newCaseName, int caseIndex) {
    String caseNameInputId =
        String.format("case-widget:case-list-scroller:%d:case-item:case-header:case-name-form:case-name-input", caseIndex);
    WebElement caseNameInput = findElementById(caseNameInputId);
    waitForElementDisplayed(caseNameInput, true);
    caseNameInput.clear();
    caseNameInput.sendKeys(newCaseName);
  }

  private void onClickNameInplace(int caseIndex) {
    String caseNameInplaceId =
        String.format("case-widget:case-list-scroller:%d:case-item:case-header:case-name-form:case-name-edit-inplace_display",
            caseIndex);
    WebElement caseNameInplace = findElementById(caseNameInplaceId);
    caseNameInplace.click();
  }
}

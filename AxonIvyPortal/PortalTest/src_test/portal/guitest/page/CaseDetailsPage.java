package portal.guitest.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class CaseDetailsPage extends TemplatePage {
  private static final String DOCUMENT_COMPONENT_ID = "div[id='case-item-details:document']";
  private static final String CASE_ICONS_CONTAINER_COMPONENT_CSS_SELECTOR = "span[id$='responsive-handle-container']";
  private static final String HISTORY_COMPONENT_ID = "div[class*='case-history-button-container']";
  private static final String RELATED_TASKS_COMPONENT_ID = "div[id='case-item-details:related-tasks']";
  private static final String HISTORY_LIST_CSS_SELECTOR = "a[id^='case-item-details:case-histories:case-histories']";
  private static final String LATEST_HISTORY_LIST_CSS_SELECTOR =
      "a[id^='case-item-details:case-histories:case-histories:0']";
  private static final String GENERAL_INFORMATION_COMPONENT_ID = "div[class$='case-detail-section-title-container']";
  private static final String ADDITIONAL_CASE_DETAILS_URL_CSS_SELECTOR = "a[id$='additional-case-details-link']";
  private static final String AUTHOR_USER_CSS_SELECTOR = "span[class='history-fullname']";
  private static final String VIEW_NOTE_DIALOG_ID = "case-item-details:case-histories:view-note-dialog";
  private WebElement caseItem;

  @Override
  protected String getLoadedLocator() {
    return "id('case-item-details:case-detail-title-form:case-detail-name')";
  }

  public CaseDetailsPage(WebElement caseItem) {
    this.caseItem = caseItem;
  }

  public CaseDetailsPage() {
    // TODO Auto-generated constructor stub
    this.caseItem = findElementByCssSelector("#main-area-panel");
  }


  public String getCaseCategory() {
    return caseItem.findElement(By.cssSelector("span[id$='case-category']")).getText();
  }

  public int getNumberOfHistory() {
    return caseItem.findElements(By.cssSelector(HISTORY_LIST_CSS_SELECTOR)).size();
  }

  public int countRelatedTasks() {
    return caseItem.findElement(By.cssSelector("div[id$='related-tasks']"))
        .findElements(By.cssSelector("a[id$='task-name']")).size();
  }

  public int countTechnicalCases() {
    return caseItem.findElements(By.cssSelector("div[id$=':technicalCase']")).size();
  }

  public void addNote(String content) {
    onClickHistoryIcon();
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div.ui-dialog[aria-hidden='false']"), true);
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
    return caseItem.findElement(By.cssSelector(LATEST_HISTORY_LIST_CSS_SELECTOR)).getText();
  }

  public void openAdditionalCaseDetailsPage() {
    click(caseItem.findElement(By.cssSelector(ADDITIONAL_CASE_DETAILS_URL_CSS_SELECTOR)));
  }

  private WebElement getGeneralInformationComponent() {
    return findElementByCssSelector(GENERAL_INFORMATION_COMPONENT_ID);
  }

  public boolean isGeneralInformationComponentPresented() {
    return getGeneralInformationComponent().isDisplayed();
  }

  private WebElement getRelatedTasksComponent() {
    return findElementByCssSelector(RELATED_TASKS_COMPONENT_ID);
  }

  public boolean isRelatedTasksComponentPresented() {
    return getRelatedTasksComponent().isDisplayed();
  }

  public boolean isHistoryComponentPresented() {
    WebElement historyComponent = getHistoryComponent();
    return historyComponent.isDisplayed();
  }

  private WebElement getHistoryComponent() {
    WebElement historyComponent = findElementByCssSelector(HISTORY_COMPONENT_ID);
    return historyComponent;
  }

  public boolean isDocumentComponentPresented() {
    WebElement documentComponent = getDocumentComponent();
    return documentComponent.isDisplayed();
  }

  private WebElement getDocumentComponent() {
    WebElement documentComponent = findElementByCssSelector(DOCUMENT_COMPONENT_ID);
    return documentComponent;
  }

  public TaskDetailsPage openTasksOfCasePage(int index) {
    click(By.cssSelector("a[id$='tasks:" + index + ":task-name']"));
    return new TaskDetailsPage();
  }

  public String openDoneTask(int index) {
    WebElement showTaskNoteLink = caseItem.findElements(By.cssSelector("a[id$='show-task-note-link']")).get(index);
    String taskName = showTaskNoteLink.getText();
    showTaskNoteLink.click();
    return taskName;
  }

  public String getHistoryAuthor() {
    return findElementByCssSelector(AUTHOR_USER_CSS_SELECTOR).getText();
  }

  public void clickViewNote() {
    findElementByCssSelector(LATEST_HISTORY_LIST_CSS_SELECTOR).click();
    waitAjaxIndicatorDisappear();
    WebElement noteDialog = getViewNoteDialog();
    waitForElementDisplayed(noteDialog, true);
  }

  public boolean isViewNoteDialogPresented() {
    return getViewNoteDialog().isDisplayed();
  }

  public List<String> getCaseNoteAuthors() {
    WebElement caseHistoriesDiv = findElementById(HISTORY_COMPONENT_ID);
    List<WebElement> noteAuthorElements =
        caseHistoriesDiv.findElements(By.cssSelector("span[id$=user-full-name\\:user]"));
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
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

  public void changeCaseDescription(String newDescription) {
    onClickDescriptionEditIcon();
    onClickDescriptionInplace();
    onChangeDescriptionInput(newDescription);
    onSubmitDescriptionInplaceEditor();
    waitForPageLoaded();
  }

  public String getNameOfCaseAt(int caseIndex) {
    WebElement taskName = findElementById(String.format(
        "case-widget:case-list-scroller:%d:case-item:case-name-component:case-name-form:case-name-edit-inplace_display",
        caseIndex));
    waitForElementDisplayed(taskName, true);
    return taskName.getText();
  }

  public boolean isCaseNameChangeComponentPresented(int caseIndex) {
    return isElementPresent(By.id(String.format(
        "case-widget:case-list-scroller:%d:case-item:case-name-component:case-name-form:case-name-input", caseIndex)));
  }

  public String getDescriptionOfCaseAt() {
    WebElement caseDescription = findElementById("case-item-details:description:case-description-form:case-description-output");
    waitForElementDisplayed(caseDescription, true);
    return caseDescription.getText();
  }

  public boolean isCaseDescriptionChangeComponentPresented(int caseIndex) {
    return isElementPresent(By
        .id(String.format("case-widget:case-list-scroller:%d:case-item:case-body:case-description-input", caseIndex)));
  }

  private void onSubmitDescriptionInplaceEditor() {
    WebElement editor = findElementById("case-item-details:description:case-description-form:case-description-inplace_editor");
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitAjaxIndicatorDisappear();
  }

  private void onChangeDescriptionInput(String newDescription) {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   // WebElement caseDescriptionInput = findElementById("case-item-details:description:case-description-form:case-description-output");
    WebElement caseDescriptionInput = findElementByCssSelector("textarea[id='case-item-details:description:case-description-form:case-description-input']");
    waitForElementDisplayed(caseDescriptionInput, true);
    caseDescriptionInput.clear();
    caseDescriptionInput.sendKeys(newDescription);
  }

  private void onClickDescriptionInplace() {
    WebElement caseDescriptionInplace = findElementById("case-item-details:description:case-description-form:case-description-output");
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
    findElementById("case-item-details:case-histories:add-note-command").click();
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
    WebElement editor = findElementById(String.format(
        "case-widget:case-list-scroller:%d:case-item:case-name-component:case-name-form:case-name-edit-inplace_editor",
        caseIndex));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitAjaxIndicatorDisappear();
  }

  private void onChangeNameInput(String newCaseName, int caseIndex) {
    String caseNameInputId = String.format(
        "case-widget:case-list-scroller:%d:case-item:case-name-component:case-name-form:case-name-input", caseIndex);
    WebElement caseNameInput = findElementById(caseNameInputId);
    waitForElementDisplayed(caseNameInput, true);
    caseNameInput.clear();
    caseNameInput.sendKeys(newCaseName);
  }

  private void onClickNameInplace(int caseIndex) {
    String caseNameInplaceId = String.format(
        "case-widget:case-list-scroller:%d:case-item:case-name-component:case-name-form:case-name-edit-inplace_display",
        caseIndex);
    WebElement caseNameInplace = findElementById(caseNameInplaceId);
    caseNameInplace.click();
  }

  public TaskWidgetPage clickShowAllTasks() {
    caseItem.findElement(By.cssSelector("a[id$='show-more-related-tasks']")).click();
    return new TaskWidgetPage();
  }

  public void uploadDocumentWithoutError(String pathToFile) {
    openAddDocumentDialogAndUploadDocument(0, pathToFile);
    click(By.cssSelector("button[id$='case-item-details:document:document-upload-close-command']"));
  }

  public String uploadDocumentWithError(String pathToFile) {
    openAddDocumentDialogAndUploadDocument(0, pathToFile);
    WebElement errorMsg = findElementByCssSelector("div[id$='upload-messages']");
    String returnMsg = StringUtils.EMPTY;
    if (errorMsg.isDisplayed()) {
      returnMsg = errorMsg.getText();
    }
    click(By.cssSelector("button[id$='document-upload-close-command']"));
    return returnMsg;
  }

  private void openAddDocumentDialogAndUploadDocument(int index, String pathToFile) {
    findElementByCssSelector("a[id$='add-document-command']").click();
    waitForElementDisplayed(By.cssSelector("span[id$='document-upload-dialog_title']"), true);
    try {
      click(By.className("ui-fileupload-choose"));
    } catch (UnhandledAlertException e) {
      Alert alert = driver.switchTo().alert();
      alert.accept();
    }
    StringSelection ss = new StringSelection(pathToFile);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    Robot robot;
    try {
      robot = new Robot();
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
    } catch (AWTException e) {
      e.printStackTrace();
    }
    // currently haven't found solution to check when the file upload finish, we have to wait
    if (isIntegrationTestRun()) {
      Sleeper.sleepTight(10000);
    } else {
      Sleeper.sleepTight(5000);
    }
  }

  public boolean isDeleteDocumentButtonPresented() {
    return isElementDisplayed(By.cssSelector("a[id$='delete-file']"));
  }

  public String getCaseName() {
    WebElement selectedCaseNameElement =
        findElementByCssSelector("#case-item-details\\:case-detail-title-form\\:case-name-edit-inplace_display");
    return selectedCaseNameElement.getText();
  }
  public boolean isAddNoteButtonDisplayed() {
    return isElementDisplayedById("case-item-details:case-histories:add-note-command");
  }

  public boolean isShowMoreNoteButtonDisplayed() {
    return isElementDisplayedById("case-item-details:case-histories:show-more-note-link");
  }

  public boolean isShowDetailsDisplayed() {
    return isElementDisplayedById("case-item-details:show-additional-case-details-link");
  }

  public boolean isShowAllTasksDisplayed() {
    return isElementDisplayedById("case-item-details:related-tasks:show-more-related-tasks");
  }

  public boolean isAddDocumentLinkDisplayed() {
    return isElementDisplayedById("case-item-details:document:add-document-command");
  }
  
  public int countNumberOfDocument() {
    return findListElementsByCssSelector("a[id$='download']").size();
  }
  

}

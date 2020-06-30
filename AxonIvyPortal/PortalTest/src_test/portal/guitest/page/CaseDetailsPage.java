package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;

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
    return "id('case-item-details:case-detail-title-form')";
  }

  public CaseDetailsPage() {
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

  public void addNote(String content) {
    onClickHistoryIcon();
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div.ui-dialog[aria-hidden='false']"), true);
    WebElement addNoteDialog = findElementByCssSelector("div.ui-dialog[aria-hidden='false']");
    waitForElementDisplayed(addNoteDialog, true);
    addNoteDialog.findElement(By.cssSelector("textarea[id$='note-content']")).sendKeys(content);
    click(addNoteDialog.findElement(By.cssSelector("button[id$='save-add-note-command']")));
    waitAjaxIndicatorDisappear();
  }

  public void showNoteHistory() {
    click(findElementByCssSelector("a[id$='show-more-note-link']"));
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
    click(showTaskNoteLink);
    return taskName;
  }

  public String getHistoryAuthor() {
    return findElementByCssSelector(AUTHOR_USER_CSS_SELECTOR).getText();
  }

  public void clickViewNote() {
    clickByCssSelector(LATEST_HISTORY_LIST_CSS_SELECTOR);
    waitAjaxIndicatorDisappear();
    WebElement noteDialog = getViewNoteDialog();
    waitForElementDisplayed(noteDialog, true);
  }

  public boolean isViewNoteDialogPresented() {
    return getViewNoteDialog().isDisplayed();
  }

  public List<String> getCaseNoteAuthors() {
    List<WebElement> noteAuthorElements = findListElementsByCssSelector(".fa-pencil-square-o + span.history-fullname");
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }

  private WebElement getViewNoteDialog() {
    return findElementById(VIEW_NOTE_DIALOG_ID);
  }

  public void changeCaseName(String newCaseName) {
    clickByCssSelector("span[id$='case-name-edit-inplace_display']");
    WebElement taskNameInput = findElementByCssSelector("input[id$='case-detail-name-input']");
    waitForElementDisplayed(taskNameInput, true);
    taskNameInput.clear();
    taskNameInput.sendKeys(newCaseName);
    clickByCssSelector("#case-item-details\\:case-detail-title-form\\:case-name-edit-inplace_editor .ui-inplace-save");
    waitAjaxIndicatorDisappear();
  }

  public void changeCaseDescription(String newDescription) {
    onClickDescriptionEditIcon();
    onClickDescriptionInplace();
    onChangeDescriptionInput(newDescription);
    onSubmitDescriptionInplaceEditor();
    waitForPageLoaded();
  }

  public String getNameOfCaseAt() {
    WebElement taskName = findElementByCssSelector("span[id$='case-name-edit-inplace_display']");
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

  public CaseWidgetPage goBackToCaseListFromCaseDetails() {
    clickBackButton();
    return new CaseWidgetPage();
  }
  
  private void onSubmitDescriptionInplaceEditor() {
    WebElement editor = findElementById("case-item-details:description:case-description-form:case-description-inplace_editor");
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    click(saveButton);
    waitAjaxIndicatorDisappear();
  }

  private void onChangeDescriptionInput(String newDescription) {
   // WebElement caseDescriptionInput = findElementById("case-item-details:description:case-description-form:case-description-output");
    WebElement caseDescriptionInput = findElementByCssSelector("textarea[id='case-item-details:description:case-description-form:case-description-input']");
    waitForElementDisplayed(caseDescriptionInput, true);
    caseDescriptionInput.clear();
    caseDescriptionInput.sendKeys(newDescription);
  }

  private void onClickDescriptionInplace() {
    WebElement caseDescriptionInplace = findElementById("case-item-details:description:case-description-form:case-description-output");
    waitForElementDisplayed(caseDescriptionInplace, true);
    click(caseDescriptionInplace);
  }

  private void onClickDescriptionEditIcon() {
    try {
      WebElement caseIcons = caseItem.findElement(By.cssSelector(CASE_ICONS_CONTAINER_COMPONENT_CSS_SELECTOR));
      WebElement descriptionIcon = caseIcons.findElement(By.cssSelector("a[class*='fa fa-clipboard']"));
      if (descriptionIcon != null) {
        click(descriptionIcon);
      }
    } catch (Exception e) {
      return;
    }
  }

  public void onClickHistoryIcon() {
    click(findElementById("case-item-details:case-histories:add-note-command"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void onClickDestroyCase() {
    click(findElementById("case-item-details:destroy-case-link"));
  }
  
  public void confimDestruction() {
    String destroyCaseDialogId = "case-item-details:destroy-case-confirmation-dialog";
    waitForElementDisplayed(By.id(destroyCaseDialogId), true);
    WebElement destroyConfirmationDialog = findElementById(destroyCaseDialogId);
    WebElement confirmButton = findChildElementById(destroyConfirmationDialog, "case-item-details:confirm-destruction");
    confirmButton.click();
  }
  
  @Override
  public void waitAjaxIndicatorDisappear() {
    WebElement ajaxIndicatorStartState = findElementById("ajax-indicator:ajax-indicator-ajax-indicator_start");
    boolean displayed = false;
    try {
      displayed = ajaxIndicatorStartState.isDisplayed();
    } catch (Exception e) {
      try {
        displayed = ajaxIndicatorStartState.isDisplayed();
      } catch (Exception e1) {
        System.out.println("Cannot check if ajax indicator is displayed");
      }
    }
    if (displayed) {
      waitForElementDisplayed(ajaxIndicatorStartState, false);
    }
  }

  public TaskWidgetPage clickShowAllTasks() {
    click(caseItem.findElement(By.cssSelector("a[id$='show-more-related-tasks']")));
    return new TaskWidgetPage();
  }

  public void uploadDocumentWithoutError(String pathToFile) {
    Sleeper.sleep(2000);//slow down a bit for FF
    openAddDocumentDialogAndUploadDocument(pathToFile);
    waitForElementDisplayed(By.cssSelector("span[class$='ui-messages-info-summary']"), true);
    click(By.cssSelector("button[id$='case-item-details:document:document-upload-close-command']"));
  }

  public String uploadDocumentWithError(String pathToFile) {
    openAddDocumentDialogAndUploadDocument(pathToFile);
    WebElement errorMsg = findElementByCssSelector("div[id$='upload-messages']");
    String returnMsg = StringUtils.EMPTY;
    if (errorMsg.isDisplayed()) {
      returnMsg = errorMsg.getText();
    }
    click(By.cssSelector("button[id$='document-upload-close-command']"));
    return returnMsg;
  }

  private void openAddDocumentDialogAndUploadDocument(String pathToFile) {
    getAddAttachmentDialog();
    findElementByCssSelector("input[id$='document-upload-panel_input']").sendKeys(pathToFile);
    // currently haven't found solution to check when the file upload finish, we have to wait
    Sleeper.sleep(2000);
  }

  public boolean isDeleteDocumentButtonPresented() {
    return isElementDisplayed(findDeleteDocumentIcon());
  }
  
  public void removeAttachmentInCaseDocument(String filename) {
    WebElement documentName = findElementByCssSelector("span[class$='js-document-name']");
    if (documentName.getText().equalsIgnoreCase(filename)) {
      getDeleteDocumentConfirmDialog();
      findElementByCssSelector("button[id$='document-deletion-command']").click();
    }
  }
  
  public WebElement getDeleteDocumentConfirmDialog() {
    click(findElementByCssSelector("a[id$='delete-file']"));
    waitForElementDisplayed(By.cssSelector("div[class$='document-deletion-dialog']"), true);
    return findElementByCssSelector("div[class$='document-deletion-dialog']");
  }

  public WebElement findDeleteDocumentIcon() {
    return findElementByCssSelector("a[id$='delete-file']");
  }

  public String getCaseName() {
    return getTextOfCurrentBreadcrumb().replace("Case: ", "");
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
  
  public List<WebElement> findDocumentItemInCaseDetailsDocumentTable() {
    return findListElementsByCssSelector("a[id$='download']");
  }

  public void clickCaseListBreadCrumb() {
    click(By.cssSelector(".portal-breadcrumb ul li:nth-of-type(3) .ui-menuitem-link"));
  }
  
  public boolean isBackButtonDisplayed() {
    return isElementDisplayedById("case-item-details:case-detail-title-form:back-to-cases");
  }
  
  public void clickBackButton() {
    click(findElementById("case-item-details:case-detail-title-form:back-to-cases"));
  }

  public void openRelatedCaseOfBusinessCase(int index) {
    click(By.cssSelector("a[id$='related-tasks:cases:" + index + ":case-name']"));
    waitForPageLoaded();
  }
  
  public void waitForCaseDetailsReload() {
    waitForPageLoaded();
    Sleeper.sleep(3000); // currently, cannot find how to navigate to same page
    waitForElementDisplayed(By.className("case-detail-body"), true);
  }
  
  public void waitForCaseNameChanged(String caseName) {
    waitForElementDisplayed(findElementByCssSelector(CURRENT_BREADCRUMB_SELECTOR), true);
    Awaitility.waitAtMost(new Duration(60, TimeUnit.SECONDS)).until(() -> getTextOfCurrentBreadcrumb().contains(caseName));
  }

  public WebElement getGeneralInforBox() {
    return findElementByCssSelector("[id$='case-general-information-card']");
  }

  public WebElement getRelatedRunningTaskBox() {
    return findElementByCssSelector("[id$='case-details-related-running-tasks-card']");
  }

  public WebElement getHistoriesBox() {
    return findElementByCssSelector("[id$='history-container']");
  }

  public WebElement getDocumentBox() {
    return findElementByCssSelector("[id$='case-details-document-card']");
  }

  public WebElement getAddNoteDialog() {
    onClickHistoryIcon();
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    return findElementById("case-item-details:case-histories:add-note-dialog");
  }

  public WebElement getAddAttachmentDialog() {
    clickByCssSelector("a[id$='add-document-command']");
    waitForElementDisplayed(By.cssSelector("span[id$='document-upload-dialog_title']"), true);
    return findElementById("case-item-details:document:document-upload-dialog");
  }
  
  public void waitForCaseDetailsDisplay() {
    waitForElementDisplayed(By.id("case-item-details:case-detail-title-form"), true);
  }

}

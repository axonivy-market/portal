package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;

public class CaseDetailsPage extends TemplatePage {
  private static final String DONE_TASKS_SELECTOR = "td.related-task-state-column .task-state.done-task-state";
  private static final String DOCUMENT_COMPONENT_ID = "div[id='case-details-document-panel']";
  private static final String HISTORY_COMPONENT_ID = "div[id='case-details-history-panel']";
  private static final String RELATED_TASKS_COMPONENT_ID = "div[id='case-details-relatedTask-panel']";
  private static final String RELATED_CASES_COMPONENT_ID = "div[id='case-details-technicalCase-panel']";
  private static final String HISTORY_LIST_CSS_SELECTOR = "a[id^='case-item-details:widgets:4:case-histories:case-histories']";
  private static final String LATEST_HISTORY_LIST_CSS_SELECTOR =
      "a[id='case-item-details:widgets:4:case-histories:case-histories:0:note-link']";
  private static final String GENERAL_INFORMATION_COMPONENT_ID = "div[id='case-details-information-panel']";
  private static final String ADDITIONAL_CASE_DETAILS_URL_CSS_SELECTOR = "a[id$='additional-case-details-link']";
  private static final String PROCESS_OVERVIEW_URL_CSS_SELECTOR = "a[id$='show-process-overview-link']";
  private static final String AUTHOR_USER_CSS_SELECTOR = "span[class='history-fullname']";
  private static final String VIEW_NOTE_DIALOG_ID = "case-item-details:widgets:4:case-histories:view-note-dialog";
  private WebElement caseItem;

  @Override
  protected String getLoadedLocator() {
    return "id('case-item-details:case-detail-body')";
  }

  public CaseDetailsPage() {
    this.caseItem = findElementByCssSelector("#main-area-panel");
  }

  public String getCaseDuration() {
    return caseItem.findElement(By.cssSelector("span[id$='case-duration-time']")).getText();
  }

  public String getCaseCategory() {
    return caseItem.findElement(By.cssSelector("span[id$='case-category']")).getText();
  }

  public int getNumberOfHistory() {
    return caseItem.findElements(By.cssSelector(HISTORY_LIST_CSS_SELECTOR)).size();
  }

  public int countRelatedTasks() {
    return caseItem.findElement(By.cssSelector("div[id$='related-tasks']"))
        .findElements(By.cssSelector("td.related-task-name-column")).size();
  }

  public int countRelatedDoneTasks() {
    return caseItem.findElement(By.cssSelector("div[id$='related-tasks']"))
        .findElements(By.cssSelector(".task-state.done-task-state")).size();
  }

  public boolean checkDoneTasksOfHistory() {
    List<WebElement> taskNames = caseItem.findElement(By.cssSelector("div[id$='related-tasks']"))
        .findElements(By.cssSelector("span.task-name-value"));
    List<WebElement> taskStates = caseItem.findElement(By.cssSelector("div[id$='related-tasks']"))
        .findElements(By.cssSelector("span.task-state"));
    List<WebElement> histories = caseItem.findElement(By.cssSelector("div[id$='case-histories']"))
        .findElements(By.cssSelector("a.task-note-link"));
    if (CollectionUtils.isNotEmpty(taskStates)) {
      String DONE = "Done";
      for (int i = 0; i < taskStates.size(); i++) {
        if (DONE.equals(taskStates.get(i).getText()) && !isHistoryExistent(histories, taskNames.get(i).getText())) {
          System.out.println(taskStates.get(i).getText());
          return false;
        }
      }
    }
    return true;
  }

  private boolean isHistoryExistent(List<WebElement> histories, String historyContent) {
    for (WebElement history : histories) {
      System.out.println(history.getText() + " - " + historyContent );
      if (history.getText().equals(historyContent)) {
        return true;
      }
    }
    return false;
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

  public void openProcessOverviewPage() {
    click(caseItem.findElement(By.cssSelector(PROCESS_OVERVIEW_URL_CSS_SELECTOR)));
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
  
  public boolean isRelatedCasesComponentPresented() {
    return getRelatedCasesComponent().isDisplayed();
  }
  
  private WebElement getRelatedCasesComponent() {
    return findElementByCssSelector(RELATED_CASES_COMPONENT_ID);
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
    caseItem.findElement(By.cssSelector("div[id$='related-tasks']"))
    .findElements(By.cssSelector("td.related-task-name-column")).get(index).click();
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
    List<WebElement> noteAuthorElements = findListElementsByCssSelector(".si-notes-quill + span.history-fullname");
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }

  private WebElement getViewNoteDialog() {
    return findElementById(VIEW_NOTE_DIALOG_ID);
  }

  public void changeCaseDescription(String newDescription) {
    onClickDescriptionEditIcon();
    onChangeDescriptionInput(newDescription);
    onSubmitDescriptionInplaceEditor();
    waitForPageLoaded();
  }

  public String getDescription() {
    WebElement caseDescription = findElementByCssSelector("[id$='case-description-output']");
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
    WebElement editor = findElementById("case-item-details:widgets:0:general-information:description:case-description-form:case-description-inplace_editor");
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    click(saveButton);
    waitAjaxIndicatorDisappear();
  }

  private void onChangeDescriptionInput(String newDescription) {
    WebElement caseDescriptionInput = findElementByCssSelector("textarea[id='case-item-details:widgets:0:general-information:description:case-description-form:case-description-input']");
    waitForElementDisplayed(caseDescriptionInput, true);
    caseDescriptionInput.clear();
    caseDescriptionInput.sendKeys(newDescription);
  }

  private void onClickDescriptionEditIcon() {
    click(By.id("case-item-details:widgets:0:general-information:description:edit-description-link"));
  }

  public void onClickHistoryIcon() {
    click(findElementById("case-item-details:widgets:4:case-histories:add-note-command"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void onClickDestroyCase() {
    click(findElementById("case-item-details:widgets:0:destroy-case-link"));
  }
  
  public void confimDestruction() {
    String destroyCaseDialogId = "case-item-details:destroy-case-confirmation-dialog";
    waitForElementDisplayed(By.id(destroyCaseDialogId), true);
    WebElement destroyConfirmationDialog = findElementById(destroyCaseDialogId);
    WebElement confirmButton = findChildElementById(destroyConfirmationDialog, "case-item-details:confirm-destruction");
    click(confirmButton);
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
    click(By.cssSelector("button[id$='document:document-upload-close-command']"));
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
    return isElementDisplayed(By.cssSelector("a[id$='delete-file']"));
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
    waitForElementDisplayed(By.cssSelector("div[id$='document-deletion-dialog']"), true);
    return findElementByCssSelector("div[id$='document-deletion-dialog']");
  }

  public WebElement findDeleteDocumentIcon() {
    return findElementByCssSelector("a[id$='delete-file']");
  }

  public String getCaseName() {
    return getTextOfCurrentBreadcrumb().replace("Case: ", "");
  }
  
  public String getCaseId() {
    return findElementByCssSelector("span[id$='general-information:case-id']").getText();
  }
  
  public boolean isAddNoteButtonDisplayed() {
    return isElementDisplayed(By.cssSelector("a[id$='case-histories:add-note-command']"));
  }

  public boolean isShowMoreNoteButtonDisplayed() {
    return isElementDisplayed(By.cssSelector("a[id$='case-histories:show-more-note-link']"));
  }

  public boolean isShowDetailsDisplayed() {
    return isElementDisplayed(By.cssSelector("a[id$='show-additional-case-details-link']"));
  }

  public boolean isAddDocumentLinkDisplayed() {
    return isElementDisplayed(By.cssSelector("a[id$='document:add-document-command']"));
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
    click(findElementById("case-item-details:back-to-cases"));
  }

  public void openRelatedCaseOfBusinessCase(int index) {
    WebElement findElement = caseItem.findElement(By.cssSelector("div[id$='related-cases']"));
    if (findElement != null) {
      findElement
      .findElements(By.cssSelector("td.name-column")).get(index).click();
    } 
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
    return findElementByCssSelector("[id$='case-detail-general-container']");
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
    return findElementByCssSelector("[id$='case-histories:add-note-dialog']");
  }

  public WebElement getAddAttachmentDialog() {
    clickByCssSelector("a[id$='add-document-command']");
    waitForElementDisplayed(By.cssSelector("span[id$='document-upload-dialog_title']"), true);
    return findElementByCssSelector("[id$='document:document-upload-dialog']");
  }
  
  public void waitForCaseDetailsDisplay() {
    waitForElementDisplayed(By.id("case-item-details:case-detail-body"), true);
  }

  public WebElement getSwitchToEditModeButton() {
    return findElementByCssSelector("[id$=':switch-to-edit-mode-button']");
  }
  
  public WebElement getSwitchToViewModeButton() {
    return findElementByCssSelector("[id$=':switch-to-view-mode-button']");
  }
  
  public WebElement getResetButton() {
    return findElementByCssSelector("[id$=':reset-details-settings-button']");
  }
  
  public void resetToDefault() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-details-settings-button']"), true);
    click(By.cssSelector("[id$=':reset-details-settings-button']"));
  }
  
  public void switchToEditMode() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-edit-mode-button']"), true);
    click(By.cssSelector("[id$=':switch-to-edit-mode-button']"));
  }

  public void waitForSaveButtonDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-view-mode-button']"), true);
  }

  public void saveAndSwitchToViewMode() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-view-mode-button']"), true);
    click(By.cssSelector("[id$=':switch-to-view-mode-button']"));
  }

  public void drapAndDropWidgets(String sourceName, String destinationName) {
    waitForElementDisplayed(By.cssSelector(String.format("[id='case-details-%s-panel']", sourceName)), true);
    WebElement sourceElement = findElementByCssSelector(String.format("[id='case-details-%s-panel']", sourceName));
    waitForElementDisplayed(By.cssSelector(String.format("[id='case-details-%s-panel']", destinationName)), true);
    WebElement destinationElement = findElementByCssSelector(String.format("[id='case-details-%s-panel']", destinationName));
    Actions actions = new Actions(driver);
    Action moveWidget = actions.dragAndDrop(sourceElement, destinationElement).build();
    moveWidget.perform();
  }

  public void waitForResetButtonDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id='case-item-details:reset-details-settings-button']"), true);
  }
  
  public void waitForResetButtonNotPresent() {
    waitForElementPresent(By.cssSelector("[id='case-item-details:reset-details-settings-button']"), false);
  }

  public boolean hasDoneTask() {
    List<WebElement> doneTasks = findListElementsByCssSelector(DONE_TASKS_SELECTOR);
    return CollectionUtils.isNotEmpty(doneTasks);
  }
}

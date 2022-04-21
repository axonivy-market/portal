package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import ch.ivyteam.ivy.workflow.TaskState;
import portal.guitest.common.Sleeper;
import portal.guitest.common.WaitHelper;

public class CaseDetailsPage extends TemplatePage {
  private static final String CLASS = "class";
  private static final String DONE_TASKS_SELECTOR = "td.related-task-state-column .task-state.done-task-state";
  private static final String DOCUMENT_COMPONENT_ID = "div[id='case-details-document-panel']";
  private static final String HISTORY_COMPONENT_ID = "div[id='case-details-history-panel']";
  private static final String RELATED_TASKS_COMPONENT_ID = "div[id='case-details-relatedTask-panel']";
  private static final String RELATED_CASES_COMPONENT_ID = "div[id='case-details-technicalCase-panel']";
  private static final String HISTORY_LIST_CSS_SELECTOR = "td.history-note a[id*=':case-histories:case-histories:']";
  private static final String LATEST_HISTORY_LIST_CSS_SELECTOR = "a[id*=':case-histories:case-histories:0:note-link']";
  private static final String GENERAL_INFORMATION_COMPONENT_ID = "div[id='case-details-information-panel']";
  private static final String ADDITIONAL_CASE_DETAILS_URL_CSS_SELECTOR = "a[id$=':show-additional-case-details-link']";
  private static final String PROCESS_OVERVIEW_URL_CSS_SELECTOR = "a[id$=':show-process-overview-link']";
  private static final String AUTHOR_USER_CSS_SELECTOR = "span[class='history-fullname']";
  private static final String VIEW_NOTE_DIALOG_SELECTOR = "[id$=':case-histories:view-note-dialog']";
  private WebElement caseItem;

  @Override
  protected String getLoadedLocator() {
    return "id('case-item-details:case-details-container:case-detail-body')";
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

  public boolean isBusinessCaseInformationSectionDisplayed() {
    return isElementDisplayed(By.cssSelector("div[id$='business-case-information']"));
  }

  public CaseDetailsPage openBusinessCaseFromTechnicalCase() {
    caseItem.findElement(By.cssSelector("a[id$='related-business-case']")).click();
    waitForElementPresent(By.cssSelector("div[id$='business-case-information']"), false);
    return new CaseDetailsPage();
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
          return false;
        }
      }
    }
    return true;
  }

  private boolean isHistoryExistent(List<WebElement> histories, String historyContent) {
    for (WebElement history : histories) {
      if (history.getText().equals(historyContent)) {
        return true;
      }
    }
    return false;
  }

  public int countRelatedCases() {
    return caseItem.findElement(By.cssSelector("div[id$='related-cases']"))
        .findElements(By.cssSelector("td.name-column")).size();
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
    clickByCssSelector(ADDITIONAL_CASE_DETAILS_URL_CSS_SELECTOR);
  }

  public void openProcessOverviewPage() {
    clickByCssSelector(PROCESS_OVERVIEW_URL_CSS_SELECTOR);
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

  public TaskDetailsPage openTasksOfCasePageViaDetailsAction(int index) {
    String openDetailsCommandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-open-detail-command']", index);
    waitForElementDisplayed(By.cssSelector(openDetailsCommandButton), true);
    findElementByCssSelector(openDetailsCommandButton).click();
    return new TaskDetailsPage();
  }

  public CaseDetailsPage openCasesOfCasePageViaDetailsAction(int index) {
    String openDetailsCommandButton = String.format("[id$='related-cases-widget:related-cases:%d:action-step-component:case-item-open-detail-link']", index);
    waitForElementDisplayed(By.cssSelector(openDetailsCommandButton), true);
    findElementByCssSelector(openDetailsCommandButton).click();
    waitForElementPresent(By.cssSelector(openDetailsCommandButton), false);
    return new CaseDetailsPage();
  }

  public AdditionalCaseDetailsPage openRelatedCaseBusinessDetail(int index) {
    String openDetailsCommandButton = String.format("[id$='related-cases-widget:related-cases:%d:action-step-component:show-additional-case-details-link']", index);
    waitForElementDisplayed(By.cssSelector(openDetailsCommandButton), true);
    findElementByCssSelector(openDetailsCommandButton).click();
    return new AdditionalCaseDetailsPage();
  }

  public HomePage clickRelatedCaseSubmitLeaveReason(int index) {
    WebElement sideSteps = findElementByCssSelector(String.format("[id$='related-cases-widget:related-cases:%d:action-step-component:side-steps']", index));
    //findChildElementsByCssSelector(sideSteps, "a[id$=':side-step-item']").get(0).click();
    findChildElementByLinkText(sideSteps, "Submit leave reason").click();
    return new HomePage();
  }

  public HomePage clickRelatedCaseUploadAdditionalDocument(int index) {
    WebElement sideSteps = findElementByCssSelector(String.format("[id$='related-cases-widget:related-cases:%d:action-step-component:side-steps']", index));
    //findChildElementsByCssSelector(sideSteps, "a[id$=':side-step-item']").get(1).click();
    findChildElementByLinkText(sideSteps, "Upload additional data").click();
    return new HomePage();
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
    return findElementByCssSelector(VIEW_NOTE_DIALOG_SELECTOR);
  }

  public void changeCaseDescription(String newDescription) {
    onClickDescriptionEditIcon();
    onChangeDescriptionInput(newDescription);
    onSubmitDescriptionInplaceEditor();
    waitForPageLoaded();
  }

  private void onClickDescriptionEditIcon() {
    waitForElementDisplayed(By.cssSelector("a[id$='general-information:description:edit-description-link']"), true);
    click(By.cssSelector("a[id$='general-information:description:edit-description-link']"));
  }

  private void onChangeDescriptionInput(String newDescription) {
    WebElement caseDescriptionInput = findElementByCssSelector("textarea[id$='general-information:description:case-description-form:case-description-input']");
    waitForElementDisplayed(caseDescriptionInput, true);
    caseDescriptionInput.clear();
    caseDescriptionInput.sendKeys(newDescription);
  }

  private void onSubmitDescriptionInplaceEditor() {
    WebElement editor = findElementByCssSelector("span[id$='general-information:description:case-description-form:case-description-inplace_editor']");
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitAjaxIndicatorDisappear();
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

  public void onClickHistoryIcon() {
    click(findElementByCssSelector("a[id$=':case-histories:add-note-command']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void onClickDestroyCase() {
    click(findElementByCssSelector("a[id$=':action-group:destroy-case']"));
  }
  
  public void confimDestruction() {
    String destroyCaseDialogSelector = "[id$=':destroy-case-confirmation-dialog']";
    waitForElementDisplayed(By.cssSelector(destroyCaseDialogSelector), true);
    WebElement destroyConfirmationDialog = findElementByCssSelector(destroyCaseDialogSelector);
    WebElement confirmButton = findChildElementByCssSelector(destroyConfirmationDialog, "[id$=':confirm-destruction']");
    click(confirmButton);
  }
  
  @SuppressWarnings("deprecation")
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
    click(By.cssSelector(".portal-breadcrumb ol li:nth-of-type(2) .ui-menuitem-link"));
  }
  
  public void clickBackButton() {
    click(findElementById("case-item-details:case-details-container:back-to-cases"));
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
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ui-inputtextarea.note-content-textarea", CLASS_PROPERTY);
    return findElementByCssSelector("[id$='case-histories:add-note-dialog']");
  }

  public WebElement getAddAttachmentDialog() {
    clickByCssSelector("a[id$='add-document-command']");
    waitForElementDisplayed(By.cssSelector("span[id$='document-upload-dialog_title']"), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ui-dialog.case-upload-dialog", CLASS_PROPERTY);
    return findElementByCssSelector("[id$='document:document-upload-dialog']");
  }
  
  public void waitForCaseDetailsDisplay() {
    waitForElementDisplayed(By.id("case-item-details:case-details-container:case-detail-body"), true);
  }

  public WebElement getSwitchToEditModeButton() {
    return findElementByCssSelector("[id$=':switch-to-edit-mode-button']");
  }
  
  public WebElement getSwitchToViewModeButton() {
    return findElementByCssSelector("[id$=':switch-to-view-mode-button']");
  }
  
  public WebElement getResetButton() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-details-settings-button']"), true);
    return findElementByCssSelector("[id$=':reset-details-settings-button']");
  }
  
  public void resetToDefault() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-details-settings-button']"), true);
    click(By.cssSelector("[id$=':reset-details-settings-button']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void confirmResetToDefault() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-to-default-case-form:confirm-destruction']"), true);
    click(By.cssSelector("[id$=':reset-to-default-case-form:confirm-destruction']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }
  
  public void switchToEditMode() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-edit-mode-button']"), true);
    click(By.cssSelector("[id$=':switch-to-edit-mode-button']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    WaitHelper.assertTrueWithWait(() -> {
      var infoWidget = findElementByCssSelector("[id$='case-details-information-panel']");
      return infoWidget.getAttribute(CLASS_PROPERTY).contains("ui-resizable ui-resizable-autohide");
    });
  }

  public void waitForSaveButtonDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-view-mode-button']"), true);
  }

  public void saveAndSwitchToViewMode() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-view-mode-button']"), true);
    click(By.cssSelector("[id$=':switch-to-view-mode-button']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void drapAndDropWidgets(String sourceName, String destinationName) {
    waitForElementDisplayed(By.cssSelector(String.format("[id='case-details-%s-panel']", sourceName)), true);
    WebElement sourceElement = findElementByCssSelector(String.format("[id='case-details-%s-panel']", sourceName));
    waitForElementDisplayed(By.cssSelector(String.format("[id='case-details-%s-panel']", destinationName)), true);
    WebElement destinationElement = findElementByCssSelector(String.format("[id='case-details-%s-panel']", destinationName));
    Actions actions = new Actions(driver);
    Action moveWidget = actions.dragAndDrop(sourceElement, destinationElement).build();
    moveWidget.perform();
    WaitHelper.assertTrueWithWait(() -> {
      var caseDetails = findElementByCssSelector("[id$=':case-details-container:case-details-widgets']");
      return !caseDetails.getAttribute(CLASS_PROPERTY).contains("ui-droppable-over");
    });
  }

  public void waitForResetButtonDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-details-settings-button']"), true);
  }
  
  public void waitForResetButtonNotPresent() {
    waitForElementPresent(By.cssSelector("[id$=':reset-details-settings-button']"), false);
  }

  public boolean hasDoneTask() {
    List<WebElement> doneTasks = findListElementsByCssSelector(DONE_TASKS_SELECTOR);
    return CollectionUtils.isNotEmpty(doneTasks);
  }

  public boolean isRelatedTaskStartEnabled(int index) {
    WebElement element = findListElementsByCssSelector("[id$='task-action-component']").get(index);
    return !element.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public TaskTemplatePage startRelatedTask(int index) {
    WebElement element = findListElementsByCssSelector("[id$='task-action-component']").get(index);
    element.click();
    return new TaskTemplatePage();
  }

  public void clickRelatedTaskActionButton(int index) {
    clickByCssSelector(String.format("[id$=':related-tasks:%d:additional-options:task-side-steps-menu']", index));
    String actionPanel = String.format("[id$='task-widget:related-tasks:%d:additional-options:side-steps-panel']", index); 
    waitForElementDisplayed(By.cssSelector(actionPanel), true);
  }

  public void clickRelatedCaseActionButton(int index) {
    WebElement element = findListElementsByCssSelector(".related-cases .more-column .action-link").get(index);
    element.click();
    String actionPanel = String.format("[id$='related-cases-widget:related-cases:%d:action-step-component:action-steps-panel']", index); 
    waitForElementDisplayed(By.cssSelector(actionPanel), true);
  }

  public void reserveTask(int index) {
    String reserveCommandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-reserve-command']", index);
    waitForElementDisplayed(By.cssSelector(reserveCommandButton), true);
    findElementByCssSelector(reserveCommandButton).click();
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void resetTask(int index) {
    String resetCommandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-reset-command", index);
    waitForElementDisplayed(By.cssSelector(resetCommandButton), true);
    findElementByCssSelector(resetCommandButton).click();
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public boolean isTaskState(int index, TaskState taskState) {
    WebElement element = findListElementsByCssSelector("td.related-task-state-column span.task-state").get(index);
    if(element!=null) {
      String stateClass = element.getAttribute(CLASS);
      return stateClass.contains(taskState.toString().toLowerCase()+"-task-state");
    }
    return false;
  }

  public boolean isRelatedTaskDestroyEnabled(int index) {
    WebElement destroyButton = findDestroyCommand(index);
    return !destroyButton.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public void destroyTask(int index) {
    findDestroyCommand(index).click();
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public WebElement findDestroyCommand(int index) {
    String destroyCommandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-destroy-command", index);
    waitForElementDisplayed(By.cssSelector(destroyCommandButton), true);
    return findElementByCssSelector(destroyCommandButton);
  }

  public void confimRelatedTaskDestruction() {
    String destroyCaseDialogId = "[id$='task-widget:destroy-task-confirmation-dialog']";
    waitForElementDisplayed(By.cssSelector(destroyCaseDialogId), true);
    WebElement destroyConfirmationDialog = findElementByCssSelector(destroyCaseDialogId);
    WebElement confirmButton = findChildElementByCssSelector(destroyConfirmationDialog, "[id$='task-widget:confirm-destruction']");
    confirmButton.click();
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public String getResponsibleOfRelatedTaskAt(int index) {
    List<WebElement> responsibles = findListElementsByCssSelector("td.related-task-responsible-column");
    return responsibles.get(index).getText();
  }

  public void openTaskDelegateDialog(int index) {
    try {
      clickRelatedTaskActionButton(index);
    } catch (Exception e) {
      clickRelatedTaskActionButton(index);
    }
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
        .until(() -> findElementByCssSelector("a[id$='\\:task-delegate-command']").isDisplayed());
    clickByCssSelector("a[id$='\\:task-delegate-command']");
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), true);
  }

  public boolean isDelegateTypeSelectAvailable() {
    return isElementPresent(By.cssSelector("div[id$=':activator-panel']"));
  }

  public void selectDelegateResponsible(String responsibleName, boolean isRole) {
    if (isRole) {
      waitForElementDisplayed(By.cssSelector("[id$=':task-delegate-form:activator-type-select']"), true);
      waitForElementEnabled(By.cssSelector("[id$=':task-delegate-form:activator-type-select:1']"), true, DEFAULT_TIMEOUT);
      clickByCssSelector("[for$=':task-delegate-form:activator-type-select:1']");
      waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
      waitForElementDisplayed(By.cssSelector("input[id$='group-activator-select_input']"), true);
      type(By.cssSelector("input[id$='group-activator-select_input']"), responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='group-activator-select_panel']"), true);
      List<WebElement> foundRoles =
          findElementByCssSelector("span[id$='group-activator-select_panel").findElements(By.tagName("li"));
      foundRoles.get(0).click();
    } else {
      waitForElementDisplayed(By.cssSelector("input[id$='user-activator-select_input']"), true);
      type(By.cssSelector("input[id$='user-activator-select_input']"), responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='user-activator-select_panel']"), true);
      List<WebElement> foundUsers =
          findElementByCssSelector("span[id$='user-activator-select_panel']").findElements(By.tagName("tr"));
      foundUsers.get(0).click();
    }
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    click(By.cssSelector("button[id$='proceed-task-delegate-command']"));
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), false);
  }

  public boolean isTaskDelegateOptionDisable(int index) {
    clickRelatedTaskActionButton(index);
    String commandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-delegate-command", index);
    waitForElementDisplayed(By.cssSelector(commandButton), true);
    WebElement delegateButton = findElementByCssSelector(commandButton);
    return delegateButton.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public void openRelatedTaskWorkflowEvents(int index) {
    String commandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-workflow-event-command", index);
    waitForElementDisplayed(By.cssSelector(commandButton), true);
    findElementByCssSelector(commandButton).click();
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public boolean isRelatedTaskWorkflowEventsOpened() {
    try {
      waitForElementDisplayed(By.cssSelector("div[id$='task-widget:workflow-event-component:workflow-events-dialog']"), true);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public ExpressProcessPage addAdHocTask(int index) {
    String commandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-additional-actions']", index);
    waitForElementDisplayed(By.cssSelector(commandButton), true);
    findElementByCssSelector(commandButton).click();
    return new ExpressProcessPage();
  }

  public WebElement getExportToExcelLink(String linkId) {
    return findElementByCssSelector("a[id$=':" + linkId + "']");
  }

  public void clickExportToExcelLink(String linkId, String statusDialogId) {
    // Ensure that attribute is removed before downloading
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement statusDialog = driver.findElement(By.cssSelector("div[id$=':" + statusDialogId + "']"));
    js.executeScript("arguments[0].removeAttribute('download-status')", statusDialog);

    // click download
    WebElement downloadLink = getExportToExcelLink(linkId);
    if (downloadLink != null) {
      downloadLink.click();
    }
  }

  public boolean isDownloadCompleted(String statusDialogId) {
    WebElement statusDialog = driver.findElement(By.cssSelector("div[id$=':" + statusDialogId + "']"));
    WaitHelper.assertTrueWithWait(() -> StringUtils.isNotBlank(statusDialog.getAttribute("download-status")));
    return StringUtils.equals(statusDialog.getAttribute("download-status"), "completed");
  }

  public boolean isRelatedCaseListColumnExist(String columnClass) {
    WebElement column = findElementByCssSelector(".related-cases-container th." + columnClass);
    return column != null && column.isDisplayed();
  }

  public void clickRelatedCaseColumnsButton() {
    clickByCssSelector("a[id$='case-config-button']");
    waitForElementDisplayedByCssSelector("label[for$='related-cases-widget:case-columns-configuration:select-columns-form:columns-checkbox:3']");
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void clickRelatedCaseColumnCheckbox(int columnIndex) {
    WebElement columnCheckbox = findElementByXpath(String.format("//*[contains(@id,\":related-cases-widget:case-columns-configuration:select-columns-form:columns-checkbox\")]/tbody/tr[%s]/td/div/div[2]", columnIndex));//findElementByCssSelector(String.format("input[id$='related-cases-widget:case-columns-configuration:select-columns-form:columns-checkbox:%d']", columnIndex));
    columnCheckbox.click();
  }

  public void clickRelatedCaseDefaultCheckbox() {
    WebElement columnCheckbox = findElementByXpath("//*[contains(@id,\":related-cases-widget:case-columns-configuration:select-columns-form:default-columns\")]/div[2]");//findElementByCssSelector("input[id$='related-cases-widget:case-columns-configuration:select-columns-form:default-columns_input']");
    columnCheckbox.click();
    WaitHelper.assertTrueWithWait(() -> !findElementByCssSelector("label[for$='related-cases-widget:case-columns-configuration:select-columns-form:columns-checkbox:3']").getAttribute("class").equals("ui-state-disabled"));
  }

  public void clickRelatedCaseApplyButton() {
    click(By.cssSelector("button[id$='related-cases-widget:case-columns-configuration:select-columns-form:update-command']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public boolean isRelatedTaskListColumnExist(String columnClass) {
    WebElement column = findElementByCssSelector(".case-details-related-task-table th." + columnClass);
    return column != null && column.isDisplayed();
  }

  public void clickRelatedTaskColumnsButton() {
    clickByCssSelector("a[id$='task-config-command']");
    waitForElementDisplayedByCssSelector("label[for$='task-widget:task-columns-configuration:select-columns-form:columns-checkbox:5']");
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void clickRelatedTaskColumnCheckbox(int columnIndex) {
    WebElement columnCheckbox = findElementByXpath(String.format("//*[contains(@id,\":task-widget:task-columns-configuration:select-columns-form:columns-checkbox\")]/tbody/tr[%s]/td/div/div[2]", columnIndex));//findElementByCssSelector(String.format("input[id$='related-cases-widget:case-columns-configuration:select-columns-form:columns-checkbox:%d']", columnIndex));
    columnCheckbox.click();
  }

  public void clickRelatedTaskDefaultCheckbox() {
    WebElement columnCheckbox = findElementByXpath("//*[contains(@id,\":task-widget:task-columns-configuration:select-columns-form:default-columns\")]/div[2]");//findElementByCssSelector("input[id$='related-cases-widget:case-columns-configuration:select-columns-form:default-columns_input']");
    columnCheckbox.click();
    WaitHelper.assertTrueWithWait(() -> !findElementByCssSelector("label[for$='task-widget:task-columns-configuration:select-columns-form:columns-checkbox:5']").getAttribute("class").equals("ui-state-disabled"));
  }

  public void clickRelatedTaskApplyButton() {
    click(By.cssSelector("button[id$='task-widget:task-columns-configuration:select-columns-form:update-command']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public boolean iframeCustomWidgetIsDisplayed() {
    return findElementByCssSelector("iframe[name='custom-widget-iframe']").isDisplayed();
  }

  public String getProcessLinkInCustomIFrameWidget() {
    WebElement formInFrame = findElementByCssSelector("form[id='custom-widget-iframe-data']");
    return formInFrame.getAttribute("action");
  }

  public String getIFrameURLOfCustomWidget() {
    WebElement iframe = findElementByCssSelector("[id$=':custom-iframe']");
    return iframe.findElement(By.tagName("iframe")).getAttribute("src");
  }

  public boolean isCustomMiddlePanelDisplay() {
    return findElementByCssSelector("[id$=':caseItemDetailCustomMiddle']").isDisplayed();
  }

  public void waitForIFrameWidgetLoad() {
    driver.switchTo().frame("custom-widget-iframe");
    WaitHelper.assertTrueWithWait(() -> findElementByCssSelector("form[id='content-form']").isDisplayed());
  }

  public void waitForIFrameURLWidgetLoad() {
    driver.switchTo().frame("custom-widget-iframe-url");
    WaitHelper.assertTrueWithWait(() -> findElementByCssSelector("a[href='https://www.axonivy.com']").isDisplayed());
  }

  public void openActionMenu() {
    clickByCssSelector("[id$=':action-group:case-details-action-link']");
    waitForElementDisplayed(By.cssSelector("[id$=':action-group:action-steps-panel'].action-steps-panel"), true);
  }

  public List<String> getAvailableActionSteps() {
    waitForElementDisplayed(By.cssSelector("[id$=':action-group:action-steps-panel'].action-steps-panel"), true);
    var steps = findListElementsByCssSelector("[id$=':action-group:action-steps-panel'].action-steps-panel a.action-step-item");
    return steps.stream().map(WebElement::getText).collect(Collectors.toList());
  }

  public List<String> getAvailableActionStepsOfTechnicalCase(int caseIndex) {
    var panelId = String.format("[id$=':related-cases-widget:related-cases:%d:action-step-component:action-steps-panel']", caseIndex);
    waitForElementDisplayed(By.cssSelector(panelId), true);
    var steps = findListElementsByCssSelector(panelId + " a.action-step-item");
    return steps.stream().map(WebElement::getText).collect(Collectors.toList());
  }

  public int getNumberOfHistoryForRelatedCaseLink() {
    waitForElementDisplayed(By.cssSelector("[id$=':case-histories:case-histories']"), true);
    return findListElementsByCssSelector("td.history-related-case a[id$=':related-case-link']").size();
  }

  public String getContentOfHistoryTableRelatedCaseColumn(int rowIndex) {
    waitForElementDisplayed(By.cssSelector("[id$=':case-histories:case-histories']"), true);
    return findElementByCssSelector(String
        .format("td.history-related-case a[id$='case-histories:%d:related-case-link']", rowIndex))
        .getText();
  }

  public boolean isShowRelatedCaseCheckbox() {
    waitForElementDisplayed(By.cssSelector("[id$=':history-container']"), true);
    try {
      return findElementByCssSelector("[id$=':case-histories:related-case-checkbox']").isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  public void clickOnRelatedCaseCheckbox(boolean checkboxShouldBeChecked) {
    waitForElementDisplayed(By.cssSelector("[id$=':history-container']"), true);
    var relatedCaseCheckbox = findElementByCssSelector("[id$=':case-histories:related-case-checkbox']");
    var checkbox = relatedCaseCheckbox.findElement(By.cssSelector("div.ui-chkbox-box.ui-widget"));
    if ((checkboxShouldBeChecked && checkbox.getAttribute(CLASS).contains("ui-state-active"))
        || (!checkboxShouldBeChecked && !checkbox.getAttribute(CLASS).contains("ui-state-active"))) {
      return;
    } else {
      click(relatedCaseCheckbox.findElement(By.cssSelector("span.ui-chkbox-label")));
      // Cannot identify when the ajax request of select checkbox is finished
      // So we need to wait for Ajax Indicator disappear
      waitAjaxIndicatorDisappear();
      clickOnRelatedCaseCheckbox(checkboxShouldBeChecked);
    }
  }

  public boolean isRelatedCaseInfoColumnIsDisplay() {
    waitForElementDisplayed(By.cssSelector("[id$=':history-container']"), true);
    try {
      return findElementByCssSelector("[id$=':case-histories'] th.history-related-case").isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }
}

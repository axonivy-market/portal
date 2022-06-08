package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;
import portal.guitest.common.TaskState;
import portal.guitest.common.WaitHelper;

public class TaskDetailsPage extends TemplatePage {
  
  private static final String UI_INPLACE_SAVE = "ui-inplace-save";

  @Override
  protected String getLoadedLocator() {
    return "id('task-detail-template:task-detail-container')";
  }

  public TaskDetailsPage() {}

  public String getCreatedOnDateText() {
    return findElementByCssSelector("span[id$='start-date']").getText();
  }
  
  public String getDurationTimeText() {
	return findElementByCssSelector("span[id$='duration-time']").getText();
  }

  public List<String> getTaskNoteAuthors() {
    List<WebElement> noteAuthorElements = findListElementsByCssSelector("td.task-document-author .name-after-avatar");
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }

  public void changePriorityOfTask(int priorityValue) {
    findElementByCssSelector("[id$=':general-information:priority-form:edit-priority-inplace_display']").click();
    waitForElementDisplayed(By.cssSelector("[id$=':general-information:priority-form:priority-select-menu_label']"),
        true);
    findElementByCssSelector("[id$=':general-information:priority-form:priority-select-menu_label']").click();
    WebElement prioritySelectElement = findElementByCssSelector(
        String.format("[id$=':general-information:priority-form:priority-select-menu_%d']", priorityValue));
    waitForElementDisplayed(prioritySelectElement, true);
    prioritySelectElement.click();
    clickByCssSelector(
        "[id$=':general-information:priority-form:edit-priority-inplace_editor'] .ui-inplace-save");
    waitForElementDisplayed(By.cssSelector("[id$=':general-information:priority-form:edit-priority-inplace_editor'] .ui-inplace-save"), false);
  }

  public String getPriorityOfTask() {
    return findElementByCssSelector("span[id$='edit-priority-inplace_display']").getText();
  }

  @SuppressWarnings("deprecation")
  public void changeNameOfTask(String name) {
    clickByCssSelector("span[id$='task-name-inplace_display']");
    WebElement taskNameInput = findElementByCssSelector("input[id$='task-name-input']");
    waitForElementDisplayed(taskNameInput, true);
    taskNameInput.clear();
    taskNameInput.sendKeys(name);
    clickByCssSelector(
        "#:task-detail-title-form\\:task-name-inplace_editor .ui-inplace-save");
    waitAjaxIndicatorDisappear();
  }


  public String getNameOfTaskWhenDisplayingDetailsAt() {
    return findElementByCssSelector("span[id$='task-name-inplace_display']").getText();
  }

  public boolean isAddNoteButtonDisplayed() {
    return isElementDisplayed(By.cssSelector("[id$=':task-notes:add-note-command']"));
  }

  public boolean isShowMoreNoteButtonDisplayed() {
    return isElementDisplayed(By.cssSelector("[id$=':task-notes:show-more-note-link']"));
  }

  public boolean isAddDocumentLinkDisplayed() {
    return isElementDisplayed(By.cssSelector("[id$=':task-documents:add-document-command']"));
  }
  
  public TaskWidgetPage goBackToTaskListFromTaskDetails() {
    clickBackButton();
    return new TaskWidgetPage();
  }
  
  public boolean isBackButtonPresented() {
    return findElementByCssSelector("[id$=':task-detail-title-form:back-to-previous-page']").isDisplayed();
  }
  
  public void clickBackButton() {
    click(By.cssSelector("[id$=':task-detail-title-form:back-to-previous-page']"));
  }

  public TaskTemplatePage clickStartTask() {
    findElementByCssSelector("[id$=':task-detail-start-command']").click();
    return new TaskTemplatePage();
  }

  @SuppressWarnings("deprecation")
  public void clickTaskListBreadCrumb() {
    click(By.cssSelector(".portal-breadcrumb ol li:nth-of-type(2) .ui-menuitem-link"));
    ensureNoBackgroundRequest();
  }
  
  public String getTaskName() {
    String[] breadcrumbTexts = getTextOfCurrentBreadcrumb().split(":");
    int item = breadcrumbTexts.length;
    if (item > 1) {
      return breadcrumbTexts[item - 1].trim();
    }
    return breadcrumbTexts[0].trim();
  }
  
  public String getTaskNameInDialog() {
    waitForElementDisplayed(By.cssSelector("[id$=':task-detail-title-form:task-name-edit-form']"), true);
    return findElementByCssSelector("[id$=':task-detail-title-form:task-name-edit-form']").getText();
  }
  
  public CaseDetailsPage backToCaseDetails() {
    clickBackButton();
    return new CaseDetailsPage();
  }


  public WebElement getTaskGeneralInformation() {
    return findElementByCssSelector("[id$=':task-detail-general-container']");
  }

  public void openAddNoteDialog() {
    findElementByCssSelector("[id$=':task-notes:add-note-command']").click();
    waitForElementDisplayed(By.cssSelector("[id$=':task-notes:add-new-note-dialog']"), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ui-inputfield.ui-inputtextarea.note-content-textarea", CLASS_PROPERTY);
  }

  public WebElement getAddNoteDialog() {
    return findElementByCssSelector("[id$=':task-notes:add-new-note-dialog']");
  }

  public void openAddAttachmentDialog() {
    findElementByCssSelector("[id$=':task-documents:add-document-command']").click();
    waitForElementDisplayed(By.cssSelector("[id$=':task-documents:document-upload-dialog']"), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ui-dialog.document-upload-dialog", CLASS_PROPERTY);
  }

  @SuppressWarnings("deprecation")
  public void addNoteToTaskWithContent(String content) {
    waitForElementDisplayed(By.cssSelector("div.ui-dialog[aria-hidden='false']"), true);
    WebElement addNoteDialog = findElementByCssSelector("div.ui-dialog[aria-hidden='false']");
    waitForElementDisplayed(addNoteDialog, true);
    addNoteDialog.findElement(By.cssSelector("textarea[id$='note-content']")).sendKeys(content);
    click(addNoteDialog.findElement(By.cssSelector("button[id$='save-add-note-command']")));
    waitAjaxIndicatorDisappear();
  }

  public void uploadDocument(String path) {
    Sleeper.sleep(500);
    uploadDocumentByPath(path);
    waitForElementDisplayed(By.cssSelector("span[class$='ui-messages-info-summary']"), true);
    click(By.cssSelector("button[id$=':task-documents:document-upload-close-command']"));
  }

  private void uploadDocumentByPath(String path) {
    findElementByCssSelector("input[id$='document-upload-panel_input']").sendKeys(path);
  }
  
  public void waitUtilsTaskDetailsDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$=':task-detail-container']"), true);
  }

  public WebElement getTaskHistories() {
    return findElementByCssSelector("[id$=':task-detail-note-container']");
  }

  public WebElement getTaskAttachment() {
    return findElementByCssSelector("[id$=':task-detail-document-container']");
  }

  public WebElement getSwitchToEditModeButtonElement() {
    return findElementByCssSelector("[id$=':switch-to-edit-mode-button']");
  }
  
  public WebElement getSwitchToViewModeButtonElement() {
    return findElementByCssSelector("[id$=':switch-to-view-mode-button']");
  }
  
  public WebElement getResetButtonElement() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-details-settings-button']"), true);
    return findElementByCssSelector("[id$=':reset-details-settings-button']");
  }

  public WebElement getAddAttachmentDialog() {
    return findElementByCssSelector("[id$=':task-documents:document-upload-dialog']");
  }

  public void clickOnDeleteDocumentIcon(int index) {
    String deleteIconId = String.format("[id$=':task-documents:task-details-documents:%s:delete-file']", index);
    findElementByCssSelector(deleteIconId).click();
    waitForElementDisplayed(By.cssSelector("[id$=':task-documents:document-deletion-dialog_content']"), true);
  }

  public WebElement getDeleteDocumentConfirmDialog() {
    return findElementByCssSelector("[id$=':task-documents:document-deletion-dialog']");
  }

  public void clickOnShowMoreHistories() {
    findElementByCssSelector("[id$=':task-notes:show-more-note-link']").click();
  }
  
  public String getTaskResponsible() {
    return findElementByCssSelector(".role-and-user-information .task-activator").getText();
  }
  
  public String getTaskId() {
    return findElementByCssSelector("[id$=':task-id']").getText();
  }

  public String getFirstTaskNoteComment() {
    return findElementByCssSelector("a[id$=':task-notes:task-note-table:0:note-message']").getText();
  }

  public void openTaskDelegateDialog() {
    openActionPanel();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
        .until(() -> findElementByCssSelector("a[id$='\\:task-delegate-command']").isDisplayed());
    clickByCssSelector("a[id$='\\:task-delegate-command']");
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), true);
  }
  
  public void openActionPanel() {
    waitForElementDisplayed(By.cssSelector("[id$=':additional-options:task-detail-more-step']"), true);
    click(By.cssSelector("[id$=':additional-options:task-detail-more-step']"));
    waitForElementDisplayed(By.cssSelector("[id$=':additional-options:side-steps-panel']"),true);
  }
  
  public boolean isActionLinkEnable() {
    return !findElementByClassName("action-link").getAttribute("class").contains("ui-state-disabled");
  }
  
  public List<String> getActiveTaskAction() {
    openActionPanel();
    WebElement actionPanel = findElementByCssSelector("div[id$=':additional-options:side-steps-panel']");
    return actionPanel.findElements(By.cssSelector("a[class*='option-item']")).stream().map(WebElement::getText).collect(Collectors.toList());
  }

  public void addCommentOnTaskDelegationDialog(String comment) {
    waitForElementDisplayed(By.cssSelector("textarea[id$=':task-item-delegate-component:task-delegate-form:input-text-area-delegate-message']"), true);
    type(By.cssSelector("textarea[id$=':task-item-delegate-component:task-delegate-form:input-text-area-delegate-message']"), comment);
  }

  @SuppressWarnings("deprecation")
  public void selectDelegateResponsible(String responsibleName, boolean isRole) {
    if(isRole) {
      List<WebElement> radioButtonLabels = findListElementsByCssSelector("table[id$='activator-type-select'] label");
      click(radioButtonLabels.get(1));
      waitAjaxIndicatorDisappear();
      waitForElementDisplayed(By.cssSelector("input[id$='group-activator-select_input']"), true);
      type(By.cssSelector("input[id$='group-activator-select_input']"), responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='group-activator-select_panel']"), true);
      List<WebElement> foundRoles =
          findListElementsByCssSelector("span[id$='group-activator-select_panel'] .name-after-avatar");
      click(foundRoles.get(0));
    }
    else {
      waitForElementDisplayed(By.cssSelector("input[id$='user-activator-select_input']"), true);
      type(By.cssSelector("input[id$='user-activator-select_input']"), responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='user-activator-select_panel']"), true);
      List<WebElement> foundUsers =
          findListElementsByCssSelector("span[id$='user-activator-select_panel'] .name-after-avatar");
      click(foundUsers.get(0));
    }
    waitAjaxIndicatorDisappear();
    click(By.cssSelector("button[id$='proceed-task-delegate-command']"));
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), false);
  }
  
  public void changeExpiryOfTaskAt(String dateStringLiteral) {
    click(By.cssSelector("[id$=':expiry-form:edit-inplace_display']"));
    
    waitForElementDisplayed(By.cssSelector("[id$=':expiry-form:expiry-calendar']"), true);
    WebElement taskExpiryInlineEdit = findElementByCssSelector("[id$=':expiry-form:expiry-calendar_input']");
    taskExpiryInlineEdit.sendKeys(dateStringLiteral);

    WebElement editor = findElementByCssSelector("[id$=':expiry-form:edit-inplace_editor']");
    WebElement saveButton = findChildElementByClassName(editor, UI_INPLACE_SAVE);
    saveButton.click();
    waitForElementDisplayed(By.cssSelector("[id$=':expiry-form:edit-inplace_editor']"), false);
  }

  public boolean isClearDelayTimeDisplayed() {
    return isElementDisplayed(findElementByCssSelector("[id$=':additional-options:task-clear-delay-command']"));
  }
  
  public boolean isDelayTimeDisplayed() {
    try {
      return isElementDisplayed(findElementByCssSelector("[id$=':delay-form:delay-date']"));
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
  
  public void clickOnClearDelayTime() {
    findElementByCssSelector("a[id$=':additional-options:task-clear-delay-command']").click();
    waitForElementDisplayed(By.cssSelector("[id$=':additional-options:side-steps-panel']"), false);
  }

  public boolean isClearDeadlineDisplayed() {
    return isElementDisplayed(findElementByCssSelector("[id$=':additional-options:task-clear-expiry-command']"));
  }

  public void clickOnClearDeadlineTime() {
    findElementByCssSelector("a[id$=':additional-options:task-clear-expiry-command']").click();
    waitForElementDisplayed(By.cssSelector("[id$=':additional-options:side-steps-panel']"), false);
  }

  public String getTaskState() {
    WebElement taskStateComponent = findElementByCssSelector("[id$=':general-information:task-detail-state']");
    String stateStyleClass = taskStateComponent.findElement(By.cssSelector("i[class*='task-state']")).getAttribute("class");
    return TaskState.fromClass(Stream.of(stateStyleClass.trim().split(" ")).filter(style -> style.endsWith("-task-state")).findFirst().orElse("")).name();
  }

  public String getTaskDelayTime() {
    return findElementByCssSelector("span[id$='general-information:delay-form:delay-date_display']").getText();
  }

  public void updateDelayTimestamp(String tomorrow) {
    findElementByCssSelector("span[id$='general-information:delay-form:delay-date_display']").click();
    waitForElementDisplayed(findElementByCssSelector("[id$='general-information:delay-form:delay-date-calendar_panel']"), true);
    WebElement delayInput = findElementByCssSelector("[id$='delay-form:delay-date-calendar_input']");
    delayInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    delayInput.sendKeys(Keys.BACK_SPACE);
    delayInput.sendKeys(tomorrow);
    WebElement buttonAction = findElementByCssSelector("[id$='delay-form:delay-date_editor']");
    buttonAction.findElement(By.className("ui-inplace-save")).click();
    WaitHelper.assertTrueWithWait(() -> findElementByCssSelector("span[id$=':delay-form:delay-date_display']").getText().equalsIgnoreCase(tomorrow));
  }

  public boolean isShowWorkflowEventsLinkDisplayed() {
    return isElementDisplayedById("form:show-workflow-event");
  }

  public String openWorkflowEventDialog() {
    openActionPanel();
    clickOnShowWorkflowEventLink();
    return getDataOfWorkflowEventsTable();
  }

  private String getDataOfWorkflowEventsTable() {
    WebElement eventTable = findElementByCssSelector("tbody[id$='events-table_data']");
    List<WebElement> cells = eventTable.findElements(By.cssSelector("td"));
    return String.join(",", cells.stream().map(WebElement::getText).collect(Collectors.toList()));
  }

  public void clickOnShowWorkflowEventLink() {
    findElementByCssSelector("a[id$=':task-workflow-event-command']").click();
    waitForElementDisplayed(By.cssSelector("div[id$='events-table']"), true);
  }

  public WebElement getWorkflowEventsTable() {
    waitForElementDisplayed(By.cssSelector("th[id*='events-table:']"), true);
    return findElementByCssSelector("div[id$='workflow-events-dialog']");
  }

  public String getExpiryOfTaskAt() {
    waitForElementDisplayed(By.cssSelector("[id$=':expiry-form:edit-inplace_display']"), true);
    WebElement taskExpiry = findElementByCssSelector("[id$=':expiry-form:edit-inplace_display']");
    return taskExpiry.getText();
  }

  public void clickOnResetToDefaultButton() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-details-settings-button']"), true);
    click(By.cssSelector("[id$=':reset-details-settings-button']"));
  }
  
  public void clickOnSwitchToEditModeButton() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-edit-mode-button']"), true);
    click(By.cssSelector("[id$=':switch-to-edit-mode-button']"));
    WaitHelper.assertTrueWithWait(() -> {
      var infoWidget = findElementByCssSelector("[id$='task-details-information-panel']");
      return infoWidget.getAttribute(CLASS_PROPERTY).contains("ui-resizable ui-resizable-autohide");
    });
  }

  public void waitForSwitchToViewModeButtonDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-view-mode-button']"), true);
  }

  public void clickOnSwitchToViewModeButton() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-view-mode-button']"), true);
    click(By.cssSelector("[id$=':switch-to-view-mode-button']"));
  }

  public void drapAndDropWidgets(String sourceName, String destinationName) {
    waitForElementDisplayed(By.cssSelector(String.format("[id$=':task-detail-%s-container']", sourceName)), true);
    WebElement sourceElement = findElementByCssSelector(String.format("[id$=':task-detail-%s-container']", sourceName));
    waitForElementDisplayed(By.cssSelector(String.format("[id$=':task-detail-%s-container']", destinationName)), true);
    WebElement destinationElement = findElementByCssSelector(String.format("[id$=':task-detail-%s-container']", destinationName));
    Actions actions = new Actions(driver);
    Action moveWidget = actions.dragAndDrop(sourceElement, destinationElement).build();
    moveWidget.perform();
    WaitHelper.assertTrueWithWait(() -> {
      var taskDetails = findElementByCssSelector("[id$='task-detail-template:task-details-widgets']");
      return !taskDetails.getAttribute(CLASS_PROPERTY).contains("ui-droppable-over");
    });
  }

  public void waitForResetButtonDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-details-settings-button']"), true);
  }

  public void changeEscaltionActivatorTo(String activatorName, boolean isUser) {
    boolean canEditExpiryActivator = canChangeEscalationActivator();
    if (canEditExpiryActivator) {
      clickOnEditEscaltionEditIcon();
      if (isUser) {
        waitForElementDisplayed(By.cssSelector("input[id$=':user-expiry-activator-select_input']"), true);
        type(By.cssSelector("input[id$=':user-expiry-activator-select_input']"), activatorName);
        waitForElementDisplayed(By.cssSelector("span[id$=':user-expiry-activator-select_panel']"), true);
        List<WebElement> foundUsers =
            findListElementsByCssSelector("span[id$=':user-expiry-activator-select_panel'] .name-after-avatar");
        click(foundUsers.get(0));
      } else {
        List<WebElement> radioButtonLabels = findListElementsByCssSelector("table[id$='task-escalation-activator-form:activator-type-select'] label");
        click(radioButtonLabels.get(1));
        waitForElementDisplayed(By.cssSelector("input[id$=':group-expiry-activator-select_input']"), true);
        type(By.cssSelector("input[id$=':group-expiry-activator-select_input']"), activatorName);
        waitForElementDisplayed(By.cssSelector("span[id$=':group-expiry-activator-select_panel']"), true);
        List<WebElement> foundRoles =
            findListElementsByCssSelector("span[id$=':group-expiry-activator-select_panel'] .name-after-avatar");
        click(foundRoles.get(0));
      }
      waitForElementEnabled(By.cssSelector("button[id$=':task-escalation-activator-form:assign-task-command']"), true, DEFAULT_TIMEOUT);
      clickByCssSelector("button[id$=':task-escalation-activator-form:assign-task-command']");
      waitForElementDisplayed(By.cssSelector("div[id$='task-escalation-activator-dialog']"), false);
    }
  }

  private void clickOnEditEscaltionEditIcon() {
    waitForElementDisplayed(By.className("task-expiry-activator-edit"), true);
    clickByCssSelector("a[class$='task-expiry-activator-edit']");
    waitForElementDisplayed(By.cssSelector("form[id$=':task-escalation-activator-form']"), true);
  }

  public boolean canChangeEscalationActivator() {
    try {
      return isElementDisplayed(findElementByClassName("task-expiry-activator-edit"));
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  public String getAfterEscalation() {
    return findElementByClassName("task-expiry-activator-name").getText();
  }

  public void waitForIFrameWidgetLoad() {
    driver.switchTo().frame("custom-widget-iframe");
    WaitHelper.assertTrueWithWait(() -> findElementByCssSelector(".container.frame").isDisplayed());
  }

  public void waitForIFrameURLWidgetLoad() {
    driver.switchTo().frame("custom-widget-iframe-url");
    WaitHelper.assertTrueWithWait(() -> findElementByCssSelector("a[href='https://www.axonivy.com']").isDisplayed());
  }
}

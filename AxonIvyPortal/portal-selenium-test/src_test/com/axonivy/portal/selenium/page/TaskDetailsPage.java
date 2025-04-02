package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;


public class TaskDetailsPage extends TemplatePage {
  private static final String UI_INPLACE_SAVE = ".ui-inplace-save";

  @Override
  protected String getLoadedLocator() {
    return "#role-and-user-information";
  }

  public void addNote(String noteContent) {
    $("a[id$=':task-notes:add-note-command']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$=':task-notes:add-note-command']").click();
    $("div[id$=':task-notes:add-new-note-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[id$=':task-notes:add-new-note-dialog']").find("textarea").sendKeys(noteContent);
    $("button[id$=':task-notes:task-add-new-note-form:save-add-note-command']").click();
    $("div[id$=':task-notes:task-add-new-note-form:save-add-note-command']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getNotesWithContent(String content) {
    $("span[id$=':task-notes:task-history-content-container']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("span[id$=':task-notes:task-history-content-container'] table tbody tr td a").filter(text(content));
  }

  public CaseDetailsPage gotoTechnicalCase() {
    $("a[id$=':general-information:related-technical-case']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$=':general-information:related-technical-case']").click();
    $("div[id$=':general-information:business-case-information']").shouldBe(appear, DEFAULT_TIMEOUT);
    return new CaseDetailsPage();
  }

  public void gotoBusinessCase() {
    $("a[id$=':general-information:related-case']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$=':general-information:related-case']").click();
    $("div[id$=':general-information:business-case-information']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getBreadcrumbLastDisplayedItem() {
    return $$("span.ui-menuitem-text").last().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getInformationPanel() {
    return $("div[id$='task-details-information-panel'").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void openActionPanel() {
    $("[id$=':additional-options:task-detail-more-step']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("[id$=':additional-options:side-steps-panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  private void openTriggerEscalationDialog() {
    $("a[id$='\\:task-trigger-escalation-command']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id$='\\:escalation-task-confirmation-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void triggerEscalation() {
    openTriggerEscalationDialog();
    $("button[id$='\\:confirm-escalation']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getPriorityOfTask() {
    return $("span[id$='task-priority']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("i[class*='priority']")
        .closest("span");
  }

  public SelenideElement getStateOfTask() {
    return $("[id$=':general-information:task-detail-state']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("i[class*='task-state']").closest("span");
  }

  public void back() {
    $("[id$=':task-detail-title-form:back-to-previous-page']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getResponsibleAvatar() {
    return $(".security-member-container > .has-avatar > .ui-avatar").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getShareButton() {
    return $("button[id$=':share-page-button']");
  }

  public SelenideElement getShareDialog() {
    return $("div[id$=':share-task-details-dialog']");
  }

  public String getCreatedOnDateText() {
    return findElementByCssSelector("span[id$='start-date']").getText();
  }

  public boolean isActionLinkEnable() {
    return !$(".action-link").getAttribute("class").contains("ui-state-disabled");
  }

  public List<String> getActiveTaskAction() {
    openActionPanel();
    WebElement actionPanel = findElementByCssSelector("div[id$=':additional-options:side-steps-panel']");
    return actionPanel.findElements(By.cssSelector("a[class*='option-item']")).stream().map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public boolean isAddNoteButtonDisplayed(boolean expected) {
    return isElementDisplayed(By.cssSelector("[id$=':task-notes:add-note-command']"), expected);
  }

  public boolean isAddDocumentLinkDisplayed(boolean expected) {
    return isElementDisplayed(By.cssSelector("[id$=':task-documents:add-document-command']"), expected);
  }

  public boolean isShowMoreNoteButtonDisplayed(boolean expected) {
    return isElementDisplayed(By.cssSelector("[id$=':task-notes:show-more-note-link']"), expected);
  }

  public SelenideElement getSharePageButtonElement() {
    return $("[id$=':share-page-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getSwitchToEditModeButtonElement() {
    return $("[id$=':switch-to-edit-mode-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void clickOnSwitchToEditModeButton() {
    $(By.cssSelector("[id$=':switch-to-edit-mode-button']")).shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("[id$='task-details-information-panel']").shouldBe(Condition.visible, DEFAULT_TIMEOUT).shouldHave(
        Condition.attribute(CLASS_PROPERTY, "grid-stack-item ui-draggable ui-resizable ui-resizable-autohide"));
  }

  public void waitForSwitchToViewModeButtonDisplayed() {
    $(By.cssSelector("[id$=':switch-to-view-mode-button']")).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void drapAndDropWidgets(String sourceName, String destinationName) {
    SelenideElement sourceElement =
        $(String.format("[id$=':task-detail-%s-container']", sourceName)).shouldBe(appear, DEFAULT_TIMEOUT);
    SelenideElement destinationElement =
        $(By.cssSelector(String.format("[id$=':task-detail-%s-container']", destinationName))).shouldBe(appear,
            DEFAULT_TIMEOUT);
    Actions actions = new Actions(WebDriverRunner.getWebDriver());
    Action moveWidget = actions.clickAndHold(sourceElement).moveToElement(destinationElement).release().build();
    moveWidget.perform();
  }

  public SelenideElement getSwitchToViewModeButtonElement() {
    return $("[id$=':switch-to-view-mode-button']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOnSwitchToViewModeButton() {
    $(By.cssSelector("[id$=':switch-to-view-mode-button']")).shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getResetButtonElement() {
    return $(By.cssSelector("[id$=':reset-details-settings-button']")).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOnResetToDefaultButton() {
    getResetButtonElement().shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getTaskGeneralInformation() {
    return $("[id$=':task-detail-general-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void openAddNoteDialog() {
    $("[id$=':task-notes:add-note-command']").shouldBe(getClickableCondition()).click();
    $("[id$=':task-notes:add-new-note-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getAddNoteDialog() {
    var noteDialog = $("[id$=':task-notes:add-new-note-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    noteDialog.$(".ui-dialog-title").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return noteDialog;
  }

  public void waitUtilsTaskDetailsDisplayed() {
    $("[id$=':task-detail-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void addNoteToTaskWithContent(String content) {
    $("div.ui-dialog[aria-hidden='false']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    SelenideElement addNoteDialog = $("div.ui-dialog[aria-hidden='false']").shouldBe(appear, DEFAULT_TIMEOUT);
    addNoteDialog.findElement(By.cssSelector("textarea[id$='note-content']")).sendKeys(content);
    addNoteDialog.findElement(By.cssSelector("button[id$='save-add-note-command']")).click();
  }

  public void openAddAttachmentDialog() {
    $("[id$=':task-documents:add-document-command']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("[id$=':task-documents:document-upload-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getAddAttachmentDialog() {
    var uploadDialog = $("[id$=':task-documents:document-upload-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    uploadDialog.$(".ui-dialog-title").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return uploadDialog;
  }

  public void uploadDocument(String path) {
    uploadDocumentByPath(path);
    $("span[class$='ui-messages-info-summary']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$=':task-documents:document-upload-close-command']").shouldBe(getClickableCondition()).click();
  }

  private void uploadDocumentByPath(String path) {
    $("input[id$='document-upload-panel_input']").shouldBe(Condition.exist, DEFAULT_TIMEOUT).sendKeys(path);
  }

  public SelenideElement getTaskHistories() {
    return $("[id$=':task-detail-note-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getTaskAttachment() {
    return $("[id$=':task-detail-document-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getDeleteDocumentConfirmDialog() {
    $("a[id$='delete-file']").shouldBe(getClickableCondition()).click();
    return $("div[id$='document-deletion-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String openWorkflowEventDialog() {
    openActionPanel();
    clickOnShowWorkflowEventLink();
    return getDataOfWorkflowEventsTable();
  }

  private String getDataOfWorkflowEventsTable() {
    List<WebElement> cells =
        $("tbody[id$='events-table_data']").shouldBe(appear, DEFAULT_TIMEOUT).findElements(By.cssSelector("td"));
    return String.join(",", cells.stream().map(WebElement::getText).collect(Collectors.toList()));
  }

  public void clickOnShowWorkflowEventLink() {
    $("a[id$=':task-workflow-event-command']").shouldBe(getClickableCondition()).click();
    $("div[id$='events-table']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getWorkflowEventsTable() {
    $("th[id*='events-table:']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id$='workflow-events-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOnShowMoreHistories() {
    $("[id$=':task-notes:show-more-note-link']").shouldBe(getClickableCondition()).click();
  }

  public void waitForIFrameURLWidgetLoad() {
    switchToIframeWithNameOrId("custom-widget-iframe-url");
    $("a[href='https://www.iana.org/domains/example']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    switchBackToParent();

  }

  public void waitForIFrameWidgetLoad() {
    switchToIframeWithNameOrId("custom-widget-iframe");
    $(".container.frame").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    switchBackToParent();
  }

  public String getTaskUuid() {
    return $("a[id$='show-more-note-link']").getAttribute("href").split("uuid=")[1];
  }
  
  public String getTaskId() {
    return findElementByCssSelector("span[id$='general-information:task-id']").getText();
  }


  public List<String> getTaskNoteHasAuthors() {
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1600));
    $("th.task-document-author").shouldBe(appear);
    List<SelenideElement> noteAuthorElements =
        $$("td.task-document-author .name-after-avatar").shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1));
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }

  public List<String> getTaskNoteAuthors() {
    List<SelenideElement> noteAuthorElements = $$("td.task-document-author .name-after-avatar")
        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(0), DEFAULT_TIMEOUT);
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }

  public void clickBackButton() {
    waitForElementClickableThenClick($("[id$=':task-detail-title-form:back-to-previous-page']"));
  }

  public void switchToCaseInfoIframe() {
    switchToIframeWithId("i-frame-case-details");
  }

  public TaskWidgetPage goBackToTaskListFromTaskDetails() {
    clickBackButton();
    return new TaskWidgetPage();
  }

  public TaskTemplatePage clickStartTask() {
    findElementByCssSelector("[id$=':task-detail-start-command']").click();
    return new TaskTemplatePage();
  }

  public void changePriorityOfTask(int priorityValue) {
    findElementByCssSelector("[id$=':general-information:priority-form:edit-priority-inplace_display']").click();
    waitForElementDisplayed(By.cssSelector("[id$=':general-information:priority-form:priority-select-menu_label']"),
        true);
    findElementByCssSelector("[id$=':general-information:priority-form:priority-select-menu_label']").click();
    SelenideElement prioritySelectElement = findElementByCssSelector(
        String.format("[id$=':general-information:priority-form:priority-select-menu_%d']", priorityValue));
    waitForElementDisplayed(prioritySelectElement, true);
    prioritySelectElement.click();
    clickByJavaScript($("[id$=':general-information:priority-form:edit-priority-inplace_editor'] .ui-inplace-save"));
    waitForElementDisplayed(
        By.cssSelector("[id$=':general-information:priority-form:edit-priority-inplace_editor'] .ui-inplace-save"),
        false);
  }

  public String getTaskNameInDialog() {
    waitForElementDisplayed(
        By.cssSelector("span[id='task-detail-template:task-detail-title-form:task-name-edit-form']"), true);
    return findElementByCssSelector("span[id='task-detail-template:task-detail-title-form:task-name-edit-form']")
        .getText();
  }

  public String getTaskName() {
    String[] breadcrumbTexts = getTextOfCurrentBreadcrumb().split(":");
    int item = breadcrumbTexts.length;
    if (item > 1) {
      return breadcrumbTexts[item - 1].trim();
    }
    return breadcrumbTexts[0].trim();
  }

  public CaseDetailsPage backToCaseDetails() {
    clickBackButton();
    return new CaseDetailsPage();
  }

  public void clickTaskListBreadCrumb() {
    waitForElementClickableThenClick(By.cssSelector(".portal-breadcrumb ol li:nth-of-type(2) .ui-menuitem-link"));
  }

  public void changeEscaltionActivatorTo(String activatorName, boolean isUser) {
    boolean canEditExpiryActivator = canChangeEscalationActivator();
    if (canEditExpiryActivator) {
      clickOnEditEscaltionEditIcon();
      if (isUser) {
        $("input[id$=':user-expiry-activator-select_input']").shouldBe(appear, DEFAULT_TIMEOUT);
        $("input[id$=':user-expiry-activator-select_input']").sendKeys(activatorName);
        $("span[id$=':user-expiry-activator-select_panel']").shouldBe(appear, DEFAULT_TIMEOUT);
        List<SelenideElement> foundUsers = $$("span[id$=':user-expiry-activator-select_panel'] .name-after-avatar");
        foundUsers.get(0).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      } else {
        List<SelenideElement> radioButtonLabels = $$("table[id$='task-escalation-activator-form:activator-type-select'] label");
        radioButtonLabels.get(1).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
        $("input[id$=':group-expiry-activator-select_input']").shouldBe(appear, DEFAULT_TIMEOUT);
        $("input[id$=':group-expiry-activator-select_input']").sendKeys(activatorName);
        $("span[id$=':group-expiry-activator-select_panel']").shouldBe(appear, DEFAULT_TIMEOUT);
        List<SelenideElement> foundRoles = $$("span[id$=':group-expiry-activator-select_panel'] .name-after-avatar");
        foundRoles.get(0).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      }
      $("button[id$=':task-escalation-activator-form:assign-task-command']").shouldBe(getClickableCondition()).click();
      $("div[id$='task-escalation-activator-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
    }
  }
  
  private void clickOnEditEscaltionEditIcon() {
    $(".task-expiry-activator-edit").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[class$='task-expiry-activator-edit']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("form[id$=':task-escalation-activator-form']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public boolean canChangeEscalationActivator() {
    try {
      return $(".task-expiry-activator-edit").isDisplayed();
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  public String getTaskState() {
    $("[id$=':general-information:task-detail-state']").shouldBe(appear, DEFAULT_TIMEOUT);;
    String taskStateClasses = $("i[class*='task-state']").getAttribute("class");
    String taskState = Stream.of(taskStateClasses.trim().split(" ")).filter(style -> style.endsWith("-task-state")).findFirst().orElse("");
    return taskState;
  }

  public String updateDelayTimestamp(String tomorrow, String tomorrowWithLocale) {
    $("span[id$='general-information:delay-form:delay-date_display']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='general-information:delay-form:delay-date-calendar_panel']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id$='delay-form:delay-date-calendar_input']").sendKeys(Keys.chord(Keys.CONTROL, "a"));
    $("[id$='delay-form:delay-date-calendar_input']").sendKeys(Keys.BACK_SPACE);
    $("[id$='delay-form:delay-date-calendar_input']").sendKeys(tomorrow);
    $("[id='role-and-user-information']").$("span.text-ellipsis").click();
    $("[id$='delay-form:delay-date_editor']").shouldBe(appear, DEFAULT_TIMEOUT)
    .$(".ui-inplace-save").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    
    $("span[id$=':delay-form:delay-date_display']").shouldBe(appear, DEFAULT_TIMEOUT);
    String result = $("span[id$=':delay-form:delay-date_display']").getText();
    return result;
  }
  
  public void changeExpiryOfTaskAt(String dateStringLiteral) {
    $("[id$=':expiry-form:edit-inplace_display']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    
    $("[id$=':expiry-form:expiry-calendar']").shouldBe(appear);
    SelenideElement taskExpiryInlineEdit = $("[id$=':expiry-form:expiry-calendar_input']");
    taskExpiryInlineEdit.sendKeys(dateStringLiteral);

    SelenideElement editor = $("[id$=':expiry-form:edit-inplace_editor']");
    SelenideElement saveButton = editor.$(UI_INPLACE_SAVE);
    saveButton.shouldBe(getClickableCondition()).click();
    $("[id$=':expiry-form:edit-inplace_editor']").shouldBe(disappear,
        DEFAULT_TIMEOUT);
  }

  public String getExpiryOfTaskAt() {
    $("[id$=':expiry-form:edit-inplace_display']").shouldBe(appear);
    return $("[id$=':expiry-form:edit-inplace_display']").getText();
  }

  public String getFirstTaskNoteComment() {
    return $("a[id$=':task-notes:task-note-table:0:note-message']").getText();
  }

  public String getAfterEscalation() {
    return $(".task-expiry-activator-name").shouldBe(appear).getText();
  }

  public boolean isClearDelayTimeDisplayed() {
    return $("[id$=':additional-options:task-clear-delay-command']").shouldBe(appear).isDisplayed();
  }
  
  public void clickOnClearDelayTime() {
    $("a[id$=':additional-options:task-clear-delay-command']").shouldBe(getClickableCondition()).click();
    $("[id$=':additional-options:side-steps-panel']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public String getTaskDelayTime() {
    return $("span[id$='general-information:delay-form:delay-date_display']").shouldBe(appear).getText();
  }

  public String getTaskResponsible() {
    return $(".role-and-user-information .task-activator").shouldBe(appear).getText();
  }

  public void openTaskDelegateDialog() {
    openActionPanel();
    $("a[id$=':task-delegate-command']").shouldBe(appear);
    $("a[id$=':task-delegate-command']").shouldBe(getClickableCondition()).click();
    $("div[id$='task-delegate-dialog']").shouldBe(appear);
  }
  
  public void selectDelegateResponsible(String responsibleName, boolean isRole) {
    if(isRole) {
      List<SelenideElement> radioButtonLabels = $$("table[id$='activator-type-select'] label");
      radioButtonLabels.get(1).shouldBe(getClickableCondition()).click();
      $("input[id$='group-activator-select_input']").shouldBe(appear);
      $("input[id$='group-activator-select_input']").sendKeys(responsibleName);
      $("tr[data-item-label='" + responsibleName + "']").shouldBe(appear, DEFAULT_TIMEOUT);
      List<SelenideElement> foundRoles = $$("span[id$='group-activator-select_panel'] .name-after-avatar");
      foundRoles.get(0).shouldBe(getClickableCondition()).click();
    }
    else {
      $("input[id$='user-activator-select_input']").shouldBe(appear);
      $("input[id$='user-activator-select_input']").sendKeys(responsibleName);
      $("tr[data-item-label='" + responsibleName + "']").shouldBe(appear, DEFAULT_TIMEOUT);
      List<SelenideElement> foundUsers = $$("span[id$='user-activator-select_panel'] .name-after-avatar");
      foundUsers.get(0).shouldBe(getClickableCondition()).click();
    }
    $("button[id$='proceed-task-delegate-command']").shouldBe(getClickableCondition()).click();
    $("div[id$='task-delegate-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void addCommentOnTaskDelegationDialog(String comment) {
    $("textarea[id$=':task-item-delegate-component:task-delegate-form:input-text-area-delegate-message']").shouldBe(appear);
    $("textarea[id$=':task-item-delegate-component:task-delegate-form:input-text-area-delegate-message']").sendKeys(comment);
  }

  public void waitForResetButtonDisplayed() {
    $("[id$=':reset-details-settings-button']").shouldBe(appear);
  }

  public boolean isClearDeadlineDisplayed() {
    return $("[id$=':additional-options:task-clear-expiry-command']").shouldBe(appear).exists();
  }
  
  public void clickOnClearDeadlineTime() {
    $("a[id$=':additional-options:task-clear-expiry-command']").shouldBe(getClickableCondition()).click();
    $("[id$=':additional-options:side-steps-panel']").shouldBe(disappear);
  }

  public String getDurationTimeText() {
    return $("span[id$='duration-time']").shouldBe(appear).getText();
  }

  public boolean isDelayTimeDisplayed() {
    try {
      return $("[id$=':delay-form:delay-date']").isDisplayed();
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

}
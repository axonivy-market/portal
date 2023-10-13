package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.axonivy.portal.selenium.common.Sleeper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class CaseDetailsPage extends TemplatePage {
  private static final String RELATED_CASES = "Related Cases";
  private static final String RELATED_TASKS_OF_CASES = "Related Tasks of Case";
  private static final String HISTORY = "History";
  public static final String CLASS_PROPERTY = "class";

  @Override
  protected String getLoadedLocator() {
    return ".js-layout-content";
  }

  public void waitForTechnicalCaseDisplay() {
    $("div[id='case-details-technicalCase-panel']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getRelatedCasesComponent() {
    $("div[id='case-item-details:case-details-container:case-detail-body']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("div[id='case-item-details:case-details-container:case-detail-body']").filter(text(RELATED_CASES));
  }

  public ElementsCollection getRelatedTasksOfCasesComponent() {
    $("div[id='case-item-details:case-details-container:case-detail-body']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("div[id='case-item-details:case-details-container:case-detail-body']")
        .filter(text(RELATED_TASKS_OF_CASES));
  }

  public ElementsCollection getHitoriesComponent() {
    $("div[id='case-item-details:case-details-container:case-detail-body']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("div[id='case-item-details:case-details-container:case-detail-body']").filter(text(HISTORY));
  }

  public void addNote(String noteContent) {
    $("a[id$=':case-histories:add-note-command']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[id$=':case-histories:add-note-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[id$=':case-histories:add-note-dialog']").find("textarea").sendKeys(noteContent);
    $("button[id$=':case-histories:add-note-form:save-add-note-command']").click();
    $("div[id$=':case-histories:add-note-form:save-add-note-command']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getNotesWithContent(String content) {
    return $$("span[id$=':case-histories:case-histories-table'] table tbody tr td a").filter(text(content));
  }

  public void gotoTaskDetailsPageOfRelatedTask(String taskName) {
    $$("div[id='case-details-related-task-table'] table tbody tr td span").filter(text(taskName)).first().click();
  }

  public void gotoCaseDetailsPageOfRelatedCase(String caseName) {
    $$("div[id$=':related-cases-widget:related-cases'] table tbody tr td span").filter(text(caseName)).first().click();
    $("div[id$=':general-information:business-case-information']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void gotoBusinessCase() {
    $("a[id$=':general-information:related-business-case']").click();
    $("div[id$=':general-information:business-case-information']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getRelatedTasksPanel() {
    return $("div[id$='case-details-relatedTask-panel']");
  }

  public void openTaskWithRunTheTaskBehaviour(String taskName) {
    getRelatedTasksPanel().shouldBe(appear, DEFAULT_TIMEOUT);
    var taskItem = $$("div[id='case-details-related-task-table'] table tbody tr td span.task-name-value").filter(text(taskName)).first()
      .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    waitUntilElementToBeClickable(taskItem);
    waitUntilElementToBeClickable(taskItem.parent());
    taskItem.parent().click();
  }
  
  public SelenideElement getNameOfRelatedTask(int index) {
    getRelatedTasksPanel().shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id='case-details-related-task-table'] table tbody").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr").get(index).$$("td")
        .findBy(Condition.attributeMatching("class", ".*related-task-name-column.*")).$("span");
  }
  
  public SelenideElement getStateOfRelatedTask(int index) {
    getRelatedTasksPanel().shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id='case-details-related-task-table'] table tbody").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr").get(index).$$("td")
        .findBy(Condition.attributeMatching("class", ".*related-task-state-column.*")).$("span span");
  }

  public void clickRelatedTaskActionButton(int index) {
    $(String.format("[id$=':related-tasks:%d:additional-options:task-side-steps-menu']", index))
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public void triggerEscalationTask(int index) {
    $(String.format("[id$='task-widget:related-tasks:%d:additional-options:task-trigger-escalation-command']", index))
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$='\\:escalation-task-confirmation-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("button[id$='\\:confirm-escalation']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }


  public SelenideElement getCreatorAvatar() {
    return $(".security-member-container > .has-avatar > .ui-avatar").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getHistoryAuthorAvatar() {
    return $(".case-document-author > .has-avatar > .ui-avatar").shouldBe(exist, DEFAULT_TIMEOUT);
  }

  public SelenideElement destroyLink() {
    return $("a[id$='destroy-case']");
  }

  public void destroy() {
    destroyLink().shouldBe(getClickableCondition()).click();
    confirmDestroy();
  }

  private void confirmDestroy() {
    $("div[id$='destroy-case-confirmation-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("button[id$='confirm-destruction']").shouldBe(getClickableCondition()).click();
  }

  public ElementsCollection countRelatedTasks() {
    return $("div[id$='related-tasks']").shouldBe(appear, DEFAULT_TIMEOUT).$$("td.related-task-name-column");
  }

  public ElementsCollection countRelatedCases() {
    return $("div[id$='related-cases']").shouldBe(appear, DEFAULT_TIMEOUT).$$("td.name-column");
  }

  public void openAdditionalCaseDetailsPage() {
    $("a[id$=':show-additional-case-details-link']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  public ElementsCollection countAdditionalFieldsPage() {
    return $("div[id$='additional-case-detail-table'] > div > table > tbody").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr");
  }

  public SelenideElement firstAdditionalFieldsPage() {
    return countAdditionalFieldsPage().first();
  }

  public void openActionPanel() {
    $("a[id$=':action-group:case-details-action-link']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id$=':action-group:action-steps-panel']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void switchToIframe() {
    switchToIframeWithId("iFrame");
  }
  
  public SelenideElement getShareButton() {
    return $("button[id$=':share-page-button']");
  }

  public SelenideElement getShareDialog() {
    return $("div[id$=':share-case-details-dialog']");
  } 

  public SelenideElement getGeneralInforBox() {
    return $("[id$='case-detail-general-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getRelatedRunningTaskBox() {
    return $("[id$='case-details-related-running-tasks-card']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void waitForCaseDetailsDisplay() {
    $(By.id("case-item-details:case-details-container:case-detail-body")).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getAddNoteDialog() {
    onClickHistoryIcon();
    var result = $("div[id$=':case-histories:add-note-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    waitForAjaxIndicatorDisappeared();
    result.$(".ui-dialog-title").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return result.shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void onClickHistoryIcon() {
    $("a[id$=':case-histories:add-note-command']").shouldBe(getClickableCondition()).click();
  }

  public void openAddAttachmentDialog() {
    $("a[id$='add-document-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getAddAttachmentDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("[id$='document:document-upload-dialog_title']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getAddAttachmentDialog() {
    return $("[id$='document:document-upload-dialog']");
  }

  public void uploadDocumentWithoutError(String pathToFile) {
    Sleeper.sleep(500);
    uploadDocumentByPath(pathToFile);
    $("span[class$='ui-messages-info-summary']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$=':document-upload-close-command']").shouldBe(getClickableCondition()).click();
  }
  
  private void uploadDocumentByPath(String path) {
    $("input[id$='document-upload-panel_input']").shouldBe(Condition.exist, DEFAULT_TIMEOUT).sendKeys(path);
  }

  public void closeUploadDocumentDialog() {
    $(By.cssSelector("button[id$='document:document-upload-close-command']")).shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getDocumentBox() {
    return $("[id$='case-details-document-card']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getHistoriesBox() {
    return $("[id$='history-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getDeleteDocumentConfirmDialog() {
    $("a[id$='delete-file']").shouldBe(getClickableCondition()).click();
    $(By.cssSelector("div[id$='document-deletion-dialog']")).shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id$='document-deletion-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void showNoteHistory() {
    $("a[id$='show-more-note-link']").shouldBe(getClickableCondition()).click();
  }
  
  public void waitForShowNoteHistory() {
    $(".note-history-container").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
  }

  public void waitForIFrameWidgetLoad() {
    $("[name='custom-widget-iframe']").shouldBe(appear, DEFAULT_TIMEOUT);
    switchToIframeWithNameOrId("custom-widget-iframe");
    $("form[id='content-form']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    switchBackToParent();
  }

  public void waitForIFrameURLWidgetLoad() {
    $("[name='custom-widget-iframe-url']").shouldBe(appear, DEFAULT_TIMEOUT);
    switchToIframeWithNameOrId("custom-widget-iframe-url");
    $("a[href='https://www.axonivy.com']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    switchBackToParent();
  }

  public SelenideElement getSharePageButtonElement() {
    return $("[id$=':share-page-button']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getSwitchToEditModeButton() {
    return $("[id$=':switch-to-edit-mode-button']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getSwitchToViewModeButton() {
    return $("[id$=':switch-to-view-mode-button']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getResetButton() {
    $(By.cssSelector("[id$=':reset-details-settings-button']")).shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    return $("[id$=':reset-details-settings-button']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
  }
  
  public void switchToEditMode() {
    $(By.cssSelector("[id$=':switch-to-edit-mode-button']")).shouldBe(getClickableCondition()).click();
    $("[id$='case-details-information-panel']").shouldBe(Condition.visible, DEFAULT_TIMEOUT).shouldHave(Condition.attribute(CLASS_PROPERTY, "grid-stack-item ui-draggable ui-resizable ui-resizable-autohide"));
  }

  public void waitForSaveButtonDisplayed() {
    $(By.cssSelector("[id$=':switch-to-view-mode-button']")).shouldBe(Condition.visible, DEFAULT_TIMEOUT);
  }
  
  public void drapAndDropWidgets(String sourceName, String destinationName) {
    $(By.cssSelector(String.format("[id='case-details-%s-panel']", sourceName))).shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    SelenideElement sourceElement = $(String.format("[id='case-details-%s-panel']", sourceName)).shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    $(By.cssSelector(String.format("[id='case-details-%s-panel']", destinationName))).shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    SelenideElement destinationElement = $(String.format("[id='case-details-%s-panel']", destinationName)).shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    Actions actions = new Actions(getDriver());
    Action moveWidget = actions.dragAndDrop(sourceElement, destinationElement).build();
    moveWidget.perform();
    $("[id$=':case-details-container:case-details-widgets']").shouldBe(Condition.visible, DEFAULT_TIMEOUT).$(".ui-droppable-over").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void saveAndSwitchToViewMode() {
    $(By.cssSelector("[id$=':switch-to-view-mode-button']")).shouldBe(getClickableCondition()).click();
  }
  
  public void resetToDefault() {
    $(By.cssSelector("[id$=':reset-details-settings-button']")).shouldBe(getClickableCondition()).click();
  }
}

package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.axonivy.portal.selenium.common.Sleeper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TaskDetailsPage extends TemplatePage {

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
    $("[id$=':additional-options:task-detail-more-step']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$=':additional-options:side-steps-panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  private void openTriggerEscalationDialog() {
    $("a[id$='\\:task-trigger-escalation-command']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$='\\:escalation-task-confirmation-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void triggerEscalation() {
    openTriggerEscalationDialog();
    $("button[id$='\\:confirm-escalation']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getPriorityOfTask() {
    return $("span[id$='task-priority']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("i[class*='priority']").closest("span");
  }

  public SelenideElement getStateOfTask() {
    return $("[id$=':general-information:task-detail-state']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("i[class*='task-state']").closest("span");
  }

  public void back() {
    $("[id$=':task-detail-title-form:back-to-previous-page']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
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
    return actionPanel.findElements(By.cssSelector("a[class*='option-item']")).stream().map(WebElement::getText).collect(Collectors.toList());
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
    $(By.cssSelector("[id$=':switch-to-edit-mode-button']")).shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$='task-details-information-panel']").shouldBe(Condition.visible, DEFAULT_TIMEOUT).shouldHave(Condition.attribute(CLASS_PROPERTY, "grid-stack-item ui-draggable ui-resizable ui-resizable-autohide"));
  }

  public void waitForSwitchToViewModeButtonDisplayed() {
    $(By.cssSelector("[id$=':switch-to-view-mode-button']")).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void drapAndDropWidgets(String sourceName, String destinationName) {
    SelenideElement sourceElement = $(String.format("[id$=':task-detail-%s-container']", sourceName)).shouldBe(appear, DEFAULT_TIMEOUT);
    SelenideElement destinationElement = $(By.cssSelector(String.format("[id$=':task-detail-%s-container']", destinationName))).shouldBe(appear, DEFAULT_TIMEOUT);
    Actions actions = new Actions(WebDriverRunner.getWebDriver());
    Action moveWidget = actions.dragAndDrop(sourceElement, destinationElement).build();
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
    waitForAjaxIndicatorDisappeared();
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
    $("[id$=':task-documents:add-document-command']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$=':task-documents:document-upload-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getAddAttachmentDialog() {
    var uploadDialog = $("[id$=':task-documents:document-upload-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    uploadDialog.$(".ui-dialog-title").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return uploadDialog;
  }

  public void uploadDocument(String path) {
    Sleeper.sleep(500);
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
    List<WebElement> cells = $("tbody[id$='events-table_data']").shouldBe(appear, DEFAULT_TIMEOUT).findElements(By.cssSelector("td"));
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
    $("a[href='https://www.axonivy.com']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
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
}

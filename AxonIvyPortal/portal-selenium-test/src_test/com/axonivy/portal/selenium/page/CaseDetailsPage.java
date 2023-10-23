package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

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
  private static final String VIEW_NOTE_DIALOG_SELECTOR = "[id$=':case-histories:view-note-dialog']";
  private static final String RELATED_CASES = "Related Cases";
  private static final String RELATED_TASKS_OF_CASES = "Related Tasks of Case";
  private static final String HISTORY = "History";
  public static final String CLASS_PROPERTY = "class";
  private SelenideElement caseItem;

  @Override
  protected String getLoadedLocator() {
    return ".js-layout-content";
  }

  public CaseDetailsPage() {
    this.caseItem = findElementByCssSelector("#main-area-panel");
  }

  public void waitForTechnicalCaseDisplay() {
    $("div[id='case-details-technicalCase-panel']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getRelatedCasesComponents() {
    $("div[id='case-item-details:case-details-container:case-detail-body']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("div[id='case-item-details:case-details-container:case-detail-body']").filter(text(RELATED_CASES));
  }

  public ElementsCollection getRelatedTasksOfCasesComponent() {
    $("div[id='case-item-details:case-details-container:case-detail-body']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("div[id='case-item-details:case-details-container:case-detail-body']").filter(text(RELATED_TASKS_OF_CASES));
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
    var taskItem = $$("div[id='case-details-related-task-table'] table tbody tr td span.task-name-value").filter(text(taskName)).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    waitUntilElementToBeClickable(taskItem);
    waitUntilElementToBeClickable(taskItem.parent());
    taskItem.parent().click();
  }

  public SelenideElement getNameOfRelatedTask(int index) {
    getRelatedTasksPanel().shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id='case-details-related-task-table'] table tbody").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr").get(index).$$("td").findBy(Condition.attributeMatching("class", ".*related-task-name-column.*")).$("span");
  }

  public SelenideElement getStateOfRelatedTask(int index) {
    getRelatedTasksPanel().shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id='case-details-related-task-table'] table tbody").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr").get(index).$$("td").findBy(Condition.attributeMatching("class", ".*related-task-state-column.*"))
        .$("span span");
  }

  public void clickRelatedTaskActionButton(int index) {
    $(String.format("[id$=':related-tasks:%d:additional-options:task-side-steps-menu']", index)).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public void triggerEscalationTask(int index) {
    $(String.format("[id$='task-widget:related-tasks:%d:additional-options:task-trigger-escalation-command']", index)).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
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
    $("div[id$='destroy-case-confirmation-dialog']").shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='confirm-destruction']").shouldBe(getClickableCondition()).click();
  }

  public ElementsCollection countRelatedTasks() {
    return $("div[id$='related-tasks']").shouldBe(appear, DEFAULT_TIMEOUT).$$("td.related-task-name-column");
  }

  public int countRelatedCases() {
    return $("div[id$='related-cases']").shouldBe(appear, DEFAULT_TIMEOUT).$$("td.name-column").size();
  }

  public void openAdditionalCaseDetailsPage() {
    $("a[id$=':show-additional-case-details-link']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public ElementsCollection countAdditionalFieldsPage() {
    return $("div[id$='additional-case-detail-table'] > div > table > tbody").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr");
  }

  public SelenideElement firstAdditionalFieldsPage() {
    return countAdditionalFieldsPage().first();
  }

  public void openActionPanel() {
    $("a[id$=':action-group:case-details-action-link']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
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

  public void openAddAttachmentDialog() {
    $("a[id$='add-document-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getAddAttachmentDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("[id$='document:document-upload-dialog_title']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

//  public SelenideElement getAddAttachmentDialog() {
//    return $("[id$='document:document-upload-dialog']");
//  }

//  public void uploadDocumentWithoutError(String pathToFile) {
//    Sleeper.sleep(500);
//    uploadDocumentByPath(pathToFile);
//    $("span[class$='ui-messages-info-summary']").shouldBe(appear, DEFAULT_TIMEOUT);
//    $("button[id$=':document-upload-close-command']").shouldBe(getClickableCondition()).click();
//  }

  private void uploadDocumentByPath(String path) {
    $("input[id$='document-upload-panel_input']").shouldBe(Condition.exist, DEFAULT_TIMEOUT).sendKeys(path);
  }
//
//  public void closeUploadDocumentDialog() {
//    $(By.cssSelector("button[id$='document:document-upload-close-command']")).shouldBe(getClickableCondition()).click();
//  }

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
//
//  public void showNoteHistory() {
//    $("a[id$='show-more-note-link']").shouldBe(getClickableCondition()).click();
//  }

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

//  public void switchToEditMode() {
//    $(By.cssSelector("[id$=':switch-to-edit-mode-button']")).shouldBe(getClickableCondition()).click();
//    $("[id$='case-details-information-panel']").shouldBe(Condition.visible, DEFAULT_TIMEOUT).shouldHave(Condition.attribute(CLASS_PROPERTY, "grid-stack-item ui-draggable ui-resizable ui-resizable-autohide"));
//  }
//
//  public void waitForSaveButtonDisplayed() {
//    $(By.cssSelector("[id$=':switch-to-view-mode-button']")).shouldBe(Condition.visible, DEFAULT_TIMEOUT);
//  }

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
//
//  public void saveAndSwitchToViewMode() {
//    $(By.cssSelector("[id$=':switch-to-view-mode-button']")).shouldBe(getClickableCondition()).click();
//  }
//
//  public void resetToDefault() {
//    $(By.cssSelector("[id$=':reset-details-settings-button']")).shouldBe(getClickableCondition()).click();
//  }

  public boolean isCaseDescriptionChangeComponentPresented(int caseIndex) {
    return isElementPresent(By.id(String.format("case-widget:case-list-scroller:%d:case-item:case-body:case-description-input", caseIndex)));
  }

  public String getCaseDuration() {
    return caseItem.findElement(By.cssSelector("span[id$='case-duration-time']")).getText();
  }

  public String getCaseCategory() {
    return caseItem.findElement(By.cssSelector("span[id$='case-category']")).getText();
  }

  public String getCaseState() {
    return caseItem.findElement(By.cssSelector("span.state-with-indicator > span")).getText();
  }

  public void openActionMenu() {
    waitForElementClickableThenClick($("[id$=':action-group:case-details-action-link']"));
    waitForElementDisplayed(By.cssSelector("[id$=':action-group:action-steps-panel'].action-steps-panel"), true);
  }

  public void onClickHistoryIcon() {
    waitForElementClickableThenClick($("a[id$=':case-histories:add-note-command']"));
    waitAjaxIndicatorDisappear();
  }

  public void onClickDestroyCase() {
    waitForElementClickableThenClick($("a[id$=':action-group:destroy-case']"));
  }

  public void confimDestruction() {
    String destroyCaseDialogSelector = "[id$=':destroy-case-confirmation-dialog']";
    $(By.cssSelector(destroyCaseDialogSelector)).shouldBe(appear, DEFAULT_TIMEOUT).$("[id$=':confirm-destruction']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public ElementsCollection getNumberOfHistoryForRelatedCaseLink() {
    waitForElementDisplayed(By.cssSelector("[id$=':case-histories:case-histories']"), true);
    return $$("td.history-related-case a[id$=':related-case-link']");
  }

  public ElementsCollection getNumberOfHistory() {
    $("[id$=':history-container']").shouldBe(Condition.visible, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    return caseItem.$$(By.cssSelector(HISTORY_LIST_CSS_SELECTOR));
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

  public boolean isRelatedCasesComponentPresented() {
    return getRelatedCasesComponent().isDisplayed();
  }

  private WebElement getRelatedCasesComponent() {
    return findElementByCssSelector(RELATED_CASES_COMPONENT_ID);
  }

  public String getLatestHistoryContent() {
    return caseItem.findElement(By.cssSelector(LATEST_HISTORY_LIST_CSS_SELECTOR)).getText();
  }

  public boolean isBusinessCaseInformationSectionDisplayed() {
    return isElementDisplayed(By.cssSelector("div[id$='business-case-information']"));
  }

  public String getCaseId() {
    return findElementByCssSelector("span[id$='general-information:case-id']").getText();
  }

  public void clickRelatedCaseActionButton(int index) {
    WebElement element = $$(".related-cases .more-column .action-link").get(index);
    element.click();
    String actionPanel = String.format("[id$='related-cases-widget:related-cases:%d:action-step-component:action-steps-panel']", index);
    waitForElementDisplayed(By.cssSelector(actionPanel), true);
  }

  public CaseDetailsPage openCasesOfCasePageViaDetailsAction(int index) {
    String openDetailsCommandButton = String.format("[id$='related-cases-widget:related-cases:%d:action-step-component:case-item-open-detail-link']", index);
    waitForElementDisplayed(By.cssSelector(openDetailsCommandButton), true);
    findElementByCssSelector(openDetailsCommandButton).click();
    waitForElementDisplayed(By.cssSelector(openDetailsCommandButton), false);
    // waitForElementPresent(By.cssSelector(openDetailsCommandButton), false);
    return new CaseDetailsPage();
  }

  public CaseDetailsPage openBusinessCaseFromTechnicalCase() {
    caseItem.findElement(By.cssSelector("a[id$='related-business-case']")).click();
    // waitForElementPresent(By.cssSelector("div[id$='business-case-information']"),
    // false);
    waitForElementDisplayed(By.cssSelector("div[id$='business-case-information']"), false);
    return new CaseDetailsPage();
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
    if ((checkboxShouldBeChecked && checkbox.getAttribute(CLASS).contains("ui-state-active")) || (!checkboxShouldBeChecked && !checkbox.getAttribute(CLASS).contains("ui-state-active"))) {
      return;
    } else {
      relatedCaseCheckbox.findElement(By.cssSelector("span.ui-chkbox-label")).click();
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

  public String getCaseName() {
    return getTextOfCurrentBreadcrumb().replace("Case: ", "");
  }

  public String getTextOfCurrentBreadcrumb() {
    WebElement breadcrumb = findElementByCssSelector(CURRENT_BREADCRUMB_SELECTOR);
    String result = "";
    if (CollectionUtils.isNotEmpty(breadcrumb.findElements(By.cssSelector(".js-count")))) {
      result = breadcrumb.findElement(By.cssSelector(".ui-menuitem-text")).getAttribute("innerHTML") + breadcrumb.findElement(By.cssSelector(".js-count")).getAttribute("innerHTML");
    } else {
      result = breadcrumb.findElement(By.cssSelector(".ui-menuitem-text")).getAttribute("innerHTML");
    }
    return result;

  }

  public String getContentOfHistoryTableRelatedCaseColumn(int rowIndex) {
    waitForElementDisplayed(By.cssSelector("[id$=':case-histories:case-histories']"), true);
    return findElementByCssSelector(String.format("td.history-related-case a[id$='case-histories:%d:related-case-link']", rowIndex)).getText();
  }

  public boolean iframeCustomWidgetIsDisplayed() {
    return findElementByCssSelector("iframe[name='custom-widget-iframe']").isDisplayed();
  }

  public void resetToDefault() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-details-settings-button']"), true);
    $(By.cssSelector("[id$=':reset-details-settings-button']")).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

//    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void confirmResetToDefault() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-to-default-case-form:confirm-destruction']"), true);
    $(By.cssSelector("[id$=':reset-to-default-case-form:confirm-destruction']")).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    // waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void switchToEditMode() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-edit-mode-button']"), true);
    $(By.cssSelector("[id$=':switch-to-edit-mode-button']")).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    waitAjaxIndicatorDisappear();
    com.axonivy.portal.selenium.common.WaitHelper.assertTrueWithWait(() -> {
      var infoWidget = findElementByCssSelector("[id$='case-details-information-panel']");
      return infoWidget.getAttribute(CLASS_PROPERTY).contains("ui-resizable ui-resizable-autohide");
    });
  }

  public void waitForSaveButtonDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-view-mode-button']"), true);
  }

  public void saveAndSwitchToViewMode() {
    waitForElementDisplayed(By.cssSelector("[id$=':switch-to-view-mode-button']"), true);
    $(By.cssSelector("[id$=':switch-to-view-mode-button']")).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    // waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

//  public void drapAndDropWidgets(String sourceName, String destinationName) {
//    waitForElementDisplayed(By.cssSelector(String.format("[id='case-details-%s-panel']", sourceName)), true);
//    WebElement sourceElement = findElementByCssSelector(String.format("[id='case-details-%s-panel']", sourceName));
//    waitForElementDisplayed(By.cssSelector(String.format("[id='case-details-%s-panel']", destinationName)), true);
//    WebElement destinationElement = findElementByCssSelector(String.format("[id='case-details-%s-panel']", destinationName));
//    Actions actions = new Actions(WebDriverRunner.getWebDriver());
//    Action moveWidget = actions.dragAndDrop(sourceElement, destinationElement).build();
//    moveWidget.perform();
//    WaitHelper.assertTrueWithWait(() -> {
//      var caseDetails = findElementByCssSelector("[id$=':case-details-container:case-details-widgets']");
//      return !caseDetails.getAttribute(CLASS_PROPERTY).contains("ui-droppable-over");
//    });
//  }

  public void waitForResetButtonDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-details-settings-button']"), true);
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

  public void clickViewNote() {
    waitForElementClickableThenClick(LATEST_HISTORY_LIST_CSS_SELECTOR);
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed($(VIEW_NOTE_DIALOG_SELECTOR), true);
  }

  public boolean isViewNoteDialogPresented() {
    return getViewNoteDialog().isDisplayed();
  }

  private WebElement getViewNoteDialog() {
    return findElementByCssSelector(VIEW_NOTE_DIALOG_SELECTOR);
  }

  private boolean isHistoryExistent(List<WebElement> histories, String historyContent) {
    for (WebElement history : histories) {
      if (history.getText().equals(historyContent)) {
        return true;
      }
    }
    return false;
  }

  public boolean checkDoneTasksOfHistory() {
    List<WebElement> taskNames = caseItem.findElement(By.cssSelector("div[id$='related-tasks']")).findElements(By.cssSelector("span.task-name-value"));
    List<WebElement> taskStates = caseItem.findElement(By.cssSelector("div[id$='related-tasks']")).findElements(By.cssSelector("span.task-state"));
    List<WebElement> histories = caseItem.findElement(By.cssSelector("div[id$='case-histories']")).findElements(By.cssSelector("a.task-note-link"));
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

  public void showNoteHistory() {
    findElementByCssSelector("a[id$='show-more-note-link']").click();
  }

  public int countRelatedDoneTasks() {
    return caseItem.findElement(By.cssSelector("div[id$='related-tasks']")).findElements(By.cssSelector(".task-state.done-task-state")).size();
  }

  public String getHistoryAuthor() {
    return findElementByCssSelector(".history-fullname").getText();
  }

  public void clickRelatedTaskColumnCheckbox(int columnIndex) {
    WebElement columnCheckbox = $(By.xpath(String.format("//*[contains(@id,\":task-widget:task-columns-configuration:select-columns-form:columns-checkbox\")]/tbody/tr[%s]/td/div/div[2]", columnIndex)));
    columnCheckbox.click();
  }

  public boolean isRelatedTaskListColumnExist(String columnClass) {
    return $(".case-details-related-task-table th." + columnClass).isDisplayed();
  }

  public void clickRelatedTaskDefaultCheckbox() {
    WebElement columnCheckbox = $(By.xpath(("//*[contains(@id,\":task-widget:task-columns-configuration:select-columns-form:default-columns\")]/div[2]")));
    columnCheckbox.click();
    WaitHelper.assertTrueWithWait(() -> !findElementByCssSelector("label[for$='task-widget:task-columns-configuration:select-columns-form:columns-checkbox:5']").getAttribute("class").equals("ui-state-disabled"));
  }

  public void clickRelatedTaskColumnsButton() {
    $("a[id$='task-config-command']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick("a[id$='task-config-command']");
    waitForElementDisplayed($("label[for$='task-widget:task-columns-configuration:select-columns-form:columns-checkbox:5']"), true);
  }

  public void clickRelatedTaskApplyButton() {
    $(By.cssSelector("button[id$='task-widget:task-columns-configuration:select-columns-form:update-command']")).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    waitAjaxIndicatorDisappear();
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

  public WebElement getExportToExcelLink(String linkId) {
    return findElementByCssSelector("a[id$=':" + linkId + "']");
  }

  public boolean isDownloadCompleted(String statusDialogId) {
    WebElement statusDialog = driver.findElement(By.cssSelector("div[id$=':" + statusDialogId + "']"));
    WaitHelper.assertTrueWithWait(() -> StringUtils.isNotBlank(statusDialog.getAttribute("download-status")));
    return StringUtils.equals(statusDialog.getAttribute("download-status"), "completed");
  }

  public Integer getTaskRowIndex(String taskName) {
    List<SelenideElement> taskNames = $$(".task-name-value");
    int taskIndex = IntStream.range(0, taskNames.size()).filter(i -> taskNames.get(i).getText().equals(taskName)).findFirst().getAsInt();
    return taskIndex;
  }

  public void reserveTask(String taskName) {
    Integer index = getTaskRowIndex(taskName);
    String reserveCommandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-reserve-command']", index);
    waitForElementDisplayed(By.cssSelector(reserveCommandButton), true);
    findElementByCssSelector(reserveCommandButton).click();
    waitAjaxIndicatorDisappear();
  }

  public void clickRelatedTaskActionButton(String taskName) {
    Integer index = getTaskRowIndex(taskName);
    $(String.format("[id$=':related-tasks:%d:additional-options:task-side-steps-menu']", index)).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    String actionPanel = String.format("[id$='task-widget:related-tasks:%d:additional-options:side-steps-panel']", index);
    waitForElementDisplayed(By.cssSelector(actionPanel), true);
  }

  public ExpressProcessPage addAdHocTask(int index) {
    String commandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-additional-actions']", index);
    waitForElementDisplayed(By.cssSelector(commandButton), true);
    findElementByCssSelector(commandButton).click();
    return new ExpressProcessPage();
  }

  public int getTaskRowIndexFromDetailPage(String taskName) {
    List<SelenideElement> taskNames = $$(".task-name-value");
    int taskIndex = IntStream.range(0, taskNames.size()).filter(i -> taskNames.get(i).getText().equals(taskName)).findFirst().getAsInt();
    return taskIndex;
  }

  public void openRelatedTaskWorkflowEvents(int index) {
    String commandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-workflow-event-command", index);
    waitForElementDisplayed(By.cssSelector(commandButton), true);
    findElementByCssSelector(commandButton).click();
    waitAjaxIndicatorDisappear();
  }

  public boolean isRelatedTaskWorkflowEventsOpened() {
    try {
      waitForElementDisplayed(By.cssSelector("div[id$='task-widget:workflow-event-component:workflow-events-dialog']"), true);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public NewDashboardPage clickRelatedCaseUploadAdditionalDocument(int index) {
    WebElement sideSteps = findElementByCssSelector(String.format("[id$='related-cases-widget:related-cases:%d:action-step-component:side-steps']", index));
    $(sideSteps).$(By.linkText("Upload additional data")).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return new NewDashboardPage();
  }

  public AdditionalCaseDetailsPage openRelatedCaseBusinessDetail(int index) {
    String openDetailsCommandButton = String.format("[id$='related-cases-widget:related-cases:%d:action-step-component:show-additional-case-details-link']", index);
    waitForElementClickableThenClick($(By.cssSelector(openDetailsCommandButton)));
    return new AdditionalCaseDetailsPage();
  }

  public NewDashboardPage clickRelatedCaseSubmitLeaveReason(int index) {
    WebElement sideSteps = findElementByCssSelector(String.format("[id$='related-cases-widget:related-cases:%d:action-step-component:side-steps']", index));
    $(sideSteps).$(By.linkText("Submit leave reason")).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return new NewDashboardPage();
  }

  public TaskDetailsPage openTasksOfCasePageViaDetailsAction(int index) {
    String openDetailsCommandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-open-detail-command']", index);
    waitForElementDisplayed(By.cssSelector(openDetailsCommandButton), true);
    findElementByCssSelector(openDetailsCommandButton).click();
    return new TaskDetailsPage();
  }

  public String getResponsibleOfRelatedTaskAt(String taskName) {
    List<SelenideElement> taskRows = $$("tr.ui-widget-content");
    return taskRows.stream().filter(row -> row.$(".task-name-value").getText().equals(taskName)).map(row -> row.$(".name-after-avatar").getText()).findAny().get();
  }

  public void openTaskDelegateDialog(String taskName) {
    try {
      clickRelatedTaskActionButton(taskName);
    } catch (Exception e) {
      clickRelatedTaskActionButton(taskName);
    }

    waitForElementDisplayed(By.cssSelector("a[id$='\\:task-delegate-command']"), true);
//    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> findElementByCssSelector("a[id$='\\:task-delegate-command']").isDisplayed());
    waitForElementClickableThenClick($("a[id$='\\:task-delegate-command']"));
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), true);
  }

  public boolean isTaskDelegateOptionDisable(String taskName) {
    clickRelatedTaskActionButton(taskName);
    Integer index = getTaskRowIndex(taskName);
    String commandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-delegate-command", index);
    waitForElementDisplayed(By.cssSelector(commandButton), true);
    WebElement delegateButton = findElementByCssSelector(commandButton);
    return delegateButton.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public boolean isRelatedTaskDestroyEnabled(String taskName) {
    WebElement destroyButton = findDestroyCommand(taskName);
    return !destroyButton.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public void selectDelegateResponsible(String responsibleName, boolean isRole) {
    if (isRole) {
      waitForElementDisplayed(By.cssSelector("[id$=':task-delegate-form:activator-type-select']"), true);
      waitForElementEnabled(By.cssSelector("[id$=':task-delegate-form:activator-type-select:1']"), true);
      waitForElementClickableThenClick($("[for$=':task-delegate-form:activator-type-select:1']"));
      waitAjaxIndicatorDisappear();
      waitForElementDisplayed(By.cssSelector("input[id$='group-activator-select_input']"), true);
      $(By.cssSelector("input[id$='group-activator-select_input']")).sendKeys(responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='group-activator-select_panel']"), true);
      List<SelenideElement> foundRoles = $$("span[id$='group-activator-select_panel'] .name-after-avatar");
      foundRoles.get(0).click();
    } else {
      waitForElementDisplayed(By.cssSelector("input[id$='user-activator-select_input']"), true);
      $(By.cssSelector("input[id$='user-activator-select_input']")).sendKeys(responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='user-activator-select_panel']"), true);
      List<SelenideElement> foundUsers = $$("span[id$='user-activator-select_panel'] .name-after-avatar");
      foundUsers.get(0).click();
    }
//    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    waitAjaxIndicatorDisappear();
    waitForElementClickableThenClick($(By.cssSelector("button[id$='proceed-task-delegate-command']")));
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), false);
  }

  public boolean isDelegateTypeSelectAvailable() {
    return isElementPresent(By.cssSelector("div[id$=':activator-panel']"));
  }

  public void confimRelatedTaskDestruction() {
    String destroyCaseDialogId = "[id$='task-widget:destroy-task-confirmation-dialog']";
    waitForElementDisplayed(By.cssSelector(destroyCaseDialogId), true);
    WebElement confirmButton = $(destroyCaseDialogId).$("[id$='task-widget:confirm-destruction']");
    confirmButton.click();
    waitAjaxIndicatorDisappear();
    // waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void destroyTask(String taskName) {
    findDestroyCommand(taskName).click();
//    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    waitAjaxIndicatorDisappear();
  }

  public WebElement findDestroyCommand(String taskName) {
    Integer index = getTaskRowIndex(taskName);
    String destroyCommandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-destroy-command", index);
    waitForElementDisplayed(By.cssSelector(destroyCommandButton), true);
    return findElementByCssSelector(destroyCommandButton);
  }

  public boolean isTaskState(String taskName, TaskBusinessState taskState) {
    Integer index = getTaskRowIndex(taskName);
    WebElement element = $$("td.related-task-state-column span.task-state").get(index);
    if (element != null) {
      String stateClass = element.getAttribute(CLASS);
      return stateClass.contains(taskState.toString().toLowerCase() + "-task-state");
    }
    return false;
  }

  public String getEventTypeInWorkflowEvents() {
    waitForElementDisplayed(By.cssSelector("[id$=':task-widget:workflow-event-component:events-table_data']"), true);
    var eventTypeList = findElementByCssSelector("[id$=':task-widget:workflow-event-component:events-table_data']").getText();
    return eventTypeList;
  }

  public boolean isRelatedTaskStartEnabled(String taskName) {
    Integer index = getTaskRowIndex(taskName);
    WebElement element = $$("[id$='task-action-component']").get(index);
    return !element.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public TaskTemplatePage startRelatedTask(String taskName) {
    Integer index = getTaskRowIndex(taskName);
    WebElement element = $$("[id$='task-action-component']").get(index);
    element.click();
    return new TaskTemplatePage();
  }

  public void resetTask(String taskName) {
    Integer index = getTaskRowIndex(taskName);
    String resetCommandButton = String.format("[id$='task-widget:related-tasks:%d:additional-options:task-reset-command", index);
    waitForElementDisplayed(By.cssSelector(resetCommandButton), true);
    findElementByCssSelector(resetCommandButton).click();
    // waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    waitAjaxIndicatorDisappear();
  }

  public int countNumberOfDocument() {
    return $$("a[id$='download']").size();
  }

  public boolean checkNumberOfDocument(int number) {
    return $$("a[id$='download']").size() == number;
  }

  public void uploadDocumentWithoutError(String pathToFile) {
    openAddDocumentDialogAndUploadDocument(pathToFile);
    $("span[class$='ui-messages-info-summary']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$='document:document-upload-close-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void openAddDocumentDialogAndUploadDocument(String pathToFile) {
    getAddAttachmentDialog();
    $("input[id$='document-upload-panel_input']").shouldBe(exist, DEFAULT_TIMEOUT).shouldBe(Condition.hidden, DEFAULT_TIMEOUT).sendKeys(pathToFile);
  }

  public SelenideElement getAddAttachmentDialog() {
    $("a[id$='add-document-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("span[id$='document-upload-dialog_title']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $("[id$='document:document-upload-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public boolean checkUploadDocumentErrorContent(String error) {
    return $("div[id$='upload-messages']").shouldBe(appear, DEFAULT_TIMEOUT).is(Condition.text(error));
  }

  public void closeUploadDocumentDialog() {
    $("button[id$='document-upload-close-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='document:document-upload-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection findDocumentItemInCaseDetailsDocumentTable() {
    return $$("a[id$='download']");
  }

  public void clickCaseListBreadCrumb() {
    waitForElementClickableThenClick($((By.cssSelector(".portal-breadcrumb ol li:nth-of-type(2) .ui-menuitem-link"))));
  }

  public boolean isRelatedCaseListColumnExist(String columnClass) {
    return $(By.cssSelector(".related-cases-container th." + columnClass)).isDisplayed();
  }

  public void clickRelatedCaseColumnsButton() {
    waitForElementClickableThenClick($(("a[id$='case-config-button']")));
    ;
    waitForElementDisplayed($("label[for$='related-cases-widget:case-columns-configuration:select-columns-form:columns-checkbox:3']"), true);
//    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void clickRelatedCaseColumnCheckbox(int columnIndex) {
    String xpath = String.format("//*[contains(@id,\":related-cases-widget:case-columns-configuration:select-columns-form:columns-checkbox\")]/tbody/tr[%s]/td/div/div[2]", columnIndex);
    waitForElementClickableThenClick($(By.xpath(xpath)));
  }

  public void clickRelatedCaseDefaultCheckbox() {
    String xpath = "//*[contains(@id,\":related-cases-widget:case-columns-configuration:select-columns-form:default-columns\")]/div[2]";
    waitForElementClickableThenClick($(By.xpath(xpath)));
    WaitHelper
        .assertTrueWithWait(() -> !findElementByCssSelector("label[for$='related-cases-widget:case-columns-configuration:select-columns-form:columns-checkbox:3']").getAttribute("class").equals("ui-state-disabled"));
  }

  public void clickRelatedCaseApplyButton() {
    waitForElementClickableThenClick($(By.cssSelector("button[id$='related-cases-widget:case-columns-configuration:select-columns-form:update-command']")));
//    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
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

  public void openProcessOverviewPage() {
    waitForElementClickableThenClick(PROCESS_OVERVIEW_URL_CSS_SELECTOR);
  }

  public List<String> getAvailableActionSteps() {
    waitForElementDisplayed(By.cssSelector("[id$=':action-group:action-steps-panel'].action-steps-panel"), true);
    var steps = $$("[id$=':action-group:action-steps-panel'].action-steps-panel a.action-step-item");
    return steps.asFixedIterable().stream().map(WebElement::getText).collect(Collectors.toList());
  }

  public List<String> getAvailableActionStepsOfTechnicalCase(int caseIndex) {
    var panelId = String.format("[id$=':related-cases-widget:related-cases:%d:action-step-component:action-steps-panel']", caseIndex);
    waitForElementDisplayed(By.cssSelector(panelId), true);
    var steps = $$(panelId + " a.action-step-item");
    return steps.asFixedIterable().stream().map(WebElement::getText).collect(Collectors.toList());
  }

}

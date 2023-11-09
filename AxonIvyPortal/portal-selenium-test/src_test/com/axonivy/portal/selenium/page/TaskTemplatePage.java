package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TaskTemplatePage extends TemplatePage {
  private static final String CASE_INFO_IFRAME_ID = "i-frame-case-details";

  @Override
  protected String getLoadedLocator() {
    return "#content-container";
  }

  public SelenideElement getDisplayedTaskTitle() {
    return getTaskTitle().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getTaskTitle() {
    return $("span[id='title']");
  }

  public SelenideElement getStartedTaskTemplateTitle() {
    return $("span[id='title']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openCaseInfo() {
    waitForElementClickableThenClick("[id='horizontal-case-info']");
    waitForElementDisplayed(By.cssSelector("[id$='i-frame-case-details']"), true);
    driver.switchTo().defaultContent();
  }

  public void switchToCaseInfoIframe() {
    WaitHelper.waitForIFrameAvailable(driver, CASE_INFO_IFRAME_ID);
  }

  public void addNewNote(String content) {
    switchToCaseInfoIframe();
    waitForElementClickableThenClick("a[id$='add-note-command']");
    waitForElementDisplayed(By.cssSelector("div[id$='add-note-dialog']"), true);
    findElementByCssSelector("textarea[id$='note-content']").sendKeys(content);
    int beginCounts = countNoteItems();
    waitForElementClickableThenClick("button[id$='save-add-note-command']");
    WaitHelper.assertTrueWithWait(() -> countNoteItems() != beginCounts);
  }

  public int countNoteItems() {
    return $$("a[id$=':note-link']").size();
  }

  public String getCaseName() {
    driver.switchTo().defaultContent();
    return findElementByCssSelector("span[id$='case-info-dialog_title']").getText().split(":")[1].trim();
  }

  public SelenideElement getElementInPortalIFramTask(String cssSelector) {
    WaitHelper.waitForIFrameAvailable(WebDriverRunner.getWebDriver(), "iFrame");
    return $(cssSelector);
  }

  public void clickCancelButton() {
    $("a[id$='button-cancel']").shouldBe(getClickableCondition()).click();
  }

  public void clickActionButton() {
    $("[id='horizontal-task-actions']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='horizontal-task-action-form:horizontal-task-action-menu']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOnStartAdhocLink() {
    $("div[id$='horizontal-task-action-form:horizontal-task-action-menu']").$("a[id$='start-adhoc']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='adhoc-task-reset-confirmation-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickAdhocOkButton() {
    $("button[id$='start-adhoc-ok-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='adhoc-task-reset-confirmation-dialog_content']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void closeAdhocHistoryDialog() {
    $("button[id$='close-adhoc-dialog-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='adhoc-task-history-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public WebElement openAdhocHistoryDialog() {
    clickActionButton();
    $("[id='horizontal-task-action-form:show-adhoc-history']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $("div[id$='adhoc-task-history-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickChatGroup() {
    $("a[id$='chat-group']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public WebElement getAddMemberToChatDialog() {
    return $("[id='chat-assignee-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickCreateGroupChatButton() {
    $("[id='chat-assignee-selection-form:chat-group-create-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='chat-assignee-selection-form:chat-group-create-button']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public String getTaskName() {
    return getTextOfCurrentBreadcrumb().replace("Task: ", "");
  }

  public void clickCancelLink() {
    waitForElementClickableThenClick($(By.linkText("Cancel")));
  }

  public boolean isTaskNameDisplayed() {
    return isElementDisplayedById("title");
  }

  public boolean isTaskActionDisplayed() {
    return isElementDisplayedById("horizontal-task-actions");
  }

  public boolean isCaseInfoButtonDisplayed() {
    return isElementDisplayedById("horizontal-case-info");
  }

  public TaskWidgetPage finishCreateInvestmentTask() {
    WaitHelper.waitForIFrameAvailable(driver, "iFrame");
    waitForElementDisplayed(By.id("form:invested-amount"), true);
    $(By.id("form:invested-amount")).sendKeys("1");
    clickByJavaScript($(By.id("form:save-btn")));
    driver.switchTo().defaultContent();
    WaitHelper.waitForPresenceOfElementLocatedInFrame("[id$='global-search-component:global-search-data']");
    return NavigationHelper.navigateToTaskList();
  }

  public String getTaskNameOutsideIFrameWithSkipTaskList() {
    String taskNameOutIFrameCssSelector = "span[id$='title']";
    String approveTaskNameOutIFrame = findDisplayedElementByCssSelector(taskNameOutIFrameCssSelector).getText();
    waitForCloseButtonDisplayAfterInputedAprrovalNote("1");
    driver.switchTo().defaultContent();
    WaitHelper.assertTrueWithWait(
        () -> !approveTaskNameOutIFrame.equals(findDisplayedElementByCssSelector(taskNameOutIFrameCssSelector).getText()));
    return findDisplayedElementByCssSelector(taskNameOutIFrameCssSelector).getText();
  }

  private void waitForCloseButtonDisplayAfterInputedAprrovalNote(String approvalNote) {
    driver.switchTo().frame("iFrame");
    waitForElementDisplayed(By.id("content-form:approve-btn"), true);
    $(By.id("content-form:content-tab-view:approval-note")).sendKeys(approvalNote);
    clickByJavaScript($(By.id("content-form:approve-btn")));
    waitForElementDisplayed(By.id("content-form:close-btn"), true);
  }

  public boolean isCategoryColumnDisplayed() {
    return findElementByCssSelector("td.category-column").isDisplayed();
  }

  public NewDashboardPage backToHomeInIFrameApprovalTask() {
    driver.switchTo().frame("iFrame");
    waitForElementDisplayed(By.id("content-form:home-btn"), true);
    clickByJavaScript($(By.id("content-form:home-btn")));
    driver.switchTo().defaultContent();
    return new NewDashboardPage();
  }

  public void openDocumentUploadingDialog() {
    switchToCaseInfoIframe();
    waitForElementDisplayed(By.cssSelector("a[id$='add-document-command']"), true);
    clickByJavaScript($("a[id$='add-document-command']"));
    waitForElementDisplayed(By.cssSelector("div[id$='document-upload-dialog']"), true);
  }

  public Boolean isDocumentUploadingDialogDisplayed() {
    return findElementByCssSelector("div[id$='document-upload-dialog']").isDisplayed();
  }

  public TaskDetailsPage openRelatedTaskInList(String taskName) {
    switchToCaseInfoIframe();
    Integer index = getTaskRowIndex(taskName);
    waitForElementDisplayed(By.cssSelector("td.related-task-name-column"), true);
    $$("td.related-task-name-column").get(index).click();
    return new TaskDetailsPage();
  }

  public Integer getTaskRowIndex(String taskName) {
    List<SelenideElement> taskNames = $$(".task-name-value");
    int taskIndex = IntStream.range(0, taskNames.size()).filter(i -> taskNames.get(i).getText().equals(taskName)).findFirst().getAsInt();
    return taskIndex;
  }

  private void closeReviewPage() {
    clickByJavaScript($(By.id("content-form:close-btn")));
    driver.switchTo().defaultContent();
  }

  public TaskWidgetPage finishIFrameReviewTask() {
    waitForCloseButtonDisplayAfterInputedAprrovalNote("1");
    closeReviewPage();
    return new TaskWidgetPage();
  }

  public boolean containsCaseDetails() {
    WebElement caseDetails = findDisplayedElementByCssSelector("div[id$='case-details-panel']");
    return caseDetails.isDisplayed();
  }

  public int countRelatedTasks() {
    switchToCaseInfoIframe();
    waitForElementDisplayed(By.cssSelector("td.related-task-name-column"), true);
    return $$("td.related-task-name-column").size();
  }

  public void clickTaskActionMenu() {
    clickByJavaScript($("button[id$='horizontal-task-actions']"));
    waitForElementDisplayed(By.cssSelector("[id$=':horizontal-task-action-menu']"), true);
  }

  public void startSideStep() {
    String actionPanelId = "horizontal-task-action-form:horizontal-task-action-menu";
    waitForElementDisplayed(By.id(actionPanelId), true);
    clickByJavaScript($(By.className("side-step-item")));
    waitForElementDisplayed(By.id("sidestep-task-reset-confirmation-dialog"), true);
    clickByJavaScript(findElementById("side-step-start-ok"));
    new NewDashboardPage();
  }
}

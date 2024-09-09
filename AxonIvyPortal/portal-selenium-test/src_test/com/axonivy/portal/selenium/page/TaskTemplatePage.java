package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TaskTemplatePage extends TemplatePage {
  private static final String ADHOC_HISTORY_TABLE_CSS_SELECTOR = "div[id*='adhoc-task-history-table'] table>tbody>tr";

  @Override
  protected String getLoadedLocator() {
    return ".layout-wrapper";
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
    switchToIframeWithId("i-frame-case-details");
  }

  public void addNewNote(String content) {
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
    $("[id='horizontal-task-action-form:show-adhoc-history']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    return $("div[id$='adhoc-task-history-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickChatGroup() {
    $("a[id$='chat-group']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public WebElement getAddMemberToChatDialog() {
    return $("[id='chat-assignee-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickCreateGroupChatButton() {
    $("[id='chat-assignee-selection-form:chat-group-create-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    $("[id='chat-assignee-selection-form:chat-group-create-button']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void waitUntilLayoutWrapperDisplayed() {
    waitForElementDisplayed(By.className("layout-wrapper"), Boolean.TRUE);
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
    WaitHelper.assertTrueWithWait(() -> !approveTaskNameOutIFrame
        .equals(findDisplayedElementByCssSelector(taskNameOutIFrameCssSelector).getText()));
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
    waitForElementDisplayed(By.cssSelector("a[id$='add-document-command']"), true);
    clickByJavaScript($("a[id$='add-document-command']"));
    waitForElementDisplayed(By.cssSelector("div[id$='document-upload-dialog']"), true);
  }

  public Boolean isDocumentUploadingDialogDisplayed() {
    return findElementByCssSelector("div[id$='document-upload-dialog']").isDisplayed();
  }

  public TaskDetailsPage openRelatedTaskInList(String taskName) {
    Integer index = getTaskRowIndex(taskName);
    waitForElementDisplayed(By.cssSelector("td.related-task-name-column"), true);
    $$("td.related-task-name-column").get(index).shouldBe(clickable(), DEFAULT_TIMEOUT).click();
    return new TaskDetailsPage();
  }

  public Integer getTaskRowIndex(String taskName) {
    List<SelenideElement> taskNames = $$(".task-name-value");
    int taskIndex = IntStream.range(0, taskNames.size()).filter(i -> taskNames.get(i).getText().equals(taskName))
        .findFirst().getAsInt();
    return taskIndex;
  }

  private void closeReviewPage() {
    clickByJavaScript($(By.id("content-form:close-btn")));
    driver.switchTo().defaultContent();
  }

  public TaskWidgetPage finishIFrameReviewTask() {
    waitForCloseButtonDisplayAfterInputedAprrovalNote("1");
    closeReviewPage();
    return NavigationHelper.navigateToTaskList();
  }

  public boolean containsCaseDetails() {
    return $("div[id$='case-item-details']").isDisplayed();
  }

  public int countRelatedTasks() {
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

  public void inputValue(String employee, String from, String to, String representation) {
    $(By.id("leave-request:fullname")).sendKeys(employee);
    $(By.id("leave-request:substitute")).sendKeys(representation);
    $(By.id("leave-request:from_input")).sendKeys(from);
    $(By.id("leave-request:to_input")).sendKeys(to);
  }

  public void inputField(String cssSelector, String value) {
    $(cssSelector).sendKeys(value);
  }

  public void clickSubmitButton() {
    clickOnSubmitButton();
//    return NavigationHelper.navigateToTaskList();
  }

  public void clickOnSubmitButton() {
    $("button[id$=':button-submit']").shouldBe(appear, DEFAULT_TIMEOUT);
    clickByJavaScript($("button[id$=':button-submit']"));
  }

  public void clickAdhocCreationButton() {
    clickTaskActionMenu();
    clickOnStartAdhocLink();
  }

  public boolean isShowAdhocHistoryBtnNotExist() {
    String adhocHistoryBtnCSSSelection = "a[id$='show-adhoc-history']";
    return !$(By.cssSelector(adhocHistoryBtnCSSSelection)).exists();
  }

  public boolean isStartAdhocBtnNotExist() {
    String startAdhocBtnCSSSelection = "a[id$='start-adhoc']";
    return !$(By.cssSelector(startAdhocBtnCSSSelection)).exists();
  }

  public boolean isAdhocHistoryDialogExistWhenOpenTaskFirstTime() {
    return findElementByCssSelector("div[id$='adhoc-task-history-dialog']").isDisplayed();
  }

  public SelenideElement getAdhocHistoryDialog() {
    return $("div[id$='adhoc-task-history-dialog']");
  }

  public void clickShowAdhocHistoryBtn() {
    waitForElementClickableThenClick("#horizontal-task-actions");
    waitForElementDisplayed(By.cssSelector("a[id$='show-adhoc-history']"), true);
    waitForElementClickableThenClick("a[id$='show-adhoc-history']");
  }

  public String getAdhocCreationMessage() {
    String adhocCreationMessageCSSSelector = "div[id$='adhoc-creation-message']";
    return findDisplayedElementByCssSelector(adhocCreationMessageCSSSelector).getText();
  }

  public String getTaskNameOfAdhocHistoryRow(int index) {
    SelenideElement elem = $$(By.cssSelector(ADHOC_HISTORY_TABLE_CSS_SELECTOR)).get(index);
    return elem.findAll(By.xpath("td")).get(2).getText();
  }

  public String getCommentOfAdhocHistoryRow(int index) {
    SelenideElement elem = $$(By.cssSelector(ADHOC_HISTORY_TABLE_CSS_SELECTOR)).get(index);
    return elem.findAll(By.xpath("td")).get(3).getText();
  }

  public void joinProcessChatAlreadyCreated() {
    waitForElementDisplayed(By.id("chat-group-join-form:chat-group-join-button"), true);
    waitForElementClickableThenClick($(By.id("chat-group-join-form:chat-group-join-button")));
    waitForElementDisplayed(By.id("chat-form:group-chat-container"), true);
  }

  public void clickCreateGroupChatBtn() {
    waitForElementDisplayed(By.id("chat-assignee-selection-form:chat-group-create-button"), true);
    waitForElementClickableThenClick(By.id("chat-assignee-selection-form:chat-group-create-button"));
    waitForElementDisplayed(By.id("chat-assignee-selection-form:chat-group-create-button"), false);
  }

  public TaskWidgetPage clickCancelAndLeftButton() {
    waitForElementClickableThenClick("a[id$='button-cancel']");
    return new TaskWidgetPage();
  }

  public int countSideSteps() {
    waitForElementDisplayed($("[id$='horizontal-task-action-menu']"), true);
    return $$("a.side-step-item").shouldHave(CollectionCondition.size(2)).size();
  }

  public void openFinishedTaskInHistoryArea() {
    SelenideElement element = $("[id$=':case-histories:show-more-note-link']").scrollTo();
    String url = element.getAttribute("href");
    ((JavascriptExecutor) driver).executeScript("window.open('" + url + "','_blank');");
  }
  
  public void waitForLeftMenuActive() {
    $(".menu-item-dashboard").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void waitForApprovalStepRendered() {
    waitForElementDisplayed(By.id("iFrame"), true);
    waitForElementDisplayed(By.id("task-template-title"), true);
  }
  
  public void waitForMainAreaPanelRendered() {
    $("div[id$='main-area-panel']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void waitForOverlayGuideRendered() {
    $("div[id$='welcome-portal-guide']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public String getTaskName() {
    $("#title").shouldBe(appear, DEFAULT_TIMEOUT);
    return $("#title").getText();
  }
  
  public void waitForIFrameContentVisible() {
    $("div[id='content-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}

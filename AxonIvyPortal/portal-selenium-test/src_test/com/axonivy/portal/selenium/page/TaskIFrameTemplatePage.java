package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TaskIFrameTemplatePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#content";
  }

  public SelenideElement getDisplayedTaskTitle() {
    switchBackToParent();
    return getTaskTitle().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getTaskTitle() {
    return $("span[id='title']");
  }

  public SelenideElement getStartedTaskTemplateTitle() {
    return $("span[id='title']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openCaseInfo() {
    switchBackToParent();
    waitForElementClickableThenClick("[id='horizontal-case-info']");
    waitForElementDisplayed(By.cssSelector("[id$='i-frame-case-details']"), true);
    // driver.switchTo().defaultContent();
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
    $("a[id$='button-cancel']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickActionButton() {
    $("[id='horizontal-task-actions']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='horizontal-task-action-form:horizontal-task-action-menu']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  // moved
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

  public String getTaskName() {
    return getTextOfCurrentBreadcrumb().replace("Task: ", "");
  }

  public void clickCancelLink() {
    clickByJavaScript($(By.linkText("Cancel")));
    switchBackToParent();
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

  public NewDashboardPage finishCreateInvestmentTask() {
    WaitHelper.waitForIFrameAvailable(driver, "iFrame");
    waitForElementDisplayed(By.id("form:invested-amount"), true);
    $(By.id("form:invested-amount")).sendKeys("1");
    clickByJavaScript($(By.id("form:save-btn")));
    switchToDefaultContent();
    WaitHelper.waitForPresenceOfElementLocatedInFrame("[id$='global-search-component:global-search-data']");
    return NavigationHelper.navigateToTaskList();
  }

  public String getTaskNameOutsideIFrameWithSkipTaskList() {
    switchToDefaultContent();
    String taskNameOutIFrameCssSelector = "span[id$='title']";
    String approveTaskNameOutIFrame = findDisplayedElementByCssSelector(taskNameOutIFrameCssSelector).getText();
    switchToIFrameOfTask();
    waitForCloseButtonDisplayAfterInputedAprrovalNote("1");
    switchToDefaultContent();
    WaitHelper.assertTrueWithWait(() -> !approveTaskNameOutIFrame
        .equals(findDisplayedElementByCssSelector(taskNameOutIFrameCssSelector).getText()));
    return findDisplayedElementByCssSelector(taskNameOutIFrameCssSelector).getText();
  }

  private void waitForCloseButtonDisplayAfterInputedAprrovalNote(String approvalNote) {
    waitForElementDisplayed(By.id("content-form:approve-btn"), true);
    $(By.id("content-form:content-tab-view:approval-note")).sendKeys(approvalNote);
    clickByJavaScript($(By.id("content-form:approve-btn")));
    waitForElementDisplayed(By.id("content-form:close-btn"), true);
  }

  public boolean isCategoryColumnDisplayed() {
    return findElementByCssSelector("td.category-column").isDisplayed();
  }

  public NewDashboardPage backToHomeInIFrameApprovalTask() {
    waitForElementDisplayed(By.id("content-form:home-btn"), true);
    clickByJavaScript($(By.id("content-form:home-btn")));
    switchToDefaultContent();
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
    ElementsCollection taskNames = $$(".task-name-value");
    int taskIndex = IntStream.range(0, taskNames.size()).filter(i -> taskNames.get(i).getText().equals(taskName))
        .findFirst().getAsInt();
    return taskIndex;
  }

  private void closeReviewPage() {
    clickByJavaScript($(By.id("content-form:close-btn")));
    switchToDefaultContent();
  }

  public NewDashboardPage finishIFrameReviewTask() {
    waitForCloseButtonDisplayAfterInputedAprrovalNote("1");
    closeReviewPage();
    return new NewDashboardPage();
  }

  public boolean containsCaseDetails() {
    return $("div[id$='case-item-details']").isDisplayed();
  }

  public int countRelatedTasks() {
    waitForElementDisplayed(By.cssSelector("td.related-task-name-column"), true);
    return $$("td.related-task-name-column").size();
  }

  // moved
  public void clickTaskActionMenu() {
    switchBackToParent();
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
    SelenideElement fullNameElement =
        $(By.id("leave-request:fullname")).shouldBe(Condition.appear).shouldBe(Condition.editable);
    fullNameElement.click();
    fullNameElement.sendKeys(employee);
    SelenideElement representationElement = $(By.id("leave-request:substitute"));
    representationElement.click();
    representationElement.sendKeys(representation);
    SelenideElement fromElement = $(By.id("leave-request:from_input"));
    fromElement.click();
    fromElement.sendKeys(from);
    SelenideElement toElement = $(By.id("leave-request:to_input"));
    toElement.click();
    toElement.sendKeys(to);
  }

  public NewDashboardPage clickSubmitButton() {
    clickOnSubmitButton();
    return new NewDashboardPage();
  }

  public void clickSubmitButtonProceed() {
    $("button[id='form:proceed']").shouldBe(clickable(), DEFAULT_TIMEOUT).click();
    $("button[id='form:proceed']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void clickOnSubmitButton() {
    clickByJavaScript($("button[id$=':button-submit']"));
    switchBackToParent();
  }
  
  public void clickOnCancelButton() {
    clickByJavaScript($("a[id$=':button-cancel']"));
    switchBackToParent();
  }

  // moved
  public void joinProcessChatAlreadyCreated() {
    waitForElementDisplayed(By.id("chat-group-join-form:chat-group-join-button"), true);
    waitForElementClickableThenClick($(By.id("chat-group-join-form:chat-group-join-button")));
    waitForElementDisplayed(By.id("chat-form:group-chat-container"), true);
  }

  // moved
  public void clickCreateGroupChatBtn() {
    waitForElementDisplayed(By.id("chat-assignee-selection-form:chat-group-create-button"), true);
    waitForElementClickableThenClick(By.id("chat-assignee-selection-form:chat-group-create-button"));
    waitForElementDisplayed(By.id("chat-assignee-selection-form:chat-group-create-button"), false);
  }

  public void clickCancelAndLeftButton() {
    waitForElementClickableThenClick("a[id$='button-cancel']");
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

  public void inputFields(String employee, String from, String to, String representation) {
    $(By.id("leave-request:fullname")).sendKeys(employee);
    $(By.id("leave-request:from_input")).sendKeys(from);
    $(By.id("leave-request:to_input")).sendKeys(to);
    $(By.id("leave-request:substitute")).sendKeys(representation);
  }

  public void switchToCaseInfoIframe() {
    switchToIframeWithId("i-frame-case-details");
  }
}

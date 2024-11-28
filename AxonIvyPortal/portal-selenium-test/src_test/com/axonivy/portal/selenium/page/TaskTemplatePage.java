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

public class TaskTemplatePage extends TemplatePage {

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
    return $(cssSelector);
  }

  public void clickCancelButton() {
    $("a[id$='button-cancel']").shouldBe(getClickableCondition()).click();
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
    return $("#title").getText();
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
    waitForElementClickableThenClick($(By.id("form:save-btn")));
    waitPageDisappear();
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
    $(By.id("leave-request:fullname")).sendKeys(employee);;
    $(By.id("leave-request:from_input")).sendKeys(from);;
    $(By.id("leave-request:to_input")).sendKeys(to);;
    $(By.id("leave-request:substitute")).sendKeys(representation);;
  }

  public CaseDetailsPage goToCaseDetail() {
    waitForElementClickableThenClick("[id$='form:go-to-case-detail']");
    waitForPageLoad();
    return new CaseDetailsPage();
  }

}

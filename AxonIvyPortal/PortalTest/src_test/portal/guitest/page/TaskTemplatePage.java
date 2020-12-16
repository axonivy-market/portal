package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class TaskTemplatePage extends TemplatePage {

  private static final String ADHOC_HISTORY_TABLE_CSS_SELECTOR = "div[id*='adhoc-task-history-table'] table>tbody>tr";

  @Override
  protected String getLoadedLocator() {
    return "id('content-container')";
  }

  public CaseInformationPage openCaseInfo() {
    clickByCssSelector("#horizontal-case-info");
    waitForElementDisplayed(By.cssSelector("span[id$='case-info-dialog_title']"), true);
    return new CaseInformationPage();
  }

  public boolean containsCaseDetails() {
    WebElement caseDetails = findDisplayedElementByCssSelector("div[id$='case-details-panel']");
    return caseDetails.isDisplayed();
  }

  public void addNewNote(String content) {
    clickByCssSelector("a[id$='add-note-command']");
    waitForElementDisplayed(By.cssSelector("div[id$='add-note-dialog']"), true);
    findElementByCssSelector("textarea[id$='note-content']").sendKeys(content);
    clickByCssSelector("button[id$='save-add-note-command']");
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.cssSelector("div[id$='add-note-dialog']"), false);
  }

  public void openDocumentUploadingDialog() {
    clickByCssSelector("a[id$='add-document-command']");
    waitForElementDisplayed(By.cssSelector("div[id$='document-upload-dialog']"), true);
  }

  public Boolean isDocumentUploadingDialogDisplayed() {
	return findElementByCssSelector("div[id$='document-upload-dialog']").isDisplayed();
  }

  public void openFinishedTaskInHistoryArea() {
    WebElement element = findElementById("case-item:history:show-more-note-link");
    String url = element.getAttribute("href");
    ((JavascriptExecutor) driver).executeScript("window.open('"+ url +"','_blank');");
  }

  public TaskDetailsPage openFirstRelatedTaskInHistoryArea() {
    String firstFinishedTaskCssSelector = "a[id$='task-name']";
    return openATaskInCaseDetails(firstFinishedTaskCssSelector);
  }

  private TaskDetailsPage openATaskInCaseDetails(String taskCssSelector) {
    clickByCssSelector(taskCssSelector);
    return new TaskDetailsPage();
  }

  public int countNoteItems() {
    return findListElementsByCssSelector("a[id$=':note-link']").size();
  }

  public int countHistoryItems() {
    return findListElementsByCssSelector("a[id$='show-task-note-link']")
        .size();
  }

  public int countRelatedTasks() {
    return findListElementsByCssSelector("a[id$='task-name']").size();
  }

  public void openActionMenu() {
    String actionButtonId = "horizontal-task-actions";
    waitForElementDisplayed(By.id(actionButtonId), true);
    click(findElementById(actionButtonId));
  }
  
  public void startSideStep() {
    String actionPanelId = "horizontal-task-action-form:horizontal-task-action-menu";
    waitForElementDisplayed(By.id(actionPanelId), true);
    click(findElementByClassName("side-step-item"));
    waitForElementDisplayed(By.id("sidestep-task-reset-confirmation-dialog"), true);
    click(findElementById("side-step-start-ok"));
  }

  public int countSideSteps() {
    String sideStepPanelId = "side-steps-panel";
    waitForElementDisplayed(By.id(sideStepPanelId), true);
    return findListElementsByCssSelector("a[id$='side-step-item']").size();
  }
  
  public void clickCancelButton() {
    click(driver.findElement(By.className("portal-cancel-button")));
  }
  
  public void showNoteHistory() {
    click(driver.findElement(By.cssSelector("a[id$='show-more-note-link']")));
  }
  
  public String getCaseName(){
    return findElementByCssSelector("span[id$='case-detail-title-form:case-name-edit-inplace_display']").getText();
  }
  
  public String getCaseId(){
    return findElementById("general-information:case-id").getText();
  }
  
  public void clickAdhocCreationButton() {
    clickByCssSelector("#horizontal-task-actions");
    clickByCssSelector("a[id$='start-adhoc']");
    waitAjaxIndicatorDisappear();
  }
  
  public void clickAdhocOkButton() {
    clickByCssSelector("button[id$='start-adhoc-ok-button']");
    waitAjaxIndicatorDisappear();
  }
  
  public boolean isShowAdhocHistoryBtnNotExist() {
    String adhocHistoryBtnCSSSelection = "a[id$='show-adhoc-history']";
    return driver.findElements(By.cssSelector(adhocHistoryBtnCSSSelection)).isEmpty();
  }

  public boolean isStartAdhocBtnNotExist() {
    String startAdhocBtnCSSSelection = "a[id$='start-adhoc']";
    return driver.findElements(By.cssSelector(startAdhocBtnCSSSelection)).isEmpty();
  }

  public boolean isAdhocHistoryDialogExistWhenOpenTaskFirstTime() {
    waitAjaxIndicatorDisappear();
    return findElementByCssSelector("div[id$='adhoc-task-history-dialog']").isDisplayed();
  }
  
  public boolean isAdhocHistoryDialogExist() {
    return findElementByCssSelector("div[id$='adhoc-task-history-dialog']").isDisplayed();
  }
  
  public void clickShowAdhocHistoryBtn() {
    clickByCssSelector("#horizontal-task-actions");
    waitForElementDisplayed(By.cssSelector("a[id$='show-adhoc-history']"), true);
    clickByCssSelector("a[id$='show-adhoc-history']");
    waitAjaxIndicatorDisappear();
  }

  public String getTaskNameOfAdhocHistoryRow(int index) {
    WebElement row = driver.findElements(By.cssSelector(ADHOC_HISTORY_TABLE_CSS_SELECTOR)).get(index);
    return row.findElements(By.xpath("td")).get(2).getText();
  }
  
  public String getCommentOfAdhocHistoryRow(int index) {
    WebElement row = driver.findElements(By.cssSelector(ADHOC_HISTORY_TABLE_CSS_SELECTOR)).get(index);
    return row.findElements(By.xpath("td")).get(3).getText();
  }
  
  public void closeAdhocHistoryDialog() {
    clickByCssSelector("button[id$='close-adhoc-dialog-button']");
    waitAjaxIndicatorDisappear();
  }
  
  public String getAdhocCreationMessage() {
    String adhocCreationMessageCSSSelector = "span[id$='adhoc-creation-message']";
    return findDisplayedElementByCssSelector(adhocCreationMessageCSSSelector).getText();
  }

  public HomePage clickSubmitButton() {
    String submitButton = "button[id$='button-submit']";
    clickByCssSelector(submitButton);
    return new HomePage();
  }
  
  public HomePage clickCancelAndLeftButton() {
    String cancelButton = "button[id$='button-cancel']";
    clickByCssSelector(cancelButton);
    return new HomePage();
  }
  public void clickTaskActionMenu() {
	    String taskAction = "button[id$='horizontal-task-actions']";
	    clickByCssSelector(taskAction);
}
  
  public void clickChatGroup(boolean growlMessageExpected) {
    String chatGroup = "a[id$='chat-group']";
    clickByCssSelector(chatGroup);
    if (growlMessageExpected) {
      waitForElementDisplayedByCssSelector("span.ui-growl-title", 5);
    }
  }
  
  public void joinProcessChatAlreadyCreated() {
  	waitForElementDisplayed(By.id("chat-group-join-form:chat-group-join-button"), true);
  	click(By.id("chat-group-join-form:chat-group-join-button"));
  	waitAjaxIndicatorDisappear();
  }
  
  public void inputFields(String employee, String from, String to, String representation) {
    type(By.id("leave-request:fullname"), employee);
    type(By.id("leave-request:from_input"), from);
    type(By.id("leave-request:to_input"), to);
    type(By.id("leave-request:substitute"), representation);
  }
  
  public boolean isTaskNameDisplayed() {
    return isElementDisplayedById("title");
  }
  
  public boolean isCaseInfoButtonDisplayed() {
    return isElementDisplayedById("horizontal-case-info");
  }
  
  public boolean isTaskActionDisplayed() {
    return isElementDisplayedById("horizontal-task-actions");
  }
  public String getTaskName() {
    return findElementById("title").getText();
  }

  public void clickLeaveTaskOnWarningDialog() {
    By leaveButton = By.id("task-leave-warning-component:leave-button");
    waitForElementDisplayed(leaveButton, true);
    click(leaveButton);
  }
  
  public TaskWidgetPage finishCreateInvestmentTask() {
    driver.switchTo().frame("iFrame");
    type(By.id("form:invested-amount"), "1");
    click(By.id("form:save-btn"));
    driver.switchTo().defaultContent();
    return new TaskWidgetPage();
  }

  public boolean isTextOutIFrameChangedWithSkipTaskList() {
    waitForElementDisplayed(By.id("iFrame"), true);
    String taskNameOutIFrameCssSelector = "span[id$='title']";
    String approveTaskNameOutIFrame = findDisplayedElementByCssSelector(taskNameOutIFrameCssSelector).getText();
    processDataFromApproveToReview();
    waitAjaxIndicatorDisappear();
    String reviewTaskNameOutIFrame = findDisplayedElementByCssSelector(taskNameOutIFrameCssSelector).getText();
    return !approveTaskNameOutIFrame.equals(reviewTaskNameOutIFrame); 
  }

  private void processDataFromApproveToReview() {
    driver.switchTo().frame("iFrame");
    waitForElementDisplayed(By.id("content-form:investment-info:approval-note"), true);
    type(By.id("content-form:investment-info:approval-note"), "OK");
    WebElement approveButtonGroupElement = findDisplayedElementByCssSelector("table[id$='content-form:investment-info:is-approved']");
    WebElement approveButtonLabelElement = approveButtonGroupElement.findElement(By.cssSelector("label"));
    approveButtonLabelElement.click();
    waitForElementDisplayed(By.id("content-form:approve-btn"), true);
    clickByCssSelector("button[id$='content-form:approve-btn']");
    driver.switchTo().defaultContent();
    waitAjaxIndicatorDisappear();
    driver.switchTo().frame("iFrame");
    waitForElementDisplayed(By.id("content-form:close-btn"), true);
    clickByCssSelector("button[id$='content-form:close-btn']");
    driver.switchTo().defaultContent();
  }
}

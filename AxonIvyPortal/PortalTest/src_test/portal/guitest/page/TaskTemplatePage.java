package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class TaskTemplatePage extends TemplatePage {

  String ADHOC_HISTORY_TABLE_CSS_SELECTOR = "div[id*='adhoc-task-history-table'] table>tbody>tr";

  @Override
  protected String getLoadedLocator() {
    return "id('task-actions')";
  }

  public void openStatusTab() {
    String statusTabXpath = "//a[@href='#status-tab']";
    waitForElementDisplayed(By.xpath(statusTabXpath), true);
    click(findElementByXpath(statusTabXpath));
  }

  public boolean containsCaseDetails() {
    WebElement caseDetails = findElementByCssSelector("div[id$='case-details-panel']");
    return caseDetails.isDisplayed();
  }

  public void addNewNote(String content) {
    findElementByCssSelector("a[id$='add-note-command']").click();
    waitForElementDisplayed(By.cssSelector("div[id$='add-note-dialog']"), true);
    findElementByCssSelector("textarea[id$='note-content']").sendKeys(content);
    findElementByCssSelector("button[id$='save-add-note-command']").click();
    waitAjaxIndicatorDisappear();
  }

  public void openDocumentUploadingDialog() {
    findElementByCssSelector("a[id$='add-document-command']").click();
    waitForElementDisplayed(By.cssSelector("a[id$='add-document-command']"), true);
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

  public TaskDetailsPage openATaskInCaseDetails(String taskCssSelector) {
    WebElement task = findElementByCssSelector(taskCssSelector);
    task.click();
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

  public void openSideStepMenu() {
    String sideStepId = "side-steps-menu";
    waitForElementDisplayed(By.id(sideStepId), true);
    click(findElementById(sideStepId));
  }

  public int countSideSteps() {
    String sideStepPanelId = "side-steps-panel";
    waitForElementDisplayed(By.id(sideStepPanelId), true);
    return findListElementsByCssSelector("a[id$='side-step-item']").size();
  }
  
  public void clickCancelButton() {
    driver.findElement(By.className("portal-cancel-button")).click();
  }
  
  public void showNoteHistory() {
    click(driver.findElement(By.cssSelector("a[id$='show-more-note-link']")));
  }
  
  public String getCaseName(){
    return findElementById("case-item:case-name-component:case-header-name-cell").getText();
  }
  
  public void clickAdhocCreationButton() {
    findElementByCssSelector("#task-actions").click();
    waitForElementDisplayed(By.cssSelector("a[id$='start-adhoc']"), true);
    findElementByCssSelector("a[id$='start-adhoc']").click();
    waitAjaxIndicatorDisappear();
  }
  
  public void clickAdhocOkButton() {
    String adhocButtonCssSelection = "button[id$='start-adhoc-ok-button']";
    findElementByCssSelector(adhocButtonCssSelection).click();
    waitAjaxIndicatorDisappear();
  }
  
  public void clickAdhocCancelButton() {
    String adhocButtonCssSelection = "button[id$='start-adhoc-cancel-button']";
    findElementByCssSelector(adhocButtonCssSelection).click();
    waitAjaxIndicatorDisappear();
  }
  
  
  public boolean isShowAdhocHistoryBtnNotExist() {
    String adhocHistoryBtnCSSSelection = "a[id$='show-adhoc-history']";
    return driver.findElements(By.cssSelector(adhocHistoryBtnCSSSelection)).isEmpty();
  }
  
  public boolean isAdhocHistoryDialogExist() {
    return findElementByCssSelector("div[id$='adhoc-task-history-dialog']").isDisplayed();
  }
  
  public void clickShowAdhocHistoryBtn() {
    findElementByCssSelector("#task-actions").click();
    waitForElementDisplayed(By.cssSelector("a[id$='show-adhoc-history']"), true);
    findElementByCssSelector("a[id$='show-adhoc-history']").click();
    waitAjaxIndicatorDisappear();
  }

  public String getTaskNameOfAdhocHistoryRow(int index) {
    WebElement row = driver.findElements(By.cssSelector(ADHOC_HISTORY_TABLE_CSS_SELECTOR)).get(index);
    return row.findElements(By.xpath("td")).get(1).getText();
  }
  
  public String getCommentOfAdhocHistoryRow(int index) {
    WebElement row = driver.findElements(By.cssSelector(ADHOC_HISTORY_TABLE_CSS_SELECTOR)).get(index);
    return row.findElements(By.xpath("td")).get(2).getText();
  }
  
  public void closeAdhocHistoryDialog() {
    findElementByCssSelector("button[id$='close-adhoc-dialog-button']").click();
    waitAjaxIndicatorDisappear();
  }
  
  public String getAdhocCreationMessage() {
    String adhocCreationMessageCSSSelector = "span[id$='adhoc-creation-message']";
    return findDisplayedElementBySelector(adhocCreationMessageCSSSelector).getText();
  }

  public void clickSubmitButton() {
    String submitButton = "button[id$='command-form:button-submit']";
    findElementByCssSelector(submitButton).click();
    waitAjaxIndicatorDisappear();
  }
  
  public void clickChatGroup() {
    String chatGroup = "a[id$='chat-group']";
    findElementByCssSelector(chatGroup).click();
    waitAjaxIndicatorDisappear();
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
}

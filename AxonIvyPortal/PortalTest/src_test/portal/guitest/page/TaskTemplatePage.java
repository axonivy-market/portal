package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class TaskTemplatePage extends TemplatePage {

  String ADHOC_HISTORY_TABLE_CSS_SELECTOR = "div[id*='adhoc-task-history-table'] table>tbody>tr";

  @Override
  protected String getLoadedLocator() {
    return "id('side-steps-menu')";
  }

  public void openStatusTab() {
    String statusTabXpath = "//a[@href='#status-tab']";
    waitForElementDisplayed(By.xpath(statusTabXpath), true);
    click(findElementByXpath(statusTabXpath));
  }

  public boolean containsCaseDetails() {
    WebElement caseDetails = findElementByCssSelector("div[id$='case-item']");
    return caseDetails.isDisplayed();
  }

  public void addNewNote(String content) {
    String addNoteButtonId = "case-item:history:add-note-command";
    String addNoteDialogId = "case-item:history:add-note-dialog";
    findElementById(addNoteButtonId).click();
    waitForElementDisplayed(By.cssSelector("div[id^='" + addNoteDialogId + "']"), true);
    WebElement addNoteDialog = findElementByCssSelector("div[id^='" + addNoteDialogId + "']");
    addNoteDialog.findElement(By.cssSelector("textarea[id$='note-content']")).sendKeys(content);
    addNoteDialog.findElement(By.cssSelector("button[id$='save-add-note-command']")).click();
    waitAjaxIndicatorDisappear();
  }

  public void openDocumentUploadingDialog() {
    findElementById("case-item:document:add-document-command").click();
    String documentUploadingDialogId = "case-item:document:document-upload-dialog";
    waitForElementDisplayed(By.cssSelector("[id^='" + documentUploadingDialogId +"']"), true);
  }

  public Boolean isDocumentUploadingDialogDisplayed() {
    String documentUploadingDialogId = "case-item:document:document-upload-dialog";
    return findElementByCssSelector("[id^='" + documentUploadingDialogId +"']").isDisplayed();
  }

  public void openFinishedTaskInHistoryArea() {
    WebElement element = findElementById("case-item:history:show-more-note-link");
    String url = element.getAttribute("href");
    ((JavascriptExecutor) driver).executeScript("window.open('"+ url +"','_blank');");
  }

  public TaskWidgetPage openFirstRelatedTaskInHistoryArea() {
    String firstFinishedTaskCssSelector = "div[id='case-item:related-tasks'] a";
    return openATaskInCaseDetails(firstFinishedTaskCssSelector);
  }

  public TaskWidgetPage openATaskInCaseDetails(String taskCssSelector) {
    WebElement task = findElementByCssSelector(taskCssSelector);
    task.click();
    return new TaskWidgetPage();
  }

  public int countNoteItems() {
    return findListElementsByCssSelector("a[id$='note-content']").size();
  }

  public int countHistoryItems() {
    return findListElementsByCssSelector("a[id$='show-task-note-link']")
        .size();
  }

  public int countRelatedTasks() {
    return findListElementsByCssSelector("div[id='case-item:related-tasks'] a").size();
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
    String adhocButtonCssSelection = "a[id$='start-adhoc']";
    findElementByCssSelector(adhocButtonCssSelection).click();
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
    String adhocHistoryBtnCSSSelection = "div[id$='adhoc-task-history-dialog']";
    return findElementByCssSelector(adhocHistoryBtnCSSSelection).isDisplayed();
  }
  
  public void clickShowAdhocHistoryBtn() {
    String adhocHistoryBtnCSSSelection = "a[id$='show-adhoc-history']";
    findElementByCssSelector(adhocHistoryBtnCSSSelection).click();
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
    String adhocButtonCssSelection = "button[id$='close-adhoc-dialog-button']";
    findElementByCssSelector(adhocButtonCssSelection).click();
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
